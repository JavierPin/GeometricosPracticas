package algGeom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

//class ErrorAlGuardar extends Exception {}
//class ErrorAlLeerFichero extends Exception {}


public class Cloud3d {
    
    ArrayList<Vect3d> nubepuntos;

    public Cloud3d(){
        nubepuntos = new ArrayList<Vect3d>();
    }

    public Cloud3d(int tam){
        nubepuntos = new ArrayList<Vect3d>();
        if(tam > 0){
           
            Random aleatorio = new Random (40);

            double x, y, z;
            for(int i=0;i<tam;i++){
                x = aleatorio.nextDouble();
                //x = x * 2 * Geometria.RANGO - Geometria.RANGO;
                y = aleatorio.nextDouble();
                //y = y * 2 * Geometria.RANGO - Geometria.RANGO;
                z = aleatorio.nextDouble();
                //z = z * 2 * Geometria.RANGO - Geometria.RANGO;

                Vect3d p = new Vect3d (x,y,z);
                nubepuntos.add(p);
            }
        }
    }

    public int tama(){
        return nubepuntos.size();
    }

    public void limpia (){
    	nubepuntos.clear();
    }
    
//    public Cloud3d (Mesh m){
//        // constructor a partir de una mesh
//    }
    
    public Cloud3d(String nombre) throws ErrorAlLeerFichero{
        //Leemos el fichero de puntos
        //Cada linea del fichero tiene la siguiente sintaxis:
        //coordenadaX coordenadaY
        File archivo;
        FileReader fr = null;
        BufferedReader br;

        nubepuntos = new ArrayList<Vect3d>();
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File (nombre);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null){
                String[] coordenadas = linea.split(" ");
                Vect3d p = new Vect3d(Double.parseDouble(coordenadas[0]), Double.parseDouble(coordenadas[1]), Double.parseDouble(coordenadas[2]));
                nubepuntos.add(p);
            }
        }
        catch(IOException e){
            throw new ErrorAlLeerFichero();
        } catch (NumberFormatException e) {
            throw new ErrorAlLeerFichero();
        }

        finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{
                if(fr != null){
                   fr.close();
                }
            }catch (IOException e2){ }
        }
    }


    public void salvar(String nombre) throws ErrorAlGuardar{
        FileWriter fichero = null;
        PrintWriter pw;
        try
        {
            fichero = new FileWriter(nombre);
            pw = new PrintWriter(fichero);
            String linea;
            for (int i = 0; i < nubepuntos.size(); i++){
                //Cada linea del archivo estara con la siguiente sintaxis:
                //coordenadaX coordenadaY (separadas por un espacio)
                linea = Double.toString(nubepuntos.get(i).getX()) + " " 
                      + Double.toString(nubepuntos.get(i).getY()) + " " 
                	  + Double.toString(nubepuntos.get(i).getZ());
                pw.println(linea);
            }

        } catch (IOException e) {
            throw new ErrorAlGuardar();
        }

        finally {
           try {
                //Utilizamos el finally para asegurarnos que se cierra el fichero.
                if (null != fichero){
                   fichero.close();
                }
           } catch (IOException e2) { }
        }
    }


    public Vect3d getPunto(int pos){
        if((pos >= 0)&&(pos<nubepuntos.size())){
            return nubepuntos.get(pos);
        }
        return null;
    }

    public AABB getAABB (){

    	double minx=9e9, miny=9e9, minz=9e9;
    	double maxx=-9e9, maxy=-9e9, maxz=-9e9;
    	for (int i=0; i< nubepuntos.size(); i++){
    		if (nubepuntos.get(i).getX() < minx) minx = nubepuntos.get(i).getX();
    		if (nubepuntos.get(i).getY() < miny) miny = nubepuntos.get(i).getY();
    		if (nubepuntos.get(i).getZ() < minz) minz = nubepuntos.get(i).getZ();
    		if (nubepuntos.get(i).getX() > maxx) maxx = nubepuntos.get(i).getX();
    		if (nubepuntos.get(i).getY() > maxy) maxy = nubepuntos.get(i).getY();
    		if (nubepuntos.get(i).getZ() > maxz) maxz = nubepuntos.get(i).getZ();
    	}
    	Vect3d min = new Vect3d(minx,miny,minz);
    	Vect3d max = new Vect3d(maxx,maxy,maxz);   	
    	AABB ab = new AABB(min,max);
    	return ab;
    }
    
   
}
