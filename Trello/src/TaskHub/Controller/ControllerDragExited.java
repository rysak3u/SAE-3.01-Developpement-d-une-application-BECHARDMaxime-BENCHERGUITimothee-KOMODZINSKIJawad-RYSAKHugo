package TaskHub.Controller;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ControllerDragExited implements EventHandler<DragEvent> {
    private VBox vbox;

    public ControllerDragExited(VBox vbox) {
        this.vbox = vbox;
    }

    public void handle(DragEvent event) {
        vbox.setBackground(new Background(new BackgroundFill(Color.rgb(228,228,228), new CornerRadii(18), Insets.EMPTY)));
        event.consume();
    }
}
