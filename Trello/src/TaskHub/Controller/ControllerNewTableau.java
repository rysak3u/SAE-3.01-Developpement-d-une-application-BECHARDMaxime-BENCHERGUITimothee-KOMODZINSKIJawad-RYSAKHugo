package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ControllerNewTableau implements EventHandler<MouseEvent> {
    /** Modele que le controller va appeler*/
    private ModeleTache m;

    public ControllerNewTableau(ModeleTache m) {
        this.m = m;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // On récupère le bouton sur lequel on a cliqué
        Button b = (Button) mouseEvent.getSource();
        // Si le bouton est le bouton ajouter Tableau, on affiche le formulaire
        if (b.getText().equals("Ajouter Tableau")) {
            System.out.println("formulaire tableau");
        }
    }
}
