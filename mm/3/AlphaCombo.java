import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <applet code=AlphaCombo width=300 height=320></applet>
 */

public class AlphaCombo extends JApplet implements ActionListener {

    private final static String[] FILENAMES = { "A", "B", "C", "D", "E" };

    JComboBox combo;
    AudioClip clip;
    JLabel lblShow;

    public void init() {
        Container container = getContentPane();
        combo = new JComboBox();
        combo.addItem("Select alphabet");
        for(String s: FILENAMES) {
            combo.addItem(s);
        }

        container.add(combo, BorderLayout.NORTH);
        combo.addActionListener(this);
        lblShow = new JLabel();
        container.add(lblShow, BorderLayout.CENTER);
    }
    
    public void actionPerformed(ActionEvent e){
        lblShow.setIcon(new ImageIcon(combo.getSelectedItem().toString() + ".png"));
        if(clip == null) {
            clip = getAudioClip(getCodeBase(), combo.getSelectedItem().toString()+".wav");
        }        
        clip.play();
    }
}
