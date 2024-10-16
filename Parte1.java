/*Autores:
 * Lucia Castillo 202214949
 * Andres Molano 202215460
 */
import java.util.*;  
import java.util.Arrays; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 

public class Parte1 {
    static class Edge {
        int source, destination, weight;
    
        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    
    static class Graph {
        Map<Integer, List<Edge>> adjList;
        int vertices;
    
        public Graph() {
            adjList = new HashMap<>();
            vertices = 0;  // Inicialmente no conocemos el número de vértices
        }
    
        public void addEdge(int source, int destination, int weight) {
            // Agregar el vértice si no existe en el grafo
            if (!adjList.containsKey(source)) {
                adjList.put(source, new ArrayList<>());
                vertices = Math.max(vertices, source + 1);
            }
            if (!adjList.containsKey(destination)) {
                adjList.put(destination, new ArrayList<>());
                vertices = Math.max(vertices, destination + 1);
            }
            adjList.get(source).add(new Edge(source, destination, weight));
        }
    
        public int getVertices() {
            return vertices;  // Devuelve el número total de vértices
        }
    }
    
    static class Dijkstra {
        public int[] dijkstra(Graph graph, int start) {
            int vertices = graph.getVertices();
            int[] dist = new int[vertices];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[start] = 0;
    
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
            pq.add(new Edge(start, start, 0));
    
            while (!pq.isEmpty()) {
                Edge current = pq.poll();
                int u = current.source;
    
                if (!graph.adjList.containsKey(u)) continue;
    
                for (Edge edge : graph.adjList.get(u)) {
                    int v = edge.destination;
                    int weight = edge.weight;
    
                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pq.add(new Edge(v, v, dist[v]));
                    }
                }
            }
            return dist;
        }
    }
    
    static class BellmanFord {
        public int[] bellmanFord(Graph graph, int start) {
            int vertices = graph.getVertices();
            int[] dist = new int[vertices];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[start] = 0;
    
            for (int i = 1; i < vertices; i++) {
                for (int u : graph.adjList.keySet()) {
                    for (Edge edge : graph.adjList.get(u)) {
                        int v = edge.destination;
                        int weight = edge.weight;
                        if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                            dist[v] = dist[u] + weight;
                        }
                    }
                }
            }
    
            // Detección de ciclos negativos
            for (int u : graph.adjList.keySet()) {
                for (Edge edge : graph.adjList.get(u)) {
                    int v = edge.destination;
                    int weight = edge.weight;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                        throw new RuntimeException("El grafo contiene un ciclo de peso negativo");
                    }
                }
            }
    
            return dist;
        }
    }
    
    static class FloydWarshall {
        public int[][] floydWarshall(Graph graph) {
            int vertices = graph.getVertices();
            int[][] dist = new int[vertices][vertices];
    
            // Matriz de distancias
            for (int i = 0; i < vertices; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
                dist[i][i] = 0;  // Distancia de un nodo a sí mismo es 0
            }
    
            // Llenar distancias iniciales desde las aristas
            for (int u : graph.adjList.keySet()) {
                for (Edge edge : graph.adjList.get(u)) {
                    dist[edge.source][edge.destination] = edge.weight;
                }
            }
    
            // Algoritmo Floyd-Warshall
            for (int k = 0; k < vertices; k++) {
                for (int i = 0; i < vertices; i++) {
                    for (int j = 0; j < vertices; j++) {
                        if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
    
            return dist;
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("grafo.txt")); 
        Graph graph = new Graph();
    
        while (scanner.hasNext()) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight);
        }
        scanner.close();
    
        Dijkstra dijkstra = new Dijkstra();
        int[] dijkstraDist = dijkstra.dijkstra(graph, 0);
        System.out.println("Distancias Dijkstra desde 0:");
        System.out.println(Arrays.toString(dijkstraDist));
    
        BellmanFord bellmanFord = new BellmanFord();
        int[] bellmanDist = bellmanFord.bellmanFord(graph, 0);
        System.out.println("Distancias Bellman-Ford desde 0:");
        System.out.println(Arrays.toString(bellmanDist));
    
        FloydWarshall floydWarshall = new FloydWarshall();
        int[][] floydDist = floydWarshall.floydWarshall(graph);
        System.out.println("Distancias Floyd-Warshall:");
        for (int i = 0; i < graph.getVertices(); i++) {
            System.out.println(Arrays.toString(floydDist[i]));
        }
    }
}


