package algGeom;

public class Vect3d {

        double x,y,z;

        public Vect3d(double aa, double bb,double cc){
                x = aa;
                y = bb;
                z = cc;
        }

       public Vect3d( Vect3d v){
            x = v.x;
            y = v.y;
            z = v.z;
        }
       
       public Vect3d(Point_dt p){
           x = p.x;
           y = p.y;
           z = p.z;  
       }


        public Vect3d(){
            x = 0;
            y = 0;
            z = 0;
        }


        public double getX(){
                return x;
        }

        public double getY(){
                return y;
        }

        public double getZ(){
                return z;
        }
        
        public void setX(double xx){
            
            x=xx;
        }
        
        public void setY(double yy){
            
            y=yy;
        }
        
        public void setZ(double zz){
            
            z=zz;
        }

        public double[] getVert(){
                double[] vt = {x,y,z};
                return vt;
        }

        public void setVert(double aa,double  bb,double cc){
                x = aa;
                y = bb;
                z = cc;
        }

        /** result = this - b */
        public Vect3d resta (Vect3d b){
            return new Vect3d (x-b.getX(), y-b.getY(), z-b.getZ());

        }

        /** result = this + b */
        public Vect3d suma (Vect3d b){
            return new Vect3d (x+b.getX(), y+b.getY(), z+b.getZ());

        }


        /** producto por un escalar result = this * valorEscalar */
        public Vect3d prodEscalar (double val){
            return new Vect3d (x*val, y*val, z*val);
        }

        /** producto escalar result = this.dot(b) */
        public double dot(Vect3d v){
            return (x*v.getX()+y*v.getY()+z*v.getZ());
        }


        /** devuelve this X b (producto cruzado) */
        public Vect3d XProduct (Vect3d b){
            return new Vect3d (y * b.getZ() - z * b.getY(),
                               z * b.getX() - x * b.getZ(),
                               x * b.getY() - y * b.getX());
        }

        /** devuelve la longitud del vector */
        public double modulo (){
            return Math.sqrt(x*x+y*y+z*z);
        }
        
        /** busca el maximo valor del vector*/
        public double buscaMaximo(){
            if(x >= y && x >= z){
                return x;
            }else if (y >= z){
                return y;
            }else{
                return z;
            }
        }
        /** busca el minimo valor del vector*/
        public double buscaMinimo(){
            if(x <= y && x <= z){
                return x;
            }else if (y <= z){
                return y;
            }else{
                return z;
            }
        }
        public double findMin(){
            
            if(x<y && x<z){
                return x;
            }
            else if (y<z){
                return y;
            }
            return z;     
        }
        
        public double findMax(){
            
            if(x>y && x>z){
                return x;
            }
            else if (y>z){
                return y;
            }
            return z; 
            
        }
        
        public double distancia(Vect3d p){
            
            return Math.sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)+(z-p.z)*(z-p.z));
            
        }
        
        /** Muestra en pantalla los valores de las coordenadas del Point. */
        public void out () {
            System.out.print ("Coordenada x: ");
            System.out.println (x);
            System.out.print ("Coordenada y: ");
            System.out.println (y);
            System.out.print ("Coordenada z: ");
            System.out.println (z);
        }

}


