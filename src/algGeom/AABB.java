package algGeom;
import java.lang.Math;

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
    boolean intersecta = true;
    
    Vect3d direccion = _r.getDestino().resta(_r.getOrigen());
    if(!testRayCara(tnear,tfar,_r.orig.getX(),direccion.getX(),min.getX(),max.getX())){
        point[0] = _r.getOrigen().suma(direccion.prodEscalar(tnear[0]));
        intersecta = false;
    }
    if(!testRayCara(tnear,tfar,_r.orig.getY(),direccion.getY(),min.getY(),max.getY())){
        point[0] = _r.getOrigen().suma(direccion.prodEscalar(tnear[0]));
        intersecta = false;
    }
    if(!testRayCara(tnear,tfar,_r.orig.getZ(),direccion.getZ(),min.getZ(),max.getZ())){
        point[0] = _r.getOrigen().suma(direccion.prodEscalar(tnear[0]));
        intersecta = false;
    }
    return intersecta;
}

private boolean testRayCara(double[] tnear, double[] tfar, double ox,double dx, double x1, double x2){
    double t1,t2;
    if(dx == 0){
       //Es paralelo al plano
       if(ox < x1 || ox > x2) return false; //No intersecta
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

public boolean AABBTri(Triangle3d t){
    Vect3d v0,v1,v2, normal, e0, e1, e2;
    double minimo, maximo,a, b, d,p0, p1, p2, rad,fex,fey,fez;
    
    v0 = t.a.resta(boxCenter());
    v1 = t.b.resta(boxCenter());
    v2 = t.c.resta(boxCenter());
    
    e0= v1.resta(v0);
    e1= v2.resta(v1);
    e2= v0.resta(v2);
    
    fex= Math.abs(e0.getX());
    fey= Math.abs(e0.getY());
    fez= Math.abs(e0.getZ());
    
    // original x01
    p0 = e0.getZ() * v0.getY() - e0.getY()*v0.getZ();
    p2 = e0.getZ() * v2.getY() - e0.getY()*v2.getZ();
    if(p0<p2){
        minimo = p0;
        maximo = p2;
    }else{
        minimo = p2;
        maximo = p0;
    }
    rad = fez*boxHalf().getY() + fey*boxHalf().getZ();
    if(minimo>rad || maximo<-rad)return false;
    //Fin original
    
        //Victima del coy-paste vvvvvvvvvvv y02
    p0 = -e0.getY() * v0.getY() + e0.getX()*v0.getZ();
    p2 = -e0.getY() * v2.getY() + e0.getX()*v2.getZ();
    if(p0<p2){
        minimo = p0;
        maximo = p2;
    }else{
        minimo = p2;
        maximo = p0;
    }
    rad = fey*boxHalf().getX() + fex*boxHalf().getZ();
    if(minimo>rad || maximo<-rad)return false;
    //Victima del copoy-paste ^^^^^^^^^^^^
    
    //Victima del coy-paste vvvvvvvvvvv z12
    p0 = -e1.getZ() * v0.getY() + e1.getY()*v0.getZ();
    p1 = -e1.getZ() * v2.getY() + e1.getY()*v2.getZ();
    if(p0<p1){
        minimo = p0;
        maximo = p1;
    }else{
        minimo = p1;
        maximo = p0;
    }
    rad = fez*boxHalf().getX() + fey*boxHalf().getZ();
    if(minimo>rad || maximo<-rad)return false;
    //Victima del copoy-paste ^^^^^^^^^^^^
    
    fex= Math.abs(e1.getX());
    fey= Math.abs(e1.getY());
    fez= Math.abs(e1.getZ());
    
    // original x01 Victima copi paste vvvvvvvvv
    p0 = e1.getZ() * v0.getY() - e1.getY()*v0.getZ();
    p2 = e1.getZ() * v2.getY() - e1.getY()*v2.getZ();
    if(p0<p2){
        minimo = p0;
        maximo = p2;
    }else{
        minimo = p2;
        maximo = p0;
    }
    rad = fez*boxHalf().getY() + fey*boxHalf().getZ();
    if(minimo>rad || maximo<-rad)return false;
    //Fin original victima copi paste ^^^^^^^^ 
  
    //Victima del coy-paste vvvvvvvvvvv y02
    p0 = -e1.getY() * v0.getY() + e1.getX()*v0.getZ();
    p2 = -e1.getY() * v2.getY() + e1.getX()*v2.getZ();
    if(p0<p2){
        minimo = p0;
        maximo = p2;
    }else{
        minimo = p2;
        maximo = p0;
    }
    rad = fey*boxHalf().getX() + fex*boxHalf().getZ();
    if(minimo>rad || maximo<-rad)return false;
    //Victima del copoy-paste ^^^^^^^^^^^^
    
    //Victima del coy-paste vvvvvvvvvvv z0
    p1 = e1.getY() * v0.getY() - e1.getX()*v0.getZ();
    p2 = e1.getY() * v2.getY() - e1.getX()*v2.getZ();
    if(p0<p2){
        minimo = p1;
        maximo = p2;
    }else{
        minimo = p2;
        maximo = p1;
    }
    rad = fey*boxHalf().getX() + fex*boxHalf().getY();
    if(minimo>rad || maximo<-rad)return false;
    //Victima del copoy-paste ^^^^^^^^^^^^
    
    fex= Math.abs(e2.getX());
    fey= Math.abs(e2.getY());
    fez= Math.abs(e2.getZ());
    
    //Victima del coy-paste vvvvvvvvvvv x_02
    p0 = e0.getZ() * v0.getY() - e0.getX()*v0.getZ();
    p1 = e0.getZ() * v1.getY() - e0.getX()*v1.getZ();
    if(p0<p1){
        minimo = p0;
        maximo = p1;
    }else{
        minimo = p1;
        maximo = p0;
    }
    rad = fez*boxHalf().getY() + fex*boxHalf().getZ();
    if(minimo>rad || maximo<-rad)return false;
    //Victima del copoy-paste ^^^^^^^^^^^^
    
    // original y1
    p0 = -e2.getZ() * v0.getY() + e2.getY()*v0.getZ();
    p1 = -e2.getZ() * v2.getY() + e2.getY()*v2.getZ();
    if(p1<p0){
        minimo = p0;
        maximo = p1;
    }else{
        minimo = p1;
        maximo = p0;
    }
    rad = fez*boxHalf().getX() + fex*boxHalf().getZ();
    if(minimo>rad || maximo<-rad)return false;
    //Fin original
    
    //Victima del coy-paste vvvvvvvvvvv z12
    p0 = -e2.getY() * v0.getY() + e2.getX()*v0.getZ();
    p1 = -e2.getY() * v2.getY() + e2.getX()*v2.getZ();
    if(p0<p1){
        minimo = p0;
        maximo = p1;
    }else{
        minimo = p1;
        maximo = p0;
    }
    rad = fey*boxHalf().getX() + fex*boxHalf().getZ();
    if(minimo>rad || maximo<-rad)return false;
    //Victima del copoy-paste ^^^^^^^^^^^^
    
    maximo = new Vect3d (v0.getX(),v1.getX(),v2.getX()).buscaMaximo();
    minimo = new Vect3d (v0.getX(),v1.getX(),v2.getX()).buscaMinimo();
    if(minimo>boxHalf().getX() || maximo < - boxHalf().getX())return false;
    
    maximo = new Vect3d (v0.getY(),v1.getY(),v2.getY()).buscaMaximo();
    minimo = new Vect3d (v0.getY(),v1.getY(),v2.getY()).buscaMinimo();
    if(minimo>boxHalf().getY() || maximo < - boxHalf().getY())return false;
    
    maximo = new Vect3d (v0.getZ(),v1.getZ(),v2.getZ()).buscaMaximo();
    minimo = new Vect3d (v0.getZ(),v1.getZ(),v2.getZ()).buscaMinimo();
    if(minimo>boxHalf().getZ() || maximo < - boxHalf().getZ())return false;
    
    normal = e0.XProduct(e1);
    d = -(normal.dot(v0));
    
    if(!planeBoxOverlap(normal, d ,boxHalf())) return false;

    
    return true;
}

private boolean planeBoxOverlap(Vect3d normal, double d, Vect3d maxbox){
    
    Vect3d vmin, vmax;
    vmin = normal;
    vmax= normal;
    
    if(normal.getX()>0){
        vmin.x = (-maxbox.getX());
        vmax.x = (maxbox.getX());
    }else{
        vmin.x = (maxbox.getX());
        vmax.x = (-maxbox.getX());   
    }
    
    if(normal.getY()>0){
        vmin.x = (-maxbox.getY());
        vmax.x = (maxbox.getY());
    }else{
        vmin.x = (maxbox.getY());
        vmax.x = (-maxbox.getY());   
    }
    
    if(normal.getZ()>0){
        vmin.x = (-maxbox.getZ());
        vmax.x = (maxbox.getZ());
    }else{
        vmin.x = (maxbox.getZ());
        vmax.x = (-maxbox.getZ());   
    }
    
    if((normal.dot(vmin)) + d > 0)return false;
    if((normal.dot(vmax)) + d >= 0)return true;
    
    return false;
}



public Vect3d boxCenter(){
    return new Vect3d(min.suma(boxHalf()));
}
public Vect3d boxHalf(){
    return new Vect3d(max.resta(min).prodEscalar(0.5));
} 

public void out () {
        System.out.print ("Coordenadas minimas: ");
        min.out();
        System.out.print ("Coordenadas maximas: ");
        max.out();
    }

}
