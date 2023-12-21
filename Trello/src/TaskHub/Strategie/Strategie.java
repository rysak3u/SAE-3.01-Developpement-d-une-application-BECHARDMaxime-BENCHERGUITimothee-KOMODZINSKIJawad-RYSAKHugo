package TaskHub.Strategie;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import javafx.scene.layout.VBox;

public interface Strategie {
    public void affichage(ModeleTache o);

    public VBox createVisuTache(TacheMere tache, ModeleTache modele);
}
