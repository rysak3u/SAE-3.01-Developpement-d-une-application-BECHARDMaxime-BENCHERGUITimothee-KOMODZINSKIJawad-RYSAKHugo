package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class ControllerSwitchTableau implements EventHandler<ActionEvent> {
    private ModeleTache m;

    public ControllerSwitchTableau(ModeleTache m) {
        this.m = m;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("test");
        ComboBox<String> b=(ComboBox<String>)event.getSource();
        String f=b.getValue();
        if(m.getTableaux().indexOf(m.findByName(f))!=-1){
            m.setIdTableauCourant(m.getTableaux().indexOf(m.findByName(f)));
        }
    }
}
