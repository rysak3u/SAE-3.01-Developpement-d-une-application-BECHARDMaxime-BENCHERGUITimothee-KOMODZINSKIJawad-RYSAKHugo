package TaskHub.Modele;

import TaskHub.Exception.TacheNomVideException;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Tableau;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ModeleTest {
    private ModeleTache m;
    @BeforeEach
    void setUp() throws TacheNomVideException {
        this.m = new ModeleTache();
        ArrayList<TacheMere> taches = new ArrayList<TacheMere>();
        taches.add(new TacheMere("test0","test0"));
        taches.add(new TacheMere("test1","test1"));
        taches.add(new TacheMere("test2","test2"));
        Conteneur c0 = new Conteneur("test0", taches,m);
        Conteneur c1 = new Conteneur("test1", taches,m);
        Conteneur c2 = new Conteneur("test1", taches,m);
        ArrayList<Conteneur> colonnes = new ArrayList<Conteneur>();
        colonnes.add(c0);
        colonnes.add(c1);
        colonnes.add(c2);
        m.setTableau(new Tableau("test",colonnes));
    }

    @Test
    void changerColonneSelectionner() {
        this.m.changerColonneSelectionner(1);
        assertEquals(1,this.m.getColonneSelectionner());
    }

    @Test
    void creerTache() throws TacheNomVideException {
        this.m.creerTache("test4","test4");
        assertEquals(4,this.m.getTableau().getConteneur(1).getTaches().size());
    }

    @AfterEach
    void chaos() {
        this.m = null;
    }
}