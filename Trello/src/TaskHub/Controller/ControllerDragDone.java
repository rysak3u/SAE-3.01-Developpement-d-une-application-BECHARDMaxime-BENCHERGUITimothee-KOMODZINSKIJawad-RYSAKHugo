package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class ControllerDragDone implements EventHandler<DragEvent>{
    // Attributs
    private Conteneur colonne;
    private TacheMere tache;
    private ModeleTache modele;

    /**
     * Constructeur de la classe ControllerDragDone
     * @param colonne
     * @param tache
     * @param modele
     */
    public ControllerDragDone(Conteneur colonne, TacheMere tache, ModeleTache modele) {
        this.colonne = colonne;
        this.tache = tache;
        this.modele = modele;
    }

    /**
     * Méthode qui permet de gérer le drag and drop quand le curseur lache l'objet
     * @param event
     */
    public void handle(DragEvent event) {
        if (event.getTransferMode() == TransferMode.MOVE) {
            colonne.supprimerTache(tache);
        }
        modele.notifierObservateur();
        event.consume();
    }
}
