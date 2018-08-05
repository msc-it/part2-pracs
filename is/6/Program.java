import java.util.*;

class Element
{
    // The string must be 20 characters long with only 1s and 0s
    public String data = "";
    public Element(String data) {
        this.data = data;
    }

    /**
     * Higher values if 1s and 0s are nearer to each other.
     * Lower values for skewed numbers of 1s and 0s.
     * Goal at 20, when 10 ones and 10 zeros exist.
     */
    public int getFitness() {
        int ones = 0;
        int zeros = 0;
        for (char c : data.toCharArray()) {
            if(c == '0') zeros++;
            else if(c == '1') ones++;
        }
        return 20 - Math.abs(ones - zeros);
    }

    public Element getOffspring(Element b) {
        Random random = new Random();
        boolean shouldCrossOver = random.nextInt(100) < 80; // 80% crossover chances
        boolean shouldMutate = random.nextInt(100) < 10; // 10% mutation chances

        Element offspring = new Element(b.data);
        if(shouldCrossOver) {
            int crossoverPoint = random.nextInt(data.length() - 1);
            offspring = crossover(b, crossoverPoint);
        }
        if(shouldMutate) {
            int mutationPoint = random.nextInt(data.length() - 1);
            offspring.mutate(mutationPoint);
        }
        return offspring;
    }

    private Element crossover(Element b, int crossoverIndex) {
        String d = data.substring(0, crossoverIndex) + b.data.substring(crossoverIndex, b.data.length());
        return new Element(d);
    }

    private void mutate(int mutationIndex) {
        char newValue;
        if(data.charAt(mutationIndex) == '0') { 
            newValue = '1';
        }
        else {
            newValue = '0';
        }

        data = data.substring(0, mutationIndex) 
                + newValue 
                + data.substring(mutationIndex + 1, data.length());
    }

    @Override
    public String toString() {
        return data + "(" + getFitness() + ")";
    }
}

public class Program {

    public static void main(String[] args) {
        Element x = new Element("11111011011100000111");
        Element y = new Element("00110000000001010000");

        System.out.println("Initial elements are: ");
        System.out.println(x.toString());
        System.out.println(y.toString());

        System.out.println("\n");
        System.out.println("🔍  Beginning search for string with equal number of 0s and 1s.");

        Element o;
        do {
            o = x.getOffspring(y);
            System.out.println("Offspring is " + o.toString());
            if(o.getFitness() > x.getFitness()) {
                System.out.println("Replacing x with offspring.");
            }
            else if(o.getFitness() > y.getFitness()) {
                System.out.println("Replacing y with offspring.");
            }
            else {
                System.out.println("Discarding the offspring.");
            }
        } while(o.getFitness() != 20); // repeat until we reach our arbitrary goal

        System.out.println("The ideal solution is: " + o.toString());
    }
}
