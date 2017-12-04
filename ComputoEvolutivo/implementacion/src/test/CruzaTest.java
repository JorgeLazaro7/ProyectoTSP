import gaframework.*;
import java.util.LinkedList; 

public class CruzaTest {

	public static void main(String[] args) {

		Genotype<Integer> p1 = new Genotype<>(6);
		Genotype<Integer> p2 = new Genotype<>(6);

		p1.setGene(0, 6);
		p1.setGene(1, 3);
		p1.setGene(2, 1);
		p1.setGene(3, 4);
		p1.setGene(4, 2);
		p1.setGene(5, 5);

		p2.setGene(0, 6);
		p2.setGene(1, 5);
		p2.setGene(2, 4);
		p2.setGene(3, 3);
		p2.setGene(4, 2);
		p2.setGene(5, 1);

		LinkedList<Genotype<Integer>> l = new LinkedList<>();

		l.add(p1);
		l.add(p2);

		CruzaUniformeO<Integer> cruza = new CruzaUniformeO<>(1.0);

		LinkedList<Genotype<Integer>> lr = (LinkedList<Genotype<Integer>>)cruza.crossover(l);

		System.out.println(lr.toString());

	}


}