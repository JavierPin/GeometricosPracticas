package algGeom;

public class AABB {

    Vect3d min; //menor x,y,z
    Vect3d max; //max x, y, z

public AABB (Vect3d menor, Vect3d mayor){
    min = menor;
    max = mayor;
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
