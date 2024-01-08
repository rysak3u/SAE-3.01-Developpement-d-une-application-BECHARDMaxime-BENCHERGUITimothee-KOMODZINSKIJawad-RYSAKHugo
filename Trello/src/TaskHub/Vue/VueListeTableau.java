package TaskHub.Vue;

import TaskHub.Controller.ControllerSwitchTableau;
import TaskHub.Modele.ModeleTache;
import TaskHub.Modele.Sujet;
import TaskHub.Tache.Tableau;
import javafx.event.EventType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class VueListeTableau extends ComboBox<String> implements Observateur{

    private ModeleTache modeleTache;

    public VueListeTableau(ModeleTache modeleTache){
        super();
        this.modeleTache=modeleTache;
    }

    @Override
    public void actualiser(Sujet s) {
        // Si le changement est un ajout de tableau, on ajoute le tableau à la liste //
        if(modeleTache.getChangement().getAction().equals("newTableau")){
            this.getItems().add(modeleTache.getTableaux().get(modeleTache.getTableaux().size()-1).getTitre());
        }
    }
}
