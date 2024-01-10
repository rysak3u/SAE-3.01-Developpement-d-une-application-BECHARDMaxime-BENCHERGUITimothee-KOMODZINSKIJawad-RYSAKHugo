package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class ControllerSwitchTableau implements EventHandler<ActionEvent> {
    // Attribut
    private ModeleTache m;

    /**
     * Constructeur de la classe ControllerSwitchTableau
     * @param m
     */
    public ControllerSwitchTableau(ModeleTache m) {
        this.m = m;
    }

    /**
     * Méthode qui permet de changer le tableau courant en fonction de la valeur de la ComboBox
     * @param event
     */
    @Override
    public void handle(ActionEvent event) {
        ComboBox<String> b=(ComboBox<String>)event.getSource();
        String f=b.getValue();
        if(m.getTableaux().indexOf(m.findByName(f))!=-1){
            m.setIdTableauCourant(m.getTableaux().indexOf(m.findByName(f)));
        }
    }
}
