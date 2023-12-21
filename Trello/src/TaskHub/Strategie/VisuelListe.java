package TaskHub.Strategie;

import TaskHub.Controller.ControllerAfficherFormulaire;
import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class VisuelListe extends StrategieVisuel {
    @Override
    public void affichage(ModeleTache modele) {
        // Affichage en liste //
        VBox vbox = new VBox(10); // Réduire l'espace entre les conteneurs

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);

        for (Conteneur colonne : modele.getTableau().getColonnes()) {
            VBox vboxColonne = new VBox();

            // Ajout du titre de la colonne
            Text titreColonne = new Text(colonne.getTitre());
            titreColonne.setFont(Font.font("Arial", FontWeight.BOLD, 14));
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

            vboxColonne.getChildren().add(button);

            vbox.getChildren().add(vboxColonne);
        }

        this.getChildren().setAll(scrollPane);
    }
}
