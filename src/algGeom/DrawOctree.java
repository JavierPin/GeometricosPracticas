package algGeom;

import javax.media.opengl.*;

public class DrawOctree extends Draw{
    
    Octree oct;
    
    public DrawOctree(Octree oo){
        
        oct = oo;
        
    }
    
    public Octree getOctree(){
        
        return oct;
        
    }
    
    public void drawWireObject(GL g){
        
        g.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_LINE);
        g.glBegin(GL.GL_QUADS);
 
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.max.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.max.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.min.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.min.y),(float)(oct.box.max.z));

            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.max.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.max.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.min.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.min.y),(float)(oct.box.min.z));

            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.max.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.min.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.min.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.max.y),(float)(oct.box.min.z));

            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.max.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.min.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.min.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.max.y),(float)(oct.box.min.z));

            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.max.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.max.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.max.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.max.y),(float)(oct.box.min.z));

            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.min.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.min.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.min.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.min.y),(float)(oct.box.min.z));

        g.glEnd();
        g.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_FILL);
        
    }
    
    @Override public void drawObject(GL g){
        
        g.glBegin(GL.GL_QUADS);
 
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.max.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.max.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.min.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.min.y),(float)(oct.box.max.z));

            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.max.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.max.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.min.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.min.y),(float)(oct.box.min.z));

            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.max.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.min.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.min.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.max.y),(float)(oct.box.min.z));

            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.max.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.min.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.min.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.max.y),(float)(oct.box.min.z));

            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.max.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.max.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.max.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.max.y),(float)(oct.box.min.z));

            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.min.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.min.y),(float)(oct.box.max.z));
            g.glVertex3f((float)(oct.box.min.x),(float)(oct.box.min.y),(float)(oct.box.min.z));
            g.glVertex3f((float)(oct.box.max.x),(float)(oct.box.min.y),(float)(oct.box.min.z));

        g.glEnd();
        
    }
    
    public void drawWireObjectC(GL g,float R, float G, float B, AABB nodeBox){
        
        g.glColor3f(R, G, B);
        g.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_LINE);
        g.glBegin(GL.GL_QUADS);
 
            g.glVertex3f((float)(nodeBox.max.x),(float)(nodeBox.max.y),(float)(nodeBox.max.z));
            g.glVertex3f((float)(nodeBox.min.x),(float)(nodeBox.max.y),(float)(nodeBox.max.z));
            g.glVertex3f((float)(nodeBox.min.x),(float)(nodeBox.min.y),(float)(nodeBox.max.z));
            g.glVertex3f((float)(nodeBox.max.x),(float)(nodeBox.min.y),(float)(nodeBox.max.z));

            g.glVertex3f((float)(nodeBox.max.x),(float)(nodeBox.max.y),(float)(nodeBox.min.z));
            g.glVertex3f((float)(nodeBox.min.x),(float)(nodeBox.max.y),(float)(nodeBox.min.z));
            g.glVertex3f((float)(nodeBox.min.x),(float)(nodeBox.min.y),(float)(nodeBox.min.z));
            g.glVertex3f((float)(nodeBox.max.x),(float)(nodeBox.min.y),(float)(nodeBox.min.z));

            g.glVertex3f((float)(nodeBox.max.x),(float)(nodeBox.max.y),(float)(nodeBox.max.z));
            g.glVertex3f((float)(nodeBox.max.x),(float)(nodeBox.min.y),(float)(nodeBox.max.z));
            g.glVertex3f((float)(nodeBox.max.x),(float)(nodeBox.min.y),(float)(nodeBox.min.z));
            g.glVertex3f((float)(nodeBox.max.x),(float)(nodeBox.max.y),(float)(nodeBox.min.z));

            g.glVertex3f((float)(nodeBox.min.x),(float)(nodeBox.max.y),(float)(nodeBox.max.z));
            g.glVertex3f((float)(nodeBox.min.x),(float)(nodeBox.min.y),(float)(nodeBox.max.z));
            g.glVertex3f((float)(nodeBox.min.x),(float)(nodeBox.min.y),(float)(nodeBox.min.z));
            g.glVertex3f((float)(nodeBox.min.x),(float)(nodeBox.max.y),(float)(nodeBox.min.z));

            g.glVertex3f((float)(nodeBox.max.x),(float)(nodeBox.max.y),(float)(nodeBox.max.z));
            g.glVertex3f((float)(nodeBox.min.x),(float)(nodeBox.max.y),(float)(nodeBox.max.z));
            g.glVertex3f((float)(nodeBox.min.x),(float)(nodeBox.max.y),(float)(nodeBox.min.z));
            g.glVertex3f((float)(nodeBox.max.x),(float)(nodeBox.max.y),(float)(nodeBox.min.z));

            g.glVertex3f((float)(nodeBox.max.x),(float)(nodeBox.min.y),(float)(nodeBox.max.z));
            g.glVertex3f((float)(nodeBox.min.x),(float)(nodeBox.min.y),(float)(nodeBox.max.z));
            g.glVertex3f((float)(nodeBox.min.x),(float)(nodeBox.min.y),(float)(nodeBox.min.z));
            g.glVertex3f((float)(nodeBox.max.x),(float)(nodeBox.min.y),(float)(nodeBox.min.z));

        g.glEnd();
        g.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_FILL);
        g.glColor3f(1, 1, 1);
        
    }
    
    public void dib(GL g,NodoOctree n, float R, float G, float B){
        
        if (n.nivel==oct.getLimite() || n.hijosCreados==false){
                AABB box = n.box;
                drawWireObjectC(g, R,G,B,box);
        }
        else{
            for(int i=0;i<8;i++){
                dib(g,n.hijos[i],R,G,B); 
            }
        }
        
        
    }
    
    @Override public void drawObjectC(GL g,float R, float G, float B){
        
        dib(g,oct.getRaiz(),R,G,B);
        
        
    }
    
}
