/*
 * Ponto.java
 *
 * Created on 10 de Março de 2005, 12:16
 */

package Componentes.Grafico;

/**
 *
 * @author Gustavo
 */
public class Ponto {
    
    /** Creates a new instance of Ponto */
    public Ponto() {
        this.x = 0;
        this.y = 0;
    }
    public Ponto( int _x, int _y ) {
        this.x = _x;
        this.y = _y;
    }

    public int x;
    public int y;
}