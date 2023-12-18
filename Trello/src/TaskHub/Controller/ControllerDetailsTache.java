package TaskHub.Controller;

import TaskHub.Modele.ModeleTache;
import TaskHub.Tache.Composite.Tache;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControllerDetailsTache implements EventHandler<MouseEvent> {

	private ModeleTache modeleTache;

	public ControllerDetailsTache(ModeleTache modeleTache) {
		this.modeleTache = modeleTache;
	}

	/**
	 *
	 * @param mouseEvent
	 */
	@Override
	public void handle(MouseEvent mouseEvent) {
		// selection de la tâche
		this.modeleTache.setTacheSelectionner((Tache) mouseEvent.getSource());
	}
}
