package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControllerNewColonne implements EventHandler<ActionEvent>{

    private ModeleTache m;

    public ControllerNewColonne(ModeleTache m){
        this.m = m;
    }

    @Override
    public void handle(ActionEvent event) {
        Button b = (Button) event.getSource();
        this.m.switchFormulaire(2);
    }
}
