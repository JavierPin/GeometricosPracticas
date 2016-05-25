package algGeom;

public class Segment3d extends Edge3d{

    TriangleTin tIzq;
    TriangleTin tDer;
    
    public Segment3d (Vect3d o, Vect3d d)  {
        super (o,d);
    }
    
    public Segment3d (Vect3d o, Vect3d d, TriangleTin iz, TriangleTin de){
        super (o,d);
        tIzq=iz;
        tDer=de;
    }
    
    public void setTIzq(TriangleTin iz){
        
        tIzq=iz;
    }
    
    public TriangleTin trianguloIzquierda(){
        
        return tIzq;
        
    }
    
    public void setTDer(TriangleTin de){
        
        tDer=de;
    }
    
    public TriangleTin trianguloDerecha(){
        
        return tDer;
        
    }
	
    /** Muestra en pantalla los valores los valores de Edge3d */
    public void out () {
        System.out.print ("Segment3d->Origen: ");
        orig.out();
        System.out.print ("Segment3d->Destino: ");
        dest.out();
    }

}
