package TaskHub.Strategie;

import TaskHub.Controller.ControllerGantt;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.Tache;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.util.ArrayList;

public class StrategieDiagrammeGantt extends StrategieDiagramme {
    public StrategieDiagrammeGantt(ModeleTache modele) {
        super(modele);
    }

    @Override
    public void affichage() {
        // Création de la VBox principale
        VBox vboxDiag = new VBox(50);
        Label titreTab = new Label("Diagramme de Gantt");
        vboxDiag.setAlignment(Pos.CENTER);
        titreTab.getStyleClass().add("titreTableau");
        vboxDiag.getChildren().add(titreTab);
        GridPane gridDiag = new GridPane();
        gridDiag.setGridLinesVisible(true);
        gridDiag.setHgap(10);

        // Recuperation de la taille de l'écran
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        gridDiag.setPrefWidth(screenWidth);
        //grid.setPrefSize(screenWidth, screenHeight);

        // Création de la première colonne
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        gridDiag.getColumnConstraints().add(column1);

        // Création des autres colonnes
        for (int i = 1; i < 13; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(80/13);
            gridDiag.getColumnConstraints().add(column);
        }

        // Creation des noms des colonnes (mois) et ajout dans la grille
        Label task = new Label("Task");
        gridDiag.add(task, 0, 0);
        Label jan = new Label("Jan");
        gridDiag.add(jan, 1, 0);
        Label feb = new Label("Feb");
        gridDiag.add(feb, 2, 0);
        Label mar = new Label("Mar");
        gridDiag.add(mar, 3, 0);
        Label apr = new Label("Apr");
        gridDiag.add(apr, 4, 0);
        Label may = new Label("May");
        gridDiag.add(may, 5, 0);
        Label jun = new Label("Jun");
        gridDiag.add(jun, 6, 0);
        Label jul = new Label("Jul");
        gridDiag.add(jul, 7, 0);
        Label aug = new Label("Aug");
        gridDiag.add(aug, 8, 0);
        Label sep = new Label("Sep");
        gridDiag.add(sep, 9, 0);
        Label oct = new Label("Oct");
        gridDiag.add(oct, 10, 0);
        Label nov = new Label("Nov");
        gridDiag.add(nov, 11, 0);
        Label dec = new Label("Dec");
        gridDiag.add(dec, 12, 0);

        // Création des lignes et placement des taches
        int i=1;
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for(Tache t: modele.getTaches()) {
            Label l = new Label(t.getTitre());
            gridDiag.add(l, 0, i);
            Rectangle r = new Rectangle();
            r.setStroke(Color.BLUE);
            r.setFill(Color.BLUE);
            r.setWidth(screenWidth/20);
            r.setHeight(8);
            rectangles.add(r);
            gridDiag.add(r, t.getNiv()+1, i);
            i++;
        }

        // Creation des boutons
        Button retour = new Button("Retour");
        retour.getStyleClass().add("buttonColonne");
        retour.setOnAction(new ControllerGantt(modele));

        // Ajout des elements de la VBox principale
        vboxDiag.getChildren().add(gridDiag);
        vboxDiag.getChildren().add(retour);
        this.getChildren().add(vboxDiag);
    }


}
