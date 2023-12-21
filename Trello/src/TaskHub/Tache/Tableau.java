package TaskHub.Tache;

import java.util.ArrayList;

public class Tableau {
    // titre et conteneur du tableau
    ArrayList<Conteneur> colonnes;
    private String titre;

    /**
     * Constructeur de Tableau
     * @param titre titre du tableau
     */
    public Tableau(String titre){
        this.titre = titre;
        this.colonnes = new ArrayList<Conteneur>();
    }

    /**
     * Constructeur de Tableau
     * @param titre titre du tableau
     * @param colonnes colonnes du tableau
     */
    public Tableau(String titre, ArrayList<Conteneur> colonnes){
        this.titre = titre;
        this.colonnes = colonnes;
    }

    // Getters et setters
    public void ajouterColonne(Conteneur colonne){
        this.colonnes.add(colonne);
    }
    public void retirerColonne(Conteneur colonne){
        this.colonnes.remove(colonne);
    }

    public void setTitre(String titre){
        this.titre = titre;
    }
    public String getTitre(){
        return this.titre;
    }

    public Conteneur getColonne(int i) {
        return this.colonnes.get(i);
    }

    public ArrayList<Conteneur> getColonnes() {
        return this.colonnes;
    }

}
