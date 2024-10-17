/*Diseñar e implementar un algoritmo que resuelva el siguiente
problema. Una ciudad se diseño de tal modo que todas sus calles fueran de una
sola vía. Con el paso del tiempo la cantidad de habitantes de la ciudad creció y
esto produjo grandes trancones en algunas de las vias debido a algunos desvíos
innecesarios que tienen que tomar los habitantes de la ciudad para poder llegar
a sus trabajos. Por lo tanto, el alcalde tomó la decisión de ampliar algunas vias
para que puedan convertirse en doble via. Dado el mapa de la ciudad y el costo
de convertir cada via actual en doble via, determinar qué vias se deben
convertir, de modo que se pueda transitar de cualquier punto a cualquier punto
de la ciudad por dobles vias y que el costo de la conversión sea el mínimo
posible.*/
import java.util.*;

public class Parte3 {

    static class Edge implements Comparable<Edge> {
        int source, destination, weight;
        
        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        // Compara las aristas por su peso (costo)
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }
    
    static class Subset {
        int parent, rank;
    }

    // Clase que implementa el algoritmo de Kruskal
    static class KruskalAlgorithm {
        int vertices;  
        List<Edge> edges;  

        public KruskalAlgorithm(int vertices) {
            this.vertices = vertices;
            edges = new ArrayList<>();
        }

        public void addEdge(int source, int destination, int weight) {
            edges.add(new Edge(source, destination, weight));
        }

        public int find(Subset[] subsets, int u) {
            if (subsets[u].parent != u)
                subsets[u].parent = find(subsets, subsets[u].parent);
            return subsets[u].parent;
        }

        public void union(Subset[] subsets, int u, int v) {
            int rootU = find(subsets, u);
            int rootV = find(subsets, v);

            // Une los árboles según su rango
            if (subsets[rootU].rank < subsets[rootV].rank) {
                subsets[rootU].parent = rootV;
            } else if (subsets[rootU].rank > subsets[rootV].rank) {
                subsets[rootV].parent = rootU;
            } else {
                subsets[rootV].parent = rootU;
                subsets[rootU].rank++;
            }
        }

        // Algoritmo de Kruskal para encontrar el MST
        public void kruskalMST() {
            List<Edge> result = new ArrayList<>();  
            int e = 0; 
            int i = 0;  

            Collections.sort(edges);

            // Crea V subconjuntos (uno para cada nodo)
            Subset[] subsets = new Subset[vertices];
            for (int v = 0; v < vertices; v++) {
                subsets[v] = new Subset();
                subsets[v].parent = v;
                subsets[v].rank = 0;
            }

            while (e < vertices - 1) {
                Edge nextEdge = edges.get(i++);
                
                int x = find(subsets, nextEdge.source);
                int y = find(subsets, nextEdge.destination);

                // Si no forman un ciclo, incluye la arista en el resultado
                if (x != y) {
                    result.add(nextEdge);
                    e++;
                    union(subsets, x, y);
                }
            }

            System.out.println("Las vías que deben convertirse en dobles son:");
            for (Edge edge : result) {
                System.out.println(edge.source + " -- " + edge.destination + " == " + edge.weight);
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 6;  
        KruskalAlgorithm graph = new KruskalAlgorithm(vertices);

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 8);  
        graph.addEdge(4, 5, 12); 
        graph.addEdge(2, 5, 7);

        graph.kruskalMST();
    }
}

