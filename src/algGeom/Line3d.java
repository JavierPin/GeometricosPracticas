package algGeom;

public class Line3d extends Edge3d{

   public Line3d (Vect3d o, Vect3d d)  {
	   super (o,d);
   }
        
	
    /** Muestra en pantalla los valores los valores de Edge3d */
    public void out () {
        System.out.print ("Line3d->Origen: ");
        orig.out();
        System.out.print ("Line3d->Destino: ");
        dest.out();
    }

}
