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

    public List<Tache> getSousTache() {
        return sousTache;
    }
}
