package Composite;

import java.util.ArrayList;
import java.util.List;

public class TacheMere extends Tache {
    protected List<Tache> sousTache=new ArrayList<Tache>();

    public TacheMere(String description) {
        super(description);
    }

    @Override
    public boolean ajouterSousTache(Tache st) {
        return this.sousTache.add(st);
    }
}
