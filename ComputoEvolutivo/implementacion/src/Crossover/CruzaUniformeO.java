import gaframework.*;
import java.util.List;
import java.util.LinkedList; 
import java.util.Random;

/**
 * @author Jorge Alberto
 */
public class CruzaUniformeO<G> implements CrossoverOp<G>{
    
    // Probabilidad de que se de el cruzamiento.
    private final double probCruza;

    public CruzaUniformeO(double probabilidad){
	   probCruza = probabilidad;
    }
    
    public double getProbCruza() {
        return probCruza;
    }

    @Override
    public List<Genotype<G>> crossover(List<Genotype<G>> padres) {

        Genotype<G> padre1 = padres.get(0);
        Genotype<G> padre2 = padres.get(1);

        LinkedList<Genotype<G>> hijos = new LinkedList<>();

        Random rdn = new Random(System.currentTimeMillis());


        //////////////////////////

        

        double a = rdn.nextDouble(); 
                    // Experimento de Bernoulli si el número alaetorio 
                    // obtenido está entre 0 y la probabilidad de cruza, 
                    // esta se hace.
        if(a <= probCruza){ ////Se hace la cruza

            int size = padre1.size();


            /////////////////////
            //Se crea una mascara de tamano size de booleanos true y false son equiprobables
            boolean[] mascara = {true, false, false, true, true, false};
            /*boolean[] mascara = new boolean[size];
            for (int i=0; i<size; i++){
                mascara[i] = (rdn.nextDouble() < 0.5) ? false : true;
            }*/

            //Se genera una cadena genética vacía
            LinkedList<G> f1 = new LinkedList<>();
            //int faltan = 0; 
            LinkedList<G> f2 = new LinkedList<>();

            // Hijos
            Genotype<G> hijo1 = new Genotype<>(size);
            Genotype<G> hijo2 = new Genotype<>(size);

            //Si el valor en la mascara es 1 se toman los genes de los padres en la misma posicion
            for (int i=0; i<size; i++){
                if (mascara[i] == true){
                    hijo1.setGene(i, padre1.getGene(i));
                    hijo2.setGene(i, padre2.getGene(i));
                }else{
                    f1.add(padre1.getGene(i));
                    f2.add(padre2.getGene(i));
                    //faltan++;
                }
            }

            for(int i=0; i<size; i++){
                if(f1.contains(padre2.getGene(i))){
                    for(int j=0; j<size; j++){
                        if(hijo1.getGene(j) == null){
                            hijo1.setGene(j, padre2.getGene(i));
                            j=size;
                        }
                    }
                    f1.remove(padre2.getGene(i));
                }
            }

            for(int i=0; i<size; i++){
                if(f2.contains(padre1.getGene(i))){
                    for(int j=0; j<size; j++){
                        if(hijo2.getGene(j) == null){
                            hijo2.setGene(j, padre1.getGene(i));
                            j = size;
                        }

                    }
                    f2.remove(padre1.getGene(i));
                }
            }
            /////////////////////


            /*int punto = rdn.nextInt(size - 1) + 1;

            for (int i=0; i< punto; i++){
                hijo1.setGene(i, padre1.getGene(i));
                hijo2.setGene(i, padre2.getGene(i));
            }

            for (int j=punto; j<size; j++){
                hijo1.setGene(j, padre2.getGene(j));
                hijo2.setGene(j, padre1.getGene(j));
            }*/

            hijos.add(hijo1);
            hijos.add(hijo2);

            return hijos;

        } else {
            return padres;    
        }
    }
}