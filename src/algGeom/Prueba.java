package algGeom;


//import com.sun.opengl.util.Animator;
import java.awt.*;
import javax.media.opengl.*;
//import java.awt.event.*;
//import java.util.*;
import java.io.IOException;
import java.io.FileNotFoundException;



//import net.java.games.jogl.*;


public class Prueba extends Frame implements GLEventListener {

	static int HEIGHT = 800;
        static int WIDTH = 800;
	static GL gl; // interface to OpenGL
	static GLCanvas canvas; // drawable in a frame
	static GLCapabilities capabilities;
//      static Animator animator; // drive display() in a loop
//      static Model m;

	public Prueba() {
              
		// 1. specify a drawable: canvas
		capabilities = new GLCapabilities();
		capabilities.setDoubleBuffered(false);
		canvas = new GLCanvas();
		//canvas = GLDrawableFactory.getFactory().createGLCanvas(capabilities);

		// 2. listen to the events related to canvas: reshape
		canvas.addGLEventListener(this);

		// 3. add the canvas to fill the Frame container
		add(canvas, BorderLayout.CENTER);

		// 4. interface to OpenGL functions
		gl = canvas.getGL();
              
//        addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				animator.stop(); // stop animation
//				System.exit(0);
//			}
//	    	});

              
              
	}


	// called once for OpenGL initialization
	public void init(GLAutoDrawable drawable) {

//      animator = new Animator(canvas);
//		animator.start(); // start animator thread

		// display OpenGL and graphics system information
		System.out.println("INIT GL IS: " + gl.getClass().getName());
		System.err.println(drawable.getChosenGLCapabilities());
		System.err.println("GL_VENDOR: " + gl.glGetString(GL.GL_VENDOR));
		System.err.println("GL_RENDERER: " + gl.glGetString(GL.GL_RENDERER));
		System.err.println("GL_VERSION: " + gl.glGetString(GL.GL_VERSION));




	}

      
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {

            
		WIDTH = width; // new width and height saved
		HEIGHT = height;
              //DEEP = deep;

		// 7. specify the drawing area (frame) coordinates
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0, width, 0, height, -100, 100);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		
	}

	// called for OpenGL rendering every reshape
	public void display(GLAutoDrawable drawable) {
          // limpiar la pantalla
          gl.glClearColor(0.0f,0.0f,0.0f,0.0f); /* El color de limpiado será cero */
          gl.glClearDepth(1.0);
          gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
           
          /*
          gl.glPointSize(4);
          Point a = new Point (0,0);
          DrawPoint da = new DrawPoint (a);
          da.drawObjectC(gl, 0.0f, 0.0f, 0.9f);
          

          
          Point b = new Point (10, 55);
          SegmentLine ab = new SegmentLine (a,b);
          DrawSegment dab = new DrawSegment (ab);
          dab.drawObjectC(gl, 1.0f, 0.0f, 0.3f);
          */
        DrawHud hud= new DrawHud(gl);
        PointCloud miNube = new PointCloud(16);
        for(int i=0;i<miNube.nubepuntos.size();i++){
            hud.PuntoAvanzado(miNube.getPunto(i),0.9f,0.9f,0.9f);
        }
        SegmentLine sA =new SegmentLine (miNube.getPunto(0),miNube.getPunto(1));
        SegmentLine sB =new SegmentLine (miNube.getPunto(2),miNube.getPunto(3));
        DrawSegment ds =new DrawSegment (sA);
        ds.drawObject(gl);
        ds =new DrawSegment (sB);
        ds.drawObject(gl);
        
        RayLine rA =new RayLine (miNube.getPunto(4),miNube.getPunto(5));
        RayLine rB =new RayLine (miNube.getPunto(6),miNube.getPunto(7));
        DrawRay dr =new DrawRay (rA);
        dr.drawObjectC(gl, 0.0f,0.9f,0.0f);
        dr =new DrawRay (rB);
        dr.drawObjectC(gl, 0.0f,0.9f,0.0f);
        
        Line lA =new Line (miNube.getPunto(8),miNube.getPunto(9));
        Line lB =new Line (miNube.getPunto(10),miNube.getPunto(11));
        DrawLine dl =new DrawLine (lA);
        dl.drawObjectC(gl, 0.0f,0.0f,0.9f);
        dl =new DrawLine (lB);
        dl.drawObjectC(gl, 0.0f,0.0f,0.9f);
        
        Polygon pA =new Polygon ();
        Vertex v = new Vertex(miNube.getPunto(12));
        pA.Vertexs.add(v);
        pA.nVertexs++;
        v = new Vertex(miNube.getPunto(13));
        pA.Vertexs.add(v);
        pA.nVertexs++;
        v = new Vertex(miNube.getPunto(14));
        pA.Vertexs.add(v);
        pA.nVertexs++;
        v = new Vertex(miNube.getPunto(15));
        pA.Vertexs.add(v);
        pA.nVertexs++;
        DrawPolygon dp = new DrawPolygon(pA);
        dp.drawObjectC(gl,0.3f,0.5f,0.f );
        
        hud.MarcaPunto(miNube.getPunto(15));
        
/*
        // cuando estén todas las funciones correctamente definidas se verá el resultado
          Point a = new Point (20,40); DrawPoint aa = new DrawPoint (a);
          Point b = new Point (50,71); DrawPoint bb = new DrawPoint (b);
          aa.drawObject(gl);
          bb.drawObject(gl);
          Line d = new Line (a,b);
          //Line d = new Line(new Point (20,40), new Point (50,71));
          DrawLine dd = new DrawLine (d);
          dd.drawObjectC(gl, 0.8f,0.0f,0.2f);
          System.out.print ("pendiente:");
          System.out.print(d.pendiente());        
          

          RayLine r = new RayLine(new Point (-20,-20), new Point (-76,20));
          DrawRay rr = new DrawRay (r);
          rr.drawObjectC(gl, 0.8f,0.5f,0.2f);

  */      

          gl.glFlush();


	}


	// called if display mode or device are changed
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
	}


    	public static void main(String[] args) {
	   Draw.ALTO = HEIGHT;
           Draw.ANCHO = WIDTH;
           Prueba frame = new Prueba();
        frame.setSize(WIDTH, HEIGHT);
	frame.setVisible(true);

         
    }


}


