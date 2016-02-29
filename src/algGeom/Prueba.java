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
<<<<<<< HEAD
=======
           
          // limpiar la pantalla
          gl.glClearColor(0.0f,0.0f,0.0f,0.0f); /* El color de limpiado será cero */
          gl.glClearDepth(1.0);
          gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
          
          
          /*HUD*/
          DrawHud dh= new DrawHud(gl);
          
          gl.glPointSize(4);
          Point a = new Point (0,0);
          DrawPoint da = new DrawPoint (a);
          da.drawObjectC(gl, 0.0f, 0.0f, 0.9f);
          

          
          //Point b = new Point (10, 55);
          Point b = new Point (10, 10);
          //DrawPoint db = new DrawPoint (b);
          //db.drawObjectC(gl, 0.0f, 0.9f, 0.0f);
          dh.MarcaPunto(b);
          dh.PuntoAvanzado(b,0.0f, 0.9f, 0.0f);
          
          /*Dibuja Segmento*/
          //SegmentLine ab = new SegmentLine (a,b);
          //DrawSegment dab = new DrawSegment (ab);
          //dab.drawObjectC(gl, 1.0f, 0.0f, 0.3f);
          
          /*Dibujar un rayo*/
          RayLine rab = new RayLine (a,b);
          DrawRay drab = new DrawRay(rab);
          drab.drawObjectC(gl,0.9f, 0.0f, 0.0f);
          
          /*Dibujar puntos sin mas*/
          Point pa,pb,pc,pd;
          pa= new Point (50,60);
          pb= new Point (-30,20);
          pc= new Point (-20,-20);
          pd= new Point (90,-40);
          dh.PuntoAvanzado(pa,0.9f, 0.9f, 0.9f);
          dh.PuntoAvanzado(pb,0.9f, 0.9f, 0.9f);
          dh.PuntoAvanzado(pc,0.9f, 0.9f, 0.9f);
          dh.PuntoAvanzado(pd,0.9f, 0.9f, 0.9f);
          
          /*Segmento pb-pd*/
          SegmentLine spapb = new SegmentLine(pa,pd);
          DrawSegment dspapd= new DrawSegment(spapb);
          dspapd.drawObject(gl);
          dh.MarcaPunto(pd);
          dh.MarcaPunto(pa);
          
          
          /*crea poligono-guardar poligono*/
//          String ruta = "c:/poligono.txt";
//          String ruta2 = "e:/poligono2.txt";
//          try{
//              Polygon poli = new Polygon(ruta);
//          //    poli.out();
//              Polygon poli2 = new Polygon(poli);
//              try{
//                poli2.salvar(ruta2);
//              }catch(IOException ex){
//                System.out.print(ex);
//              }catch(ErrorAlGuardar ex){
//                  System.out.print(ex);
//              }
//          }catch(IOException ex){
//              System.out.print(ex);
//          }catch(ErrorAlLeerFichero ex){
//              System.out.print(ex);
//          }

          

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
>>>>>>> master

            // limpiar la pantalla
            gl.glClearColor(0.0f,0.0f,0.0f,0.0f); /* El color de limpiado será cero */
            gl.glClearDepth(1.0);
            gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

            // dibujar los ejes
            DrawAxis axis = new DrawAxis(WIDTH, HEIGHT);
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
            

            /*DrawCloud cloud;
            gl.glPointSize(3);
            PointCloud pc = new PointCloud(16);
            cloud = new DrawCloud(pc);
            cloud.drawObject(gl);
            try {
                pc.salvar("./src/algGeom/npuntossalv.txt");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErrorAlGuardar ex) {
                Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
            }

            SegmentLine s1 = new SegmentLine(pc.getPunto(0), pc.getPunto(1));
            DrawSegment segment = new DrawSegment(s1);
            segment.drawObjectC(gl,1,0,0);
            SegmentLine s2 = new SegmentLine(pc.getPunto(2), pc.getPunto(3));
            segment = new DrawSegment(s2);
            segment.drawObjectC(gl,1,0,0);
            
            
            RayLine r1 = new RayLine(pc.getPunto(4), pc.getPunto(5));
            DrawRay ray = new DrawRay(r1);
            ray.drawObjectC(gl,0,1,0);
            RayLine r2 = new RayLine(pc.getPunto(6), pc.getPunto(7));
            ray = new DrawRay(r2);
            ray.drawObjectC(gl,0,1,0);
            
            Line l1 = new Line(pc.getPunto(8), pc.getPunto(9));
            DrawLine line = new DrawLine(l1);
            line.drawObjectC(gl,0,0,1);
            Line l2 = new Line(pc.getPunto(10), pc.getPunto(11));
            line = new DrawLine(l2);
            line.drawObjectC(gl,0,0,1);
            
            
            
            Polygon p = new Polygon();
            
            ArrayList<Vertex> Vertices = new ArrayList<Vertex>();
            
            Vertices.add(new Vertex(new Point(pc.getPunto(12)),p));
            Vertices.add(new Vertex(new Point(pc.getPunto(13)),p));
            Vertices.add(new Vertex(new Point(pc.getPunto(14)),p));
            Vertices.add(new Vertex(new Point(pc.getPunto(15)),p));

            
            p.anade(Vertices.get(0));
            p.anade(Vertices.get(1));
            p.anade(Vertices.get(2));
            p.anade(Vertices.get(3));
            
            p.out();
            
            DrawPolygon polygon = new DrawPolygon(p);
            polygon.drawObjectC(gl,1,1,1);
            
            
            //Pruebas anteriores
            Point a = new Point(-10,-10);

            ArrayList<Vertex> Vertices = new ArrayList<Vertex>();
            Vertex v = new Vertex(a);
            Vertices.add(v);

            a= new Point(10,-10);
            v = new Vertex(a);
            Vertices.add(v);

<<<<<<< HEAD
            a= new Point(10,10);
            v = new Vertex(a);
            Vertices.add(v);

            a= new Point(-10,10);
            v = new Vertex(a);
            Vertices.add(v);

            Polygon p = new Polygon(Vertices,4);
            DrawPolygon polygon = new DrawPolygon(p);
            polygon.drawObject(gl);

            Polygon p2 = null;
              try {
                  p2 = new Polygon("./src/algGeom/pol.txt");
              } catch (ErrorAlLeerFichero ex) {
                  Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
              } catch (IOException ex) {
                  Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
              }
              DrawPolygon polygon = new DrawPolygon(p2);
              polygon.drawObject(gl);

              System.out.println(p2.convexo());

              try {
                  p2.salvar("./src/algGeom/polsalv.txt");
              } catch (ErrorAlGuardar ex) {
                  Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
              } catch (IOException ex) {
                  Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
              }

            gl.glPointSize(3);
            Point a = new Point (0,0);
            DrawPoint da = new DrawPoint (a);
            da.drawObjectC(gl, 0.0f, 0.0f, 0.9f);

            Point b = new Point (30, -30);
            DrawPoint db = new DrawPoint (b);
            db.drawObjectC(gl, 0.0f, 0.0f, 0.9f);

            RayLine rl = new RayLine(a,b);
            DrawRay ray = new DrawRay(rl);

            ray.drawObject(gl);

            Point c = new Point (20,20);
            DrawPoint dc = new DrawPoint (c);
            dc.drawObjectC(gl, 0.0f, 0.0f, 0.9f);

            Point d = new Point (10, -40);
            DrawPoint dd = new DrawPoint (d);
            dd.drawObjectC(gl, 0.0f, 0.0f, 0.9f);

            RayLine rl2 = new RayLine(c,d);
            ray = new DrawRay(rl2);

            ray.drawObject(gl);

            System.out.println(rl2.intersectaSegmento(rl));


            Point a1 = new Point(0,0);
            Point b1 = new Point(0,10);
            Point c1 = new Point (10,20);
            Point c = new Point(10,10);
            Point d = new Point(0,-10);
            System.out.println(b1.areaTriangulo2(c1, a1));

            SegmentLine ab = new SegmentLine (a,b);
            DrawSegment dab = new DrawSegment (ab);
            dab.drawObjectC(gl, 1.0f, 0.0f, 0.3f);


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

=======
>>>>>>> master
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


