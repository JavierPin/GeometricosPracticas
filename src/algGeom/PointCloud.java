package algGeom;



import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class ErrorAlGuardar extends Exception {
	private static final long serialVersionUID = 1L;
}	
	
class ErrorAlLeerFichero extends Exception {

	private static final long serialVersionUID = 2L;
}


public class PointCloud {
    
    ArrayList<Point> nubepuntos;

    /**
     * Constructor vacío, sin tamaño predeterminado, debe llenarse con addPunto()
     */
    public PointCloud(){
        nubepuntos = new ArrayList<Point>();
    }
    
    /**
     * 
     * @param tam : crea una nube de puntos aleatoria
     */

    
    public PointCloud(int tam){
        nubepuntos = new ArrayList<Point>();
        if(tam > 0){
           
            Random aleatorio = new Random(BasicGeom.semilla);
            
            double x, y;
            for(int i=0;i<tam;i++){
                x = aleatorio.nextDouble();
                x = x * 2 * BasicGeom.RANGO - BasicGeom.RANGO;
                y = aleatorio.nextDouble();
                y = y * 2 * BasicGeom.RANGO - BasicGeom.RANGO;

                Point p = new Point (x,y);
                nubepuntos.add(p);
            }
        }
    }

    /**
     * Añade un nuevo punto a la nube de puntos
     * @param p: punto de entrada a la nube
     */
    
    public void AddPunto (Point p){
    	nubepuntos.add(p);
    }
    
    /** 
     * 
     * @return devuelve el tamaño de la nube
     */
    public int tama(){
        return nubepuntos.size();
    }

    
    /**
     * Constructor de la nube a partir de un fichero con el formato
     * x0 y0
     * x1 y1
     * ...
     * xn-1 yn-1
     * 
     * @param nombre del fichero
     * @throws ErrorAlLeerFichero si no existe el fichero se lanza una excepción
     */

    public PointCloud(String nombre) throws ErrorAlLeerFichero, IOException{
    	BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(nombre));
        
        String linea=null;
        
        for (int i = 0;i < this.nubepuntos.size();i++) {
            if(i>0){
                bw.newLine();
            }
           linea=(Double.toString(nubepuntos.get(i).getX())+","+Double.toString(nubepuntos.get(i).getY()));
           bw.write(linea);            
        }

        bw.close();
    
    }


    /**
     * Guarda la nube de puntos en fichero con el mismo formato que utiliza el constructor
     * @param nombre del fichero 
     * @throws ErrorAlGuardar, se lanza una excepción si exite algún problema al abrir el fichero
     */
    public void salvar(String nombre) throws ErrorAlGuardar,IOException{
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(nombre));
        
        String linea=null;
        
        for (int i = 0;i < nubepuntos.size();i++) {
            if(i>0){
                bw.newLine();
            }
           linea=(Double.toString(nubepuntos.get(i).getX())+","+Double.toString(nubepuntos.get(i).getY()));
           bw.write(linea);            
        }

        bw.close();
    }


    public Point getPunto(int pos){
        if((pos >= 0)&&(pos<nubepuntos.size())){
            return nubepuntos.get(pos);
        }
        return null;
    }

  


    
}
