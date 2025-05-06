
/**
 * Write a description of class ZeroInDegreeTopologicalSort here.
 *
 * Ελένη Στυλιανού
 * ge21708
 * 11/2024
 */
import java.util.*;

public class ZeroInDegreeTopologicalSort {
    private int vertices; // Αριθμός κορυφών
    private LinkedList<Integer> adjList[]; // Λίστα γειτνίασης

    // Κατασκευαστής
    public ZeroInDegreeTopologicalSort(int v) {
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

    // Συνάρτηση που υπολογίζει την τοπολογική διάταξη
    public void zeroInDegreeTopologicalSort() {
        // Δημιουργούμε έναν πίνακα για να αποθηκεύσουμε τον εσωβαθμό (in-degree) κάθε κορυφής
        int[] inDegree = new int[vertices];
        
        // Υπολογίζουμε τον εσωβαθμό κάθε κορυφής
        for (int i = 0; i < vertices; i++) {
            for (int neighbor : adjList[i]) {
                inDegree[neighbor]++;
            }
        }

        // Λίστα εξόδου για την τοπολογική διάταξη
        List<Integer> topologicalOrder = new ArrayList<>();

        // Εκτέλεση του αλγορίθμου Zero-in-degree_topological_sort
        for (int i = 0; i < vertices; i++) {
            // Επιλογή κορυφής με εσωβαθμό 0
            int v = -1;
            for (int j = 0; j < vertices; j++) {
                if (inDegree[j] == 0) {
                    v = j;
                    break;
                }
            }

            // Αν δεν βρεθεί κορυφή με εσωβαθμό 0, υπάρχει κύκλος
            if (v == -1) {
                System.out.println("Ο γράφος περιέχει κύκλο, δεν είναι δυνατό να βρεθεί τοπολογική διάταξη.");
                return;
            }

            // Προσθέτουμε την κορυφή στη λίστα εξόδου και την αφαιρούμε από το γράφο
            topologicalOrder.add(v);
            inDegree[v] = -1; // Αφαιρούμε την κορυφή από τον γράφο

            // Μειώνουμε τον εσωβαθμό των γειτονικών κορυφών κατά 1
            for (int neighbor : adjList[v]) {
                inDegree[neighbor]--;
            }
        }

        // Εμφανίζουμε την τοπολογική διάταξη
        System.out.print("Τοπολογική σειρά: ");
        for (int vertex : topologicalOrder) {
            System.out.print(vertex + " ");
        }
    }

    public static void main(String args[]) {
        ZeroInDegreeTopologicalSort graph = new ZeroInDegreeTopologicalSort(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        System.out.println("Η τοπολογική διάταξη του γραφήματος είναι:");
        graph.zeroInDegreeTopologicalSort();
    }
}
