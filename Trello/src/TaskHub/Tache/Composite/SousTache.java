package TaskHub.Tache.Composite;

public class SousTache extends Tache {

    public SousTache(String description) {
        super(description);
    }

    @Override
    public boolean ajouterSousTache(Tache st) {
        return false;
    }
}
