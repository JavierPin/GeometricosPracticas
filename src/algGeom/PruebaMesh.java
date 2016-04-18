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

import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;



public class PruebaMesh implements GLEventListener, 
                             MouseListener, 
                             MouseMotionListener,
                             KeyListener,
                             MouseWheelListener
{
    private float view_scale = 1.0f;
    // translate the scene
    private float view_trax = 0.0f;
    private float view_tray = 0.0f;
    private float view_traz = 0.0f;

    public static void main(String[] args) {
    	Draw.ALTO = HEIGHT;
    	Draw.ANCHO = WIDTH;
    	Draw.FONDO = 100;
    			
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new PruebaMesh());
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
    
    // rotating the scene
    private float view_rotx = 20.0f; //20
    private float view_roty = 30.0f; //30
    // remember last mouse position
    private int oldMouseX;
    private int oldMouseY;
    //static int HEIGHT = 800, WIDTH = 800;
    static int HEIGHT = 100, WIDTH = 100;
    
    public Mesh modelo; //variable accesible en toda la clase
    
    public void init(GLAutoDrawable drawable)
    {	
    	
        GL gl = drawable.getGL();
        // Set backgroundcolor and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        gl.glShadeModel(GL.GL_FLAT);
        // give me some light
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
        
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
        drawable.addMouseWheelListener(this);
        
        
        try {
            modelo = new Mesh ("./src/modelos/ballet.obj");
            System.out.println("Modelo cargado con " + modelo.getSizeCaras() + "caras.");
            modelo.getAABB().out();
        
        }  catch (Exception e) {
	  System.out.println("Error en la lectura del fichero");
	}

        
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
        
        //EDICION DEL TAMA?O DEL FRUSTUM PARA PODER VISUALIZAR EL OBJETO DESDE MAS LEJOS
        glu.gluPerspective(45.0f, h, 1.0, 2000.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        
    }
    
    public void display(GLAutoDrawable drawable)
    {
        GL gl = drawable.getGL();
        GLU glu = new GLU(); // needed for lookat
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        
        //alteramos la eye pos para ver la figura desde mas lejos.  HACE FALTA CAMBIAR EL TAMA?O DEL FRUSTUM MAS ARRIBA
        glu.gluLookAt(-356,340,340,  // eye pos
                     0,0,0,   // look at
                     0,1,0);  // up
        
        //posicion para visualizar geometría sin mesh, cámara más cerca
        /*glu.gluLookAt(3,1,3,  // eye pos
                     0,0,0,   // look at
                     0,1,0);  // up*/
        
        gl.glScalef(view_scale,view_scale,view_scale);
        gl.glTranslatef(view_trax, view_tray, view_traz);        
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);

        DrawAxis3d axis = new DrawAxis3d();
        axis.drawColoredObject(gl);
        

        //hacemos un translate para intentar aproximar el modelo al punto (0,0,0)
        //gl.glTranslatef(-520, 500, 0);
        
        
        //dibujamos el modelogl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_NORMALIZE);
        DrawMesh dmodel = new DrawMesh(modelo);
        dmodel.drawObject(gl);
        gl.glDisable(GL.GL_LIGHTING);
        gl.glDisable(GL.GL_LIGHT0);
        gl.glDisable(GL.GL_DEPTH_TEST);
        gl.glDisable(GL.GL_NORMALIZE);
        
        //Ray3d r = new Ray3d(new Vect3d(-50,50,80), new Vect3d(50,50,-100));
        //DrawRay3d ray = new DrawRay3d(r);
        //ray.drawObjectC(gl, 0,0,1);
        
        RayBeam rb = new RayBeam(new Vect3d(10,200,200), new Vect3d(10,200,-100),150,15);
        rb.DrawRayBeam(gl,0,0,1);
        
        ArrayList<Triangle3d> tMalla = modelo.getTriangulos();
        
        Vect3d[] point = new Vect3d[1];
        
        long time_start, time_end, time_acum=0;
        
        time_start = System.nanoTime();

        for (int j=0; j<rb.rays.size();j++){
            
            Ray3d r = rb.rays.get(j);
            
            for (int i=0; i<tMalla.size();i++){
                boolean intersecta = tMalla.get(i).RayTriangle3d(r, point);
                if(intersecta){
                    time_end = System.nanoTime();
                    
                    time_acum=time_acum+time_end-time_start;

                    DrawTriangle3d triangle = new DrawTriangle3d(tMalla.get(i));
                    triangle.drawObjectC(gl, 0.3f,1,0.3f);
                    DrawVect3d punto = new DrawVect3d(point[0]);
                    punto.drawObjectC(gl, 0,0,1);

                    i=tMalla.size();
                }
            }
        }
        
        System.out.println("Operación realizada en "+ ( time_acum )/1000000.0f +" millisegundos");
        
        //Dibujamos el octree. Hemos deshabilitado la luz para poder pintarlo del color que queramos
        /*AABB modelBox = modelo.getAABB();
        Octree om = new Octree(modelBox,4,modelo.getListaVertices());
        DrawOctree octree2 = new DrawOctree(om);
        octree2.drawObjectC(gl,0.5f,0.5f,0);*/

        //desactivar luces para obtener el color deseado con DrawObjectC
        /*gl.glDisable(GL.GL_LIGHTING);
        gl.glDisable(GL.GL_LIGHT0);
        gl.glDisable(GL.GL_DEPTH_TEST);
        gl.glDisable(GL.GL_NORMALIZE);
        
        Cloud3d n = new Cloud3d(30);
        gl.glPointSize(4);
        
        //Octree con nube de puntos
        Octree o = new Octree(n,5);
        DrawOctree octree = new DrawOctree(o);
        octree.drawObjectC(gl, 0.1f,0.2f,0.1f);
        DrawCloud3d nube = new DrawCloud3d(n);
        nube.drawObjectC(gl,0.9f,0,0.9f);*/
        
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
    public void mouseWheelMoved(MouseWheelEvent e) {
        
        if (e.getWheelRotation()<0){
            
            view_scale=view_scale+0.1f;
            
        }
        if (e.getWheelRotation()>0){
            if(view_scale>=0.1f){
                view_scale=view_scale-0.1f;
            }
            
        }
    
    }
    
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