package TaskHub;

import TaskHub.Exception.TacheNomVideException;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Tableau;
import TaskHub.Vue.VueConteneurs;
import TaskHub.Vue.VueFormulaire;
import TaskHub.Vue.VuePrincipal;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrincipaleFx extends Application {


    private ModeleTache modeleTache;

    private Stage stagePrincipale;
    @Override
    public void start(Stage pstage) throws Exception {

        this.initModele();
        VuePrincipal vp=new VuePrincipal(this.modeleTache);
        //this.initPrincipale();
        this.modeleTache.enregistrerObservateur(new VueFormulaire(this.modeleTache));
        this.modeleTache.enregistrerObservateur(vp);
    }

/**
        public void initPrincipale() {

           stagePrincipale= new Stage();
           stagePrincipale.setScene(scenePrincipale);
           stagePrincipale.setFullScreen(true);
           stagePrincipale.show();
        }
*/
        public void initModele(){
            this.modeleTache=new ModeleTache();
        }
    public static void main(String[] args) {
        launch(args);
    }
}
