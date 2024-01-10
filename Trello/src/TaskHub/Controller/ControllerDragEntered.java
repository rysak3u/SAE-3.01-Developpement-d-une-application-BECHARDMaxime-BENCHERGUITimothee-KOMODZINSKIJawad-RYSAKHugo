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
    // Attribut
    private VBox vbox;

    /**
     * Constructeur de la classe ControllerDragEntered
     * @param vbox
     */
    public ControllerDragEntered(VBox vbox) {
        this.vbox = vbox;
    }

    /**
     * Méthode qui va permettre de gérer le drag and drop quand le curseur entre dans la zone de drop
     * @param event
     */
    public void handle(DragEvent event) {
        if (event.getGestureSource() != vbox && event.getDragboard().hasString()) {
            vbox.setBackground(new Background(new BackgroundFill(Color.rgb(75,75,75), new CornerRadii(18), Insets.EMPTY)));
        }

        event.consume();
    }
}
