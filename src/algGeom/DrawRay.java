package algGeom;


import javax.media.opengl.*;

public class DrawRay extends Draw {
        RayLine rl;
    /** Creates a new instance of VisuPoint */
    public DrawRay (RayLine l) {
        rl = l;
    }

    public RayLine getRay (){
        return rl;
    }

    @Override public void drawObject (GL g){

        double ax,ay,bx,by;
        double m = rl.pendiente();
        double c = rl.getC();
       
        ax = rl.leeax();
        ay = rl.leeay();
        bx = rl.leebx();
        by = rl.leeby();
       
        if (m<BasicGeom.INFINITO){ //intersectamos con el lateral
            if (bx-ax>BasicGeom.CERO){
                bx = BasicGeom.RANGO;
                by = m*bx+c;
            }
            else{
                bx = -BasicGeom.RANGO;
                by = m*bx+c;
            }
           
        } else { //Es una recta perpendicular
            if(by-ay<BasicGeom.CERO){
                ax = rl.getA().getX();
                ay = -BasicGeom.RANGO;
            }
            else{
                bx = rl.getB().getY();
                by = BasicGeom.RANGO;
            }
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
        double m = rl.pendiente();
        double c = rl.getC();
       
        ax = rl.leeax();
        ay = rl.leeay();
        bx = rl.leebx();
        by = rl.leeby();
       
        if (m<BasicGeom.INFINITO){ //intersectamos con el lateral
            if (bx-ax>BasicGeom.CERO){
                bx = BasicGeom.RANGO;
                by = m*bx+c;
            }
            else{
                bx = -BasicGeom.RANGO;
                by = m*bx+c;
            }
           
        } else { //Es una recta perpendicular
            if(by-ay<BasicGeom.CERO){
                ax = rl.getA().getX();
                ay = -BasicGeom.RANGO;
            }
            else{
                bx = rl.getB().getY();
                by = BasicGeom.RANGO;
            }
        }
       
       
        ax = convCoordX(ax);
        ay = convCoordX(ay);
        bx = convCoordX(bx);
        by = convCoordX(by);
        
                
        g.glLineWidth(3.0f);
        g.glColor3f(R, G, B);
        g.glBegin(GL.GL_LINES);
            g.glVertex2d(ax,ay);
            g.glVertex2d(bx,by);//the fourth (w) component is zero!
        g.glEnd();     
    }

}
