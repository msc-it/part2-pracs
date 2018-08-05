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

    public boolean traceBreathSearch(String query) {
        System.out.println(name);

        if(query.equalsIgnoreCase(name)) {
            return true;
        }

        if(children == null) {
            return false;
        }

        Queue<Node> nodesToVisit = new ArrayDeque<Node>();
        for (Node n : children) {
            nodesToVisit.add(n);
        }

        while(!nodesToVisit.isEmpty()) {
            // Get the next item from the queue
            Node next = nodesToVisit.poll();

            System.out.println(next.name);
            if(query.equalsIgnoreCase(next.name)) {
                return true;
            }

            if(next.children == null) {
                continue;
            }

            // Add next's children to the queue
            for (Node n : next.children) {
                nodesToVisit.add(n);
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

        // tree.traceBreathSearch("C");

        System.out.println("Enter your location (A-L):");
        Scanner scanner = new Scanner(System.in);
        String query = scanner.nextLine();

        System.out.println();
        System.out.println("Performing BFS");
        System.out.println("--------------");
        tree.traceBreathSearch(query);
    }
}
