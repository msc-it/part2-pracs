// Mini-max algorithm

import java.util.*;
import java.io.*;

class Node 
{
    public String name;
    public Integer cost;

    public Node[] children;

    public Node(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public Node(String name, Node[] children) {
        this.name = name;
        this.cost = null;
        this.children = children;
    }

    public void traceMiniMax() {
        // Get from each of the child nodes
        ArrayList<Integer> minAmounts = new ArrayList<>();
        for(Node n : children) {
            int min = n.getMinCost();
            System.out.println("Min for " + n.name + ": " + min);
            minAmounts.add(min);
        }

        // Get the max of the min values
        int max = Collections.max(minAmounts);
        System.out.println("Max for " + this.name + ": " + max);
    }

    // Retuns the min cost
    // from the current node's children
    private int getMinCost() {

        // create a list of costs from the current node's children
        ArrayList<Integer> costs = new ArrayList<>();
        for (Node n : children) {
            costs.add(n.cost);
        }

        // Return the min value from the collection
        return Collections.min(costs);
    }

}

public class Program {
    public static void main(String args[]) throws Exception {

        /**
         *             A
         *          /   \   \
         *        B     C     D
         *      / |\    /|\   \\\
         *     E  F G  H I J  K L M
         *     3 12 8  2 4 6  14 5 2
         */

        Node tree = new Node("A", new Node[] {
                new Node("B", new Node[] {
                    new Node("E", 3),
                    new Node("F", 12),
                    new Node("G", 8),
                }),
                new Node("C", new Node[] {
                    new Node("H", 2),
                    new Node("I", 4),
                    new Node("J", 6),
                }),
                new Node("D", new Node[] {
                    new Node("K", 14),
                    new Node("L", 5),
                    new Node("M", 2),
                }),
            }
        );

        tree.traceMiniMax();
    }
}
