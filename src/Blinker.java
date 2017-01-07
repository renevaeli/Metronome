import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by Rene on 07/01/2017.
 */
public class Blinker {
    Circle blinker;
    DropShadow glowingEdge;

    public Blinker() {
        Color blinkColor = Color.web("189EC5");

        glowingEdge = new DropShadow();
        glowingEdge.setColor(blinkColor);
        glowingEdge.setRadius(0);

        blinker = new Circle(25);

        blinker.setFill(blinkColor);
        blinker.setStroke(blinkColor);
        blinker.setEffect(glowingEdge);

    }

    public void isOn() {
        glowingEdge.setRadius(30);
        blinker.setEffect(glowingEdge);
    }
    public void isOff() {
        glowingEdge.setRadius(0);
        blinker.setEffect(glowingEdge);
    }

}
