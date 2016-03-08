package algGeom;


import javax.media.opengl.*;

public class DrawAxis3d extends Draw{
    
    
    @Override public void drawObject (GL gl){
        
        Line3d l = new Line3d(new Vect3d(0,0,0),new Vect3d(1,0,0));
        DrawLine3d xAxis = new DrawLine3d(l);
        xAxis.drawObject(gl);
        
        l = new Line3d(new Vect3d(0,0,0),new Vect3d(0,1,0));
        xAxis = new DrawLine3d(l);
        xAxis.drawObject(gl);
        
        l = new Line3d(new Vect3d(0,0,0),new Vect3d(0,0,1));
        xAxis = new DrawLine3d(l);
        xAxis.drawObject(gl);
        
    }
    
    @Override public void drawObjectC (GL gl, float R, float G, float B){
        
        
    }
    
}
