package algGeom;


import javax.media.opengl.*;

public class DrawCloud extends Draw {

    PointCloud pc;
    /** Creates a new instance of VisuPoint */
    public DrawCloud (PointCloud p) {
        pc = p;
    }

    public PointCloud getPoint (){
        return pc;
    }

    @Override public void drawObject (GL g){

        float x,y;

        g.glBegin(GL.GL_POINTS);
            for (int i=0; i<pc.tama(); i++){
                Point p = pc.getPunto(i);
                x = convCoordX(p.getX());
                y = convCoordY(p.getY());
                g.glVertex2d(x, y);
            }
	g.glEnd();

    }

    @Override public void drawObjectC (GL g, float R, float G, float B){

        float x,y;
        g.glColor3f(R, G, B);

        g.glBegin(GL.GL_POINTS);
            for (int i=0; i<pc.tama(); i++){
                Point p = pc.getPunto(i);
                x = convCoordX(p.getX());
                y = convCoordY(p.getY());
                g.glVertex2d(x, y);
            }
	g.glEnd();
    }

}

