package TaskHub.Controller;

import TaskHub.Exception.TacheNomVideException;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/** Controller qui va permettre de Créer une tâche
 * ATTENTION A NE OAS CONFONDRE AVEC LE CONTROLLER QUI VA PERMETTRE D'AFFICHER LE FORMULAIRE*/
public class ControllerCréerTache implements EventHandler<MouseEvent> {
    /**
     * Modele que le controller va appeler
     */
    private ModeleTache m;
    /**
     * TextField name Contient le nom de la tache que l'Utilisateur a donné
     * TextField desc Contient la description de la tache que l'Utilisateur a éventuellement donner
     */
    private TextField name, desc;



    /**
     * @param m    Modele que le controller va appeler
     * @param name TextField name Contient le nom de la tache que l'Utilisateur a donné
     * @param desc TextField desc Contient la description de la tache que l'Utilisateur a éventuellement donner
     */
    public ControllerCréerTache(ModeleTache m, TextField name, TextField desc) {
        this.m = m;
        this.name = name;
        this.desc = desc;
    }

    /**
     * @param mouseEvent évènement de la souris
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        //on récupère le bouton sur lequel on a cliqué
        Button b = (Button) mouseEvent.getSource();
        //si le bouton est le bouton créer tache
        if (b.getText().equals("Créer Tâche")) {
            //on créer la tache
            try {
                this.m.creerTache(name.getText(), desc.getText());
            } catch (TacheNomVideException e) {
                e.printStackTrace();
            }


        }
        //on switch sur la scene principale
        this.m.switchFormulaire();
    }
}
