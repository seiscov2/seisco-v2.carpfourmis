package carpfourmis.algo;

import seisco.algo.AlgorithmException;
import seisco.algo.Algorithme;
import seisco.probleme.Probleme;

public class AlgoFourCARP extends Algorithme {

	private Colonie colonie;

	public AlgoFourCARP(String nom, Probleme probleme, Colonie colonie) {
		super(nom, probleme);
		this.colonie = colonie;
	}

	public Colonie getColonie() {
		return colonie;
	}

	public void setColonie(Colonie colonie) {
		this.colonie = colonie;
	}

	@Override
	public void executer() throws AlgorithmException {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}