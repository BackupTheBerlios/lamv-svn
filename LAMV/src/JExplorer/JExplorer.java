package JExplorer;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.table.TableColumn;
import javax.swing.JOptionPane;
import javax.swing.JMenuItem;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreeSelectionModel;
import java.io.File;
import Sistema.*;

/** Classe responsável pela exibição do MaLabExplorer*/
public class JExplorer extends JFrame {

  public static final int COMPUTER = 0;
  public static final int DRIVE = 1;
  public static final int DIRECTORY = 2;
  public static final int FILE = 3;
  public static String selectedDiskObjectName = "";
  public static ImageIcon folderIcon = null;
  public static ImageIcon fileIcon = null;
  public static ImageIcon driveIcon = null;
  public static ImageIcon computerIcon = null;
  public javax.swing.JMenuItem Carregar;
  public javax.swing.JMenuItem CarregarInicializacao;
  public javax.swing.JMenuItem SetarDiretorio;

  public boolean displayFilesInTree = true;

  JScrollPane scrollPane1 = new JScrollPane();
  JSplitPane splitPane = new JSplitPane();
  JTable table;
  public JTree tree;
  JScrollPane scrollPane2 = new JScrollPane();

  /** Carrega as imagens que serão utilizadas no MaLabExplorer*/
  public void ok()
  {
    try {
      String imagePath = "Imagem/";
      folderIcon = new ImageIcon(getClass().getResource("/Imagem/Folder.gif"));
      fileIcon = new ImageIcon(getClass().getResource("/Imagem/File.gif"));
      driveIcon = new ImageIcon(getClass().getResource("/Imagem/Drive.gif"));
      computerIcon = new ImageIcon(getClass().getResource("/Imagem/Computer.gif"));
    }
    catch (Exception e) {
      JOptionPane.showMessageDialog(null,
        "Não consegiu encontrar a image no arquivo",
        "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /** Contrutor da classe
   @param configuracoes Objeto configurações do MaLab*/
  public JExplorer(Configuracoes configuracoes) {
    conf = configuracoes;
    try {
      init();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  /** Inicializa os objetos da classe*/
  private void init() throws Exception {
    // set so that the program exits
    // when the user clicks the X button
    // on the JFrame,
    Carregar = new javax.swing.JMenuItem("Carregar");
    CarregarInicializacao = new javax.swing.JMenuItem("Carregar na inicialização");
    //SetarDiretorio = new javax.swing.JMenuItem("Setar como diretório padrão");
    CarregarInicializacao.addActionListener(new java.awt.event.ActionListener(){
       public void actionPerformed(java.awt.event.ActionEvent evt){
           DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node!=null) {
                   DiskObject nodeInfo = (DiskObject) node.getUserObject();
                   File dir = new File(nodeInfo.path);
                   String Path = new String(dir.getAbsolutePath());
                   conf.enderecos.add(Path);
                   //String nome = new String(dir.getName());//f.getName(f.getSelectedFile()));
            }
       } 
    });
    Carregar.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node!=null) {
                   DiskObject nodeInfo = (DiskObject) node.getUserObject();
                   File dir = new File(nodeInfo.path);
                   String Path = new String(dir.getAbsolutePath());
                   String nome = new String(dir.getName());//f.getName(f.getSelectedFile()));
                   if(nome.toLowerCase().endsWith(".txt"))
                   {
                         java.util.StringTokenizer novoNome = new java.util.StringTokenizer(nome,".");
                         nome = novoNome.nextToken();
                         Janelas.TelaProgressao  Bar = new Janelas.TelaProgressao(new JFrame(), Path,nome);
                         Bar.start();
                   }
                   else
                   {
                       Sistema.FSys.Msg("ERRO","O arquivo '"+nome+"' não pareçe ser um arquivo .txt!" +
                                             "\nSe tiver certeza que ele contém o conteúdo que você \n" +
                                             "deseja carregar, renomeie o arquivo com a extensão .txt " +
                                             "e tente novamente",0);
                   }
            }
            else
            {
               
            }
        }
    });
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setLayout(null);
    // add ComponentListener to resize component when
    // the frame is resized.
    this.addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent e) {
        int x = e.getComponent().getWidth();
        int y = e.getComponent().getHeight();
        splitPane.setBounds(5, 5, x - 15, y - 35);
        // call the validate() method to adjust the
        // controls inside the JSplitPane
        splitPane.validate();
      }
    });

    splitPane.setBounds(new Rectangle(0, 0, 800, 600));
    this.getContentPane().add(splitPane, null);
    splitPane.add(scrollPane1, JSplitPane.LEFT);
    splitPane.add(scrollPane2, JSplitPane.RIGHT);


    // ------------------tree ---------------
    tree = new JTree(getRoot());
    tree.setCellRenderer(new MyTreeRenderer());
    tree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node =
          (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        getChildren(node);
      }
    });
    tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PDiretorioMouseClicked(evt);
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                getChildren(node);
            }
        });
    scrollPane1.getViewport().add(tree, null);

    // ------------------table ---------------
    table = new JTable(new MyTableModel());
    TableColumn column = table.getColumnModel().getColumn(0);
    column.setCellRenderer(new MyTableRenderer());
    table.setGridColor(new Color(255, 255, 255));
    scrollPane2.getViewport().add(table, null);

    // initialize ImageIcon controls
    try {
      String imagePath = "imagem/";
      folderIcon = new ImageIcon(getClass().getResource("/Imagem/Folder.gif"));
      fileIcon = new ImageIcon(getClass().getResource("/Imagem/File.gif"));
      driveIcon = new ImageIcon(getClass().getResource("/Imagem/Drive.gif"));
      computerIcon = new ImageIcon(getClass().getResource("/Imagem/Computer.jpg"));
    }
    catch (Exception e) {
      JOptionPane.showMessageDialog(null,
        "Não foi possível encontrar a imagem no arquivo: "+e.getMessage(),
        "Erro", JOptionPane.ERROR_MESSAGE);
    }
      this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PDiretorioMouseClicked(evt);
            }
        });
  }

  /** Obtém as pastas e arquivos filhos da pasta mãe*/
  private void getChildren(DefaultMutableTreeNode node) {
    if (node!=null) {
      DiskObject nodeInfo = (DiskObject) node.getUserObject();
      if (nodeInfo.type==JExplorer.DIRECTORY || nodeInfo.type==JExplorer.DRIVE) {
        File dir = new File(nodeInfo.path);
        File[] files = dir.listFiles();

        int fileCount = files.length;
        for (int i=0; i<fileCount; i++) {
          File file = files[i];
          int type = JExplorer.DIRECTORY;
          if (file.isDirectory()) {
            DefaultMutableTreeNode childNode =
              new DefaultMutableTreeNode(
                new DiskObject(type, file.getName(), file.getAbsolutePath()));
            node.add(childNode);
          }
        }
        if (displayFilesInTree) {
          for (int i=0; i<fileCount; i++) {
            File file = files[i];
            int type = JExplorer.FILE;
            if (!file.isDirectory()) 
            {
                 if(conf.maLabExplorerFiltrar)
                 {
                    if( file.getName().toLowerCase().endsWith(".txt") )
                       {
                           DefaultMutableTreeNode childNode =
                              new DefaultMutableTreeNode(
                                 new DiskObject(type, file.getName(), file.getAbsolutePath()));
                                     node.add(childNode);
                       }
                 }
                 else
                 {
                         DefaultMutableTreeNode childNode =
                              new DefaultMutableTreeNode(
                                 new DiskObject(type, file.getName(), file.getAbsolutePath()));
                                     node.add(childNode);   
                 }
            }
          }
        }
        refreshTable();
      }
      else if(nodeInfo.type==JExplorer.FILE)
      {/*
          /*File dir = new File(nodeInfo.path);
          String Path = new String(dir.getAbsolutePath());
          String nome = new String(dir.getName());//f.getName(f.getSelectedFile()));
          MaLab.Janelas.TelaProgressao  Bar = new MaLab.Janelas.TelaProgressao(new JFrame(), Path,nome);
          Bar.start();*/
      }
    }
  }

  /** Atualiza a árvore de diretórios*/
  private void refreshTable() {
    MyTableModel model = (MyTableModel) table.getModel();
    model.setRowData();
    scrollPane2.repaint();
  }


  /** Assumindo que A: é o primeiro driver e C: é o driver do sistema, este método seta o driver inicial como C:*/
  private DefaultMutableTreeNode getRoot() {
    DefaultMutableTreeNode computerNode =
      new DefaultMutableTreeNode(
        new DiskObject(JExplorer.COMPUTER, "Meu Computador", ""));
    File[] drives = File.listRoots();
    int driveCount = drives.length;
    try {
      //Windows
      selectedDiskObjectName = drives[1].getAbsolutePath();
    }
    catch (Exception e) {
      //Linux/Unix 
      selectedDiskObjectName = drives[0].getAbsolutePath();
    }

    for (int i=0; i<driveCount; i++) {
      File drive = drives[i];
      int type = JExplorer.DRIVE;
      DiskObject child =
        new DiskObject(type, drive.getAbsolutePath(), drive.getAbsolutePath());
      DefaultMutableTreeNode driveNode =
        new DefaultMutableTreeNode(child);
      computerNode.add(driveNode);

      // adiciona diretórios e arquivos para cada driver
      File[] files = drive.listFiles();
      if (files!=null) {
        int fileCount = files.length;
        // adiciona diretórios
        for (int j=0; j<fileCount; j++) {
          type = JExplorer.DIRECTORY;
          File file = files[j];
          if (file.isDirectory())
            driveNode.add(new DefaultMutableTreeNode(
              new DiskObject(type, file.getName(), file.getAbsolutePath() )));
        }
        // adiciona arquivos
        if (displayFilesInTree) {
          for (int j=0; j<fileCount; j++) {
            type = JExplorer.FILE;
            File file = files[j];
            if (!file.isDirectory() )
            {
                if( file.getName().toLowerCase().endsWith(".txt") )
                     driveNode.add(new DefaultMutableTreeNode(
                     new DiskObject(type, file.getName(), file.getAbsolutePath() )));
            }
          }
        }
      }
    }
    return computerNode;
  }
  
  /** Evento gerado pelo click do mouse em um diretório. O método abre um objeto JPopupMenu com os itens de menu*/
  private void PDiretorioMouseClicked(java.awt.event.MouseEvent evt) {
         
         if(evt.getButton() == evt.BUTTON3 || evt.getButton() == evt.BUTTON2 ) {
            javax.swing.JPopupMenu pm = new javax.swing.JPopupMenu();
            pm.add(Carregar);
            pm.add(CarregarInicializacao);
            pm.show(evt.getComponent(),evt.getX(),evt.getY());
        }
        
    }
  Configuracoes conf;
}