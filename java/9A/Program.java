import java.io.*; 
import java.util.*;

public class Program
{
    public static void main(String[] args)throws IOException
    {
        Locale[] allLocales = {
            new Locale("ja", "JP"),
            new Locale("zh", "CN"),
            new Locale("it", "IT"),
            new Locale("de", "DE")
        };
        System.out.println("Printing 'hello' in different languages.\n");
        for(Locale l : allLocales) {
            ResourceBundle rb = ResourceBundle.getBundle("MessageBundle", l); 
            String message = rb.getString("Hello");
            System.out.println(l.getDisplayLanguage() + "(" + l.getDisplayCountry() + ")\t\t" + message);
        }
    }

}
