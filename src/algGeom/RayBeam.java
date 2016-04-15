/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algGeom;
import java.util.ArrayList;
import java.util.Random;
import javax.media.opengl.*;

public class RayBeam {
    public ArrayList<Ray3d> rays;
    
    public RayBeam (Vect3d ini, Vect3d fin, double radio, int densidad) {
        double x,y,z;
        rays = new ArrayList<Ray3d>();
        
        Random aleatorio = new Random (BasicGeom.semilla);
        for(int i = 0; i<densidad; i++ ){
            x = (((aleatorio.nextDouble()%2)-1)*radio) + fin.getX(); //Da una posicion aleatoria en un radio
            y = (((aleatorio.nextDouble()%2)-1)*radio) + fin.getY();
            z = (((aleatorio.nextDouble()%2)-1)*radio) + fin.getZ();
            rays.add(new Ray3d(ini, new Vect3d(x,y,z)));
        }
    }   
    public void DrawRayBeam(GL g){
        DrawRay3d dr;
        for (int i=0; i < rays.size();i++){
            dr = new DrawRay3d(rays.get(i));
            dr.drawObject(g);
        }
    }
    
    public void DrawRayBeam(GL g,float R, float G, float B){
        DrawRay3d dr;
        for (int i=0; i < rays.size();i++){
            dr = new DrawRay3d(rays.get(i));
            dr.drawObjectC(g,R,G,B);
        }
    }
}
