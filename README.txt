Cómo se debe ejecutar cada programa (formato del archivo de entrada y descripción de la salida)

Punto 1:
 Entrada: El programa lee automaticamente un archivo .txt con el formato que se indica en el enunciado,
 el nombre del archivo se puede cambiar en la linea 140. 

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
Entradas: Lee un archivo .txt donde los grafo se muestran como 0 1 90 donde los primeros son 2 nodos y el 
tercero es el peso.

Salidas: la salida del programa son los componentes conectados de la siguiente manera :

Componentes conectados: [{"ComponentesConectados1"}, {"ComponentesConectados2"},...]

Punto 3:
 Entradas: Lee un archivo .txt donde los grafo se muestran como 0 1 90 donde los primeros son 2 nodos y el 
tercero es el peso.
 
 Salidas: Imprime Las Aristas(vias) que deberian convertirse a doble via con el siguiente formato:
 Nodo Origen -- Nodo Destino == Costo

Puto 4:
 Entradas: Recibe un grafo de capacidades, una fuente y un sumidero, que están definidos en el main. Cada fila y columna 
 de la matriz corresponde a un nodo. En la matriz el valor de la posición [i][j] representa la capacidad de libros que 
 pueden ser transportados del nodo i al nodo j.

 Salida: Imprime un entero que representa el flujo maximo de libros que se pueden transportar.  