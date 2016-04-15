package algGeom;
import java.util.ArrayList;
import java.util.Vector;

public class Octree {
    
    private int nPuntos;
    private Vector<Vect3d> vPuntos;
    private int topeNivel;
    private NodoOctree raiz;
    AABB box;
    
    public Octree(){
        topeNivel = 1;
        nPuntos=0;
        vPuntos = new Vector<Vect3d>();
        raiz = new NodoOctree(0,null,null,null,null);
        
    }
    
    public Octree(AABB bb, int tNivel, ArrayList<Vect3d> vertex){
        topeNivel = tNivel;
        nPuntos=vertex.size();
        vPuntos = new Vector<Vect3d>();
        box = bb;
        raiz = new NodoOctree(0,null,box.getMin(),box.getMax(),this);
        for (int i=0; i<nPuntos;i++){
            Vect3d p= vertex.get(i);
            vPuntos.add(p);
            raiz.insertaPunto(p);
        }
    }
    
    public Octree(Cloud3d nb, int tNivel){
        topeNivel = tNivel;
        nPuntos=nb.tama();
        vPuntos = new Vector<Vect3d>();
        box = nb.getAABB();
        raiz = new NodoOctree(0,null,box.getMin(),box.getMax(),this);
        for (int i=0; i<nPuntos;i++){
            Vect3d p= nb.getPunto(i);
            vPuntos.add(p);
            raiz.insertaPunto(p);
        }
    }
    
    public int getLimite(){
        return topeNivel;
    }
    
    public NodoOctree getRaiz(){
        
        return raiz;
        
    }
    
    
}
