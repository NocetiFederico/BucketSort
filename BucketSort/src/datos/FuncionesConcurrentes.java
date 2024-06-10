// Bucket sort in Java
package datos;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class FuncionesConcurrentes {
	
	public static void bucketSort(int[] arr) {
		// numeros de buckets
		int n = 10; 
		
		@SuppressWarnings("unchecked")
			
		//Creo una lista de buckets con n posiciones
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

	    // Número de hilos disponibles (se ajusta según los recursos del sistema)
	    int numThreads = Math.min(n, Runtime.getRuntime().availableProcessors());
	    // Crear un semaforo para controlar el numero de hilos y evitar la sobrecarga
	    Semaphore semaphore = new Semaphore(numThreads);
	    // Usar un CountDownLatch para sincronizar la finalización de los hilos
	    CountDownLatch latch = new CountDownLatch(n);
	    // Crear y arrancar hilos para ordenar cada bucket
	    for (int i = 0; i < n; i++) {
	        final int index = i;
	        new Thread(() -> {
	            try {
	            	// Adquirir un permiso del semaforo antes de proceder
	            	semaphore.acquire();
	            	// Ordenar el bucket correspondiente usando insertionSort
	                insertionSort(buckets, index);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            } finally {
	            	// Liberar el permiso del semaforo para permitir que otro hilo proceda
	            	semaphore.release();
	            	// Indicar que este hilo ha terminado (decremento CountDownLatch)
	                latch.countDown();
	            }
	         }).start();
	    }
			
	    try {
            // Esperar a que todos los hilos terminen
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
		
		/*System.out.print("Arreglo ordenado concurrente: ");
		for (int num : arr) {
		    System.out.print(num + " ");
		}
		*/
		
  }
	
	//Funcion para ordenar los buckets
    public static void insertionSort(ArrayList<Integer>[] buckets, int index) {
        // Obtener el bucket especifico a ordenar usando el indice proporcionado
    	ArrayList<Integer> bucket = buckets[index];
        // Iterar desde el segundo elemento hasta el final del bucket
        for (int i = 1; i < bucket.size(); ++i) {
        	// Almacenar el valor actual como clave
            int key = bucket.get(i);
            // Inicializar j como el indice anterior al actual
            int j = i - 1;
            // Mientras j sea valido y el elemento en bucket[j] sea mayor
            // que la clave
            while (j >= 0 && bucket.get(j) > key) {
            	// Desplazar el elemento en bucket[j] una posicion hacia adelante
                bucket.set(j + 1, bucket.get(j));
                // Decrementar j
                j--;
            }
            // Colocar la clave en su posicion correcta
            bucket.set(j + 1, key);
        }
    }

}