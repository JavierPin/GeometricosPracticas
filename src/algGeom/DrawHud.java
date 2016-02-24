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
    GL g;
    
    
   public DrawHud(GL _g){
       g=_g;
       visible=true;
       rango=BasicGeom.RANGO;
       intervalo= (double)rango/10;
       grosorPrincipal=0.5f;
       R=0.01f;
       G=0.31f;
       B=0.59f;
       DrawAxis();
   }
   
   public void DrawAxis(){
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
   public void MarcaPunto (Point p){
       DrawSegment marca;
       SegmentLine sy = new SegmentLine(new Point(0,p.y),p);//segmento de eje y
       SegmentLine sx = new SegmentLine(new Point(p.x,0),p);//segmento de eje x
       marca= new DrawSegment(sy);
       marca.drawObjectC(g, R, G, B, 0.01f);
       marca= new DrawSegment(sx);
       marca.drawObjectC(g, R, G, B, 0.01f);
   }
   
   public void PuntoAvanzado (Point p){
       DrawSegment marca;
       
       SegmentLine sy1 = new SegmentLine(new Point(p.x,p.y+1.2),
                                        new Point(p.x,p.y+0.2));
       SegmentLine sy2 = new SegmentLine(new Point(p.x,p.y-1.5),
                                        new Point(p.x,p.y-0.5));
       SegmentLine sx1 = new SegmentLine(new Point(p.x-1.5,p.y),
                                         new Point(p.x-0.5,p.y));
       SegmentLine sx2 = new SegmentLine(new Point(p.x+1.2,p.y),
                                         new Point(p.x+0.2,p.y));
       
       marca= new DrawSegment(sy1);
       marca.drawObjectC(g, R, G, B, 0.01f);
       marca= new DrawSegment(sx1);
       marca.drawObjectC(g, R, G, B, 0.01f);
       marca= new DrawSegment(sy2);
       marca.drawObjectC(g, R, G, B, 0.01f);
       marca= new DrawSegment(sx2);
       marca.drawObjectC(g, R, G, B, 0.01f);   
   }
   public void PuntoAvanzado (Point p,float _R,float _G, float _B){
       float oR=R,oG=G,oB=B;
       R=_R;
       G=_G;
       B=_B;
       PuntoAvanzado(p);
       R=oR;
       G=oG;
       B=oB;
   }
   
}
