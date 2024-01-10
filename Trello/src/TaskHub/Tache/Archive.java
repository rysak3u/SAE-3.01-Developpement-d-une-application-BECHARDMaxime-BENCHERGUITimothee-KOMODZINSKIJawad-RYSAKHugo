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

/**
    public void archiverConteneur(Tableau tableau, Conteneur conteneur) {
        archiveConteneur.computeIfAbsent(tableau, k -> new ArrayList<>()).add(conteneur);
        verifierDependance();
    }

    public void verifierDependance(){
        for(Tableau tableau: archiveTache.keySet()) {
            for (Map.Entry<Tache, Integer> entry : archiveTache.get(tableau).entrySet()) {
                if (archiveConteneur.get(tableau)!=null) {
                    for(Conteneur conteneur: archiveConteneur.get(tableau)) {
                        if (entry.getValue().equals(conteneur.getIdConteneur())) {
                            conteneur.ajouterTache((TacheMere) entry.getKey());
                            archiveTache.get(tableau).remove(entry.getKey());
                        }
                    }
                }
            }
        }
    }

 public Conteneur desarchiverConteneur(Tableau tableau, Conteneur conteneur) {
 archiveConteneur.get(tableau).remove(conteneur);
 return conteneur;
 }
 */

/**
    public Tache desarchiverTache(Tableau tableau, Tache tache) {
        Map<Tache, Integer> tachesArchives = archiveTache.get(tableau);
        if (tachesArchives != null) {
            tachesArchives.remove(tache);
        }
        return tache;
    }

    public void reintegrerTachesArchives(Tableau tableau, Conteneur conteneur) {
        // Vérifier si le conteneur est présent dans l'archive
        if (archiveConteneur.containsKey(tableau) && archiveConteneur.get(tableau).equals(conteneur)) {
            Map<Tache, Integer> tachesArchives = archiveTache.getOrDefault(tableau, new HashMap<>());

            // Parcourir les tâches archivées et les réintégrer au conteneur
            for (Map.Entry<Tache, Integer> entry : tachesArchives.entrySet()) {
                if (entry.getValue().equals(conteneur.getIdConteneur())) {
                    conteneur.ajouterTache((TacheMere) entry.getKey());
                }
            }
            // Supprimer les tâches réintégrées de l'archive
            tachesArchives.entrySet().removeIf(entry -> entry.getValue().equals(conteneur.getIdConteneur()));
        }
    }
 */

}
