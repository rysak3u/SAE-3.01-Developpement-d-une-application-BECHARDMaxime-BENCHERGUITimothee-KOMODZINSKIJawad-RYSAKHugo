package TaskHub;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.VBox;

public class ControllerDrop implements EventHandler<DragEvent> {
    @Override
    public void handle(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        Point2D point = ((VBox) dragEvent.getSource()).sceneToLocal(dragEvent.getSceneX(), dragEvent.getSceneY());
        ((VBox) dragEvent.getSource()).getChildren();

    }
}
