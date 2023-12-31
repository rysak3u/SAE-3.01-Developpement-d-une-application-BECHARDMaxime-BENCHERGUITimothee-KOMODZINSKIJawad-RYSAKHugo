package TaskHub.Tache;

import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Tableau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
public class Archive {
    private Map<Tableau, Map<Tache, Integer>> archiveTache;
    private Map<Tableau, List<Conteneur>> archiveConteneur;

    public Archive() {
        this.archiveTache = new HashMap<>();
        this.archiveConteneur = new HashMap<>();
    }

    public void archiverTache(Tableau tableau, Tache tache, Conteneur conteneur) {
        archiveTache.computeIfAbsent(tableau, k -> new HashMap<>()).put(tache, conteneur.getIdConteneur());
    }

    public void archiverConteneur(Tableau tableau, Conteneur conteneur) {
        archiveConteneur.computeIfAbsent(tableau, k -> new ArrayList<>()).add(conteneur);;
    }

    public void desarchiverTache(Tableau tableau, Tache tache) {
        Map<Tache, Integer> tachesArchives = archiveTache.get(tableau);
        if (tachesArchives != null) {
            tachesArchives.remove(tache);
        }
    }

    public void desarchiverConteneur(Tableau tableau) {
        archiveConteneur.remove(tableau);
    }

    public void reintegrerTachesArchives(Tableau tableau, Conteneur conteneur) {
        // Vérifier si le conteneur est présent dans l'archive
        if (archiveConteneur.containsKey(tableau) && archiveConteneur.get(tableau).equals(conteneur)) {
            Map<Tache, Integer> tachesArchives = archiveTache.getOrDefault(tableau, new HashMap<>());

            // Parcourir les tâches archivées et les réintégrer au conteneur
            for (Map.Entry<Tache, Integer> entry : tachesArchives.entrySet()) {
                if (entry.getValue().equals(conteneur.getIdConteneur())) {
                    conteneur.ajouterTache(entry.getKey());
                }
            }
            // Supprimer les tâches réintégrées de l'archive
            tachesArchives.entrySet().removeIf(entry -> entry.getValue().equals(conteneur.getIdConteneur()));
        }
    }

}
*/