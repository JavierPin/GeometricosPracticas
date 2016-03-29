package algGeom;

import math.geom2d.Point2D;
import math.geom2d.conic.Circle2D;
import math.geom2d.line.StraightLine2D;
import math.geom2d.line.LineSegment2D;
import math.geom2d.polygon.Polygon2D;
import java.util.ArrayList;
import math.geom2d.polygon.convhull.*;


/**
 *
 * @author lidia
 */
public class JavaGeom1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        // Create some points
        Point2D p1 = new Point2D(40, 30);
        Point2D p2 = new Point2D(180, 120);
        Point2D p3 = new Point2D(20, 120);
        Point2D p4 = new Point2D(40, 140);

        // Create a circle
        Circle2D circle1 = new Circle2D(80, 120, 20);

        // Create an edge
        LineSegment2D edge1 = new LineSegment2D(p3, p4);

        // Create some lines
        StraightLine2D line1 = new StraightLine2D(p1, p2);
        StraightLine2D line2 = StraightLine2D.createMedian(p3, p4); 
        
        Polygon2D poli;
        // definir una colección de puntos 
        int nPoints = 20;
        ArrayList<Point2D> puntos = new ArrayList<>(nPoints);
        for(int p=0; p<nPoints; p++)
            puntos.add(new Point2D(
                    Math.random()*200+100, Math.random()*200+100)); 
        ConvexHull2D calculator = new GrahamScan2D();
        poli = calculator.convexHull(puntos);
        // System.out.print(poli.area());
        double areapoli = poli.area();
        System.out.print(areapoli);
        
        
    }
    
}
