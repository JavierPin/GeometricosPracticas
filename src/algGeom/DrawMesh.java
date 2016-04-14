package algGeom;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL;

import com.sun.opengl.util.BufferUtil;



public class DrawMesh extends Draw{
	
	Mesh m;
	
	/** Creates a new instance of VisuPunto */
    public DrawMesh (Mesh m) {
        this.m  = m;
    }
    

 @Override public void drawObject (GL g){
    
     for (int i = 0; i<m.getSizeCaras(); i++)
        {    
        	Triangle3d t = new Triangle3d (m.getTriangulo(i));
		DrawTriangle3d dt = new DrawTriangle3d(t);
		dt.drawObject(g);
        }
     
     	 
		 
         
	 }
	 
@Override public void drawObjectC (GL g, float R, float G, float B){

//XXX

}
}
