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

    public boolean traceDepthSearch(String query) {
        // print the current name
        System.out.println(name);

        if(query.equalsIgnoreCase(name)) {
            return true; // match found
        }

        if(children == null) {
            return false;
        }

        boolean result = false;
        for (Node n : children) {
            result = result || n.traceDepthSearch(query);
        }
        return result;
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

        // tree.traceDepthSearch("C");

        System.out.println("Enter your location(A-L):");
        Scanner scanner = new Scanner(System.in);
        String query = scanner.nextLine();

        System.out.println();
        System.out.println("Performing DFS");
        System.out.println("--------------");
        tree.traceDepthSearch(query);
    }
}
