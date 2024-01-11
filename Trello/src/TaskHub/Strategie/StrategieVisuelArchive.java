package TaskHub.Strategie;

import TaskHub.Controller.ControllerAccesArchive;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Tableau;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StrategieVisuelArchive extends StrategieVisuel{
    @Override
    public void affichage(ModeleTache modele) {
        // Création du titre du tableau
        Label titreTab = new Label("Archive");
        titreTab.getStyleClass().add("titreTableau");

        // Création de la structure de la fenêtre principale
        HBox hboxColonnes = new HBox(50);
        hboxColonnes.getChildren().setAll();

        // Création de la colonne
        // HBox contenant les tâches
        VBox vboxColonne = new VBox(10);
        // Ajout des tâches de la colonne à la VBox
        for (Tableau tab : modele.getArchivage().getArchiveTache().keySet()) {
            for(TacheMere tache : modele.getArchivage().getArchiveTache().get(tab).keySet()){

                // Création de la VBox contenant la tâche
                VBox vboxTache = createVisuTache(tache, modele);


                // Style de la VBox
                vboxTache.setPrefHeight(20);
                vboxTache.setPrefWidth(250);

                // Ajout de la tâche dans la liste
                vboxColonne.getChildren().add(vboxTache);
                //vbox.fillWidthProperty().set(false);

            }
        }
        // Creation des bouttons en bas
        HBox hboxBas = new HBox(50);
        hboxBas.setAlignment(Pos.BOTTOM_RIGHT);

        Button archive=new Button("Retour");
        archive.setOnAction(new ControllerAccesArchive(modele));
        archive.getStyleClass().add("buttonTableau");
        hboxBas.getChildren().add(archive);

        // Style de la VBox de la colonne
        vboxColonne.setAlignment(Pos.TOP_CENTER);
        vboxColonne.getStyleClass().add("colonne");

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

        vbox.getChildren().addAll(titreOutil,titreTab, vboxColonne, hboxBas);

        this.getChildren().clear();
        this.getChildren().addAll(vbox);

    }

    /**
     * Méthode qui va permettre de définir le visuel de l'archive
     */

}
