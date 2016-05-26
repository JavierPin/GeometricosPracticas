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
    
    Delaunay_Triangulation triangulation;
    
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

                //System.out.println(tri.c.z);
                
                TriangleTin p = new TriangleTin(dtri.getTriangleAt(i));
                aristas.add(p.a1);
                p.a1.tIzq=p;

                aristas.add(p.a2);
                p.a2.tIzq=p;

                aristas.add(p.a3);
                p.a3.tIzq=p;
                
                p.toOrigin(XMin, XMax, YMin, YMax);
                
                triangulos.add(p);
                
                //contador++;
            }
        }
        
        //System.out.println("Contador = "+contador);
          
        asignaTrianguloDerecha();
        
        
    }
    
    public void asignaTrianguloDerecha(){
        
        for(int i=0; i<aristas.size();i++){
            
            Segment3d arista = aristas.get(i);
            try{
                arista.tDer = new TriangleTin(triangulation.compartido(new Triangle_dt(arista.tIzq), arista));
            } catch (Exception e) {
                //System.out.println("ta ta taaa, ta taaaa");
            }
            
        }

    }
    
}
