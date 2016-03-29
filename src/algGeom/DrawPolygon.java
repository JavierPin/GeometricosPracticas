package algGeom;

import javax.media.opengl.*;


public class DrawPolygon extends Draw {
       
    Polygon vp;
    /** Creates a new instance of VisuPunto */
    public DrawPolygon (Polygon p) {
        vp = p;
    }
    
    @Override public void drawObject (GL g){
        /** convertir las distintas coordenadas a posiciones de pantalla */
        int tama = vp.numeroVertices();
        
        g.glBegin(GL.GL_POLYGON); 
        for (int i = 0; i<tama; i++){
           float sy = convCoordY(vp.lee(i).getY());
           float sx = convCoordX(vp.lee(i).getX());
           g.glVertex2f (sx,sy);
        }    
        g.glEnd();
        
    }
    
    @Override public void drawObjectC (GL g, float R, float G, float B){

        /** convertir las distintas coordenadas a posiciones de pantalla */
        int tama = vp.numeroVertices();
        g.glColor3f(R, G, B);
        g.glBegin(GL.GL_POLYGON); 
        for (int i = 0; i<tama; i++){
           float sy = convCoordY(vp.lee(i).getX());
           float sx = convCoordX(vp.lee(i).getY());
           g.glVertex2f (sx,sy);
        }    
        g.glEnd();
        
        g.glColor3f(1, 1, 1);
        
    }
    
    //Ahora con transparencia
    public void drawObjectC (GL g, float R, float G, float B, float alpha){

        /** convertir las distintas coordenadas a posiciones de pantalla */
        int tama = vp.numeroVertices();
        //g.glColor3f(R, G, B);
        g.glEnable(g.GL_BLEND);
       
        g.glBlendFunc(g.GL_SRC_ALPHA, g.GL_ONE_MINUS_SRC_ALPHA);
        g.glColor4f(R, G, B, alpha);
        g.glBegin(GL.GL_POLYGON); 
        for (int i = 0; i<tama; i++){
           float sy = convCoordY(vp.lee(i).getX());
           float sx = convCoordX(vp.lee(i).getY());
           g.glVertex2f (sx,sy);
        }    
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         g.glDisable(g.GL_BLEND);
        g.glColor4f(1, 1, 1, 1);
        //g.glColor3f(1, 1, 1);
        g.glEnd();
        
    }
    
}
