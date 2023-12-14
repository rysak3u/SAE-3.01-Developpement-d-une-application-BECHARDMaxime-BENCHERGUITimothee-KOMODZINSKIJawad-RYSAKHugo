package TaskHub.Tache.Composite;

import TaskHub.Exception.TacheNomVideException;
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
    public Tache(String titre,String description) throws TacheNomVideException {
        //verifier si le nom est vide
        if(titre==null){
            throw new TacheNomVideException();
        }
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



}
