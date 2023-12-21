package TaskHub.Vue;

import TaskHub.Controller.ControllerManipTache;
import TaskHub.Modele.ModeleTache;
import TaskHub.Modele.Sujet;
import TaskHub.Strategie.StrategieVisuel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VueFormulaire extends Stage implements Observateur{
    private ModeleTache modeleTache;

    public VueFormulaire(ModeleTache modeleTache) {
        super();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #9c8ae2;"); // Fond violet, une nuance différente

        Text scenetitle = new Text("Création de Tâche");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setStyle("-fx-fill: white;"); // Style du titre (texte blanc)
        grid.add(scenetitle, 0, 0, 2, 1);

// Création des labels et des textfields
        Label name = new Label("Nom de La Tache:");
        name.setStyle("-fx-text-fill: white;"); // Style du label (texte blanc)
        grid.add(name, 0, 1);

        TextField nameTextField = new TextField("New Tâche");
        nameTextField.setStyle("-fx-background-color: #d8bfd8; -fx-text-fill: #4b0082;"); // Style du TextField
        grid.add(nameTextField, 1, 1);

        Label ds = new Label("Description:");
        ds.setStyle("-fx-text-fill: white;"); // Style du label (texte blanc)
        grid.add(ds, 0, 2);

        TextField tfDesc = new TextField();
        tfDesc.setStyle("-fx-background-color: #d8bfd8; -fx-text-fill: #4b0082;"); // Style du TextField
        grid.add(tfDesc, 1, 2);

        ControllerManipTache controllerCréerTache = new ControllerManipTache(modeleTache, nameTextField, tfDesc);
        Button btnCreer = new Button("Créer Tâche");
        btnCreer.setStyle("-fx-background-color: #ff69b4; -fx-text-fill: white;"); // Style du bouton
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.setStyle("-fx-background-color: #ff69b4; -fx-text-fill: white;"); // Style du bouton
        btnCreer.addEventHandler(MouseEvent.MOUSE_CLICKED, controllerCréerTache);
        btnAnnuler.addEventHandler(MouseEvent.MOUSE_CLICKED, controllerCréerTache);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnCreer, btnAnnuler);
        hbBtn.setStyle("-fx-padding: 10px;"); // Style de la boîte des boutons
        grid.add(hbBtn, 1, 4);

        Scene sc = new Scene(grid, 400, 500); // Ajustement de la taille initiale
        sc.setFill(Color.web("#9c8ae2")); // Fond violet, la même nuance que le fond du GridPane
        this.setScene(sc);


        this.setFullScreen(false);


        // Définir la taille initiale de la fenêtre
        this.setWidth(400);
        this.setHeight(500);

    }

    /**
     * @param s modèle pour lequelle la vue va se baser
     */
    @Override
    public void actualiser(Sujet s) {
        if(((ModeleTache)s).getFormulaire()){
            this.show();
        }
        else{
            this.hide();
        }
    }
}
