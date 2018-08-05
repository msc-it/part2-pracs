import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.*;
import java.io.*;

class PlayVideo extends JFrame {

    PlayVideo() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        try {
            File file = new File("step.mov");
            URL url = file.toURL();
            Player player = Manager.createRealizedPlayer(url);

            Component controlPanelComponent = player.getControlPanelComponent();
            container.add(controlPanelComponent, BorderLayout.SOUTH);

            Component videoComponent = player.getVisualComponent();
            container.add(videoComponent, BorderLayout.CENTER);

            player.start();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PlayVideo();
    }

}
