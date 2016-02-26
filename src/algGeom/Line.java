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
    
    @Override
    public boolean intersecta(Line r, Vector interseccion){
        double s=0,t=0;
        if(intersecta(new Vector(r.a),new Vector(r.b),s,t)){
            //Calculo el punto de interseccion
        interseccion.asignax(b.resta(a).x*s + a.x);
        interseccion.asignay(b.resta(a).y*s + a.y);
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public boolean intersecta (RayLine r, Vector interseccion){
        double s=0,t=0;
        if(intersecta(new Vector(r.a),new Vector(r.b),s,t) && t>=0){
                //Calculo el punto de interseccion
        interseccion.asignax(b.resta(a).x*s + a.x);
        interseccion.asignay(b.resta(a).y*s + a.y);
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public boolean intersecta (SegmentLine r, Vector interseccion){
        double s=0,t=0;
        if(intersecta(new Vector(r.a),new Vector(r.b),s,t)
                && (t>=0 && t<=1)){
                //Calculo el punto de interseccion
        interseccion.asignax(b.resta(a).x*s + a.x);
        interseccion.asignay(b.resta(a).y*s + a.y);
            return true;
        }else{
            return false;
        }        
    } 

    //Esta funcion me coloca la linea en el punto (0,0) //Normaliza
    public Vector toVector(){
        return new Vector((new Point(b.x-a.x,b.y-a.y)));
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
