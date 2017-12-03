import gaframework.*;
import java.util.Random;

public class Mutacion implements MutationOp<Integer>{

	private double probMut;

	public Mutacion(double probabilidad){
		probMut = probabilidad;
	}

	public Genotype<Integer> mutate(Genotype<Integer> g){
		Random r = new Random(System.currentTimeMillis());
		for(int i = 0; i < g.size(); i++){
			if (r.nextDouble() <= probMut){
				int nuevoGen = r.nextInt(g.size());
				g.setGene(i, nuevoGen);
			}
		}
		return g;
	}

}