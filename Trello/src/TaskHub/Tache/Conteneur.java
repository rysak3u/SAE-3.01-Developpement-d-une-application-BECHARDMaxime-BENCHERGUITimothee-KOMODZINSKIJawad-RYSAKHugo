package TaskHub.Tache;

<<<<<<< HEAD
import Composite.Tache;
=======
import TaskHub.Tache.Composite.Tache;
>>>>>>> 53e2cb44e3acdef6fe6fe3b147678517e5d6a966
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Conteneur {
    private String titre;
    private ArrayList<Tache> taches;

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public ArrayList<Tache> getTaches() {
        return this.taches;
    }

    public void ajouterTache(Tache tache) {
        this.taches.add(tache);
    }

    public void supprimerTache(Tache tache) {
        this.taches.remove(tache);
    }

    public void supprimerTache(int index) {
        this.taches.remove(index);
    }

    public Conteneur(String titre) {
        this.titre = titre;
        this.taches = new ArrayList<Tache>();
    }

    public Conteneur(String titre, ArrayList<Tache> taches) {
        this.titre = titre;
        this.taches = taches;
    }

    public HBox affichage_tableau() {
        HBox hbox = new HBox();
        for (Tache tache : this.taches) {
            hbox.getChildren().add(tache.affichage());
        }
        return hbox;
    }

    public ArrayList<HBox> affichage_liste() {
        ArrayList<HBox> hbox = new ArrayList<HBox>();
        for (Tache tache : this.taches) {
            hbox.add(tache.affichage());
        }
        return hbox;
    }

}
