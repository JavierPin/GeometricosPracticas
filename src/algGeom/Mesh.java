

package algGeom;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
    
public final class Mesh {

	ArrayList<Face> caras;
	ArrayList<Vect3d> vertices;
	boolean setNormal;
	
		
  /**
   *  Construye una mesh a partir de un fichero Obj 
   * @param filename: nombre del objeto
   * @throws IOException 
   */      
	public Mesh(String filename) throws IOException {		
            caras = new ArrayList<Face>();
            vertices = new ArrayList<Vect3d>();
	    setNormal = false;
	    String obj = "obj";
		
	     File archivo;
	     FileReader fr;
	     BufferedReader reader;
	     
	     archivo = new File (filename);
             fr = new FileReader (archivo);
             reader = new BufferedReader(fr);
         	              
             String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
             if (! extension.equals(obj)) {
                    System.out.println("Solo se soportan modelos en obj ");
             } else {	
                loadobject(reader);
                setNormales();
                setNormal = true;
            }  
	  }
	
	/**
         * 
         * @return una lista de triángulos del modelo
         */
	public ArrayList<Triangle3d> getTriangulos (){
		ArrayList<Triangle3d> triangulos = new ArrayList<Triangle3d>();
		for (int i=0; i<caras.size(); i++){
			Face f = caras.get(i);
			Vect3d a = vertices.get(f.v1-1);
			Vect3d b = vertices.get(f.v2-1);
			Vect3d c = vertices.get(f.v3-1);
			triangulos.add ( new Triangle3d (a,b,c));
		}
		return triangulos;
	}
	
        /**
         * 
         * @return Devuelve las coordenadas de todos los vértices de los triángulos en la mesh 
         */
        
	public double[] getVerticesTriangulos (){
		double [] tri = new double [caras.size()*9];
		for (int i=0; i<caras.size(); i++){
			Face f = caras.get(i);
			Vect3d a = vertices.get(f.v1-1); tri[i*9]=a.x; tri[i*9+1]=a.y; tri[i*9+2]=a.z;
			Vect3d b = vertices.get(f.v2-1); tri[i*9+3]=b.x; tri[i*9+4]=b.y; tri[i*9+5]=b.z;
			Vect3d c = vertices.get(f.v3-1); tri[i*9+6]=c.x; tri[i*9+7]=c.y; tri[i*9+8]=c.z;			
		}
		return tri;
	}
        
        /**
         * 
         * @return devuelve las coordenadas de los vértices de la mesh sin ordenación aparente 
         */
	
	public double[] getVertices (){
		double [] vertex  = new double [3*vertices.size()];
		int j = 0;
		for (int i=0; i<vertices.size(); i++){
			vertex[j++] = vertices.get(i).x;
			vertex[j++] = vertices.get(i).y;
			vertex[j++] = vertices.get(i).z;
			
		}
		return vertex;
	}
	
        /**
         * 
         * @return los índices de las caras
         */

	public int[] getIndiceFaces (){
		int [] faces  = new int [3*caras.size()];
		int j = 0;
		for (int i=0; i<caras.size(); i++){
			faces[j++] = caras.get(i).v1;
			faces[j++] = caras.get(i).v2;
			faces[j++] = caras.get(i).v3;
		}
		return faces;
	}

	
	/**
         * 
         * @param i índice del triángulo
         * @return el Triangulo con dicho índice
         */
	public Triangle3d getTriangulo (int i){
			Face f = caras.get(i);
			Vect3d a = vertices.get(f.v1-1);
			Vect3d b = vertices.get(f.v2-1);
			Vect3d c = vertices.get(f.v3-1);
			return new Triangle3d (a,b,c);
	}
	
	/**
         * establece la normal en todos los triángulos
         */
        
	public void setNormales () {
		for (int i=0; i<caras.size(); i++){
			Face f = caras.get(i);
			Vect3d a = vertices.get(f.v1-1);
			Vect3d b = vertices.get(f.v2-1);
			Vect3d c = vertices.get(f.v3-1);
			Triangle3d t = new Triangle3d (a,b,c);
			f.setNormal(t.Normal());
		}
		setNormal = true;
	}
	
        /**
         * 
         * @return todas las normales según la disposición de los triángulos
         */
        
	public double[] getNormales () {
		double [] nor = new double [3*caras.size()];
		if (!setNormal) 
			setNormales();
		for (int i=0; i<caras.size(); i++){
			Face f = caras.get(i);
			Vect3d b = f.getNormal();
			nor[i*3] = b.x; nor[i*3+1] = b.y; nor[i*3+2] = b.z;
		}
		return nor;
	}

    /**
     *
     * @param r el rayo que choca
     * @param p el punto de intersección si existe
     * @param t el triángulo de interseccion si existe
     * @return true si hay intersección con el modelo
     */
    public boolean rayTraversal (Ray3d r, Vect3d p, Triangle3d t){
     ///XXXXXX
            return true; 
        }
	
    /**
     *
     * @param lr el conj. de rayos 
     * @param lp el conj. de puntos de intersección si existe
     * @param lt el conj. de triángulos de interseccion si existe
     * @return true si hay intersección con el modelo
     */
    public int rayCast (ArrayList<Ray3d> lr, ArrayList<Vect3d> lp, ArrayList<Triangle3d> lt){
      // XXXX
            return 0; 
        }
    
    
    
        /**
         * carga el modelo de la variable fichero
         * @param br variable fichero 
         */
	    
	    public void loadobject(BufferedReader br) {
	        String line = "";
	 
			try {
				while ((line = br.readLine()) != null) {
					line = line.trim();
					line = line.replaceAll("  ", " ");
					if (line.length() > 0) {
						if (line.startsWith("v ")) {
							float[] vert = read3Floats(line);
							Vect3d punto = new Vect3d (vert[0],vert[1],vert[2]);
							vertices.add(punto);
						}
						else if (line.startsWith("vt")) {
							
							continue;
							
						}
						else if (line.startsWith("vn")) { 
							
							continue;
						}
						else if (line.startsWith("f ")) {
							int[] faces = read3Integer(line);
							caras.add(new Face (faces));
						}
	                    else if (line.startsWith("g ")) {
	                        continue;
	                    }
	                    else if (line.startsWith("usemtl")) {
	                        continue;
	                    }
	                    else if (line.startsWith("mtllib")) {
	                      continue;
	                    }
					}
				}
			} catch (Exception e) {
				System.out.println("GL_OBJ_Reader.loadObject() failed at line: " + line);
			}


			
			System.out.println("Lector de Obj: vértices " + vertices.size()
							+ " caras " + caras.size() );

	        
		}
	    
	   

	    public int getSizeCaras(){
	    	return caras.size();
	    }

	    public int getSizeVertices(){
	    	return vertices.size();
	    }

	   public ArrayList<Vect3d> getListaVertices(){
               return vertices;
           }
	    
	    private int[] read3Integer(String line)
		{
			try
			{
				StringTokenizer st = new StringTokenizer(line, " ");
				st.nextToken();   
				if (st.countTokens() == 2) { 
					return new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),0};
				} 
				else { 
					return new int[] {Integer.parseInt(st.nextToken()),
										Integer.parseInt(st.nextToken()),
										Integer.parseInt(st.nextToken())};
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("GL_OBJ_Reader.read3Floats(): error on line '" + line + "', " + e);
				return null;
			}
		}
	    
	    
	    private float[] read3Floats(String line)
		{
			try
			{
				StringTokenizer st = new StringTokenizer(line, " ");
				st.nextToken();   
				if (st.countTokens() == 2) { 
					return new float[] {Float.parseFloat(st.nextToken()),
										Float.parseFloat(st.nextToken()),
										0};
				}
				else {
					return new float[] {Float.parseFloat(st.nextToken()),
										Float.parseFloat(st.nextToken()),
										Float.parseFloat(st.nextToken())};
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("GL_OBJ_Reader.read3Floats(): error on line '" + line + "', " + e);
				return null;
			}
		}
	    
	    
	    public AABB getAABB() {
	        // reset min/max points
	        double minx = 9e99;
	        double miny = 9e99;
	        double minz = 9e99;
	        double maxx = 0;
	        double maxy = 0; 
	        double maxz = 0;
	        
                for (int v = 0; v < vertices.size(); v++) {
		    Vect3d vv =  vertices.get(v);
		    if (vv.x > maxx)  maxx = vv.x;
		    if (vv.y > maxy)  maxy = vv.y;
		    if (vv.z > maxz)  maxz = vv.z;
		    if (vv.x < minx)  minx = vv.x;
		    if (vv.y < miny)  miny = vv.y;
		    if (vv.z < minz)  minz = vv.z;
		}
		return new AABB(minx,miny,minz,maxx,maxy,maxz);
	    }
	    
}


