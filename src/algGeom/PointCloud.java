package algGeom;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
           
            Random aleatorio = new Random (BasicGeom.semilla);

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

    public PointCloud(String nombre) throws ErrorAlLeerFichero, FileNotFoundException{
    	nubepuntos = new ArrayList<Point>();
        
        int pos=0;

        Scanner scanner = new Scanner(new FileReader(nombre));

        while (scanner.hasNextLine()) {

            Scanner line = new Scanner(scanner.nextLine());
            line.useDelimiter(",");

            while (line.hasNext()){

                nubepuntos.add(new Point(Double.parseDouble(line.next()),Double.parseDouble(line.next())));
                pos++;
            }
        }
        
        scanner.close();            
    
    }


    /**
     * Guarda la nube de puntos en fichero con el mismo formato que utiliza el constructor
     * @param nombre del fichero 
     * @throws ErrorAlGuardar, se lanza una excepción si exite algún problema al abrir el fichero
     */
    public void salvar(String nombre) throws ErrorAlGuardar, IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(nombre));
        
        for (int i=0; i<nubepuntos.size(); i++) {
            bw.write(nubepuntos.get(i).x+","+nubepuntos.get(i).y+ "\r");
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
