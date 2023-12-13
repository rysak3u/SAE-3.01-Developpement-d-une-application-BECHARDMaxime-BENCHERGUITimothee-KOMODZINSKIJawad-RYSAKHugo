package TaskHub.Modele;

import TaskHub.Tache.Colonne;
import TaskHub.Vue.Observateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe ModeleTache
 * **/
public class ModeleTache implements Sujet{
    /**Attribut contenant la listes des colonnes qui eux même contiennent la listes des tâches*/
    private ArrayList<Colonne> listContainer;
    /**Attribut représant*/
    private int colonneSelectionner


    /**
     * @param o
     */
    @Override
    public void enregisterObservateur(Observateur o) {

    }

    /**
     * @param o
     */
    @Override
    public void supprimerObservateur(Observateur o) {

    }

    /**
     *
     */
    @Override
    public void notifierObservateur() {

    }
}
