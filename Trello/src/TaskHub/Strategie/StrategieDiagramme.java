package TaskHub.Strategie;

import TaskHub.Modele.ModeleTache;
import javafx.scene.layout.Pane;

public abstract class StrategieDiagramme extends Pane {
    protected ModeleTache modele;
    public abstract void affichage();
}
