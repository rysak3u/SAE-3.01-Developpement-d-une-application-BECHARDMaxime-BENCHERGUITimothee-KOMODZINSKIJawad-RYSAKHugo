package TaskHub.Controller;

import TaskHub.Exception.TacheNomVideException;
import TaskHub.Modele.ModeleTache;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/** Controller qui va permettre de Créer une tâche
 * ATTENTION A NE OAS CONFONDRE AVEC LE CONTROLLER QUI VA PERMETTRE D'AFFICHER LE FORMULAIRE*/
public class ControllerManip implements EventHandler<MouseEvent> {
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
    public ControllerManip(ModeleTache m, TextField name, TextField desc) {
        this.m = m;
        this.name = name;
        this.desc = desc;
    }

    /**
     * @param m    Modele que le controller va appeler
     * @param name TextField name Contient le nom de la tache que l'Utilisateur a donné
     */
    public ControllerManip(ModeleTache m, TextField name) {
        this.m = m;
        this.name = name;
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
                    this.name.setText("New Tâche");
                }
                // Si une tache est selectionner on modifie la tache
                else if(this.m.getTacheSelectionner() !=null){
                    this.m.modifierTache(this.name.getText(), this.desc.getText());
                    this.name.setText("New Tâche");
                }
                // Sinon on créer une nouvelle tache
                else {
                    this.m.creerTache(name.getText(), desc.getText());
                    this.name.setText("New Tâche");
                }
            } catch (TacheNomVideException e) {
                e.printStackTrace();
            }
        } else if (b.getText().equals("Créer")) {
            // Si le formulaire est celui de la création d'une colonne
            if(this.m.getForm()==2){
                // On créer une nouvelle colonne
                this.m.creerColonne(this.name.getText());
                this.name.setText("New Colonne");
            }
        } else if (b.getText().equals("Créer Tableau")) {
            System.out.println(this.m.getForm()+"xxxx");
            // Si le formulaire est celui de la création d'une colonne
            if(this.m.getForm()==3){
                // On créer une nouvelle colonne
                this.m.ajouterTableau(this.name.getText());
                this.name.setText("New Tableau");
            }
        }
        //on vide les champs
        if(this.desc!=null){
            this.desc.clear();
        }
        //this.name.setText("New Tâche");
        this.m.switchFormulaire(0);
    }
}
