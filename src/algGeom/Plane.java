package algGeom;

public class Plane {

	   Vect3d a,b,c; //tres puntos cualquiera del plano  

	   /**
	    * 
	    * @param p en pi =  p+u * lambda + v * mu -> r en los puntos (R,S,T) 
	    * @param u en pi =  p+u * lambda + v * mu -> d en los puntos (R,S,T)
	    * @param v en pi =  p+u * lambda + v * mu -> t en los puntos (R,S,T)
	    * @param sonPuntos =     false entonces los parámetros son p+u * lambda + v * mu sino son los puntos (R,S,T)
	    */
		public Plane (Vect3d p, Vect3d u, Vect3d v, boolean sonPuntos) {
			if (!sonPuntos) { // son vectores de la forma: pi =  p+u * lambda + v * mu 
				a = p;
				b = u.suma(a);
				c = v.suma(a);
			} else { // son 3 puntos del plano cualesquiera 
				a = p;
				b = u;
				c = v;
			}
		}
		

		/**
		 * 
		 * @return el valor de A en  AX+BY+CZ+D = 0;
         * 
		 */
		public double getA(){
			
			return (BasicGeom.determinante2x2(b.getY()-a.getY(), b.getZ()-a.getZ(), c.getY()-a.getY(), c.getZ()-a.getZ()));	
		}	
		/**
		 * 
		 * @return el valor de B en  AX+BY+CZ+D = 0;
         * 
		 */
		public double getB(){
			
			return (BasicGeom.determinante2x2(b.getZ()-a.getZ(), b.getX()-a.getX(), c.getZ()-a.getZ(), c.getX()-a.getX()));	
		}	
		
		/**
		 * 
		 * @return el valor de C en  AX+BY+CZ+D = 0;
         * 
		 */
		public double getC(){
			
			return (BasicGeom.determinante2x2(b.getX()-a.getX(), b.getY()-a.getY(), c.getX()-a.getX(), c.getY()-a.getY()));
		}	
		
		/**
		 * 
		 * @return el valor de D en  AX+BY+CZ+D = 0;
         * 
		 */
		public double getD(){
			
			return (-1)*(getA()*a.getX()+getB()*a.getY()+getC()*a.getZ());
		}

				

		/**
		 * 
		 * @return el vector normal formado por (A,B,C) de la ecuación Ax+By+Cz+D = 0
		 */
		public Vect3d getNormal (){
			double A = getA();
			double B = getB();
			double C = getC();
			return new Vect3d (A,B,C);
		}
		

		
	    /** Muestra en pantalla los valores los valores de Plano */
	    public void out () {
	        System.out.print ("Plano->a: ");
	        System.out.println (a);
	        System.out.print ("Plano->b: ");
	        System.out.println (b);
	        System.out.print ("Plano->c: ");
	        System.out.println (c);
	    }

		
	}
	

