package TaskHub.Tache;

import TaskHub.Exception.TacheNomVideException;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DependanceTest {

    private ArrayList<Tache> taches;
    private Dependance d;

    @BeforeEach
    void setUp() throws TacheNomVideException {
        this.d = new Dependance();
        this.taches = new ArrayList<Tache>();
        this.taches.add(new TacheMere("A","A"));
        this.taches.add(new TacheMere("B","B"));
        this.taches.add(new TacheMere("C","C"));
        this.taches.add(new TacheMere("D","D"));
        this.taches.add(new TacheMere("E","E"));
    }

    @Test
    void ajouterDependance() {
        this.d.ajouterDependance(this.taches.get(0),this.taches.get(1));
        assertEquals(1,this.d.getDependance(this.taches.get(0)).size());
        assertEquals("B",this.d.getDependance(this.taches.get(0)).get(0).getTitre());
        assertEquals("A", this.d.getSuccessors(this.taches.get(1)).get(0).getTitre());
        this.d.ajouterDependance(this.taches.get(0),this.taches.get(2));
        assertEquals(2,this.d.getDependance(this.taches.get(0)).size());
        assertEquals("C",this.d.getDependance(this.taches.get(0)).get(1).getTitre());
        assertEquals("A", this.d.getSuccessors(this.taches.get(2)).get(0).getTitre());
    }

    @Test
    void ajouterDependanceExceptionDouble() {
        this.d.ajouterDependance(this.taches.get(0),this.taches.get(1));
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            this.d.ajouterDependance(this.taches.get(1),this.taches.get(0));
        });
    }

    @Test
    void ajouterDependanceExceptionCycle() {
        this.d.ajouterDependance(this.taches.get(0),this.taches.get(1));
        this.d.ajouterDependance(this.taches.get(1),this.taches.get(2));
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            this.d.ajouterDependance(this.taches.get(2),this.taches.get(0));
        });
    }

    @Test
    void update() {
        this.d.ajouterDependance(this.taches.get(0),this.taches.get(1));
        this.d.calculerNiveau(this.taches.get(0));
        this.d.ajouterDependance(this.taches.get(1),this.taches.get(2));
        this.d.calculerNiveau(this.taches.get(1));
        this.d.update();
        this.d.ajouterDependance(this.taches.get(2),this.taches.get(3));
        this.d.calculerNiveau(this.taches.get(2));
        this.d.update();
        this.d.ajouterDependance(this.taches.get(3),this.taches.get(4));
        this.d.calculerNiveau(this.taches.get(3));
        this.d.update();
        assertEquals(4,this.taches.get(0).getNiv());
        assertEquals(3,this.taches.get(1).getNiv());
        assertEquals(2,this.taches.get(2).getNiv());
        assertEquals(1,this.taches.get(3).getNiv());
        assertEquals(0,this.taches.get(4).getNiv());
    }

    @AfterEach
    void chaos() {
        this.taches=null;
        this.d=null;
    }
}