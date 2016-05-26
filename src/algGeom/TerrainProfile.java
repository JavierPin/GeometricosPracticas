package algGeom;


import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.util.Vector;

public class TerrainProfile implements GLEventListener,
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
    
    static int HEIGHT = 100000, WIDTH = 100000;
    
    private Vector<Segment3d> polilinea;
    
    public static void main(String[] args) {
        
        
    	Draw.ALTO = HEIGHT;
    	Draw.ANCHO = WIDTH;
    	Draw.FONDO = 100;

    }
    
    @Override
    public void init(GLAutoDrawable drawable)
    {
        
        GL gg = drawable.getGL();
        glu = new GLU();
        
        // Set backgroundcolor and shading mode
        gg.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); //Como que duele un poco en blanco
        //gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); //En negro no duele tanto
        gg.glShadeModel(GL.GL_FLAT);
        
        gg.glEnable(GL.GL_POINT_SMOOTH );
        gg.glEnable(GL.GL_LINE_SMOOTH);
        gg.glEnable(GL.GL_POLYGON_SMOOTH);
        gg.glEnable(GL.GL_BLEND);
        gg.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
        drawable.addMouseWheelListener(this);
        
        polilinea = new Vector<Segment3d>();
    }
    
    @Override
    public void reshape(GLAutoDrawable drawable, 
                        int x, int y, int width, int height)
    {
	WIDTH = width; // new width and height saved
        HEIGHT = height;

        GL gg = drawable.getGL();
        GLU gglu = new GLU();
        if (height <= 0) // no divide by zero
            height = 1;
        // keep ratio
        final float h = (float) width / (float) height;
        gg.glViewport(0, 0, width, height);
        gg.glMatrixMode(GL.GL_PROJECTION);
        gg.glLoadIdentity();
        gglu.gluPerspective(45.0f, h, 0.1, BasicGeom.INFINITO);
        gg.glMatrixMode(GL.GL_MODELVIEW);
        gg.glLoadIdentity();
        
        
    }
    
    public void display(GLAutoDrawable drawable)
    {
        
        gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        
        glu.gluLookAt(0,0,5190,  // eye pos
                     0,0,0,   // look at
                     0,1,0);  // up*/

        gl.glScalef(view_scale,view_scale,view_scale);
        gl.glTranslatef(view_trax, view_tray, view_traz);
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);

        for (int i=0; i<polilinea.size();i++){

            DrawSegment3d segment = new DrawSegment3d(polilinea.get(i));
            segment.drawObject(gl);
            
        }
        
        polilinea.clear();
        
    }
    
    public void addLine(Segment3d s){
        
        polilinea.add(s);
    }
    
    public void clearLine(){
        
        polilinea.clear();
    }
    
    public void setProfile(Vector p){
        
        polilinea=p;
        
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
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
        if (e.getWheelRotation()>0 && view_scale>0.1f){

            view_scale=view_scale-0.1f;
        }
        
    }
    
}
