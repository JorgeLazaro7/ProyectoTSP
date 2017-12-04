import gaframework.*;
import org.moeaframework.problem.tsplib.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList; 

public class TSPSolucion{

	public static void main(String[] args) {

		if (args.length !=3){
			System.out.println("Error: necesitamos tres argumentos," 
				+ "[archivo] [probabilidad de cruza] [probabilidad de mutacion]");
		}else{
			try{
				TSPInstance instancia = new TSPInstance(new File(args[0]));
				String stout = args[0].substring(0, args[0].lastIndexOf('.'));
				stout += ".opt.tour";
				File tours = new File(stout);
				if (tours.exists()){
					instancia.addTour(tours);
					for (Tour tour : instancia.getTours()){
						System.out.println("mejor ruta conocida:");
						System.out.println(tour.toString());
						System.out.println("Distancia mínima: " + tour.distance(instancia));
					}
				}

				int[] ciudades = instancia.getDistanceTable().listNodes();
        		LinkedList<Integer> nodos = new LinkedList<>();
        		for(int i = 0; i < ciudades.length; i++)
            		nodos.add(ciudades[i]);
				CodEntero codificacion = new CodEntero(instancia.getDimension());
				Fitness fitness = new Fitness(instancia.getDistanceTable());
				Ruleta<Integer, Integer> seleccion = new Ruleta<>(2);
				CruzaUniformeO<Integer> cruza = new CruzaUniformeO<>(Double.parseDouble(args[1]));
				Mutacion mutacion = new Mutacion(Double.parseDouble(args[2]));
				Generaciones terminacion = new Generaciones(5000);

				AlgoritmoGenetico<Integer, Integer> algoritmoGenetico = new AlgoritmoGenetico<>(codificacion, 
					fitness, seleccion, cruza, mutacion, terminacion, null, nodos, 200);

				algoritmoGenetico.run();


			}catch(IOException e) {
            	System.err.println("No se pudo abrir el archivo TSP");
            	e.printStackTrace();
        	}
		}
		
	}

}