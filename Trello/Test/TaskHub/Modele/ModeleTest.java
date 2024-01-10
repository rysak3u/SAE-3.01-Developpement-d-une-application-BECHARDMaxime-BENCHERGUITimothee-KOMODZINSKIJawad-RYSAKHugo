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
        Tableau t = new Tableau("test",colonnes);
        ArrayList<Tableau> tx = new ArrayList<Tableau>();
        tx.add(t);
        this.m.setTableaux(tx);
    }

    @Test
    void changerColonneSelectionner() {
        this.m.changerColonneSelectionner(1);
        assertEquals(1,this.m.getColonneSelectionner());
    }

    @Test
    void creerTache() throws TacheNomVideException {
        this.m.creerTache("test4","test4");
        assertEquals(4,this.m.getTableau().getColonne(1).getTaches().size());
    }

    @Test
    void ajouterColonne(){
        assertEquals(3,this.m.getTableau().getColonnes().size());
        this.m.creerColonne("test4");
        assertEquals(4,this.m.getTableau().getColonnes().size());
    }

    @Test
    void ajouterTableau(){
        assertEquals(1,this.m.getTableaux().size());
        this.m.ajouterTableau("test");
        assertEquals(2,this.m.getTableaux().size());
    }

    @Test
    void archiverTache() {
        Tache t=this.m.getTableau().getColonne(0).getTaches().get(0);
        assertEquals(3,this.m.getTableau().getColonne(0).getTaches().size());
        this.m.archiverTache(this.m.getTableau(), t, this.m.getTableau().getColonne(0).getIdConteneur());
        assertEquals(2,this.m.getTableau().getColonne(0).getTaches().size());
        assertTrue(this.m.getArchivage().isTache(this.m.getTableau(), t));
    }

    @Test
    void desarchiverTache() {
        Tache t=this.m.getTableau().getColonne(0).getTaches().get(0);
        this.m.archiverTache(this.m.getTableau(), t, this.m.getTableau().getColonne(0).getIdConteneur());
        assertEquals(2,this.m.getTableau().getColonne(0).getTaches().size());
        this.m.desarchiverTache(this.m.getTableau(),t);
        assertEquals(3,this.m.getTableau().getColonne(0).getTaches().size());
        assertFalse(this.m.getArchivage().isTache(this.m.getTableau(), t));
    }

    @AfterEach
    void chaos() {
        this.m = null;
    }
}