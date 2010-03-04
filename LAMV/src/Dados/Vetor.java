/*
 * Vetor.java
 *
 * Created on 27 de Janeiro de 2005, 17:46
 */

package Dados;

/**
 *
 * @author  Gustavo
 */
import java.lang.Math;

public class Vetor extends Dado {
    
    /** Creates a new instance of Vetor */
    public Vetor( int dim, String nome ) {
        this( dim, nome, Dado.NULO );
    }
    public Vetor( int dim ) {
        this( dim, "Vetor", Dado.NULO );
    }
    public Vetor( int dim , String nome, int tipo ) {
        this.setDimC(1);
        this.setDimL(dim);

        this.v = new double[dim];
        this.setTipo( Dado.VETOR );
        this.tam = dim;
        this.setName(nome);
        if( tipo == Dado.NULO ) {
            for( int c = 0; c < dim; c++ ) {
                this.v[c] = 0.0D;
            }
        } else {
            if( tipo == Dado.ALEATORIO ) {
                for( int c = 0; c < dim; c++ ) {
                    this.v[c] = java.lang.Math.random();
                }
            } else {
                if( tipo == Dado.UNS ) {
                    for( int c = 0; c < dim; c++ ) {
                        this.v[c] = 1.0D;
                    }
                } else {
                    for( int c = 0; c < dim; c++ )
                        this.v[c] = 0.0D;
                    System.out.println("Tipo de vetor inválido!\nO vetor foi setado como nulo!");
                }
            }
        }
    }

    public void Zera() {
        for( int c = 0; c < tam; c ++ ) {
            v[c] = 0.0;
        }
    }
    public void Sqrt() {
        for( int c = 0; c < tam; c++ ) {
            v[c] = Math.sqrt( v[c] );
        }
    }


    public Vetor( Vetor _v ) {
        this(_v.getTam(), _v.getName() );
        for( int c = 0; c < _v.getTam(); c++ ) {
            v[c] = _v.getValor( c );
        }
        this.setVerPontos( _v.getVerPontos() );
        this.setVerRetas( _v.getVerRetas() );
        this.setCor( _v.getCor() );
        this.setTipoPonto( _v.getTipoPonto() );
    }

    public double [] getVetor() {
        return v;
    }

    public void setVetor( double [] d ) {
        this.v = d;
    }

    public void setValor( int indice , double val ) {
        this.v[indice] = val;
    }

    public double getValor( int indice ) {
        return this.v[indice];
    }

    public int getTam() {
        return tam;
    }

    protected double [] v;
    protected int tam;
}