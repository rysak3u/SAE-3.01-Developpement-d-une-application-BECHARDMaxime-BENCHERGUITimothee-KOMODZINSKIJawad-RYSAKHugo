package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
        // On récupère le bouton sur lequel on a cliqué
        Button b = (Button) mouseEvent.getSource();
        // Si le bouton est le bouton créer Sous-tâche alors on met l'attribut sousTache à true
        if (b.getText().equals("Créer Sous-Tâche")) {
            m.setSousTache(true);
        }
        // On affiche le formulaire
        this.m.changerColonneSelectionner(id_colonne);
        this.m.switchFormulaire(1);
    }
}
