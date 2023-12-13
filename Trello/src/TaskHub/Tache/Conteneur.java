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

    // Affichage des taches en tableau et en liste

    /**
     * méthode pour afficher les tâches en tableau
     * @return HBox contenant les tâches
     */
    public VBox affichage_tableau() {
        // HBox contenant les tâches
        VBox vbox = new VBox();
        vbox.getChildren().add(new Text(this.titre));
        for (Tache tache : this.taches) {
            // ajout de la tâche dans la liste
            vbox.getChildren().add(tache.affichage());
        }
        vbox.setAlignment(Pos.TOP_CENTER);
        Button button = new Button("Ajouter une tâche");
        button.addEventHandler(MouseEvent.MOUSE_CLICKED,new ControllerAfficherFormulaire(this.modele,this.modele.getTableau().getConteneurs().indexOf(this)));
        button.setPadding(new Insets(10));
        button.setPrefSize(200, 30);
        button.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));
        vbox.getChildren().add(button);
        vbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));

        return vbox;
    }

    /**
     * méthode pour afficher les tâches en liste
     * @return ArrayList<HBox> contenant les tâches
     */
    public ArrayList<VBox> affichage_liste() {
        // ArrayList<HBox> contenant les tâches
        ArrayList<VBox> vbox = new ArrayList<VBox>();
        for (Tache tache : this.taches) {
            // ajout de la tâche dans la liste
            vbox.add(tache.affichage());
        }
        // retour de la liste
        return vbox;
    }

}
