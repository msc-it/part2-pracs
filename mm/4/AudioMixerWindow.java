import java.awt.*;
import javax.swing.*;
import javax.media.*;

// export JMFHOME=/Library/Java/Extensions/jmf-2.1.1
// export CLASSPATH=${JMFHOME}/lib/jmf.jar:${JMFHOME}/lib/customizer.jar:${CLASSPATH}

class AudioMixerWindow extends JFrame {
    
    AudioMixerWindow() {
        Container container = getContentPane();
        container.setLayout(new GridLayout(1, 2));

        AudioThread audio1 = new AudioThread("../assets/audio.wav");
        AudioThread audio2 = new AudioThread("../assets/audio-2.wav");

        audio1.setBackground(Color.red);
        audio1.add(new JLabel("A"), BorderLayout.CENTER);
        audio2.setBackground(Color.green);
        audio2.add(new JLabel("B"), BorderLayout.CENTER);

        container.add(audio1);
        container.add(audio2);

        audio1.start();
        audio2.start();

        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AudioMixerWindow();
    }
}
