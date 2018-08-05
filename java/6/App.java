import javax.swing.*;
import java.awt.*;

public class App extends JFrame implements TemperatureListener {

    private JLabel label;
    private Thermometer thermometer;

    public App() {
        thermometer = new Thermometer(-5, 100, 30);
        thermometer.setup();
        thermometer.addTemperatureListener(this);

        label = new JLabel(getTemperatureString(), JLabel.CENTER);
        label.setFont(new Font("Arial Black", Font.BOLD, 80)); 

        Container container = getContentPane();
        container.add(thermometer, BorderLayout.SOUTH);
        container.add(label,BorderLayout.CENTER);

        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Thermometer");
        setVisible(true);
    }

    @Override
    public void temperatureChanged(TemperatureEvent e) {
        label.setText(getTemperatureString());
    }

    private String getTemperatureString() {
        return "<html>"+thermometer.getTemperature()+"<sup>o</sup> C<html>";
    }
}
