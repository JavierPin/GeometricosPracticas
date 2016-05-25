package algGeom;
import java.util.ArrayList;
import java.util.Vector;
import javax.media.opengl.GL;

public class Tin {
    
    Vector<TriangleTin> triangulos;
    Vector<Vect3d> vertices;
    Vector<Segment3d> aristas;
    
    public double XMax;
    public double YMax;
    public double XMin;
    public double YMin;
    public double ZMin;
    public double ZMax;
    
    boolean first=true;
    
    public Tin(){
        
        triangulos = new Vector<TriangleTin>();
        vertices = new Vector<Vect3d>();
        aristas = new Vector<Segment3d>();
        
        XMin = 0;
        XMax = 0;
        YMin = 0;
        YMax = 0;
        ZMin = 0;
        ZMax = 0;

    }

    public Tin(Delaunay_Triangulation delaunay){
        
        triangulos = new Vector<TriangleTin>();
        vertices = new Vector<Vect3d>();
        aristas = new Vector<Segment3d>();
        
        XMin = delaunay.delaunayXMin();
        XMax = delaunay.delaunayXMax();
        YMin = delaunay.delaunayYMin();
        YMax = delaunay.delaunayYMax();
        ZMin = delaunay.delaunayZMin();
        ZMax = delaunay.delaunayZMax();
        
        for (int i=0;i<delaunay.trianglesSize();i++){
            
            Triangle_dt tri = delaunay.getTriangleAt(i);
            
            if(!tri.isHalfplane() && tri.c!=null){
                TriangleTin tTin = new TriangleTin(tri);

                //arista1
                Triangle_dt der = delaunay.compartido(tri, tTin.a1);
                if(der!=null){
                   tTin.a1.tDer = new TriangleTin(der); 
                }
                tTin.a1.tIzq = tTin;
                
                //arista2
                der = delaunay.compartido(tri, tTin.a2);
                if(der!=null){
                   tTin.a2.tDer = new TriangleTin(der); 
                }
                tTin.a2.tIzq = tTin;
                
                //arista3
                der = delaunay.compartido(tri, tTin.a3);
                if(der!=null){
                   tTin.a3.tDer = new TriangleTin(der); 
                }
                tTin.a3.tIzq = tTin;
                
                if(!tTin.a1.tDer.isDisplaced()){
                    tTin.a1.tDer.toOrigin(XMin,XMax,YMin,YMax);
                }
                if(!tTin.a2.tDer.isDisplaced()){
                    tTin.a2.tDer.toOrigin(XMin,XMax,YMin,YMax);
                }
                if(!tTin.a3.tDer.isDisplaced()){
                    tTin.a3.tDer.toOrigin(XMin,XMax,YMin,YMax);
                }
                if(!tTin.a1.tIzq.isDisplaced()){
                    tTin.a1.tIzq.toOrigin(XMin, XMax, YMin, YMax);
                }
                
                if(tTin.a1.tIzq == null) System.out.println("err");
                if(tTin.a1.tDer == null) System.out.println("err");
                if(tTin.a2.tIzq == null) System.out.println("err");
                if(tTin.a2.tDer == null) System.out.println("err");
                if(tTin.a3.tIzq == null) System.out.println("err");
                if(tTin.a3.tDer == null) System.out.println("err");
                if(tTin == null) System.out.println("err");
                
                triangulos.add(tTin);
            }
        }
        
    }
    
    public Vector<TriangleTin> route(GL g, TriangleTin tInicio, TriangleTin tFinal, Ray3d r){
        
        Vector<TriangleTin> ruta = new Vector<TriangleTin>();
        TriangleTin next = tInicio;
        
        while(next != tFinal){

            next = nextTriangleInRoute(g, next,r);
            
            if(next == null){
                
                next=tFinal;
                
            }
            if(next != tFinal){
                
                ruta.add(next);
                
            }
            
            tInicio=next;
        }

        return ruta;
    }
    
    public TriangleTin nextTriangleInRoute(GL g, TriangleTin ttt, Ray3d rrr){
        
        algGeom.Vector v1 = new algGeom.Vector(ttt.getA().x,ttt.getA().y);
        algGeom.Vector v2 = new algGeom.Vector(ttt.getB().x,ttt.getB().y);
        algGeom.Vector v3 = new algGeom.Vector(ttt.getC().x,ttt.getC().y);
        
        SegmentLine s1 = new SegmentLine(v1,v2);
        SegmentLine s2 = new SegmentLine(v2,v3);
        SegmentLine s3 = new SegmentLine(v3,v1);
        
        RayLine rayo = new RayLine(new Vertex(new Point(rrr.orig.x,rrr.orig.y)),new Vertex(new Point(rrr.dest.x,rrr.dest.y)));
        
        
        algGeom.Vector punto = new algGeom.Vector();
        Vector<algGeom.Vector> puntos = new Vector<algGeom.Vector>(0);
        
        if (rayo.intersecta(s1, punto)){
            puntos.add(punto);
        }
        else{
            puntos.add(new algGeom.Vector(-BasicGeom.INFINITO,-BasicGeom.INFINITO));
        }
        
        if (rayo.intersecta(s2, punto)){
            puntos.add(punto);
        }
        else{
            puntos.add(new algGeom.Vector(-BasicGeom.INFINITO,-BasicGeom.INFINITO));
        }
        
        if (rayo.intersecta(s3, punto)){
            puntos.add(punto);
        }
        else{
            puntos.add(new algGeom.Vector(-BasicGeom.INFINITO,-BasicGeom.INFINITO));
        }
        
        if(puntos.get(0).distancia(rayo.b) > puntos.get(1).distancia(rayo.b) && puntos.get(0).distancia(rayo.b) > puntos.get(2).distancia(rayo.b)){
            
            return ttt.a1.tDer;
            
        }
        else{
            
            if(puntos.get(1).distancia(rayo.b)>puntos.get(2).distancia(rayo.b)){
                
                return ttt.a2.tDer;
                
            }
            
            return ttt.a3.tDer;
            
        }
        
        /*if (rayo.intersecta(s1, punto) && ttt.a1.tDer!=ttt){
            if(first){
                DrawSegment3d s = new DrawSegment3d(new Segment3d(new Vect3d(v1.x,v1.y,0),new Vect3d(v2.x,v2.y,0)));
                s.drawObjectC(g, 1,1,0);
                first=false;
                return ttt.a1.tDer;
            }
            else{
                first=true;
            }
        }
        if (rayo.intersecta(s2, punto) && ttt.a2.tDer!=ttt){
            if(first){
                DrawSegment3d s = new DrawSegment3d(new Segment3d(new Vect3d(v2.x,v2.y,0),new Vect3d(v3.x,v3.y,0)));
                s.drawObjectC(g, 1,1,0);
                first=false;
                return ttt.a2.tDer;
            }
            else{
                first=true;
            }
        }
        if (rayo.intersecta(s3, punto) && ttt.a3.tDer!=ttt){
            if(first){
                DrawSegment3d s = new DrawSegment3d(new Segment3d(new Vect3d(v3.x,v3.y,0),new Vect3d(v1.x,v1.y,0)));
                s.drawObjectC(g, 1,1,0);
                first=false;
                return ttt.a3.tDer;
            }
            else{
                first=true;
            }
        }

        
        return ttt;*/
    }
    
    public TriangleTin getTriangle(int i){
        
        return triangulos.get(i);
        
    }
    
}
