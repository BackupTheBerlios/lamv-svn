/*
 * ModeloTabela.java
 *
 * Created on 2 de Março de 2005, 23:54
 */

package Componentes;

import javax.swing.table.DefaultTableModel;
import Dados.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Gustavo
 */
public class ModeloTabelaLista extends DefaultTableModel {

    /** Creates a new instance of ModeloTabela */
    public ModeloTabelaLista() {
        super(Ob , nomeCols);
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

    public boolean addObjeto( String nomeGraf ) {
        Object [] b = new Object[1];
        b[0] = nomeGraf;
        addRow( b );
        return true;
    }

    public void enableEdit() {
        canEdit[0] = true;
    }
    public void desableEdit() {
        canEdit[0] = false;
    }

    private static boolean [] canEdit = new boolean [] { false };
    private static String [] nomeCols = new String [] { "Nome" };
    private static Object [][] Ob = new Object [][] { {""} };
}