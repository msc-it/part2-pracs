import java.awt.*;
import java.applet.*;
import javax.swing.*;

/*
<applet code="NamesApplet" width=300 height=300> </applet>
*/

public class NamesApplet extends JApplet {

    private final static String FONT_NAME = "PT Serif";

    public void paint(Graphics g) {

        g.drawString("Practicals", this.getWidth() / 2 - 20, this.getHeight() / 2 - 50);

        g.setFont(new Font(FONT_NAME, Font.BOLD, 16));
        g.drawString("Practicals", this.getWidth() / 2 - 30, this.getHeight() / 2 - 25);

        g.setFont(new Font(FONT_NAME, Font.ITALIC, 24));
        g.drawString("Practicals", this.getWidth() / 2 - 40, this.getHeight() / 2);

        g.setFont(new Font(FONT_NAME, Font.BOLD + Font.ITALIC, 28));
        g.drawString("Practicals", this.getWidth() / 2 - 50, this.getHeight() / 2 + 25);
    }
}
