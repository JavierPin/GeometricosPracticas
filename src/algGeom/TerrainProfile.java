package algGeom;


import com.sun.opengl.util.GLUT;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
    private GLUT glut = new GLUT();
    
    //scaling the scene
    private float view_scale = 1.0f;
    // translate the scene
    private float view_trax = 0.0f;
    private float view_tray = 0.0f;
    private float view_traz = 0.0f;
    // rotating the scene
    private float view_rotx = 0.0f; //20
    private float view_roty = 0.0f; //30
    //Mínimos y máximos
    double minX, minY;
    double maxX, maxY;
    
    static int HEIGHT = 100000, WIDTH = 100000;
    
    private Vector<Segment3d> polilinea;
    
    public static void main(String[] args) {
        
        
    	Draw.ALTO = HEIGHT*100;
    	Draw.ANCHO = WIDTH*100;
    	Draw.FONDO = 100000;
    }
    
    @Override
    public void init(GLAutoDrawable drawable)
    {
        
        GL gg = drawable.getGL();
        glu = new GLU();
        
        // Set backgroundcolor and shading mode
        gg.glClearColor(0.3f, 0.0f, 0.0f, 0.0f); //Como que duele un poco en blanco
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
        
        minX=BasicGeom.INFINITO;
        minY=BasicGeom.INFINITO;
        maxX=-BasicGeom.INFINITO;
        maxY=-BasicGeom.INFINITO;
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
        
        //Si hay que dibujuar el perfil, trasladamos la cámara en función de la amplitud del perfil
        if(polilinea.size()>1){
            
            glu.gluLookAt((float)polilinea.get((int)(polilinea.size()/2)).orig.x,(float)polilinea.get((int)(polilinea.size()/2)).orig.y+(maxY-minY),1500+(maxX-minX)*1,  // eye pos
                     (float)polilinea.get((int)(polilinea.size()/2)).orig.x,(float)polilinea.get((int)(polilinea.size()/2)).orig.y+(maxY-minY),0,   // look at
                     0,1,0);  // up*/
           
        }
        else{
            glu.gluLookAt(0,0,6190,  // eye pos
                     0,0,0,   // look at
                     0,1,0);  // up*/
        }
            
        //Transformaciones de la cámara (rotación, traslación, escalado)
        gl.glScalef(view_scale,view_scale,view_scale);
        gl.glTranslatef(view_trax, view_tray, view_traz);
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);

        //Dibujamos la polilínea
        for (int i=0; i<polilinea.size();i++){

            DrawSegment3d segment = new DrawSegment3d(polilinea.get(i));
            segment.drawObject(gl);
            
        }
        
        
        if (polilinea.size()>1){
            
            //Linea de tierra (eje x)
            DrawLine3d tierra = new DrawLine3d(new Line3d(
                    new Vect3d(polilinea.get(0).orig.x,0,0),
                    new Vect3d(polilinea.get(polilinea.size()-1).dest.x,0,0)));
            tierra.drawObjectC(gl, 1,1,0);
            
            //Linea vertical (eje y)
            DrawRay3d vertical = new DrawRay3d(new Ray3d(
                    new Vect3d(polilinea.get(0).orig.x-550,0,0),
                    new Vect3d(polilinea.get(0).orig.x-550,polilinea.get(0).orig.y,0)
            ));
            vertical.drawObjectC(gl,1,1,0);
            
            //Linea desde el punto 1 de la polilínea a linea de tierra
            DrawSegment3d proy = new DrawSegment3d(new Segment3d(
                    new Vect3d(polilinea.get(0).orig.x,polilinea.get(0).orig.y,0),
                    new Vect3d(polilinea.get(0).orig.x,0,0)));
            proy.drawObjectC(gl,1,1,0);
            
            //Linea desde el punto final de la polilínea a linea de tierra
            proy = new DrawSegment3d(new Segment3d(
                    new Vect3d(polilinea.get(polilinea.size()-1).dest.x,polilinea.get(polilinea.size()-1).dest.y,0),
                    new Vect3d(polilinea.get(polilinea.size()-1).dest.x,0,0)));
            proy.drawObjectC(gl,1,1,0);
            
            //Punto inicio
            DrawVect3d p = new DrawVect3d(polilinea.get(0).orig);
            p.drawObjectC(gl, 1,0,0,2);
            
            //Punto final
            p = new DrawVect3d(polilinea.get(polilinea.size()-1).dest);
            p.drawObjectC(gl, 1,0,0,2);
            
            //Texto raster en canvas
            gl.glTranslatef(0,10,0);
            
            gl.glRasterPos2f((float)polilinea.get(0).orig.x-500,(float)polilinea.get(0).orig.y);
            glut.glutBitmapString(GLUT.BITMAP_HELVETICA_10, String.valueOf(Math.round(polilinea.get(0).orig.y*100)/100.0)+" meters");
            gl.glRasterPos2f((float)polilinea.get(polilinea.size()-1).dest.x+80,(float)polilinea.get(polilinea.size()-1).dest.y);
            glut.glutBitmapString(GLUT.BITMAP_HELVETICA_10, String.valueOf(Math.round(polilinea.get(polilinea.size()-1).dest.y*100)/100-0)+" meters");
            
            //Guía numérica en el eje Y
            gl.glTranslatef(0, -10,0);
            
            for (int i=0;i<maxY;i=i+100){
                gl.glRasterPos2f((float)polilinea.get(0).orig.x-1150,i);
                glut.glutBitmapString(GLUT.BITMAP_HELVETICA_10, String.valueOf(i)+" meters");
            }
            
        }
        
        //Limpiamos la polilínea y los valores mínimos y máximos
        polilinea.clear();
        minX=BasicGeom.INFINITO;
        minY=BasicGeom.INFINITO;
        maxX=-BasicGeom.INFINITO;
        maxY=-BasicGeom.INFINITO;
        
    }
    
    /**Método para a?adir elementos a la polilínea.
     * 
     * @param s 
     */
    public void addLine(Segment3d s){
        
        polilinea.add(s);
        
        if(s.orig.x>maxX){
            maxX=s.orig.x;
        }
        if(s.dest.x>maxX){
            maxX=s.dest.x;
        }
        
        if(s.orig.y>maxY){
            maxY=s.orig.y;
        }
        if(s.dest.y>maxY){
            maxY=s.dest.y;
        }
        if(s.orig.x<minX){
            minX=s.orig.x;
        }
        if(s.dest.x<minX){
            minX=s.dest.x;
        }
        
        if(s.orig.y<minY){
            minY=s.orig.y;
        }
        if(s.dest.y<minY){
            minY=s.dest.y;
        }
        
    }
    
    /**Método para limpiar la polilínea
     * 
     */
    public void clearLine(){
        
        polilinea.clear();
    }
    
    /**Método para aisgnar una polilínea
     * 
     * @param p 
     */
    public void setProfile(Vector p){
        
        polilinea=p;
        
    }

    //Eventos de ratón y teclado
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
    public void keyPressed(KeyEvent e){
        
        if(e.getKeyChar()=='a' || e.getKeyCode()==37){
            view_trax+=10*(1+Math.abs(1-view_scale));
        }
        if (e.getKeyChar()=='d' || e.getKeyCode()==39){
            view_trax-=10*(1+Math.abs(1-view_scale));
        }
        if(e.getKeyChar()=='z'){
            view_traz+=10*(1+Math.abs(1-view_scale));
        }
        if (e.getKeyChar()=='q'){
            view_traz-=10*(1+Math.abs(1-view_scale));
        }
        if(e.getKeyChar()=='s' || e.getKeyCode()==40){
            view_tray+=10*(1+Math.abs(1-view_scale));
        }
        if (e.getKeyChar()=='w' || e.getKeyCode()==38){
            view_tray-=10*(1+Math.abs(1-view_scale));
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
    public void mousePressed(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseWheelMoved(MouseWheelEvent e) {
   
        if (e.getWheelRotation()<0){

            view_traz+=10*(1+Math.abs(1-view_scale));
            
        }
        if (e.getWheelRotation()>0){

            view_traz-=10*(1+Math.abs(1-view_scale));
        }
        
    }
    
}
