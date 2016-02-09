package algGeom;


public class Line extends SegmentLine{
    
    public Line (Point a, Point b){
    	super (a,b);
    }
	
    public Line (SegmentLine s){
        a = s.a;
        b = s.b;
    }
        
        
    /** Muestra en pantalla la informacion del RayLine. */
    public void out () {
        System.out.println ("Line->");
        System.out.println ("Punto a: ");
        a.out ();
        System.out.println ("Punto b: ");
        b.out ();
    }

    
}
