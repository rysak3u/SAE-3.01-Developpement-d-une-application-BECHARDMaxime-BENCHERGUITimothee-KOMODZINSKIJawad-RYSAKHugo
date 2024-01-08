package TaskHub.Vue;

import TaskHub.Modele.ModeleTache;
import TaskHub.Modele.Sujet;
import TaskHub.Tache.Tableau;
import javafx.scene.control.ComboBox;

public class VueListeTableau extends ComboBox<String> implements Observateur{

    private ModeleTache modeleTache;

    public VueListeTableau(ModeleTache modeleTache){
        super();
        this.modeleTache=modeleTache;
    }

    @Override
    public void actualiser(Sujet s) {
        this.getItems().setAll();
        for(Tableau t : this.modeleTache.getTableaux()){
            this.getItems().add(t.getTitre());
        }
    }
}
