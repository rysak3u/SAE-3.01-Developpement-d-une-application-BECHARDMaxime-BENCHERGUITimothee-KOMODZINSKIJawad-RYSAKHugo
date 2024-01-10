package TaskHub.Modele;

import TaskHub.Vue.Observateur;

public interface Sujet {
    /**
     * Méthode enregistrerObservateur
     * @param o observateur
     */
    public void enregistrerObservateur(Observateur o);
    /**
     * Méthode supprimerObservateur
     * @param o observateur
     */
    public void supprimerObservateur(Observateur o);
    /**
     * Méthode notifierObservateur
     */
    public void notifierObservateur();
}

