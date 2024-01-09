package TaskHub.Strategie;

import TaskHub.Controller.ControllerAfficherFormulaire;
import TaskHub.Controller.ControllerNewColonne;
import TaskHub.Exception.TacheNomVideException;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Vue.VueListeTableau;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
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

            Label titreTab = new Label(modele.getTableau().getTitre());
            titreTab.getStyleClass().add("titreTableau");
            // Création de la structure de la fenêtre principale
            HBox hbox = new HBox(50);
            hbox.getChildren().setAll();

            for (Conteneur colonne : modele.getTableau().getColonnes()) {
                // HBox contenant les tâches
                VBox vbox = new VBox(10);

                vbox.setOnDragOver(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* data is dragged over the target */
                        /* accept it only if it is not dragged from the same node
                         * and if it has a string data */
                        if (event.getGestureSource() != vbox &&
                                event.getDragboard().hasString()) {
                            /* allow for both copying and moving, whatever user chooses */
                            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        }

                        event.consume();
                    }
                });

                vbox.setOnDragEntered(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* the drag-and-drop gesture entered the target */
                        /* show to the user that it is an actual gesture target */
                        if (event.getGestureSource() != vbox && event.getDragboard().hasString()) {
                            vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                        }

                        event.consume();
                    }
                });

                vbox.setOnDragExited(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* mouse moved away, remove the graphical cues */
                        vbox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

                        event.consume();
                    }
                });

                vbox.setOnDragDropped(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* data dropped */
                        /* if there is a string data on dragboard, read it and use it */
                        Dragboard db = event.getDragboard();
                        boolean success = false;
                        if (event.getTransferMode() == TransferMode.MOVE) {
                            colonne.ajouterTache(modele.getTacheDrag());
                            modele.notifierObservateur();
                            success = true;
                        }
                        /* let the source know whether the string was successfully
                         * transferred and used */
                        event.setDropCompleted(success);

                        event.consume();
                    }
                });

                // Ajout des texts de la HBox
                Text titre = new Text(colonne.getTitre());
                titre.getStyleClass().add("colonneTitre");
                vbox.getChildren().add(titre);
                // on ajoute les tâches de la colonne à la VBox
                for (TacheMere tache : colonne.getTaches()) {
                    VBox vboxt = createVisuTache(tache, modele);
                    vboxt.setOnDragDetected(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent event) {
                            /* drag was detected, start a drag-and-drop gesture*/
                            /* allow any transfer mode */
                            Dragboard db = vboxt.startDragAndDrop(TransferMode.ANY);

                            /* Put a string on a dragboard */
                            ClipboardContent content = new ClipboardContent();
                            content.putString(tache.toString());
                            db.setContent(content);

                            modele.setTacheDrag(tache);

                            event.consume();
                        }
                    });
                    vboxt.setOnDragDone(new EventHandler<DragEvent>() {
                        public void handle(DragEvent event) {
                            /* the drag and drop gesture ended */
                            /* if the data was successfully moved, clear it */
                            if (event.getTransferMode() == TransferMode.MOVE) {
                                colonne.getTaches().remove(tache);
                            }
                            modele.notifierObservateur();
                            event.consume();
                        }
                    });
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
            newColonne.setFont(Font.font("Arial Black", FontWeight.BOLD, 15));
            //newColonne.setPadding(new Insets(10));
            newColonne.setPrefSize(200, 30);
            vbox.getChildren().add(newColonne);
            hbox.getChildren().add(vbox);

            VBox  vBox= new VBox(10);
            vBox.getChildren().add(titreTab);
            vBox.getChildren().add(hbox);
            this.getChildren().setAll(vBox);
        }
    }
}
