package carpfourmis.algo;

import carp.SolutionCARP;
import java.util.ArrayList;
import java.util.List;
import seisco.util.graphe.Arc;

public class Fourmi extends SolutionCARP {

	private int maxDistance = 0;
	private float fitness = 0;
	private List<Arc> listeTabou;
	

	public Fourmi() {
		super();
		listeTabou = new ArrayList<>();
	}

	public Fourmi(int nbTaches) {
		super(nbTaches);
		listeTabou = new ArrayList<>();
	}

	public Fourmi(ArrayList<Arc> taches) {
		super(taches);
		listeTabou = new ArrayList<>();
	}

	public float getFitness() {
		return fitness;
	}

	public void setFitness(float fitness) {
		this.fitness = fitness;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}

	public List<Arc> getListeTabou() {
		return listeTabou;
	}

	public boolean ajouterTacheTabou(Arc t) {
		return listeTabou.add(t);
	}

	public void viderListeTabou() {
		listeTabou.clear();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Fourmi)
			return super.equals(obj);

		return false;
	}

	public Fourmi copy() throws CloneNotSupportedException {
		Fourmi f = (Fourmi) super.clone();

		f.fitness = this.fitness;

		return f;
	}

	@Override
	public String toString() {
		String resultat = "Fourmi - Fitness = " + fitness + "\n";

		resultat += super.toString();

		return resultat;
	}
}
