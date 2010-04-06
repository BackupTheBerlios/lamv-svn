/*
 * ModeloTabela.java
 *
 * Created on 2 de Mar�o de 2005, 23:54
 */

package Componentes;

import javax.swing.table.DefaultTableModel;
import Dados.*;

/**
 *
 * @author Gustavo
 */
public class ModeloTabela extends DefaultTableModel {

    /** Creates a new instance of ModeloTabela */
    public ModeloTabela() {
        super(Ob ,nomeCols);
        this.removeRow(0);
        /*addDado( new Matriz(1,1) );
        addDado( new Vetor( 100 ) );
        addDado( new Constante(15) );*/
    }
    
    public boolean isCellEditable(int row, int column) {
        return canEdit[column];
    }
    public String getColumnName(int column) {
        return nomeCols[column];
    }

    public boolean addDado( Dado d ) {
        if( d.eMatriz() ) {
            Matriz M = (Matriz)d;
            addMatriz(M);
        } else {
            if( d.eVetor() ) {
                Vetor V = (Vetor)d;
                addVetor(V);
            } else {
                if( d.eConstante() ) {
                    Constante C = (Constante)d;
                    addConstante(C);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    public void addMatriz( Matriz M ) {
        Object [] b = new Object[3];
        b[0] = M.getName();
        b[1] = ""+M.getnLinhas()+"x"+M.getnColunas();
        b[2] = "M";
        //createImageIcon("ImageClub/food/strawberry.jpg");
        addRow( b );
    }
    public void addVetor( Vetor V ) {
        Object [] b = new Object[3];
        b[0] = V.getName();
        b[1] = ""+V.getTam()+"x1";
        b[2] = "V";
        addRow( b );
    }
    public void addConstante( Constante C ) {
        Object [] b = new Object[3];
        b[0] = C.getName();
        b[1] = "1x1";
        b[2] = "C";
        addRow( b );
    }
    public void enableEdit() {
        canEdit[0] = true;
    }
    public void desableEdit() {
        canEdit[0] = false;
    }

    private static boolean [] canEdit = new boolean [] { true, false, false };
    private static String [] nomeCols = new String [] { "Nome", "Dim", "Tipo" };
    private static Object [][] Ob = new Object [][] {  {"", "", ""} };
}