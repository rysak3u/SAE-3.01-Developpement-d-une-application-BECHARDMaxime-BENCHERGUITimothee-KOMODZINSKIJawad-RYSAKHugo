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
        VBox vbox = new VBox(50);
        Label titreTab = new Label("Diagramme de Gantt");
        vbox.setAlignment(Pos.CENTER);
        titreTab.getStyleClass().add("titreTableau");
        vbox.getChildren().add(titreTab);
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.setHgap(10);

        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        grid.setPrefWidth(screenWidth);
        //grid.setPrefSize(screenWidth, screenHeight);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        grid.getColumnConstraints().add(column1);

        for (int i = 1; i < 13; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(80/13);
            grid.getColumnConstraints().add(column);
        }
        Label task = new Label("Task");
        grid.add(task, 0, 0);
        Label jan = new Label("Jan");
        grid.add(jan, 1, 0);
        Label feb = new Label("Feb");
        grid.add(feb, 2, 0);
        Label mar = new Label("Mar");
        grid.add(mar, 3, 0);
        Label apr = new Label("Apr");
        grid.add(apr, 4, 0);
        Label may = new Label("May");
        grid.add(may, 5, 0);
        Label jun = new Label("Jun");
        grid.add(jun, 6, 0);
        Label jul = new Label("Jul");
        grid.add(jul, 7, 0);
        Label aug = new Label("Aug");
        grid.add(aug, 8, 0);
        Label sep = new Label("Sep");
        grid.add(sep, 9, 0);
        Label oct = new Label("Oct");
        grid.add(oct, 10, 0);
        Label nov = new Label("Nov");
        grid.add(nov, 11, 0);
        Label dec = new Label("Dec");
        grid.add(dec, 12, 0);
        int i=1;
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for(Tache t: modele.getTaches()) {
            Label l = new Label(t.getTitre());
            grid.add(l, 0, i);
            Rectangle r = new Rectangle();
            r.setStroke(Color.BLUE);
            r.setFill(Color.BLUE);
            r.setWidth(screenWidth/20);
            r.setHeight(8);
            rectangles.add(r);
            grid.add(r, t.getNiv()+1, i);
            i++;
        }


        Button retour = new Button("Retour");
        retour.getStyleClass().add("buttonColonne");
        retour.setOnAction(new ControllerGantt(modele));
        vbox.getChildren().add(grid);
        vbox.getChildren().add(retour);
        this.getChildren().add(vbox);
    }


}
