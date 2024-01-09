package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class ControllerDragDetected implements EventHandler<MouseEvent> {
    private VBox vboxt;
    private ModeleTache modele;
    private TacheMere tache;

    public ControllerDragDetected(VBox vboxt, ModeleTache modele, TacheMere tache) {
        this.vboxt = vboxt;
        this.modele = modele;
        this.tache = tache;
    }

    public void handle(MouseEvent event) {
        Dragboard db = vboxt.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(tache.toString());
        db.setContent(content);

        modele.setTacheDrag(tache);

        event.consume();
    }
}
