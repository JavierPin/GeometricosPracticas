package algGeom;

/** Representa un Point 2D. */

enum clasificaPuntoEje {IZDA, DECHA, DELANTE, DETRAS, ENMEDIO, ORIGEN, DESTINO};


public class Point {

    /** Coordenada X del Point. */
    protected double x;
    /** Coordenada Y del Point. */
    protected double y;

    /** Crea un nuevo Point con coordenadas por defecto (iguales a 0) */
    public Point () {
        x = 0;
        y = 0;
    }

    /** Crea un nuevo Point con coordenadas X e Y igual a xx e yy,
     respectivamente. */
    public Point (double xx, double yy) {
        x = xx;
        y = yy;
    }
    
    /** Crea un nuevo Point copiando el Point p. */
    public Point (Point p) {
        x = p.x;
        y = p.y;
    }

    /** get x*/
    public double getX(){
        return x;
    }

    /** get y*/
    public double getY(){
        return y;
    }
    
    /*
    Ejercicio Constructor a partir de un �ngulo alpha (radianes) y el m�dulo del vector rp
    */
    public Point (double alpha, double rp, boolean polar){
        x = rp*Math.cos(alpha);
        y = rp*Math.sin(alpha);
    }
    
    /** constructor a partir de coordenadas polares; considerar el cuadrante**/ 
    //public Point (float alpha, float rp){
    //  x = alpha;
    //  y = rp; 
    //}
    
    
    /** devolver el ángulo **/
    public double getAlpha (){
        if(y>0){
            return Math.atan2(y, x);
        }
        return Math.atan2(y, x)+(2*Math.PI); //Solucion a que este en el cuadrante 3 o 4

    }

    public double getModule () {
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }

    /*dice si dos puntos son prácticamente iguales */
    public boolean iguales (Point pt){
        return (BasicGeom.iguales(x, pt.x) && BasicGeom.iguales(y, pt.y));

    }

    /** Obtiene la distancia del Point actual al Point p, calculada como:
     distancia = raiz ((p.x - x)2 + (p.y - y)2). */
    public double distancia (Point p) {
        return Math.sqrt(((p.x-x)*2)+(p.y-y)*2) ;
	
    }

    /** Obtiene el doble del �rea que forman el tri�ngulo definido por el Point
     actual y los Points a y b, en el orden (*this, a, b). */
    public double areaTriangulo2 (Point a, Point b) {
        return x*a.y-y*a.x+a.x*b.y-a.y*b.x+b.x*y-b.y*x;
    }
    
    
    /** Compara el Point actual con el Point p, devolviendo true si son
     distintos (si alguna de las coordenadas no coinciden), y false en caso contrario. */
    public boolean distinto (Point p) {
        return (Math.abs(x-p.x)>BasicGeom.CERO || Math.abs(y-p.y)>BasicGeom.CERO);
    }

    /** Obtiene un Point copia del actual. */
    public Point copia () {
        return new Point (x,y);
    }

    /** El Point actual pasa a ser una copia del Point p. */
    public void copia (Point p) {
        x = p.x;
        y = p.y;
    }


    /** Devuelve el Point para ser leido. */
    public Point get () {
        return this;
    }

   
    /** Establece las coordenadas X e Y del Point con los valores de xx e yy,
     respectivamente. */
    public void asigna (double xx, double yy) {
        x = xx;
        y = yy;
    }

    /** Establece la coordenada X del Point. */
    public void asignax (double xx) {
        x = xx;
    }

    /** Establece la coordenada Y del Point. */
    public void asignay (double yy) {
        y = yy;
    }
    
    /** Operacion Resta de punto **/
    
    public Point resta(Point a){
        return (new Point(this.x-a.x,this.y-a.y));
    }
    public Point suma(Point a){
        return (new Point(this.x+a.x,this.y+a.y));
    }
    public double modulo(){
        return Math.sqrt((x*x)+(y*y));
    }
    
    /** función que clasifica la posición de this con respecto a p1, p2**/
    public clasificaPuntoEje clasifica (Point p0, Point p1){
        if(izquierda(p0,p1)) return clasificaPuntoEje.IZDA;
        if(derecha(p0,p1)) return clasificaPuntoEje.DECHA;
        if(detras(p0,p1)) return clasificaPuntoEje.DETRAS;
        if(delante(p0,p1)) return clasificaPuntoEje.DELANTE;
        if(iguales(p0)) return clasificaPuntoEje.ORIGEN;
        if(iguales(p1)) return clasificaPuntoEje.DESTINO;
        return clasificaPuntoEje.ENMEDIO;

    }

    /** Indica si el Point esta a la izquierda del segmento definido por los
     Points ab. */
    public boolean izquierda (Point p0, Point p1) {
        Point p2 = this;
        Point a = p1.resta(p0);
        Point b = p2.resta(p0);
        double sa = a.x * b.y - b.x * a.y;
        if(sa>BasicGeom.CERO) return true; else return false;
    }

    /** Indica si el Point esta a la derecha del segmento definido por los
     Points ab. */
    public boolean derecha (Point p0, Point p1) {
        Point p2 = this;
        Point a = p1.resta(p0);
        Point b = p2.resta(p0);
        double sa = a.x * b.y - b.x * a.y;
        if(sa<BasicGeom.CERO) return true; else return false;
    }

    /** Indica si los tres Points son colineales. */
    public boolean colineal (Point p0, Point p1) {
    return (!derecha(p0,p1)&&!izquierda(p0,p1));
    }

    /** Indica si el Point esta a la izquierda o es colineal al segmento
     definido por los Points ab. */
    public boolean izquierdaSobre (Point a, Point b) {
        return (colineal(a,b)&&(izquierda(a,b)));
    }

    /** Indica si el Point esta a la derecha o es colineal al segmento definido
     por los Points ab. */
    public boolean derechaSobre (Point a, Point b) {
        return (colineal(a,b)&&(derecha(a,b)));
    }
    
    
    /* Dice si está entre o en medio los puntos a y b. */
    public boolean entre (Point a, Point b){
        if(izquierda(a,b)||derecha(a,b)|| colineal(a,b)||izquierdaSobre(a,b) ||
                derechaSobre(a,b)||delante(a,b)||detras(a,b)){
            return false;
        }
        return true;
    }

    
    /** Indica si el Point esta delante  al segmento definido
     por los Points ab. */
    public boolean delante (Point a, Point b) {
        return (a.modulo()<b.modulo());
    }
    
    /** Indica si el Point esta detras  al segmento definido
     por los Points ab.
     **/
    public boolean detras (Point a, Point b) {
        return ((a.x*b.x<BasicGeom.CERO)||(a.y*b.y<BasicGeom.CERO));
    }

    /** Indica la pendiente que forma con el Point p.
     * @param p
     * @return  */
    public double pendiente (Point p) {
        if (Math.abs(x-p.x)<0) return BasicGeom.INFINITO;

	return (p.y - y)/(p.x - x);
    }


   
    
    /** Muestra en pantalla los valores de las coordenadas del Point. */
    public void out () {
        System.out.print ("Coordenada x: ");
        System.out.println (x);
        System.out.print ("Coordenada y: ");
        System.out.println (y);
    }

}
