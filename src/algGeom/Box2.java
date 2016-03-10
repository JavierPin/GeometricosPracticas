package algGeom;

import com.sun.opengl.util.Animator;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


public class Box2 implements GLEventListener, 
                             MouseListener, 
                             MouseMotionListener,
                             KeyListener
{
    GLU glu;
    GL gl;
    
    // translate the scene
    private float view_trax = 0.0f;
    private float view_tray = 0.0f;
    private float view_traz = 0.0f;
    // rotating the scene
    private float view_rotx = 0.0f; //20
    private float view_roty = 0.0f; //30
    // remember last mouse position
    private int oldMouseX;
    private int oldMouseY;
    //static int HEIGHT = 800, WIDTH = 800;
    static int HEIGHT = 10, WIDTH = 10;
    
    public static void main(String[] args) {
    	Draw.ALTO = HEIGHT;
    	Draw.ANCHO = WIDTH;
    	Draw.FONDO = 100;
    			
        Frame frame = new Frame("Libreria 3D");
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new Box2());
        frame.add(canvas);
        frame.setSize(800, 800);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
        
    }
    
    public void init(GLAutoDrawable drawable)
    {
        GL gl = drawable.getGL();
        glu = new GLU();
        
        // Set backgroundcolor and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f); //Como que duele un poco en blanco
        //gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); //En negro no duele tanto
        gl.glShadeModel(GL.GL_FLAT);
        // descomentar esto para poder ver las sombras de los modelos 
        /*
        float ambient[] = {1.0f,1.0f,1.0f,1.0f };
        float diffuse[] = {1.0f,1.0f,1.0f,1.0f };
        float specular[]= {0.2f,1.0f,0.2f,1.0f};
        float position[] = {20.0f,30.0f,20.0f,0.0f };
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT,ambient,0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse,0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position,0);
        gl.glMaterialfv(GL.GL_FRONT,GL.GL_SPECULAR, specular, 0);
        // and some red material
        float[] mambient ={ 0.1745f, 0.01175f, 0.01175f, 0.55f };
        float[] mdiffuse ={0.61424f, 0.04136f, 0.04136f, 0.55f };
        float[] mspecular ={0.727811f, 0.626959f, 0.626959f, 0.55f };
        float mshine =76.8f ;
        gl.glMaterialfv(GL.GL_FRONT,GL.GL_AMBIENT,mambient,0);
        gl.glMaterialfv(GL.GL_FRONT,GL.GL_DIFFUSE,mdiffuse,0);
        gl.glMaterialfv(GL.GL_FRONT,GL.GL_SPECULAR,mspecular, 0);
        gl.glMaterialf (GL.GL_FRONT,GL.GL_SHININESS,mshine);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_NORMALIZE);
        */
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
    }
    
    public void reshape(GLAutoDrawable drawable, 
                        int x, int y, int width, int height)
    {
	WIDTH = width; // new width and height saved
        HEIGHT = height;

        GL gl = drawable.getGL();
        GLU glu = new GLU();
        if (height <= 0) // no divide by zero
            height = 1;
        // keep ratio
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        
    }
    
    public void display(GLAutoDrawable drawable)
    {
        
        gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);


        gl.glLoadIdentity();
        glu.gluLookAt(6,10,10,  // eye pos
                     0,0,0,   // look at
                     0,1,0);  // up

        gl.glTranslatef(view_trax, view_tray, view_traz);       
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);


        //Dibujar los ejes y los planos de cada eje
        //Ejes
        DrawAxis3d axis = new DrawAxis3d();
        axis.drawColoredObject(gl);
        
        
        //Planos
        Vect3d xInf,yInf,zInf;
        xInf= new Vect3d(100,0,0);
        yInf= new Vect3d(0,100,0);
        zInf= new Vect3d(0,0,0);
         
        Plane pEje = new Plane(xInf,yInf,zInf,true);
        DrawPlane dpEje= new DrawPlane(pEje);
        dpEje.drawObjectC(gl ,0.0f, 1.0f, 0.0f, 0.3f);
         
        xInf= new Vect3d(100,0,0);
        yInf= new Vect3d(0,0,0);
        zInf= new Vect3d(0,0,100);
         
        pEje = new Plane(xInf,yInf,zInf,true);
        dpEje= new DrawPlane(pEje);
        dpEje.drawObjectC(gl ,1.0f, 0.0f, 0.0f, 0.3f);
        
        xInf= new Vect3d(0,0,0);
        yInf= new Vect3d(0,100,0);
        zInf= new Vect3d(0,0,100);
        
        pEje = new Plane(xInf,yInf,zInf,true);
        dpEje= new DrawPlane(pEje);
        dpEje.drawObjectC(gl ,0.0f, 0.0f, 1.0f, 0.3f);
        
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        
        Triangle3d t1 = new Triangle3d (    new Vect3d (3, 4, 3),
                                            new Vect3d (4, 3, 3),
                                            new Vect3d (3, 3, 4)
                                        );
        
        Triangle3d t1x = t1.proyecta_XY();
        Triangle3d t1y = t1.proyecta_YZ();
        Triangle3d t1z = t1.proyecta_XZ();

        //Calculamos primero los triangulos proyectados pero los pintamos al final
        //Importa el orden de dibujado, si pintamos los triangulos al principio, las lineas se superponen a la geometria
        Segment3d l = new Segment3d(t1.getA(),t1x.getA());
        DrawSegment3d line = new DrawSegment3d(l);
        line.drawObjectC(gl,0,0,0);
        l = new Segment3d(t1.getB(),t1x.getB());
        line = new DrawSegment3d(l);
        line.drawObjectC(gl,0,0,0);
        l = new Segment3d(t1.getC(),t1x.getC());
        line = new DrawSegment3d(l);
        line.drawObjectC(gl,0,0,0);
        
        l = new Segment3d(t1.getA(),t1y.getA());
        line = new DrawSegment3d(l);
        line.drawObjectC(gl,0,0,0);
        l = new Segment3d(t1.getB(),t1y.getB());
        line = new DrawSegment3d(l);
        line.drawObjectC(gl,0,0,0);
        l = new Segment3d(t1.getC(),t1y.getC());
        line = new DrawSegment3d(l);
        line.drawObjectC(gl,0,0,0);
        
        l = new Segment3d(t1.getA(),t1z.getA());
        line = new DrawSegment3d(l);
        line.drawObjectC(gl,0,0,0);
        l = new Segment3d(t1.getB(),t1z.getB());
        line = new DrawSegment3d(l);
        line.drawObjectC(gl,0,0,0);
        l = new Segment3d(t1.getC(),t1z.getC());
        line = new DrawSegment3d(l);
        line.drawObjectC(gl,0,0,0);
        
        DrawTriangle3d dt1 = new DrawTriangle3d(t1x);
        dt1.drawObjectC(gl, 1.0f,0,0);
        dt1 = new DrawTriangle3d(t1y);
        dt1.drawObjectC(gl, 0,1.0f,0);
        dt1 = new DrawTriangle3d(t1z);
        dt1.drawObjectC(gl, 0,0,1.0f);
        
        
        
        dt1 = new DrawTriangle3d (t1);
        dt1.drawObjectC(gl,0.5f,0.5f,0.5f);
        
        //Cloud3d c = new Cloud3d (30);

        Vect3d[] inters = new Vect3d[1];
        inters[0]= new Vect3d(0,0,0);
        
        Line3d l2 = new Line3d(new Vect3d(0,4,1),new Vect3d(7,0,0));
        DrawLine3d line2 = new DrawLine3d(l2);
        line2.drawObjectC(gl,0.9f,0.9f,0.9f);
        
        if(t1.LineTriangle3d(l2, inters[0])){
            
            System.out.println("El triangulo t1 intersecta con la recta r en:");
            inters[0].out();
            
            Vect3d pu = new Vect3d(inters[0]);
            DrawVect3d punto = new DrawVect3d(pu);
            punto.drawObjectC(gl, 1,0,0);
            
        }
        
        
        
        AABB b = new AABB(new Vect3d(3,3,3), new Vect3d(4,4,4));
        DrawAABB box = new DrawAABB(b);
        box.drawWireObject(gl);
        
        gl.glFlush();
        
    }
    
    public void displayChanged(GLAutoDrawable drawable, 
                              boolean modeChanged, boolean deviceChanged)
    { }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) { }
    public void mousePressed(MouseEvent e) {
        oldMouseX = e.getX();
        oldMouseY = e.getY();
    }
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Dimension size = e.getComponent().getSize();
        float thetaY = 360.0f * ( (float)(x-oldMouseX)/(float)size.width);
        float thetaX = 360.0f * ( (float)(y-oldMouseY)/(float)size.height);
        oldMouseX = x;
        oldMouseY = y;
        view_rotx += thetaX;
        view_roty += thetaY;
    }
    public void mouseMoved(MouseEvent e) {}
    
    public void keyPressed(KeyEvent e){
        
        if(e.getKeyChar()=='a'){
            view_trax++;
        }
        if (e.getKeyChar()=='d'){
            view_trax--;
        }
        if(e.getKeyChar()=='z'){
            view_tray++;
        }
        if (e.getKeyChar()=='q'){
            view_tray--;
        }
        if(e.getKeyChar()=='w'){
            view_traz++;
        }
        if (e.getKeyChar()=='s'){
            view_traz--;
        }
        
        if (e.getKeyChar()=='r'){
            view_trax=0.0f;
            view_tray=0.0f;
            view_traz=0.0f;
            
        }
        
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
}