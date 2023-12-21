package TaskHub.Strategie;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import javafx.scene.layout.VBox;

public interface StrategieVisuel {
    public void affichage(ModeleTache modele);

    public VBox createVisuTache(TacheMere tache, ModeleTache modele);
}
