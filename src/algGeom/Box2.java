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
import java.util.Random;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


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
    static int HEIGHT = 10, WIDTH = 10;
    static Animator animator;
    
    //Problema de refresco infinito
    /*boolean once = true;
    Vect3d v1, v2, v3;
    DrawCloud3d cloud;
    DrawTriangle3d triangle;
    DrawRay3d rayo;
    Cloud3d c;
    Triangle3d t1;*/
    
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
        GL gl = drawable.getGL();
        glu = new GLU();
        
        // Set backgroundcolor and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f); //Como que duele un poco en blanco
        //gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); //En negro no duele tanto
        gl.glShadeModel(GL.GL_FLAT);
        
        gl.glEnable(GL.GL_POINT_SMOOTH );
        gl.glEnable(GL.GL_LINE_SMOOTH);
        gl.glEnable(GL.GL_POLYGON_SMOOTH);
        
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
        
        ArrayList<Vertex> points = new ArrayList<>();
        points.add(new Vertex(new Point(-100,-100)));
        points.add(new Vertex(new Point(-100,-86)));
        points.add(new Vertex(new Point(-57,-100)));
        Polygon p1 = new Polygon(points,3);
        
        points = new ArrayList<>();
        points.add(new Vertex(new Point(-97,-97)));
        points.add(new Vertex(new Point(-97,-80)));
        points.add(new Vertex(new Point(-80,-80)));
        points.add(new Vertex(new Point(-80,-97)));
        Polygon p2 = new Polygon(points,4);
        
        Polygon p3 = p1.union(p2);
        
        DrawPolygon poly3 = new DrawPolygon(p3);
        poly3.drawObjectC(gl,0,1,0);
        
        /*DrawPolygon poly1 = new DrawPolygon(p1);
        poly1.drawObjectC(gl,1,0,0);

        DrawPolygon poly2 = new DrawPolygon(p2);
        poly2.drawObjectC(gl,0,0,1);*/
        
        
        
        //Practica 3, apartado 1
        /*PointCloud c = new PointCloud(100);
        
        DrawCloud cloud = new DrawCloud(c);
        gl.glPointSize(4);
        cloud.drawObjectC(gl,1,0,0);
        
        gl.glEnable(gl.GL_BLEND);
        gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
        gl.glColor4f(1, 1, 1, 0.5f);
        Polygon p = new Polygon(c.convexHull());
        DrawPolygon poly = new DrawPolygon(p);
        poly.drawObject(gl);
        gl.glColor4f(1, 1, 1, 1);
        */
        
        //Practica 3, apartado 3
        PointCloud c2 = new PointCloud(200);
        points = new ArrayList<>();
        points.add(new Vertex(new Point(-50,-50)));
        points.add(new Vertex(new Point(-50,50)));
        points.add(new Vertex(new Point(50,50)));
        points.add(new Vertex(new Point(50,-50)));
        Polygon area = new Polygon(points,4);
        
        K2tree kt = new K2tree(c2);
        PointCloud c3 = kt.busquedaRango(-50, -50, 50, 50);
        //Tengo que empezar a pintar puntos de la nube
        DrawPoint dp;
        /*for (int i=0; i<=c3.tama();i++){
            dp = new DrawPoint(c3.getPunto(i));
            dp.drawObjectC(gl, 1, 0, 1);
        }*/
        DrawPolygon dArea = new DrawPolygon(area);
        dArea.drawObjectC(gl,0,0,1,0.25f);
        
//        DrawCloud dCloud = new DrawCloud(c2);
//        dCloud.drawObjectC(gl, 0, 0, 0);
        
        
        /*Practica 2
        if (once){
            once = false;
            
            c = new Cloud3d (30);
            cloud = new DrawCloud3d(c);
            
            //Triangulo con tres puntos random de la nube
            Random rn = new Random();

            ArrayList<Vect3d> cloudPoints = new ArrayList<Vect3d>();
            
            for (int i=0; i<c.tama();i++){
                
                cloudPoints.add(c.getPunto(i));
                
            }

            int index = rn.nextInt(cloudPoints.size()-1);
            v1 = cloudPoints.get(index);
            cloudPoints.remove(index);

            index = rn.nextInt(cloudPoints.size()-1);
            v2 = cloudPoints.get(index);
            cloudPoints.remove(index);

            index = rn.nextInt(cloudPoints.size()-1);
            v3 = cloudPoints.get(index);
            cloudPoints.remove(index);
            
            t1 = new Triangle3d (v1,v2,v3);
            triangle = new DrawTriangle3d(t1);
            
//            boolean continua = true;
//            int pivote=1;
            Vect3d point = new Vect3d (0,0,0);
            
            
            
            for (int i=0; i<cloudPoints.size()-1; i++){
                
                for (int j=1+i; j<cloudPoints.size()-1;j++){
                    
                    Ray3d r1 = new Ray3d(cloudPoints.get(i),cloudPoints.get(j));
                    Ray3d r2 = new Ray3d(cloudPoints.get(j),cloudPoints.get(i));
                    
                    if (t1.RayTriangle3d(r1, point)){
                        
                        System.out.print("El rayo formado por los puntos ");
                        cloudPoints.get(i).out();
                        System.out.print(" y ");
                        cloudPoints.get(j);
                        System.out.print(" intersectan con el triangulo en el punto");
                        point.out();
                        j=cloudPoints.size();
                        i=cloudPoints.size();
                        
                        rayo = new DrawRay3d(r2);
                        DrawVect3d punt = new DrawVect3d (point);
                        punt.drawObjectC(gl, 0,0,1);
                        
                        
                    }
                    
                    if(t1.RayTriangle3d(r2, point)){

                        System.out.println("El rayo formado por los puntos ");
                        cloudPoints.get(j).out();
                        System.out.println(" y ");
                        cloudPoints.get(i);
                        System.out.print(" intersectan con el triangulo en el punto");
                        point.out();
 
                        rayo = new DrawRay3d(r1);
                        DrawVect3d punt = new DrawVect3d (point);
                        punt.drawObjectC(gl, 0,0,1);
                        j=cloudPoints.size();
                        i=cloudPoints.size();


                    }
                    
                    
                }
                
            }
//            do{
//                System.out.println(i);
//                
//                Ray3d r1 = new Ray3d(cloudPoints.get(0),cloudPoints.get(pivote));
//                Ray3d r2 = new Ray3d(cloudPoints.get(pivote),cloudPoints.get(0));
//                if(t1.RayTriangle3d(r1, point)){
//                    
//                    System.out.print("El rayo formado por los puntos ");
//                    cloudPoints.get(0).out();
//                    System.out.print(" y ");
//                    cloudPoints.get(pivote);
//                    System.out.print(" intersectan con el triangulo en el punto");
//                    point.out();
//                    continua=false;
//                    rayo = new DrawRay3d(r1);
//                    DrawVect3d punt = new DrawVect3d (point);
//                    punt.drawObjectC(gl, 0,0,1);
//                    
//                    
//                }
//                if(t1.RayTriangle3d(r2, point) && continua){
//                    
//                    System.out.println("El rayo formado por los puntos ");
//                    cloudPoints.get(pivote).out();
//                    System.out.println(" y ");
//                    cloudPoints.get(0);
//                    System.out.print(" intersectan con el triangulo en el punto");
//                    point.out();
//                    continua=false;
//                    rayo = new DrawRay3d(r1);
//                    DrawVect3d punt = new DrawVect3d (point);
//                    punt.drawObjectC(gl, 0,0,1);
//                    
//                    
//                }
//                pivote++;
//                if(pivote==c.tama()-3){
//                    
//                    pivote=1;
//                    cloudPoints.remove(0);
//                    
//                }
//                
//            }while(continua);

            
        }
        
                /*Dibujamos el plano que contiene al triangulo
        Plane planoTriangulo;
        //planoTriangulo = new Plane(triangle.tr.a,triangle.tr.b,triangle.tr.c,false);
        planoTriangulo = new Plane(triangle.tr.getA(),triangle.tr.getB(),triangle.tr.getC(),true);
        //planoTriangulo = planoTriangulo.PlaneInfinite();
        DrawPlane dPlanoTriangulo= new DrawPlane (planoTriangulo);
        
        double distPlano = BasicGeom.INFINITO;
        int puntoCercano=0;
        /*Seccion A
            el siguientre trozo de codigo se usa para calcular el punto 
            más cercano al plano y cambiar su grosor
        
         for (int i = 0; i < 30; i++) {
            if(planoTriangulo.distancia(cloud.cloud.getPunto(i))<distPlano && (
                    
                    cloud.cloud.getPunto(i)!=triangle.tr.getA()) //Tampoco puede estar incluido en el triangulo
                    && (cloud.cloud.getPunto(i)!=triangle.tr.getB())
                    && (cloud.cloud.getPunto(i)!=triangle.tr.getC())){
                puntoCercano=i;
                distPlano=planoTriangulo.distancia(cloud.cloud.getPunto(i));
            }
         }
        /*Fin Seccion A*/
        

        /*Vamos a hacer que cada punto se dibuje dependiendo de 
        su posicion respecto al triangulo
        for (int i = 0; i < 30; i++) {
            posicionPunto tipoPunto;
            tipoPunto =  triangle.tr.clasifica(cloud.cloud.getPunto(i));

            if(tipoPunto == posicionPunto.ENCIMA){
                if(i==puntoCercano){
                    new DrawVect3d(cloud.cloud.getPunto(i)).drawObjectC(gl, 0,0,0,2);
                }else{
                    new DrawVect3d(cloud.cloud.getPunto(i)).drawObjectC(gl, 0,0,0);
                }
            }else if(tipoPunto == posicionPunto.NEGATIV0){                
                if(i==puntoCercano){
                    new DrawVect3d(cloud.cloud.getPunto(i)).drawObjectC(gl, 1,0,0,2);
                }else{
                    new DrawVect3d(cloud.cloud.getPunto(i)).drawObjectC(gl, 1,0,0);
                }
            }else if(tipoPunto == posicionPunto.POSITIV0){
                if(i==puntoCercano){
                    new DrawVect3d(cloud.cloud.getPunto(i)).drawObjectC(gl, 0,1,0,2);
                }else{
                    new DrawVect3d(cloud.cloud.getPunto(i)).drawObjectC(gl, 0,1,0);
                }
            }else{
                if(i==puntoCercano){
                    new DrawVect3d(cloud.cloud.getPunto(i)).drawObjectC(gl, 1,1,1,2);
                }else{
                    new DrawVect3d(cloud.cloud.getPunto(i)).drawObjectC(gl, 1,1,1);
                }
            }
        /*Con eso de ahi ya tenemos clasificados los puntos
        ojo con los puntos blancos, es que algo falla
        
        }

        /*AABB de la nuve de puntos
        AABB abNube = new AABB(c);
        DrawAABB dABnube = new DrawAABB(abNube);
         
        Triangle3d boxY = new Triangle3d(t1.proyecta_XY(abNube));
        DrawTriangle3d proyBoxY = new DrawTriangle3d(boxY);
        proyBoxY.drawObject(gl);
         
        Triangle3d boxX = new Triangle3d(t1.proyecta_XZ(abNube));
        proyBoxY = new DrawTriangle3d(boxX);
        proyBoxY.drawObject(gl);
         
        Triangle3d boxZ = new Triangle3d(t1.proyecta_YZ(abNube));
        proyBoxY = new DrawTriangle3d(boxZ);
        proyBoxY.drawObject(gl);

        dPlanoTriangulo.drawObjectC(gl, 1, 1, 0, 0.2f);
         
        // cloud.drawObject(gl);//Esto dibuja toda la nube de puntos-Pero vamos a hacer que pinte cada punto por separado
        triangle.drawObjectC(gl,0,0,0); 
        rayo.drawObjectC(gl,1,1,0);
        dABnube.drawWireObject(gl);*/
        
                
        gl.glFlush();
        
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