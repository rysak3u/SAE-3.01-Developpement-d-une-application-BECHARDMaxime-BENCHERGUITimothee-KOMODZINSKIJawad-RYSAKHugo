package TaskHub.Modele;

public class Changement {
    private int id_tache;
    private int id_colonne;
    private String action;

    public Changement(int id_tache, int id_colonne, String action) {
        this.id_tache = id_tache;
        this.id_colonne = id_colonne;
        this.action = action;
    }

    public int getId_tache() {
        return id_tache;
    }

    public int getId_colonne() {
        return id_colonne;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
