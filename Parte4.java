/*Autores:
 * Lucia Castillo 202214949
 * Andres Molano 202215460
 */

import java.util.*;

public class Parte4 {
    public class RedDeFlujo {

        private static boolean bfs(int[][] rGraph, int source, int sink, int[] parent) {
            boolean[] visited = new boolean[rGraph.length];
            Arrays.fill(visited, false);
    
            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);
            visited[source] = true;
            parent[source] = -1;
    
            while (!queue.isEmpty()) {
                int u = queue.poll();
    
                for (int v = 0; v < rGraph.length; v++) {
                    if (!visited[v] && rGraph[u][v] > 0) {
                        // Si encontramos el camino al sink, devolvemos true
                        if (v == sink) {
                            parent[v] = u;
                            return true;
                        }
                        queue.add(v);
                        parent[v] = u;
                        visited[v] = true;
                    }
                }
            }
    
            // No encontramos un camino aumentante
            return false;
        }
    
        public static int edmondsKarp(int[][] graph, int source, int sink) {
            int u, v;
            int vertices = graph.length;
    
            int[][] rGraph = new int[vertices][vertices];
            for (u = 0; u < vertices; u++) {
                for (v = 0; v < vertices; v++) {
                    rGraph[u][v] = graph[u][v];
                }
            }
    
            // Array para almacenar el camino de aumento
            int[] parent = new int[vertices];
            int maxFlow = 0;  
    
            // Mientras haya un camino aumentante, se incrementa el flujo
            while (bfs(rGraph, source, sink, parent)) {
                // Flujo máximo que podemos enviar por el camino encontrado
                int pathFlow = Integer.MAX_VALUE;
                for (v = sink; v != source; v = parent[v]) {
                    u = parent[v];
                    pathFlow = Math.min(pathFlow, rGraph[u][v]);
                }
    
                // Actualiza el grafo residual
                for (v = sink; v != source; v = parent[v]) {
                    u = parent[v];
                    rGraph[u][v] -= pathFlow;
                    rGraph[v][u] += pathFlow;
                }
    
                // Suma el flujo del camino encontrado al flujo total
                maxFlow += pathFlow;
            }
    
            return maxFlow;
        }
    
        public static void main(String[] args) {
    
            int[][] graph = new int[][]{
                // Fábricas (nodos 0, 1) a Bodegas (nodos 2, 3) y Librerías (nodos 4, 5)
                {0, 0, 10, 10, 0, 0},  // Fábrica 1 (nodo 0)
                {0, 0, 5, 15, 0, 0},   // Fábrica 2 (nodo 1)
                {0, 0, 0, 0, 10, 0},   // Bodega 1 (nodo 2)
                {0, 0, 0, 0, 5, 15},   // Bodega 2 (nodo 3)
                {0, 0, 0, 0, 0, 0},    // Librería 1 (nodo 4)
                {0, 0, 0, 0, 0, 0}     // Librería 2 (nodo 5)
            };
    
            int source = 0;  
            int sink = 5;   
    
            System.out.println("El flujo máximo de libros que se puede transportar es: " +
                                edmondsKarp(graph, source, sink));
        }
    }
}
