package algGeom;


import javax.media.opengl.*;


public class DrawSegment3d extends Draw {
    Segment3d e3;
   
    public DrawSegment3d (Segment3d e){
       e3 = e;
    }
	
    public Segment3d getEdge3d (){
        return e3;
     }
	    	
@Override public void drawObject (GL g){

    g.glColor3f(1, 1, 0);
    g.glPointSize(2.0f);
    g.glBegin(GL.GL_LINES);
      g.glVertex3d(e3.orig.x,e3.orig.y,e3.orig.z);
      g.glVertex3d(e3.dest.x,e3.dest.y,e3.dest.z);
    g.glEnd();

 }

   
   @Override public void drawObjectC (GL g, float R, float G, float B){
        g.glColor3f(R, G, B);
        g.glColor3f(1, 1, 0);
        g.glPointSize(2.0f);
        g.glBegin(GL.GL_LINES);
            g.glVertex3d(e3.orig.x,e3.orig.y,e3.orig.z);
            g.glVertex3d(e3.dest.x,e3.dest.y,e3.dest.z);
        g.glEnd();
        
        g.glColor3f(1, 1, 1);
     
 }
   
       
}