import java.awt.*;

public class DrawingAreaCanvas extends Canvas {

    private Shape shapeToDraw;

    // shape parameters
    private int width, height;
    private int x, y;
    private int[] xs, ys; // for polygonals
    private float opacity = 1;

    private Color color;
    private GradientPaint gradient;

    @Override
    public void paint(Graphics graphics) {

        // Don't do anything if no shape is selected
        if(shapeToDraw == null) {
            return;
        }

        Graphics2D g = (Graphics2D)graphics;

        // Set the graphics parameters
        g.setColor(color);
        g.setPaint(gradient);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

        switch (shapeToDraw) {
            case CIRCLE:
            case ELLIPSE:
                g.fillOval(x, y, width, height);
                break;

            case SQUARE:
            case RECTANGLE:
                g.fillRect(x, y, width, height);
                break;

            case POLYGON:
                g.fillPolygon(xs, ys, xs.length);
                break;
        }
    }

    public void drawEllipse(int x, int y, int width, int height) {
        shapeToDraw = Shape.ELLIPSE;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        repaint();
    }

    public void drawCircle(int x, int y, int diameter) {
        drawEllipse(x, y, diameter, diameter);
    }

    public void drawRectangle(int x, int y, int width, int height) {
        shapeToDraw = Shape.RECTANGLE;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        repaint();
    }

    public void drawSquare(int x, int y, int side) {
        drawRectangle(x, y, side, side);
    }

    public void drawPloygon(int[] x, int[] y) {
        shapeToDraw = Shape.POLYGON;
        this.xs = x;
        this.ys = y;
        repaint();
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    public void setGradient(Color toColor, int gx1, int gy1, int gx2, int gy2) {
        this.gradient = new GradientPaint(gx1, gy1, this.color, gx2, gy2, toColor);
        repaint();
    }

    public void removeGradient() {
        this.gradient = null;
        repaint();
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
        repaint();
    }
}
