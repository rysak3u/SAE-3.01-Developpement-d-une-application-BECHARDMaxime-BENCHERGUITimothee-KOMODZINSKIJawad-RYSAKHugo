package TaskHub.Controller;

import TaskHub.Vue.VuePrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class ControllerVuePrincipale implements EventHandler<ActionEvent> {

    private VuePrincipal vuePrincipal;

    public ControllerVuePrincipale(VuePrincipal vuePrincipal) {
        this.vuePrincipal = vuePrincipal;
    }

    @Override
    public void handle(ActionEvent event) {
        ComboBox<String> b=(ComboBox<String>)event.getSource();
        String f=b.getValue();
        switch (f){
            case "Affichage Bureau":
                this.vuePrincipal.setAffichage(new VisuelBureau());
                break;
            case "Affichage Bureau":
                this.vuePrincipal.setAffichage(new VisuelListe());
                break;
        }
    }
}
