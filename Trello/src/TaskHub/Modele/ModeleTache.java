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


   private boolean formulaire;


   /**Attribut représentant la tache selectionner*/
   private Tache tacheSelectionner;


   /**Methode pour changer la tache selctionner
    * @param tacheSelectionner tache à selectionner
    * */
    public void setTacheSelectionner(Tache tacheSelectionner) {
        this.tacheSelectionner = tacheSelectionner;
    }

    public Tache getTacheSelectionner() {
        return this.tacheSelectionner;
    }
    public Tableau getTableau() {
        return this.tableau;
    }


    /**
     * Constructeur temporaire de la classe ModeleTache
     * */
    public ModeleTache(){
        this.colonneSelectionner=0;
        this.observateurs=new ArrayList<Observateur>();
        this.formulaire=false;
    }

    /**
     * méthode pour ajouter un observateur au modèle
     * @param o observateur à ajouter
     *
     */
    @Override
    public void enregistrerObservateur(Observateur o) {
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

    public void creerTache(String titre, String description) throws TacheNomVideException {
        TacheMere tache = new TacheMere(titre, description);
        this.tableau.getConteneurs().get(this.colonneSelectionner).ajouterTache(tache);
        this.notifierObservateur();
    }




    public void setTableau(Tableau tableau) {
        this.tableau = tableau;

    }

    public int getColonneSelectionner() {
        return this.colonneSelectionner;
    }

    public void switchFormulaire(){
        this.formulaire=!this.formulaire;
        this.notifierObservateur();
    }


    /**
     * méthode pour modifier la tâche selectionner
     * @param titre titre de la tâche
     * @param description description de la tâche
     * */
    public void modifierTache(String titre, String description) {
        this.tacheSelectionner.setTitre(titre);
        this.tacheSelectionner.setDescription(description);
        this.notifierObservateur();
    }

    public boolean getFormulaire(){
        return this.formulaire;
    }

}
