import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

/**
 * Run the program with "appletviewer PlayAudio.java"
 */

/**
 * <applet code=PlayAudio left=300 top=300 width=300 height=200></applet>
 */

public class PlayAudio extends JApplet implements ActionListener {

	Container container;
    JButton btnPlay, btnLoop, btnStop;
    AudioClip clip;

    public void init() {
        container = getContentPane();
        container.setLayout(new FlowLayout());

        btnPlay = new JButton("Play");
        btnLoop = new JButton("Loop");
        btnStop = new JButton("Stop");

        btnPlay.addActionListener(this);
        btnLoop.addActionListener(this);
        btnStop.addActionListener(this);

        container.add(btnPlay);
        container.add(btnLoop);
        container.add(btnStop);

        try {
            clip = getAudioClip(getCodeBase(), "audio.wav");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR!");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPlay) {
            clip.play();
        }

        if (e.getSource() == btnLoop) {
            clip.loop();
        }

        if (e.getSource() == btnStop) {
            clip.stop();
        }
    }
}
