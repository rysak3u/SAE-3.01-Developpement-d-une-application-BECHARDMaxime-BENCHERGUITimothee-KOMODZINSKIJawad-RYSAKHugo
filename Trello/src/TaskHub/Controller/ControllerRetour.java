package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControllerRetour implements EventHandler<ActionEvent> {

    private ModeleTache modeleTache;

    public ControllerRetour(ModeleTache modeleTache) {
        this.modeleTache = modeleTache;
    }

    @Override
    public void handle(ActionEvent event) {
        this.modeleTache.setTacheSelectionner(null);
    }
}
