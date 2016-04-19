package algGeom;

public class AABB {

    Vect3d min; //menor x,y,z
    Vect3d max; //max x, y, z

public AABB (Vect3d menor, Vect3d mayor){
    min = menor;
    max = mayor;
}

public AABB (double mx, double my, double mz, double mxx, double mxy, double mxz){
    Vect3d menor = new Vect3d (mx,my,mz);
    Vect3d mayor = new Vect3d (mxx, mxy, mxz);
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

public boolean RayAABB(Ray3d _r, Vect3d[] point){
    double[] tnear,tfar;
    tnear = new double[1];
    tfar = new double[1];
    tnear[0] = -BasicGeom.INFINITO; 
    tfar[0] = BasicGeom.INFINITO;
    boolean ix = true, iy = true, iz = true; //interseccion con el par de planos
    
    Ray3d r = new Ray3d (_r.orig, _r.getDestino().resta(_r.getOrigen()).suma(_r.orig));
    Vect3d direccion = r.getDestino().resta(r.getOrigen());
    if(testRayCara(tnear,tfar,r.orig.getX(),_r.dest.getX(),min.getX(),max.getX())){
        point[0] = _r.getOrigen().suma(direccion.prodEscalar(tnear[0]));
        return true;
    }
    if(testRayCara(tnear,tfar,r.orig.getY(),_r.dest.getY(),min.getY(),max.getY())){
        point[0] = _r.getOrigen().suma(direccion.prodEscalar(tnear[0]));
        return true;
    }
    if(testRayCara(tnear,tfar,r.orig.getZ(),_r.dest.getZ(),min.getZ(),max.getZ())){
        point[0] = _r.getOrigen().suma(direccion.prodEscalar(tnear[0]));
        return true;
    }
    return false;
}

private boolean testRayCara(double[] tnear, double[] tfar, double ox,double dx, double x1, double x2){
    double t1,t2;
    if(dx == 0){
       //Es paralelo al plano
       if(ox < x1 || ox > x2){
           return false; //No intersecta
       }
    }else{
        t1 = (x1-ox)/dx;
        t2 = (x2-ox)/dx;
        if (t1>t2){
            double aux = t1;
            t1=t2;
            t2=aux;
        }
        if(t1>tnear[0]){
            tnear[0]=t1;
        }
        if(t2<tfar[0]){
            tfar[0]=t2;
        }
    }
    if(tnear[0]>tfar[0]) return false;
    if(tfar[0]<0) return false;
    return true;
}

/** devuelve el punto de la esquina inferior */
public Vect3d getMin (){
    return min;
}

/** devuelve el punto de la esquina superior */
public Vect3d getMax (){
    return max;
}

public void out () {
        System.out.print ("Coordenadas minimas: ");
        min.out();
        System.out.print ("Coordenadas maximas: ");
        max.out();
    }

}
