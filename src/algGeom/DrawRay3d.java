package algGeom;

import javax.media.opengl.GL;

public class DrawRay3d extends Draw{
    public Ray3d l;

    public DrawRay3d(Ray3d _l) {
        l = _l;
    }

    @Override
    public void drawObject(GL g) {
        Vect3d p1, p2;
            
        
        p1 = new Vect3d(
                l.dest.x + (l.orig.x- l.dest.x),
                l.dest.y + (l.orig.y - l.dest.y),
                l.dest.z + (l.orig.z - l.dest.z));
        p2 = new Vect3d(
                l.orig.x - (l.orig.x - l.dest.x)* 100,
                l.orig.y - (l.orig.y - l.dest.y)* 100,
                l.orig.z - (l.orig.z - l.dest.z)* 100);

        g.glBegin(GL.GL_LINES);
        g.glVertex3f((float) p1.getX(),(float) p1.getY(),(float) p1.getZ());
        g.glVertex3f((float) p2.getX(),(float) p2.getY(),(float) p2.getZ());
        
        g.glEnd();
  

    }

    @Override
    public void drawObjectC(GL g, float R, float G, float B) {
        Vect3d p1, p2;
            
        
        p1 = new Vect3d(
                l.orig.x - (l.orig.x - l.dest.x)* 2000,
                l.orig.y - (l.orig.y - l.dest.y)* 2000,
                l.orig.z - (l.orig.z - l.dest.z)* 2000);
                
        p2 = new Vect3d(
            l.dest.x + (l.orig.x - l.dest.x),
            l.dest.y + (l.orig.y - l.dest.y),
            l.dest.z + (l.orig.z - l.dest.z));

        g.glColor3f(R, G, B);
        g.glBegin(GL.GL_LINES);
        g.glVertex3f((float) p1.getX(),(float) p1.getY(),(float) p1.getZ());
        g.glVertex3f((float) p2.getX(),(float) p2.getY(),(float) p2.getZ());
        
        g.glEnd();
        
        g.glColor3f(1, 1, 1);

    }
    
    
}
