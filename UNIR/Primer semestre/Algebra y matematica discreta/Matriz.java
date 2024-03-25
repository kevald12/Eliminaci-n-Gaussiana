package matriz;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class Matriz { // la clase

  public static void main(String args[]) { // metodo main
    Scanner Entrada = new Scanner(System.in); // definimos el scanner

    int nFilas = 0, nColum = 0; // definimos las variables que guaerdaran el número de filas y columnas de la matriz

    nFilas =
      Integer.parseInt(
        JOptionPane.showInputDialog("Inserte el numero de Filas: ")
      ); // mandamos la ventana para que se ingreso las filas
    nColum =
      Integer.parseInt(
        JOptionPane.showInputDialog("Inserte el numero de Columnas: ")
      ); // mandamos la ventana para que se ingrese las columnas

    double M[][] = new double[nFilas][nColum];
    double Mb[][] = new double[nFilas][1]; // definimos la matriz

    // se llena la matriz M con los valores proporcionados
    for (int i = 0; i < nFilas; i++) { // Ciclo for, se hace mientras la variable i sea menor a el número de filas y así preguntamos por pantalla el número que deberia ir en la posicion
      for (int j = 0; j < nColum; j++) {
        System.out.print("Matriz [" + i + "][" + j + "] : ");
        M[i][j] = Entrada.nextDouble(); // obtenemos los números y los asignamos a una posición dentro de la matriz
      }
    }
    // Llenar la matriz de resultados Mb
    for (int i = 0; i < nFilas; i++) {
      System.out.print("Matriz de resultado [" + i + "][0] : ");
      Mb[i][0] = Entrada.nextDouble();
    }

    // Mostrar la matriz M
    System.out.println("\n La matriz es : ");
    for (int i = 0; i < nFilas; i++) {
      for (int j = 0; j < nColum; j++) {
        System.out.printf("%8.1f", M[i][j]);
      }
      System.out.println();
    }

    // Mostrar la matriz total (M junto con Mb)
    System.out.println("\n La matriz Total es : ");
    for (int i = 0; i < nFilas; i++) {
      for (int j = 0; j < nColum; j++) {
        System.out.printf("%8.1f", M[i][j]);
      }
      System.out.printf(" |%5.1f\n", Mb[i][0]);
    }

    // Vamos a hacer la eliminación gaussiana por el metodo pivotaje parcial

    for (int k = 0; k < Math.min(nFilas, nColum); k++) {
      // Encontrar el pivote máximo en la columna k
      double maxPivot = Math.abs(M[k][k]);
      int maxIndex = k;
      for (int i = k + 1; i < nFilas; i++) { //se corre un ciclo for que recorra la matriz M y encuentre el maximo numero de la columna k
        if (Math.abs(M[i][k]) > maxPivot) {
          maxPivot = Math.abs(M[i][k]);
          maxIndex = i;
        }
      }
      // Intercambiar filas si es necesario
      if (maxIndex != k) { //si el numero que esta de primero en la columna es menor se va a intercambiar toda la columna
        double[] temp = M[k];
        M[k] = M[maxIndex];
        M[maxIndex] = temp;
        double tempMb = Mb[k][0];
        Mb[k][0] = Mb[maxIndex][0];
        Mb[maxIndex][0] = tempMb;
      }
      // Eliminación Gaussiana
      for (int i = k + 1; i < nFilas; i++) {
        double factor = M[i][k] / M[k][k];
        for (int j = k + 1; j < nColum; j++) {
          M[i][j] -= factor * M[k][j];
        }
        Mb[i][0] -= factor * Mb[k][0];
      }
    }

    // Mostrar la matriz M después de la eliminación gaussiana
    System.out.println("\nLa matriz despues de la eliminacion gaussiana:");
    for (int i = 0; i < nFilas; i++) {
      for (int j = 0; j < nColum; j++) {
        System.out.printf("%8.1f", M[i][j]);
      }
      System.out.printf(" |%5.1f\n", Mb[i][0]);
    }

    // Resolución del sistema triangular
    double[] soluciones = new double[Math.min(nFilas, nColum)];
    for (int i = Math.min(nFilas, nColum) - 1; i >= 0; i--) {
      double sum = 0.0;
      for (int j = i + 1; j < Math.min(nFilas, nColum); j++) {
        sum += M[i][j] * soluciones[j];
      }
      soluciones[i] = (Mb[i][0] - sum) / M[i][i];
    }

    // Resultado de la matriz despues de la eliminacion gaussiana
    System.out.println("\nResultado Matriz por debajo de la diagonal:");
    for (int i = 0; i < nFilas; i++) {
      for (int j = 0; j < nColum; j++) {
        if (j < i) {
          M[i][j] = 0; //Se establecen los valores por debajo de la diagonal a 0
        }
        System.out.printf("%8.1f", M[i][j]);
      }
      System.out.println();
    }

    // Resultados despues de la eliminacion
    System.out.println("\nLos valores de las incognitas son:");
    for (int i = 0; i < Math.min(nFilas, nColum); i++) {
      if (Math.abs(soluciones[i] - Math.round(soluciones[i])) < 1e-10) {
        System.out.println("x" + (i + 1) + ": " + Math.round(soluciones[i]));
      } else {
        System.out.println(
          "x" + (i + 1) + ": " + String.format("%.2f", soluciones[i])
        );
      }
    }
  }
}
