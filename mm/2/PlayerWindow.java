import java.io.*;
import javax.media.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class PlayerWindow extends JFrame implements Runnable {
    Container container;
    Thread thread;
    String streamType;
    Player player;

    public PlayerWindow(String typename) {
        streamType = typename;
        thread = new Thread(this, streamType);
        System.out.println("Thread for " + streamType + "is created.");
        thread.start();
    }

    public void run() {
        container = getContentPane();

        if(streamType.equals("audio")) {    
            try {
                File file = new File("../assets/audio.wav");
                URL url = file.toURL();
                player = Manager.createRealizedPlayer(url);

                Component cpc = player.getControlPanelComponent();
                container.add(cpc, BorderLayout.CENTER);
            }
            catch(Exception e) {
                System.out.println("Exception occurred in Audio Stream: " + e.getMessage());
            }

            setSize(800, 50);
        }

        if(streamType.equals("video")) {
            try {
                File file = new File("../1/A.mpg");
                URL url = file.toURL();
                player = Manager.createRealizedPlayer(url);

                Component cpc = player.getControlPanelComponent();
                container.add(cpc, BorderLayout.SOUTH);
                Component vc = player.getVisualComponent();
                container.add(vc, BorderLayout.CENTER);
            }
            catch(Exception e) {
                e.printStackTrace();
                System.out.println("Exception occurred in Audio Stream: " + e.getMessage());
            }
            setSize(500,500);
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        player.start();
    }
}


/**
 * http://www.oracle.com/technetwork/java/javase/formats-138492.html
 * http://hubblesource.stsci.edu/sources/video/clips/
 * https://www.steppublishers.com/sample-video
 * http://www.oracle.com/technetwork/java/javase/issues-139827.html
 */
