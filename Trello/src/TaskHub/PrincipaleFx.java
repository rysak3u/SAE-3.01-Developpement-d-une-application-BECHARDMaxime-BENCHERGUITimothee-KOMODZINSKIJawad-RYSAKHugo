package TaskHub;

import TaskHub.Controller.ControllerAfficherFormulaire;
import TaskHub.Controller.ControllerCréerTache;
import TaskHub.Modele.ModeleTache;
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

        // Mise en plein écran de la scène

        stagePrincipale.show();

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


            // Création du tableau contenant les listes de tâches
            VBox tableau = new VBox();
            Text titreTableau = new Text("nom du tableau");
            titreTableau.setFont(Font.font("Arial Black", FontWeight.BLACK, 30));
            Button voirTableau = new Button("Voir les tableaux");
            voirTableau.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));
            voirTableau.setPrefSize(200, 30);
            voirTableau.setAlignment(Pos.TOP_LEFT);
            tableau.getChildren().add(voirTableau);

            // Création du bouton d'ajout de tâche et son placement
            Button ajouterListe = new Button("Ajouter une liste");



            ajouterListe.setPadding(new Insets(10));
            ajouterListe.setPrefSize(200, 30);
            ajouterListe.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));

            // Création des conteneurs pour les listes de tâches
            VBox liste1 = new VBox(30);
            VBox liste2 = new VBox(30);

            // Création des conteneurs pour les tâches et leurs titres
            VBox tache1 = new VBox(10);
            VBox tache2 = new VBox(10);
            VBox tache3 = new VBox(10);

            // Définition des titres des listes et des tâches
            Text titreliste1 = new Text("Liste 1");
            titreliste1.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));
            Text titreliste2 = new Text("Liste 2");
            titreliste2.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));
            Text titretache1 = new Text("Tache 1");
            Text voirplustache1 = new Text("Voir plus...");
            voirplustache1.setUnderline(true);
            Text titretache2 = new Text("Tache 2");
            Text voirplustache2 = new Text("Voir plus...");
            voirplustache2.setUnderline(true);
            Text titretache3 = new Text("Tache 3");
            Text voirplustache3 = new Text("Voir plus...");
            voirplustache3.setUnderline(true);

            // Mise en forme des tâches et listes avec bordures, marges et alignements
            tache1.setPadding(new Insets(20));
            tache1.setPrefSize(200, 100);
            tache3.setPadding(new Insets(20));
            tache3.setPrefSize(200, 100);
            tache1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
            tache3.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
            liste1.setPadding(new Insets(50));
            liste1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
            liste1.setAlignment(Pos.TOP_CENTER);

            tache2.setPadding(new Insets(20));
            tache2.setPrefSize(200, 100);
            tache2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
            liste2.setPadding(new Insets(50));
            liste2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
            liste2.setAlignment(Pos.TOP_CENTER);


            ControllerAfficherFormulaire cAF = new ControllerAfficherFormulaire(modeleTache);
            // Création du bouton d'ajout de tâche liste 2
            Button ajoutertachelst1 = new Button("Ajouter une tâche");
            ajoutertachelst1.addEventHandler(MouseEvent.MOUSE_CLICKED,cAF);
            ajoutertachelst1.setPadding(new Insets(10));
            ajoutertachelst1.setPrefSize(200, 30);
            ajoutertachelst1.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));

            Button ajoutertachelst2 = new Button("Ajouter une tâche");
            ajoutertachelst2.setPadding(new Insets(10));
            ajoutertachelst2.setPrefSize(200, 30);
            ajoutertachelst2.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));

            // Ajout des titres et des tâches aux listes correspondantes
            tache1.getChildren().addAll(titretache1, voirplustache1);
            tache3.getChildren().addAll(titretache3, voirplustache3);
            tache2.getChildren().addAll(titretache2, voirplustache2);
            liste1.getChildren().addAll(titreliste1, tache1, tache3, ajoutertachelst1);
            liste2.getChildren().addAll(titreliste2, tache2, ajoutertachelst2);




            // Ajout des listes au tableau
            HBox listes = new HBox(50);
            listes.getChildren().addAll(liste1, liste2, ajouterListe);
            tableau.getChildren().addAll(titreTableau, listes);


            // Ajout de tous les éléments à la VBox principale
            vbox.getChildren().addAll(titrePrin, tableau);

           scenePrincipale= new Scene(vbox, 300, 250);
           stagePrincipale=new Stage();
           stagePrincipale.setScene(scenePrincipale);
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
