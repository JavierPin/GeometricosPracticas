package algGeom;


public class RayLine extends SegmentLine{
    
    public RayLine (Point a, Point b){
    	super (a,b);
    }
	
    public RayLine (SegmentLine s){
        a = s.a;
        b = s.b;
    }
 
    @Override
    public boolean intersecta (Line r, Vector interseccion){
        double s=0,t=0;
        return(intersecta(new Vector(r.a),new Vector(r.b),s,t)
               &&(s>=0));
    }
    
    @Override
    public boolean intersecta (RayLine r, Vector interseccion){
        double s=0,t=0;
        return(intersecta(new Vector(r.a),new Vector(r.b),s,t)
               &&(s>=0) && t>=0);
    }
    
        @Override
    public boolean intersecta (SegmentLine r, Vector interseccion){
        double s=0,t=0;
        return(intersecta(new Vector(r.a),new Vector(r.b),s,t)
               &&(s>=0) && (t>=0 && t<=1));
    }
    
    
    /** Muestra en pantalla la informacion del RayLine. */
    public void out () {
        System.out.println ("RayLine->");
        System.out.println ("Punto a: ");
        a.out ();
        System.out.println ("Punto b: ");
        b.out ();
    }
    
}