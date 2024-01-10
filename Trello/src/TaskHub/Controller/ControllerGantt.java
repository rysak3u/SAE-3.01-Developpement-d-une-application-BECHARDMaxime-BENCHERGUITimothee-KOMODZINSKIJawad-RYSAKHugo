package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControllerGantt implements EventHandler<ActionEvent> {
    // Attribut
    private ModeleTache m;

    /**
     * Constructeur de la classe ControllerGantt
     * @param m
     */
    public ControllerGantt(ModeleTache m){
        this.m=m;
    }

    /**
     * Méthode qui permet d'actualiser le diagramme de Gantt
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        this.m.actualiserGantt();
    }
}
