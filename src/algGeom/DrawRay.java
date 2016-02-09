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
        
        //XXX dibujar el rayo como un segmento con origen en a y con final en los bordes del canvas
        
        /*
        ax = convCoordX(ax);
        ay = convCoordX(ay);
        bx = convCoordX(bx);
        by = convCoordX(by);
        


        g.glBegin(GL.GL_LINES);
            g.glVertex2d(ax,ay);
            g.glVertex2d(bx,by);//the fourth (w) component is zero!
        g.glEnd();
        */
    }

    @Override public void drawObjectC (GL g, float R, float G, float B){
        double ax,ay,bx,by;
        
      //XXX dibujar el rayo como un segmento con origen en a y con final en los bordes del canvas        
        
        /*
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

        */       
    }

}
