package TaskHub.Strategie;

import TaskHub.Tache.Composite.Tache;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StrategieDiagrammeGantt extends StrategieDiagramme {
    @Override
    public void affichage() {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.setHgap(10);
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
        for(Tache t: modele.getTaches()){
            Label l = new Label(t.getTitre());
            grid.add(l,0,i);
            i++;
        }
    }
}
