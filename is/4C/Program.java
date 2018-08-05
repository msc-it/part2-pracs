import java.util.*;
import java.io.*;


class Node implements Comparable<Node> 
{
    public String name;
    public int estimate; // h(n)
    public int path_cost; // g(n)

    public Node[] children;

    public Node(String name, int estimate, int path_cost) {
        this(name, estimate, path_cost, null);
    }

    public Node(String name, int estimate, int path_cost, Node[] children) {
        this.name = name;
        this.estimate = estimate;
        this.path_cost = path_cost;
        this.children = children;
    }

    public boolean traceRecursiveBestFirstSearch() {
        System.out.println("Tracing RecursiveBestFirstSearch");
        System.out.print(name + "(" + estimate + ") ");
        PriorityQueue<Node> q = new PriorityQueue<Node>();
        Collections.addAll(q, children);
        return traceRecursiveBestFirstSearch(q);
    }

    private static boolean traceRecursiveBestFirstSearch(PriorityQueue<Node> q) {
        Node n = q.poll();
        // Quit if the queue is empty
        if(n == null) {
            return false; // goal state not found
        }

        System.out.print(n.name + "(" + n.estimate + ") ");

        // Check if we're at the goal state
        if(n.estimate == 0) {
            return true;
        }

        // Add the children to the priority queue
        if(n.children != null && n.children.length != 0) {
            Collections.addAll(q, n.children);
        }
        
        return traceRecursiveBestFirstSearch(q);
    }

    public int calculateFOfN() {
        // f(n) = h(n) + g(n)
        return estimate + path_cost;
    }

    // For the queue to return values based on f(n) as priority
    @Override 
    public int compareTo(Node other) {
        return Integer.compare(calculateFOfN(), other.calculateFOfN());
    }
}

public class Program {
    public static void main(String args[]) throws Exception {

        /**
         *             A10 
         *          /5 |5 \5
         *         B15 H15  J5
         *       /5|5  |5   |5 \5 
         *     C20 D20 I20  K0  L10
         *       5/|5 \5
         *      E  F   G
         *     25  25  25
         */

        Node tree = new Node("A", 10, 0, new Node[] {
                new Node("B", 15, 5, new Node[] {
                    new Node("C", 20, 5),
                    new Node("D", 20, 5, new Node[]{
                        new Node("E", 25, 5),
                        new Node("F", 25, 5),
                        new Node("G", 25, 5)
                    }),
                }),
                new Node("H", 15, 5, new Node[] {
                    new Node("I", 20, 5)
                }),
                new Node("J", 5, 5, new Node[] {
                    new Node("K", 0, 5),
                    new Node("L", 10, 5)
                }),
            }
        );

        boolean result = tree.traceRecursiveBestFirstSearch();
        System.out.println(result);
    }
}
