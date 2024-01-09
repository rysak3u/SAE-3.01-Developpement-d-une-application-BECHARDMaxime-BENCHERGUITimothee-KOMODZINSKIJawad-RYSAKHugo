package TaskHub.Controller;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class ControllerDragEntered implements EventHandler<DragEvent> {
    private VBox vbox;

    public ControllerDragEntered(VBox vbox) {
        this.vbox = vbox;
    }

    public void handle(DragEvent event) {
        if (event.getGestureSource() != vbox && event.getDragboard().hasString()) {
            vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        }

        event.consume();
    }
}
