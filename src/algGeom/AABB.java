package algGeom;

import java.util.ArrayList;

public class AABB {

    Vect3d min; //menor x,y,z
    Vect3d max; //max x, y, z

public AABB (Vect3d menor, Vect3d mayor){
    min = menor;
    max = mayor;
}

public AABB (ArrayList<Vect3d> v){
    double x1,x2,y1,y2,z1,z2;
    
    x1=BasicGeom.INFINITO;
    x2=(-BasicGeom.INFINITO);
    y1=BasicGeom.INFINITO;
    y2=(-BasicGeom.INFINITO);
    z1=BasicGeom.INFINITO;
    z2=(-BasicGeom.INFINITO);
    
    for(int i=0;i<v.size();i++){
        if(v.get(i).getX()<x1){
            x1=v.get(i).getX();
        }
        if(v.get(i).getY()<y1){
            y1=v.get(i).getY();
        }
        if(v.get(i).getZ()<z1){
            z1=v.get(i).getZ();
        }
        if(v.get(i).getX()>x2){
            x2=v.get(i).getX();
        }
        if(v.get(i).getY()>y2){
            y2=v.get(i).getY();
        }
        if(v.get(i).getZ()>z2){
            z2=v.get(i).getZ();
        }
    }
    min = new Vect3d(x1,y1,z1);
    max = new Vect3d(x2,y2,z2);
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
