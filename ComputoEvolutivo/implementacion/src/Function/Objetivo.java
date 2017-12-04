import gaframework.*;


// Uso la función objetivo linear ranking presentada en el libro

public class Objetivo implements ObjectiveFunction<Integer,Integer>{

    public void evaluate(Population<Integer,Integer> p){

        double max = p.getWorstIndividual().getFitness();
        double min = p.getBestIndividual().getFitness();
        
        for(int i = 0; i < p.size(); i++){
            Individual<Integer, Integer> ind;
            double val = 0;
            ind = p.getIndividual(i);
            val = (maxfit + minfit) - ind.getFitness();
            ind.setObjectiveEvaluation(val);
        }
    }
}