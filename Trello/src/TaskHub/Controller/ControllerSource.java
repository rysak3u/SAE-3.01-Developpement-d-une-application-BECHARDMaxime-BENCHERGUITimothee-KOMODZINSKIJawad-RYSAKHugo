package TaskHub.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class ControllerSource implements EventHandler<MouseEvent>{
    @Override
    public void handle(MouseEvent mouseEvent) {
        VBox source = (VBox) mouseEvent.getSource();
        Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
        db.setDragView(source.snapshot(null, null));
        mouseEvent.consume();
    }

}
