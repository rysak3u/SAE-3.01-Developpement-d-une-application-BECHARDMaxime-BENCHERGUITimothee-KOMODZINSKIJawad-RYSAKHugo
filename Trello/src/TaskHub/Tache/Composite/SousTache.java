package TaskHub.Tache.Composite;

public class SousTache extends Tache {

    
    public SousTache(String description) {
        super(description);
    }

    @Override
    /**
     * Méthods pour ajouter une tâche.
     * Return false car c'est une sous tâche qui ne peut pas posséder d'autre tâche.
     * @param st la sous tâche
     * @return false
     */
    public boolean ajouterSousTache(Tache st) {
        return false;
    }
}
