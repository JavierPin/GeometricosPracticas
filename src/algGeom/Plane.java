package algGeom;

public class Plane {

   Vect3d a,b,c; //tres puntos cualquiera del plano  

   /**
    * 
    * @param p en pi =  p+u * lambda + v * mu -> r en los puntos (R,S,T) 
    * @param u en pi =  p+u * lambda + v * mu -> d en los puntos (R,S,T)
    * @param v en pi =  p+u * lambda + v * mu -> t en los puntos (R,S,T)
    * @param sonPuntos =     false entonces los parámetros son p+u * lambda + v * mu sino son los puntos (R,S,T)
    */
    public Plane (Vect3d p, Vect3d u, Vect3d v, boolean sonPuntos) {
            if (!sonPuntos) { // son vectores de la forma: pi =  p+u * lambda + v * mu 
                    a = p;
                    b = u.suma(a);
                    c = v.suma(a);
            } else { // son 3 puntos del plano cualesquiera 
                    a = p;
                    b = u;
                    c = v;
            }
    }
    
    public Plane PlaneInfinite(){
        
        //Vect3d extend = new Vect3d(BasicGeom.INFINITO,BasicGeom.INFINITO,BasicGeom.INFINITO);
        a = a.prodEscalar(BasicGeom.INFINITO);
        b = b.prodEscalar(BasicGeom.INFINITO);;
        c = c.prodEscalar(BasicGeom.INFINITO);;
        
        return this;
        
    }
    
    /**
     * 
     * @return el valor de A en  AX+BY+CZ+D = 0;
* 
     */
    public double getA(){

            return (BasicGeom.determinante2x2(b.getY()-a.getY(), b.getZ()-a.getZ(), c.getY()-a.getY(), c.getZ()-a.getZ()));	
    }	
    /**
     * 
     * @return el valor de B en  AX+BY+CZ+D = 0;
* 
     */
    public double getB(){

            return (BasicGeom.determinante2x2(b.getZ()-a.getZ(), b.getX()-a.getX(), c.getZ()-a.getZ(), c.getX()-a.getX()));	
    }	

    /**
     * 
     * @return el valor de C en  AX+BY+CZ+D = 0;
* 
     */
    public double getC(){

            return (BasicGeom.determinante2x2(b.getX()-a.getX(), b.getY()-a.getY(), c.getX()-a.getX(), c.getY()-a.getY()));
    }	

    /**
     * 
     * @return el valor de D en  AX+BY+CZ+D = 0;
* 
     */
    public double getD(){

            return (-1)*(getA()*a.getX()+getB()*a.getY()+getC()*a.getZ());
    }

    /**
     * 
     * @return el vector normal formado por (A,B,C) de la ecuación Ax+By+Cz+D = 0
     */
    public Vect3d getNormal (){
            double A = getA();
            double B = getB();
            double C = getC();
            return new Vect3d (A,B,C);
    }

    public double distancia (Vect3d p){
        double nume, deno;
        
        nume = Math.abs(
                (getA() * p.x) + (getB() * p.y) + (getC() * p.z) + getD()
        );
        deno = Math.sqrt(Math.pow(getA(), 2) + Math.pow(getB(), 2) + Math.pow(getC(), 2));
        
        if (deno <= BasicGeom.CERO && deno>=(-BasicGeom.CERO)) {
            return BasicGeom.INFINITO;
        } else {
            return nume / deno;
        }        
    }
    
    public boolean coplanar (Vect3d p){
        return(distancia(p)<=BasicGeom.CERO);
    }
    
    public Vect3d puntoPlano (Vect3d p){
        Vect3d Q,n,q;
        double l;
        
        n= new Vect3d(a.x , b.y , c.z);
        if (n.modulo()==1 || n.modulo()==(-1)){
            l= (-(n.dot(p) + getD())) / n.dot(n);
        }else{
            l= -(n.dot(p)+getD());
        }
        
        q = new Vect3d(p.suma(n.prodEscalar(l)));
        //Estoy usando expresiones matematicas. No voy a comprimir el codigo a fin de poder comprenderlo en el futuro
        return q;
    }
    
    

    /** Muestra en pantalla los valores los valores de Plano */
    public void out () {
        System.out.print ("Plano->a: ");
        System.out.println (a);
        System.out.print ("Plano->b: ");
        System.out.println (b);
        System.out.print ("Plano->c: ");
        System.out.println (c);
    }


}
	

