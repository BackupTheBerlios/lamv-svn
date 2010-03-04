/*
 * Constante.java
 *
 * Created on 27 de Janeiro de 2005, 17:15
 */

package Dados;

/**
 *
 * @author  Gustavo
 */
public class Constante extends Dado {
    
    /** Creates a new instance of Constante */
    public Constante() {
        this(0.0);
    }
    
    public Constante( double d ) {
        this( d , "Constante" );
    }
    public Constante( double d, String nome ) {
        this( "Constante", Dado.NULO );
        this.k = d;
    }
    public Constante( String n, int tipo ) {
        this.setDimC(1);
        this.setDimL(1);
        this.setName(n);
        this.setTipo(Dado.CONSTANTE);
        this.orientacao = 0;
        if( tipo == Dado.NULO )
            this.k = 0.0D;
        else if( tipo == Dado.UNS )
            this.k = 1.0D;
        else if( tipo == Dado.ALEATORIO )
            this.k = java.lang.Math.random();
        else {
            this.k = 0.0D;
            System.out.println("Tipo de constante inválido!\nA constante foi setada como nula!");
        }
    }
    
    public void setValor( double d ) {
        this.k = d;
    }
    
    public double getValor() {
        return k;
    }
    
    public void setOrientacao( int o ) {
        orientacao = o;
    }
    public int getOrientacao() {
        return orientacao;
    }

    public void Zera() {
        k = 0.0;
    }

    public void Sqrt() {
        setName("raiz_"+ getName() );
        k = Math.sqrt(k);
    }

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    protected double k;
    protected int orientacao;  /* 1 - vertical
                                * 0 - horizontal */
}