package TaskHub.Controller;

import TaskHub.Strategie.*;
import TaskHub.Vue.VuePrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

/**
 * Classe ControllerVuePrincipale qui permet de changer la stratégie d'affichage en fonction de la valeur de la ComboBox
 */
public class ControllerVuePrincipale implements EventHandler<ActionEvent> {

    // Attribut
    private VuePrincipal vuePrincipal;

    /**
     * Constructeur de la classe ControllerVuePrincipale
     * @param vuePrincipal
     */
    public ControllerVuePrincipale(VuePrincipal vuePrincipal) {
        this.vuePrincipal = vuePrincipal;
    }

    /**
     * Méthode qui permet de changer la stratégie d'affichage en fonction de la valeur de la ComboBox
     * @param event
     */
    @Override
    public void handle(ActionEvent event) {
        // On récupère la valeur de la ComboBox
        ComboBox<String> b=(ComboBox<String>)event.getSource();
        String f=b.getValue();
        switch (f){
            // On change la stratégie d'affichage en fonction de la valeur de la ComboBox
            case "Affichage Bureau":
                this.vuePrincipal.setAffichage(new StrategieVisuelBureau());
                break;
            case "Affichage Liste":
                this.vuePrincipal.setAffichage(new VisuelListe());
                break;
        }
    }
}
