package algGeom;


/** Representa un SegmentLine definido por dos Points. */
public class SegmentLine {

protected Point a;
protected Point b;

/** Contruye un SegmentLine con valores por defecto (igual a (0,0). */
public SegmentLine() {
    b = new Point ();
    a = new Point ();
}

/** Construye un SegmentLine a partir de dos Points */
public SegmentLine (Point ii,Point ss) {
    a=ii; b=ss;
}

/** Construye un SegmentLine copia del SegmentLine sg. */
public SegmentLine (SegmentLine sg) {
    a=sg.a; b=sg.b;
}

/** Construye un SegmentLine a partir de las cuatro coordenadas */
public SegmentLine (double ax, double ay, double bx, double by) {
    a = new Point (ax,ay);
    b = new Point (bx,by);
}

/** Obtiene el doble area formada por el triangulo compuesto por el 
 SegmentLine actual y la union de los extremos de dicho SegmentLine con el Point p. */
public double areaTriangulo2 (Point p) {
    return p.areaTriangulo2 (a,b);
}

/** Devuelve la longitud del SegmentLine, utilizando el metodo distancia de la
 clase Point. */
public double longitud () {
    return a.distancia (b);
}

/** Compara el SegmentLine actual con el SegmentLine sg, devolviendo true si son
 iguales, es decir coinciden y false en
 caso contrario. */
public boolean igual (SegmentLine sg) {
    return (a.iguales (sg.a) && b.iguales (sg.b)) ||  (a.iguales (sg.b) && b.iguales (sg.a));
}

/** Compara el SegmentLine actual con el SegmentLine sg, devolviendo true si son
 distintos, es decir el Point a o b es distinto, y false en
 caso contrario. */
public boolean distinto (SegmentLine sg) {
    return  !(a.iguales (sg.a) && b.iguales (sg.b)) ||  (a.iguales (sg.b) && b.iguales (sg.a));
}

/** Obtiene una copia del SegmentLine actual. */
public SegmentLine copia () {
    return new SegmentLine (a,b);
}

/** Obtiene una copia en el SegmentLine actual del SegmentLine sg. */
public void copia (SegmentLine sg) {
    a.copia (sg.a);
    b.copia (sg.b);
}

/** Obtiene el SegmentLine actual. */
public SegmentLine lee () {
    return this;
}

/** Obtiene el Point a. */
public Point leea () {
    return a;
}

/** Obtiene la coordenada X del Point a. */
public double leeax () {
    return a.getX ();
}

/** Obtiene la coordenada Y del Point a. */
public double leeay () {
    return a.getY ();
}

/** Obtiene el Point b. */
public Point leeb () {
    return b;
}

/** Obtiene la coordenada X del Point b. */
public double leebx () {
    return b.getX ();
}

/** Obtiene la coordenada Y del Point b. */
public double leeby () {
    return b.getY ();
}

/** Establece el Point a con las coordenadas del Point p. */
public void asignai (Point p) {
    a.copia (p);
}

/** Establece el Point a con los valores xx e yy para las coordenadas
 X e Y, respectivamente. */
public void asignaa (double xx,double yy) {
    a.asigna (xx,yy);
}

/** Establece la coordenada X del Point a. */
public void asignaax (double xx) {
    a.asignax (xx);
}

/** Establece la coordenada Y del Point a. */
public void asignaay (double yy) {
    a.asignay (yy);
}

/** Establece el Point b con las coordenadas del Point p. */
public void asignab (Point p) {
    b.copia (p);
}

/** Establece el Point a con los valores xx e yy para las coordenadas
 X e Y, respectivamente. */
public void asignab (double xx,double yy) {
    b.asigna (xx,yy);
}

/** Establece la coordenada X del Point a. */
public void asignabx (double xx) {
    b.asignax (xx);
}
    
/** Establece la coordenada Y del Point a. */
public void asignaby (double yy) {
    b.asignay (yy);
}

/** Indica si el SegmentLine es horizontal, en cuyo caso se devuelve true, y en
 caso contrario se devuelve false. */
public boolean esHorizontal () {
    if (Math.abs(a.x - b.x)>BasicGeom.CERO) return false;
    return true;
}	

/** Indica si el SegmentLine es vertical, en cuyo caso se devuelve true, y en
 caso contrario se devuelve false. */
public boolean esVertical () {
    if (Math.abs(a.y - b.y)>BasicGeom.CERO) return false;
    return true;
}

/** Indica si el Point p esta a la izquierda del SegmentLine. Para ello, se
 utiliza el metodo "izquierda" definido en la clase Point. */
public boolean izquierda (Point p) {
    return p.izquierda (a,b);
}

/**
Devuelve el punto con dicho valor de t; 0<=t<=1
@param t: a + t (b-a)
*/
Point GetPoint (double t){
    return new Point(a.x+t*(b.x-a.x), a.y+t*(b.y-a.y));
}

public boolean intersectaSegmento (SegmentLine l){
    //Point a = this.a;
    //Point b = this.b;
    Point c = l.a;
    Point d = l.b;
    if (a.colineal(c,d) || b.colineal(c,d) || c.colineal(a,b) || d.colineal(a,b) ){
        return false;
    } else {
        return (a.izquierda(c,d) ^ b.izquierda(c, d)  &&
        		 c.izquierda(a,b) ^ d.izquierda(a, b));
    }
}

public Point getA (){
    return a;
}

public Point getB (){
    return b;
}

public double pendiente (){
    if ((b.x-a.x) == BasicGeom.CERO) return BasicGeom.CERO;
    if ((b.y-a.y) == BasicGeom.INFINITO) return BasicGeom.INFINITO;
    return (b.y-a.y)/(b.x-a.x);
}

public double getC (){
    if (pendiente() == BasicGeom.INFINITO) return BasicGeom.INFINITO;
    if (pendiente() == BasicGeom.CERO) return BasicGeom.CERO;
    return b.y-(pendiente()*b.x);
}

/**
Devuelve true si dos segmentos intersectan de forma propia o impropia. 
@param: l: segmento con el que puede intersectar
nota: dos segmentos intersectan de forma impropia cuando se cruzan (intersección propia)
o con colineales y un extremo pertenece al segmento (usar la función Tema2:clasifica())

*/
public boolean intersecSegImpropia (SegmentLine l){
   
    Point c = l.a;
    Point d = l.b;
    if (a.colineal(c,d)  || b.colineal(c,d) || c.colineal(a,b) || d.colineal(a,b) ){
        return true;
    } else {
        return (a.izquierda(c,d) ^ b.izquierda(c, d) && c.izquierda(a,b) ^ d.izquierda(a, b));
    }
}

public float distPuntoSegmento(Vector p){
    
    Vector m = new Vector(b.x-a.x,b.y-a.y);
    Vector b = new Vector(a.x,a.y);

    double t0 = ((m.dot(p.resta(b)))/(m.dot(m)));

    if (t0<=BasicGeom.CERO){

        return (float)(p.resta(b)).modulo();
    }
    else if ((BasicGeom.CERO<t0)&&(t0<1)){

        return (float)(p.resta(b.suma(m.prodEsc(t0)))).modulo();
    }
    else{
        
        return (float)(p.resta(b.suma(m))).modulo();
    }
        
}

/*
interseccion dos rectas
*/
protected boolean intersecta(Vector C, Vector D, double[] s, double[] t){
    Vector cd,ac,ab;
    cd= new Vector(D.resta(C));
    ac= new Vector(C.x-a.x,C.y-a.y);
    ab= new Vector(b.x-a.x,b.y-b.x);
    double nume,deno;
   
    deno = (cd.x*ab.y) - (ab.x*cd.y);
    if (deno==0.0f){
        s[0]= 0;
        t[0]= 0;
        return false;
    }else{
        nume = (cd.x*ac.y) - (ac.x*cd.y);
        s[0] = nume/deno;
        nume = (ab.x-ac.y) - (ac.x*ab.y);
        t[0]= nume/deno;
        System.out.println(s+", "+t);
        return true;
    }
}

/*
interseccion segmento recta
*/
public boolean intersecta (Line r, Vector interseccion){
    double[] t = new double[1];
    double[] s = new double[1];
    if(intersecta(new Vector(r.a),new Vector(r.b),s,t)
            && (s[0]>=0 && s[0]<=1)){
        //Calculo el punto de interseccion
        interseccion.asignax(b.resta(a).x*s[0] + a.x);
        interseccion.asignay(b.resta(a).y*s[0] + a.y);
        return true;
    }else{
        return false;
    }
}

/*
interseccion segmento rayo
*/
public boolean intersecta (RayLine r, Vector interseccion){
    double[] t = new double[1];
    double[] s = new double[1];
    if(intersecta(new Vector(r.a),new Vector(r.b),s,t)
            && (s[0]>=0 && s[0]<=1) && t[0]>=0){
        //Calculo el punto de interseccion
        interseccion.asignax(b.resta(a).x*s[0] + a.x);
        interseccion.asignay(b.resta(a).y*s[0] + a.y);
        return true;
    }else{
        return false;
    } 
}

/*
interseccion segmento rayo
*/
public boolean intersecta (SegmentLine r, Vector interseccion){
    double[] t = new double[1];
    double[] s = new double[1];
    if(intersecta(new Vector(r.a),new Vector(r.b),s,t)
            && (s[0]>=0 && s[0]<=1) && (t[0]>=0 && t[0]<=1)){
        //Calculo el punto de interseccion
        interseccion.asignax(b.resta(a).x*s[0] + a.x);
        interseccion.asignay(b.resta(a).y*s[0] + a.y);
        return true;
    }else{
        return false;
    } 
}

/** Muestra en pantalla la informacion del SegmentLine. */
public void out () {
    System.out.println ("Punto a: ");
    a.out ();
    System.out.println ("Punto b: ");
    b.out ();
}



}