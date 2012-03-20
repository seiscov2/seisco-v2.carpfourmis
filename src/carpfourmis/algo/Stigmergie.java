package carpfourmis.algo;

import seisco.algo.Operateur;

public class Stigmergie extends Operateur {

	@Override
	public Object[] operate(Object... os) {
		boolean allInstanceOfFourmi = true;

		for (Object o : os)
			allInstanceOfFourmi &= o instanceof Fourmi;

		if (allInstanceOfFourmi) {
			Fourmi[] fourmis = new Fourmi[os.length];

			for (int i = 0; i < fourmis.length; i++)
				fourmis[i] = (Fourmi) os[i];

			return operate(fourmis);
		}

		return null;
	}

	private Fourmi[] operate(Fourmi... fourmis) {
		int nbF = fourmis.length;
		int maxD = fourmis[0].getMaxDistance();
		Fourmi[] result = new Fourmi[nbF];
		
		int rang = 1;
		for (Fourmi f : fourmis) {
			/*
			 * Calcul de la quantité de phéromone à déposer
			 * en fonction de la qualité de la fourmi
			 * Etant donné qu'elles sont triées par ordre décroissant,
			 * les premières fourmis auront une odeur plus faible.
			 */
			float qtePheromones = ( ( maxD-1 ) / ( nbF-1 ) ) * rang + ( ( nbF-maxD ) / ( nbF-1 ) );
			qtePheromones *= f.getFitness();
			
			
			
			rang++;
		}

		return result;
	}
}
