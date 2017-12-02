package Codification;
import gaframework.*;
import java.util.Random;

/**
 *
 * @author Jorge Alberto
 */

public class CodEntero implements Codification<Integer,Integer>{
	private int dim; //dimension de la instancia (número de ciudades)

	public CodEntero(int i){
		dim = i;
	}

	public Genotype<Integer> encode(Phenotype<Integer> p){
		Genotype<Integer> g = new Genotype<>(dim);
		for (int i=0; i<dim; i++){
			g.setGene(i, p.getAllele(i));
		}
		return g;
	}

	public Phenotype<Integer> decode(Genotype<Integer> g) {
	Phenotype<Integer> p = new Phenotype<>(dim);
        for(int i = 0; i < dim; i++)
	    p.setAllele(i, g.getGene(i));
		return p;
    }

    public Genotype<Integer> newRandomGenotype(){
	Random r = new Random(System.currentTimeMillis());
	Genotype<Integer> rdnG = new Genotype<>(dim);
	for(int i = 0; i < dim; i++)
	    rdnG.setGene(i,r.nextInt(dim)+1);
	return rdnG;
    }

}