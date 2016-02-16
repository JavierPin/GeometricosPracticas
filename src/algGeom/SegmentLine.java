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
    if (pendiente() == BasicGeom.CERO)return true;
    return false;
}	

/** Indica si el SegmentLine es vertical, en cuyo caso se devuelve true, y en
 caso contrario se devuelve false. */
public boolean esVertical () {
    if (pendiente() == BasicGeom.INFINITO)return true;
    return false;
}

/** Indica si el Point p esta a la izquierda del SegmentLine. Para ello, se
 utiliza el metodo "izquierda" definido en la clase Point. */
public boolean izquierda (Point p) {
    return p.izquierda (a,b);
}

/*
Devuelve el punto con dicho valor de t; 0<=t<=1
@param t: a + t (b-a)
*/

Point GetPoint (double t){
    Vector v = new Vector (b.resta(a));
    return new Point(a.suma(v.prodEsc(t)));
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
    if(b.x-a.x==BasicGeom.CERO)return BasicGeom.CERO;
    if(b.y-a.y==BasicGeom.INFINITO) return BasicGeom.INFINITO;
    return ((b.y-a.y)/(b.x-a.x));
}

public double getC (){
    double c=0;
    if(pendiente()== BasicGeom.INFINITO) return BasicGeom.INFINITO;
    c = (b.y)-(pendiente()*b.x);
    return c;
}

/*
Devuelve true si dos segmentos intersectan de forma propia o impropia. 
@param: l: segmento con el que puede intersectar
nota: dos segmentos intersectan de forma impropia cuando se cruzan (intersección propia)
o con colineales y un extremo pertenece al segmento (usar la función Tema2:clasifica())

*/

public boolean intersecSegImpropia (SegmentLine l){
    Point a = this.a;
    Point b = this.b;
    Point c = l.a;
    Point d = l.b;
    if (a.colineal(c,d)  || b.colineal(c,d) || 
            c.colineal(a,b) || d.colineal(a,b) ){
        return true;
    } else {
        return (a.izquierda(c,d) ^ b.izquierda(c, d)  &&
                c.izquierda(a,b) ^ d.izquierda(a, b));
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