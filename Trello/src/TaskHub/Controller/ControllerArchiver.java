package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Tableau;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControllerArchiver implements EventHandler<ActionEvent> {

    private ModeleTache modeleTache;
    private TacheMere tache;
    private Conteneur conteneur;
    private Tableau tableau;


    public ControllerArchiver(ModeleTache modeleTache, TacheMere tache, Conteneur conteneur, Tableau tableau) {
        this.modeleTache = modeleTache;
        this.tache = tache;
        this.conteneur = conteneur;
        this.tableau = tableau;
    }

    @Override
    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().equals("Archiver")) {
            modeleTache.archiverTache(tableau, tache, conteneur.getIdConteneur());
            this.modeleTache.setTacheSelectionner(null);
        } else {
            modeleTache.desarchiverTache(tableau, tache);
            this.modeleTache.setTacheSelectionner(null);
        }
    }
}
