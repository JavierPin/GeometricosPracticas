package algGeom;

/** Representa un Vector 2D. */

public class Vector extends Point{

    /** Crea un nuevo Point con coordenadas por defecto (iguales a 0) */
    public Vector () {
        super (0,0);
    }

    /** Crea un nuevo Vector con coordenadas X e Y igual a xx e yy,
     respectivamente.
     * @param xx = x
     * @param yy = y*/
    public Vector (double xx, double yy) {
        super(xx,yy);
    }

    /** Crea un nuevo Vector de Point p. */
    public Vector (Point p) {
        super (p.x,p.y);
    }
    
    /** Crea un nuevo Vector de Vector. */
    public Vector (Vector v) {
        super (v.x,v.y);
    }

    /** get x*/
    public double getX(){
        return x;
    }

    /** get y*/
    public double getY(){
        return y;
    }

    /** suma dos puntos (vectores) result = this + b*/
    public Vector suma (Vector b){
        return new Vector(x+b.x,y+b.y);
    }
    
    /** resta dos puntos (vectores) result = this - b*/
    public Vector resta (Vector b){
        return new Vector(x-b.x,y-b.y);
    }

    /** producto escalar: this · b* en base ortogonales
     v ( x1 , y1 ) u ( x2 , y2 ) ---> v * u = x1 * x2 + y1 * y2 */
    public double dot (Vector b){
        return ((x*b.x) + (y*b.y));
    }
    
    /** producto por un escalar */
    public Vector prodEsc (double t){
        return new Vector(x*t,y*t);
    }
    public Vector prodEsc (Vector t){
        return new Vector(x*t.x,y*t.y);
    }
    

    /** Obtiene un Vector copia del actual. */
    public  Vector copia () {
        return new Vector (x,y);
    }

    /** El vector actual pasa a ser una copia del vector v. */
    public void copia (Vector v) {
        x = v.x;
        y = v.y;
    }


    /** Devuelve el Vector para ser leido. */
    public Vector get () {
        return this;
    }
    
    /** Devuelve el Punto para ser leido. */
    public Point getPunto () {
        return this;
    }
    
    /** modulo, distancia al origen */
    public double modulo (){
        return Math.sqrt(x*x+y*y);
    }
    public Vector perpendicular (){
        return new Vector(-y,x);
    }
}
