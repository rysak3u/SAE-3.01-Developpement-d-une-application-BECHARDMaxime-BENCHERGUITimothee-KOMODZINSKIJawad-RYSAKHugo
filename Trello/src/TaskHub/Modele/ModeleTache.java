package TaskHub.Modele;

import TaskHub.Exception.TacheNomVideException;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Tableau;
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
    /**Attribut représentant le tableau*/
    private Tableau tableau;

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
    public void créerTache(String name, String desc) throws TacheNomVideException {
        if(name==null){
            throw new TacheNomVideException();
        }
        Conteneur c=listContainer.get(colonneSelectionner);
        c.ajouterTache(new TacheMere(name,desc));
    }
}
