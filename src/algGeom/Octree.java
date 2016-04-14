package algGeom;
import java.util.Vector;

public class Octree {
    
    private int nPuntos;
    private Vector<Vect3d> vPuntos;
    private int topeNivel;
    private NodoOctree raiz;
    
    public Octree(){
        topeNivel = 1;
        nPuntos=0;
        vPuntos = new Vector<Vect3d>();
        raiz = new NodoOctree(0,null,null,null,null);
        
    }
    
    public Octree(Cloud3d nb, int tNivel){
        topeNivel = tNivel;
        nPuntos=nb.tama();
        vPuntos = new Vector<Vect3d>();
        AABB box = nb.getAABB();
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
    
    
}
