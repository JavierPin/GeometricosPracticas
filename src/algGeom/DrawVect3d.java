package algGeom;


import javax.media.opengl.*;

public class DrawVect3d extends Draw {
   

    Vect3d v3;
    /** Creates a new instance of VisuPoint */
    public DrawVect3d (Vect3d p) {
        v3 = p;
    }
    
    public Vect3d getPoint (){
        return v3;
    }
    
    @Override public void drawObject (GL g){
        
        g.glColor3f(1, 0, 0);
        g.glPointSize(4.0f);
        g.glBegin(GL.GL_POINTS);
            g.glVertex3d(v3.x, v3.y, v3.z);
	g.glEnd();
        
    }
    
    @Override public void drawObjectC (GL g, float R, float G, float B){

        g.glColor3f(R, G, B);
        g.glPointSize(4.0f);
        g.glBegin(GL.GL_POINTS);
            g.glVertex3d(v3.x, v3.y, v3.z);
	g.glEnd();
        
        g.glColor3f(1, 1, 1);

    }
    
    public void drawObjectC (GL g, float R, float G, float B,float size){

        g.glColor3f(R, G, B);
        g.glPointSize(4.0f*size);
        g.glBegin(GL.GL_POINTS);
            g.glVertex3d(v3.x, v3.y, v3.z);
	g.glEnd();
        
        g.glColor3f(1, 1, 1);

    }
}