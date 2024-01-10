package TaskHub.Strategie;

import TaskHub.Controller.ControllerDetailsTache;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public abstract class StrategieVisuel extends Pane{
    public abstract  void affichage(ModeleTache modele);

    public VBox createVisuTache(TacheMere tache, ModeleTache modele){
        VBox vboxTache = new VBox();
        // Création des texts
        Text titreTache=new Text(tache.getTitre());
        Text descTache=new Text(tache.getDescription());
        titreTache.getStyleClass().add("tacheText");
        descTache.getStyleClass().add("tacheText");

        // Ajout des texts de la HBox
        vboxTache.getChildren().addAll(titreTache,descTache);
        vboxTache.addEventHandler(MouseEvent.MOUSE_CLICKED,new ControllerDetailsTache(modele,tache));

        // Style de la HBox
        vboxTache.setPadding(new Insets(20));
        vboxTache.setPrefSize(200, 100);
        //vboxt.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        vboxTache.getStyleClass().add("tache");

        return vboxTache;
    }
}
