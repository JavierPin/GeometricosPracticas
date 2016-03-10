package algGeom;

public class AABB {

    Vect3d min; //menor x,y,z
    Vect3d max; //max x, y, z

public AABB (Vect3d menor, Vect3d mayor){
    min = menor;
    max = mayor;
}

public AABB(Cloud3d cloud){
    
    double minX=BasicGeom.INFINITO;
    double minY=BasicGeom.INFINITO;
    double minZ=BasicGeom.INFINITO;
    double maxX=-BasicGeom.INFINITO;
    double maxY=-BasicGeom.INFINITO;
    double maxZ=-BasicGeom.INFINITO;
    
    for (int i=0; i<cloud.tama(); i++){
        
        if (cloud.getPunto(i).x<minX){
            
            minX=cloud.getPunto(i).x;
            
        }
        if (cloud.getPunto(i).x>maxX){
            
            maxX=cloud.getPunto(i).x;
            
        }
        if (cloud.getPunto(i).y<minY){
            
            minY=cloud.getPunto(i).y;
            
        }
        if (cloud.getPunto(i).y>maxY){
            
            maxY=cloud.getPunto(i).y;
            
        }
        if (cloud.getPunto(i).z<minZ){
            
            minZ=cloud.getPunto(i).z;
            
        }
        if (cloud.getPunto(i).z>maxZ){
            
            maxZ=cloud.getPunto(i).z;
            
        }
        
        
    }
    
    min = new Vect3d(minX,minY,minZ);
    max = new Vect3d(maxX,maxY,maxZ);
    
}

/** devuelve el punto de la esquina inferior */
public Vect3d getMin (){
    return min;
}

/** devuelve el punto de la esquina superior */
public Vect3d getMax (){
    return max;
}


}
