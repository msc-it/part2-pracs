import java.awt.*;
import java.util.*;
import javax.swing.*;

public class BallAnimationPanel extends JPanel implements Runnable {

    Ball[] balls;
    Thread thread = new Thread(this);

    public BallAnimationPanel() {
        setSize(600,600);
        balls = getRandomBalls(30);
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        // Draw all the balls
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }

    @Override
    public void run() {
        while(true) {
            // update the balls
            for (Ball ball : balls) {
                ball.update(this);
            }
            // repaint the screen
            repaint();
            try {
                Thread.sleep(40);
            } catch(InterruptedException ex) { 
                ex.printStackTrace();
            }
        }
    }

    // Creates an array of red balls with random location coordinates
    private Ball[] getRandomBalls(int number) {
        Ball[] balls = new Ball[number];
        Random r = new Random();
        for(int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(
                r.nextInt(getWidth()), 
                r.nextInt(getHeight())
            );
        }
        return balls;
    }

}
