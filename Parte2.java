/*BFS para encontrar componentes conectados. Encontrar los
componentes conectados en un grafo no dirigido. Dado un grafo no dirigido,
encontrar una partición de los vértices tal que para cada par de elementos
dentro de un subconjunto de la partición exista un camino en el grafo. Por
ejemplo, para el siguiente grafo:
 */

import java.util.*;

public class Parte2 {

    // Método para encontrar los componentes conectados utilizando BFS
    public static List<Set<Integer>> findConnectedComponents(Map<Integer, List<Integer>> graph) {
        List<Set<Integer>> components = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (Integer node : graph.keySet()) {
            if (!visited.contains(node)) {
                Set<Integer> component = new HashSet<>();
                bfs(node, graph, visited, component);
                components.add(component);
            }
        }
        return components;
    }

    // Método auxiliar para hacer BFS
    private static void bfs(Integer startNode, Map<Integer, List<Integer>> graph, Set<Integer> visited,
            Set<Integer> component) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        visited.add(startNode);

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
    private static void addEdge(Map<Integer, List<Integer>> graph, int node1, int node2) {
        graph.putIfAbsent(node1, new ArrayList<>());
        graph.get(node1).add(node2);

        graph.putIfAbsent(node2, new ArrayList<>());
        graph.get(node2).add(node1);
    }

    public static void main(String[] args) {
        // Crear el grafo como una lista de adyacencia
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el número de aristas: ");
        int numEdges = scanner.nextInt();

        System.out.println("Introduce las aristas en formato u v (dos enteros (nodos) por línea): ");
        for (int i = 0; i < numEdges; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            addEdge(graph, u, v);
        }

        List<Set<Integer>> components = findConnectedComponents(graph);

        System.out.println("Componentes conectados: " + components);
    }
}
