package Composite;

public abstract class Tache {
    protected String description;

    public Tache(String description) {
        this.description = description;
    }

    public String afficherDescription(){
        return this.description;
    }
    public abstract boolean ajouterSousTache(Tache st);

    public void setDescription(String description) {
        this.description = description;
    }
}
