package TaskHub.Vue;

import TaskHub.Modele.ModeleTache;
import TaskHub.Modele.Sujet;
import javafx.scene.control.ComboBox;

public class VueListeTableau extends ComboBox<String> implements Observateur{

    private ModeleTache modeleTache;

    public VueListeTableau(ModeleTache modeleTache){
        super();
        this.modeleTache=modeleTache;
        this.setPromptText("Choix du Tableau");
    }

    @Override
    public void actualiser(Sujet s) {
        // Si le changement est un ajout de tableau, on ajoute le tableau à la liste //
        if(modeleTache.getChangement().getAction().equals("newTableau")){
            this.getItems().add(modeleTache.getTableaux().get(modeleTache.getTableaux().size()-1).getTitre());
            modeleTache.getChangement().setAction("");
        }
    }
}
