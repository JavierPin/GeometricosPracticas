package algGeom;


public class Line extends SegmentLine{
    
    public Line (Point a, Point b){
    	super (a,b);
    }
	
    public Line (SegmentLine s){
        a = s.a;
        b = s.b;
    }
    
    /*Calcular la distacia punto recta dado un vector*/
    public float distPuntoRecta (Vector p){
        Vector u, aux;
        u = new Vector(b.x - a.x, b.y-a.y);
        aux = new Vector(p.x - a.x, p.y - a.y);
       
        return ((float)aux.dot(u)) / ((float)u.modulo());
    }
    
    public float distRectaRecta(Line r){
        
        Vector d0 = new Vector(b.x-a.x,b.y-a.y);
        Vector d1 = new Vector(r.b.x-r.a.x,r.b.y-r.a.y);
        
        Vector inc = new Vector(a.x-r.a.x,a.y-r.a.y);
        
        if (d0.dot(d1.perpendicular())!= BasicGeom.CERO){
            
            return 0;
            
        }
        else{
            
            return (float)(Math.abs(d0.perpendicular().dot(inc))/(d0.modulo()));
        }
        
    }
    
    public float distRectaRayo(RayLine r){
        
        Vector d0 = new Vector(b.x-a.x,b.y-a.y);
        Vector d1 = new Vector(r.b.x-r.a.x,r.b.y-r.a.y);
        
        Vector inc = new Vector(a.x-r.a.x,a.y-r.a.y);
        
        if (((d0.perpendicular().dot(d1))*(d0.perpendicular().dot(inc)))< BasicGeom.CERO){
            
            return 0;
            
        }
        else{
            
            return (float)(Math.abs(d0.perpendicular().dot(inc))/(d0.modulo()));
        }
        
    }
    
    public float distRectaSegmento(SegmentLine r){
        
        Vector d0 = new Vector(b.x-a.x,b.y-a.y);
        Vector d1 = new Vector(r.b.x-r.a.x,r.b.y-r.a.y);
        
        Vector inc = new Vector(a.x-r.a.x,a.y-r.a.y);
        
        if (((d0.perpendicular().dot(inc))*(d0.perpendicular().dot(inc.suma(d1))))< BasicGeom.CERO){
            
            return 0;
            
        }
        else{
            
            return (float)Math.min((Math.abs(d0.perpendicular().dot(inc))/(d0.modulo())),(Math.abs(d0.perpendicular().dot(inc.suma(d1)))/(d0.modulo())));
            
        }
        
    }
    
    @Override
    public boolean intersecta(Line r, Vector interseccion){
        double[] t = new double[1];
        double[] s = new double[1];
        if(intersecta(new Vector(r.a),new Vector(r.b),s,t)){
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
        if(intersecta(new Vector(r.a),new Vector(r.b),s,t) && t[0]>=0){
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
                && (t[0]>=0 && t[0]<=1)){
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
        System.out.println ("Line->");
        System.out.println ("Punto a: ");
        a.out ();
        System.out.println ("Punto b: ");
        b.out ();
    }

    
}
