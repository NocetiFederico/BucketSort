package datos;

import java.util.ArrayList;
import java.util.Random;

public class FuncionesSecuenciales {
	
	public static void bucketSort(int[] arr) {
		// Numero de buckets
		int n = 10; 
		
		@SuppressWarnings("unchecked")
		//Creo una lista de buckets con 10 posiciones
		ArrayList<Integer>[] buckets = new ArrayList[n];

		// Creo los buckets vacios
		for (int i = 0; i < n; i++) {
			buckets[i] = new ArrayList<Integer>();
		}
    
        // Encontrar el valor máximo en el arreglo
		// Inicializar maxValue con el valor minimo posible de un entero
        int maxValue = Integer.MIN_VALUE;
        // Iterar a traves de cada numero en el arreglo
        for (int num : arr) {
        	// Si el numero actual es mayor que maxValue
            if (num > maxValue) {
            	// Actualizar maxValue al numero actual
                maxValue = num;
            }
        }
		
	    // Distribuir los elementos del arreglo en los buckets
        // Iterar a traves de cada numero en el arreglo
        for (int num : arr) {
        	// Calcular el indice del bucket para el numero actual
            int bucketIndex = (num * n) / (maxValue + 1);
            // Añadir el numero al bucket correspondiente
            buckets[bucketIndex].add(num);
        }
		
		// Ordenar los elementos de cada bucket 
        // Iterar a traves de cada bucket
		for (int i = 0; i < n; i++) {
			// Ordenar los elementos del bucket actual utilizando insertionSort
			insertionSort(buckets[i]);
					
		}
		
		// Inicializar un indice para fusionar los buckets en el arreglo original
		int index = 0;
		// Iterar a traves de cada bucket en la lista de buckets
		for (ArrayList<Integer> b : buckets) {
			// Iterar a traves de cada numero en el bucket actual
			for (int num : b) {
				// Colocar el numero del bucket en la posicion correspondiente del arreglo original
		        arr[index++] = num;
		    }
		}
		      
		       
		// Imprimir el arreglo ordenado
		
		/*System.out.print("Arreglo ordenado secuencial: ");
		for (int num : arr) {
		    System.out.print(num + " ");
		}
		*/
		
  }
	
	//Funcion para ordenar los buckets
    public static void insertionSort(ArrayList<Integer> buckets) {
    	// Iterar desde el segundo elemento hasta el final del bucket
        for (int i = 1; i < buckets.size(); ++i) {
        	// Almacenar el valor actual como clave
            int key = buckets.get(i);
            // Inicializar j como el indice anterior al actual
            int j = i - 1;
            // Mientras j sea valido y el elemento en bucket[j] sea mayor
            // que la clave
            while (j >= 0 && buckets.get(j) > key) {
            	// Desplazar el elemento en bucket[j] una posicion hacia adelante
                buckets.set(j + 1, buckets.get(j));
                // Decrementar j
                j--;
            }
            // Colocar la clave en su posicion correcta
            buckets.set(j + 1, key);
        }
    }
	

    // Funcion para generar un array aleatorio
	public static int[] generarArrayAleatorio(int tam, int min, int max) {
        // Declaración del array
        int[] arr = new int[tam];
        
        // Generación de números aleatorios
        Random random = new Random();
        // Bucle para llenar el array de numeros aleatorios
        for (int i = 0; i < tam; i++) {
        	// Genera un numero aleatorio entre 'min' y 'max' (inclusive)
        	// y lo asigna a la posicion 'i' del array
            arr[i] = random.nextInt(max - min + 1) + min;
        }
        // Devuelve el array lleno de numeros aleatorios
        return arr;
    }



}