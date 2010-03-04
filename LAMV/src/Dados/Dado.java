/*
 * Dado.java
 *
 * Created on 21 de Janeiro de 2005, 06:01
 */

package Dados;
import Dados.Complex;
/**
 *
 * @author  Gustavo
 */
public class Dado implements InterfaceDado{
    
    /** Creates a new instance of Dado */
    public Dado() {
        tipo = -1;
        valor = null;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo( int t ) {
        tipo = t;
    }

    public void setDado( Dado d ) {
        valor = d;
        tipo = d.getTipo();
    }

    public Dado getDado() {
        return valor;
    }

    public String getName() {
        return nome;
    }

    public void setName( String n ) {
        this.nome = n;
    }

    public boolean eMatriz() {
        if( tipo == MATRIZ )
            return true;
        else
            return false;
    }
    public boolean eVetor() {
        if( tipo == VETOR )
            return true;
        else
            return false;
    }
    public boolean eConstante() {
        if( tipo == CONSTANTE )
            return true;
        else
            return false;
    }

    public java.awt.Color getCor() {
        return cor;
    }
    public boolean verPontos() {
        return pontos;
    }
    public boolean verRetas() {
        return retas;
    }
    public int getTipoPonto() {
        return TipoPonto;
    }

    public void setCor( java.awt.Color _cor ) {
        this.cor = _cor;
    }
    public void setVerPontos( boolean _b ) {
        pontos = _b;
    }
    public void setVerRetas( boolean _b ) {
        retas = _b;
    }

    public boolean getVerPontos( ) {
        return pontos;
    }
    public boolean getVerRetas( ) {
        return retas;
    }
    public void setTipoPonto( int _t ) {
        TipoPonto = _t;
    }

    public void setDimL( int _a ) {
        DimLinhas = _a;
    }
    public void setDimC( int _a ) {
        DimColunas = _a;
    }

    public int getDimL( ) {
        return DimLinhas;
    }
    public int getDimC( ) {
        return DimColunas;
    }

    public String [] getNomeLinhas() {
        return nomeLinhas;
    }
    public String getNomeLinhas( int x ) {
        return nomeLinhas[x];
    }
    public void setNomeLinhas( String [] n ) {
        nomeLinhas = n;
    }
    public void setNomeLinhas( String n, int x ) {
        nomeLinhas[x] = n;
    }
    public void setComplexa( boolean flag) {
        complexa = flag;
    }
    public boolean isComplexa(){
        return complexa;
    }
    
    public String [] getNomeColunas() {
        return nomeColunas;
    }
    public String getNomeColunas( int x ) {
        return nomeColunas[x];
    }
    public void setNomeColunas( String [] n ) {
        nomeColunas = n;
    }
    public void setNomeColunas( String n, int x ) {
        nomeColunas[x] = n;
    }

    private int DimLinhas;
    private int DimColunas;
    
    public static int MATRIZ = 0;
    public static int VETOR = 1;
    public static int CONSTANTE = 2;

    public static int ALEATORIO = 0;
    public static int NULO = 3;
    public static int UNS = 2;
    public static int IDENTIDADE = 1;

    private int tipo;
    private String nome;
    private Dado valor;
    
    private java.awt.Color cor = java.awt.Color.BLACK;
    private boolean pontos = true;
    private boolean retas = true;
    private boolean complexa = false;
    private int TipoPonto = RECTF;

    public static final int RECT    = 0;
    public static final int RECTF   = 1;
    public static final int CIRCLE  = 2;
    public static final int CIRCLEF = 3;
    public static final int CROSS   = 4;
    public static final int MINUS   = 5;
    public static final int STAR    = 6;

    public String [] nomeLinhas;
    public String [] nomeColunas;
}