package TaskHub.Tache;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;

import java.util.*;

public class Dependance {
    // Attributs
    private Map<Tache, List<Tache>> dependance;

    /**
     * Constructeur de la classe Dependance
     */
    public Dependance() {
        // Initialisation de la map
        this.dependance = new HashMap<Tache, List<Tache>>();
    }

<<<<<<< HEAD
=======
    /**
     * Méthode pour ajouter une dépendance
     * @param tache
     * @param dependance
     */
>>>>>>> bcb2daedb3c3bd82c01effd7241a6a4e2b3c85fb
    public void ajouterDependance(Tache tache, List<Tache> dependance) {
        this.dependance.put(tache, dependance);
    }

<<<<<<< HEAD
=======
    /**
     * Méthode pour ajouter une dépendance
     * @param predecesseur
     * @param dependance
     */
>>>>>>> bcb2daedb3c3bd82c01effd7241a6a4e2b3c85fb
    public void ajouterDependance(Tache predecesseur, Tache dependance) {
        if (existeDependanceInverse(predecesseur, dependance) || predecesseur.equals(dependance) || this.dependance.getOrDefault(predecesseur, new ArrayList<>()).contains(dependance)) {
            throw new IllegalArgumentException("Dépendance inverse détectée. Double dépendance non autorisée.");
        }
        if(detecteCycle(predecesseur, dependance)){
            throw new IllegalArgumentException("Cycle détecter. Impossible d'ajouter la dépendance.");
        }
        this.dependance.computeIfAbsent(predecesseur, k -> new ArrayList<>()).add(dependance);
    }
    /**
     * Méthode pour détecter les cycles
     * @param start
     * @param dependance
     * @return
     */
    private boolean detecteCycle(Tache start, Tache dependance) {
        Set<Tache> visiter = new HashSet<>();
        return detecteCycleRecursive(start, dependance, visiter);
    }
    /**
     * Méthode pour détecter les cycles
     * @param actuel
     * @param dependance
     * @param visiter
     * @return
     */
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

    /**
     * Méthode pour vérifier si une dépendance inverse existe
     * @param tachePredecesseur
     * @param tacheDependante
     * @return
     */
    private boolean existeDependanceInverse(Tache tachePredecesseur, Tache tacheDependante) {
        return this.dependance.getOrDefault(tacheDependante, new ArrayList<>()).contains(tachePredecesseur);
    }

    /**
     * Méthode pour calculer le niveau de dépendance d'une tache
     * @param tache
     */
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

    /**
     * Méthode pour mettre à jour les niveaux de dépendance
     */
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

    /**
     * Méthode pour supprimer une dépendance
     * @param tache
     */
    public void supprimerDependance(Tache tache) {
        this.dependance.remove(tache);
    }

    /**
     * Méthode pour supprimer une dépendance
     * @param tache
     * @param dependance
     */
    public void supprimerDependance(Tache tache, Tache dependance) {
        this.dependance.get(tache).remove(dependance);
    }

    /**
     * Méthode pour récupérer les dépendances d'une tâche
     * @param predecesseur
     * @return dependance
     */
    public List<Tache> getDependance(Tache predecesseur) {
        return this.dependance.get(predecesseur);
    }

    /**
     * Méthode pour récupérer les dépendances
     * @return dependance
     */
    public Map<Tache, List<Tache>> getDependance() {
        return dependance;
    }

    /**
     * Méthode pour récupérer les successeurs d'une tâche
     * @param tache
     * @return successors
     */
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