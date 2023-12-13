package TaskHub;

import TaskHub.Modele.ModeleTache;
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


        // Création du tableau contenant les listes de tâches
        VBox tableau = new VBox();
        Text titreTableau = new Text("nom du tableau");
        titreTableau.setFont(Font.font("Arial Black", FontWeight.BLACK, 30));
        Button voirTableau = new Button("Voir les tableaux");
        voirTableau.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));
        voirTableau.setPrefSize(200, 30);
        voirTableau.setAlignment(Pos.TOP_LEFT);
        tableau.getChildren().add(voirTableau);

        // Création du bouton d'ajout de tâche et son placement
        Button ajouterListe = new Button("Ajouter une liste");
        ajouterListe.setPadding(new Insets(10));
        ajouterListe.setPrefSize(200, 30);
        ajouterListe.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));

        // Création des conteneurs pour les listes de tâches
        VBox liste1 = new VBox(30);
        VBox liste2 = new VBox(30);

        // Création des conteneurs pour les tâches et leurs titres
        VBox tache1 = new VBox(10);
        VBox tache2 = new VBox(10);
        VBox tache3 = new VBox(10);

        // Définition des titres des listes et des tâches
        Text titreliste1 = new Text("Liste 1");
        titreliste1.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));
        Text titreliste2 = new Text("Liste 2");
        titreliste2.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));
        Text titretache1 = new Text("Tache 1");
        Text voirplustache1 = new Text("Voir plus...");
        voirplustache1.setUnderline(true);
        Text titretache2 = new Text("Tache 2");
        Text voirplustache2 = new Text("Voir plus...");
        voirplustache2.setUnderline(true);
        Text titretache3 = new Text("Tache 3");
        Text voirplustache3 = new Text("Voir plus...");
        voirplustache3.setUnderline(true);

        // Mise en forme des tâches et listes avec bordures, marges et alignements
        tache1.setPadding(new Insets(20));
        tache1.setPrefSize(200, 100);
        tache3.setPadding(new Insets(20));
        tache3.setPrefSize(200, 100);
        tache1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        tache3.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        liste1.setPadding(new Insets(50));
        liste1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        liste1.setAlignment(Pos.TOP_CENTER);

        tache2.setPadding(new Insets(20));
        tache2.setPrefSize(200, 100);
        tache2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        liste2.setPadding(new Insets(50));
        liste2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        liste2.setAlignment(Pos.TOP_CENTER);

        // Création du bouton d'ajout de tâche liste 2
        Button ajoutertachelst1 = new Button("Ajouter une tâche");
        ajoutertachelst1.setPadding(new Insets(10));
        ajoutertachelst1.setPrefSize(200, 30);
        ajoutertachelst1.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));

        Button ajoutertachelst2 = new Button("Ajouter une tâche");
        ajoutertachelst2.setPadding(new Insets(10));
        ajoutertachelst2.setPrefSize(200, 30);
        ajoutertachelst2.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));

        // Ajout des titres et des tâches aux listes correspondantes
        tache1.getChildren().addAll(titretache1, voirplustache1);
        tache3.getChildren().addAll(titretache3, voirplustache3);
        tache2.getChildren().addAll(titretache2, voirplustache2);
        liste1.getChildren().addAll(titreliste1, tache1, tache3, ajoutertachelst1);
        liste2.getChildren().addAll(titreliste2, tache2, ajoutertachelst2);




        // Ajout des listes au tableau
        HBox listes = new HBox(50);
        listes.getChildren().addAll(liste1, liste2, ajouterListe);
        tableau.getChildren().addAll(titreTableau, listes);


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
