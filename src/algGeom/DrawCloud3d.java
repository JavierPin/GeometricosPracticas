package algGeom;

import javax.media.opengl.*;

public class DrawCloud3d extends Draw{
    
    Cloud3d cloud;
    
    public DrawCloud3d(Cloud3d cc){
        
        cloud=cc;
        
    }
    
    public Cloud3d getCloud(){
        
        return cloud;
        
    }
    
    @Override public void drawObject(GL g){

        g.glBegin(GL.GL_POINTS);
            for (int i=0; i<cloud.tama(); i++){
                g.glVertex3d(cloud.getPunto(i).x, cloud.getPunto(i).y, cloud.getPunto(i).z);
            }
	g.glEnd();
        
    }
    
    @Override public void drawObjectC(GL g, float R, float G, float B){
        
        g.glColor3f(R, G, B);
        g.glBegin(GL.GL_POINTS);
            for (int i=0; i<cloud.tama(); i++){
                g.glVertex3d(cloud.getPunto(i).x, cloud.getPunto(i).y, cloud.getPunto(i).z);
            }
	g.glEnd();
        
        g.glColor3f(1, 1, 1);
        
    }
}
