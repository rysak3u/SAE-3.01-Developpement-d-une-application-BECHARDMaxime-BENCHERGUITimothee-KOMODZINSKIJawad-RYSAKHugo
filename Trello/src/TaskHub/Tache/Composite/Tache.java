package TaskHub.Tache.Composite;

import TaskHub.Exception.TacheNomVideException;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Objects;

public abstract class Tache implements Comparable<Tache>{
    private String titre;
    protected String description;

    protected int niv;

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
        this.niv = 0;
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
    public int getNiv() {
        return niv;
    }
    public void setNiv(int niv) {
        this.niv = niv;
    }

    @Override
    public int compareTo(Tache other) {
        return Integer.compare(this.niv, other.niv);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tache other = (Tache) obj;
        return niv == other.niv && Objects.equals(titre, other.titre) && Objects.equals(description, other.description);
    }
}
