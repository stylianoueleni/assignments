
/**
 * Write a description of class TopologicalSortExperiment here.
 *
 * Ελένη Στυλιανού
 * ge21708
 * 11/2024
 */
import java.util.*;

public class TopologicalSortExperiment {
    private int vertices;
    private List<Integer>[] adjList;

    public TopologicalSortExperiment(int v) {
        vertices = v;
        adjList = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    // Προσθήκη ακμής στον γράφο
    public void addEdge(int v, int w) {
        adjList[v].add(w);
    }

    // Δημιουργία τυχαίου DAG με καθορισμένο αριθμό κορυφών και ακμών
    public static TopologicalSortExperiment generateRandomDAG(int vertices, int edges) {
        TopologicalSortExperiment graph = new TopologicalSortExperiment(vertices);
        Random rand = new Random();

        while (edges > 0) {
            int v = rand.nextInt(vertices);
            int w = rand.nextInt(vertices);
            if (v != w && !graph.adjList[v].contains(w)) { // Αποφυγή βρόχων και διπλών ακμών
                graph.addEdge(v, w);
                edges--;
            }
        }
        return graph;
    }

    // Μέθοδος για την εκτέλεση των πειραμάτων
    public static void runExperiment(int vertices, int edges) {
        TopologicalSortExperiment graph = generateRandomDAG(vertices, edges);

        // Χρονισμός υλοποίησης O(V^2)
        long startTime = System.nanoTime();
        graph.zeroInDegreeTopologicalSort_V2();
        long endTime = System.nanoTime();
        System.out.println("O(V^2): " + (endTime - startTime) / 1e6 + " ms");

        // Χρονισμός υλοποίησης O(E log V)
        startTime = System.nanoTime();
        graph.zeroInDegreeTopologicalSort_ELogV();
        endTime = System.nanoTime();
        System.out.println("O(E log V): " + (endTime - startTime) / 1e6 + " ms");

        // Χρονισμός υλοποίησης O(V + E)
        startTime = System.nanoTime();
        graph.zeroInDegreeTopologicalSort_VplusE();
        endTime = System.nanoTime();
        System.out.println("O(V + E): " + (endTime - startTime) / 1e6 + " ms");
    }

    // Παράδειγμα κύριας μεθόδου για πειραματικές εκτελέσεις
    public static void main(String[] args) {
        int[] vertexCounts = {100, 200, 400, 800}; // Πλήθη κορυφών για το πείραμα

        for (int V : vertexCounts) {
            System.out.println("\n--- Αποτελέσματα για V = " + V + " ---");
            
            // Αραιά γραφήματα
            int edgesSparse = 4 * V;
            System.out.println("\nΑραιό γράφημα (|E| = " + edgesSparse + ")");
            runExperiment(V, edgesSparse);

            // Γραφήματα μεσαίας πυκνότητας
            int edgesMedium = (int)Math.pow(V, 1.5);
            System.out.println("\nΓράφημα μεσαίας πυκνότητας (|E| = " + edgesMedium + ")");
            runExperiment(V, edgesMedium);

            // Πλήρη γραφήματα
            int edgesDense = V * (V - 1) / 2;
            System.out.println("\nΠλήρες γράφημα (|E| = " + edgesDense + ")");
            runExperiment(V, edgesDense);
        }
    }

    // Υλοποιήσεις των τριών αλγορίθμων τοπολογικής διάταξης

    public void zeroInDegreeTopologicalSort_V2() {
        // Υλοποίηση O(V^2)
    }

    public void zeroInDegreeTopologicalSort_ELogV() {
        // Υλοποίηση O(E log V) με χρήση ουράς προτεραιότητας
    }

    public void zeroInDegreeTopologicalSort_VplusE() {
        // Υλοποίηση O(V + E) με απλή ουρά
    }
}
