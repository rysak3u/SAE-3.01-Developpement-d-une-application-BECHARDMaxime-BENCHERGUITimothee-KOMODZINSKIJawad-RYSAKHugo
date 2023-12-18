package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.Tache;
import TaskHub.Tache.Composite.TacheMere;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControllerDetailsTache implements EventHandler<MouseEvent> {

	private ModeleTache modeleTache;
	private TacheMere tacheSelectionner;

	public ControllerDetailsTache(ModeleTache modeleTache, TacheMere t) {
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
