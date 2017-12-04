import gaframework.*;
import java.util.List;
import java.util.Random;
import java.util.LinkedList;

public class Ruleta<G,P> implements SelectionOp<G,P>{

	int numSelec;

	public Ruleta(int numSel){
		numSelec = numSel;
	}

	public List<Individual<G,P>> select(Population<G,P> p){
		double[] fit = new double[p.size()];
		fit[0] = p.getIndividual(0).getFitness();
		for (int i=1; i < p.size(); i++){
			fit[i] = fit[i-1] + p.getIndividual(i).getFitness();
		}
		Random rdn = new Random(System.currentTimeMillis());
		LinkedList<Individual<G,P>> seleccion = new LinkedList<>();
		for(int i=0; i<numSelec; i++){
			double ex = rdn.nextDouble() * fit[fit.length-1];
			int j=0;
			while (j<fit.length-1 && fit[j] <= ex){
				j++;
			}
			seleccion.add(p.getIndividual(j));
		}
		return seleccion;
	}
}