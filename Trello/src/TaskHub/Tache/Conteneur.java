package TaskHub.Tache;
import TaskHub.Controller.ControllerAfficherFormulaire;
import TaskHub.Controller.ControllerCréerTache;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.Tache;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

// Conteneur est une classe qui contient des taches
public class Conteneur {
    // titre et tache du conteneur
    private String titre;
    private ArrayList<Tache> taches;
    private ModeleTache modele;

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
    public void ajouterTache(Tache tache){
        this.taches.add(tache);
    }
    public void supprimerTache(Tache tache) {
        this.taches.remove(tache);
    }
    public void supprimerTache(int index) {
        this.taches.remove(index);
    }


    /**
     * Constructeur de Conteneur
     * @param titre titre du conteneur
     */
    public Conteneur(String titre,ModeleTache modele) {
        this.titre = titre;
        this.taches = new ArrayList<Tache>();
        this.modele = modele;
    }

    /**
     * Constructeur de Conteneur
     * @param titre titre du conteneur
     * @param taches taches du conteneur
     */
    public Conteneur(String titre, ArrayList<Tache> taches, ModeleTache modele) {
        this.titre = titre;
        this.taches = taches;
        this.modele = modele;
    }


}
