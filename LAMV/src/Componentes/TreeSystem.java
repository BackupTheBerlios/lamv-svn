/*
 * TabelaMatrizes.java
 *
 * Created on 28 de Janeiro de 2005, 22:41
 */

package Componentes;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Enumeration;
import java.util.Arrays;
import javax.swing.tree.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author  Gustavo
 */

public class TreeSystem extends javax.swing.JTree {
    public TreeSystem() {
        super();
        TreeModel model = iniModel();
        Modelo = iniModel();
        this.setModel( Modelo );
        iniEventos();
    }

    private TreeModel iniModel() {
        File f = fsv.getRoots()[0];
        raiz = getFS(f);
        return new DefaultTreeModel( raiz, true );
    }

    private DefaultMutableTreeNode getFS( File f ) {
        No r = new No(f);//DefaultMutableTreeNode r = new DefaultMutableTreeNode( f.getName() ) ;
        No filho;//DefaultMutableTreeNode filho;
        File [] arquivos = fsv.getFiles( f , true );
        Arrays.sort(arquivos);
        for( int c = 0; c < arquivos.length; c++ ) {
            filho = new No( arquivos[c], true);//filho = new DefaultMutableTreeNode( makeName( arquivos[c] ) , true );
            if( arquivos[c].isDirectory() ) {
                r.add(filho);
            }
        }
        for( int c = 0; c < arquivos.length; c++ ) {
            filho = new No( arquivos[c], true);//filho = new DefaultMutableTreeNode( makeName( arquivos[c] )  , true );
            if( arquivos[c].isFile() ) {
                filho.setAllowsChildren(false);
                r.add(filho);
            }
        }
        return r;
    }
    
    private String makeName( File f ) {
        String nome = fsv.getSystemDisplayName(f);
        return nome;
    }
    
    private void iniEventos() {
        this.addTreeExpansionListener(
                new TreeExpansionListener() {
                    public void treeExpanded(TreeExpansionEvent event) {
                        System.out.println("Expandiu");
                        TreePath p = event.getPath();
                        System.out.println(event.toString());
                        //File F = new File( p.getParentPath().getLastPathComponent().toString() );

                        //System.out.println( p
                        //p.getLastPathComponent(); = getFS( new File("asd") );

                        System.out.println(p.toString());
//                        TreePath dd = getLeadSelectionPath();
//                        System.out.println( getLastSelectedPathComponent().toString()  );
                    }

                    public void treeCollapsed(TreeExpansionEvent event) {
                        System.out.println("Recolheu");
                    }
        });
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                
            }
        });
    }
    
    private class No extends DefaultMutableTreeNode {

        public No( File f ) {
            super( makeName(f) );
            Caminho = f.getPath();
        }
        public No( File f, boolean b ){
            super( makeName(f), b);
            Caminho = f.getPath();
        }
        public String getCaminho() {
            return Caminho;
        }

        private String Caminho;
    }
    
    FileSystemView fsv = FileSystemView.getFileSystemView();
    DefaultMutableTreeNode raiz;
    private TreeModel Modelo;
}