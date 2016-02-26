package algGeom;

public class Vertex extends Point {
    /* Posicion que ocupa dentro del Polygon. */
    protected int posicion;
    /* Polygon al que pertenece. */
    protected Polygon polig;
    
    /** Construye un Vertex por defecto, es decir no asociado a ningun 
     Polygon, con posicion -1 (no pertenece a ningun Polygon) y coordenadas 
     (0,0). */
    public Vertex() {
        x = 0; y=0;
        posicion = 0;
        polig = null;
    }
    
    
    /** Construye un Vertex asociado a un Point, sin estar asociado a ningun 
     Polygon. Aunque no es correcto, ya que un Vertex debe pertenecer a un
     Polygon, es muy util a la hora de inicializar Vertexs, que luego formen
     un Polygon determinado. */    
    public Vertex (Point p) {
        x = p.x; y=p.y;
	posicion = -1;
        polig = null;
    }

    /** Construye un v�rtice dado un punto y su pol�gono asociado*/
    public Vertex (Point p, Polygon pol) {
        x = p.x; y=p.y;
	posicion = -1;
        polig = pol;
    }

    /** Construye un polígono dado un punto y su polígono asociado*/
    public Vertex (Point p, Polygon pol, int pos) {
        x = p.x; y=p.y;
	posicion = pos;
        polig = pol;
    }
    

    /** Construye un Vertex similar al anterior, con los valores xx e yy que se
     corresponden con las coordenadas X e Y del Point p asociado al Vertex. */
    public Vertex (double xx,double yy, Polygon pol) {
        x = xx; y = yy;
	posicion = -1;
        polig = pol;
    }
    
    /** Lee el valor de la x */
    @Override public double getX () {
        return x;
    }
    
     /** Lee el valor de la y */
    @Override public double getY () {
        return y;
    }

        
    /** Lee la posicion asociada al Vertex actual. */
    public int leePosicion () {
        return posicion;
    }
    
    /** Lee el Polygon al que esta asociado el Vertex. */
    public Polygon leePolygon () {
        return polig;
    }
    
    /** Modifica las coordenadas del Point asociadas al Vertex actual con los
     valores de las coordenadas del Point p. */
    public void modificaPoint (Point p) {
        x = p.x; y = p.y;
    }
    
    /** Modifica la posicion del Vertex dentro del Polygon. Esta operacion
     se ha definido como protegida porque es peligroso que el usuario pueda
     modificar la posicion del Vertex. */
    protected void modificaPosicion (int pos) {
        posicion = pos;
    }
    
    /** Modifica el Polygon al que pertenece el Vertex v. De igual modo que el 
     metodo anterior, tambien se ha declarado como un metodo protegido. */
    protected void modificaPolygon (Polygon pl) {
        polig = pl;
    }

    /** Devuelve un punto con las coordenadas del vértice */

    public Point getPoint (){
        return new Point (x,y);
    }

    
    /**
     * Determina si el vértice es o no convexo
     * @return true si es convexo, false en otro caso
     */
    boolean convexo (){
        Vertex siguiente = new Vertex (siguiente());
        Vertex anterior = new Vertex (anterior());      
        return (siguiente.izquierda(anterior, this));
    }

    /**
     * Determina si el vértice es o no cóncavo
     * @return true si es cóncavo, false en otro caso
     */
    
    boolean concavo (){
	Vertex siguiente = new Vertex (siguiente());
        Vertex anterior = new Vertex (anterior());      
        return (siguiente.derecha(anterior, this));
    }
    

    /** 
     * @return  Devuelve el siguiente vértice en el polígono en sentido antihorario
     * */
    public Vertex siguiente (){
        
        return polig.lee((posicion+1)%(polig.numeroVertices()));
    }

    /** @return Devuelve el siguiente vértice en el polígono en sentido horario
     * 
     * */
    public Vertex anterior (){
        
        return polig.lee((posicion-1+polig.numeroVertices())%(polig.numeroVertices()));
    }


    /** @return Devuelve la arista con origen en el vértice en sentido antihorario*/
    public SegmentLine ejeSig (){
        return new SegmentLine(polig.lee(posicion),polig.lee(posicion+1));
    }

    /** @return Devuelve la arista con origen en el vértice en sentido horario*/
    public SegmentLine ejeAnt (){
        return new SegmentLine(polig.lee(posicion),polig.lee(posicion-1));
    }

    

    /** Muestra por pantalla la informacion del Vertex actual. */
    @Override public void out () {
        System.out.print ("Coordenada x: ");
        System.out.println (x);
        System.out.print ("Coordenada y: ");
        System.out.println (y);

        System.out.print ("Posición: ");
        System.out.println (posicion);
    }



    
}
