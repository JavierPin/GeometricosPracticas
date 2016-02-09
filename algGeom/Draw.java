package algGeom;

import javax.media.opengl.*;


public abstract class Draw {

  static int ANCHO , ALTO,  FONDO=100, ANCHO_PUNTO=6;

  public float convCoordX (double x) {
      return ((float)(x*ANCHO)/(2*BasicGeom.RANGO)+(ANCHO/2));
  }

  public float convCoordY (double y) {
      return ((float)((y*ALTO)/(2*BasicGeom.RANGO)+(ALTO/2)));
  }

  public float convCoordZ (double y) {
      return ((float)((y*FONDO)/(2*BasicGeom.RANGO)+(FONDO/2)));
  }

  
    /** primitiva abstracta */

    public abstract void drawObject (GL gl);
    public abstract void drawObjectC (GL gl, float R, float G, float B);
}
