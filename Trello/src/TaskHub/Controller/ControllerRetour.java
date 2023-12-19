package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Controller qui va permettre de retourner à la vue principale
 */
public class ControllerRetour implements EventHandler<ActionEvent> {

    /**
     * Modele que le controller va appeler
     */
    private ModeleTache modeleTache;

    /**
     * Constructeur de la classe ControllerRetour
     * @param modeleTache Modele que le controller va appeler
     */
    public ControllerRetour(ModeleTache modeleTache) {
        this.modeleTache = modeleTache;
    }

    /**
     * Méthode qui va permettre de retourner à la vue principale
     * @param event
     */
    @Override
    public void handle(ActionEvent event) {
        // On met l'attribut tacheSelectionner à null
        this.modeleTache.setTacheSelectionner(null);
    }
}
