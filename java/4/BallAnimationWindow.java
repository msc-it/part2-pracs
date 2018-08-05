import javax.swing.*;
import java.awt.*;

public class BallAnimationWindow extends JFrame {

    public BallAnimationWindow() {
        setSize(600, 600);
        setContentPane(new BallAnimationPanel());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Bouncing balls");
        setVisible(true);
    }
}
