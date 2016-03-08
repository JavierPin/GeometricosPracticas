package algGeom;

public class Line3d extends Edge3d{

   public Line3d (Vect3d o, Vect3d d)  {
	   super (o,d);
   }
        
   public double distancia (Vect3d p){
        Vect3d M, B;
        B = new Vect3d(orig.x,orig.y,orig.z);
        //M = new Vector((new Vector(b.x,b.y)).resta(B));
        M = new Vect3d(dest.x-orig.x,dest.y-orig.y,dest.z-orig.z);
        double t0 = M.dot(new Vect3d(p.x-B.x,p.y-B.y,p.z-B.z))/M.dot(M);
        //double t0=M.dot((p.resta(B)))/M.dot(M);
       return (float)(p.resta(B.suma(M.prodEscalar(t0)))).modulo();
   }
	
    /** Muestra en pantalla los valores los valores de Edge3d */
    public void out () {
        System.out.print ("Line3d->Origen: ");
        orig.out();
        System.out.print ("Line3d->Destino: ");
        dest.out();
    }

}
