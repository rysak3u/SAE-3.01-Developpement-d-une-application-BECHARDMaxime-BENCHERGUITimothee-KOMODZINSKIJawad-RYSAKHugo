package TaskHub.Tache.Composite;

import TaskHub.Exception.TacheNomVideException;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Tableau;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TacheTest {
    private TacheMere t;

    @BeforeEach
    void setUp() throws TacheNomVideException {
        this.t = new TacheMere("testMere0","testMere0");
        TacheMere tM0 = new TacheMere("testMere1","testMere1");
        tM0.ajouterSousTache(new SousTache("testSousTache0","testSousTache0"));
        this.t.ajouterSousTache(tM0);
    }

    @Test
    void ajouterSousTache() throws TacheNomVideException {
        TacheMere tM1 = new TacheMere("testMere2","testMere2");
        tM1.ajouterSousTache(new SousTache("testSousTache1","testSousTache1"));
        tM1.ajouterSousTache(new SousTache("testSousTache2","testSousTache2"));
        this.t.ajouterSousTache(tM1);
        assertEquals(2,this.t.getSousTache().size());
    }



    @AfterEach
    void chaos() {
        this.t = null;
    }
}