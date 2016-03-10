package algGeom;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.media.opengl.*;

public class DrawPoint extends Draw {
    
    Point vp;
    /** Creates a new instance of VisuPoint */
    public DrawPoint (Point p) {
        vp = p;
    }
    
    public Point getPoint (){
        return vp;
    }
    
    @Override public void drawObject (GL g){

        /** pasar a coordenadas de pantalla */

        float x = convCoordX(vp.getX());
        /** convierte la y en posiciones de pantalla */
        float y = convCoordY(vp.getY());
        /** pasar a coordenadas de pantalla */

        g.glPointSize(5.0f);
        g.glBegin(GL.GL_POINTS);
            g.glVertex2d(x, y);
    	g.glEnd();
         
    }
    
    @Override public void drawObjectC (GL g, float R, float G, float B){

        g.glColor3f(R, G, B);
        
        float x = convCoordX(vp.getX());
        /** convierte la y en posiciones de pantalla */
        float y = convCoordY(vp.getY());
        /** pasar a coordenadas de pantalla */
        //int tama = ANCHO_Point;

        g.glPointSize(5f);
        g.glBegin(GL.GL_POINTS);
            g.glVertex2d(x, y);
        g.glEnd();
        
        g.glColor3f(1, 1, 1);
        
    }
}

