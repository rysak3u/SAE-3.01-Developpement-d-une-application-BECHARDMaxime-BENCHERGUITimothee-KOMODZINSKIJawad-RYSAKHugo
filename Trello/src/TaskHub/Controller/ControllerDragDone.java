package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class ControllerDragDone implements EventHandler<DragEvent>{
    private Conteneur colonne;
    private TacheMere tache;
    private ModeleTache modele;

    public ControllerDragDone(Conteneur colonne, TacheMere tache, ModeleTache modele) {
        this.colonne = colonne;
        this.tache = tache;
        this.modele = modele;
    }

    public void handle(DragEvent event) {
        if (event.getTransferMode() == TransferMode.MOVE) {
            colonne.getTaches().remove(tache);
        }
        modele.notifierObservateur();
        event.consume();
    }
}
