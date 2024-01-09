package TaskHub.Tache;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;

import java.util.ArrayList;

// Conteneur est une classe qui contient des taches
public class Conteneur {
    // titre et tache du conteneur
    private String titre;
    private ArrayList<TacheMere> taches;
    private ModeleTache modele;
    private static int id = 0;
    private int idConteneur;

    // getters et setters
    public String getTitre() {
        return this.titre;
    }

    public int getIdConteneur() {
        return idConteneur;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    public ArrayList<TacheMere> getTaches() {
        return this.taches;
    }
    public void ajouterTache(TacheMere tache){
        this.taches.add(tache);
    }
    public void supprimerTache(TacheMere tache) {
        this.taches.remove(tache);
    }
    public void supprimerTache(int index) {
        this.taches.remove(index);
    }


    /**
     * Constructeur de Conteneur
     * @param titre titre du conteneur
     */
    public Conteneur(String titre,ModeleTache modele) {
        this.titre = titre;
        this.taches = new ArrayList<TacheMere>();
        this.modele = modele;
        this.idConteneur = id;
        id++;
    }

    /**
     * Constructeur de Conteneur
     * @param titre titre du conteneur
     * @param taches taches du conteneur
     */
    public Conteneur(String titre, ArrayList<TacheMere> taches, ModeleTache modele) {
        this.titre = titre;
        this.taches = taches;
        this.modele = modele;
    }


}
