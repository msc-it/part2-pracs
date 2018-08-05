import java.util.*;
import java.io.*;


class Node 
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

    public boolean traceAStar() {
        System.out.println("Tracing A*");
        return traceAStar(this);
    }

    private static boolean traceAStar(Node n) {
        System.out.print(n.name + "(" + n.estimate + ") ");

        // Check if we're at the goal state
        if(n.estimate == 0) {
            return true;
        }

        // Check if we reached the end
        if(n.children == null || n.children.length == 0) {
            return false;
        }

        // find the child node optimal value for f(n)
        int minIndex = 0;
        for(int i = 1; i < n.children.length; i++) {
            if(Node.calculateFOfN(n.children[i]) < Node.calculateFOfN(n.children[minIndex])) {
                minIndex = i;
            }
        }
        return traceAStar(n.children[minIndex]);
    }

    private static int calculateFOfN(Node to) {
        // f(n) = h(n) + g(n)
        return to.estimate + to.path_cost;
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

        boolean result = tree.traceAStar();
        System.out.println(result);
    }
}
