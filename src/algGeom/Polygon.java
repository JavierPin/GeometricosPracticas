package algGeom;


import java.io.*;
import java.util.*;
import math.geom2d.polygon.*;
import math.geom2d.Point2D;
import math.geom2d.polygon.Polygons2D;

/** Representa un Polygon con nVertexs Vertexs. */
public class Polygon {

    /** Numero de Vertexs. */
    protected int nVertexs;
    /** Vector de Vertexs que pertenecen al Polygon. */
    protected ArrayList<Vertex> Vertexs;
    
    /** Contruye un Polygon por defecto (sin ningun Vertex). */
    public Polygon () {
        Vertexs = new ArrayList<Vertex> ();
        nVertexs = 0;
    }
    
    /** Construye un Polygon con un numero de Vertexs nV. */

    public Polygon (int nV) {
        Vertexs = new ArrayList<Vertex> (nV);
        nVertexs = nV;
    }
    
    /** Construye un Polygon como p1. Para ello se utiliza el metodo clone,
     sin necesidad de reservar memoria, ya que para usar este metodo no es
     necesario. */
    public Polygon(Polygon pl) {
        Vertexs = new ArrayList<Vertex> ( pl.Vertexs) ;
        nVertexs = pl.nVertexs;
    }
    
    /** Construye un Polygon con el numero de Vertex nV, los cuales estaran
     en el vector vert. */
    public Polygon (ArrayList<Vertex> vert, int nV) {
        Vertexs = new ArrayList<Vertex> (vert);
        nVertexs = nV;
    }
    
    public Polygon (Polygon2D pol) {
        
        nVertexs = pol.vertexNumber();
        Vertexs = new ArrayList<Vertex> (nVertexs);
        
        for (int i=0; i<pol.vertexNumber();i++){
            Vertexs.add(new Vertex(pol.vertex(i)));
        }
        
    }
    /** Devuelve una copia del Polygon actual. */
    public Polygon copia () {
        Polygon nuevoPolygon = new Polygon (nVertexs);
        nuevoPolygon.Vertexs = new ArrayList<Vertex> (Vertexs);
        nuevoPolygon.nVertexs = nVertexs;
        return nuevoPolygon;
    }
    
    /** Obtiene una copia del Polygon pl. */
    public void copia (Polygon pl) {
        Vertexs.clear();                           //Se limpia el vector.
        Vertexs = new ArrayList<Vertex> (pl.Vertexs);    //Se copia el vector.
        nVertexs = pl.nVertexs;
    }
    
    /** Establece el Vertex v en la posicion pos del Polygon actual. Teniendo
     en cuenta que el vector esta indexado desde 0. */
    public void modifica (Vertex v,int pos) {
        if (pos >= 0 && pos < nVertexs) {
            Vertex antiguo = new Vertex ((Vertex) Vertexs.get (pos));
            antiguo.modificaPolygon (null);
            antiguo.modificaPosicion (-1);
            Vertexs.set(pos, (Vertex)v);
            v.modificaPolygon (this);
            v.modificaPosicion (pos);
        }
    }
    
    /** Establece el vertice v en la ultima posicion del poligono actual. */
    public void anade (Vertex v) {
        Vertexs.add ((Vertex)v );
        v.modificaPolygon (this);
        v.modificaPosicion (nVertexs);
        nVertexs ++;
    }

    /** Establece el vertice v en la ultima posicion del poligono actual. */
    public void anade (Point p) {
            Vertex v =  new Vertex (p,this, nVertexs);
            Vertexs.add ((Vertex)v );
            nVertexs ++;
    }
    
    
    /** Devuelve el v�rtice v que ocupa la posici�n pos. */
    public Vertex lee (int pos) {
        if (pos >= 0 && pos < nVertexs)
            return (Vertex) Vertexs.get (pos);
        else
            return null;
    }
    
    
    /** Devuelve una copia del v�rtice v que ocupa la posici�n pos. */
    public Vertex leeAsigna (int pos) {
        if (pos >= 0 && pos < nVertexs)
            return new Vertex ((Vertex) Vertexs.get(pos));
        else
            return null;
    }
    
    /** Devuelve el n�mero de v�rtices que tiene el pol�gono. */
    public int numeroVertices () {
        return nVertexs;
    }
    

    /** devuelve la arista poligono[i]-poligono[i+1] en sentido antihorario*/
    public SegmentLine getArista (int i){
        return new SegmentLine (lee(i),lee((i+1)%nVertexs));
    }
    
    
    /**
     * Crea un polígono a partir de las coordenadas de los vértices ubicados en fichero
     * @param nombre nobre del fichero
     * @throws ErrorAlLeerFichero excepción si hay error en la lectura del fichero 
     */
    public Polygon (String nombre) throws ErrorAlLeerFichero, IOException{
        
        Vertexs = new ArrayList<Vertex>();
        
        int pos=0;

        Scanner scanner = new Scanner(new FileReader(nombre));

        while (scanner.hasNextLine()) {

            Scanner line = new Scanner(scanner.nextLine());
            line.useDelimiter(",");

            while (line.hasNext()){

                Vertexs.add(new Vertex(new Point(Double.parseDouble(line.next()),Double.parseDouble(line.next())),this,pos));
                pos++;
            }
        }
        
        scanner.close();

        nVertexs = Vertexs.size();

    }
    
    /**
     * Guarda el polígono en fichero con el mismo formato que utiliza el constructor
     * @param nombre del fichero 
     * @throws ErrorAlGuardar, se lanza una excepción si exite algún problema al abrir el fichero
     */
    public void salvar(String nombre) throws ErrorAlGuardar, IOException{
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(nombre));
        
        for (int i=0; i<nVertexs; i++) {
            bw.write(Vertexs.get(i).x+","+Vertexs.get(i).y+ "\r");
        }
        
        bw.close();
    }

   
    /**
     * Dado un polígono conocido como covexo, determina si el punto P está o no dentro del polígono
     * @return true si el punto está dentro
     */
   
    boolean puntoEnPoligConvex (Point pt){
    	 //XXX
        return true;
    }
        
   /**
     * Dado un polígono determinar si es convexo
     * @return true si el polígono es convexo
     */
   
    boolean convexo (){
              
        for (int i=0; i<nVertexs;i++){
            
            if (Vertexs.get(i).convexo()==false){
                
                return false;
            }
            
        }
        
        return true;
    }
        
  
    boolean concavo (){
              
        for (int i=0; i<nVertexs;i++){
            
            if (Vertexs.get(i).concavo()==false){
                
                return false;
            }
            
        }
        
        return true;
    }

    public boolean intersecta (Line r, Vector interseccion){
        //toca recorer todos los segmentos que forman las aristas
        SegmentLine s;
        for (int i=0;i<=nVertexs;i++){
            if(i==nVertexs){
                s = new SegmentLine(lee(i),lee(0));//esta es la arista final
            }else{
                s = new SegmentLine(lee(i),lee(i+1));
            }
            if(r.intersecta(s, interseccion)){
                return true; //en el momento que tengamos una interseccion salimos
            }
        }
        return false;
    }
    
    public boolean intersecta (RayLine r, Vector interseccion){
        //toca recorer todos los segmentos que forman las aristas
        SegmentLine s;
        for (int i=0;i<=nVertexs;i++){
            if(i==nVertexs){
                s = new SegmentLine(lee(i),lee(0));//esta es la arista final
            }else{
                s = new SegmentLine(lee(i),lee(i+1));
            }
            if(r.intersecta(s, interseccion)){
                return true;
            }
        }
        return false;
    }
    
    public boolean intersecta (SegmentLine r, Vector interseccion){
        //toca recorer todos los segmentos que forman las aristas
        SegmentLine s;
        for (int i=0;i<=nVertexs;i++){
            if(i==nVertexs){
                s = new SegmentLine(lee(i),lee(0));//esta es la arista final
            }else{
                s = new SegmentLine(lee(i),lee(i+1));
            }
            if(r.intersecta(s, interseccion)){
                return true;
            }
        }
        return false;
    }
    
    /**convierte un poligono de algGeom a SimplePolygon2D de JavaGeom.
     * @return  SimplePolygon2D*/
    public SimplePolygon2D toJavaGeom(){
        
        SimplePolygon2D pol = new SimplePolygon2D();
        
        for(int i=0; i<nVertexs; i++){
            
            pol.addVertex(Vertexs.get(i).toJavaGeom());
            
        }
        
        return pol;
    }

    public Polygon union(Polygon a){
        
        Polygons2D calculator = new Polygons2D();        
        return new Polygon(calculator.union(toJavaGeom(),a.toJavaGeom()));
        
    }
    
    public Polygon intersection(Polygon a){
        
        Polygons2D calculator = new Polygons2D();        
        return new Polygon(calculator.intersection(toJavaGeom(),a.toJavaGeom()));
        
    }
    
    public Polygon difference(Polygon a){
        
        Polygons2D calculator = new Polygons2D();        
        return new Polygon(calculator.difference(toJavaGeom(),a.toJavaGeom()));
        
    }
    
    public Vertex centroid(){
        
        Polygons2D calculator = new Polygons2D();        
        return new Vertex(calculator.computeCentroid(toJavaGeom()));
        
    }




    /** Muestra por pantalla la informaci�n del pol�gono. */
    public void out () {
        Vertex v = new Vertex ();
        for (int i = 0;i < nVertexs;i++) {
            v = (Vertex)Vertexs.get(i);
            v.out ();
        }
    }
    
    

}
