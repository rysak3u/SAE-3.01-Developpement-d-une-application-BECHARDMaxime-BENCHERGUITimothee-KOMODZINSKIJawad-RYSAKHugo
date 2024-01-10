package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControllerAfficherFormulaireDependance implements EventHandler<MouseEvent> {
    /** Modele que le controller va appeler*/
    private ModeleTache m;

    /**
     * Constructeur de la classe ControllerAfficherFormulaireDependance
     * @param m
     */
    public ControllerAfficherFormulaireDependance(ModeleTache m) {
        this.m = m;
    }

    /**
     * Méthode qui va permettre d'afficher le formulaire de dépendance
     * @param event
     */
    @Override
    public void handle(MouseEvent event) {
        this.m.switchFormulaire(4);
    }
}
