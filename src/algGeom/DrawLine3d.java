package algGeom;


import javax.media.opengl.*;

public class DrawLine3d extends Draw{
    
    Line3d vl;
    
    public DrawLine3d (Line3d l) {
        vl = l;
    }
    
    public Line3d getEdge3d(){
        
        return vl;
    }
    
    @Override public void drawObject (GL g){

        double ax,ay,az,bx,by,bz;
        
        Vect3d a = new Vect3d(vl.getOrigen());
        Vect3d u = new Vect3d(vl.getDestino().resta(vl.getOrigen()));
        
        Vect3d aux = new Vect3d(a.suma(u.prodEscalar(-1000)));
        Vect3d aux2 = new Vect3d(a.suma(u.prodEscalar(1000)));
        
        ax = aux.x;
        ay = aux.y;
        az = aux.z;
        
        bx = aux2.x;
        by = aux2.y;
        bz = aux2.z;

        g.glBegin(GL.GL_LINES);
            g.glVertex3d(ax,ay,az);
            g.glVertex3d(bx,by,bz);
        g.glEnd();
    
    }
    
    @Override public void drawObjectC (GL g, float R, float G, float B){

        double ax,ay,az,bx,by,bz;
        
        Vect3d a = new Vect3d(vl.getOrigen());
        Vect3d u = new Vect3d(vl.getDestino().resta(vl.getOrigen()));
        
        Vect3d aux = new Vect3d(a.suma(u.prodEscalar(-1000)));
        Vect3d aux2 = new Vect3d(a.suma(u.prodEscalar(1000)));
        
        ax = aux.x;
        ay = aux.y;
        az = aux.z;
        
        bx = aux2.x;
        by = aux2.y;
        bz = aux2.z;

        g.glColor3f(R, G, B);
        g.glBegin(GL.GL_LINES);
            g.glVertex3d(ax,ay,az);
            g.glVertex3d(bx,by,bz);
        g.glEnd();
        
        g.glColor3f(1, 1, 1);
    
    }
    
}
