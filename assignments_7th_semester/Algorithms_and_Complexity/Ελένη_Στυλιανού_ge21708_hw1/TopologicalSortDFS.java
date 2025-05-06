
/**
 * Write a description of class TopologicalSortDFS here.
 *
 * Ελένη Στυλιανού
 * ge21708
 * 11/2024
 */
import java.util.*;

public class TopologicalSortDFS {
    private int vertices; // Αριθμός κορυφών
    private LinkedList<Integer> adjList[]; // Λίστα γειτνίασης

    // Κατασκευαστής
    public TopologicalSortDFS(int v) {
        vertices = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Προσθήκη ακμής στον γράφο
    public void addEdge(int v, int w) {
        adjList[v].add(w);
    }

    // Βοηθητική συνάρτηση για την τοπολογική διάταξη
    private void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
        // Σήμανση της τρέχουσας κορυφής ως επισκέψιμη
        visited[v] = true;

        // Επανάληψη για όλες τις γειτονικές κορυφές
        for (Integer neighbor : adjList[v]) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }

        // Προσθήκη της κορυφής στην στοίβα
        stack.push(v);
    }

    // Συνάρτηση τοπολογικής διάταξης
    public void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[vertices];

        // Κλήση της βοηθητικής συνάρτησης για όλες τις μη επισκέψιμες κορυφές
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        // Εμφάνιση της στοίβας (τοπολογική σειρά)
        System.out.print("Τοπολογική σειρά: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String args[]) {
        TopologicalSortDFS graph = new TopologicalSortDFS(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        System.out.println("Η τοπολογική διάταξη του γραφήματος είναι:");
        graph.topologicalSort();
    }
}

