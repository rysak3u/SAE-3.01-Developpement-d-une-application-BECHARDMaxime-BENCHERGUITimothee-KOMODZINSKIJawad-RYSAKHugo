package TaskHub.Strategie;

import TaskHub.Controller.ControllerAfficherFormulaire;
import TaskHub.Controller.ControllerDetailsTache;
import TaskHub.Controller.ControllerNewColonne;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Vue.VueListeTableau;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
            switch (modele.getChangement().getAction()) {
                case "ajout":
                    // Création de la structure de la fenêtre principale
                    TacheMere tache = modele.getTableau().getColonnes().get(modele.getChangement().getId_colonne()).getTaches().get(modele.getChangement().getId_tache());
                    VBox vboxt = createVisuTache(tache, modele);

                    // Ajoute la tache a la vue a la colonne correspondante
                    VBox conteneur = ((VBox) ((HBox) this.getChildren().get(1)).getChildren().get(modele.getChangement().getId_colonne()));
                    Button button = (Button) conteneur.getChildren().get(conteneur.getChildren().size() - 1);
                    conteneur.getChildren().set(conteneur.getChildren().size() - 1, vboxt);
                    conteneur.getChildren().add(button);
                    break;

            }
            modele.getChangement().setAction("");
        } else {
            // Création de la structure de la fenêtre principale
            HBox hbox = new HBox(50);
            hbox.getChildren().setAll();

            for (Conteneur colonne : modele.getTableau().getColonnes()) {
                // HBox contenant les tâches
                VBox vbox = new VBox(10);
                // Ajout des texts de la HBox
                Text titre = new Text(colonne.getTitre());
                titre.getStyleClass().add("colonneTitre");
                vbox.getChildren().add(titre);
                // on ajoute les tâches de la colonne à la VBox
                for (TacheMere tache : colonne.getTaches()) {
                    VBox vboxt = createVisuTache(tache, modele);
                    vboxt.setPrefHeight(20);
                    vboxt.setPrefWidth(250);

                    // ajout de la tâche dans la liste
                    vbox.getChildren().add(vboxt);
                    //vbox.fillWidthProperty().set(false);
                }
                ImageView plus = new ImageView("plus.png");
                plus.fitHeightProperty().set(40);
                plus.fitWidthProperty().set(40);
                vbox.setAlignment(Pos.TOP_CENTER);
                vbox.getStyleClass().add("colonne");
                Button button = new Button();
                button.setGraphic(plus);
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerAfficherFormulaire(modele, modele.getTableau().getColonnes().indexOf(colonne)));
                button.setPadding(new Insets(10));
                button.setPrefSize(200, 30);
                button.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));
                button.getStyleClass().add("button"); // Ajout de la classe de style pour le bouton
                vbox.getChildren().add(button);
                hbox.getChildren().add(vbox);
                hbox.fillHeightProperty().set(false);
            }
            VBox vbox = new VBox(10);
            Button newColonne = new Button("Nouvelle Colonne");
            newColonne.getStyleClass().add("buttonColonne");
            newColonne.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControllerNewColonne(modele));
            //newColonne.setPadding(new Insets(10));
            newColonne.setPrefSize(200, 30);
            vbox.getChildren().add(newColonne);
            hbox.getChildren().add(vbox);

            this.getChildren().setAll(new Text(modele.getTableau().getTitre()),hbox);
        }
    }
}
