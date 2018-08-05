import java.util.*;
import java.io.*;


class Node 
{
    public String name;
    public Node[] children;

    public Node(String name) {
        this(name, null);
    }

    public Node(String name, Node[] children) {
        this.name = name;
        this.children = children;
    }


    public boolean traceIterativeDeepeningSearch(String query) {
        int depth = 0;
        while(depth < 1000) { // Arbitrary upper limit
            System.out.println("\nPerforming DLS with depth=" + depth);
            boolean result = traceDepthLimitingSearch(query, depth);
            if(result) {
                return true;
            }
            depth++;
        }
        return false;
    }

    public boolean traceDepthLimitingSearch(String query, int depth) {

        // print the current name
        System.out.println(name);

        if(depth == 0 && query.equalsIgnoreCase(name)) {
            return true;
        }

        if(children == null) {
            return false;
        }

        if(depth > 0) {
            for(Node n : children) {
                boolean result = n.traceDepthLimitingSearch(query, depth - 1);
                if(result) {
                    return true;
                }
            }
        }

        return false;
    }
}

public class Program {
    public static void main(String args[]) throws Exception {

        /**
         *             A 
         *          /  |  \
         *         B   H    J
         *       / |   |    |\ 
         *     C   D   I    K  L
         *        /|\
         *      E, F, G
         */

        Node tree = new Node("A", new Node[] {
                new Node("B", new Node[] {
                    new Node("C"),
                    new Node("D", new Node[]{
                        new Node("E"),
                        new Node("F"),
                        new Node("G")
                    }),
                }),
                new Node("H", new Node[] {
                    new Node("I")
                }),
                new Node("J", new Node[] {
                    new Node("K"),
                    new Node("L")
                }),
            }
        );

        // boolean result = tree.traceIterativeDeepeningSearch("G");

        System.out.println("Enter your location(A-L):");
        Scanner scanner = new Scanner(System.in);
        String query = scanner.nextLine();

        System.out.println();
        System.out.println("Performing IDS");
        System.out.println("--------------");
        tree.traceIterativeDeepeningSearch(query);
    }
}
