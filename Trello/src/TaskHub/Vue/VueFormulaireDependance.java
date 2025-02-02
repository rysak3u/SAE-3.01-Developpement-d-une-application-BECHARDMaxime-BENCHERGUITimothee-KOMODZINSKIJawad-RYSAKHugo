package TaskHub.Vue;

import TaskHub.Controller.ControllerManip;
import TaskHub.Modele.ModeleTache;
import TaskHub.Modele.Sujet;
import TaskHub.Tache.Composite.Tache;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

import java.util.ArrayList;

public class VueFormulaireDependance extends Stage implements Observateur{
    // Attributs

    private ComboBox<String> cbDep;


    /**
     * Constructeur de VueFormulaireDependance
     * @param modeleTache modèle pour lequelle la vue va se baser
     */
    public VueFormulaireDependance(ModeleTache modeleTache) {
        // Création de la structure de la fenêtre principale
        super();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("formulaire"); // Appliquer le style CSS

        // Création du titre
        Text scenetitle = new Text("Ajouter Dépendance");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.getStyleClass().add("titre-formulaire"); // Appliquer le style CSS
        grid.add(scenetitle, 0, 0, 2, 1);

        // Création des labels et des textfields
        Label name = new Label("Tâche :");
        name.getStyleClass().add("label-formulaire");
        grid.add(name, 0, 1);

        // Création de la combobox
        this.cbDep = new ComboBox<>();
        this.cbDep.setPromptText("Selectionner une Tâche");
        cbDep.getStyleClass().add("textfield-formulaire");

        // Ajout de la combobox dans le grid et ajout d'un listener
        grid.add(cbDep, 1, 1);
        ControllerManip controllerCreerTache = new ControllerManip(modeleTache, cbDep);

        // Création et style des boutons
        Button btnCreer = new Button("Ajouter Dépendance");
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

        // Ajout du grid dans la scène
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
     * @param s modèle pour lequelle la vue va se baser
     */
    @Override
    public void actualiser(Sujet s) {
        // Si le formulaire est affiché
        if(((ModeleTache)s).getFormulaire() && ((ModeleTache)s).getForm()==4){
            this.show();
        }
        // Si le formulaire est caché
        else{
            if(((ModeleTache)s).getTacheSelectionner()!=null){
                this.setTitle("Ajouter Dépendance à "+((ModeleTache)s).getTacheSelectionner().getTitre());
                this.changerCb((ModeleTache)s);
            }

            this.hide();
        }
    }

    /**
     * Méthode pour changer le contenu de la combobox
     * @param modeleTache modèle pour lequelle la vue va se baser
     */
    public void changerCb( ModeleTache modeleTache){
        // On vide la combobox
        this.cbDep.getItems().clear();
        // On ajoute les tâches dans la combobox
        for (Tache t : modeleTache.getTaches()) {
            if (modeleTache.getTacheSelectionner() != null) {
                if (!t.getTitre().equals(modeleTache.getTacheSelectionner().getTitre())) {
                    // On ajoute les tâches qui ne sont pas la tâche sélectionnée
                    cbDep.getItems().add(t.getTitre());
                }
            }
        }
    }
}
