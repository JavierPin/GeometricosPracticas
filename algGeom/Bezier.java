package algGeom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lidia
 */

import java.util.*;

public class Bezier {
      /** Numero de puntos de la curva */
    protected int nVertexs;
    /** Vector de puntos que forman la curva. */
    protected ArrayList<Vector> lpuntos;
    

 /** Contruye una curva por defecto  */
    public Bezier () {
        lpuntos = new ArrayList<Vector> ();
        nVertexs = 0;
    }
    
    /** Construye una curva con un numero de Vertexs nV; cuidado, la curva necesita añadir puntos
     * @param nV */

    public Bezier (int nV) {
        lpuntos = new ArrayList<Vector> (nV);
        nVertexs = nV;
    }
    
    /** Construye una curva a partir de otra (constructor copia)
     * @param b */
    public Bezier(Bezier b) {
        lpuntos = new ArrayList<Vector> ( b.lpuntos) ;
        nVertexs = b.nVertexs;
    }
    
    /** Construye una curva a partir de los vértices de un vector */
    public Bezier (ArrayList<Vector> vert, int nV) {
        lpuntos = new ArrayList<Vector> (vert);
        nVertexs = nV;
    }
    
    
    
    /** Devuelve una copia del Bezier actual. */
    public Bezier copia () {
        Bezier nuevoBezier = new Bezier (nVertexs);
        nuevoBezier.lpuntos = new ArrayList<Vector> (lpuntos);
        nuevoBezier.nVertexs = nVertexs;
        return nuevoBezier;
    }
    
    /** Obtiene una copia del Bezier pl. */
    public void copia (Bezier b) {
        lpuntos.clear();                           //Se limpia el vector.
        lpuntos = new ArrayList<Vector> (b.lpuntos);    //Se copia el vector.
        nVertexs = b.nVertexs;
    }
    
    
    /** Establece el vertice v en la ultima posicion del poligono actual. */
    public void anade (Vector v) {
        lpuntos.add (v );
        nVertexs ++;
    }

    /** Establece el vertice v en la ultima posicion del poligono actual. */
    public void anade (Point p) {
            Vector v =  new Vector (p);
            lpuntos.add (v );
            nVertexs ++;
    }
    
    
    /** Devuelve el v�rtice v que ocupa la posici�n pos. */
    public Vector lee (int pos) {
        if (pos >= 0 && pos < nVertexs)
            return lpuntos.get (pos);
        else
            return null;
    }
    
    
    /** Devuelve una copia del v�rtice v que ocupa la posici�n pos. */
    public Vector leeAsigna (int pos) {
        if (pos >= 0 && pos < nVertexs)
            return new Vector (lpuntos.get(pos));
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
    
    
    
}
