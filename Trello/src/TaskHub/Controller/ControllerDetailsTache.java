package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Controller qui va permettre d'afficher les détails d'une tâche
 */
public class ControllerDetailsTache implements EventHandler<MouseEvent> {

	/**
	 * Modele que le controller va appeler
	 */
	private ModeleTache modeleTache;
	/**
	 * TacheMere tacheSelectionner Contient la tâche sélectionnée
	 */
	private TacheMere tacheSelectionner;

	/**
	 * Constructeur de la classe ControllerDetailsTache
	 *
	 * @param modeleTache Modele que le controller va appeler
	 * @param t Contient la tâche sélectionnée
	 */
	public ControllerDetailsTache(ModeleTache modeleTache, TacheMere t) {
		this.modeleTache = modeleTache;
		this.tacheSelectionner = t;
	}

	/**
	 * Méthode qui va permettre de définir la tâche sélectionnée
	 *
	 * @param mouseEvent
	 */
	@Override
	public void handle(MouseEvent mouseEvent) {
		// selection de la tâche
		this.modeleTache.setTacheSelectionner(this.tacheSelectionner);
	}
}
