package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Conteneur;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class ControllerDragDropped implements EventHandler<DragEvent> {
    // Attributs
    private Conteneur colonne;
    private ModeleTache modele;

    /**
     * Constructeur de la classe ControllerDragDropped
     * @param colonne
     * @param modele
     */
    public ControllerDragDropped(Conteneur colonne, ModeleTache modele) {
        this.colonne = colonne;
        this.modele = modele;
    }

    /**
     * Méthode qui permet de gérer le drag and drop quand le curseur lache l'objet
     * @param event
     */
    public void handle(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (event.getTransferMode() == TransferMode.MOVE && db.hasString()) {
            colonne.ajouterTache(modele.getTacheDrag());
            modele.notifierObservateur();
            success = true;
        }
        event.setDropCompleted(success);

        event.consume();
    }


}
