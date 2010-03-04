/*
 * Elipse.java
 *
 * Created on 31 de Março de 2005, 17:44
 */

package Dados;

/**
 *
 * @author Gustavo
 */
public class Elipse extends Dado {
    
    /** Creates a new instance of Elipse */
    public Elipse() {
    }
    
    public double dFocos() {
        return 0;
    }
    
    private Ponto Centro;
    private Ponto Foco1;
    private Ponto Foco2;

    private double a;
    private double b;
    private double c;

    private Ponto VA1;
    private Ponto VA2;
    private Ponto VB1;
    private Ponto VB2;

    private double inc; // incremento do cálculo. Resolução da figura.
}
