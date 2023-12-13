package TaskHub.Tache.Composite;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public abstract class Tache {
    private String titre;
    protected String description;

    /**
     * Constructeur de Tache
     * @param titre titre de la tâche
     * @param description description de la tâche
     */
    public Tache(String titre,String description) {
        this.titre= titre;
        this.description = description;
    }

    /**
     * Méthods pour ajouter une sous-tâche.
     * @param st la sous tâche
     * @return true si la tâche a été ajoutée, false sinon
     */
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

    /**
     * méthode pour afficher une tâche
     * @return HBox contenant la tâche
     */
    public HBox affichage(){
        // HBox contenant la tâche
        HBox hbox = new HBox();
        // on ajoute les labels à la HBox
        hbox.getChildren().addAll(new Label(this.titre),new Label(this.description));
        return hbox;
    }

}
