package algGeom;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.media.opengl.*;

public class DrawAxis extends Draw{
    
    static double w;
    static double h;
    
    public DrawAxis(double width, double height){
        w=width;
        h=height;
        
        
    }
    
    @Override public void drawObject (GL gl){

        Point a = new Point (-(w/10.0f),0);
        Point b = new Point (w/10.0f,0);
        Point c = new Point (0,-(h/10.0f));
        Point d = new Point (0,(h/10.0f));

        Line l = new Line(a,b);
        DrawLine line = new DrawLine(l);
        line.drawObject(gl);

        l=new Line(c,d);
        line = new DrawLine(l);
        line.drawObject(gl);

        a = new Point (0,-1);
        b = new Point (0,1);
        c = new Point (-1,0);
        d = new Point (1,0);
        SegmentLine sg;
        DrawSegment segment;
          
        for (int i=0; i<(int)(w/10.0f); i++){

          a.asignax(i*10);
          b.asignax(a.x);
          sg = new SegmentLine(a,b);
          segment = new DrawSegment(sg);
          segment.drawObject(gl);

          a.asignax(-a.x);
          b.asignax(a.x);
          sg = new SegmentLine(a,b);
          segment = new DrawSegment(sg);
          segment.drawObject(gl);

        }

        for (int i=0; i<(int)(h/10.0f);i++){

          c.asignay(i*10);
          d.asignay(c.y);
          sg = new SegmentLine(c,d);
          segment = new DrawSegment(sg);
          segment.drawObject(gl);

          c.asignay(-c.y);
          d.asignay(c.y);
          sg = new SegmentLine(c,d);
          segment = new DrawSegment(sg);
          segment.drawObject(gl);

        }
        
    }
    
    @Override public void drawObjectC(GL gl, float R, float G, float B){
        
        Point a = new Point (-(w/10.0f),0);
        Point b = new Point (w/10.0f,0);
        Point c = new Point (0,-(h/10.0f));
        Point d = new Point (0, h/10.0f);
        Line l = new Line(a,b);
        DrawLine line = new DrawLine(l);
        line.drawObjectC(gl,R,G,B);

        l=new Line(c,d);
        line = new DrawLine(l);
        line.drawObjectC(gl,R,G,B);

        a = new Point (0,-1);
        b = new Point (0,1);
        c = new Point (-1,0);
        d = new Point (1,0);
        SegmentLine sg;
        DrawSegment segment;
          
        for (int i=0; i<(int)(w/10.0f); i++){

          a.asignax(i*10);
          b.asignax(a.x);
          sg = new SegmentLine(a,b);
          segment = new DrawSegment(sg);
          segment.drawObjectC(gl,R,G,B);

          a.asignax(-a.x);
          b.asignax(a.x);
          sg = new SegmentLine(a,b);
          segment = new DrawSegment(sg);
          segment.drawObjectC(gl,R,G,B);

        }

        for (int i=0; i<(int)(h/10.0f);i++){

          c.asignay(i*10);
          d.asignay(c.y);
          sg = new SegmentLine(c,d);
          segment = new DrawSegment(sg);
          segment.drawObjectC(gl,R,G,B);

          c.asignay(-c.y);
          d.asignay(c.y);
          sg = new SegmentLine(c,d);
          segment = new DrawSegment(sg);
          segment.drawObjectC(gl,R,G,B);

        }
        
        gl.glColor3f(1, 0, 0);
        
    }
}
