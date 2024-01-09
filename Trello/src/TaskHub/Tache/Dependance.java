package TaskHub.Tache;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;

import java.util.*;

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
        if(detecteCycle(predecesseur, dependance)){
            throw new IllegalArgumentException("Cycle détecter. Impossible d'ajouter la dépendance.");
        }
        this.dependance.computeIfAbsent(predecesseur, k -> new ArrayList<>()).add(dependance);
    }
    private boolean detecteCycle(Tache start, Tache dependance) {
        Set<Tache> visiter = new HashSet<>();
        return detecteCycleRecursive(start, dependance, visiter);
    }

    private boolean detecteCycleRecursive(Tache actuel, Tache dependance, Set<Tache> visiter) {

        if (actuel.equals(dependance)) {
            return true;
        }

        visiter.add(actuel);
        List<Tache> dependances = this.getSuccessors(actuel);
        if (dependances == null) return false;

        for (Tache tache : dependances) {
            //System.out.println(tache.getTitre());
            if (visiter.contains(tache) || detecteCycleRecursive(tache, dependance, visiter)) {
                return true;
            }
        }

        visiter.remove(actuel);
        return false;
    }
    private boolean existeDependanceInverse(Tache tachePredecesseur, Tache tacheDependante) {
        return this.dependance.getOrDefault(tacheDependante, new ArrayList<>()).contains(tachePredecesseur);
    }
    public void calculerNiveau(Tache tache) {
        int initial=tache.getNiv();
        int max=-1;
        if (this.dependance.get(tache) == null) return;
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
        for(int i=0;i<13;i++){
            for(Tache t : this.dependance.keySet()){
                if(t.getNiv()==i) {
                    calculerNiveau(t);
                }
            }

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