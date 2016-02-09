package algGeom;


import java.util.Random;

abstract public class BasicGeom {

  static final double CERO = 0.00001; /** evita problemas con difentes valores de 0.0*/
  static final double INFINITO = 99e99; /** para manejar infinito */
  static int RANGO = 100;  /** define el rango de trabajo; 2*RANGO es el ancho total*/

  static public boolean iguales (double a, double b){
      return (Math.abs(a-b)<CERO);
  }

  static Random semillaAleatoria = new Random ();
  static int semilla = semillaAleatoria.nextInt();
  static double min3 (double a, double b, double c){
     return (a<b ? (a<c?a:c ) : (b<c ? b : c));
  }

  static double max3 (double a, double b, double c){
     return (a>b ? (a>c?a:c ) : (b>c ? b : c));
  }


}
