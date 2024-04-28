//This is for a CYCLE and not a PATH -- not yet accurate
import java.util.*;

public class HamiltonianCycle {

    private int V; // Number of vertices
    private int[][] graph; // Adjacency matrix

    public HamiltonianCycle(int[][] graph) {
        this.graph = graph;
        this.V = graph.length;
    }

    // Main function to check if a Hamiltonian cycle exists
    public boolean hasHamiltonianCycle() {
        int[] path = new int[V];
        Arrays.fill(path, -1); // Initialize path array with -1

        // Start from the first vertex (index 0)
        path[0] = 0;

        // Try different permutations of vertices as starting points
        if (!hamiltonianCycleUtil(path, 1))
            return false;

        // If a Hamiltonian cycle exists, check if it returns to the starting vertex
        return path[0] == path[V - 1];
    }

    // Utility function to recursively find Hamiltonian cycle
    private boolean hamiltonianCycleUtil(int[] path, int pos) {
        if (pos == V) { // If all vertices are included in the path
            // Check if the last vertex is adjacent to the first vertex
            return graph[path[pos - 1]][path[0]] == 1;
        }

        // Try different vertices as the next candidate in the path
        for (int v = 1; v < V; v++) {
            if (isSafe(v, path, pos)) {
                path[pos] = v; // Include vertex v in the path
                // Recur to construct rest of the path
                if (hamiltonianCycleUtil(path, pos + 1))
                    return true;
                // If including vertex v doesn't lead to a solution, backtrack
                path[pos] = -1;
            }
        }

        return false; // No Hamiltonian cycle exists
    }

    // Check if adding vertex v to the path is safe
    private boolean isSafe(int v, int[] path, int pos) {
        // Check if vertex v is adjacent to the last added vertex
        if (graph[path[pos - 1]][v] == 0)
            return false;

        // Check if vertex v has already been included in the path
        for (int i = 0; i < pos; i++) {
            if (path[i] == v)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 0, 1},
            {0, 1, 1, 1, 0}
        };

        HamiltonianCycle hc = new HamiltonianCycle(graph);
        if (hc.hasHamiltonianCycle())
            System.out.println("Graph has a Hamiltonian cycle");
        else
            System.out.println("Graph does not have a Hamiltonian cycle");
    }
}
