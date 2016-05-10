package algGeom;


import javax.media.opengl.*;

public class DrawTriangle3d extends Draw{

    Triangle3d tr;
    Vect3d n;
    
    /** Creates a new instance of VisuPunto */
    public DrawTriangle3d (Triangle3d t) {
        tr = t;
        n = tr.Normal();
    }

    @Override public void drawObject (GL g){
        
        g.glBegin(GL.GL_TRIANGLES);
           g.glNormal3d(n.x, n.y, n.y);
           g.glVertex3d (tr.a.x, tr.a.y, tr.a.z);
           g.glVertex3d (tr.b.x, tr.b.y, tr.b.z);
           g.glVertex3d (tr.c.x, tr.c.y, tr.c.z);
        g.glEnd();

    }

    @Override public void drawObjectC (GL g, float R, float G, float B){
        g.glColor3f(R, G, B);

        g.glBegin(GL.GL_TRIANGLES);
           g.glNormal3d(n.x, n.y, n.y);
           g.glVertex3d (tr.a.x, tr.a.y, tr.a.z);
           g.glVertex3d (tr.b.x, tr.b.y, tr.b.z);
           g.glVertex3d (tr.c.x, tr.c.y, tr.c.z);
        g.glEnd();

        g.glColor3f(1, 1, 1);
    }
    
    public void drawWireObjectC (GL g, float R, float G, float B){
        g.glColor3f(R, G, B);

        g.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_LINE);
        g.glBegin(GL.GL_TRIANGLES);
           g.glNormal3d(n.x, n.y, n.y);
           g.glVertex3d (tr.a.x, tr.a.y, tr.a.z);
           g.glVertex3d (tr.b.x, tr.b.y, tr.b.z);
           g.glVertex3d (tr.c.x, tr.c.y, tr.c.z);
        g.glEnd();
        g.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_FILL);
        
        g.glColor3f(1, 1, 1);
    }

}