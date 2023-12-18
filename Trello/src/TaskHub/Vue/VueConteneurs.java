package TaskHub.Vue;

import TaskHub.Controller.ControllerAfficherFormulaire;
import TaskHub.Controller.ControllerDetailsTache;
import TaskHub.Modele.ModeleTache;
import TaskHub.Modele.Sujet;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import TaskHub.Tache.Conteneur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Objects;

public class VueConteneurs extends VBox implements Observateur {
    public VueConteneurs() {
        super();
    }
    @Override
    public void actualiser(Sujet o) {
        ModeleTache modele = (ModeleTache) o;

        if(modele.getChangement()!=null && !modele.getChangement().getAction().isEmpty()){
            switch(modele.getChangement().getAction()){
                case "ajout":
                    // Création de la structure de la fenêtre principale
                    Tache tache = modele.getTableau().getConteneurs().get(modele.getChangement().getId_colonne()).getTaches().get(modele.getChangement().getId_tache());
                    VBox vboxt = new VBox();

                    // Ajout des texts de la HBox
                    vboxt.getChildren().addAll(new Text(tache.getTitre()),new Text(tache.getDescription()));

                    // Style de la HBox
                    vboxt.setPadding(new Insets(20));
                    vboxt.setPrefSize(200, 100);
                    vboxt.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));

                    // Ajoute la tache a la vue a la colonne correspondante
                    VBox conteneur = ((VBox) ((HBox) this.getChildren().get(1)).getChildren().get(modele.getChangement().getId_colonne()));
                    Button button = (Button) conteneur.getChildren().get(conteneur.getChildren().size()-1);
                    conteneur.getChildren().set(conteneur.getChildren().size()-1,vboxt);
                    conteneur.getChildren().add(button);
                    break;

            }
            modele.getChangement().setAction("");
        }else{
            HBox hbox = new HBox(50);
            hbox.getChildren().setAll();
            for(Conteneur colonne : modele.getTableau().getConteneurs()){
                // HBox contenant les tâches
                VBox vbox = new VBox();
                // Ajout des texts de la HBox
                vbox.getChildren().add(new Text(colonne.getTitre()));
                // on ajoute les tâches de la colonne à la VBox
                for (TacheMere tache : colonne.getTaches()) {
                    VBox vboxt = new VBox();

                    // Ajout des texts de la HBox
                    vboxt.getChildren().addAll(new Text(tache.getTitre()),new Text(tache.getDescription()));
                    vboxt.addEventHandler(MouseEvent.MOUSE_CLICKED,new ControllerDetailsTache(modele,tache));

                    // Style de la HBox
                    vboxt.setPadding(new Insets(20));
                    vboxt.setPrefSize(200, 100);
                    vboxt.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    // ajout de la tâche dans la liste
                    vbox.getChildren().add(vboxt);
                }
                vbox.setAlignment(Pos.TOP_CENTER);
                Button button = new Button("Ajouter une tâche");
                button.addEventHandler(MouseEvent.MOUSE_CLICKED,new ControllerAfficherFormulaire(modele,modele.getTableau().getConteneurs().indexOf(colonne)));
                button.setPadding(new Insets(10));
                button.setPrefSize(200, 30);
                button.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));
                vbox.getChildren().add(button);
                vbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                hbox.getChildren().add(vbox);
            }
            this.getChildren().setAll(new Text(modele.getTableau().getTitre()),hbox);

        }


    }
}
