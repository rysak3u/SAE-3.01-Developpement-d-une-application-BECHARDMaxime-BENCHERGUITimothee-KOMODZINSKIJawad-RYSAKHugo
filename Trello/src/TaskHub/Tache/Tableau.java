package TaskHub.Tache;

import javafx.scene.layout.HBox;

import java.util.ArrayList;

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
    public void ajouterColonne(Conteneur colonne){
        this.colonnes.add(colonne);
    }
    public void retirerColonne(Conteneur colonne){
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
    public HBox affichage_tableau(){
        // HBox contenant les tâches
        HBox hbox = new HBox();
        for(Conteneur colonne : this.colonnes){
            // on ajoute les tâches de la colonne à la HBox
            hbox.getChildren().add(colonne.affichage_tableau());
        }
        // on retourne la HBox
        return hbox;
    }

    /**
     * méthode pour afficher les tâches en liste
     * @return HBox contenant les tâches
     */
    public HBox affichage_liste(){
        // HBox contenant les tâches
        HBox hbox = new HBox();
        // on parcourt les colonnes
        for(Conteneur colonne : this.colonnes){
            // on ajoute les tâches de la colonne à la HBox
            for(HBox tache : colonne.affichage_liste()){
                hbox.getChildren().add(tache);
            }
        }
        // on retourne la HBox
        return hbox;
    }
}
