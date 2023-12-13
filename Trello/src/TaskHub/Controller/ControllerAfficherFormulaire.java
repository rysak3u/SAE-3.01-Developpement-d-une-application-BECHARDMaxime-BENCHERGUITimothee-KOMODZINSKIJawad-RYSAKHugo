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
    private int id_colonne;

    public ControllerAfficherFormulaire(ModeleTache m, int id_colonne) {
        this.m = m;
        this.id_colonne = id_colonne;
    }
    /**
     * @param mouseEvent
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        //on affiche le formulaire
        this.m.changerColonneSelectionner(id_colonne);
        this.m.afficherFormulaire();
    }
}
