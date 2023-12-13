package TaskHub.Modele;

import TaskHub.Exception.TacheNomVideException;
import TaskHub.PrincipaleFx;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Tableau;
import TaskHub.Vue.Observateur;
import javafx.stage.Stage;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe ModeleTache
 * **/
public class ModeleTache implements Sujet{
    /**Attribut représentant la colonne sélectionner*/
    private int colonneSelectionner;
    /**Attribut représentant le tableau*/
    private Tableau tableau;

    /**listes des observateurs*/
    private List<Observateur> observateurs;


    /**Stage de l'application*/


    /**Attribut représentant la classe PrincipaleFx*/
    private PrincipaleFx principaleFx;


    public Tableau getTableau() {
        return this.tableau;
    }


    /**
     * Constructeur temporaire de la classe ModeleTache
     * */
    public ModeleTache(PrincipaleFx pf){
        this.colonneSelectionner=0;
        this.observateurs=new ArrayList<Observateur>();
        this.principaleFx=pf;
       // this.stage.setScene(PrincipaleFx.scenePrincipale);
    }

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
        if(index>=0 && index<this.tableau.getConteneurs().size()){
            this.colonneSelectionner=index;
        }
    }


    /**
     * methode pour reveneir sur la Scene Principale
     */
    public void switchScenePrincipale(){
        this.principaleFx.closeForm();
    }
    /**
     * méthode pour changer de scene et passer sur celle qui affiche le formulaire
     * */
    public void afficherFormulaire(){
        this.principaleFx.showForm();
    }


    public void setTableau(Tableau tableau) {
        this.tableau = tableau;

    }
}
