package TaskHub;

import TaskHub.Modele.ModeleTache;
import TaskHub.Vue.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class PrincipaleFx extends Application {

    private ModeleTache modeleTache;

    private Stage stagePrincipale;
    @Override
    public void start(Stage pstage) throws Exception {
        this.modeleTache=new ModeleTache();
        VuePrincipal vp=new VuePrincipal(this.modeleTache);
        this.modeleTache.enregistrerObservateur(new VueFormulaireTache(this.modeleTache));
        this.modeleTache.enregistrerObservateur(new VueFormulaireColonne(this.modeleTache));
        this.modeleTache.enregistrerObservateur(new VueFormulaireTableau(this.modeleTache));
        this.modeleTache.enregistrerObservateur(new VueFormulaireDependance(this.modeleTache));
        this.modeleTache.enregistrerObservateur(vp);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
