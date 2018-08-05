import java.awt.*;
import javax.swing.*;

public class Program extends JFrame implements Runnable
{
    int currentImage = 0;
    int numberOfImages = 9;

    JLabel label;

    public Program()
    {
        Container container = getContentPane();
        container.setBackground(Color.black);
        label = new JLabel(new ImageIcon(currentImage + ".gif"));
        container.add(label, BorderLayout.CENTER);

        setSize(550, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        // start the thread
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run()
    {
        while (true)
        {
            // Display the current image
            label.setIcon(new ImageIcon(currentImage + ".gif"));
    
            // wait for 100 ms
            try
            {
                Thread.sleep(100);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // update the current image counter
            currentImage = (currentImage + 1) % numberOfImages; 
        }
    }

    public static void main(String[] args)
    {
        new Program();
    }
}
