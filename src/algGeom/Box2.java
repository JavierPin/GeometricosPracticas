package algGeom;

import com.sun.opengl.util.Animator;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.util.Vector;
import javax.swing.JFrame;


public class Box2 implements GLEventListener, 
                             MouseListener, 
                             MouseMotionListener,
                             KeyListener,
                             MouseWheelListener
{
    GLU glu;
    GL gl;
    //scaling the scene
    private float view_scale = 0.7f;
    // translate the scene
    private float view_trax = 0.0f;
    private float view_tray = 0.0f;
    private float view_traz = 0.0f;
    // rotating the scene
    private float view_rotx = -50.0f; //20
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
    static Animator animator1;
    static Animator animator2;
    static Animator animator3;
    
    //Problema de refresco infinito
    boolean once = true;
    Vect3d v1, v2, v3;
    DrawCloud3d cloud;
    DrawTriangle3d triangle;
    DrawRay3d rayo;
    Cloud3d c;
    TriangleTin inicio;
    TriangleTin fin;
    TriangleTin selected;
    double Xmax, Xmin, Ymax, Ymin;
    Ray3d r;
    boolean selecting;
    static BoxSky boxsky;
    static TerrainProfile profile;
    int uuu=0;
  
    
    public static void main(String[] args) {
        
        
    	Draw.ALTO = HEIGHT;
    	Draw.ANCHO = WIDTH;
    	Draw.FONDO = 100;

        boxsky=new BoxSky();
        profile=new TerrainProfile();
        
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new Box2());
        canvas.setSize(700,540);
        
        GLCanvas aereo = new GLCanvas();
        aereo.addGLEventListener(boxsky);
        aereo.setSize(350,270);
        
        GLCanvas perfil = new GLCanvas();
        perfil.addGLEventListener(profile);
        perfil.setSize(390,270);
    			
        JFrame frame = new JFrame("Libreria 3D");
 
        GridBagLayout layout = new GridBagLayout();
        frame.setLayout(layout);
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.gridheight = 4;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(canvas,constraints);
      
        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        frame.add(aereo, constraints);
        
        constraints.gridx = 4;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(perfil, constraints);
        
        animator1 = new Animator(canvas);
        animator2 = new Animator(aereo);
        animator3 = new Animator(perfil);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {
                    public void run() {
                        animator1.stop();
                        animator2.stop();
                        animator3.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        
        frame.pack();
        frame.setLocation(0,0);
        frame.setSize(1100, 540);
        frame.setVisible(true);
        animator1.start();
        animator2.start();
        animator3.start();
    }
    
    public void init(GLAutoDrawable drawable)
    {
        Xmax=0;
        Xmin=0;
        Ymax=0;
        Ymin=0;
        
        GL gl = drawable.getGL();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_FLAT);
        
        gl.glEnable(GL.GL_POINT_SMOOTH );
        gl.glEnable(GL.GL_LINE_SMOOTH);
        gl.glEnable(GL.GL_POLYGON_SMOOTH);
        gl.glEnable(GL.GL_BLEND);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
        drawable.addMouseWheelListener(this);
        
        selecting=false;
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
        
        glu.gluLookAt(0,0,10000,  // eye pos
                     0,0,0,   // look at
                     0,1,0);  // up*/

        gl.glScalef(view_scale,view_scale,view_scale);
        gl.glTranslatef(view_trax, view_tray, view_traz);
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);

        
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

        
        TNetwork tin = new TNetwork(dt);
        DrawTin tintin = new DrawTin(tin);
        tintin.drawObjectMap(gl);
        
        
        try{
            selected = new TriangleTin(boxsky.selected_dt);
            selected.toOrigin(Xmin, Xmax, Ymin, Ymax);
            selected = tin.busca(selected);
            DrawTriangle3d select = new DrawTriangle3d(selected);
            select.drawObjectC(gl, 0,1,1);
        }catch(Exception e){}
        
        try{
            //Obtiene el triangulo desde el delaunay del canvas 2
            //Lo traslada a coordenadas de la camara
            //busca en el tin el triangulo equivalente
            inicio = new TriangleTin(boxsky.inicio_dt);
            inicio.toOrigin(Xmin, Xmax, Ymin, Ymax);
            inicio = tin.busca(inicio);
            
            DrawTriangle3d select = new DrawTriangle3d(inicio);
            select.drawObjectC(gl, 1,1,0);
        }catch(Exception e){}
        
        try{
            fin = new TriangleTin(boxsky.fin_dt);
            fin.toOrigin(Xmin, Xmax, Ymin, Ymax);
            fin = tin.busca(fin);
            
            DrawTriangle3d select = new DrawTriangle3d(fin);
            select.drawObjectC(gl, 1,1,0);
            dibujaRuta(tin, inicio, fin);

        }catch(Exception e){}

        gl.glFlush();
        
    }
    
    private void dibujaRuta(TNetwork tin, TriangleTin tOrig, TriangleTin tDest){
        
        Ray3d rr = new Ray3d(tOrig.centroide(),tDest.centroide());
        
        
        Vector<TriangleTin> ruta = tin.route(tOrig, tDest, rr);
        Vector<Vect3d> alturas = new Vector<Vect3d>();
        ruta.add(0, tOrig);
        ruta.add(tDest);
        
        for (int i=0;i<ruta.size();i++){
            
            //profile.addLine(new Segment3d(ruta.get(i).centroideAltura(),ruta.get(i+1).centroideAltura()));
            
            alturas.add(new Vect3d(ruta.get(i).centroide().x,ruta.get(i).centroide().z,ruta.get(i).centroide().y));
            DrawVect3d v = new DrawVect3d(new Vect3d(ruta.get(i).centroide().x, 0,ruta.get(i).centroide().z));
            v.drawObjectC(gl, 1,0,0,2);
            
        }
        
        for (int i=0; i<alturas.size()-1;i++){
            
            profile.addLine(new Segment3d(alturas.get(i),alturas.get(i+1)));
        }
        
        DrawSegment3d rayo = new DrawSegment3d(new Segment3d(rr.orig,rr.dest));
        rayo.drawObjectC(gl, 1,0.5f,0);
        
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {
  
        
    }
    public void mousePressed(MouseEvent e) {
        
        oldMouseX = e.getX();
        oldMouseY = e.getY();
        
    }
    public void mouseDragged(MouseEvent e) {
        if(!selecting){
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
    }
    public void mouseMoved(MouseEvent e) {}
    public void mouseWheelMoved(MouseWheelEvent e) {
        
        if(!selecting){
            if (e.getWheelRotation()<0){

                view_scale=view_scale+0.1f;

            }
            if (e.getWheelRotation()>0 && view_scale>0.1f){

                view_scale=view_scale-0.1f;
            }
        }
    }
    public void keyPressed(KeyEvent e){
        
        if(e.getKeyChar()=='a'){
            view_trax+=10;
        }
        if (e.getKeyChar()=='d'){
            view_trax-=10;
        }
        if(e.getKeyChar()=='z'){
            view_tray+=10;
        }
        if (e.getKeyChar()=='q'){
            view_tray-=10;
        }
        if(e.getKeyChar()=='w'){
            view_traz+=10;
        }
        if (e.getKeyChar()=='s'){
            view_traz-=10;
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


    public void setView_trax(float i){
        
        
        view_trax = i;
        
    }




}