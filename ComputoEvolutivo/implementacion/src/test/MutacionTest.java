import gaframework.*;

public class MutacionTest {

	public static void main(String[] args) {

		Genotype<Integer> g1 = new Genotype<>(6);

		g1.setGene(0, 6);
		g1.setGene(1, 3);
		g1.setGene(2, 1);
		g1.setGene(3, 4);
		g1.setGene(4, 2);
		g1.setGene(5, 5);

		System.out.println("antes de la mutacion: \n" + g1.toString());

		Mutacion m = new Mutacion(0.5);

		Genotype<Integer> g2 = m.mutate(g1);

		System.out.println("despues de la mutacion: \n" + g2.toString());

	}

}