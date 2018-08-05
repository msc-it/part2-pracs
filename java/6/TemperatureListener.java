import java.util.EventListener;

public interface TemperatureListener extends EventListener {
    void temperatureChanged(TemperatureEvent e);
}