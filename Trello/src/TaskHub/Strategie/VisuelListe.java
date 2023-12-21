package TaskHub.Strategie;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class VisuelListe extends StrategieVisuel {
    @Override
    public void affichage(ModeleTache modele) {
        // Affichage en liste //
        HBox hbox = new HBox(50);
        hbox.getChildren().setAll();
        // HBox contenant les tâches
        VBox vbox = new VBox();
        for(Conteneur colonne : modele.getTableau().getColonnes()){
            // on ajoute les tâches de la colonne à la VBox
            for (TacheMere tache : colonne.getTaches()) {
                VBox vboxt = createVisuTache(tache,modele);
                // ajout de la tâche dans la liste
                vbox.getChildren().add(vboxt);
            }
        }
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        hbox.getChildren().add(vbox);
        this.getChildren().setAll(new Text(modele.getTableau().getTitre()),hbox);
    }
}
