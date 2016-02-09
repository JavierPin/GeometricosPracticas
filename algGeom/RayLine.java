package algGeom;


public class RayLine extends SegmentLine{
    
    public RayLine (Point a, Point b){
    	super (a,b);
    }
	
    public RayLine (SegmentLine s){
        a = s.a;
        b = s.b;
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