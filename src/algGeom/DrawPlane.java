package algGeom;

import javax.media.opengl.*;
/**
 *
 * @author javier
 */
public class DrawPlane extends Draw{
    private Plane p;
    
    public DrawPlane(Plane _p) {
        p = _p;
    }

    public Plane getPlane() {
        return p;
    }
    
    @Override
    public void drawObject(GL g) {
        Vect3d pt1, pt2, pt3, pt4; //pt de punto, que luego tengo follones con el plano
        pt4 = p.b.resta(p.a);
        pt1 = p.c.resta(p.a);
        pt4 = p.a.suma(pt4.suma(pt1));
        
        Vect3d ad, bc;
        ad = pt4.resta(p.a);
        bc = p.b.resta(p.c);
        
        pt2 = p.b.suma(bc);
        pt3 = p.c.resta(bc);
        pt1 = p.a.resta(ad);
        pt4 = pt4.suma(ad);

        g.glBegin(GL.GL_QUADS);
        g.glVertex3f((float) pt1.x,(float) pt1.y,(float) pt1.z);
        g.glVertex3f((float)pt2.x,(float) pt2.y,(float) pt2.z);
        g.glVertex3f((float) pt4.x,(float) pt4.y,(float) pt4.z);
        g.glVertex3f((float) pt3.x,(float) pt3.y,(float) pt3.z);
        
        g.glEnd();
    }
    
    @Override
    public void drawObjectC(GL g, float R, float G, float B) {
        Vect3d pt1, pt2, pt3, pt4;
        pt4 = p.b.resta(p.a);
        pt1 = p.c.resta(p.a);
        pt4 = p.a.suma(pt4.suma(pt1));
        
        Vect3d ad, bc;
        ad = pt4.resta(p.a);
        bc = p.b.resta(p.c);
        
        pt2 = p.b.suma(bc);
        pt3 = p.c.resta(bc);
        pt1 = p.a.resta(ad);
        pt4 = pt4.suma(ad);
        
        g.glColor3f(R, G, B);

        g.glBegin(GL.GL_QUADS);
        g.glVertex3f((float) pt1.x,(float) pt1.y,(float) pt1.z);
        g.glVertex3f((float)pt2.x,(float) pt2.y,(float) pt2.z);
        g.glVertex3f((float) pt4.x,(float) pt4.y,(float) pt4.z);
        g.glVertex3f((float) pt3.x,(float) pt3.y,(float) pt3.z);
        
        g.glEnd();
    }
    
    public void drawObjectC(GL g, float R, float G, float B, float alpha) {
        Vect3d pt1, pt2, pt3, pt4;
        pt4 = p.b.resta(p.a);
        pt1 = p.c.resta(p.a);
        pt4 = p.a.suma(pt4.suma(pt1));
        
        Vect3d ad, bc;
        ad = pt4.resta(p.a);
        bc = p.b.resta(p.c);
        
        pt2 = p.b.suma(bc);
        pt3 = p.c.resta(bc);
        pt1 = p.a.resta(ad);
        pt4 = pt4.suma(ad);
        
        //g.glColor3f(R, G, B);
        //vvvvvvvEsto permite el color alphavvvvvv
        g.glEnable(g.GL_BLEND);
        g.glBlendFunc(g.GL_SRC_ALPHA, g.GL_ONE_MINUS_SRC_ALPHA);
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        g.glColor4f(R, G, B, alpha);

        g.glBegin(GL.GL_QUADS);
        g.glVertex3f((float) pt1.x,(float) pt1.y,(float) pt1.z);
        g.glVertex3f((float)pt2.x,(float) pt2.y,(float) pt2.z);
        g.glVertex3f((float) pt4.x,(float) pt4.y,(float) pt4.z);
        g.glVertex3f((float) pt3.x,(float) pt3.y,(float) pt3.z);
        
        g.glColor4f(R, G, B, 1);
        
        g.glEnd();

    }
    
    
}
