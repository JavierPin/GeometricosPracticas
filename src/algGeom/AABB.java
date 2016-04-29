package algGeom;

import java.lang.Math;

public class AABB {

    Vect3d min; //menor x,y,z
    Vect3d max; //max x, y, z
    Vect3d v0;
    Vect3d v1;
    Vect3d v2;
    Vect3d normal;
    Vect3d e0;
    Vect3d e1;
    Vect3d e2;
    double minimo, maximo, d, p0, p1, p2, rad, fex, fey, fez;

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

public Vect3d boxCenter(){
    
    return min.suma(boxHalfSize());
    
}

public Vect3d boxHalfSize(){
    
    return (max.resta(min)).prodEscalar(0.5f);
}

public boolean aabbTri(Triangle3d triverts){
    
    v0 = triverts.a.resta(boxCenter());
    v1 = triverts.b.resta(boxCenter());
    v2 = triverts.c.resta(boxCenter());
    
    e0 = v1.resta(v0);
    e1 = v2.resta(v1);
    e2 = v0.resta(v2);
    
    fex = Math.abs(e0.getX());
    fey = Math.abs(e0.getY());
    fez = Math.abs(e0.getZ());
    
    if (!axisTest_X01(e0.getZ(),e0.getY(),fez,fey))return false;
    if (!axisTest_Y02(e0.getZ(),e0.getX(),fez,fex))return false;
    if (!axisTest_Z12(e0.getY(),e0.getX(),fey,fex))return false;
    
    fex = Math.abs(e1.getX());
    fey = Math.abs(e1.getY());
    fez = Math.abs(e1.getZ());
    
    if (!axisTest_X01(e1.getZ(),e1.getY(),fez,fey))return false;
    if (!axisTest_Y02(e1.getZ(),e1.getX(),fez,fex))return false;
    if (!axisTest_Z0(e1.getY(),e1.getX(),fey,fex))return false;
    
    fex = Math.abs(e2.getX());
    fey = Math.abs(e2.getY());
    fez = Math.abs(e2.getZ());
    
    if (!axisTest_X2(e2.getZ(),e2.getY(),fez,fey))return false;
    if (!axisTest_Y1(e2.getZ(),e2.getX(),fez,fex))return false;
    if (!axisTest_Z12(e2.getY(),e2.getX(),fey,fex))return false;
    
    minimo = new Vect3d(v0.getX(), v1.getX(), v2.getX()).findMin();
    maximo = new Vect3d(v0.getX(), v1.getX(), v2.getX()).findMax();
    
    if(minimo>boxHalfSize().getX() || maximo<-boxHalfSize().getX()) return false;
    
    minimo = new Vect3d(v0.getY(), v1.getY(), v2.getY()).findMin();
    maximo = new Vect3d(v0.getY(), v1.getY(), v2.getY()).findMax();
    
    if(minimo>boxHalfSize().getY() || maximo<-boxHalfSize().getY()) return false;
    
    minimo = new Vect3d(v0.getZ(), v1.getZ(), v2.getZ()).findMin();
    maximo = new Vect3d(v0.getZ(), v1.getZ(), v2.getZ()).findMax();
    
    if(minimo>boxHalfSize().getZ() || maximo<-boxHalfSize().getZ()) return false;
    
    normal = e0.XProduct(e1);
    d = -(normal.dot(v0));
    
    if(!planeBoxOverlap(normal,d,boxHalfSize())) return false;
    
    return true;
}

private boolean planeBoxOverlap(Vect3d normal, double d, Vect3d maxbox){
    
    Vect3d vmin;
    Vect3d vmax;
    vmin=new Vect3d(0,0,0);
    vmax=new Vect3d(0,0,0);
    
    if (normal.getX()>0){
        vmin.setX(-maxbox.getX());
        vmax.setX(maxbox.getX());
    }
    else{
        vmin.setX(maxbox.getX());
        vmax.setX(-maxbox.getX());
    }
    
    if (normal.getY()>0){
        vmin.setY(-maxbox.getY());
        vmax.setY(maxbox.getY());
    }
    else{
        vmin.setY(maxbox.getY());
        vmax.setY(-maxbox.getY());
    }
    
    if (normal.getZ()>0){
        vmin.setZ(-maxbox.getZ());
        vmax.setZ(maxbox.getZ());
    }
    else{
        vmin.setZ(maxbox.getZ());
        vmax.setZ(-maxbox.getZ());
    }
    
    if((normal.dot(vmin))+d>0)return false;
    if((normal.dot(vmax))+d>=0)return true;
    return false;
}

private boolean axisTest_X01(double a,double b,double fa,double fb){
    p0 = a*v0.getY() - b*v0.getZ();
    p2 = a*v2.getY() - b*v2.getZ();
    
    if (p0<p2){
        minimo=p0;
        maximo=p2;
    }
    else{
        minimo=p2;
        maximo=p0;
    }
    rad = fa*boxHalfSize().getY() + fb* boxHalfSize().getZ();
    
    if (minimo>rad || maximo<-rad) return false;
    
    return true;
}

private boolean axisTest_X2(double a,double b,double fa,double fb){
    p0 = a*v0.getY() - b*v0.getZ();
    p1 = a*v1.getY() - b*v1.getZ();
    
    if (p0<p1){
        minimo=p0;
        maximo=p1;
    }
    else{
        minimo=p1;
        maximo=p0;
    }
    rad = fa*boxHalfSize().getY() + fb* boxHalfSize().getZ();
    
    if (minimo>rad || maximo<-rad) return false;
    
    return true;
}

private boolean axisTest_Y02(double a,double b,double fa,double fb){
    p0 = -a*v0.getX() + b*v0.getZ();
    p2 = -a*v2.getX() + b*v2.getZ();
    
    if (p0<p2){
        minimo=p0;
        maximo=p2;
    }
    else{
        minimo=p2;
        maximo=p0;
    }
    rad = fa*boxHalfSize().getX() + fb* boxHalfSize().getZ();
    
    if (minimo>rad || maximo<-rad) return false;
    
    return true;
}

private boolean axisTest_Y1(double a,double b,double fa,double fb){
    p0 = -a*v0.getX() + b*v0.getZ();
    p1 = -a*v1.getX() + b*v1.getZ();
    
    if (p0<p1){
        minimo=p0;
        maximo=p1;
    }
    else{
        minimo=p1;
        maximo=p0;
    }
    rad = fa*boxHalfSize().getX() + fb* boxHalfSize().getZ();
    
    if (minimo>rad || maximo<-rad) return false;
    
    return true;
}

private boolean axisTest_Z12(double a,double b,double fa,double fb){
    p1 = a*v1.getX() - b*v1.getY();
    p2 = a*v2.getX() - b*v2.getY();
    
    if (p2<p1){
        minimo=p2;
        maximo=p1;
    }
    else{
        minimo=p1;
        maximo=p2;
    }
    rad = fa*boxHalfSize().getX() + fb* boxHalfSize().getY();
    
    if (minimo>rad || maximo<-rad) return false;
    
    return true;
}

private boolean axisTest_Z0(double a,double b,double fa,double fb){
    p0 = a*v0.getX() - b*v0.getY();
    p1 = a*v1.getX() - b*v1.getY();
    
    if (p0<p1){
        minimo=p0;
        maximo=p1;
    }
    else{
        minimo=p1;
        maximo=p0;
    }
    rad = fa*boxHalfSize().getX() + fb* boxHalfSize().getY();
    
    if (minimo>rad || maximo<-rad) return false;
    
    return true;
}

public void out () {
        System.out.print ("Coordenadas minimas: ");
        min.out();
        System.out.print ("Coordenadas maximas: ");
        max.out();
    }

}