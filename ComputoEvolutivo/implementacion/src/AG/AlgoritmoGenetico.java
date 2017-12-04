import gaframework.*;
import org.moeaframework.problem.tsplib.*;
import java.util.*;

public class AlgoritmoGenetico implements GeneticAlgorithm<G,P>{
	private Breeder<G,P> breeder;
	private ObjectiveFunction<G,P> funcionO;
    private SelectionOp<G,P> seleccion;
    private CrossoverOp<G> cruza;
    private MutationOp<G> mutacion;
    private TerminationCondition<G,P> terminacion;

    private final LinkedList<P> ciudades;
    private final int tamanioPob;
    private boolean cont;

    public AlgoritmoGenetico(Codification<G,P> cod, Corrector<G> cor, CrossoverOp<G> cru,
    						MutationOp<G> mut, SelectionOp<G,P> sel, FitnessFunction<P> fFun,
							ObjectiveFunction<G,P> oFun, TerminationCondition<G,P> ter, 
							LinkedList<P> cities, int popSize){

    	breeder = new Breeder<G,P>(cod, cor, fFun);
    	funcionO = ofun;
    	seleccion = sel;
    	cruza = cru;
    	mutacion = mut;
    	terminacion = ter;
    	ciudades = cities;
    	tamanioPob = popSize;
    	cont = true;
    }

    public Population<G,P> iteration(Population<G,P> current){

    	// Creamos una población vacia cuya generación es la siguiente a la actual
    	Population<G,P> sig = new Population<>(current.getGeneration() + 1); 

    	// Evaluamos la población actual
    	funcionO.evaluate(current);

    	// Elitismo: Se pasa el mejor individuo de la generación actual a la siguiente
    	System.out.println(current.getBestIndividual().toString());

    	return null;
    }

    // Obtener el mejor individuo de la poblacion p
    /*private Individual<G,P> mejorIndividuo(Population<G,P> p){
    	Individual<G,P> mejor =
    }*/

}