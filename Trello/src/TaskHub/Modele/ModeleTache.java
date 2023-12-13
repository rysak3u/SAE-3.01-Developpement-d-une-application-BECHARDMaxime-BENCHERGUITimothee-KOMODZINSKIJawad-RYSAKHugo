package TaskHub.Modele;

import TaskHub.Tache.Conteneur;
<<<<<<< HEAD
=======
import TaskHub.Tache.Conteneur;
>>>>>>> c38733924a1b54b8bca27d0dbe3b471335374aa9
import TaskHub.Vue.Observateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe ModeleTache
 * **/
public class ModeleTache implements Sujet{
    /**Attribut contenant la listes des colonnes qui eux même contiennent la listes des tâches*/
    private ArrayList<Conteneur> listContainer;
    /**Attribut représentant la colonne sélectionner*/
    private int colonneSelectionner;



    /**listes des observateurs*/
    private List<Observateur> observateurs;

    /**
     * méthode pour ajouter un observateur au modèle
     * @param o observateur à ajouter
     *
     */
    @Override
    public void enregisterObservateur(Observateur o) {
        this.observateurs.add(o);
    }

    /**
     * méthode pour supprimer un Observateur
     * @param o observateur à supprimer
     */
    @Override
    public void supprimerObservateur(Observateur o) {
        this.observateurs.remove(o);
    }

    /**
     * méthode pour notifier tout les observateurs
     */
    @Override
    public void notifierObservateur() {
        for(Observateur o:this.observateurs){
            o.actualiser(this);
        }
    }
    /**
     * methode pour changer la Colonne selectionner
     * @param index index de la colonne à selectionner
     * */
    public void changerColonneSelectionner(int index){
        if(index>=0 && index<this.listContainer.size()){
            this.colonneSelectionner=index;
        }
    }
}
