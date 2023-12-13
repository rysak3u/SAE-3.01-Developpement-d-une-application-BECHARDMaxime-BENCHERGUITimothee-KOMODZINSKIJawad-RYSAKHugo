package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
/**
 * Controller qui va permettre d'afficher le formulaire
 */
public class ControllerAfficherFormulaire implements EventHandler<MouseEvent> {
   /** Modele que le controller va appeler*/
    private ModeleTache m;

    public ControllerAfficherFormulaire(ModeleTache m) {
        this.m = m;
    }
    /**
     * @param mouseEvent
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        //on affiche le formulaire
        this.m.afficherFormulaire();
    }
}
