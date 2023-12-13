package TaskHub;

import TaskHub.Controller.ControllerAfficherFormulaire;
import TaskHub.Controller.ControllerCréerTache;
import TaskHub.Exception.TacheNomVideException;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Tableau;
import TaskHub.Vue.VueConteneurs;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import static javafx.event.ActionEvent.*;

public class PrincipaleFx extends Application {

    private  Scene sceneFormulaireCréerTache, scenePrincipale;
    private ModeleTache modeleTache;

    private Stage stagePrincipale,stageForm;
    @Override
    public void start(Stage pstage) throws Exception {

        this.initModele();
        this.initPrincipale();
        this.initFormulaireCréerTache();

    }

    /**
     * Méthode pour initialiser la fenêtre de création de tâche
     */
    public void initFormulaireCréerTache() {
        //Création du grid qui contiendra les textfields et les labels
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

            Text scenetitle = new Text("Création de Tâche");
            scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            grid.add(scenetitle, 0, 0, 2, 1);
            //Création des labels et des textfields
            Label name = new Label("Nom de La Tache:");
            grid.add(name, 0, 1);

            TextField nameTextField = new TextField();
            grid.add(nameTextField, 1, 1);

            Label ds = new Label("Description:");
            grid.add(ds, 0, 2);

            TextField tfDesc = new TextField();
            grid.add(tfDesc, 1, 2);
            ControllerCréerTache controllerCréerTache = new ControllerCréerTache(modeleTache, nameTextField, tfDesc);
            Button btnCreer = new Button("Créer Tâche");
            Button btnAnnuler = new Button("Annuler");
            btnCreer.addEventHandler(MouseEvent.MOUSE_CLICKED,controllerCréerTache);
            btnAnnuler.addEventHandler(MouseEvent.MOUSE_CLICKED,controllerCréerTache);
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btnCreer);
            hbBtn.getChildren().add(btnAnnuler);
            grid.add(hbBtn, 1, 4);
            sceneFormulaireCréerTache = new Scene(grid, 300, 250);
            stageForm=new Stage();
            stageForm.setScene(sceneFormulaireCréerTache);
        }

        public void initPrincipale() {

            // Création de la structure de la fenêtre principale
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(20));

            // Création du titre principal centré
            HBox titrePrin = new HBox();
            Text titrePrincipale = new Text("TaskHub".toUpperCase());
            titrePrincipale.setFont(Font.font("Arial Black", FontWeight.BLACK, 45));
            titrePrin.setAlignment(Pos.TOP_CENTER);
            titrePrin.getChildren().add(titrePrincipale);

            // Création du tableau



            VueConteneurs tableau = new VueConteneurs(this.modeleTache);
            modeleTache.enregisterObservateur(tableau);
            ControllerAfficherFormulaire con = new ControllerAfficherFormulaire(this.modeleTache);
            Tableau tab = new Tableau("Tableau 1");
            this.modeleTache.setTableau(tab);

            try {
                Conteneur cont = new Conteneur("Liste 1",this.modeleTache);
                cont.ajouterTache(new TacheMere("Tache 1", "Description 1"));
                cont.ajouterTache(new TacheMere("Tache 2", "Description 2"));
                cont.ajouterTache(new TacheMere("Tache 3", "Description 3"));
                tab.ajouterConteneur(cont);
                Conteneur cont2 = new Conteneur("Liste 2",this.modeleTache);
                cont2.ajouterTache(new TacheMere("Tache 4", "Description 4"));
                cont2.ajouterTache(new TacheMere("Tache 5", "Description 5"));
                tab.ajouterConteneur(cont2);
                tableau.actualiser(this.modeleTache);
            } catch (TacheNomVideException e) {
                e.printStackTrace();
            }


            // Ajout de tous les éléments à la VBox principale
            vbox.getChildren().addAll(titrePrin, tableau);


            // Mise en plein écran de la scène



           scenePrincipale= new Scene(vbox, 300, 250);
           stagePrincipale= new Stage();
           stagePrincipale.setScene(scenePrincipale);
           stagePrincipale.setFullScreen(true);
           stagePrincipale.show();
        }
        /**
         * Méthode pour afficher la fenêtre de création de tâche
         */
        public void showForm(){
            stageForm.show();
        }
        /**
         * Méthode pour fermer la fenêtre de création de tâche
         */
        public void closeForm(){
            stageForm.close();
        }

        public void initModele(){
            this.modeleTache=new ModeleTache(this);
        }
    public static void main(String[] args) {
        launch(args);
    }
}
