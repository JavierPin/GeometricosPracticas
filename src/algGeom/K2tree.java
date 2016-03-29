package algGeom;

import java.util.*;
//import math.geom2d.*;
import math.geom2d.point.KDTree2D;
import math.geom2d.Point2D;
import math.geom2d.Box2D;

public class K2tree {
    private KDTree2D kTree;
    
    public K2tree(PointCloud pc){
        for(int i=0; i<pc.tama();i++)
            kTree.add(new Point2D (pc.getPunto(i).getX(),
                    pc.getPunto(i).getY()));        
    }
    
    public void add (Point p){
        kTree.add(new Point2D (p.getX(),p.getY()));
    }
    
    public Point masCercano(Point p){
        /*Para quien no le guste lo que hago (a mi tampoco):
        Empiezo a pensar que en vez de un kDtree2d, que usa point2d, tendria 
        que usar mi propia estructura en base a Points y usar ocasionalmente los
        metodos de kdtree2d. Asi me ahorraria todas las conversiones que hago.
        Pero me chirria la idea de estar creandome un kdtree cada vez que 
        necesite un metodo.
        */
        Point2D p2 = new Point2D (p.getX(),p.getY());
        return new Point (kTree.nearestNeighbor(p2).getX(),
                          kTree.nearestNeighbor(p2).getY());
    }
    public PointCloud busquedaRango(double xmin, double ymin,double xmax,double ymax){
        PointCloud pc = new PointCloud();
        //obtengo la colecciond e puntos en el rango (Points2D)
        Collection<Point2D> vp = kTree.rangeSearch(new Box2D(xmin,ymin,xmax,ymax));
            
        //recorro la coleccion de puntos y los paso a mi nuve de puntos
        for(Iterator<Point2D> i = vp.iterator(); i.hasNext();){
            Point2D p = i.next();
            pc.AddPunto(new Point (p.getX(),p.getY()));
        }
        
        return pc;
    }
    
}
