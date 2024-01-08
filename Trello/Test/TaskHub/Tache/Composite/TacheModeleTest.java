package TaskHub.Tache.Composite;

import TaskHub.Exception.TacheNomVideException;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Tableau;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TacheModeleTest {

    private ModeleTache modeleTache;
    private TacheMere m;

    @BeforeEach
    public void init() throws TacheNomVideException {
        this.modeleTache = new ModeleTache();
        this.m=new TacheMere("test","test");
        Conteneur c=new Conteneur("test",this.modeleTache);
        c.ajouterTache(m);
        Tableau t=new Tableau("test");
        t.ajouterColonne(c);
        this.modeleTache.setTableauCourant(t);
        this.modeleTache.setTacheSelectionner(m);
    }

    // Test de la méthode ajouterTache
    @Test
    public void modifierTacheOK()throws TacheNomVideException{
        this.modeleTache.modifierTache("test2","test2");
        assert(m.getTitre().equals("test2"));
    }


    // Test de la méthode ajouterTache si il n'y a pas de tache selectionner
    @Test
    public void modifierTacheKO()throws TacheNomVideException{
        this.modeleTache.setTacheSelectionner(null);
        this.modeleTache.modifierTache("","");
        assert(m.getTitre().equals("test"));
    }

    // Test de la méthode creerSousTache
    @Test
    public void ajouterSousTacheOK()throws TacheNomVideException{
        this.modeleTache.creerSousTache("test","test");
        assert(m.getSousTache().size()==1);
    }

    // Test de la méthode creerSousTache si il n'y a pas de tache selectionner
    @Test
    public void ajouterSousTacheKO()throws TacheNomVideException{
        this.modeleTache.setTacheSelectionner(null);
        this.modeleTache.creerSousTache("test","test");
        assert(m.getSousTache().size()==0);
    }

    @AfterEach
    public void clear(){
        this.modeleTache=null;
        this.m=null;
    }
}
