package TaskHub.Modele;

import TaskHub.Exception.TacheNomVideException;
import TaskHub.PrincipaleFx;
import TaskHub.Tache.Composite.SousTache;
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

    private boolean sousTache = false;

    /**listes des observateurs*/
    private List<Observateur> observateurs;

    // Derniere action effectuée //
    private Changement changement;

    private int affichage;


   private boolean formulaire;


   /**Attribut représentant la tache selectionner*/
   private TacheMere tacheSelectionner;


   /**Methode pour changer la tache selctionner
    * @param tacheSelectionner tache à selectionner
    * */
    public void setTacheSelectionner(TacheMere tacheSelectionner) {
        this.tacheSelectionner = tacheSelectionner;
        notifierObservateur();
    }

    /**
     * methode pour changer l'affichage
     * @param affichage
     */
    public void setAffichage(int affichage) {
        this.affichage = affichage;
    }

    public TacheMere getTacheSelectionner() {
        return this.tacheSelectionner;
    }
    public Tableau getTableau() {
        return this.tableau;
    }
    public int getAffichage() {
        return this.affichage;
    }

    /**
     * Constructeur temporaire de la classe ModeleTache
     * */
    public ModeleTache(){
        this.colonneSelectionner=0;
        this.observateurs=new ArrayList<Observateur>();
        this.formulaire=false;
        this.affichage=1;
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
        this.changement = new Changement(this.tableau.getConteneurs().get(this.colonneSelectionner).getTaches().size()-1,this.colonneSelectionner,"ajout");
        this.notifierObservateur();
    }

    public void creerSousTache(String titre, String description) throws TacheNomVideException {
        if (this.tacheSelectionner==null){
            return;
        }
        TacheMere tache = new TacheMere(titre, description);
        this.tacheSelectionner.ajouterSousTache(tache);
        this.notifierObservateur();
    }

    public Changement getChangement() {
        return this.changement;
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
        if(this.tacheSelectionner==null){
            return;
        }
        this.tableau.getConteneur(this.colonneSelectionner).getTaches().get(this.tableau.getConteneur(this.colonneSelectionner).getTaches().indexOf(this.tacheSelectionner)).setTitre(titre);
        this.tableau.getConteneur(this.colonneSelectionner).getTaches().get(this.tableau.getConteneur(this.colonneSelectionner).getTaches().indexOf(this.tacheSelectionner)).setDescription(description);
        this.notifierObservateur();
    }

    public boolean getFormulaire(){
        return this.formulaire;
    }

    public void setSousTache(boolean sousTache) {
        this.sousTache = sousTache;
    }

    public boolean getSousTache() {
        return this.sousTache;
    }

}
