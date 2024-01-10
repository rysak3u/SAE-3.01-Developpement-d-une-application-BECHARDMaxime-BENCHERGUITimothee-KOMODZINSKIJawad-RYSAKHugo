package TaskHub.Vue;

import TaskHub.Controller.ControllerManip;
import TaskHub.Modele.ModeleTache;
import TaskHub.Modele.Sujet;
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

public class VueFormulaireTableau extends Stage implements Observateur {
    /**
     * Constructeur de VueFormulaireTableau
     * @param modeleTache modèle pour lequelle la vue va se baser
     */
    public VueFormulaireTableau(ModeleTache modeleTache) {
        // Création de la structure de la fenêtre principale
        super();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("formulaire"); // Appliquer le style CSS

        // Création du titre
        Text scenetitle = new Text("Création de Tableau");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.getStyleClass().add("titre-formulaire"); // Appliquer le style CSS
        grid.add(scenetitle, 0, 0, 2, 1);

        // Création des labels et des textfields
        Label name = new Label("Nom du tableau :");
        name.getStyleClass().add("label-formulaire");
        grid.add(name, 0, 1);

        // Création du textfield
        TextField nameTextField = new TextField("New Tableau");
        nameTextField.getStyleClass().add("textfield-formulaire");
        grid.add(nameTextField, 1, 1);

        // Création et style des boutons
        ControllerManip controllerCreerTache = new ControllerManip(modeleTache, nameTextField);
        Button btnCreer = new Button("Créer Tableau");
        btnCreer.getStyleClass().add("button-formulaire");
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.getStyleClass().add("button-formulaire");
        btnCreer.addEventHandler(MouseEvent.MOUSE_CLICKED, controllerCreerTache);
        btnAnnuler.addEventHandler(MouseEvent.MOUSE_CLICKED, controllerCreerTache);

        // Ajout des boutons dans une HBox
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnCreer, btnAnnuler);
        hbBtn.getStyleClass().add("hbBtn-formulaire");
        grid.add(hbBtn, 1, 4);

        // Création de la scène
        Scene sc = new Scene(grid, 400, 500);
        sc.setFill(Color.web("#9c8ae2"));
        sc.getStylesheets().add("styleFormulaire.css"); // Ajouter le fichier CSS
        this.setScene(sc);
        this.setFullScreen(false);
        this.setWidth(400);
        this.setHeight(500);
    }

    /**
     * Méthode pour actualiser la vue
     * @param s le sujet
     */
    @Override
    public void actualiser(Sujet s) {
        if (((ModeleTache) s).getFormulaire() && ((ModeleTache) s).getForm()==3) {
            this.show();
        } else {
            this.hide();
        }
    }
}
