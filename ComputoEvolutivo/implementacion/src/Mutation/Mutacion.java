import gaframework.*;
import java.util.Random;

public class Mutacion implements MutationOp<Integer>{

	private double probMut;

	public Mutacion(double probabilidad){
		probMut = probabilidad;
	}

	/*public Genotype<Integer> mutate(Genotype<Integer> g){
		Random r = new Random(System.currentTimeMillis());
		for(int i = 0; i < g.size(); i++){
			if (r.nextDouble() <= probMut){
				int nuevoGen = r.nextInt(g.size());
				g.setGene(i, nuevoGen);
			}
		}
		return g;
	}*/


	public Genotype<Integer> mutate(Genotype<Integer> g){

		int size = g.size();

		Random r = new Random(System.currentTimeMillis());
		//Se crea una mascara de tamano size de booleanos 
		//true es elegido con probabilidad probMut y 
		//false es elegido con probabilidad 1-probMut
        boolean[] mascara = new boolean[size];
        int aMutar = 0;
        for (int i=1; i<size; i++){
        	if (r.nextDouble() <= probMut){
        		mascara[i] = true;
        		aMutar++;
        	}
        	else
        		mascara[i] = false;
        }

        // Si solo tenemos un solo gen a mutar, elegimos otro aleatoriamente para permutarlos
        // Esto cambia bastante la probabilidad de mutación en el caso de que solo se haya seleccionado uno
        // Pero nos asegura que se producira un individuo valido
        while(aMutar == 1){
        	int pos = r.nextInt(size);
        	if (!mascara[pos]){
        		mascara[pos] = true;
        		aMutar++;
        	}
        }


        if(aMutar!=0){ 

        	// Ahora hay que permutar los elementos que decidimos mutar, solo los voy a girar a la izquierda,
        	// Creo que podría mejorar el algoritmo si esa permitacion se hiciera de una manera mas aleatoria.

        	//Creamos un arreglo con las posiciones de los elementos a mutar
        	int[] posiciones = new int[aMutar];
        	/*for (int i=0; i<posiciones.length; i++){
        		for (int j=0; j<mascara.length; j++){
        			if (mascara[j]){
        				posiciones[i] = j;
        				j=mascara.length;
        			}
       		 	}
        	}*/

        	int i = 0;
        	int p=0;
        	while(p<posiciones.length){
        		while (i<mascara.length && !mascara[i]){
        			i++;
        		}
        		posiciones[p] = i;
        		p++;
        		i++;
        	}

    

        	int temp = g.getGene(posiciones[0]);
        	for (int j=0; j<posiciones.length -1; j++){
        		g.setGene(posiciones[j], g.getGene(posiciones[j+1]));
        	}
        	g.setGene(posiciones[posiciones.length-1], temp);
        }

        return g;
	}
}