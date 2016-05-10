package algGeom;

import com.sun.opengl.util.Animator;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.util.Vector;


public class Box2 implements GLEventListener, 
                             MouseListener, 
                             MouseMotionListener,
                             KeyListener,
                             MouseWheelListener
{
    GLU glu;
    GL gl;
    //scaling the scene
    private float view_scale = 1.0f;
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
    static int HEIGHT = 100000, WIDTH = 100000;
    static Animator animator;
    
    //Problema de refresco infinito
    boolean once = true;
    Vect3d v1, v2, v3;
    DrawCloud3d cloud;
    DrawTriangle3d triangle;
    DrawRay3d rayo;
    Cloud3d c;
    Triangle3d t1;
    double Xmax, Xmin, Ymax, Ymin;
    
    public static void main(String[] args) {
        
        
    	Draw.ALTO = HEIGHT;
    	Draw.ANCHO = WIDTH;
    	Draw.FONDO = 100;
    			
        Frame frame = new Frame("Libreria 3D");
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new Box2());
        frame.add(canvas);
        frame.setSize(800, 800);
        animator = new Animator(canvas);
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
        Xmax=0;
        Xmin=0;
        Ymax=0;
        Ymin=0;
        
        GL gl = drawable.getGL();
        glu = new GLU();
        
        // Set backgroundcolor and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f); //Como que duele un poco en blanco
        //gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); //En negro no duele tanto
        gl.glShadeModel(GL.GL_FLAT);
        
        gl.glEnable(GL.GL_POINT_SMOOTH );
        gl.glEnable(GL.GL_LINE_SMOOTH);
        gl.glEnable(GL.GL_POLYGON_SMOOTH);
        
        
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
        
        drawable.addMouseWheelListener(this);

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
        glu.gluPerspective(45.0f, h, 0.1, BasicGeom.INFINITO);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        
    }
    
    public void display(GLAutoDrawable drawable)
    {
        
        gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);


        gl.glLoadIdentity();
        /*glu.gluLookAt(4012470,601230,10,  // eye pos
                     4012470,601230,0,   // look at
                     0,1,0);  // up*/
        
        glu.gluLookAt(2000,2000,1000,  // eye pos
                     0,0,0,   // look at
                     0,1,0);  // up*/

        gl.glScalef(view_scale,view_scale,view_scale);
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
        
        //Practica 5:
        Delaunay_Triangulation dt = null;

        try {
            dt = new Delaunay_Triangulation("./src/algGeom/t1-1000.tsin");

        } catch (Exception ex) {
            Logger.getLogger(Box2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Xmax=dt.delaunayXMax();
        Xmin=dt.delaunayXMin();
        Ymax=dt.delaunayYMax();
        Ymin=dt.delaunayYMin();
        
        //view_trax = (float)((Xmax+Xmin)/2.0);

        Iterator<Triangle_dt> iterator = dt.trianglesIterator();
        
        while (iterator.hasNext()) {
                Triangle_dt curr = iterator.next();
                if (!curr.isHalfplane()) {
                    
                    Triangle3d t1 = new Triangle3d(curr);
                    t1.toOrigin(Xmin, Xmax, Ymin, Ymax);

                    DrawTriangle3d triangle = new DrawTriangle3d(t1);
                    triangle.drawWireObjectC(gl, 0,0,0);
                    
                    Vect3d color = colorAltura(t1,dt.delaunayZMin(),dt.delaunayZMax());
                    triangle.drawObjectC(gl, (int)color.getX() , (int)color.getY(), (int)color.getZ());
                    //System.out.println("triangulo: ");
                    //color.out();
                }
        }

        gl.glFlush();

        
        Triangle_dt tri = dt.getTriangleAt(200);
        Triangle3d tritri = new Triangle3d(tri);
        
        tritri.toOrigin(Xmin,Xmax,Ymin,Ymax);

        
        //Vector<Triangle_dt> vecinos = dt.findTriangleNeighborhood(tri, tri.a);
        Vector<Triangle_dt> vecinos = dt.findTriangleNeighborhood(tri);
        
        for (int i=0;i<vecinos.size();i++){
            
            Triangle3d taux = new Triangle3d(vecinos.get(i));
            taux.toOrigin(Xmin, Xmax, Ymin, Ymax);

            DrawTriangle3d triangleaux = new DrawTriangle3d(taux);
            triangleaux.drawObjectC(gl, 1,1,0);
        
        }
        
        DrawTriangle3d triangleRandom = new DrawTriangle3d(tritri);
        triangleRandom.drawObjectC(gl, 1,0,0);
        Vect3d color = colorAltura(tritri,dt.delaunayZMin(),dt.delaunayZMax());
        //System.out.println("triangulo Tritri: ");
        //color.out();
        
        
    }
    
    private Vect3d colorAltura(Triangle3d t, double min, double max){
        double media,porcentaje,r,g,b;

        //media = (t.a.getZ() + t.b.getZ() + t.c.getZ())/3;
        media =Math.sqrt(Math.pow(t.a.getZ(), 2) + Math.pow(t.b.getZ(), 2) +Math.pow(t.c.getZ(), 2));
        porcentaje = (media-min)/(max-min);

        g = porcentaje*2*255;
        if(g<=255){
            b = 255 - g;
            r = 0;
        }else{
            r = g -255;
            g = 255 - r;
            b = 0;
        }
        
        return new Vect3d(r,g,b);
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
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
    public void mouseWheelMoved(MouseWheelEvent e) {
        
        if (e.getWheelRotation()<0){
            
            view_scale=view_scale+0.1f;
            
        }
        if (e.getWheelRotation()>0){
            
            view_scale=view_scale-0.1f;
        }
    
    }
    public void keyPressed(KeyEvent e){
        
        if(e.getKeyChar()=='a'){
            view_trax+=100;
        }
        if (e.getKeyChar()=='d'){
            view_trax-=100;
        }
        if(e.getKeyChar()=='z'){
            view_tray+=100;
        }
        if (e.getKeyChar()=='q'){
            view_tray-=100;
        }
        if(e.getKeyChar()=='w'){
            view_traz+=100;
        }
        if (e.getKeyChar()=='s'){
            view_traz-=100;
        }
        
        if (e.getKeyChar()=='r'){
            view_trax=0.0f;
            view_tray=0.0f;
            view_traz=0.0f;
            
        }
        
        
        if (e.getKeyChar()=='+'){
            
            view_scale=view_scale+0.1f;
            
        }
        
        if (e.getKeyChar()=='-'){
            
            view_scale=view_scale-0.1f;
            
        }
        
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
}