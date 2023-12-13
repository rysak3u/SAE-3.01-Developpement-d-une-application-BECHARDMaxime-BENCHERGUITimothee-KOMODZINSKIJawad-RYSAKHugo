package TaskHub.Tache.Composite;

import TaskHub.Exception.TacheNomVideException;

public class SousTache extends Tache {
    
    /**
     * Constructeur de SousTache
     * @param titre titre de la tâche
     * @param description description de la tâche
     */
    public SousTache(String titre, String description) throws TacheNomVideException {
        super(titre, description);
    }


    /**
     * Méthods pour ajouter une tâche.
     * Return false car c'est une sous tâche qui ne peut pas posséder d'autre tâche.
     * @param st la sous tâche
     * @return false
     */
    @Override
    public boolean ajouterSousTache(Tache st) {
        return false;
    }
}
