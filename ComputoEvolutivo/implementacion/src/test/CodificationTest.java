import gaframework.*;
import Codification.CodEntero;

public class CodificationTest{

	public static void main(String[] args) {

		int SIZE = 10;

		CodEntero codificacion = new CodEntero(SIZE);

		Genotype<Integer> g = new Genotype<>(SIZE);
		Phenotype<Integer> p = new Phenotype<>(SIZE);

		//Se llena el genotipo
		for (int i = 0; i < SIZE; i++) {
	    	g.setGene(i, g.size() - i);
		}

		//Se llena el fenotipo
		for (int i = 0; i < SIZE; i++) {
	    	p.setAllele(i, p.size() - i);
		}

		System.out.println("\n\nContenido del genotipo después de llenarlo:"); 
		for (int i = 0; i < SIZE; i++) {
	    	System.out.print(g.getGene(i) + " ");
		}

		System.out.println("\n\nDecode");
		Phenotype<Integer> d = codificacion.decode(g);
		for (int i = 0; i < SIZE; i++) {
	    	System.out.print(d.getAllele(i) + " ");
		}

		System.out.println("\n\nContenido del fenotipo después de llenarlo:"); 
		for (int i = 0; i < SIZE; i++) {
	    	System.out.print(p.getAllele(i) + " ");
		}
		
		System.out.println("\n\nEncode");
		Genotype<Integer> e = codificacion.encode(p);
		for (int i = 0; i < SIZE; i++) {
	    	System.out.print(e.getGene(i) + " ");
		}

	}

}