package TaskHub.Controller;

import TaskHub.Exception.TacheNomVideException;
import TaskHub.Modele.ModeleTache;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/** Controller qui va permettre de Créer une tâche
 * ATTENTION A NE OAS CONFONDRE AVEC LE CONTROLLER QUI VA PERMETTRE D'AFFICHER LE FORMULAIRE*/
public class ControllerCréerTache implements EventHandler<MouseEvent> {
    /** Modele que le controller va appeler*/
    private ModeleTache m;
    /**
     * TextField name Contient le nom de la tache que l'Utilisateur a donné
     * TextField desc Contient la description de la tache que l'Utilisateur a éventuellement donner
     */
    private TextField name,desc;

    public ControllerCréerTache(ModeleTache m, TextField name, TextField desc){
        this.m=m;
        this.name=name;
        this.desc=desc;
    }

    /**
     * @param mouseEvent
     */
    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
