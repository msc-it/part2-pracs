import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

/*
<applet code="ShapeCounterApplet" width=500 height=500>
</applet>
*/

public class ShapeCounterApplet extends JApplet {

    // The shape container.
    // All shapes will be added to this container
    // every time a button is clicked.
    private JPanel shapeContainer = new JPanel();
    private Shape lastShape = Shape.SQUARE;
    private int counter = 0;
    private JLabel lblCount = new JLabel();

    AudioClip[] audioClips;

    @Override
    public void init() {
        setLayout(new BorderLayout());

        // Add the shape container to the center
        add(shapeContainer, BorderLayout.CENTER);

        // Initialize the buttons
        JButton btnCircle = new JButton("Circle");
        btnCircle.addActionListener(new ShapeButtonActionListener(Shape.CIRCLE));
        JButton btnSquare = new JButton("Square");
        btnSquare.addActionListener(new ShapeButtonActionListener(Shape.SQUARE));
        JButton btnRectangle = new JButton("Rectangle");
        btnRectangle.addActionListener(new ShapeButtonActionListener(Shape.RECTANGLE));
        JButton btnTriangle = new JButton("Triangle");
        btnTriangle.addActionListener(new ShapeButtonActionListener(Shape.TRIANGLE));

        // Add the buttons to a toolbar panel ...
        JPanel toolbar = new JPanel();
        toolbar.add(btnCircle);
        toolbar.add(btnSquare);
        toolbar.add(btnRectangle);
        toolbar.add(btnTriangle);

        // ... and add the toolbar panel to the top of the layout
        this.add(toolbar, BorderLayout.NORTH);
        this.add(lblCount, BorderLayout.SOUTH);

        // Load the audio files, in the order of the shapes
        audioClips = new AudioClip[] {
            getAudioClip(getCodeBase(), "./assets/audio.wav"),
            getAudioClip(getCodeBase(), "./assets/audio-2.wav"),
            getAudioClip(getCodeBase(), "./assets/audio.wav"),
            getAudioClip(getCodeBase(), "./assets/audio-2.wav"),
        };
    }

    class ShapeButtonActionListener implements ActionListener {
        private Shape shape;
        ShapeButtonActionListener(Shape shape) {
            this.shape = shape;
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            if(lastShape != this.shape){
                // this is not the same as the previously selected shape
                // let's clear the existing shapes
                shapeContainer.removeAll();
                // and reset the counter
                counter = 0;
            } 
        
            lastShape = shape; // update the lastshape flag
            shapeContainer.add(new ShapePanel(shape)); // Add the correct shape to the container
            counter++; // and update the counter
            lblCount.setText(shape.toString() + " count: " + counter);

            // and update the shape container
            shapeContainer.revalidate();

            // Play the clip for the shape
            audioClips[shape.ordinal()].play();
        }
    }
}
