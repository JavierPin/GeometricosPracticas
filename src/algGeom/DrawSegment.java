package algGeom;


import javax.media.opengl.*;

public class DrawSegment extends Draw {
    SegmentLine vs;
    /** Creates a new instance of VisuPoint */
    public DrawSegment (SegmentLine s) {
        vs = s;
    }
    
    public SegmentLine getSegment (){
        return vs;
    }
    
    @Override public void drawObject (GL g){

         /** pasar a coordenadas de pantalla */
        float ax = convCoordX(vs.leeax());
        float ay = convCoordX(vs.leeay());
        float bx = convCoordX(vs.leebx());
        float by = convCoordX(vs.leeby());
       
        g.glBegin(GL.GL_LINES);
            g.glVertex2d(ax, ay);
            g.glVertex2d(bx, by);
        g.glEnd();
        
    
    }
    
    @Override public void drawObjectC (GL g, float R, float G, float B){

        g.glColor3f(R, G, B);
        
        float ax = convCoordX(vs.leeax());
        float ay = convCoordX(vs.leeay());
        /** convierte la y en posiciones de pantalla */
        float bx = convCoordX(vs.leebx());
        float by = convCoordX(vs.leeby());
        
        g.glLineWidth(3.0f);
        g.glBegin(GL.GL_LINES);
            g.glVertex2d(ax, ay);
            g.glVertex2d(bx, by);
        g.glEnd();

    }
    
}
