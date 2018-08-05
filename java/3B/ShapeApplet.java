import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
<applet code="ShapeApplet" width=1200 height=660> </applet>
*/

public class ShapeApplet extends JApplet {

    // The main drawing area
    DrawingAreaCanvas canvas;

    /**
     * The top toolbar
     */
    JSlider sliderOpacity = new JSlider(0, 100);

    /**
     * The bottom shape toolbar components
     */
    JRadioButton radioOutline = new JRadioButton("Outline"), 
        radioFill = new JRadioButton("Fill");

    JButton btnCircle = new JButton("Circle"), 
        btnSquare = new JButton("Square"),
        btnRect = new JButton("Rectangle"),
        btnEllipse = new JButton("Ellipse"), 
        btnPolygon = new JButton("Polygon");

    /**
     * The top toolbar
     */

    JComboBox<SupportedColor> comboPrimaryColor = new JComboBox<>(SupportedColor.values()),
        comboSecondaryColor = new JComboBox<>(SupportedColor.values());
    JCheckBox cboxIsGradient = new JCheckBox("Gradient");
    JSpinner spinnerGx1, spinnerGy1, spinnerGx2, spinnerGy2;
    JPanel gradientOptions;

    @Override
    public void init() {
        // Initialize the main drawing area
        canvas = new DrawingAreaCanvas();
        /**
         * Setup the color toolbar
         */

        comboPrimaryColor.addActionListener(new ColorSelectionListener());
        comboSecondaryColor.setEnabled(false);

        // Setup the spinners
        spinnerGx1 = new JSpinner(new SpinnerNumberModel(0, 0, getWidth(), 10));
        spinnerGy1 = new JSpinner(new SpinnerNumberModel(0, 0, getHeight(), 10));
        spinnerGx2 = new JSpinner(new SpinnerNumberModel(0, 0, getWidth(), 10));
        spinnerGy2 = new JSpinner(new SpinnerNumberModel(0, 0, getHeight(), 10));

        // Setup the gradient options
        gradientOptions = new JPanel();
        gradientOptions.add(new JLabel("(From) x:"));
        gradientOptions.add(spinnerGx1);
        gradientOptions.add(new JLabel("y:"));
        gradientOptions.add(spinnerGy1);
        gradientOptions.add(comboSecondaryColor);
        gradientOptions.add(new JLabel("(To) x:"));
        gradientOptions.add(spinnerGx2);
        gradientOptions.add(new JLabel("y:"));
        gradientOptions.add(spinnerGy2);
        setGradientOptionsEnabled(false);

        // Add the components to the color toolbar
        JPanel colorToolbar = new JPanel();
        colorToolbar.add(comboPrimaryColor);
        colorToolbar.add(cboxIsGradient);
        colorToolbar.add(gradientOptions);

        /**
         * Setup the opacity slider
         */
        
        // customize the slider
        sliderOpacity.setMajorTickSpacing(5);
        sliderOpacity.setMinorTickSpacing(1);
        sliderOpacity.setPaintTicks(true);
        sliderOpacity.setPaintLabels(true);
        sliderOpacity.setValue(100);
        sliderOpacity.setOrientation(JSlider.VERTICAL);

        // set the event handler
        sliderOpacity.addChangeListener(new OpacityChangeListener());

        /**
         * Setup the shape toolbar
         */

        // Add the draw/fill radio buttons to a button group
        // so that only one of them can be selected at a time
        ButtonGroup btngroupDrawType = new ButtonGroup();
        btngroupDrawType.add(radioOutline);
        btngroupDrawType.add(radioFill);
        radioOutline.setSelected(true);

        // Set the event handlers for the shape buttons
        btnCircle.addActionListener(new ShapeActionListener(Shape.CIRCLE));
        btnRect.addActionListener(new ShapeActionListener(Shape.RECTANGLE));
        btnSquare.addActionListener(new ShapeActionListener(Shape.SQUARE));
        btnEllipse.addActionListener(new ShapeActionListener(Shape.ELLIPSE));
        btnPolygon.addActionListener(new ShapeActionListener(Shape.POLYGON));

        // Add the components to the shape toolbar
        JPanel shapeToolbar = new JPanel();
        shapeToolbar.add(radioOutline);
        shapeToolbar.add(radioFill);
        shapeToolbar.add(btnCircle);
        shapeToolbar.add(btnSquare);
        shapeToolbar.add(btnRect);
        shapeToolbar.add(btnEllipse);
        shapeToolbar.add(btnPolygon);

        /**
         * Add the components and toolbars to the applet
         */
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);
        this.add(shapeToolbar, BorderLayout.SOUTH);
        this.add(sliderOpacity, BorderLayout.EAST);
        this.add(colorToolbar, BorderLayout.NORTH);
    }

    // helper method to enable/disable gradient options
    private void setGradientOptionsEnabled(boolean isEnabled) {
        for (Component component : gradientOptions.getComponents()) {
            component.setEnabled(isEnabled);
        }
    }

    // Logic for the shape buttons click event
    class ShapeActionListener implements ActionListener {

        private Shape shape;
        ShapeActionListener(Shape shape) {
            this.shape = shape;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (shape) {
                case CIRCLE:
                    canvas.drawCircle(200, 200, 200);
                    break;
                case SQUARE:
                    canvas.drawSquare(200, 200, 200);
                    break;
                case RECTANGLE:
                    canvas.drawRectangle(200, 200, 400, 200);
                    break;
                case ELLIPSE:
                    canvas.drawEllipse(200, 200, 400, 200);
                    break;
                case POLYGON:
                    canvas.drawPloygon(
                        new int[]{ 100, 200, 220, 150, 100 }, 
                        new int[]{ 100, 100, 200, 300, 100 }
                    );
                    break;
            
                default:
                    break;
            }
        }
    }

    // Logic for the color combo box selection event
    class ColorSelectionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SupportedColor c = (SupportedColor)comboPrimaryColor.getSelectedItem();
            canvas.setColor(c.getColor());
        }
    }

    // Logic for the opacity slider change event
    class OpacityChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            float opacity = sliderOpacity.getValue() / 100f;
            canvas.setOpacity(opacity);
        }
    }

}
