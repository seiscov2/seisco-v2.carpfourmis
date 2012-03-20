package carpfourmis.algo;

import carp.ProblemeCARP;
import jade.content.Concept;
import seisco.util.graphe.Arc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Colonie implements Concept {

	List<Fourmi> colonie;
    Map<Arc, Float> pheromones;
	String nom;

	public Colonie() {
		super();
		nom = new String();
		colonie = new ArrayList<>();
        pheromones = new HashMap<>();
	}

	public Colonie(List<Fourmi> colonie) {
		super();
		this.colonie = colonie;
	}

	public Colonie(String nom) {
		super();
		this.nom = nom;
	}

	public Colonie(List<Fourmi> colonie, String nom) {
		super();
		this.colonie = colonie;
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Fourmi> getColonie() {
		return colonie;
	}

	public void setColonie(List<Fourmi> colonie) {
		this.colonie = colonie;
	}

	public int getColonieSize() {
		return colonie.size();
	}

	public boolean ajouterFourmi(Fourmi newFourmi) {
		for (Fourmi f : colonie)
			if (f.equals(newFourmi))
				return false;

		return colonie.add(newFourmi);
	}

	public boolean retirerFourmi(Fourmi f) {
		return colonie.remove(f);
	}

	public List<Fourmi> genererColonie(int nb, ProblemeCARP p) {
		List<Fourmi> newColonie = new ArrayList<>();
		int i = 0;

		if (colonie.isEmpty()) {
			/*
			 * On crée la première fourmi avec toutes les tâches du problème
			 */
			Fourmi fourmiOne = new Fourmi();

			for (Arc t : ProblemeCARP.getGraphe().getArcs())
				fourmiOne.ajouterTache(t);

			this.ajouterFourmi(fourmiOne);

			/*
			 * On splitte toutes les tâches de la fourmi pour faire une tournée
			 * viable et ainsi récupérer la fitness
			 */
			float fitness = p.fonctionObjectif(fourmiOne);

			fourmiOne.setFitness(fitness);
			i++;
		}

		for (; i < nb; i++) {
			Fourmi lastFourmi = colonie.get(colonie.size() - 1);
			Fourmi newFourmi = new Fourmi();

			for (Arc t : lastFourmi.getTaches())
				newFourmi.ajouterTache(t);

			do {
				int x = (new Random()).nextInt(ProblemeCARP.getGraphe().getArcs().size() - 1);
				int y = (new Random()).nextInt(ProblemeCARP.getGraphe().getArcs().size() - 1);
				Arc t1 = newFourmi.getTaches().get(x);
				Arc t2 = newFourmi.getTaches().get(y);

				newFourmi.getTaches().set(x, t2);
				newFourmi.getTaches().set(y, t1);
			} while (!this.ajouterFourmi(newFourmi));

			float fitness = p.fonctionObjectif(newFourmi);

			newFourmi.setFitness(fitness);
			newColonie.add(newFourmi);
		}

		return newColonie;
	}

	// TRI A BULLE (FITNESS DECROISSANT)
	public void trier() {
		int longueur = colonie.size();
		boolean inversion;

		do {
			inversion = false;

			for (int i = 0; i < longueur - 1; i++)
				if (colonie.get(i).getFitness() < colonie.get(i + 1).getFitness()) {
					Fourmi f = colonie.get(i + 1);

					colonie.set(i + 1, colonie.get(i));
					colonie.set(i, f);
					inversion = true;
				}
		} while (inversion);
	}

	public String toString() {
		String resultat = "Colonie " + nom + "\n";
		int index = 1;

		for (Fourmi f : colonie)
			resultat += index + ") " + f.toString();

		return resultat;
	}
}
