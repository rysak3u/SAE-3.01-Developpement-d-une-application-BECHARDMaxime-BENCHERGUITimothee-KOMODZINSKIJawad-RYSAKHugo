package TaskHub.Tache;

import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dependance {
    private Map<Tache, List<Tache>> dependance;

    public Dependance() {
        this.dependance = new HashMap<Tache, List<Tache>>();
    }

    public void ajouterDependance(Tache tache, List<Tache> dependance) {
        this.dependance.put(tache, dependance);
    }

    public void ajouterDependance(Tache predecesseur, Tache dependance) {
        if (existeDependanceInverse(predecesseur, dependance)) {
            // Si oui, ne fais rien (ou lève une exception, selon tes besoins).
            System.out.println("Dépendance inverse détectée. Double dépendance non autorisée.");
            return;
        }
        this.dependance.computeIfAbsent(predecesseur, k -> new ArrayList<>()).add(dependance);
    }

    private boolean existeDependanceInverse(Tache tachePredecesseur, Tache tacheDependante) {
        return this.dependance.getOrDefault(tacheDependante, new ArrayList<>()).contains(tachePredecesseur);
    }

    public void supprimerDependance(Tache tache) {
        this.dependance.remove(tache);
    }

    public void supprimerDependance(Tache tache, Tache dependance) {
        this.dependance.get(tache).remove(dependance);
    }

    public List<Tache> getDependance(Tache predecesseur) {
        return this.dependance.get(predecesseur);
    }

    public Map<Tache, List<Tache>> getDependance() {
        return dependance;
    }
}
