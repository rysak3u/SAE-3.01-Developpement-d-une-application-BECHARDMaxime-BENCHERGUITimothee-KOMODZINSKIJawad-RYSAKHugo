package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControllerAccesArchive implements EventHandler<ActionEvent> {

    private ModeleTache modeleTache;

    public ControllerAccesArchive(ModeleTache modeleTache) {
        this.modeleTache = modeleTache;
    }

    @Override
    public void handle(ActionEvent event) {

    }
}
