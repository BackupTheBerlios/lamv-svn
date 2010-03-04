package JExplorer;

import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import java.awt.Component;
import javax.swing.ImageIcon;


public class MyTreeRenderer extends DefaultTreeCellRenderer {

  public Component getTreeCellRendererComponent(JTree tree, Object value,
    boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

    super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
    DiskObject nodeInfo = (DiskObject) node.getUserObject();
    int type = nodeInfo.type;
    if (type==JExplorer.COMPUTER)
      setIcon(JExplorer.computerIcon);
    else if (type==JExplorer.DRIVE)
      setIcon(JExplorer.driveIcon);
    else if (type==JExplorer.DIRECTORY)
      setIcon(JExplorer.folderIcon);
    else if (type==JExplorer.FILE)
      setIcon(JExplorer.fileIcon);
    return this;
  }
}