package algGeom;


import javax.media.opengl.*;

public class DrawLine extends Draw {
        Line vl;

    public DrawLine (Line l) {
        vl = l;
    }
    
    public Line getLine (){
        return vl;
    }
    
    @Override public void drawObject (GL g){

         /** pasar a coordenadas de pantalla */

        double ax,ay,bx,by;
        double m = vl.pendiente();
        double c = vl.getC();
        if (m<BasicGeom.INFINITO){ //intersectamos con el canvas lateral
            ax = -BasicGeom.RANGO;
            ay = m*ax+c;
            bx = BasicGeom.RANGO;
            by = m*bx+c;

        } else {
            ax = vl.getA().getX();
            ay = -BasicGeom.RANGO;
            bx = vl.getB().getX();
            by = BasicGeom.RANGO;

        }

        ax = convCoordX(ax);
        ay = convCoordX(ay);
        bx = convCoordX(bx);
        by = convCoordX(by);
        
        g.glBegin(GL.GL_LINES);
            g.glVertex2d(ax,ay);
            g.glVertex2d(bx,by);//the fourth (w) component is zero!
        g.glEnd();


    
    }
    
    @Override public void drawObjectC (GL g, float R, float G, float B){


        double ax,ay,bx,by;
        double m = vl.pendiente();
        double c = vl.getC();
        if (m<BasicGeom.INFINITO){ //intersectamos con el canvas lateral
            ax = -BasicGeom.RANGO;
            ay = m*ax+c;
            bx = BasicGeom.RANGO;
            by = m*bx+c;

        } else {
            ax = vl.getA().getX();
            ay = -BasicGeom.RANGO;
            bx = vl.getB().getX();
            by = BasicGeom.RANGO;

        }

        ax = convCoordX(ax);
        ay = convCoordX(ay);
        bx = convCoordX(bx);
        by = convCoordX(by);
        
        g.glColor3f(R, G, B);
        g.glBegin(GL.GL_LINES);
            g.glVertex2d(ax,ay);
            g.glVertex2d(bx,by);//the fourth (w) component is zero!
        g.glEnd();
        
        g.glColor3f(1, 1, 1);

    }

}
