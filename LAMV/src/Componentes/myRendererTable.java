/*
 * myRendererTable.java
 *
 * Created on 19 de Outubro de 2005, 13:26
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package Componentes;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author anderson
 */
public class myRendererTable extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

       
        if (column == 0 || row == 0) {
            setHorizontalAlignment( LEFT );
            setBackground( Color.lightGray );
            setForeground( Color.black );
        } else {
            setBackground( Color.white );
            setForeground( Color.black );
        }
        
        super.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, column);
        
        return this;
    }
}

