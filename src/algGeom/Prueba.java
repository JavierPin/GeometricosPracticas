package algGeom;


//import com.sun.opengl.util.Animator;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.media.opengl.*;
//import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


//import net.java.games.jogl.*;


public class Prueba extends Frame implements GLEventListener, Comparator{

	static int HEIGHT = 600;
        static int WIDTH = 600;
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
                gl.glTranslatef((WIDTH/2)-50, (HEIGHT/2)-50, -100);
                //gl.gluLookAt(0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f);
		
	}

	// called for OpenGL rendering every reshape
	public void display(GLAutoDrawable drawable) {

            // limpiar la pantalla
            gl.glClearColor(0.0f,0.0f,0.0f,0.0f); /* El color de limpiado será cero */
            gl.glClearDepth(1.0);
            gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
            
            DrawAxis3d axis = new DrawAxis3d();
            axis.drawObject(gl);
            

            // dibujar los ejes
            /*DrawAxis axis = new DrawAxis(WIDTH, HEIGHT);
            axis.drawObject(gl);
            
            Line a = new Line(new Point(-30.0000000001,10),new Point(-30,-10));
            DrawLine lineA = new DrawLine(a);
            lineA.drawObject(gl);
            RayLine b = new RayLine(new Point(-20,-14), new Point(13,4));
            DrawRay rayB = new DrawRay(b);
            rayB.drawObject(gl);
            Line c = new Line(new Point(-100,70),new Point(100,-70));
            DrawLine lineC = new DrawLine(c);
            lineC.drawObject(gl);
            SegmentLine d = new SegmentLine(new Point(36,10), new Point(36,-40));
            DrawSegment segmentD = new DrawSegment(d);
            segmentD.drawObject(gl);
            Point p = new Point(0,-60);
            DrawPoint pointP = new DrawPoint(p);
            pointP.drawObject(gl);
            
            System.out.println("Distancia de P a A = "+a.distPuntoRecta(new Vector(p)));
            System.out.println("Distancia de P a B = "+b.distPuntoRayo(new Vector(p)));
            System.out.println("Distancia de P a C = "+c.distPuntoRecta(new Vector(p)));
            System.out.println("Distancia de P a D = "+d.distPuntoSegmento(new Vector(p)));
            System.out.println("Distancia de A a B = "+a.distRectaRayo(b));
            System.out.println("Distancia de A a C = "+a.distRectaRecta(c));
            System.out.println("Distancia de A a D = "+a.distRectaSegmento(d));
            
            
            Vector[] v = new Vector[1];
            v[0] = new Vector();
            System.out.println("A interstecta con B: "+a.intersecta(b, v[0])+" en el punto "+v[0].x+", "+v[0].y);
            System.out.println("A interstecta con C: "+a.intersecta(c, v[0])+" en el punto "+v[0].x+", "+v[0].y);
            System.out.println("A interstecta con D: "+a.intersecta(d, v[0])+" en el punto "+v[0].x+", "+v[0].y);
            System.out.println("B interstecta con C: "+b.intersecta(c, v[0])+" en el punto "+v[0].x+", "+v[0].y);
            System.out.println("B interstecta con D: "+b.intersecta(d, v[0])+" en el punto "+v[0].x+", "+v[0].y);
            System.out.println("C interstecta con D: "+c.intersecta(d, v[0])+" en el punto "+v[0].x+", "+v[0].y);
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

    public int compare(Object o1, Object o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}


