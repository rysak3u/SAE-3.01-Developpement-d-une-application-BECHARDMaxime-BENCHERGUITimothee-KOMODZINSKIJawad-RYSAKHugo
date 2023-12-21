package TaskHub.Vue;

import TaskHub.Controller.ControllerAfficherFormulaire;
import TaskHub.Controller.ControllerDetailsTache;
import TaskHub.Controller.ControllerRetour;
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

    private StrategieVisuel strategieVisuel;
    /**
     * Constructeur de la classe VuePrincipal.puml
     *
     */
    public VuePrincipal(ModeleTache modeleTache){
        super();
        this.strategieVisuel=new StrategieVisuelBureau();
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
            tab.ajouterConteneur(cont);
            Conteneur cont2 = new Conteneur("Liste 2",this.modeleTache);
            cont2.ajouterTache(new TacheMere("Tache 4", "Description 4"));
            cont2.ajouterTache(new TacheMere("Tache 5", "Description 5"));
            tab.ajouterConteneur(cont2);
            this.strategieVisuel.affichage(this.modeleTache);
            //tableau.actualiser(this.modeleTache);
        } catch (TacheNomVideException e) {
            e.printStackTrace();
        }

        // Ajout de tous les éléments à la VBox principale
        vbox.getChildren().addAll(titrePrin, (Pane)this.strategieVisuel);

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
            this.strategieVisuel.affichage(this.modeleTache);
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
        // On récupère la tâche sélectionnée
        TacheMere tache = this.modeleTache.getTacheSelectionner();

        // Création de la structure de la fenêtre
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Création du titre
        Text scenetitle = new Text("Détail de la tâche : "+tache.getTitre());
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        //Création des labels et placement
        Label desc = new Label("Description : ");
        grid.add(desc, 0, 1);
        Label description = new Label(tache.getDescription());
        grid.add(description, 1, 1);
        Label ds = new Label("Sous-Tâche : ");
        grid.add(ds, 0, 2);

        // Création de la VBox qui contient les sous-tâches
        VBox sousTaches = new VBox();
        // Pour chaque sous-tâche, on crée une VBox
        for(Tache t : tache.getSousTache()){
            VBox vboxt = new VBox();

            // Ajout des Titres et descriptions des sous-tâches
            vboxt.getChildren().addAll(new Text(t.getTitre()),new Text(t.getDescription()));
            // Ajout de l'eventHandler pour la sélection de la tâche
            vboxt.addEventHandler(MouseEvent.MOUSE_CLICKED,new ControllerDetailsTache(modeleTache,(TacheMere) t));

            // Style de la VBox
            vboxt.setPadding(new Insets(20));
            vboxt.setPrefSize(200, 100);
            vboxt.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
            // ajout de la VBox à la VBox principale
            sousTaches.getChildren().add(vboxt);
        }
        // Ajout de la VBox des sous-tâches à la fenêtre
        grid.add(sousTaches, 0, 3);

        // Création des boutons
        Button btnCreer = new Button("Créer Sous-Tâche");
        btnCreer.addEventHandler(MouseEvent.MOUSE_CLICKED,new ControllerAfficherFormulaire(this.modeleTache, this.modeleTache.getColonneSelectionner()));
        Button btnAnnuler = new Button("Retour");
        btnAnnuler.addEventHandler(ActionEvent.ACTION, new ControllerRetour(this.modeleTache));
        Button btnModifier = new Button("Modifier");
        btnModifier.addEventHandler(MouseEvent.MOUSE_CLICKED,new ControllerAfficherFormulaire(this.modeleTache, this.modeleTache.getColonneSelectionner()));

        Button btnArchiver = new Button("Archiver");
        Button btnGantt = new Button("Diagramme de Gantt");

        // Ajout des boutons dans une HBox qu'on ajoute à la fenêtre
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnAnnuler, btnGantt, btnArchiver, btnModifier, btnCreer);
        grid.add(hbBtn, 1, 4);
        // Création de la scène et affichage
        Scene sc= new Scene(grid, 300, 250);
        this.setScene(sc);
        this.show();
    }
}
