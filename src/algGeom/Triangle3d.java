package algGeom;


//import javax.vecmath.*;
//import org.apache.commons.math3.geometry.euclidean.threed.Line;
//import org.apache.commons.math3.geometry.euclidean.threed.Plane;
//import org.apache.commons.math3.geometry.euclidean.threed.Vect3d;
//import org.j3d.geom.IntersectionUtils;

enum posicionPunto { POSITIV0, NEGATIV0, ENCIMA };
enum posicionTrianguloRecta { PARALELO, COLINEAL, INTERSECTA, NO_INTERSECTA, NO_PARALELO};
public class Triangle3d {


    /** un triangulo viene definido por tres puntos en el espacio*/
    protected Vect3d a,b,c;

    /** Constructor por defecto a valor (0,0) */
    public Triangle3d() {
        a = new Vect3d ();
        b = new Vect3d ();
        c = new Vect3d ();
    }

    /** Constructor a partir de coordenadas de los tres puntos*/
    public Triangle3d(double ax, double ay, double az,
                       double bx, double by, double bz,
                       double cx, double cy, double cz) {
        a = new Vect3d (ax,ay,az);
        b = new Vect3d (bx,by,bz);
        c = new Vect3d (cx,cy,cz);
    }

    /** Constructor copia*/
    public Triangle3d(Triangle3d t) {
        a = new Vect3d (t.a);
        b = new Vect3d (t.b);
        c = new Vect3d (t.c);
    }

    /** Constructor a partir de tres Vect3d de javax*/
    public Triangle3d (Vect3d va, Vect3d vb, Vect3d vc){
        a = new Vect3d (va);
        b = new Vect3d (vb);
        c = new Vect3d (vc);
    }

    /**modifica los valores de los vertices de los triangulos */
    public void modifica (Vect3d va, Vect3d vb, Vect3d vc){
        a = va;
        b = vb;
        c = vc;
    }


    /**Obtiene el Vect3d de a */
    public Vect3d getA() {
        return a;
    }

    /**Obtiene el Punto b */
    public Vect3d getB() {
        return b;
    }

    /**Obtiene el Punto c */
    public Vect3d getC() {
        return c;
    }
    
    /** Obtiene el punto i */
    public Vect3d getPoint (int i){
    	return (i==0 ? a : (i==1? b : c));
    }
    
    public Vect3d[] getPoints(){
        Vect3d[] vt = {a,b,c};
        return vt;
    }


     /**Devuelve una copia del objeto Punto */
     public Triangle3d copia(){
         return new Triangle3d(a,b,c);
     }

     /**Modifica el valor de a*/
     public void modificaA(Vect3d pa){
         a = pa;
     }

     /**Modifica el valor de b*/
     public void modificaB(Vect3d pb){
         b = pb;
     }

     /**Modifica el valor de c*/
     public void modificaC(Vect3d pc){
         c = pc;
     }

    /** devuelve la normal al triángulo */

    public Vect3d Normal (){
        Vect3d v1 = new Vect3d (b.resta (a));
        Vect3d v2 = new Vect3d (c.resta (a));
        Vect3d n = new Vect3d (v1.XProduct(v2));
        double longi = n.modulo();

        return (n.prodEscalar(1.0/longi));
    }

    /** determina la posición del punto con respecto al triángulo,
     puede estar en el lado positivo, en el negativo o sobre su superficie*
     - si al girar abc el punto p está en la dirección de avance del tornillo, entonces es negativo
     - si está en la posición contraria, entonces es positivo */
    
    public posicionPunto clasifica (Vect3d p){
        
        Vect3d v = new Vect3d (p.resta(a));
        double len = v.modulo();
        if (BasicGeom.iguales (len, 0.0)){
            return posicionPunto.ENCIMA;
        }
        v = v.prodEscalar(1.0/len);
        double d = v.dot(this.Normal());
        if (d > BasicGeom.CERO)
            return posicionPunto.POSITIV0;
        else if (d < -BasicGeom.CERO)
                return posicionPunto.NEGATIV0;
        else return posicionPunto.ENCIMA;
    }


    /** Determina la caja envolvente de un triángulo */

    public AABB getAABB () {
        Vect3d min = new Vect3d (BasicGeom.min3(a.x, b.x, c.x),
                                 BasicGeom.min3(a.y, b.y, c.y),
                                 BasicGeom.min3(a.z, b.z, c.z));
        Vect3d max = new Vect3d (BasicGeom.max3(a.x, b.x, c.x),
                                 BasicGeom.max3(a.y, b.y, c.y),
                                 BasicGeom.max3(a.z, b.z, c.z));
        return (new AABB(min,max));

    }

    /** Compara los triángulos por la Z */

    public int Compara_Z (Triangle3d t){
        if (this.getAABB().getMax().getZ() > t.getAABB().getMax().getZ()){
            return -1;
        } else if (this.getAABB().getMax().getZ() > t.getAABB().getMax().getZ())
            return 1;
        return 0;
    }


     /** determina si dos triángulos solapan en la coordenada X*/
     public boolean solapa_X (Triangle3d t){
        AABB c1 = this.getAABB();
        AABB c2 = t.getAABB();
        return ((c1.getMin().x <= c2.getMin().x) && (c2.getMin().x <= c1.getMax().x) ||
                (c2.getMin().x <= c1.getMin().x) && (c1.getMin().x <= c2.getMax().x));
    }


     /** determina si dos triángulos solapan en la coordenada Y*/
    public boolean solapa_Y (Triangle3d t){
        AABB c1 = this.getAABB();
        AABB c2 = t.getAABB();
        return ((c1.getMin().y <= c2.getMin().y) && (c2.getMin().y <= c1.getMax().y) ||
                (c2.getMin().y <= c1.getMin().y) && (c1.getMin().y <= c2.getMax().y));
    }


    /** determina si dos triángulos solapan en la coordenada Z*/
    public boolean solapa_Z (Triangle3d t){
        AABB c1 = this.getAABB();
        AABB c2 = t.getAABB();
        return ((c1.getMin().z <= c2.getMin().z) && (c2.getMin().z <= c1.getMax().z) ||
                (c2.getMin().z <= c1.getMin().z) && (c1.getMin().z <= c2.getMax().z));
    }

    /** devuelve el punto de interseccion eje con el plano del triangulo 3d , pag 100*/

    public posicionTrianguloRecta intersecta (Edge3d e, DoubleClass t){
        Vect3d aa = new Vect3d (e.orig);
        Vect3d bb = new Vect3d (e.dest);
        Vect3d cc = new Vect3d (a); //algún punto del plano del triángulo
        Vect3d n = new Vect3d (this.Normal());
        double denom = n.dot(bb.resta(aa));
        if (BasicGeom.iguales(denom, BasicGeom.CERO)){
            posicionPunto clasific = this.clasifica(e.orig);
            if (clasific != posicionPunto.ENCIMA)
                return posicionTrianguloRecta.PARALELO;
            else
                return posicionTrianguloRecta.COLINEAL;
        }
        double num = n.dot(aa.resta(cc));
        t.val = (-1)*num / denom;
        return posicionTrianguloRecta.NO_PARALELO;
    }

    /** obtiene el poligono tras la proyeccion del triangulo en el plano xy*/
    public Polygon getProyeccion_XY (){
        Polygon result = new Polygon (0);
        Point pa = new Point (a.x,a.y);
        Point pb = new Point (b.x,b.y);
        Point pc = new Point (c.x,c.y);

        result.anade(new Vertex (pa,result));
        result.anade(new Vertex (pb,result));
        result.anade(new Vertex (pc,result));

        // si no están en sentido antihorario intercambio las posiciones 1 y 2 del triangulo
        if (! pa.izquierda(pb,pc)){
            result.modifica(new Vertex(pc,result), 1);
            result.modifica(new Vertex(pb,result), 2);
        }
        return result;
    }

    /** determina si la proyección en el plano XY de dos triángulos solapa */

    boolean proyeccionSolapa_XY (Triangle3d t){
        //boolean result = false;
        boolean seguir = true;
        Polygon p = this.getProyeccion_XY();
        Polygon q = t.getProyeccion_XY();

        for (int i=0; i<3; i++){
            if (q.puntoEnPoligConvex(p.lee(i))){
                seguir = false;
            }
        }

        if (seguir){
            for (int i=0; i<3; i++){
                if (p.puntoEnPoligConvex(q.lee(i))){
                    seguir = false;
                }
            }
        }
        if (seguir){ // seguir pagina 152,
            for (int i=0; i<3 && seguir; i++){
                
                DoubleClass val = new DoubleClass (0); // esto lo hago para que se pas
                SegmentLine sl = new SegmentLine (p.getArista(i));
                //sl.out();
                for (int j=0; j<3 && seguir; j++){
                    SegmentLine sj = new SegmentLine (q.getArista(j));

                    if (sl.intersecta(sj) == posicionTrianguloRecta.INTERSECTA){
                        
                        seguir = false;
                        
                    }
                }
            }
        }
        return (!seguir);

        
    }


    /** true determina que this puede ocultar al triangulo t
     * 1.- solape en x?
     * 2.- solape en y?
     * 3.- está this enteramente detras o en el plano de t ?
     * 4.- está t enteramente en frente de this o en su mismo plano?
     * 5-. no se solapan las proyecciones de this y t?
     */

    public boolean puedeOcluir (Triangle3d t){ // pag 151

        // caso 1
        if (!this.solapa_X(t)) 
            return false;
        
        // caso 2
        if (!this.solapa_Y(t))
            return false;
        
        // caso 3
        boolean clasifica = true;
        if (t.clasifica(a) == posicionPunto.NEGATIV0){
            clasifica = false;
        }
        if (t.clasifica(b) == posicionPunto.NEGATIV0){
            clasifica = false;
        }
        if (t.clasifica(a) == posicionPunto.NEGATIV0){
            clasifica = false;
        }
        if (clasifica) return false;
        
        // caso 4
        clasifica = true; 
        if (this.clasifica(t.a) == posicionPunto.POSITIV0){
            clasifica = false;
        }
        if (t.clasifica(b) == posicionPunto.POSITIV0){
            clasifica = false;
        }
        if (t.clasifica(a) == posicionPunto.POSITIV0){
            clasifica = false;
        }
        if (clasifica) return false;
        
        //caso 5
        if (!this.proyeccionSolapa_XY(t))
            return false;
        
        return true;
           
        
    }


    /** El plano contenido en el triangulo p es el que parte al triángulo this en
     * dos o tres piezas
     * @return número de piezas en las que se divide el triangulo this
     * pag 153
     */
    int partirTrianguloPorPlano (Triangle3d p,
            Triangle3d q1, Triangle3d q2, Triangle3d q3){

        Vect3d crossPoints[] = new Vect3d [2];
        Vect3d triangulo[] = new Vect3d[3];
        posicionPunto cl[] = new posicionPunto [3];
        int idenEjes[] = new int [2];
        int numPos = 0;
        DoubleClass t = new DoubleClass(0);

        triangulo[0] = a;
        triangulo[1] = b;
        triangulo[2] = c;

        for (int i=0; i<3; i++){
            cl[i] = p.clasifica(triangulo[i]);
        }

        for (int i=0; i<3; i++){
            if (((cl[i]==posicionPunto.POSITIV0) && (cl[(i+1)%3]==posicionPunto.NEGATIV0)) ||
                ((cl[i]==posicionPunto.NEGATIV0) && (cl[(i+1)%3]==posicionPunto.POSITIV0))){
            	Edge3d e = new Edge3d (triangulo[i], triangulo[(i+1%3)]);
                p.intersecta(e, t);
                crossPoints[numPos]= e.getPoint(t.val);
                idenEjes[numPos++] = i;
            }
        }

        if (numPos == 0)
            return 1;

        Vect3d aa = triangulo[idenEjes[0]];
        Vect3d bb = triangulo[(idenEjes[0]+1)%3];
        Vect3d cc = triangulo[(idenEjes[0]+2)%3];

        if (numPos == 1){
            Vect3d dd = crossPoints[0];
            q1.modifica(dd,bb,cc);
            q2.modifica(aa,dd,cc);
        } else {
            Vect3d dd = crossPoints [0];
            Vect3d ee = crossPoints [1];
            if (idenEjes[1]==(idenEjes[0]+1)%3){
                q1.modifica(dd, bb, ee);
                q2.modifica(aa,dd,ee);
                q3.modifica(aa,ee,cc);
            } else {
                q1.modifica(aa,dd,ee);
                q2.modifica(bb,ee,dd);
                q3.modifica(cc,ee,bb);
            }
        }
        return (numPos +1 );
    }
     
    
    /**
     * 
     * @param hv determina el plano 0-1 > plano XY
     * la salida no puede ser degenerada, siempre es un triángulo
     */
    //función que debe ser comparada 
    public Polygon project (int h , int v){
    	Vect3d a;
    	Point [] pts = new Point[3];
    	Vect3d [] pts3 = this.getPoints();
    	for (int i = 0; i<3; i++){
    		a = pts3[i];
    		double[] aa = a.getVert();
    		pts[i] = new Point (aa[h],aa[v]);
    	}
    	//insertamos los dos primeros vertices en el polígono
    	Polygon pp = new Polygon();
    	for (int i=0; i<2; i++){
    		pp.anade(pts[i]);
    	}
    	//insertar el tercer vertice proyectado en el polígono
    	//if (pts[2].clasifica(pts[0], pts[1])==clasificaPuntoEje.IZDA)
    		pp.anade(pts[2]);
    	return pp;
    }
    
    
    /**
     * 
     * @param e: eje o recta con la que se cuestiona el corte 
     * @param t el valor donde se produce el corte en la ecuación a + t (b-a)
     * @return posicionTrianguloRecta = PARALELO, COLINEAL, INTERSECTA, NO_INTERSECTA
     */
    public posicionTrianguloRecta intersectaRecta (Edge3d e, DoubleClass t){
    	Vect3d q;
    	posicionTrianguloRecta aclass = this.intersecta(e, t);
    	if (aclass == posicionTrianguloRecta.PARALELO)
    		return posicionTrianguloRecta.PARALELO;
    	if (aclass == posicionTrianguloRecta.COLINEAL)
    		return posicionTrianguloRecta.COLINEAL;
    	q = e.getPoint(t.get());
    	int h,v;
    	if (this.Normal().dot(new Vect3d(0,0,1)) != 0.0) {
    		h = 0;
    		v = 1;
    	} else if (this.Normal().dot(new Vect3d(1,0,0)) != 0.0) {
    		h = 1;
    		v = 2;
    	} else {
    		h = 2;
    		v = 0;
    	}
    	Polygon pp = this.project(h, v);
    	double [] puntos = q.getVert(); 
    	Point qp = new Point (puntos[h],puntos[v]);
    	boolean answer = pp.puntoEnPoligConvex(qp);
    	if (answer) return posicionTrianguloRecta.INTERSECTA;
    	return posicionTrianguloRecta.NO_INTERSECTA;
   }
    
    
    
    
      /**Muestra un punto 3d en pantalla*/
     public void out (){
         System.out.println("Triangle3d: ("+ a +"-"+ b + "-"+ c +")");
     }



}