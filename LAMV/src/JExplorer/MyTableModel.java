package JExplorer;

import javax.swing.table.AbstractTableModel;
import java.io.File;

public class MyTableModel extends AbstractTableModel {

  Object rowData[][] = getTableData();
  Object columnNames[] = {"Nome", "Tamanho", "Tipo", "Modificado"};

  public String getColumnName(int column) {
    return columnNames[column].toString();
  }

  public Class getColumnClass(int column) {
    return getValueAt(0, column).getClass();
  }

  public int getRowCount() {
    return rowData.length;
  }

  public int getColumnCount() {
    return columnNames.length;
  }

  public Object getValueAt(int row, int col) {
    return rowData[row][col];
  }

  public boolean isCellEditable(int row, int column) {
    return false;
  }

  public void setRowData() {
    rowData = getTableData();
  }

  private Object[][] getTableData() {
    File dir = new File(JExplorer.selectedDiskObjectName);
    System.out.println(JExplorer.selectedDiskObjectName);
    File[] files = dir.listFiles();
    int fileCount = files.length;
    Object rowData[][] = new Object[fileCount][4];
    int counter = 0;
    for (int i=0; i<fileCount; i++) {
      File file = files[i];
      int type = JExplorer.DIRECTORY;
      if (file.isDirectory() && !file.isHidden()) {
        DiskObject diskObject = new DiskObject(type, file.getName(), "");
        rowData[counter][0] = diskObject;
        rowData[counter][1] = "";
        rowData[counter][2] = "Folder";
        rowData[counter][3] = (new java.util.Date(file.lastModified())).toString();
        counter++;
      }
    }
    for (int i=0; i<fileCount; i++) {
      File file = files[i];
      int type = JExplorer.FILE;
      if (!file.isDirectory()) {
        DiskObject diskObject = new DiskObject(type, file.getName(), "");
        rowData[counter][0] = diskObject;
        rowData[counter][1] = Long.toString(file.length());
        rowData[counter][2] = "Arquivo";
        rowData[counter][3] = (new java.util.Date(file.lastModified())).toString();
        counter++;
      }
    }
    return rowData;
  }

}