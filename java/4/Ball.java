import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Ball {

    // Constants
    final static int SPEED = 5;
    final static int DIAMETER = 30;

    // the dimensions of the ball
    public int width = DIAMETER;
    public int height = DIAMETER;

    // the position coordinates of the ball
    public int x = 0;
    public int y = 0;

    // the rate at which the ball moves along each axis
    // positive values for right/down
    // negative values for left/up
    public int dx = SPEED;
    public int dy = -SPEED;

    public Color color = Color.BLACK;

    public Ball() {

    }

    public Ball(int x, int y) {
        this(Color.BLACK, x, y);
        setRandomColor();
    }

    public Ball(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    // updates the balls position
    public void update(Component component) {
        // update the ball's coordinates
        x += dx;
        y += dy;

        // Change the directions if the ball moves out of bounds
        if(x <=0 || x + width >= component.getWidth()) {
            dx *= -1;
        }
        if(y <= 0 || y + height >= component.getHeight()) {
            dy *= -1;
        }
    }

    // draws the ball
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }

    // changes the color of the ball to a random color
    public void setRandomColor() {
        this.color = new Color(
            getRandomColorComponent(), 
            getRandomColorComponent(), 
            getRandomColorComponent()
        );
    }

    // helper method to generate a random color component
    // (a random number between 0 and 255)
    private static int getRandomColorComponent() {
        return (int) Math.round(Math.random() * 255);
    }
}
