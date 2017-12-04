import gaframework.*;
import org.moeaframework.problem.tsplib.*;
import java.util.*;

public class AlgoritmoGenetico<G,P> implements GeneticAlgorithm<G,P>{
	private Breeder<G,P> breeder;
	//private ObjectiveFunction<G,P> funcion;
    private SelectionOp<G,P> seleccion;
    private CrossoverOp<G> cruza;
    private MutationOp<G> mutacion;
    private TerminationCondition<G,P> terminacion;

    private final LinkedList<P> ciudades;
    private final int tamanioPob;
    //private boolean cont;

    public AlgoritmoGenetico(Codification<G,P> codificacion, FitnessFunction<P> fitness, 
                             SelectionOp<G,P> seleccion, CrossoverOp<G> cruza,
                             MutationOp<G> mutacion, TerminationCondition<G,P> terminacion, 
                             Corrector<G> corrector, LinkedList<P> ciudades, int popSize){

    	breeder = new Breeder<G,P>(codificacion, corrector, fitness);
    	this.seleccion = seleccion;
    	this.cruza = cruza;
    	this.mutacion = mutacion;
    	this.terminacion = terminacion;
    	this.ciudades = ciudades;
    	tamanioPob = popSize;
    	//cont = true;
    }

    public Population<G,P> iteration(Population<G,P> current){

    	// Creamos una población vacia cuya generación es la siguiente a la actual
    	Population<G,P> sig = new Population<>(current.getGeneration() + 1); 

    	// Evaluamos la población actual
    	//funcionO.evaluate(current);

    	// Elitismo: Se pasa el mejor individuo de la generación actual a la siguiente
        sig.addIndividual(current.getBestIndividual());
    	//System.out.println(current.getBestIndividual().toString() + "hola");

        while (sig.size() < tamanioPob){

            // Seleccionamos dos individuos y los clonamos para poder editarlos sin perderlos
            List<Individual<G,P>> seleccionPareja = seleccion.select(current);
            List<Individual<G,P>> seleccionList = new ArrayList<>();
            Individual<G,P> selec1 = breeder.newIndividual(seleccionPareja.get(0).getGenotype());
            seleccionList.add(selec1);
            Individual<G,P> selec2 = breeder.newIndividual(seleccionPareja.get(1).getGenotype());
            seleccionList.add(selec2);

            // Los cruzamos
            List<Genotype<G>> genotipos = new LinkedList<>();
            for (Individual<G,P> i : seleccionList)
                genotipos.add(i.getGenotype());
            List<Genotype<G>> cruzados = cruza.crossover(genotipos);

            // Los mutamos
            List<Genotype<G>> mutados = new LinkedList<>();
            for (Genotype<G> g : cruzados)
                mutados.add(mutacion.mutate(g));

            // Los añadimos a la nueva poblacion
            for (Genotype<G> g : mutados)
                sig.addIndividual(breeder.newIndividual(g));
        }

    	return sig;
    }

    public void run(){
        Population<G,P> p = poblacionAleatoria();
        while(!terminacion.conditionReached(p)){
            p = iteration(p);
            if (p.getGeneration()%500 == 0){
                System.out.println("Generación " + p.getGeneration());
                Individual<G,P>  ind= getMejorIndividuo(p);
                System.out.println("genotipo:" + ind.getGenotype());
                System.out.println("fenotipo:" + ind.getPhenotype());
                System.out.println("Destancia: " + Math.pow(ind.getFitness(), -1));
                System.out.println("Fitness: " + ind.getFitness());
            }
            
        }
    }

    private Population<G,P> poblacionAleatoria(){
        Population<G,P> p = new Population<>(1);
        Random rdn = new Random(System.currentTimeMillis());
        for(int i=0; i<tamanioPob; i++){
            LinkedList<P> cities = clonar();
            Phenotype<P> fenotipo = new Phenotype<>(ciudades.size());
            // El primer Allelo de todos los individuos siempre será el mismo, 
            // como es un ciclo, no importa por cual empezemos y esto nos acota 
            // un poco el espacio de búsqueda de la misma forma programé los 
            // operadores para que conserven siempre el primer allelo o gen
            fenotipo.setAllele(0, cities.remove());
            // Ahora los siguientes
            for(int j=1; j<fenotipo.size(); j++){
                int c = rdn.ints(1,0,cities.size()).findFirst().getAsInt();
                //System.out.println(cities.toString());
                //System.out.println(c);
                //System.out.println(fenotipo.toString());
                fenotipo.setAllele(j, cities.remove(c));

            }
            Individual<G,P> ind = breeder.newIndividual(fenotipo);
            p.addIndividual(ind);
        }

        return p;
    }

    private LinkedList<P> clonar(){
        LinkedList<P> c = new LinkedList<>();
        for(int i = 0; i < ciudades.size(); i++)
            c.add(ciudades.get(i));
        return c;
    }

    // Obtener el mejor individuo de la poblacion p
    private Individual<G,P> getMejorIndividuo(Population<G,P> pop) { 
        Individual<G, P> mejor = pop.getIndividual(0);
        for(int i = 0; i < pop.size(); i++) {
            if(mejor.getFitness() < pop.getIndividual(i).getFitness())
                mejor = pop.getIndividual(i);
        }
        return mejor;
    }
    

}