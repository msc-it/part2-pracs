import java.io.*;

public class Program {

    private static String encrypt(String str) {
        StringBuilder sb = new StringBuilder();
        for(char c : str.toCharArray()) {
            int offset = 0;
            int ch = (int)c;
            if(Character.isUpperCase(c)) {
                offset = (int)'A';
            }
            else {
                offset = (int)'a';
            }
            char e = (char)((ch - offset + 5) % 26 + offset);
            sb.append(e);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String text = "Hey there Delilah whats it like in New York city";
        System.out.println("Plain text:");
        System.out.println(text);

        System.out.println();
        System.out.println("Encrypted text:");
        System.out.println(encrypt(text));
    }
}