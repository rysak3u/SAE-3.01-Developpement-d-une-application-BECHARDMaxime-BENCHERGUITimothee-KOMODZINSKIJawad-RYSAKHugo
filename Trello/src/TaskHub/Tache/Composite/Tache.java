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

    /**
     * Méthode pour avoir le titre de la tâche
     * @return
     */
    public String getTitre(){
        return this.titre;
    }

    /**
     * Méthode pour modifier le titre de la tâche
     * @param titre
     */
    public void setTitre(String titre){
        this.titre = titre;
    }

    /**
     * Méthode pour modifier la description de la tâche
     * @return
     */
    public String getDescription(){
        return this.description;
    }
    /**
     * Méthode pour modifier la description de la tâche
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Méthode pour avoir le niveau de la tâche par rapport a ces dépendances
     * @return
     */
    public int getNiv() {
        return niv;
    }
    /**
     * Méthode pour modifier le niveau de la tâche par rapport a ces dépendances
     * @param niv
     */
    public void setNiv(int niv) {
        this.niv = niv;
    }

    /**
     * Methode pour comparer deux taches
     * @param other Tache à comparer
     * @return
     */
    @Override
    public int compareTo(Tache other) {
        return Integer.compare(this.niv, other.niv);
    }

    /**
     * Méthode pour verifier l'égalité entre deux tâches
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tache other = (Tache) obj;
        //return niv == other.niv && Objects.equals(titre, other.titre) && Objects.equals(description, other.description);
        return this.description.equals(other.description) && this.titre.equals(other.titre);
    }
}
