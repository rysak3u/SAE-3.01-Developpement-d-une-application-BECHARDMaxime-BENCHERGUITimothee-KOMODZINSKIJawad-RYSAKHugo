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
        if (existeDependanceInverse(predecesseur, dependance) || predecesseur.equals(dependance) || this.dependance.getOrDefault(predecesseur, new ArrayList<>()).contains(dependance)) {
            System.out.println("Dépendance inverse détectée. Double dépendance non autorisée.");
            return;
        }
        this.dependance.computeIfAbsent(predecesseur, k -> new ArrayList<>()).add(dependance);
    }

    private boolean existeDependanceInverse(Tache tachePredecesseur, Tache tacheDependante) {
        return this.dependance.getOrDefault(tacheDependante, new ArrayList<>()).contains(tachePredecesseur);
    }

    public void calculerNiveau(Tache tache) {
        int initial=tache.getNiv();
        int max=-1;
        for(Tache t : this.dependance.get(tache)){
            if(t.getNiv()>=max) {
                max=t.getNiv();
            }
        }
        if(max>=initial){
            tache.setNiv(max+1);
        }
    }

    public void update() {
        for(Tache t : this.dependance.keySet()){
            calculerNiveau(t);
        }
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

    public List<Tache> getSuccessors(Tache tache) {
        List<Tache> successors = new ArrayList<>();
        for (Tache t : this.dependance.keySet()) {
            if (this.dependance.get(t).contains(tache)) {
                successors.add(t);
            }
        }
        return successors;
    }
}
