package algGeom;


public class TriangleTin {


    /** un triangulo viene definido por tres puntos en el espacio*/
    protected Segment3d a1, a2, a3;

    /** Constructor por defecto a valor (0,0) */
    public TriangleTin() {
        a1 = new Segment3d (new Vect3d(0,0,0), new Vect3d(0,0,0));
        a2 = new Segment3d (new Vect3d(0,0,0), new Vect3d(0,0,0));
        a3 = new Segment3d (new Vect3d(0,0,0), new Vect3d(0,0,0));
    }
    
    public TriangleTin(Segment3d aa1, Segment3d aa2, Segment3d aa3){
        a1=aa1;
        a2=aa2;
        a3=aa3;
    }
    
    public TriangleTin(Triangle3d t){

        a1 = new Segment3d(t.a,t.b);
        a2 = new Segment3d(t.b,t.c);
        a3 = new Segment3d(t.c,t.a);

    }
    
    public TriangleTin(Triangle_dt t){
        
        Vect3d a = new Vect3d (t.a.x,t.a.y,t.a.z);
        Vect3d b = new Vect3d (t.b.x,t.b.y,t.b.z);
        Vect3d c = new Vect3d (t.c.x,t.c.y,t.c.z);
        
        a1 = new Segment3d(a,b);
        a2 = new Segment3d(b,c);
        a3 = new Segment3d(c,a);
    }

    /** Constructor a partir de coordenadas de los tres puntos*/
    public TriangleTin(double ax, double ay, double az,
                       double bx, double by, double bz,
                       double cx, double cy, double cz) {
        Vect3d a = new Vect3d (ax,ay,az);
        Vect3d b = new Vect3d (bx,by,bz);
        Vect3d c = new Vect3d (cx,cy,cz);
        
        a1 = new Segment3d(a,b);
        a2 = new Segment3d(b,c);
        a3 = new Segment3d(c,a);
    }

    /** Constructor copia*/
    public TriangleTin(TriangleTin t) {
        a1 = t.a1;
        a2 = t.a2;
        a3 = t.a3;
    }

    /** Constructor a partir de tres Vect3d de javax*/
    public TriangleTin (Vect3d va, Vect3d vb, Vect3d vc){
        a1 = new Segment3d (va,vb);
        a2 = new Segment3d (vb,vc);
        a3 = new Segment3d (vc,va);
    }

    /**modifica los valores de los vertices de los triangulos */
    public void modifica (Vect3d va, Vect3d vb, Vect3d vc){
        a1 = new Segment3d (va,vb);
        a2 = new Segment3d (vb,vc);
        a3 = new Segment3d (vc,va);
    }

    /**Obtiene el Vect3d de a */
    public Segment3d getA1() {
        return a1;
    }

    /**Obtiene el Punto b */
    public Segment3d getA2() {
        return a2;
    }

    /**Obtiene el Punto c */
    public Segment3d getA3() {
        return a3;
    }
    
    public Vect3d getA(){
        
        return a1.orig;
    }
    
    public Vect3d getB(){
        
        return a2.orig;
    }
    
    public Vect3d getC(){
        
        return a3.orig;
    }
    
    /** Obtiene el punto i */
    public Segment3d getSegment (int i){
    	return (i==0 ? a1 : (i==1? a2 : a3));
    }
    
    /** Obtiene un array con las aristas del tri�ngulo a, b y c */
    public Segment3d[] getSegments(){
        Segment3d[] vt = {a1,a2,a3};
        return vt;
    }
    
    public Vect3d[] getPoints(){
        
        Vect3d[] vt = {a1.orig,a2.orig,a3.orig};
        return vt;
    }

     /**Devuelve una copia del objeto Punto */
     public TriangleTin copia(){
         return new TriangleTin(a1,a2,a3);
     }

     /**Modifica el valor de a1*/
     public void modificaA1(Segment3d pa){
         a1 = pa;
     }

     /**Modifica el valor de a2*/
     public void modificaA2(Segment3d pb){
         a2 = pb;
     }

     /**Modifica el valor de c*/
     public void modificaA3(Segment3d pc){
         a3 = pc;
     }

    /** devuelve la normal al tri�ngulo */
    public Vect3d Normal (){
        Vect3d v1 = new Vect3d (a2.orig.resta (a1.orig));
        Vect3d v2 = new Vect3d (a3.orig.resta (a2.orig));
        Vect3d n = new Vect3d (v1.XProduct(v2));
        double longi = n.modulo();

        return (n.prodEscalar(1.0/longi));
    }

    /** determina la posición del punto con respecto al tri�ngulo,
     puede estar en el lado positivo, en el negativo o sobre su superficie*
     - si al girar abc el punto p est� en la dirección de avance del tornillo, entonces es negativo
     - si est� en la posición contraria, entonces es positivo */    
    public posicionPunto clasifica (Vect3d p){
        
        Vect3d v = new Vect3d (p.resta(a1.orig));
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

    /** determina si un punto es coplanar con el plano que contiene al tri�ngulo */
    public boolean coplanar(Vect3d p){
        
        Vect3d u = a2.orig.resta(a1.orig);
        Vect3d v = a3.orig.resta(a1.orig);
        Vect3d w = p.resta(a1.orig);
        
        return u.XProduct(v).dot(w) <= BasicGeom.CERO;
    }
    
    /** Determina la caja envolvente de un tri�ngulo */
    public AABB getAABB () {
        Vect3d min = new Vect3d (BasicGeom.min3(a1.orig.x, a2.orig.x, a3.orig.x),
                                 BasicGeom.min3(a1.orig.y, a2.orig.y, a3.orig.y),
                                 BasicGeom.min3(a1.orig.z, a2.orig.z, a3.orig.z));
        Vect3d max = new Vect3d (BasicGeom.max3(a1.orig.x, a2.orig.x, a3.orig.x),
                                 BasicGeom.max3(a1.orig.y, a2.orig.y, a3.orig.y),
                                 BasicGeom.max3(a1.orig.z, a2.orig.z, a3.orig.z));
        return (new AABB(min,max));

    }

    /** Compara los tri�ngulos por la Z */
    public int Compara_Z (Triangle3d t){
        if (this.getAABB().getMax().getZ() > t.getAABB().getMax().getZ()){
            return -1;
        } else if (this.getAABB().getMax().getZ() > t.getAABB().getMax().getZ())
            return 1;
        return 0;
    }

     /** determina si dos tri�ngulos solapan en la coordenada X*/
     public boolean solapa_X (Triangle3d t){
        AABB c1 = this.getAABB();
        AABB c2 = t.getAABB();
        return ((c1.getMin().x <= c2.getMin().x) && (c2.getMin().x <= c1.getMax().x) ||
                (c2.getMin().x <= c1.getMin().x) && (c1.getMin().x <= c2.getMax().x));
    }

     /** determina si dos tri�ngulos solapan en la coordenada Y*/
    public boolean solapa_Y (Triangle3d t){
        AABB c1 = this.getAABB();
        AABB c2 = t.getAABB();
        return ((c1.getMin().y <= c2.getMin().y) && (c2.getMin().y <= c1.getMax().y) ||
                (c2.getMin().y <= c1.getMin().y) && (c1.getMin().y <= c2.getMax().y));
    }

    /** determina si dos tri�ngulos solapan en la coordenada Z*/
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
        Vect3d cc = new Vect3d (a1.orig); //alg�n punto del plano del tri�ngulo
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
        Point pa = new Point (a1.orig.x,a1.orig.y);
        Point pb = new Point (a2.orig.x,a2.orig.y);
        Point pc = new Point (a3.orig.x,a3.orig.y);

        result.anade(new Vertex (pa,result));
        result.anade(new Vertex (pb,result));
        result.anade(new Vertex (pc,result));

        // si no est�n en sentido antihorario intercambio las posiciones 1 y 2 del triangulo
        if (! pa.izquierda(pb,pc)){
            result.modifica(new Vertex(pc,result), 1);
            result.modifica(new Vertex(pb,result), 2);
        }
        return result;
    }

    /** determina si la proyección en el plano XY de dos tri�ngulos solapa */
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
     * 3.- est� this enteramente detras o en el plano de t ?
     * 4.- est� t enteramente en frente de this o en su mismo plano?
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
        if (t.clasifica(a1.orig) == posicionPunto.NEGATIV0){
            clasifica = false;
        }
        if (t.clasifica(a2.orig) == posicionPunto.NEGATIV0){
            clasifica = false;
        }
        if (t.clasifica(a1.orig) == posicionPunto.NEGATIV0){
            clasifica = false;
        }
        if (clasifica) return false;
        
        // caso 4
        clasifica = true; 
        if (this.clasifica(t.a) == posicionPunto.POSITIV0){
            clasifica = false;
        }
        if (t.clasifica(a2.orig) == posicionPunto.POSITIV0){
            clasifica = false;
        }
        if (t.clasifica(a1.orig) == posicionPunto.POSITIV0){
            clasifica = false;
        }
        if (clasifica) return false;
        
        //caso 5
        if (!this.proyeccionSolapa_XY(t))
            return false;
        
        return true;
           
        
    }

    /** El plano contenido en el triangulo p es el que parte al tri�ngulo this en
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

        triangulo[0] = a1.orig;
        triangulo[1] = a2.orig;
        triangulo[2] = a3.orig;

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
     * la salida no puede ser degenerada, siempre es un tri�ngulo
     */
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
    
    /**
     * Calcula la proyecci�n del este tri�ngulo sobre el plano XY
     * 
     * @return Triangle3d (v1, v2, v3)
     */
    public Triangle3d proyecta_XY(){
        
        Vect3d v1 = new Vect3d(getA());
        v1.setZ(0.0f);
        Vect3d v2 = new Vect3d(getB());
        v2.setZ(0.0f);
        Vect3d v3 = new Vect3d(getC());
        v3.setZ(0.0f);
        
        return new Triangle3d(v1,v2,v3);
        
    }
    
    /**
     * Calcula la proyecci�n del este tri�ngulo sobre el plano XY de su caja envolvente
     * 
     * @return Triangle3d (v1, v2, v3)
     */
    public Triangle3d proyecta_XY(AABB box){
        
        Vect3d v1 = new Vect3d(getA());
        v1.setZ(box.min.z);
        Vect3d v2 = new Vect3d(getB());
        v2.setZ(box.min.z);
        Vect3d v3 = new Vect3d(getC());
        v3.setZ(box.min.z);
        
        return new Triangle3d(v1,v2,v3);
        
    }
    
    /**
     * Calcula la proyecci�n del este tri�ngulo sobre el plano XZ
     * 
     * @return Triangle3d (v1, v2, v3)
     */
    public Triangle3d proyecta_XZ(){
        
        Vect3d v1 = new Vect3d(getA());
        v1.setY(0.0f);
        Vect3d v2 = new Vect3d(getB());
        v2.setY(0.0f);
        Vect3d v3 = new Vect3d(getC());
        v3.setY(0.0f);
        
        return new Triangle3d(v1,v2,v3);
        
    }
    
            
    /**
     * Calcula la proyecci�n del este tri�ngulo sobre el plano XZ de su caja envolvente
     * 
     * @return Triangle3d (v1, v2, v3)
     */
    public Triangle3d proyecta_XZ(AABB box){
        
        Vect3d v1 = new Vect3d(getA());
        v1.setY(box.min.y);
        Vect3d v2 = new Vect3d(getB());
        v2.setY(box.min.y);
        Vect3d v3 = new Vect3d(getC());
        v3.setY(box.min.y);
        
        return new Triangle3d(v1,v2,v3);
        
    }
    
    /**
     * Calcula la proyecci�n del este tri�ngulo sobre el plano YZ
     * 
     * @return Triangle3d (v1, v2, v3)
     */
    public Triangle3d proyecta_YZ(){
        
        Vect3d v1 = new Vect3d(getA());
        v1.setX(0.0f);
        Vect3d v2 = new Vect3d(getB());
        v2.setX(0.0f);
        Vect3d v3 = new Vect3d(getC());
        v3.setX(0.0f);
        
        return new Triangle3d(v1,v2,v3);
        
    }
    
    /**
     * Calcula la proyecci�n del este tri�ngulo sobre el plano XY de su caja envolvente
     * 
     * @return Triangle3d (v1, v2, v3)
     */
    public Triangle3d proyecta_YZ(AABB box){
        
        Vect3d v1 = new Vect3d(getA());
        v1.setX(box.min.x);
        Vect3d v2 = new Vect3d(getB());
        v2.setX(box.min.x);
        Vect3d v3 = new Vect3d(getC());
        v3.setX(box.min.x);
        
        return new Triangle3d(v1,v2,v3);
        
    }
    
    /** Calcula la interseccion entre este tri�ngulo y una recta */
    public boolean LineTriangle3d(Line3d r, Vect3d point){
        
        Vect3d e1 = new Vect3d(a2.orig.resta(a1.orig));
        Vect3d e2 = new Vect3d(a3.orig.resta(a1.orig));
        Vect3d direccion = r.getDestino().resta(r.getOrigen());
        
        Vect3d p = new Vect3d(direccion.XProduct(e2));
        
        double tmp = p.dot(e1);
        
        if(tmp > -BasicGeom.CERO && tmp < BasicGeom.CERO ){
            
            return false;
        }
        
        tmp = 1.0/tmp;
        
        Vect3d s = new Vect3d(r.getOrigen().resta(a1.orig));
        double u = tmp * s.dot(p);
        
        if (u<0.0 || u>1.0){
            
            return false;
            
        }
        
        Vect3d q = new Vect3d(s.XProduct(e1));
        double v = tmp * (direccion.dot(q));
        
        if (v<0.0 || v>1.0){
            
            return false;
            
        }
        
        if (u+v>1.0) return false;
        
        double t = tmp * (e2.dot(q));
        
        point.x=(r.getOrigen().x)+(direccion.prodEscalar(t).x);
        point.y=(r.getOrigen().y)+(direccion.prodEscalar(t).y);
        point.z=(r.getOrigen().z)+(direccion.prodEscalar(t).z);

        return true;
    }
    
    /** Calcula la interseccion entre este tri�ngulo y un rayo */
    public boolean RayTriangle3d(Ray3d r, Vect3d[] point){

        float EPSILON = 0.000001f;

        Vect3d e1 = new Vect3d(a2.orig.resta(a1.orig));
        Vect3d e2 = new Vect3d(a3.orig.resta(a1.orig));

        Vect3d direccion = r.getDestino().resta(r.getOrigen());
        
        Vect3d p = direccion.XProduct(e2);

        double tmp = e1.dot(p);
        
        if(tmp > -EPSILON && tmp < EPSILON ){
            
            return false;
        }

        tmp = 1.0f / tmp;
        
        Vect3d s = new Vect3d(r.getOrigen().resta(a1.orig));
        double u = (s.dot(p)) * tmp;
        
        if (u<0.0 || u>1.0){
            
            return false;
            
        }

        Vect3d q = new Vect3d(s.XProduct(e1));
        double v = (direccion.dot(q))*tmp;
        
        if (v < 0.0f || (u + v) > 1.0f){
            
          return false;
          
        }
        
        if(u+v>1.0){
            
            return false;
            
        }
        
        double t = (e2.dot(q))*tmp;

        if (t<0.0){
            
            return false;
            
        }
        
        point[0] = r.getOrigen().suma(direccion.prodEscalar(t));
        
        return true;
      }
    
    
    public boolean isPointIn(Vect3d v){
        
        if(a1.orig.x == v.x && a1.orig.y == v.y && a1.orig.z == v.z){
            return true;
        }
        if(a2.orig.x == v.x && a2.orig.y == v.y && a2.orig.z == v.z){
            return true;
        }
        if(a3.orig.x == v.x && a3.orig.y == v.y && a3.orig.z == v.z){
            return true;
        }
        return false;
    }
    
    public void toOrigin(double Xmin, double Xmax, double Ymin, double Ymax){
        
        a1.orig.x=(a1.orig.x-Xmin-(Xmax-Xmin)/2);
        a1.orig.y=(a1.orig.y-Ymin-(Ymax-Ymin)/2);

        a2.orig.x=(a2.orig.x-Xmin-(Xmax-Xmin)/2);
        a2.orig.y=(a2.orig.y-Ymin-(Ymax-Ymin)/2);

        a3.orig.x=(a3.orig.x-Xmin-(Xmax-Xmin)/2);
        a3.orig.y=(a3.orig.y-Ymin-(Ymax-Ymin)/2);

    }
    
    public void undoToOrigin(double Xmin, double Xmax, double Ymin, double Ymax){
        a1.orig.x=(a1.orig.x+Xmin+(Xmax-Xmin)/2);
        a1.orig.y=(a1.orig.y+Ymin+(Ymax-Ymin)/2);

        a2.orig.x=(a2.orig.x-Xmin+(Xmax-Xmin)/2);
        a2.orig.y=(a2.orig.y+Ymin+(Ymax-Ymin)/2);

        a3.orig.x=(a3.orig.x+Xmin+(Xmax-Xmin)/2);
        a3.orig.y=(a3.orig.y+Ymin+(Ymax-Ymin)/2);
    }
    
    public Vect3d centroide(){
        
        return new Vect3d((a1.orig.x+a2.orig.x+a3.orig.x)/3,(a1.orig.y+a2.orig.y+a3.orig.y)/3,(a1.orig.z+a2.orig.z+a3.orig.z)/3);
        
    }
    
      /**Muestra un punto 3d en pantalla*/
     public void out (){
         System.out.println("Triangle3d: ("+ a1 +"-"+ a2 + "-"+ a3 +")");
     }

}