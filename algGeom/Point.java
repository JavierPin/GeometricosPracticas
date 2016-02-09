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
    
    
    /** constructor a partir de coordenadas polares; considerar el cuadrante**/ 
    //public Point (float alpha, float rp){
    //  x = alpha;
    //  y = rp; 
    //}
    
    
    /** devolver el ángulo **/
    public double getAlpha (){
        return 0; 
    }

    public double getModule () {
        return 0;
    }

    /*dice si dos puntos son prácticamente iguales */
    public boolean iguales (Point pt){
        return (BasicGeom.iguales(x, pt.x) && BasicGeom.iguales(y, pt.y));

    }

    /** Obtiene la distancia del Point actual al Point p, calculada como:
     distancia = raiz ((p.x - x)2 + (p.y - y)2). */
    public double distancia (Point p) {
        return 1;
    //XXX	
    }

    /** Obtiene el doble del �rea que forman el tri�ngulo definido por el Point
     actual y los Points a y b, en el orden (*this, a, b). */
    public double areaTriangulo2 (Point a, Point b) {
       // XXX
        return 1;
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

    /** función que clasifica la posición de this con respecto a p1, p2**/
    
    public clasificaPuntoEje clasifica (Point p0, Point p1){
        Point p2 = this;
        //XXXX
 
        return clasificaPuntoEje.ENMEDIO;

    }

    /** Indica si el Point esta a la izquierda del segmento definido por los
     Points ab. */
    public boolean izquierda (Point a, Point b) {
        //XXXX
        return true;
    }

    /** Indica si el Point esta a la derecha del segmento definido por los
     Points ab. */
    public boolean derecha (Point a, Point b) {
        //XXX
        return true;
    }

    /** Indica si los tres Points son colineales. */
    public boolean colineal (Point a, Point b) {
        //XXX
        return true;
    }

    /** Indica si el Point esta a la izquierda o es colineal al segmento
     definido por los Points ab. */
    public boolean izquierdaSobre (Point a, Point b) {
        //XXX
        return true;
    }

    /** Indica si el Point esta a la derecha o es colineal al segmento definido
     por los Points ab. */
    public boolean derechaSobre (Point a, Point b) {
        //XXX
        return true;
    }
    
    
    /* Dice si está entre o en medio los puntos a y b. */
    public boolean entre (Point a, Point b){
        //XXXX
        return true;
    }

    
    /** Indica si el Point esta delante  al segmento definido
     por los Points ab. */
    public boolean delante (Point a, Point b) {
        //XXX
        return true;
    }
    
    /** Indica si el Point esta detras  al segmento definido
     por los Points ab.
     **/
    public boolean detras (Point a, Point b) {
        //XXX
        return true;
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
