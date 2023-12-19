package TaskHub.Vue;

import TaskHub.Controller.ControllerAfficherFormulaire;
import TaskHub.Controller.ControllerCréerTache;
import TaskHub.Controller.ControllerDetailsTache;
import TaskHub.Controller.ControllerRetour;
import TaskHub.Exception.TacheNomVideException;
import TaskHub.Modele.ModeleTache;
import TaskHub.Modele.Sujet;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Tableau;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;


/**
 * Classe VuePrincipal
 * **/
public class VuePrincipal extends Stage implements Observateur{

    private ModeleTache modeleTache;

    private Scene scenePrincipale;
    /**
     * Constructeur de la classe VuePrincipal
     *
     */
    public VuePrincipal(ModeleTache modeleTache){
        super();
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
        VueConteneurs tableau = new VueConteneurs();

        modeleTache.enregistrerObservateur(tableau);
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
        this.scenePrincipale= new Scene(vbox, 300, 250);

        //scenePrincipale.getStylesheets().add("styleFormulaire.css");
        this.setScene(scenePrincipale);
        this.setFullScreen(true);
        this.show();
    }

    /**
     * @param s modèle pour lequelle la vue va se baser
     */
    @Override
    public void actualiser(Sujet s) {
        if(((ModeleTache)s).getTacheSelectionner()==null){
            this.setScene(this.scenePrincipale);
        }
        else{
            this.detailTache();
        }

    }

    public void detailTache(){
        TacheMere tache = this.modeleTache.getTacheSelectionner();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Détail de la tâche : "+tache.getTitre());
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        //Création des labels et des textfields
        Label desc = new Label("Description : ");
        grid.add(desc, 0, 1);

        Label description = new Label(tache.getDescription());
        grid.add(description, 1, 1);

        Label ds = new Label("Sous-Tâche : ");
        grid.add(ds, 0, 2);
        VBox sousTaches = new VBox();
        for(Tache t : tache.getSousTache()){
            VBox vboxt = new VBox();

            // Ajout des texts de la HBox
            vboxt.getChildren().addAll(new Text(t.getTitre()),new Text(t.getDescription()));
            vboxt.addEventHandler(MouseEvent.MOUSE_CLICKED,new ControllerDetailsTache(modeleTache,(TacheMere) t));

            // Style de la HBox
            vboxt.setPadding(new Insets(20));
            vboxt.setPrefSize(200, 100);
            vboxt.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
            // ajout de la tâche dans la liste
            sousTaches.getChildren().add(vboxt);
        }
        grid.add(sousTaches, 0, 3);



        Button btnCreer = new Button("Créer Sous-Tâche");
        btnCreer.addEventHandler(MouseEvent.MOUSE_CLICKED,new ControllerAfficherFormulaire(this.modeleTache, this.modeleTache.getColonneSelectionner()));
        Button btnAnnuler = new Button("Retour");
        btnAnnuler.addEventHandler(ActionEvent.ACTION, new ControllerRetour(this.modeleTache));

        Button btnModifier = new Button("Modifier");
        btnModifier.addEventHandler(MouseEvent.MOUSE_CLICKED,new ControllerAfficherFormulaire(this.modeleTache, this.modeleTache.getColonneSelectionner()));

        Button btnArchiver = new Button("Archiver");
        Button btnGantt = new Button("Diagramme de Gantt");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnAnnuler, btnGantt, btnArchiver, btnModifier, btnCreer);
        grid.add(hbBtn, 1, 4);
        Scene sc= new Scene(grid, 300, 250);
        this.setScene(sc);
        this.show();
    }
}
