package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.Tache;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControllerDetailsTache implements EventHandler<MouseEvent> {

	private ModeleTache modeleTache;
	private Tache tacheSelectionner;

	public ControllerDetailsTache(ModeleTache modeleTache, Tache t) {
		this.modeleTache = modeleTache;
		this.tacheSelectionner = t;
	}

	/**
	 *
	 * @param mouseEvent
	 */
	@Override
	public void handle(MouseEvent mouseEvent) {
		// selection de la tâche
		this.modeleTache.setTacheSelectionner(this.tacheSelectionner);
	}
}
