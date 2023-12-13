package TaskHub.Tache;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collection;

public class Tableau {
    // titre et conteneur du tableau
    ArrayList<Conteneur> colonnes;
    private String titre;

    /**
     * Constructeur de Tableau
     * @param titre titre du tableau
     */
    public Tableau(String titre){
        this.titre = titre;
        this.colonnes = new ArrayList<Conteneur>();
    }

    /**
     * Constructeur de Tableau
     * @param titre titre du tableau
     * @param colonnes colonnes du tableau
     */
    public Tableau(String titre, ArrayList<Conteneur> colonnes){
        this.titre = titre;
        this.colonnes = colonnes;
    }

    // Getters et setters
    public void ajouterConteneur(Conteneur colonne){
        this.colonnes.add(colonne);
    }
    public void retirerConteneur(Conteneur colonne){
        this.colonnes.remove(colonne);
    }

    public void setTitre(String titre){
        this.titre = titre;
    }
    public String getTitre(){
        return this.titre;
    }

    /**
     * méthode pour afficher les tâches en tableau
     * @return HBox contenant les tâches
     */
    public VBox affichage_tableau(){
        // HBox contenant les tâches
        HBox hbox = new HBox(50);
        for(Conteneur colonne : this.colonnes){
            // on ajoute les tâches de la colonne à la HBox
            hbox.getChildren().add(colonne.affichage_tableau());
        }
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Text(this.titre),hbox);
        // on retourne la VBox
        return vbox;
    }

    /**
     * méthode pour afficher les tâches en liste
     * @return HBox contenant les tâches
     */
    public VBox affichage_liste(){

        // HBox contenant les tâches
        HBox hbox = new HBox();

        // on parcourt les colonnes
        for(Conteneur colonne : this.colonnes){
            // on ajoute les tâches de la colonne à la HBox
            for(VBox tache : colonne.affichage_liste()){
                hbox.getChildren().add(tache);
            }
        }
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label(this.titre),hbox);
        // on retourne la VBox
        return vbox;
    }

    public Conteneur getConteneur(int i) {
        return this.colonnes.get(i);
    }

    public ArrayList<Conteneur> getConteneurs() {
        return this.colonnes;
    }
}
