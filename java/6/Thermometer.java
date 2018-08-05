import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

public class Thermometer extends JSlider implements ChangeListener {

    private Vector<TemperatureListener> listeners = new Vector<>();

    public Thermometer(int min, int max, int initial) {
        super(min, max, initial);
        addChangeListener(this);
    }

    public int getTemperature() {
        return getValue();
    }

    public void setTemperature(int i) {
        setValue(i);
    }

    public void setup() {
        setPaintTicks(true);
        setPaintLabels(true);
        setMinorTickSpacing(1);
        setMajorTickSpacing(5);
    }

    public synchronized void addTemperatureListener(TemperatureListener tl) {
        listeners.addElement(tl);
    }

    public synchronized void removeAllTemperatureListeners(TemperatureListener tl) {
        listeners.removeAllElements();
    }

    public void stateChanged(ChangeEvent ce) {
        TemperatureEvent te = new TemperatureEvent(this);
        for(TemperatureListener listener : listeners) {
            listener.temperatureChanged(te);
        }
    }
}
