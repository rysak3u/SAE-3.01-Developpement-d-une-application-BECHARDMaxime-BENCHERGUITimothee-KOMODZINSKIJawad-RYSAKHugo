package TaskHub;

import TaskHub.Modele.ModeleTache;
import TaskHub.Vue.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class PrincipaleFx extends Application {
    // Attributs
    private ModeleTache modeleTache;
    private Stage stagePrincipale;
    /**
     * Méthode start
     * @param pstage fenêtre principale
     * @throws Exception exception
     */
    @Override
    public void start(Stage pstage) throws Exception {
        // Création du modèle
        this.modeleTache=new ModeleTache();

        // Création de la fenêtre principale
        VuePrincipal vp=new VuePrincipal(this.modeleTache);

        // Création des vues et enregistrement des observateurs
        this.modeleTache.enregistrerObservateur(new VueFormulaireTache(this.modeleTache));
        this.modeleTache.enregistrerObservateur(new VueFormulaireColonne(this.modeleTache));
        this.modeleTache.enregistrerObservateur(new VueFormulaireTableau(this.modeleTache));
        this.modeleTache.enregistrerObservateur(new VueFormulaireDependance(this.modeleTache));
        this.modeleTache.enregistrerObservateur(vp);
    }

    /**
     * Méthode main
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
