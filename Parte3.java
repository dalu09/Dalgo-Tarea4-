/*Autores:
 * Lucia Castillo 202214949
 * Andres Molano 202215460
 */

import java.io.File;
import java.io.FileNotFoundException;
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

            // Une los árboles
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
        try {
            Scanner scanner = new Scanner(System.in);

            // Pedir el nombre del archivo desde la entrada
            System.out.print("Introduce el nombre del archivo (ejemplo: grafo.txt): ");
            String nombreArchivo = scanner.nextLine();

            // Leer el archivo de texto que contiene el grafo
            File file = new File(nombreArchivo);
            Scanner fileScanner = new Scanner(file);

            // Inicializar variables para calcular el número de vértices
            int maxVertex = -1;
            List<int[]> edgesFromFile = new ArrayList<>();

            // Leer las aristas del archivo
            while (fileScanner.hasNext()) {
                int source = fileScanner.nextInt();
                int destination = fileScanner.nextInt();
                int weight = fileScanner.nextInt();

                // Guardar aristas temporalmente
                edgesFromFile.add(new int[] { source, destination, weight });

                // Encontrar el vértice más grande
                maxVertex = Math.max(maxVertex, Math.max(source, destination));
            }
            fileScanner.close();

            // El número total de vértices es el máximo vértice + 1 (si el índice comienza
            // desde 0)
            int vertices = maxVertex + 1;

            // Crear el objeto para ejecutar el algoritmo de Kruskal
            KruskalAlgorithm graph = new KruskalAlgorithm(vertices);

            // Añadir todas las aristas leídas al grafo
            for (int[] edge : edgesFromFile) {
                graph.addEdge(edge[0], edge[1], edge[2]);
            }

            // Medir el tiempo de ejecución de Kruskal
            long startTime = System.nanoTime(); // Iniciar el cronómetro

            // Ejecutar el algoritmo de Kruskal para encontrar el MST
            graph.kruskalMST();

            long endTime = System.nanoTime(); // Detener el cronómetro

            // Calcular el tiempo transcurrido en nanosegundos
            long duration = endTime - startTime;
            System.out.println("Tiempo de ejecución: " + duration + " nanosegundos.");

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
            e.printStackTrace();
        }
    }

}
