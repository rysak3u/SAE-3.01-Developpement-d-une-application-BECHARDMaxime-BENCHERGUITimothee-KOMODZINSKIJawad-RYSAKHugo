package TaskHub.Vue;

import TaskHub.Controller.ControllerManipTache;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VueFormulaire extends Stage implements Observateur{
    private ModeleTache modeleTache;
    public VueFormulaire(ModeleTache modeleTache){
        super();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Création de Tâche");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        //Création des labels et des textfields
        Label name = new Label("Nom de La Tache:");
        grid.add(name, 0, 1);

        TextField nameTextField = new TextField("New Tâche");
        grid.add(nameTextField, 1, 1);

        Label ds = new Label("Description:");
        grid.add(ds, 0, 2);

        TextField tfDesc = new TextField();
        grid.add(tfDesc, 1, 2);
        ControllerManipTache controllerCréerTache = new ControllerManipTache(modeleTache, nameTextField, tfDesc);
        Button btnCreer = new Button("Créer Tâche");
        Button btnAnnuler = new Button("Annuler");
        btnCreer.addEventHandler(MouseEvent.MOUSE_CLICKED,controllerCréerTache);
        btnAnnuler.addEventHandler(MouseEvent.MOUSE_CLICKED,controllerCréerTache);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnCreer);
        hbBtn.getChildren().add(btnAnnuler);
        grid.add(hbBtn, 1, 4);
        Scene sc= new Scene(grid, 300, 250);
        sc.getStylesheets().add("styleFormulaire.css");
        this.setScene(sc);
        this.setFullScreen(true);
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
