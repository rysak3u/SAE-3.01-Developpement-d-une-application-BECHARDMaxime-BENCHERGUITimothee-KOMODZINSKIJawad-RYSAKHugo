package TaskHub.Modele;

public class Changement {
    // Attributs
    private int id_tache;
    private int id_colonne;
    private String action;

    /**
     * Constructeur de Changement
     * @param id_tache id de la tache
     * @param id_colonne id de la colonne
     * @param action action
     */
    public Changement(int id_tache, int id_colonne, String action) {
        this.id_tache = id_tache;
        this.id_colonne = id_colonne;
        this.action = action;
    }

    /**
     * Methode pour get l'id de la tache
     * @return id_tache
     */
    public int getId_tache() {
        return id_tache;
    }

    /**
     * Methode pour get l'id de la colonne
     * @return id_colonne
     */
    public int getId_colonne() {
        return id_colonne;
    }

    /**
     * Methode pour get l'action
     * @return action
     */
    public String getAction() {
        return action;
    }

    /**
     * Methode pour set l'action
     * @param action action
     */
    public void setAction(String action) {
        this.action = action;
    }
}
