package algGeom;

import javax.media.opengl.GL;

public class oneBox
{
    static void drawMe(GL gl)
    {
      /* draws the sides of a unit cube (0,0,0)-(1,1,1) */
      gl.glBegin(GL.GL_POLYGON);/* f1: front */
        gl.glNormal3f(-1.0f,0.0f,0.0f);
        gl.glVertex3f(0.0f,0.0f,0.0f);
        gl.glVertex3f(0.0f,0.0f,1.0f);
        gl.glVertex3f(1.0f,0.0f,1.0f);
        gl.glVertex3f(1.0f,0.0f,0.0f);
      gl.glEnd();
      gl.glBegin(GL.GL_POLYGON);/* f2: bottom */
        gl.glNormal3f(0.0f,0.0f,-1.0f);
        gl.glVertex3f(0.0f,0.0f,0.0f);
        gl.glVertex3f(1.0f,0.0f,0.0f);
        gl.glVertex3f(1.0f,1.0f,0.0f);
        gl.glVertex3f(0.0f,1.0f,0.0f);
      gl.glEnd();
      gl.glBegin(GL.GL_POLYGON);/* f3:back */
        gl.glNormal3f(1.0f,0.0f,0.0f);
        gl.glVertex3f(1.0f,1.0f,0.0f);
        gl.glVertex3f(1.0f,1.0f,1.0f);
        gl.glVertex3f(0.0f,1.0f,1.0f);
        gl.glVertex3f(0.0f,1.0f,0.0f);
      gl.glEnd();
      gl.glBegin(GL.GL_POLYGON);/* f4: top */
        gl.glNormal3f(0.0f,0.0f,1.0f);
        gl.glVertex3f(1.0f,1.0f,1.0f);
        gl.glVertex3f(1.0f,0.0f,1.0f);
        gl.glVertex3f(0.0f,0.0f,1.0f);
        gl.glVertex3f(0.0f,1.0f,1.0f);
      gl.glEnd();
      gl.glBegin(GL.GL_POLYGON);/* f5: left */
        gl.glNormal3f(0.0f,1.0f,0.0f);
        gl.glVertex3f(0.0f,0.0f,0.0f);
        gl.glVertex3f(0.0f,1.0f,0.0f);
        gl.glVertex3f(0.0f,1.0f,1.0f);
        gl.glVertex3f(0.0f,0.0f,1.0f);
      gl.glEnd();
      gl.glBegin(GL.GL_POLYGON);/* f6: right */
        gl.glNormal3f(0.0f,-1.0f,0.0f);
        gl.glVertex3f(1.0f,0.0f,0.0f);
        gl.glVertex3f(1.0f,0.0f,1.0f);
        gl.glVertex3f(1.0f,1.0f,1.0f);
        gl.glVertex3f(1.0f,1.0f,0.0f);
      gl.glEnd();
    }
}