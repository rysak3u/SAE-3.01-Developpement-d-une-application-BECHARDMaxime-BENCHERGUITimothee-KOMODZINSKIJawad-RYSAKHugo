package TaskHub.Strategie;

import TaskHub.Controller.*;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class StrategieVisuelBureau extends StrategieVisuel {
    /**
     * @param modele
     */
    @Override
    public void affichage(ModeleTache modele) {
        if (modele.getChangement() != null && !modele.getChangement().getAction().isEmpty()) {
            // Optimisation de l'affichage en fonction de l'action
            switch (modele.getChangement().getAction()) {
                // Cas de l'ajout d'une tache
                case "ajout":
                    // Création de la structure de la fenêtre principale
                    TacheMere tache = modele.getTableau().getColonnes().get(modele.getChangement().getId_colonne()).getTaches().get(modele.getChangement().getId_tache());
                    VBox vboxTache = createVisuTache(tache, modele);
                    VBox vboxTacheOriginal= (VBox) this.getChildren().get(0);

                    // Ajoute la tache a la vue a la colonne correspondante
                    VBox conteneur = ((VBox) ((HBox) vboxTacheOriginal.getChildren().get(1)).getChildren().get(modele.getChangement().getId_colonne()));
                    Button button = (Button) conteneur.getChildren().get(conteneur.getChildren().size() - 1);
                    conteneur.getChildren().set(conteneur.getChildren().size() - 1, vboxTache);
                    conteneur.getChildren().add(button);
                    break;

            }
            modele.getChangement().setAction("");
        } else {
            // Création du titre du tableau
            Label titreTab = new Label(modele.getTableau().getTitre());
            titreTab.getStyleClass().add("titreTableau");

            // Création de la structure de la fenêtre principale
            HBox hboxColonnes = new HBox(50);
            hboxColonnes.getChildren().setAll();

            // Création des colonnes
            for (Conteneur colonne : modele.getTableau().getColonnes()) {
                // HBox contenant les tâches
                VBox vboxColonne = new VBox(10);

                // Ajout des évènements de drag and drop sur la Cible (Colonne)
                vboxColonne.setOnDragOver(new ControllerDragOver(vboxColonne));
                vboxColonne.setOnDragEntered(new ControllerDragEntered(vboxColonne));
                vboxColonne.setOnDragExited(new ControllerDragExited(vboxColonne));
                vboxColonne.setOnDragDropped(new ControllerDragDropped(colonne, modele));

                // Ajout des texts de la HBox
                Text titre = new Text(colonne.getTitre());
                titre.getStyleClass().add("colonneTitre");
                vboxColonne.getChildren().add(titre);

                // Ajout des tâches de la colonne à la VBox
                for (TacheMere tache : colonne.getTaches()) {

                    // Création de la VBox contenant la tâche
                    VBox vboxTache = createVisuTache(tache, modele);

                    // Ajout des évènements de drag and drop sur la Cible (Tâche)
                    vboxTache.setOnDragDetected(new ControllerDragDetected(vboxTache, modele, tache));
                    vboxTache.setOnDragDone(new ControllerDragDone(colonne, tache, modele));

                    // Style de la VBox
                    vboxTache.setPrefHeight(20);
                    vboxTache.setPrefWidth(250);

                    // Ajout de la tâche dans la liste
                    vboxColonne.getChildren().add(vboxTache);
                    //vbox.fillWidthProperty().set(false);
                }

                // Style de la VBox de la colonne
                vboxColonne.setAlignment(Pos.TOP_CENTER);
                vboxColonne.getStyleClass().add("colonne");

                // Création du bouton d'ajout de tâche
                ImageView plus = new ImageView("plus.png");
                plus.fitHeightProperty().set(40);
                plus.fitWidthProperty().set(40);
                Button button = new Button();
                button.setGraphic(plus);
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerAfficherFormulaire(modele, modele.getTableau().getColonnes().indexOf(colonne)));
                button.setPadding(new Insets(10));
                button.setPrefSize(200, 30);
                button.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));

                // Ajout de la classe de style pour le bouton
                button.getStyleClass().add("button");
                vboxColonne.getChildren().add(button);
                hboxColonnes.getChildren().add(vboxColonne);
                hboxColonnes.fillHeightProperty().set(false);
            }
            // Création du bouton d'ajout de colonne
            VBox vboxNewColonne = new VBox(10);
            Button newColonne = new Button("Nouvelle Colonne");
            newColonne.getStyleClass().add("buttonColonne");
            newColonne.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerNewColonne(modele));
            newColonne.setFont(Font.font("Arial Black", FontWeight.BOLD, 15));
            //newColonne.setPadding(new Insets(10));
            newColonne.setPrefSize(200, 30);
            vboxNewColonne.getChildren().add(newColonne);
            hboxColonnes.getChildren().add(vboxNewColonne);

            // Creation de la VBox contenant le titre et les colonnes du tableau
            VBox vboxTableau= new VBox(10);
            vboxTableau.getChildren().add(titreTab);
            vboxTableau.getChildren().add(hboxColonnes);
            this.getChildren().setAll(vboxTableau);
        }
    }
}
