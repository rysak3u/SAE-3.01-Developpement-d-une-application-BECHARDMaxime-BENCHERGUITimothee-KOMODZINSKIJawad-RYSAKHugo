package TaskHub.Modele;

import TaskHub.Exception.TacheNomVideException;
//import TaskHub.Tache.Archive;
import TaskHub.Tache.Archive;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Dependance;
import TaskHub.Tache.Tableau;
import TaskHub.Vue.Observateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe ModeleTache
 * **/
public class ModeleTache implements Sujet{
    /**Attribut représentant la colonne sélectionner*/
    private int colonneSelectionner;
    /**Attribut représentant le tableau*/
    private ArrayList<Tableau> tableaux;

    /**Attribut représentant l'id du tableau courant*/
    private int idTableauCourant;

    /**Attribut représentant si on est en train de créer une sous tache*/
    private boolean sousTache = false;

    /**listes des observateurs*/
    private List<Observateur> observateurs;

    // Derniere action effectuée //
    private Changement changement;

    /**Attribut représentant le mode d'affichage**/
    private int affichage;
    private TacheMere tacheDrag;

    private Dependance dependance=new Dependance();

    private boolean gantt=false;

    private boolean archive=false;

    private Archive archivage = new Archive();

    private boolean formulaire;

   private int form;

   /**Attribut représentant la tache selectionner*/
   private TacheMere tacheSelectionner;


    public Archive getArchivage() {
        return archivage;
    }

    public void setTacheDrag(TacheMere tacheDrag) {
       this.tacheDrag = tacheDrag;
   }

   public TacheMere getTacheDrag(){
         return this.tacheDrag;
   }

   /**Methode pour changer la tache selctionner
    * @param tacheSelectionner tache à selectionner
    * */
    public void setTacheSelectionner(TacheMere tacheSelectionner) {
        this.tacheSelectionner = tacheSelectionner;
        /**
         * On cherche la colonne qui contient la tache selectionner
         * */
        for(Conteneur c:this.tableaux.get(idTableauCourant).getColonnes()){
            if (c.getTaches().contains(tacheSelectionner)){
                this.colonneSelectionner=this.tableaux.get(idTableauCourant).getColonnes().indexOf(c);
                break;
            }
        }
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
        return this.tableaux.get(idTableauCourant);
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
        this.tableaux=new ArrayList<Tableau>();
        this.idTableauCourant=0;
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
        if(index>=0 && index<this.tableaux.get(idTableauCourant).getColonnes().size()){
            this.colonneSelectionner=index;
        }
    }

    /**
     * methode pour créer une tache
     * @param titre titre de la tâche
     * @param description description de la tâche
     * */

    public void creerTache(String titre, String description) throws TacheNomVideException {
        TacheMere tache = new TacheMere(titre, description);
        this.tableaux.get(idTableauCourant).getColonnes().get(this.colonneSelectionner).ajouterTache(tache);
        this.changement = new Changement(this.tableaux.get(idTableauCourant).getColonnes().get(this.colonneSelectionner).getTaches().size()-1,this.colonneSelectionner,"ajout");
        this.notifierObservateur();
    }

    /**
     * methode pour créer une sous tache
     * @param titre
     * @param description
     * @throws TacheNomVideException
     */

    public void creerSousTache(String titre, String description) throws TacheNomVideException {
        if (this.tacheSelectionner==null){
            return;
        }
        TacheMere tache = new TacheMere(titre, description);
        this.tacheSelectionner.ajouterSousTache(tache);
        this.notifierObservateur();
    }

    /**
     * methode pour avoir le dernier changement effectué
     * @return changement
     */
    public Changement getChangement() {
        return this.changement;
    }

    /**
     * methode pour changer le tableau courant
     * @param tableau
     */

    public void setTableauCourant(Tableau tableau) {
        this.tableaux.set(idTableauCourant,tableau);
    }

    /**
     * methode pour changer les tableaux
     * @param tableaux
     */

    public void setTableaux(ArrayList<Tableau> tableaux) {
        this.tableaux = tableaux;
    }

    /**
     * methode pour changer l'id du tableau courant
     * @param idTableauCourant
     */

    public void setIdTableauCourant(int idTableauCourant) {
        this.idTableauCourant = idTableauCourant;
        notifierObservateur();
    }

    /**
     * methode pour avoir l'id du tableau courant
     * @return idTableauCourant
     */

    public int getIdTableauCourant() {
        return this.idTableauCourant;
    }

/**
     * methode pour avoir les tableaux
     * @return tableaux
     */

    public ArrayList<Tableau> getTableaux() {
        return this.tableaux;
    }

    /**
     * methode pour ajouter un tableau
     * @param titre titre du tableau
     */

    public void ajouterTableau(String titre) {
        // si le tableau existe déjà, on change legerement le titre
        if(this.findByName(titre)!=null){
            this.ajouterTableau(titre+"_");
            return;
        }
        this.tableaux.add(new Tableau(titre));
        this.changement = new Changement(0,0,"newTableau");
        this.notifierObservateur();
    }


    /**
     * methode pour supprimer un tableau
     * @param i index du tableau à supprimer
     */

    public void supprimerTableau(int i) {
        this.tableaux.remove(i);
    }

    /**
     * methode pour trouver un tableau avec un nom
     * @param name nom du tableau
     * @return tableau
     */
    public Tableau findByName(String name){
        for(Tableau t:this.tableaux){
            if(t.getTitre().equals(name)){
                return t;
            }
        }
        return null;
    }

    /**
     * methode pour avoir la colonne selectionner
     * @return colonneSelectionner
     */

    public int getColonneSelectionner() {
        return this.colonneSelectionner;
    }
    public Conteneur getConteneurSelectionner(){
        return this.tableaux.get(idTableauCourant).getColonnes().get(this.colonneSelectionner);
    }

    public void switchFormulaire(int form){
        this.form=form;
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
        this.tableaux.get(idTableauCourant).getColonne(this.colonneSelectionner).getTaches().get(this.tableaux.get(idTableauCourant).getColonne(this.colonneSelectionner).getTaches().indexOf(this.tacheSelectionner)).setTitre(titre);
        this.tableaux.get(idTableauCourant).getColonne(this.colonneSelectionner).getTaches().get(this.tableaux.get(idTableauCourant).getColonne(this.colonneSelectionner).getTaches().indexOf(this.tacheSelectionner)).setDescription(description);
        this.notifierObservateur();
    }

    public void ajoutDependance(TacheMere tache1, String tache2){
        try{
            for(Tache tm:getTaches()){
                if (tm.getTitre().equals(tache2)){
                    this.dependance.ajouterDependance(tache1, tm);
                    this.dependance.calculerNiveau(tache1);
                    this.dependance.update();
                    //System.out.println(this.dependance);
                    this.notifierObservateur();
                    return;
                }
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * methode pour avoir le formulaire
     * @return
     */
    public void creerColonne(String titre){
        this.tableaux.get(idTableauCourant).ajouterColonne(new Conteneur(titre,this));
        this.notifierObservateur();
    }

    public boolean getFormulaire(){
        return this.formulaire;
    }

    /**
     * methode pour set une sous tache
     * @param sousTache
     */
    public void setSousTache(boolean sousTache) {
        this.sousTache = sousTache;
    }

    /**
     * methode pour avoir une sous tache
     * @return sousTache
     */
    public boolean getSousTache() {
        return this.sousTache;
    }

    public int getForm() {
        return this.form;
    }

    public ArrayList<Tache> getTaches(){
        ArrayList<Tache> taches = new ArrayList<>();
        for(Conteneur c:this.tableaux.get(idTableauCourant).getColonnes()){
            taches.addAll(c.getTaches());
        }
        return taches;
    }

    public Dependance getDependance() {
        return dependance;
    }

    public void actualiserGantt() {
    	this.gantt=!this.gantt;
        this.notifierObservateur();
    }

    public boolean isGantt() {
        return gantt;
    }


    public void archiverTache(Tableau tableau, Tache tache, int idConteneur) {
        this.archivage.archiverTache(tableau, tache, idConteneur);
        this.getConteneurSelectionner().getTaches().remove(tache);
        notifierObservateur();
    }

    public void actualiserArchive() {
    	this.archive=!this.archive;
        this.notifierObservateur();
    }

    public boolean isArchive() {
        return archive;
    }

    public void desarchiverTache(Tableau tableau, Tache tache) {
        int id=this.archivage.desarchiverTache(tableau, tache);
        this.getTableau().getColonnes().get(id).ajouterTache((TacheMere) tache);
        notifierObservateur();
    }
}
