/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algGeom;
import static algGeom.Prueba.gl;
import javax.media.opengl.*;

/**
 *
 * @author javie
 */
public class DrawHud {
    boolean visible;
    int rango;
    Double intervalo;
    float grosorPrincipal;
    float R,G,B;
    
    
   public DrawHud(GL g){
       visible=true;
       rango=BasicGeom.RANGO;
       intervalo= (double)rango/10;
       grosorPrincipal=0.5f;
       R=0.01f;
       G=0.31f;
       B=0.59f;        
   }
   
   public void DrawAxis(GL g){
       //Creo los ejes cartesianos
       SegmentLine vert = new SegmentLine(new Point(0,rango),new Point(0,-rango));
       SegmentLine hori = new SegmentLine(new Point(rango,0),new Point(-rango,0));
       DrawSegment dv = new DrawSegment (vert);
       DrawSegment dh = new DrawSegment (hori);
       dv.drawObjectC(gl, R, G, B,grosorPrincipal);
       dh.drawObjectC(gl, R, G, B,grosorPrincipal);
       
       //Le doy metrica a los ejes
       Point uy;
       Point dy;
       DrawSegment marca;
       for(double i=-rango;i<hori.leeax();i=i+intervalo){
           uy = new Point (i,0.5f);
           dy = new Point (i,-0.5f);
           marca = new DrawSegment(new SegmentLine(uy,dy));
           marca.drawObjectC(g, R, G, B, grosorPrincipal);
       }

       for(double i=-rango;i<vert.leeay();i=i+intervalo){
           uy = new Point (0.5f,i);
           dy = new Point (-0.5f,i);
           marca = new DrawSegment(new SegmentLine(uy,dy));
           marca.drawObjectC(g, R, G, B, grosorPrincipal);
       }
   }
}
