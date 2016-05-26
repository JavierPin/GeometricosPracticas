package algGeom;

import java.util.ArrayList;

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
    
}
