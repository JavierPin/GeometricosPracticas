package algGeom;

import java.util.ArrayList;
import javax.media.opengl.GL;

public class TNetwork {
    
    ArrayList<TriangleTin> triangulos;
    ArrayList<Segment3d> aristas;
    ArrayList<Vect3d> vertices;
    
    public double XMax;
    public double YMax;
    public double XMin;
    public double YMin;
    public double ZMin;
    public double ZMax;
    
    Delaunay_Triangulation triangulation = new Delaunay_Triangulation();
    
    public TNetwork(Delaunay_Triangulation dtri){
        

        triangulation = dtri;
        triangulos = new ArrayList<>();
        aristas = new ArrayList<>();
        vertices = new ArrayList<>();
        
        XMin = triangulation.delaunayXMin();
        XMax = triangulation.delaunayXMax();
        YMin = triangulation.delaunayYMin();
        YMax = triangulation.delaunayYMax();
        ZMin = triangulation.delaunayZMin();
        ZMax = triangulation.delaunayZMax();
        
        //int contador=0;
        
        for (int i=0; i<triangulation.trianglesSize();i++){
            
            Triangle_dt tri = triangulation.getTriangleAt(i);

            if(!tri.isHalfplane() && tri!=null){
                
                TriangleTin p = new TriangleTin(dtri.getTriangleAt(i));
                
                aristas.add(p.a1);
                p.a1.tIzq=p;
                try{
                    p.a1.tDer=new TriangleTin(tri.next_12());
                    p.a1.tDer.toOrigin(XMin, XMax, YMin, YMax);
                } catch (Exception e) {
                    p.a1.tDer=p;
                }
                
                aristas.add(p.a2);
                p.a2.tIzq=p;
                try{
                    p.a2.tDer=new TriangleTin(tri.next_23());
                    p.a2.tDer.toOrigin(XMin, XMax, YMin, YMax);
                } catch (Exception e) {
                    p.a2.tDer=p;
                }

                aristas.add(p.a3);
                p.a3.tIzq=p;
                try{
                    p.a3.tDer=new TriangleTin(tri.next_31());
                    p.a3.tDer.toOrigin(XMin, XMax, YMin, YMax);
                } catch (Exception e) {
                    p.a3.tDer=p;
                }

                p.toOrigin(XMin, XMax, YMin, YMax);

                triangulos.add(p);
 
            }
        }        
        
    }
    
    public void asignaTrianguloDerecha(){
        int contador = 0;
        
        for(int i=0; i<aristas.size();i++){
            
            Segment3d arista = aristas.get(i);
            
            
            
            try{
                Triangle_dt dtTri = new Triangle_dt(arista.tIzq);
                arista.tDer = new TriangleTin(dtTri.next_12());
            } catch (Exception e) {
                //System.out.println("ta ta taaa, ta taaaa");
                contador++;
            }
            
        }
        
        System.out.println("Aristas no tomadas: "+contador+"/"+aristas.size());
    }
    
    public TriangleTin getTriangle(int i){
        
        return triangulos.get(i);
        
    }
    
        
    public java.util.Vector<TriangleTin> route(TriangleTin tInicio, TriangleTin tFinal, Ray3d r){
        
        java.util.Vector<TriangleTin> ruta = new java.util.Vector<TriangleTin>();
        TriangleTin next = tInicio;
        Ray3d rr = r;
        
        while(!isEqual(next,tFinal)){
            
            next = nextTriangleInRoute(next,rr);

            if(next != tFinal){
                
                ruta.add(next);
                
            }
            
            rr = new Ray3d(next.centroide(),r.dest);
            
        }

        return ruta;
    }
    
    public TriangleTin nextTriangleInRoute(TriangleTin ttt, Ray3d rrr){
        
        algGeom.Vector v1 = new algGeom.Vector(ttt.getA().x,ttt.getA().y);
        algGeom.Vector v2 = new algGeom.Vector(ttt.getB().x,ttt.getB().y);
        algGeom.Vector v3 = new algGeom.Vector(ttt.getC().x,ttt.getC().y);
        
        SegmentLine s1 = new SegmentLine(v1,v2);
        SegmentLine s2 = new SegmentLine(v2,v3);
        SegmentLine s3 = new SegmentLine(v3,v1);
        
        RayLine rayo = new RayLine(new Vertex(new Point(rrr.orig.x,rrr.orig.y)),new Vertex(new Point(rrr.dest.x,rrr.dest.y)));
        
        algGeom.Vector punto = new algGeom.Vector();
        
        if (rayo.intersecta(s1, punto) && ttt.a1.tDer!=ttt){
                return busca(ttt.a1.tDer);
        }
        if (rayo.intersecta(s2, punto) && ttt.a2.tDer!=ttt){
                return busca(ttt.a2.tDer);
        }
        if (rayo.intersecta(s3, punto) && ttt.a3.tDer!=ttt){
                return busca(ttt.a3.tDer);
        }

        
        return ttt;
    }
    
    private boolean isEqual(TriangleTin m, TriangleTin n){
        
        if(n.a1.orig.x==m.a1.orig.x){
            if(n.a1.orig.y==m.a1.orig.y){
                if(n.a1.orig.z==m.a1.orig.z){

                    if(n.a2.orig.x==m.a2.orig.x){
                        if(n.a2.orig.y==m.a2.orig.y){
                            if(n.a2.orig.z==m.a2.orig.z){

                                if(n.a3.orig.x==m.a3.orig.x){
                                    if(n.a3.orig.y==m.a3.orig.y){
                                        if(n.a3.orig.z==m.a3.orig.z){

                                            return true;


                                        }
                                    }
                                }


                            }
                        }
                    }

                }
            }
        }
        return false;
    }
    
    private TriangleTin busca(TriangleTin n){
        
        
        for (int i=0;i<triangulos.size();i++){
            
            
            if(n.a1.orig.x==triangulos.get(i).a1.orig.x){
                if(n.a1.orig.y==triangulos.get(i).a1.orig.y){
                    if(n.a1.orig.z==triangulos.get(i).a1.orig.z){
                        
                        if(n.a2.orig.x==triangulos.get(i).a2.orig.x){
                            if(n.a2.orig.y==triangulos.get(i).a2.orig.y){
                                if(n.a2.orig.z==triangulos.get(i).a2.orig.z){

                                    if(n.a3.orig.x==triangulos.get(i).a3.orig.x){
                                        if(n.a3.orig.y==triangulos.get(i).a3.orig.y){
                                            if(n.a3.orig.z==triangulos.get(i).a3.orig.z){

                                                return triangulos.get(i);


                                            }
                                        }
                                    }


                                }
                            }
                        }
                        
                    }
                }
            }
        }
        
        return null;
    }

    
}
