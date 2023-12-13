package TaskHub.Tache;

import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Tableau {
    ArrayList<Conteneur> colonnes;
    private String titre;

    public Tableau(String titre){
        this.titre = titre;
        this.colonnes = new ArrayList<Conteneur>();
    }
    public Tableau(String titre, ArrayList<Conteneur> colonnes){
        this.titre = titre;
        this.colonnes = colonnes;
    }

    public void ajouterColonne(Conteneur colonne){
        this.colonnes.add(colonne);
    }
    public void retirerColonne(Conteneur colonne){
        this.colonnes.remove(colonne);
    }

    public HBox affichage_tableau(){
        HBox hbox = new HBox();
        for(Conteneur colonne : this.colonnes){
            hbox.getChildren().add(colonne.affichage_tableau());
        }
        return hbox;
    }

    public HBox affichage_liste(){
        HBox hbox = new HBox();
        for(Conteneur colonne : this.colonnes){
            for(HBox tache : colonne.affichage_liste()){
                hbox.getChildren().add(tache);
            }
        }
        return hbox;
    }
}
