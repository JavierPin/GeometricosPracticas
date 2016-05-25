/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algGeom;

import static algGeom.Box2.HEIGHT;
import static algGeom.Box2.WIDTH;
import com.sun.opengl.util.Animator;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.Vector;


public class BoxSky implements GLEventListener,
                             KeyListener
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
    
    private float view_scale_old = 1.0f;
    // translate the scene
    private float view_trax_old = 0.0f;
    private float view_tray_old = 0.0f;
    private float view_traz_old = 0.0f;
    // rotating the scene
    private float view_rotx_old = 0.0f; //20
    private float view_roty_old = 0.0f; //30
    
    // remember last mouse position
    private int oldMouseX;
    private int oldMouseY;
    //static int HEIGHT = 800, WIDTH = 800;
    static int HEIGHT = 100000, WIDTH = 100000;
    static Animator animator;
    static Animator animator2;
    
    
    //Problema de refresco infinito
    boolean once = true;
    Vect3d v1, v2, v3;
    DrawCloud3d cloud;
    DrawTriangle3d triangle;
    DrawRay3d rayo;
    Cloud3d c;
    Triangle3d inicio;
    Triangle3d fin;
    Triangle3d selected;
    
    Triangle_dt inicio_dt;
    Triangle_dt fin_dt;
    Triangle_dt selected_dt;
    
    double Xmax, Xmin, Ymax, Ymin;
    Ray3d r;
    
    public static void main(String[] args) {
        
        
    	Draw.ALTO = HEIGHT;
    	Draw.ANCHO = WIDTH;
    	Draw.FONDO = 100;

        
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
        
        drawable.addKeyListener(this);
        
        inicio=null;
        fin=null;
        selected=null;

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
        
        glu.gluLookAt(0,0,6000,  // eye pos
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
            dt = new Delaunay_Triangulation("./src/algGeom/t1-5000.tsin");

        } catch (Exception ex) {
            Logger.getLogger(Box2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Xmax=dt.delaunayXMax();
        Xmin=dt.delaunayXMin();
        Ymax=dt.delaunayYMax();
        Ymin=dt.delaunayYMin();

        Iterator<Triangle_dt> iterator = dt.trianglesIterator();
        
        Vect3d cPos = new Vect3d(findCameraPosition());
        Vect3d look = new Vect3d(findLookat());
        look.z = look.z-100;

        r = new Ray3d(cPos, look);
        DrawRay3d dr = new DrawRay3d(r);
        
        selected=null;
        
        while (iterator.hasNext()) {
                Triangle_dt curr = iterator.next();
                if (!curr.isHalfplane()) {
                    
                    Triangle3d t1 = new Triangle3d(curr);
                    t1.toOrigin(Xmin, Xmax, Ymin, Ymax);

                    DrawTriangle3d triangle = new DrawTriangle3d(t1);
                    triangle.drawWireObjectC(gl, 0,0,0);
                    
                    Vect3d color = colorAltura(t1,dt.delaunayZMin(),dt.delaunayZMax());
                    triangle.drawObjectC(gl, (float)color.getX(), (float)color.getY(), (float)color.getZ());
                    
                    Vect3d[] point = new Vect3d[1];
                    if (t1.RayTriangle3d(r, point) && selected==null){

                        selected = t1;
                        selected_dt = curr;

                    }
                }
        }
        
        if(selected!=null){
            
            DrawTriangle3d selTriangle = new DrawTriangle3d(selected);
            selTriangle.drawObjectC(gl,0,1,1);
        }
        
        
        
        if(inicio_dt!=null && fin_dt!=null){
            
            Ray3d route = new Ray3d(inicio.centroide(),fin.centroide());
            DrawRay3d rou = new DrawRay3d(route);
            rou.drawObjectC(gl,0,0,1);
            
            Vector<Triangle_dt> v1 = new Vector<Triangle_dt>();
            v1 = dt.findTriangleNeighborhood(fin_dt, fin_dt.b);

            
            for (int i=0;i<v1.size();i++){
                
                Triangle3d t = new Triangle3d(v1.get(i));
                t.toOrigin(Xmin, Xmax, Ymin, Ymax);
                
                Vect3d[] point = new Vect3d[1];
                t.RayTriangle3d(route, point);
                
                if(point[0]!=null){
                    
                    point[0].out();
                    DrawVect3d punto = new DrawVect3d(point[0]);
                    punto.drawObjectC(gl, 1,1,1,3);
                    break;
                    
                }
                
                DrawTriangle3d tri = new DrawTriangle3d(t);
                tri.drawWireObjectC(gl, 0,0,1);
            }
        }

        gl.glFlush();
        
    }
    
    private Vect3d findCameraPosition(){
        
        double[] mdl = getM(gl);
        double[] camera_org = new double[3];
        camera_org[0] = -(mdl[0] * mdl[12] + mdl[1] * mdl[13] + mdl[2] * mdl[14]);
        camera_org[1] = -(mdl[4] * mdl[12] + mdl[5] * mdl[13] + mdl[6] * mdl[14]);
        camera_org[2] = -(mdl[8] * mdl[12] + mdl[9] * mdl[13] + mdl[10] * mdl[14]);
        
        return new Vect3d(camera_org[0], camera_org[1], camera_org[2]);
        
    }
    
    private Vect3d findLookat(){
        
        double[] mdl = getM(gl);
        
        return new Vect3d(-mdl[12],-mdl[13],-mdl[14]);
    }
    
    private static double[] getM(GL g){
        double mvmatrix[] = new double[16];
        g.glGetDoublev(GL.GL_MODELVIEW_MATRIX, mvmatrix,0);
        
        return mvmatrix;
    }
    
    private Vect3d colorAltura(Triangle3d t, double min, double max){
        double media, porcentaje, r, g, b;

        //media = (t.a.getZ() + t.b.getZ() + t.c.getZ())/3; //Aritmetica
        media = 3/(1/t.a.getZ() + 1/t.b.getZ() + 1/t.c.getZ()); //Armonica
        //media =Math.sqrt(Math.pow(t.a.getZ(), 2) + Math.pow(t.b.getZ(), 2) +Math.pow(t.c.getZ(), 2)); //Geometrica

        
        porcentaje = media/(max-min);
        g = (1 - porcentaje);
        r = (porcentaje);
        b = 0;
        
        
        return new Vect3d(r,g,b);
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
    public void keyPressed(KeyEvent e){
        
        if(e.getKeyChar()=='i'){
        }
        
        if(e.getKeyChar()=='a'){
            view_trax+=20;
        }
        if (e.getKeyChar()=='d'){
            view_trax-=20;
        }
        if(e.getKeyChar()=='s'){
            view_tray+=20;
        }
        if (e.getKeyChar()=='w'){
            view_tray-=20;
        }
        if(e.getKeyChar()=='q'){
            view_traz+=20;
        }
        if (e.getKeyChar()=='z'){
            view_traz-=20;
        }
        
        if (e.getKeyChar()=='r'){
            view_trax=0.0f;
            view_tray=0.0f;
            view_traz=0.0f;
            
        }
        
        if(e.getKeyChar()=='1'){
            
            inicio = selected;
            inicio_dt = selected_dt;
            
        }
        
        if(e.getKeyChar()=='2'){
            
            fin_dt = selected_dt;
            fin = selected;
            
        }
        
        if(e.getKeyChar()=='3'){
            
            inicio = null;
            inicio_dt = null;
            fin = null;
            fin_dt = null;
        }
        
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    
    
    public Triangle3d getSelectedTriangle(){
        
        return selected;
        
    }
    
    public Triangle3d getInicio(){
        
        return inicio;
        
    }
    
    public Triangle3d getFin(){
        
        return fin;
    }
}
