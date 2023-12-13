package TaskHub.Modele;

import TaskHub.Vue.Observateur;

public interface Sujet {

    public void enregisterObservateur(Observateur o);

    public void supprimerObservateur(Observateur o);

    public void notifierObservateur();
}

