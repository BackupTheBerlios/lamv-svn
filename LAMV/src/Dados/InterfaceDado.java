/*
 * InterfaceDado.java
 *
 * Created on 29 de Março de 2005, 20:05
 */

package Dados;

/**
 *
 * @author Gustavo
 */
public interface InterfaceDado {

    public String getName();
    public void setName( String _nome );

    public int getTipo();
    public void setTipo( int t );

    public boolean eMatriz();
    public boolean eVetor();
    public boolean eConstante();

    public java.awt.Color getCor();
    public void setCor( java.awt.Color _cor );

    public boolean verPontos();
    public boolean verRetas();

    public void setVerPontos( boolean b );
    public void setVerRetas( boolean b );

    public int getTipoPonto();
    public void setTipoPonto( int t );

    public int getDimL();
    public void setDimL( int dimL );

    public int getDimC();
    public void setDimC( int dimC );

    public String [] getNomeLinhas();
    public void setNomeLinhas( String [] n );

    public String [] getNomeColunas();
    public void setNomeColunas( String [] n );

}
