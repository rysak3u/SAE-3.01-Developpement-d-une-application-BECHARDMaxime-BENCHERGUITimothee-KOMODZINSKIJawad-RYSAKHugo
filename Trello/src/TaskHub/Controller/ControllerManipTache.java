package TaskHub.Controller;

import TaskHub.Exception.TacheNomVideException;
import TaskHub.Modele.ModeleTache;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/** Controller qui va permettre de Créer une tâche
 * ATTENTION A NE OAS CONFONDRE AVEC LE CONTROLLER QUI VA PERMETTRE D'AFFICHER LE FORMULAIRE*/
public class ControllerManipTache implements EventHandler<MouseEvent> {
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
    public ControllerManipTache(ModeleTache m, TextField name, TextField desc) {
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
            System.out.println("hum hum");
            try {
                // Si l'attribut sousTache est à true alors on créer une sous tache
                if (this.m.getSousTache()) {
                    this.m.creerSousTache(name.getText(), desc.getText());
                    // On remet l'attribut sousTache à false
                    this.m.setSousTache(false);
                }
                // Si une tache est selectionner on modifie la tache
                else if(this.m.getTacheSelectionner() !=null){
                    this.m.modifierTache(this.name.getText(), this.desc.getText());
                }
                // Sinon on créer une nouvelle tache
                else {
                    this.m.creerTache(name.getText(), desc.getText());
                }
            } catch (TacheNomVideException e) {
                e.printStackTrace();
            }
        }
        //on vide les champs
        this.desc.clear();
        this.name.setText("New Tâche");
        this.m.switchFormulaire();
    }
}
