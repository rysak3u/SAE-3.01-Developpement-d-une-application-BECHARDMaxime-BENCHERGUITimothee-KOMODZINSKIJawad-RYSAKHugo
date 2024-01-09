package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControllerGantt implements EventHandler<ActionEvent> {
    private ModeleTache m;

    public ControllerGantt(ModeleTache m){
        this.m=m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        //TODO
    }
}
