package algGeom;

import javax.media.opengl.*;

public class DrawAABB extends Draw{
    
    AABB box;
    
    public DrawAABB(AABB bb){
        
        box = bb;
        
    }
    
    public AABB getAABB(){
        
        return box;
        
    }
    
    public void drawWireObject(GL g){
        
        g.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_LINE);
        g.glBegin(GL.GL_QUADS);
 
            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.max.z));

            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.min.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.min.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.min.z));

            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.min.z));

            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.min.z));

            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.min.z));

            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.min.z));

        g.glEnd();
        g.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_FILL);
        
    }
    
    @Override public void drawObject(GL g){
        
        g.glBegin(GL.GL_QUADS);
 
            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.max.z));

            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.min.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.min.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.min.z));

            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.min.z));

            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.min.z));

            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.min.z));

            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.min.z));

        g.glEnd();
        
    }
    
    public void drawWireObjectC(GL g,float R, float G, float B){
        
        g.glColor3f(R, G, B);
        g.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_LINE);
        g.glBegin(GL.GL_QUADS);
 
            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.max.z));

            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.min.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.min.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.min.z));

            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.min.z));

            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.min.z));

            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.min.z));

            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.min.z));

        g.glEnd();
        g.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_FILL);
        g.glColor3f(1, 1, 1);
        
    }
    
    @Override public void drawObjectC(GL g,float R, float G, float B){
        
        g.glColor3f(R, G, B);
        g.glBegin(GL.GL_QUADS);
 
            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.max.z));

            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.min.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.min.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.min.z));

            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.min.z));

            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.min.z));

            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.max.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.max.y),(float)(box.min.z));

            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.max.z));
            g.glVertex3f((float)(box.min.x),(float)(box.min.y),(float)(box.min.z));
            g.glVertex3f((float)(box.max.x),(float)(box.min.y),(float)(box.min.z));

        g.glEnd();
        
        g.glColor3f(1, 1, 1);
        
    }
    
}
