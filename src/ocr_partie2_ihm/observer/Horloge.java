package ocr_partie2_ihm.observer;

import java.util.ArrayList;
import java.util.Calendar;

public class Horloge implements Observable {
	// On r�cup�re l'instance d'un calendrier
	// Elle va nous permettre de r�cup�rer l'heure actuelle
	private Calendar cal;
	private String hour = "";
	// Notre collection d'observateurs
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();

	public void run() {
		while (true) {
			this.cal = Calendar.getInstance();
			this.hour = // Les heures
					this.cal.get(Calendar.HOUR_OF_DAY) + " : " + ( // Les minutes
					this.cal.get(Calendar.MINUTE) < 10 ? "0" + this.cal.get(Calendar.MINUTE)
							: this.cal.get(Calendar.MINUTE)) + " : " + ( // Les secondes
					(this.cal.get(Calendar.SECOND) < 10) ? "0" + this.cal.get(Calendar.SECOND)
							: this.cal.get(Calendar.SECOND));
			// On avertit les observateurs que l'heure a �t� mise � jour
			this.updateObservateur();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Ajoute un observateur � la liste
	public void addObservateur(Observateur obs) {
		this.listObservateur.add(obs);
	}

	// Retire tous les observateurs de la liste
	public void delObservateur() {
		this.listObservateur = new ArrayList<Observateur>();
	}

	// Avertit les observateurs que l'objet observable a chang�
	// et invoque la m�thode update() de chaque observateur
	public void updateObservateur() {
		for (Observateur obs : this.listObservateur)
			obs.update(this.hour);
	}
}