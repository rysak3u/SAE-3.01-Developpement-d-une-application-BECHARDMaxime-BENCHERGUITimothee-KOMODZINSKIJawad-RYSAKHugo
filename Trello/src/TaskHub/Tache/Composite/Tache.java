package TaskHub.Tache.Composite;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
     *
     * @return HBox contenant la tâche
     */
    public VBox affichage(){
        // HBox contenant la tâche
        VBox vbox = new VBox();

        // Ajout des texts de la HBox
        vbox.getChildren().addAll(new Text(this.titre),new Text(this.description));

        // Style de la HBox
        vbox.setPadding(new Insets(20));
        vbox.setPrefSize(200, 100);
        vbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));

        return vbox;
    }

}
