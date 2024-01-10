package TaskHub.Tache;

import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Tableau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Archive {
    private Map<Tableau, Map<Tache, Integer>> archiveTache;

    public Archive() {
        this.archiveTache = new HashMap<>();
    }

    public void archiverTache(Tableau tableau, Tache tache, int idConteneur) {
        archiveTache.computeIfAbsent(tableau, k -> new HashMap<>()).put(tache, idConteneur);
    }

    public int desarchiverTache(Tableau tableau, Tache tache) {
        Map<Tache, Integer> tachesArchives = archiveTache.get(tableau);
        if (tachesArchives != null) {
            int idConteneur = tachesArchives.get(tache);
            tachesArchives.remove(tache);
            return idConteneur;
        }
        return -1;
    }

    public Map<Tableau, Map<Tache, Integer>> getArchiveTache() {
        return archiveTache;
    }

    public boolean isTache(Tableau tableau, Tache tache) {
        return this.archiveTache.get(tableau).containsKey(tache);
    }
}
