import java.awt.*;
import javax.swing.*;

// The supported shapes
enum Shape {
    CIRCLE,
    SQUARE,
    RECTANGLE,
    TRIANGLE
}

// Displays a shape
public class ShapePanel extends Canvas  {

    private final static int WIDTH = 30;
    private final static int HEIGHT = 30;

    private Shape shape;
    public ShapePanel(Shape shape) {
        this.shape = shape;
        setSize(WIDTH * 2 + 10, HEIGHT + 10);
    }

    @Override
    public void paint(Graphics g) {
        switch(shape) {
            case CIRCLE:
                g.drawOval(0, 0, WIDTH, HEIGHT);
            break;
            case SQUARE:
                g.drawRect(0, 0, WIDTH, HEIGHT);
            break;
            case RECTANGLE:
                g.drawRect(0, 0, WIDTH * 2, HEIGHT);
            break;
            case TRIANGLE:
                g.drawLine(0, HEIGHT, WIDTH, HEIGHT); // bottom line
                g.drawLine(0, HEIGHT, WIDTH/2, 0); // left line
                g.drawLine(WIDTH/2, 0, WIDTH, HEIGHT); // right line
            break;
        }
    }
}