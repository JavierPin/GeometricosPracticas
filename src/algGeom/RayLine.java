package algGeom;


public class RayLine extends SegmentLine{
    
    public RayLine (Point a, Point b){
    	super (a,b);
    }
	
    public RayLine (SegmentLine s){
        a = s.a;
        b = s.b;
    }
 
    public float distPuntoRayo(Vector p){
        
        Vector m = new Vector(b.x-a.x,b.y-a.y);
        Vector b = new Vector(a.x,a.y);
        
        double t0 = ((m.dot(p.resta(b)))/(m.dot(m)));
        
        if (t0<=BasicGeom.CERO){
            
            return (float)(p.resta(b)).modulo();
        }
        else{
            
            return (float)(p.resta(b.suma(m.prodEsc(t0)))).modulo();
        }
        
    }
    
    @Override
    public boolean intersecta (Line r, Vector interseccion){
        double[] t = new double[1];
        double[] s = new double[1];
        if(intersecta(new Vector(r.a),new Vector(r.b),s,t)
               &&(s[0]>=0)){
                //Calculo el punto de interseccion
        interseccion.asignax(b.resta(a).x*s[0] + a.x);
        interseccion.asignay(b.resta(a).y*s[0] + a.y);
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public boolean intersecta (RayLine r, Vector interseccion){
        double[] t = new double[1];
        double[] s = new double[1];
        if(intersecta(new Vector(r.a),new Vector(r.b),s,t)
               &&(s[0]>=0) && t[0]>=0){
                //Calculo el punto de interseccion
        interseccion.asignax(b.resta(a).x*s[0] + a.x);
        interseccion.asignay(b.resta(a).y*s[0] + a.y);
            return true;
        }else{
            return false;
        }
    }
    
        @Override
    public boolean intersecta (SegmentLine r, Vector interseccion){
        double[] t = new double[1];
        double[] s = new double[1];
        if(intersecta(new Vector(r.a),new Vector(r.b),s,t)
               &&(s[0]>=0) && (t[0]>=0 && t[0]<=1)){
                //Calculo el punto de interseccion
        interseccion.asignax(b.resta(a).x*s[0] + a.x);
        interseccion.asignay(b.resta(a).y*s[0] + a.y);
            return true;
        }else{
            return false;
        }
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