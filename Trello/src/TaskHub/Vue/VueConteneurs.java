package TaskHub.Vue;

import TaskHub.Modele.ModeleTache;
import TaskHub.Modele.Sujet;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VueConteneurs extends VBox implements Observateur {
    private ModeleTache modele;
    public VueConteneurs(ModeleTache modele) {
        super();
        this.modele = modele;
    }
    @Override
    public void actualiser(Sujet o) {
        this.getChildren().setAll(this.modele.getTableau().affichage_tableau());
    }
}