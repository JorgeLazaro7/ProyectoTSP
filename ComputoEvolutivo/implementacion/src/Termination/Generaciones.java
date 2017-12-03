import gaframework.*;

/**
* @author Jorge Alberto
*
*/

public class Generaciones implements TerminationCondition<Integer,Integer>{

	private int generaciones;

	public Generaciones(int numero){
		generaciones = numero;
	}

	public boolean conditionReached(Population<Integer, Integer> p){
		return p.getGeneration() >= generaciones;
	}

}