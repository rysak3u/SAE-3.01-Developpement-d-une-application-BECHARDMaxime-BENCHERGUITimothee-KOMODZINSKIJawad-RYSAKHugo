package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControllerAfficherFormulaireDependance implements EventHandler<MouseEvent> {
    /** Modele que le controller va appeler*/
    private ModeleTache m;

    public ControllerAfficherFormulaireDependance(ModeleTache m) {
        this.m = m;
    }

    @Override
    public void handle(MouseEvent event) {
        this.m.switchFormulaire(4);
    }
}
