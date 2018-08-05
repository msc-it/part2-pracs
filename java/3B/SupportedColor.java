import java.awt.Color;

public enum SupportedColor {
    Black(Color.BLACK), 
    White (Color.WHITE), 
    Light_Gray(Color.LIGHT_GRAY), 
    Gray(Color.GRAY), 
    Dark_Gray(Color.DARK_GRAY), 
    Red(Color.RED), 
    Pink(Color.PINK), 
    Orange(Color.ORANGE), 
    Yellow(Color.YELLOW), 
    Green(Color.GREEN), 
    Magenta(Color.MAGENTA), 
    Cyan(Color.CYAN), 
    Blue(Color.BLUE);
    
    private Color color;
    private SupportedColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
