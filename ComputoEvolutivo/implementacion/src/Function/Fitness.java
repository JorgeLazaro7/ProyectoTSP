package Function;
import gaframework.*;
import org.moeaframework.problem.tsplib.*;

/**
 * @author Jorge Alberto
 */
public class Fitness implements FitnessFunction<Integer>{


	DistanceTable table;

	public Fitness(DistanceTable dt){
		table = dt;
	}


	// Se regresa la distancia para usar la funcion objetivo linear ranking sugerida en el libro 
	public double evaluate(Phenotype<Integer> p){
		double fitness = distancia(p)
		/*double fitness = 0;
		double distance = distancia(p);
		fitness = 1/distance;
		*/
		return fitness;
	}

	public double distancia(Phenotype<Integer> p){
		double distance = 0;
		for (int i=0; i < p.size()-1; i++){ 
		// Calculamos la distancia total de la ruta, sumando las distancias entre 
		//las ciudades adyacentes en el fenotipo
			distance += table.getDistanceBetween(p.getAllele(i), p.getAllele(i+1)); 
		}
		//faltaba sumar la distancia entre el la ciudad final y la inicial para cerrar el ciclo
		distance += table.getDistanceBetween(p.getAllele(p.size()-1), p.getAllele(0));
		
		return distance;
	}
}