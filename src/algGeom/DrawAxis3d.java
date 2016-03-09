package algGeom;


import javax.media.opengl.*;

public class DrawAxis3d extends Draw{
    
    
    @Override public void drawObject (GL gl){
        
        gl.glLineWidth(2);
        gl.glColor3f(1, 0, 0);
        Line3d l = new Line3d(new Vect3d(0,0,0),new Vect3d(1,0,0));
        DrawLine3d xAxis = new DrawLine3d(l);
        xAxis.drawObject(gl);
        
        gl.glColor3f(0, 1, 0);
        l = new Line3d(new Vect3d(0,0,0),new Vect3d(0,1,0));
        xAxis = new DrawLine3d(l);
        xAxis.drawObject(gl);
        
        gl.glColor3f(0, 0, 1);
        l = new Line3d(new Vect3d(0,0,0),new Vect3d(0,0,1));
        xAxis = new DrawLine3d(l);
        xAxis.drawObject(gl);
        
        gl.glColor3f(0,0,0);
        gl.glLineWidth(1);
        
    }
    
    @Override public void drawObjectC (GL gl, float R, float G, float B){
        
        
    }
    
}
