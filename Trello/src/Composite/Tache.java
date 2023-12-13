package Composite;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public abstract class Tache {
    private String titre;
    protected String description;

    public Tache(String titre,String description) {
        this.titre= titre;
        this.description = description;
    }
    public Tache(String titre) {
        this.titre= titre;
    }

    public abstract boolean ajouterSousTache(Tache st);

    public String getTitre(){
        return this.titre;
    }
    public void setTitre(String titre){
        this.titre = titre;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public HBox affichage(){
        HBox hbox = new HBox();
        hbox.getChildren().addAll(new Label(this.titre),new Label(this.description));
        return hbox;
    }

}
