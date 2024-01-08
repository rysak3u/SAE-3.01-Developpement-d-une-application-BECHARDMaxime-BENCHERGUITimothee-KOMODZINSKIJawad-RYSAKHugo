package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Controller qui va permettre d'afficher le formulaire pour créer une nouvelle colonne
 */
public class ControllerNewColonne implements EventHandler<ActionEvent>{

    /**
     * Modele que le controller va appeler
     */
    private ModeleTache m;

    /**
     * @param m Modele que le controller va appeler
     */
    public ControllerNewColonne(ModeleTache m){
        this.m = m;
    }

    /**
     * Méthode qui va permettre d'afficher le formulaire pour créer une nouvelle colonne
     *
     * @param event
     */
    @Override
    public void handle(ActionEvent event) {
        Button b = (Button) event.getSource();
        this.m.switchFormulaire(2);
    }
}
