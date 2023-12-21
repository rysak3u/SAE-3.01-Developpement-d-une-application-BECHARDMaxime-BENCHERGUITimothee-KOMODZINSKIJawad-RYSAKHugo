package TaskHub.Vue;

import TaskHub.Controller.ControllerAfficherFormulaire;
import TaskHub.Controller.ControllerDetailsTache;
import TaskHub.Controller.ControllerRetour;
import TaskHub.Controller.ControllerVuePrincipale;
import TaskHub.Exception.TacheNomVideException;
import TaskHub.Modele.ModeleTache;
import TaskHub.Modele.Sujet;

import TaskHub.Strategie.StrategieVisuel;
import TaskHub.Strategie.StrategieVisuelBureau;

import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Tableau;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Classe VuePrincipal.puml
 * **/
public class VuePrincipal extends Stage implements Observateur{

    private ModeleTache modeleTache;

    private Scene scenePrincipale;


    private StrategieVisuel affichage;


    /**
     * Constructeur de la classe VuePrincipal.puml
     *
     */
    public VuePrincipal(ModeleTache modeleTache){
        super();
        this.affichage=new StrategieVisuelBureau();
        this.modeleTache=modeleTache;
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
        //VueConteneurs tableau = new VueConteneurs();

        //modeleTache.enregistrerObservateur(tableau);
        Tableau tab = new Tableau("Tableau 1");
        this.modeleTache.setTableau(tab);
        try {
            Conteneur cont = new Conteneur("Liste 1",this.modeleTache);
            cont.ajouterTache(new TacheMere("Tache 1", "Description 1"));
            cont.ajouterTache(new TacheMere("Tache 2", "Description 2"));
            cont.ajouterTache(new TacheMere("Tache 3", "Description 3"));
            tab.ajouterColonne(cont);
            Conteneur cont2 = new Conteneur("Liste 2",this.modeleTache);
            cont2.ajouterTache(new TacheMere("Tache 4", "Description 4"));
            cont2.ajouterTache(new TacheMere("Tache 5", "Description 5"));
            tab.ajouterColonne(cont2);
            this.affichage.affichage(this.modeleTache);
            //tableau.actualiser(this.modeleTache);
        } catch (TacheNomVideException e) {
            e.printStackTrace();
        }

        // Création du ComboBox pour le choix de l'affichage
        ComboBox<String> choixAffichage = new ComboBox<String>( );
        choixAffichage.getItems().add("Affichage Bureau");
        choixAffichage.getItems().add("Affichage Liste");
        choixAffichage.setOnAction(new ControllerVuePrincipale(this));

        // Ajout de tous les éléments à la VBox principale
        vbox.getChildren().addAll(titrePrin, this.affichage);

        // Mise en plein écran de la scène
        this.scenePrincipale= new Scene(vbox, 300, 250);

        //scenePrincipale.getStylesheets().add("styleFormulaire.css");
        this.setScene(scenePrincipale);
        this.setFullScreen(true);
        this.show();

    }

    /**
     * Méthode qui va permettre de mettre à jour la vue
     *
     * @param s modèle pour lequelle la vue va se baser
     */
    @Override
    public void actualiser(Sujet s) {
        // Si aucune tâche n'est sélectionnée, on affiche la vue principale
        if(((ModeleTache)s).getTacheSelectionner()==null){
           this.affichage.affichage(this.modeleTache);
            this.setScene(this.scenePrincipale);

        }
        else{
            // Sinon on affiche les détails de la tâche sélectionnée
            this.detailTache();
        }

    }

    /**
     * Méthode qui va permettre d'afficher les détails d'une tâche
     */
    public void detailTache(){
        TacheMere tache = this.modeleTache.getTacheSelectionner();

        // Création de la structure de la fenêtre
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #8a2be2;"); // Fond violet

        // Création du titre
        Text scenetitle = new Text("Détail de la tâche : " + tache.getTitre());
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setFill(Color.WHITE); // Texte blanc
        grid.add(scenetitle, 0, 0, 2, 1);

        // Création des labels et placement
        Label desc = new Label("Description : ");
        desc.setTextFill(Color.WHITE); // Texte blanc
        grid.add(desc, 0, 1);

        Label description = new Label(tache.getDescription());
        description.setTextFill(Color.WHITE); // Texte blanc
        grid.add(description, 1, 1);

        Label ds = new Label("Sous-Tâche : ");
        ds.setTextFill(Color.WHITE); // Texte blanc
        grid.add(ds, 0, 2);

        // Création de la VBox qui contient les sous-tâches
        VBox sousTaches = new VBox();

// Pour chaque sous-tâche, on crée une VBox
        for (Tache t : tache.getSousTache()) {
            VBox vboxt = new VBox();

            // Ajout des Titres et descriptions des sous-tâches
            Text titre = new Text(t.getTitre());
            Text descriptiont = new Text(t.getDescription());
            titre.setFill(Color.WHITE);
            descriptiont.setFill(Color.WHITE);
            vboxt.getChildren().addAll(titre, descriptiont);

            // Ajout de l'eventHandler pour la sélection de la tâche
            vboxt.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerDetailsTache(modeleTache, (TacheMere) t));

            // Style de la VBox
            vboxt.setPadding(new Insets(20));
            vboxt.setPrefSize(200, 100);
            vboxt.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
            // Fond blanc pour les sous-tâches
            vboxt.setStyle("-fx-background-color: #9c8ae2; -fx-background-radius: 10; -fx-border-radius: 10;");
            // ajout de la VBox à la VBox principale
            sousTaches.getChildren().add(vboxt);
        }

        // Style de la VBox des sous-tâches
        sousTaches.setSpacing(10);
        sousTaches.setAlignment(Pos.CENTER);

        // Ajout de la VBox des sous-tâches à la fenêtre
        grid.add(sousTaches, 0, 3);

        // Création des boutons
        Button btnCreer = new Button("Créer Sous-Tâche");
        btnCreer.setStyle("-fx-background-color: #ff69b4; -fx-text-fill: white;"); // Rose
        btnCreer.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerAfficherFormulaire(this.modeleTache, this.modeleTache.getColonneSelectionner()));

        Button btnAnnuler = new Button("Retour");
        btnAnnuler.setStyle("-fx-background-color: #ff69b4; -fx-text-fill: white;"); // Rose
        btnAnnuler.addEventHandler(ActionEvent.ACTION, new ControllerRetour(this.modeleTache));

        Button btnModifier = new Button("Modifier");
        btnModifier.setStyle("-fx-background-color: #ff69b4; -fx-text-fill: white;"); // Rose
        btnModifier.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerAfficherFormulaire(this.modeleTache, this.modeleTache.getColonneSelectionner()));

        Button btnArchiver = new Button("Archiver");
        btnArchiver.setStyle("-fx-background-color: #ff69b4; -fx-text-fill: white;"); // Rose

        Button btnGantt = new Button("Diagramme de Gantt");
        btnGantt.setStyle("-fx-background-color: #ff69b4; -fx-text-fill: white;"); // Rose

        // Ajout des boutons dans une HBox qu'on ajoute à la fenêtre
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnAnnuler, btnGantt, btnArchiver, btnModifier, btnCreer);

        // Style de la HBox des boutons
        hbBtn.setSpacing(10);
        hbBtn.setAlignment(Pos.CENTER);

        // Ajout de la HBox des boutons à la fenêtre
        grid.add(hbBtn, 1, 4);

        // Création de la scène et affichage
        Scene sc = new Scene(grid);
        sc.setFill(Color.web("#8a2be2")); // Fond violet
        this.setScene(sc);
        this.show();
    }

    /**
     * Méthode qui va permettre de changer la stratégie d'affichage (Bureau ou Liste)
     *
     * @param affichage stratégie d'affichage
     */
    public void setAffichage(StrategieVisuel affichage){
        this.affichage=affichage;
    }
}
