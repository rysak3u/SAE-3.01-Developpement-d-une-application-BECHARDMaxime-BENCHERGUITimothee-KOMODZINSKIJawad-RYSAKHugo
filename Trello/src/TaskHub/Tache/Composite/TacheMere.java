package TaskHub.Tache.Composite;

import TaskHub.Exception.TacheNomVideException;

import java.util.ArrayList;
import java.util.List;

public class TacheMere extends Tache {
    protected List<Tache> sousTache=new ArrayList<Tache>();

    /**
     * Constructeur de TacheMere
     * @param titre titre de la tâche
     * @param description description de la tâche
     */
    public TacheMere(String titre, String description) throws TacheNomVideException {

        super(titre, description);
    }

    /**
     * Méthode pour ajouter une sous-tâche.
     * @param st la sous tâche
     * @return true si la tâche a été ajoutée, false sinon
     */
    @Override
    public boolean ajouterSousTache(Tache st) {
        return this.sousTache.add(st);
    }

    /**
     * Méthode pour avoir la liste des sous-tâches
     * @return la liste des sous-tâches
     */

    public List<Tache> getSousTache() {
        return sousTache;
    }

    /**
     * Méthode pour renvoyer la chaine des sous-tâches
     * @return la chaines des sous-tâches
     */
    public String toString() {
    	String s = "";
        for (Tache t : this.sousTache) {
            s += t.getTitre()+ "\n";
        }
        return s;

    }

}
