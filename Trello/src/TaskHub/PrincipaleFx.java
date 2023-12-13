package TaskHub;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import TaskHub.Tache.Tableau;
import TaskHub.Vue.VueConteneurs;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PrincipaleFx extends Application {
    @Override
    public void start(Stage stage){

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

        ModeleTache modele = new ModeleTache();

        VueConteneurs tableau = new VueConteneurs(modele);

        Tableau tab = new Tableau("Tableau 1");
        modele.setTableau(tab);
        Conteneur cont = new Conteneur("Liste 1");
        cont.ajouterTache(new TacheMere("Tache 1", "Description 1"));
        cont.ajouterTache(new TacheMere("Tache 2", "Description 2"));
        cont.ajouterTache(new TacheMere("Tache 3", "Description 3"));
        tab.ajouterConteneur(cont);
        Conteneur cont2 = new Conteneur("Liste 2");
        cont2.ajouterTache(new TacheMere("Tache 4", "Description 4"));
        cont2.ajouterTache(new TacheMere("Tache 5", "Description 5"));
        tab.ajouterConteneur(cont2);
        ((VueConteneurs) tableau).actualiser(modele);
        // Ajout de tous les éléments à la VBox principale
        vbox.getChildren().addAll(titrePrin, tableau);

        // Mise en plein écran de la scène
        stage.setScene(new Scene(vbox, 1080, 720));
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
