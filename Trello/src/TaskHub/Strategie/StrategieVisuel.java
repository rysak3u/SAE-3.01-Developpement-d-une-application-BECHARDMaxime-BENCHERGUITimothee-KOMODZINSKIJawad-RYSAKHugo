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
        VBox vboxt = new VBox();
        // Création des texts
        Text t=new Text(tache.getTitre());
        Text desc=new Text(tache.getDescription());
        t.getStyleClass().add("tacheText");
        desc.getStyleClass().add("tacheText");

        // Ajout des texts de la HBox
        vboxt.getChildren().addAll(t,desc);
        vboxt.addEventHandler(MouseEvent.MOUSE_CLICKED,new ControllerDetailsTache(modele,tache));

        // Style de la HBox
        vboxt.setPadding(new Insets(20));
        vboxt.setPrefSize(200, 100);
        //vboxt.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        vboxt.getStyleClass().add("tache");

        return vboxt;
    }
}
