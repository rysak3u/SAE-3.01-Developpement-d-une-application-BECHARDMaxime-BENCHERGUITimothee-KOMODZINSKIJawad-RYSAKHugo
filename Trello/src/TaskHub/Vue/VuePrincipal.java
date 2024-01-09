package TaskHub.Vue;

import TaskHub.Controller.*;
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

import java.util.ArrayList;


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
        VBox vbox = new VBox(50);
        vbox.setPadding(new Insets(20));

        VBox titreOutil=new VBox(5);
        // Création du titre principal centré
        HBox titrePrin = new HBox();
        Text titrePrincipale = new Text("TaskHub".toUpperCase());
        titrePrincipale.setFont(Font.font("Arial Black", FontWeight.BLACK, 45));
        titrePrin.setAlignment(Pos.TOP_CENTER);
        titrePrin.getChildren().add(titrePrincipale);
        titreOutil.getChildren().add(titrePrin);
        HBox boxBouton = new HBox();
        // Création du ComboBox pour le choix de l'affichage
        ComboBox<String> choixAffichage = new ComboBox<String>();
        choixAffichage.setPromptText("Choix de l'affichage");
        choixAffichage.getItems().add("Affichage Bureau");
        choixAffichage.getItems().add("Affichage Liste");
        choixAffichage.setOnAction(new ControllerVuePrincipale(this));

        // Création de la ComboBox pour le choix du tableau
        VueListeTableau listeTableau = new VueListeTableau(this.modeleTache);
        listeTableau.setOnAction(new ControllerSwitchTableau(this.modeleTache));
        modeleTache.enregistrerObservateur(listeTableau);
        boxBouton.getChildren().add(listeTableau);

        //boxBouton.getChildren().addAll(choixAffichage, newColonne, newTableau);
        boxBouton.getChildren().addAll(choixAffichage);
        boxBouton.setAlignment(Pos.TOP_RIGHT);
        titreOutil.getChildren().add(boxBouton);
        this.modeleTache.ajouterTableau("Tableau 1");
        this.modeleTache.ajouterTableau("Tableau 2");
        try {
            Conteneur cont = new Conteneur("Liste 1",this.modeleTache);
            cont.ajouterTache(new TacheMere("Tache 1", "Description 1"));
            cont.ajouterTache(new TacheMere("Tache 2", "Description 2"));
            cont.ajouterTache(new TacheMere("Tache 3", "Description 3"));
            modeleTache.getTableaux().get(0).ajouterColonne(cont);
            Conteneur cont2 = new Conteneur("Liste 2",this.modeleTache);
            cont2.ajouterTache(new TacheMere("Tache 4", "Description 4"));
            cont2.ajouterTache(new TacheMere("Tache 5", "Description 5"));
            modeleTache.getTableaux().get(0).ajouterColonne(cont2);
            this.affichage.affichage(this.modeleTache);
        } catch (TacheNomVideException e) {
            e.printStackTrace();
        }

        HBox hboxBas = new HBox(50);
        hboxBas.setAlignment(Pos.BOTTOM_RIGHT);
        Button newTableau = new Button("Nouveau Tableau");

        newTableau.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerNewTableau(this.modeleTache));
        newTableau.setAlignment(Pos.BOTTOM_RIGHT);
        newTableau.getStyleClass().add("buttonTableau");
        hboxBas.getChildren().add(newTableau);
        Label titreTab = new Label(modeleTache.getTableau().getTitre());
        titreTab.getStyleClass().add("titreTableau");
        titreTab.setTextFill(Color.web("#ffffff"));
       // titreOutil.getChildren().add(titreTab);
        // Ajout de tous les éléments à la VBox principale
        vbox.getChildren().addAll(titreOutil, this.affichage,hboxBas);

        // Mise en plein écran de la scène
        this.scenePrincipale= new Scene(vbox, 300, 250);
        this.scenePrincipale.getStylesheets().add("styleVisuel.css");
        this.setScene(scenePrincipale);
        this.setFullScreen(true);
        modeleTache.notifierObservateur();
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
            this.setScene(this.scenePrincipale);
            this.affichage.affichage(this.modeleTache);
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

        GridPane grid = new GridPane();
        grid.getStyleClass().add("formulaire");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Détail de la tâche : " + tache.getTitre());
        scenetitle.getStyleClass().add("textfield-formulaire");  // Appliquer le style CSS
        grid.add(scenetitle, 0, 0, 2, 1);

        Label desc = new Label("Description : ");
        desc.getStyleClass().add("textfield-formulaire");  // Appliquer le style CSS
        grid.add(desc, 0, 1);

        Label description = new Label(tache.getDescription());
        description.getStyleClass().add("textfield-formulaire");  // Appliquer le style CSS
        grid.add(description, 1, 1);
        if(modeleTache.getDependance().getDependance(modeleTache.getTacheSelectionner())!=null) {
            Label dep = new Label("Dépendance : ");
            dep.getStyleClass().add("textfield-formulaire");  // Appliquer le style CSS
            grid.add(dep, 0, 2);

            Label dep2=new Label("");
            dep2.getStyleClass().add("textfield-formulaire");  // Appliquer le style CSS
            for (Tache t : modeleTache.getDependance().getDependance(modeleTache.getTacheSelectionner())) {
                dep2.setText(dep2.getText()+t.getTitre()+", ");


            }
            grid.add(dep2, 1, 2);
        }


        Label ds = new Label("Sous-Tâche : ");
        ds.getStyleClass().add("textfield-formulaire");  // Appliquer le style CSS
        grid.add(ds, 0, 3);

        VBox sousTaches = new VBox();

        for (Tache t : tache.getSousTache()) {
            VBox vboxt = new VBox();

            Text titre = new Text(t.getTitre());
            Text descriptiont = new Text(t.getDescription());
            titre.getStyleClass().add("textfield-formulaire");  // Appliquer le style CSS
            descriptiont.getStyleClass().add("textfield-formulaire");  // Appliquer le style CSS
            vboxt.getChildren().addAll(titre, descriptiont);

            vboxt.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerDetailsTache(modeleTache, (TacheMere) t));
            vboxt.getStyleClass().add("textfield-formulaire");  // Appliquer le style CSS

            sousTaches.getChildren().add(vboxt);
        }

        sousTaches.getStyleClass().add("textfield-formulaire");  // Appliquer le style CSS
        sousTaches.setSpacing(10);
        sousTaches.setAlignment(Pos.CENTER);
        grid.add(sousTaches, 0, 3);

        Button btnCreer = new Button("Créer Sous-Tâche");
        btnCreer.getStyleClass().add("button-formulaire");  // Appliquer le style CSS
        btnCreer.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerAfficherFormulaire(this.modeleTache, this.modeleTache.getColonneSelectionner()));

        Button btnAnnuler = new Button("Retour");
        btnAnnuler.getStyleClass().add("button-formulaire");  // Appliquer le style CSS
        btnAnnuler.addEventHandler(ActionEvent.ACTION, new ControllerRetour(this.modeleTache));

        Button btnModifier = new Button("Modifier");
        btnModifier.getStyleClass().add("button-formulaire");  // Appliquer le style CSS
        btnModifier.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerAfficherFormulaire(this.modeleTache, this.modeleTache.getColonneSelectionner()));

        Button btnArchiver = new Button("Archiver");
        btnArchiver.getStyleClass().add("button-formulaire");  // Appliquer le style CSS

        Button btnGantt = new Button("Ajouter Dépendance");
        btnGantt.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerAfficherFormulaireDependance(this.modeleTache));
        btnGantt.getStyleClass().add("button-formulaire");  // Appliquer le style CSS

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnAnnuler, btnGantt, btnArchiver, btnModifier, btnCreer);

        hbBtn.setSpacing(10);
        hbBtn.setAlignment(Pos.CENTER);
        grid.add(hbBtn, 1, 4);

        Scene sc = new Scene(grid);

        sc.getStylesheets().add("styleFormulaire.css");
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
        if(this.modeleTache.getChangement()!=null){
            this.modeleTache.getChangement().setAction("");
        }
        this.affichage=affichage;
        this.affichage.affichage(this.modeleTache);
        VBox nV=(VBox) this.scenePrincipale.getRoot();
        nV.getChildren().set(1, this.affichage);
    }
}
