package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ControllerNewTableau implements EventHandler<MouseEvent> {
    /** Modele que le controller va appeler*/
    private ModeleTache m;

    public ControllerNewTableau(ModeleTache m) {
        this.m = m;
    }

    @Override
    public void handle(MouseEvent event) {
        this.m.switchFormulaire(3);
    }
}
