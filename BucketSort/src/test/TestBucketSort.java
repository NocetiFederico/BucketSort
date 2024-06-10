package test;

import java.util.Arrays;

import datos.FuncionesConcurrentes;
import datos.FuncionesSecuenciales;

public class TestBucketSort {

	public static void main(String[] args) {
		
		
		int tam = 100000;
		int [] arr = FuncionesSecuenciales.generarArrayAleatorio(tam, 0, 50000);
        int arrayOrdenarSecuencial[] = Arrays.copyOf(arr, tam);
        int arrayOrdenarConcurrente[] = Arrays.copyOf(arr, tam);
        
        
        /*System.out.println("\n-------------------------------------------------------------------------------");
		System.out.print("Arreglo original: ");
		for (int num : arr) {
		    System.out.print(num + " ");
		}
        System.out.println("\n-------------------------------------------------------------------------------");
        */
        
	    System.out.println("\n --> Voy a hacer bucketSort Secuencial!--\n");
	    
	    double tiempo = System.nanoTime();
	    
	    FuncionesSecuenciales.bucketSort(arrayOrdenarSecuencial);
	  
		double tiemf = System.nanoTime() - tiempo;
		System.out.println("\n - Termine en = " + tiemf/1000000 + "\n");

	    System.out.println("\n --> Voy a hacer bucketSort Concurrente!--\n");
	    
	    tiempo = System.nanoTime();
	    
	    FuncionesConcurrentes.bucketSort(arrayOrdenarConcurrente);
	  
		tiemf = System.nanoTime() - tiempo;
		System.out.println("\n - Termine en = " + tiemf/1000000 + "\n");	

	}

}
