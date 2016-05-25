package algGeom;
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
                
                
                Triangle_dt der = null;
                der = delaunay.compartido(tri, tTin.a1);

                if(der!=null){
                   tTin.a1.tDer = new TriangleTin(tri); 
                   tTin.a1.tDer.toOrigin(XMin,XMax,YMin,YMax);
                }
                
                tTin.toOrigin(XMin,XMax,YMin,YMax);
                tTin.a1.tIzq = tTin;
                
                triangulos.add(tTin);
            }
        }
        
    }
    
}
