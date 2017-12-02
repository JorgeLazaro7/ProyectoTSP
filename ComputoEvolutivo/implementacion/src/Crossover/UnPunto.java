/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crossover;
import gaframework.*;
import java.util.List;

/**
 *
 * @author Jorge Alberto
 * @param <G>
 */
public class UnPunto<G> implements CrossoverOp<G>{
    
    // Probabilidad de que se de el cruzamiento.
    private final double pCruza;

    /**
     * Se construye un objeto para poder realizar el operador de cruzamiento
     * @param probabilidad probabilidad de que se de el cruzamiento
     */
    public UnPunto(double probabilidad){
	this.pCruza = probabilidad;
    }
    
    public double getpCruza() {
        return pCruza;
    }

    @Override
    public List<Genotype<G>> crossover(List<Genotype<G>> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
