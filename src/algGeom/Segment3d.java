package algGeom;

public class Segment3d extends Edge3d{

   public Segment3d (Vect3d o, Vect3d d)  {
	   super (o,d);
   }
        
	
    /** Muestra en pantalla los valores los valores de Edge3d */
    public void out () {
        System.out.print ("Segment3d->Origen: ");
        orig.out();
        System.out.print ("Segment3d->Destino: ");
        dest.out();
    }

}
