package TaskHub.Tache;

import TaskHub.Tache.Composite.Tache;
import javafx.scene.layout.HBox;
import java.util.ArrayList;

// Conteneur est une classe qui contient des taches
public class Conteneur {
    // titre et tache du conteneur
    private String titre;
    private ArrayList<Tache> taches;

    // getters et setters
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

    // Constructeurs

    public Conteneur(String titre) {
        this.titre = titre;
        this.taches = new ArrayList<Tache>();
    }

    public Conteneur(String titre, ArrayList<Tache> taches) {
        this.titre = titre;
        this.taches = taches;
    }

    // Affichage des taches en tableau et en liste

    /**
     * méthode pour afficher les tâches en tableau
     */
    public HBox affichage_tableau() {
        HBox hbox = new HBox();
        for (Tache tache : this.taches) {
            hbox.getChildren().add(tache.affichage());
        }
        return hbox;
    }

    /**
     * méthode pour afficher les tâches en liste
     */
    public ArrayList<HBox> affichage_liste() {
        ArrayList<HBox> hbox = new ArrayList<HBox>();
        for (Tache tache : this.taches) {
            hbox.add(tache.affichage());
        }
        return hbox;
    }

}
