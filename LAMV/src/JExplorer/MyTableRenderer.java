package JExplorer;

import javax.swing.table.TableCellRenderer;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;


/**
 * Title:        jExplorer
 * Description:  Use JTree to create a Windows Explorer-like application.
 * Copyright:    Copyright (c) 2002
 * Company:      BrainySoftware.com
 * @author Budi Kurniawan
 * @version 1.0
 */

public class MyTableRenderer implements TableCellRenderer {

  public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) {

    if (value instanceof DiskObject) {
      DiskObject diskObject = (DiskObject) value;
      JLabel label = null;
      if (diskObject.type==JExplorer.DIRECTORY)
        label = new JLabel(diskObject.name, JExplorer.folderIcon, SwingConstants.LEFT);
      else
        label = new JLabel(diskObject.name, JExplorer.fileIcon, SwingConstants.LEFT);
      return label;
    }
    return null;
  }

}