package TaskHub.Strategie;

import TaskHub.Controller.ControllerAfficherFormulaire;
import TaskHub.Controller.ControllerDetailsTache;
import TaskHub.Controller.ControllerNewColonne;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;


public class VisuelListe extends StrategieVisuel {
    /**
     * @param modele
     */


    @Override
    public void affichage(ModeleTache modele) {
        // Creation de la VBox principale et definition de la taille
        VBox vliste = new VBox(10);
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();

        // Creation du titre du tableau
        Label titreTab = new Label(modele.getTableau().getTitre());
        titreTab.getStyleClass().add("titreTableau");

        // Creation des colonnes
        for (Conteneur c:modele.getTableau().getColonnes()){
            // Creation de la VBox de la colonne
            VBox vboxColonne = new VBox(10);
            vboxColonne.getStyleClass().add("colonne");

            // Creation de l'image de la fleche pour dérouler les colonnes
            ImageView img=new ImageView("fleche-vers-le-bas.png");
            img.fitWidthProperty().set(30);
            img.fitHeightProperty().set(30);

            // Ajout du titre de la colonne
            Label titreColonne = new Label(c.getTitre());
            titreColonne.setGraphic(img);
            titreColonne.setPrefWidth(screenWidth-(screenWidth/8));
            titreColonne.setPrefHeight(40);
            titreColonne.getStyleClass().add("titreListe");
            VBox vboxTache=new VBox(5);
            vboxTache.getChildren().add(titreColonne);
            //titreColonne.getStyleClass().add("colonneTitre");


            // Mise en place du menu déroulant sur chaque colonne pour les tache
            titreColonne.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent actionEvent) {
                    // Si la colonne est déroulée
                    if (vboxTache.getChildren().size()==1){
                        // Ajout des taches de la colonne à la VBox
                        for (TacheMere t:c.getTaches()) {
                            // Création des texts
                            Label labelTache = new Label(t.getTitre()+ ": " + t.getDescription());
                            labelTache.setPrefWidth(screenWidth - (screenWidth / 8));
                            labelTache.setPrefHeight(40);
                            labelTache.getStyleClass().add("tacheListe");

                            // Ajout d'un controleur pour afficher les détails de la tache
                            labelTache.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerDetailsTache(modele, t));

                            // Ajout des texts de la HBox
                            vboxTache.getChildren().add(labelTache);

                        }
                        // Ajout du bouton pour enrouler le menu
                        ImageView img2=new ImageView("fleche-vers-le-haut.png");
                        img2.fitWidthProperty().set(30);
                        img2.fitHeightProperty().set(30);
                        titreColonne.setGraphic(img2);

                        // Ajout du bouton pour ajouter une tache
                        ImageView plus = new ImageView("plus.png");
                        plus.fitWidthProperty().set(30);
                        plus.fitHeightProperty().set(30);
                        Button button = new Button();
                        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerAfficherFormulaire(modele, modele.getTableau().getColonnes().indexOf(c)));
                        button.setPadding(new Insets(5));
                        button.setPrefSize(screenWidth - (screenWidth / 8), 30);
                        button.setGraphic(plus);
                        //button.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
                        //button.getStyleClass().add("buttonListe"); // Ajout de la classe de style pour le bouton
                        vboxTache.getChildren().add(button);

                        // Si la colonne est enroulée
                    }else{
                        // Suppression des taches de la colonne à la VBox
                        vboxTache.getChildren().setAll(titreColonne);
                        titreColonne.setGraphic(img);
                        }
                    }

            });
            // Ajout de la VBox de la colonne à la VBox principale
            vboxColonne.getChildren().add(vboxTache);
            vliste.getChildren().add(vboxColonne);
        }
        Button newColonne = new Button("Nouvelle Colonne");
        newColonne.getStyleClass().add("buttonColonne");
        newColonne.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerNewColonne(modele));
        newColonne.setFont(Font.font("Arial Black", FontWeight.BOLD, 15));
        newColonne.setPrefSize(200, 30);
        vliste.getChildren().add(newColonne);
        vliste.setAlignment(Pos.TOP_CENTER);
        VBox vFinal=new VBox(10);
        vFinal.getChildren().addAll(titreTab,vliste);

        this.getChildren().setAll(vFinal);
    }


    /**
    @Override
    public void affichage(ModeleTache modele) {
        // Affichage en liste //
        VBox vbox = new VBox(10); // Réduire l'espace entre les conteneurs

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Activer la barre de défilement vertical
        scrollPane.setStyle("-fx-background-color: transparent;"); // Rendre le fond transparent

        // Récupérer la taille de l'écran
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        // Définir la taille maximale de la ScrollPane sur la moitié de la hauteur de l'écran
      //  scrollPane.setMaxHeight(screenHeight/1.5);

        for (Conteneur colonne : modele.getTableau().getColonnes()) {
            VBox vboxColonne = new VBox(10); // Ajout de l'espace entre les boîtes de tâches
            vboxColonne.getStyleClass().add("colonne");

            // Ajout du titre de la colonne
            Text titreColonne = new Text(colonne.getTitre());
            titreColonne.getStyleClass().add("colonneTitre");
            vboxColonne.getChildren().add(titreColonne);

            // Ajout des tâches de la colonne à la VBox
            for (TacheMere tache : colonne.getTaches()) {
                VBox vboxTache = createVisuTache(tache, modele);
                vboxColonne.getChildren().add(vboxTache);
            }

            vboxColonne.setAlignment(Pos.TOP_CENTER);

            Button button = new Button("Ajouter");
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerAfficherFormulaire(modele, modele.getTableau().getColonnes().indexOf(colonne)));
            button.setPadding(new Insets(5));
            button.setPrefSize(100, 20);
            button.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            button.getStyleClass().add("button"); // Ajout de la classe de style pour le bouton

            vboxColonne.getChildren().add(button);

            vbox.getChildren().add(vboxColonne);
        }

       this.getChildren().setAll(scrollPane);
    }
*/

}
