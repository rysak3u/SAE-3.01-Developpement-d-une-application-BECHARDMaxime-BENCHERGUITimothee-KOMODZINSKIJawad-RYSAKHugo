package TaskHub.Controller;

import TaskHub.Controller.ControllerRetour;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class ControllerDragOver implements EventHandler<DragEvent> {
    private VBox vbox;
    public ControllerDragOver(VBox vbox) {
        this.vbox = vbox;
    }

    /**
     * Méthode qui va permettre de gérer le drag and drop
     * @param dragEvent
     */
    @Override
    public void handle(DragEvent dragEvent) {
            if (dragEvent.getGestureSource() != vbox &&
                    dragEvent.getDragboard().hasString()) {
                dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            dragEvent.consume();
    }

}

