import java.util.*;
import java.io.*;


class Node 
{
    public String name;
    public int cost;

    public Node[] children;

    public Node(String name, int cost) {
        this(name, cost, null);
    }

    public Node(String name, int cost, Node[] children) {
        this.name = name;
        this.cost = cost;
        this.children = children;
    }

    public boolean traceGreedyBestFirstSearch() {
        System.out.println("Tracing Greedy BFS");
        return Node.traceGreedyBestFirstSearch(this);
    }

    private static boolean traceGreedyBestFirstSearch(Node n) {
        System.out.print(n.name + "(" + n.cost + ") ");

        // Check if we're at the goal state
        if(n.cost == 0) {
            return true;
        }

        // Check if we reached the end
        if(n.children == null || n.children.length == 0) {
            return false;
        }

        // find the child node with the minimum cost
        int minIndex = 0;
        for(int i = 1; i < n.children.length; i++) {
            if(n.children[i].cost < n.children[minIndex].cost) {
                minIndex = i;
            }
        }

        return traceGreedyBestFirstSearch(n.children[minIndex]);
    }
    
}

public class Program {
    public static void main(String args[]) throws Exception {

        /**
         *             A10 
         *          /  |  \
         *         B15 H15  J5
         *       / |   |    | \ 
         *     C20 D20 I20  K0  L10
         *        /|\
         *      E, F, G
         *    25  25  25
         */

        Node tree = new Node("A", 10, new Node[] {
                new Node("B", 15, new Node[] {
                    new Node("C", 20),
                    new Node("D", 20, new Node[]{
                        new Node("E", 25),
                        new Node("F", 25),
                        new Node("G", 25)
                    }),
                }),
                new Node("H", 15, new Node[] {
                    new Node("I", 20)
                }),
                new Node("J", 5, new Node[] {
                    new Node("K", 0), // Goal always has 0
                    new Node("L", 10)
                }),
            }
        );

        boolean result = tree.traceGreedyBestFirstSearch();
        System.out.println(result);
    }
}
