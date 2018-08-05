
import java.awt.*;
import javax.swing.*;

/*
<applet code="movieClip" width=800 height=600>
</applet>
*/

public class MovieClipApplet extends JApplet implements Runnable {
    Container cont;
    Thread t;
    int imgCount = 0;
    int totalImages = 9;
    JLabel l;
    MediaTracker tracker;
    int tracked = 1;

    public void init() {
        cont = getContentPane();
        cont.setBackground(Color.black);
        l = new JLabel(new ImageIcon("./assets/" + imgCount + ".jpg"));
        cont.add(l, BorderLayout.CENTER);

        tracker = new MediaTracker(this);

        for (int i = 0; i < totalImages; i++) {
            tracker.addImage((new ImageIcon("./assets/" + i + ".jpg")).getImage(), tracked);
            tracked++;
        }
    }

    public void start() {
        t = new Thread(this);
        t.start();
    }

    public void run() {
        String loaded = "";
        int donecount = 0;

        while (true) {
            for (int i = 0; i < tracked; i++) {
                if (tracker.checkID(i, true)) {
                    donecount++;
                    loaded += "./assets/" + i + ".jpg, ";
                }
            }
            System.out.println("donecount : " + donecount);
            System.out.println("Tracked : " + tracked);
            if (donecount == tracked) {
                if (imgCount < totalImages) {
                    imgCount++;
                    l.setText(null);
                    l.setIcon(new ImageIcon("./assets/" + imgCount + ".jpg"));
                } else if (imgCount == totalImages) {
                    l.setText(null);
                    l.setIcon(new ImageIcon("./assets/" + imgCount + ".jpg"));
                    imgCount = 0;
                }
            } else {
                l.setIcon(null);
                l.setText("Loaded Images are : " + loaded);
            }
            donecount = 0;
            loaded = "";

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}