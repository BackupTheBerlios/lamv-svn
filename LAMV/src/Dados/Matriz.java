/*
 * Matriz.java
 *
 * Created on 21 de Janeiro de 2005, 05:49
 */

package Dados;

/**
 *
 * @author  Gustavo
 */

import java.lang.Math;

public class Matriz extends Dado {
    
    public Matriz( int l, int c) {
        this(l,c,Dado.NULO,"Matriz",null,null);
    }   
    public Matriz( int l, int c, int tipo ) {
        this( l, c, tipo, "Matriz", null, null);
    }
    public Matriz( int l, int c, String nome ) {
        this(l,c,Dado.NULO,nome,null,null);
    }
    public Matriz( int l, int c, String nome, int tipo ) {
        this(l,c,tipo,nome,null,null);
    }
    public Matriz( int lins, int cols, int tipo, String nome, String [] nLins, String [] nCols ) {
        this.setDimC( cols );
        this.setDimL( lins );
        this.nlinhas = lins;
        this.ncolunas = cols;
        this.setName(nome);
        this.setTipo(Dado.MATRIZ);
        this.M = new double[lins][cols];
        this.data = new double[lins][cols];        
        this.MComplex = new double[lins][cols];
        this.nomeLinhas = nLins;
        this.nomeColunas = nCols;
        
        if( tipo == Dado.ALEATORIO ) {
            for ( int l = 0; l < lins ; l++)
                for ( int c = 0; c < cols; c++)
                    this.M[l][c] = java.lang.Math.random();
        } else {
            if( tipo == Dado.NULO ) {
                for ( int l = 0; l < lins ; l++)
                    for ( int c = 0; c < cols; c++)
                        this.M[l][c] = 0.0D;
            } else {
                if( tipo == Dado.UNS ) {
                    for ( int l = 0; l < lins ; l++)
                        for ( int c = 0; c < cols; c++)
                            this.M[l][c] = 1.0D;
                } else {
                    if( tipo == Dado.IDENTIDADE ) {
                        if( lins != cols ) {
                            System.out.println("Impossível criar uma matriz identidade.\nErro: número de linhas é diferente do número de colunas!");
                            this.M = null;
                            return;
                        }
                        for ( int l = 0; l < lins ; l++)
                            for ( int c = 0; c < cols; c++)
                                this.M[l][c] = 0D;
                        for ( int l = 0; l < lins; l++ )
                            this.M[l][l] = 1D;
                    } else {
                        for ( int l = 0; l < lins ; l++)
                            for ( int c = 0; c < cols; c++)
                                this.M[l][c] = 0.0D;
                        System.out.println("Tipo de matriz inválido!\nA matriz foi setada como nula!");
                    }
                }
            }
        }
    }
    public Matriz( Matriz m ) {
        this.setDimC( m.getnColunas() );
        this.setDimL( m.getnLinhas() );
        this.nlinhas = m.getnLinhas();
        this.ncolunas = m.getnColunas();
        this.setName( m.getName() );
        this.setTipo( m.getTipo() );
        this.M = new double[nlinhas][ncolunas];
        //M = m.clone();
        this.nomeLinhas = (String [])m.getNomeLinhas().clone();
        this.nomeColunas = (String [])m.getNomeColunas().clone();
    }
    /**Retorna a parte real da matriz*/
    public double [][] getMatriz() {
        return M;
    }
    /**Retorna a parte complexa da matriz*/
    public double [][] getMatrizComplex(){
        return MComplex;
    }
    /**Seta o valor de um campo da matriz real*/
    public void setValor( int l, int c, double val ) {
        this.M[l][c] = val;
    }
    /**Seta o valor de um campo da matriz complexa*/
    public void setValorComplex( int l, int c, double val){
        this.MComplex[l][c] = val;
    }
    /**Seta o valor real e complexos da linha l coluna c*/
    public void setValor( int l, int c, double val, double valComplex){
        this.M[l][c] = val;
        this.MComplex[l][c] = valComplex;
    }
    public double getValor( int l, int c ) {
        return this.M[l][c];
    }
    /**Obtém o valor complexo da matriz*/
    public double getValorComplex( int l , int c) {
        return this.MComplex[l][c];
    }
    /**Obtem um vetor coluna da matriz*/
    public double [] getColuna( int n ) {
        double [] c = new double [nlinhas];
        for( int x = 0; x < nlinhas; x++ ) {
            c[x] = M[x][n];
        }
        return c;
    }
    /**Obtem um vetor coluna complexo da matriz*/
    public double [] getColunaComplex( int n ) {
        double [] c = new double [nlinhas];
        for( int x = 0; x < nlinhas; x++ ) {
            c[x] = MComplex[x][n];
        }
        return c;
    }
    /**Obtem uma linha da matriz*/
    public double [] getLinha( int n ) {
        double [] l = new double [ncolunas];
        for( int x = 0; x <= ncolunas; x++ ) {
            l[x] = M[n][x];
        }
        return l;
    }
    /**Obtem uma linha complexa da matriz*/
    public double [] getLinhaComplex( int n ) {
        double [] l = new double [ncolunas];
        for( int x = 0; x <= ncolunas; x++ ) {
            l[x] = MComplex[n][x];
        }
        return l;
    }    
    
    public int getnLinhas() {
        return nlinhas;
    }
    public int getnColunas() {
        return ncolunas;
    }
    
    public void zeraDados() {
        int l,c;
        for ( l = 0; l < this.nlinhas ; l++)
            for (c = 0; c < this.ncolunas; c++)
                this.M[l][c] = 0.0D;
    }
    public void ones() {
        int l,c;
        for ( l = 0; l < this.nlinhas ; l++)
            for (c = 0; c < this.ncolunas; c++)
                this.M[l][c] = 1.0D;
    }

    public void Zera() {
        for ( int l = 0; l < nlinhas; l++ ) {
            for ( int c = 0; c < ncolunas; c++ ) {
                M[l][c] = 0.0D;
            }
        }
    }

    public void Sqrt() {
        setName("raiz_"+ getName() );
        for ( int l = 0; l < nlinhas; l++ ) {
            for ( int c = 0; c < ncolunas; c++ ) {
                M[l][c] = Math.sqrt(M[l][c]);
            }
        }
    }
    
    public void info() {
        for( int l = 0; l < nlinhas; l++ ) {
            for( int c = 0; c < ncolunas; c++ ) {
                System.out.print(M[l][c]+" ");
            }
            System.out.println();
        }
    }

    public Vetor getVetorColuna( int indice ) {
        if( indice < 0 || indice > (ncolunas-1) )
            return null;
        Vetor v = new Vetor(nlinhas);
        for( int c = 0; c < nlinhas; c++ ) {
            v.setValor(c, M[c][indice] );
        }
        v.setName("Vetor"+getName()+"[:,"+indice+"]");
        return v;
    }
    public Vetor getVetorLinha( int indice ) {
        if( indice < 0 || indice > (nlinhas-1) )
            return null;
        Vetor v = new Vetor(ncolunas);
        for( int c = 0; c < ncolunas; c++ ) {
            v.setValor(c, M[indice][c] );
        }
        v.setName("Vetor"+getName()+"["+indice+",:]");
        return v;
    }
    public Constante getConstante( int x, int y ) {
        return new Constante(M[x][y],getName()+"["+x+","+y+"]");
    }
    /**************************************************************************/
    /** 
    public Complex(double real, double imag) {
        this.re = real;
        this.im = imag;
    }*/

    /* Retorna uma string representando um objeto invocado*/
    public String toString(int l, int c)  { return M[l][c] + " + " + MComplex[l][c] + "i"; }

    // return |this|
    public double abs(int l, int c) 
    { 
        return Math.sqrt(M[l][c]*M[l][c] + MComplex[l][c]*MComplex[l][c]);  
    }

    /** Retorna um objeto novo cujo valor seja (this + b)*/
    public void plus(double real, double img, int l , int c) { 
        //Complex a = this;             
        M[l][c] = M[l][c] + real;
        MComplex[l][c] = MComplex[l][c] + img;
        //Complex sum = new Complex(real, imag);
        //return sum;
    }

    /** Retorn um novo objeto cujo valor seja (this - b)*/
    public void minus(double real, double img, int l , int c) { 
        //Complex a = this;   
        M[l][c] = M[l][c] - real;
        MComplex[l][c] = MComplex[l][c] - img;
        //Complex diff = new Complex(real, imag);
        //return diff;
    }

    /** Retorn um novo objeto cujo valor seja (this * b)*/
    public void times(double real, double img, int l , int c) {
        //Complex a = this;
        M[l][c] = M[l][c] * real - MComplex[l][c] * img;
        MComplex[l][c] = M[l][c] * img + MComplex[l][c] * real;
        //Complex prod = new Complex(real, imag);
        //return prod;
    }

    /** Retorn um novo objeto cujo valor seja (this * alpha)*/
    public void times(int l, int c ,double alpha) {
        M[l][c] = alpha * M[l][c];
        MComplex[l][c] = alpha * MComplex[l][c];
    }

    /** Retorn um novo objeto cujo valor seja o conjugado deste*/    
    public void conjugate(int l, int c) 
    {  
        MComplex[l][c] = -MComplex[l][c]; 
    }
    
    public void setMatriz( double[][] a ) {
        M = a;
    }
    
    protected int nlinhas;
    protected int ncolunas;
    public double [][] M;
    public double [][] MComplex;
    /** Guarda os valores da matriz */
    public double [][] data;
    
}