package algGeom;
import java.util.ArrayList;
import java.util.Vector;
import javax.media.opengl.GL;

public class NodoOctree {
    NodoOctree padre;
    Vector<Vect3d> pContenidos;
    private ArrayList<Triangle3d> triangles;
    Vect3d minimo;
    Vect3d maximo;
    int nivel;
    Octree oct;
    NodoOctree[] hijos;
    boolean hijosCreados;
    AABB box;
    
    public NodoOctree(int nnivel, NodoOctree nodoP, Vect3d min, Vect3d max, Octree o){
        
        hijosCreados = false;
        hijos = new NodoOctree[8];
        nivel = nnivel;
        oct = o;
        padre = nodoP;
        for (int i=0; i<8; i++){
            hijos[i] = null; 
        }
        minimo = min;
        maximo = max;
        pContenidos = new Vector<Vect3d>();
        triangles = new ArrayList<Triangle3d>();
        box = new AABB(minimo, maximo);
    }
    public void insertaTriangulo(Triangle3d t){
        //triangles.add(t);
        if(nivel==oct.getLimite()){
        //if(!hijosCreados){
            triangles.add(t);
            //triangles.add(new Triangle3d());
        }else{
            for(int i=0; i<8; i++){
                if(hijos[i].box.aabbTri(t)){
                    hijos[i].insertaTriangulo(t);
                }
            }
        }
        
    }
    
    public void insertaPunto(Vect3d p){
        if (nivel==oct.getLimite()){
            pContenidos.add(p);
        }
        else{
            if(!hijosCreados()){
                creaHijos(); 
            }
            double coorX = p.getX();
            double coorY = p.getY();
            double coorZ = p.getZ();
            double minX = minimo.getX();
            double minY = minimo.getY();
            double minZ = minimo.getZ();
            double maxX = maximo.getX();
            double maxY = maximo.getY();
            double maxZ = maximo.getZ();
            double medX = (maxX+minX)/2;
            double medY = (maxY+minY)/2;
            double medZ = (maxZ+minZ)/2;
            if(coorY >= medY){
                if(coorX >= medX){
                    if(coorZ >= medZ){
                        hijos[7].insertaPunto(p);
                    }
                    else{
                        hijos[5].insertaPunto(p);
                    }
                }
                else{
                    if(coorZ >= medZ){
                        hijos[6].insertaPunto(p);
                    }
                    else{
                        hijos[4].insertaPunto(p);
                    }
                }
            }
            else{
                if(coorX >= medX){
                    if(coorZ >= medZ){
                        hijos[3].insertaPunto(p);
                    }
                    else{
                        hijos[1].insertaPunto(p);
                    }
                }
                else{
                    if(coorZ >= medZ){
                        hijos[2].insertaPunto(p);
                    }
                    else{
                        hijos[0].insertaPunto(p);
                    }
                }
            }
        }  
    }
    
    private void creaHijos(){
        setHijosCreados(true);
        double minX = minimo.getX();
        double minY = minimo.getY();
        double minZ = minimo.getZ();
        double maxX = maximo.getX();
        double maxY = maximo.getY();
        double maxZ = maximo.getZ();
        double medX = (maxX+minX)/2;
        double medY = (maxY+minY)/2;
        double medZ = (maxZ+minZ)/2;
        
        hijos[6] = new NodoOctree(nivel+1,this,new Vect3d(minX,medY,medZ),new Vect3d(medX,maxY,maxZ),oct);
        hijos[4] = new NodoOctree(nivel+1,this,new Vect3d(minX,medY,minZ),new Vect3d(medX,maxY,medZ),oct);
        hijos[5] = new NodoOctree(nivel+1,this,new Vect3d(medX,medY,minZ),new Vect3d(maxX,maxY,medZ),oct);
        hijos[7] = new NodoOctree(nivel+1,this,new Vect3d(medX,medY,medZ),new Vect3d(maxX,maxY,maxZ),oct);
        hijos[2] = new NodoOctree(nivel+1,this,new Vect3d(minX,minY,medZ),new Vect3d(medX,medY,maxZ),oct);
        hijos[0] = new NodoOctree(nivel+1,this,new Vect3d(minX,minY,minZ),new Vect3d(medX,medY,medZ),oct);
        hijos[1] = new NodoOctree(nivel+1,this,new Vect3d(medX,minY,minZ),new Vect3d(maxX,medY,medZ),oct);
        hijos[3] = new NodoOctree(nivel+1,this,new Vect3d(medX,minY,medZ),new Vect3d(maxX,medY,maxZ),oct);
    }
    
    private void setHijosCreados(boolean v){
        hijosCreados=v;
    }
    
    private boolean hijosCreados(){
        return hijosCreados;
    }
    
    public NodoOctree[] getHijos(){
        
        return hijos;
        
    }
    
    private NodoOctree buscar(Vect3d p){
        if (nivel==oct.getLimite()){
            return this;
        }
        double coorX = p.getX();
        double coorY = p.getY();
        double coorZ = p.getZ();
        double minX = minimo.getX();
        double minY = minimo.getY();
        double minZ = minimo.getZ();
        double maxX = maximo.getX();
        double maxY = maximo.getY();
        double maxZ = maximo.getZ();
        double medX = (maxX+minX)/2;
        double medY = (maxY+minY)/2;
        double medZ = (maxZ+minZ)/2;
        NodoOctree result;
        if(coorY >= medY){
            if(coorX >= medX){
                if(coorZ >= medZ){
                    result = hijos[7].buscar(p);
                }
                else{
                    result = hijos[5].buscar(p);
                }
            }
            else{
                if(coorZ >= medZ){
                    result = hijos[6].buscar(p);
                }
                else{
                    result = hijos[4].buscar(p);
                }
            }
        }
        else{
            if(coorX >= medX){
                if(coorZ >= medZ){
                    result = hijos[3].buscar(p);
                }
                else{
                    result = hijos[1].buscar(p);
                }
            }
            else{
                if(coorZ >= medZ){
                    result = hijos[2].buscar(p);
                }
                else{
                    result = hijos[0].buscar(p);
                }
            }
        }
        return result;
    }
        
    public boolean RayOctree(Ray3d r, Vect3d[] v, Vector<Vect3d> pPuntos, GL g){
        if(box.RayAABB(r,v)){
            if(hijosCreados){
                for (int i = 0; i<hijos.length;i++){
                    hijos[i].RayOctree(r,v,pPuntos,g);
                }
            }
            //A este punto solo llega si es el ultimo
            //Devolver lo que tenga que devolver
            if(!hijosCreados && !pContenidos.isEmpty()){
                //Triangle3d t = new Triangle3d (pContenidos.get(0),pContenidos.get(1),pContenidos.get(2));
                //DrawTriangle3d dt = new DrawTriangle3d(t);
                //dt.drawObject(g);
                DrawAABB dbox = new DrawAABB (this.box);
                dbox.drawWireObjectC(g, 1, 0.5f , 0);
                pPuntos.addAll(pContenidos);
            }
            //Aqui va el test de ray-triangle
            return true;
        }
        //este nodo no tiene interseccion con el ray
        return false;
    }
    public boolean RayOctree(Ray3d r, Triangle3d[] t, boolean[] buscando){//El bueno, el de verdad
        if(buscando[0]){
            Vect3d[] point= new Vect3d[1];
            if(box.RayAABB(r, point)){
                if(hijosCreados){
                   for (int i = 0; i<hijos.length;i++){
                        hijos[i].RayOctree(r,t,buscando);
                    } 
                }

                if(!hijosCreados && !triangles.isEmpty()){
                    for(int i = 0 ; i<triangles.size();i++){
                        if(triangles.get(i).RayTriangle3d(r, point)){
                            buscando[0] = false;
                            t[0] = triangles.get(i);
                            return false;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }
    
}
