import java.io.*; 
import java.util.*; 
import java.text.*; 

public class Program
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("\nEnter the Number to format : ");

        Scanner sc = new Scanner(System.in);
        double num = Double.parseDouble(sc.nextLine());

        Locale[] allLocales = {
            new Locale("ja", "JP"),
            new Locale("zh", "CN"),
            new Locale("it", "IT"),
            new Locale("de", "DE")
        };

        System.out.println();

        for(Locale l : allLocales) { 
            NumberFormat nf = NumberFormat.getCurrencyInstance(l); 
            System.out.println(l.getDisplayCountry() + "\t(" + l.getDisplayLanguage() + ")\t" + nf.format(num));
        }
    }
}