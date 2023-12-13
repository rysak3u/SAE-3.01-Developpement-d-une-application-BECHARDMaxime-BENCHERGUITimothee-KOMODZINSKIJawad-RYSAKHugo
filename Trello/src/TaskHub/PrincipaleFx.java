package TaskHub;

import TaskHub.Modele.ModeleTache;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PrincipaleFx extends Application {

     private Scene sceneFormulaireCréerTache;
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("TaskHub");
        ModeleTache modeleTache = new ModeleTache();
        BorderPane root = new BorderPane();
        Label créerTache = new Label("Création Tache");
        //créerTache.setAlignment(Pos.CENTER);
        root.getChildren().add(créerTache);
        BorderPane.setAlignment(créerTache,Pos.TOP_CENTER);
        //GridPane gr=new GridPane();
        sceneFormulaireCréerTache = new Scene(root,300, 250);
        stage.setScene(sceneFormulaireCréerTache);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
