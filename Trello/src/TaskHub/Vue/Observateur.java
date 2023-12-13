package TaskHub.Vue;

import TaskHub.Modele.Sujet;


/**Classe Observateur**/
public interface Observateur {
    /**
     *
     * @param s modèle pour lequelle la vue va se baser
     */
    public void actualiser(Sujet s);
}
