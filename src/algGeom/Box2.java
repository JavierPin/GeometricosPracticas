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
    			
        Frame frame = new Frame("Simple JOGL Application");
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
        glu = new GLU(); // needed for lookat
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);


        gl.glLoadIdentity();
        glu.gluLookAt(4,8,8,  // eye pos
                     0,0,0,   // look at
                     0,1,0);  // up


        gl.glTranslatef(view_trax, view_tray, view_traz);       
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
  
        //gl.glTranslatef(-0.5f, -0.5f, -0.5f);
        //oneBox.drawMe(gl);
        
        /*Vect3d p1 = new Vect3d (0,0,0);
        Vect3d p2 = new Vect3d (0,2,0);
        Vect3d p3 = new Vect3d (4,1,0);
        DrawVect3d vp1 = new DrawVect3d (p1);
        vp1.drawObject(gl);
        DrawVect3d vp2 = new DrawVect3d (p2);
        vp2.drawObjectC(gl,0 ,1 ,0);
        DrawVect3d vp3 = new DrawVect3d (p3);
        vp3.drawObjectC(gl, 0, 0, 1);
        
        Segment3d s = new Segment3d (p1,p2);
        DrawSegment3d ds = new DrawSegment3d (s);
        ds.drawObject(gl);*/
//        gl.glColor3f(1, 0, 0);
//        gl.glPointSize(4.0f);
//        gl.glBegin(GL.GL_POINTS);
//            gl.glVertex3d(p1.x, p1.y, p1.z);
//	gl.glEnd();
//        

        DrawAxis3d axis = new DrawAxis3d();
        axis.drawObject(gl);
        
        
        
        //int ori = p1.orientation(p2, p2);
        //System.out.print("orientacion:");
        //System.out.println(ori);
        
        // no logro ver mi triángulo
        Triangle3d t1 = new Triangle3d (    new Vect3d (0, 3, 0),
                                            new Vect3d (1, 0, 0),
                                            new Vect3d (0, 0, 1)
                                        );
        
        DrawTriangle3d dt1 = new DrawTriangle3d (t1);
        dt1.drawObjectC(gl,0.9f,0.9f,0.9f);
        
        //Cloud3d c = new Cloud3d (30);
        
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