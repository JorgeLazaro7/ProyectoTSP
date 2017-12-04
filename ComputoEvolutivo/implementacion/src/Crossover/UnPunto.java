
package Crossover;
import gaframework.*;
import java.util.List;
import java.util.LinkedList; 
import java.util.Random;

/**
 * @author Jorge Alberto
 */
public class UnPunto<G> implements CrossoverOp<G>{
    
    // Probabilidad de que se de el cruzamiento.
    private final double probCruza;

    public UnPunto(double probabilidad){
	   probCruza = probabilidad;
    }
    
    public double getpCruza() {
        return probCruza;
    }

    @Override
    public List<Genotype<G>> crossover(List<Genotype<G>> padres) {

        Genotype<G> padre1 = padres.get(0);
        Genotype<G> padre2 = padres.get(1);
        LinkedList<Genotype<G>> hijos = new LinkedList<>();

        Random rdn = new Random(System.currentTimeMillis());

        double a = rdn.nextDouble(); 
                    // Experimento de Bernoulli si el número alaetorio 
                    // obtenido está entre 0 y la probabilidad de cruza, 
                    // esta se hace.
        if(a <= probCruza){ ////Se hace la cruza

            int size = padre1.size();
            Genotype<G> hijo1 = new Genotype<>(size);
            Genotype<G> hijo2 = new Genotype<>(size);

            int punto = rdn.nextInt(size - 1) + 1;

            for (int i=0; i< punto; i++){
                hijo1.setGene(i, padre1.getGene(i));
                hijo2.setGene(i, padre2.getGene(i));
            }

            for (int j=punto; j<size; j++){
                hijo1.setGene(j, padre2.getGene(j));
                hijo2.setGene(j, padre1.getGene(j));
            }

            hijos.add(hijo1);
            hijos.add(hijo2);

            return hijos;

        } else {
            return padres;    
        }
    }
}