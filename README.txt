Cómo se debe ejecutar cada programa (formato del archivo de entrada y descripción de la salida)

Punto 1:
 Entrada: El programa lee automaticamente un archivo .txt que se puede cambiar en la linea 140. 

 Salidas:
  Dijkstra: Imprime un arreglo donde el índice es el nodo de destino y el valor es la distancia 
  mínima desde el nodo 0 a ese nodo.

  Bellman-Ford: Imprime un arreglo con las distancias mínimas desde el nodo fuente a todos los demas nodos.
  Además si el grafo contiene ciclos negativos, se lanza una excepción con el mensaje: "El grafo contiene un 
  ciclo de peso negativo".

  Floyd-Warshall: Imprime una matriz de distancias mínimas entre todos los nodos. La posición [i][j] de 
  la matriz representa la distancia mínima desde el nodo i al nodo j. Los valores 2147483647 representan "infinito", 
  lo ue indica que no hay un camino entre los nodos en cuestión.

Punto 2:

Punto 3:
 Entradas: No necesita de archivos extra. En el main es necesario indicar el numero de vertices en la linea 108 y 
 se añaden aristas con la siguiente función addEdge(source, destination, weight), donde source es el nodo de origen
 de la calle, destination el nodo de destino y weight el costo de convertir la calle a doble via. 
 
 Salidas: Imprime Las Aristas(vias) que deberian convertirse a doble via con el siguiente formato:
 Nodo Origen -- Nodo Destino == Costo

Puto 4:
 Entradas: Recibe un grafo de capacidades, una fuente y un sumidero, que están definidos en el main.

 Salida: Imprime un entero que representa el flujo maximo de libros que se pueden transportar.  