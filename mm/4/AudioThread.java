import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import javax.media.*;

class AudioThread extends JPanel implements Runnable {

    private Thread thread;
    private Player player;

    AudioThread(String audioPath) {
        setLayout(new BorderLayout());

        try {
            File f = new File(audioPath);
            URL url = f.toURL();
            player = Manager.createRealizedPlayer(url);
            add(player.getControlPanelComponent(), BorderLayout.SOUTH);
        } catch (Exception e) {
            System.out.println("Exception while running Song : " + audioPath + ".\n" + e.getMessage());
        }

        thread = new Thread(this);
        System.out.println("Thread for Song " + audioPath + " is created.");
    }

    public void run() {
        player.start();
        System.out.println("Player started");
    }

    public void start() {
        thread.start();
        System.out.println("Thread started");
    }
}
