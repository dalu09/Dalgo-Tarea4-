/*BFS para encontrar componentes conectados. Encontrar los
componentes conectados en un grafo no dirigido. Dado un grafo no dirigido,
encontrar una partición de los vértices tal que para cada par de elementos
dentro de un subconjunto de la partición exista un camino en el grafo. Por
ejemplo, para el siguiente grafo:
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parte2 {

    // Método para encontrar los componentes conectados utilizando BFS
    public static List<Set<Integer>> findConnectedComponents(Map<Integer, List<Integer>> graph) {
        List<Set<Integer>> components = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (Integer nodo : graph.keySet()) {
            if (!visited.contains(nodo)) {
                Set<Integer> component = new HashSet<>();
                bfs(nodo, graph, visited, component);
                components.add(component);
            }
        }
        return components;
    }

    // Método auxiliar para hacer BFS
    private static void bfs(Integer nodoinicio, Map<Integer, List<Integer>> graph, Set<Integer> visited,
            Set<Integer> component) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(nodoinicio);
        visited.add(nodoinicio);

        while (!queue.isEmpty()) {
            Integer nodoactual = queue.poll();
            component.add(nodoactual);

            for (Integer veci : graph.get(nodoactual)) {
                if (!visited.contains(veci)) {
                    visited.add(veci);
                    queue.add(veci);
                }
            }
        }
    }

    // Método para agregar una arista entre dos nodos, asegurándose de que no se
    // sobrescriban las adyacencias
    private static void addEdge(Map<Integer, List<Integer>> graph, int nodo1, int nodo2) {
        graph.putIfAbsent(nodo1, new ArrayList<>());
        graph.get(nodo1).add(nodo2);

        graph.putIfAbsent(nodo2, new ArrayList<>());
        graph.get(nodo2).add(nodo1);
    }

    public static void main(String[] args) {
        // Crear el grafo como una lista de adyacencia
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Scanner consoleScanner = new Scanner(System.in);

        // Solicitar al usuario el nombre del archivo
        System.out.print("Introduce el nombre del archivo (ej: grafo.txt): ");
        String fileName = consoleScanner.nextLine(); // Leer el nombre del archivo

        try {
            // Leer el archivo que contiene las aristas
            File file = new File(fileName); // Usar el nombre ingresado
            Scanner fileScanner = new Scanner(file);

            // Leer las aristas del archivo
            while (fileScanner.hasNext()) {
                int u = fileScanner.nextInt(); // Nodo 1
                int v = fileScanner.nextInt(); // Nodo 2
                int w = fileScanner.nextInt(); // Peso (ignorado)
                addEdge(graph, u, v); // Solo usar nodos u y v
            }

            fileScanner.close();

            // Medir el tiempo de ejecución del algoritmo BFS
            long startTime = System.nanoTime();
            List<Set<Integer>> components = findConnectedComponents(graph);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;

            // Mostrar el resultado
            System.out.println("Tiempo de ejecución: " + duration + " nanosegundos.");
            System.out.println("Componentes conectados: " + components);

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + fileName);
            e.printStackTrace();
        }

        consoleScanner.close();
    }
}
