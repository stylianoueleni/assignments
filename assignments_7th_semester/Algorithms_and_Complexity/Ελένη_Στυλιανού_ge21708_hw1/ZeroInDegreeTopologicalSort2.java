
/**
 * Write a description of class ZeroInDegreeTopologicalSort2 here.
 *
 * Ελένη Στυλιανού
 * ge21708
 * 11/2024
 */
import java.util.*;

public class ZeroInDegreeTopologicalSort2 {
    private int vertices; // Αριθμός κορυφών
    private LinkedList<Integer> adjList[]; // Λίστα γειτνίασης

    // Κατασκευαστής
    public ZeroInDegreeTopologicalSort2(int v) {
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

    // Συνάρτηση που υπολογίζει την τοπολογική διάταξη με χρήση ουράς
    public void zeroInDegreeTopologicalSort2() {
        // Δημιουργία πίνακα για τον εσωβαθμό (in-degree) κάθε κορυφής
        int[] inDegree = new int[vertices];
        
        // Υπολογισμός του εσωβαθμού κάθε κορυφής
        for (int i = 0; i < vertices; i++) {
            for (int neighbor : adjList[i]) {
                inDegree[neighbor]++;
            }
        }

        // Δημιουργία ουράς για τις κορυφές με εσωβαθμό 0
        Queue<Integer> zeroInDegreeQueue = new LinkedList<>();

        // Προσθήκη όλων των κορυφών με εσωβαθμό 0 στην ουρά
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                zeroInDegreeQueue.add(i);
            }
        }

        // Λίστα εξόδου για την τοπολογική διάταξη
        List<Integer> topologicalOrder = new ArrayList<>();

        // Εκτέλεση του αλγορίθμου Zero-in-degree_topological_sort
        while (!zeroInDegreeQueue.isEmpty()) {
            // Επιλογή κορυφής με εσωβαθμό 0
            int v = zeroInDegreeQueue.poll();
            topologicalOrder.add(v);

            // Μείωση του εσωβαθμού των γειτονικών κορυφών κατά 1
            for (int neighbor : adjList[v]) {
                inDegree[neighbor]--;
                
                // Αν κάποια γειτονική κορυφή έχει πλέον εσωβαθμό 0, την προσθέτουμε στην ουρά
                if (inDegree[neighbor] == 0) {
                    zeroInDegreeQueue.add(neighbor);
                }
            }
        }

        // Έλεγχος αν υπάρχει κύκλος στον γράφο
        if (topologicalOrder.size() != vertices) {
            System.out.println("Ο γράφος περιέχει κύκλο, δεν είναι δυνατό να βρεθεί τοπολογική διάταξη.");
        } else {
            // Εμφάνιση της τοπολογικής διάταξης
            System.out.print("Τοπολογική σειρά: ");
            for (int vertex : topologicalOrder) {
                System.out.print(vertex + " ");
            }
        }
    }

    public static void main(String args[]) {
        ZeroInDegreeTopologicalSort2 graph = new ZeroInDegreeTopologicalSort2(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        System.out.println("Η τοπολογική διάταξη του γραφήματος είναι:");
        graph.zeroInDegreeTopologicalSort2();
    }
}

