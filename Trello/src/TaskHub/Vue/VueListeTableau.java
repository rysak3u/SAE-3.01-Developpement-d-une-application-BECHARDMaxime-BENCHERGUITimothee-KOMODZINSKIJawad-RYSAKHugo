package TaskHub.Vue;

import TaskHub.Modele.ModeleTache;
import TaskHub.Modele.Sujet;
import javafx.scene.control.ComboBox;

/**
 * Classe VueListeTableau
 */
public class VueListeTableau extends ComboBox<String> implements Observateur{
    // Attributs
    private ModeleTache modeleTache;

    /**
     * Constructeur de VueListeTableau
     * @param modeleTache modèle pour lequelle la vue va se baser
     */
    public VueListeTableau(ModeleTache modeleTache){
        super();
        this.modeleTache=modeleTache;
        this.setPromptText("Choix du Tableau");
    }

    /**
     * Méthode actualiser
     * @param s modèle pour lequelle la vue va se baser
     */
    @Override
    public void actualiser(Sujet s) {
        // Si le changement est un ajout de tableau, on ajoute le tableau à la liste //
        if(modeleTache.getChangement().getAction().equals("newTableau")){
            this.getItems().add(modeleTache.getTableaux().get(modeleTache.getTableaux().size()-1).getTitre());
            modeleTache.getChangement().setAction("");
        }
    }
}
