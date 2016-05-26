package algGeom;
import java.util.ArrayList;
import java.util.Vector;
import javax.media.opengl.GL;

public class DrawTin {
    
    Tin tin;
    TNetwork tnet;
    Vector<TriangleTin> triangulos;
    ArrayList<TriangleTin> arraytriangulos = new ArrayList<TriangleTin>();
    
    public DrawTin(Tin t){
        
        tin = t;
        triangulos = tin.triangulos;
        
    }
    
    public DrawTin(TNetwork t){
        
        tnet=t;
        arraytriangulos = tnet.triangulos;
    }

    public void drawObject(GL g){
        for (int i=0;i<arraytriangulos.size();i++){
            
            DrawTriangle3d tri = new DrawTriangle3d(arraytriangulos.get(i));
            tri.drawObject(g);
        }
    }
    
    public void drawObjectC(GL g, float R, float G, float B){
        for (int i=0;i<arraytriangulos.size();i++){
            
            DrawTriangle3d tri = new DrawTriangle3d(arraytriangulos.get(i));
            tri.drawObjectC(g,R,G,B);
        }
    }
    
    public void drawObjectMap(GL g){
        
        for (int i=0;i<arraytriangulos.size();i++){
            
            Vect3d color = colorAltura(arraytriangulos.get(i),tnet.ZMin, tnet.ZMax);
            
            DrawTriangle3d tri = new DrawTriangle3d(arraytriangulos.get(i));
            tri.drawObjectC(g,(float)color.getX(), (float)color.getY(), (float)color.getZ());
        }
        
    }
    
    private Vect3d colorAltura(TriangleTin t, double min, double max){
        double media, porcentaje, r, g, b;

        //media = (t.a.getZ() + t.b.getZ() + t.c.getZ())/3; //Aritmetica
        media = 3/(1/t.getA().getZ() + 1/t.getB().getZ() + 1/t.getC().getZ()); //Armonica
        //media =Math.sqrt(Math.pow(t.a.getZ(), 2) + Math.pow(t.b.getZ(), 2) +Math.pow(t.c.getZ(), 2)); //Geometrica

        
        porcentaje = media/(max-min);
        g = ((1 - porcentaje)*7/10);
        r = (porcentaje/2);
        b = 0;
        
        
        return new Vect3d(r,g,b);
    }
    
}
