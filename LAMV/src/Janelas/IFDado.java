/*
 * IFDado.java
 *
 * Created on 4 de Mar�o de 2005, 18:52
 */

package Janelas;

import LAMV.LAMV;
import Dados.*;
import Sistema.*;
import java.beans.PropertyVetoException;
import javax.swing.event.ChangeEvent;
import java.awt.event.*;
import javax.swing.table.TableCellEditor;
import java.text.DecimalFormat;
import Componentes.myRendererTable;
/**
 *
 * @author  Gustavo
 */
public class IFDado extends javax.swing.JInternalFrame {
    
    /** Creates new form IFDado */
    public IFDado() {
        initComponents();
        
    }
    public IFDado( Configuracoes conf, Dado d ) {
        this();
        initComponents();
        D = d;
        this.configuracoes = conf;
        setTitle( d.getName() );
        MyTable t = new MyTable(configuracoes, d );
        Scroll.setViewportView( t );
        
        java.awt.Dimension dimt = Scroll.getPreferredSize();
        java.awt.Dimension dimb = Barra.getPreferredSize();
        java.awt.Rectangle dimcel = t.getCellRect(0,0,true);
        
        java.awt.Rectangle rect = t.getBounds();
        
        //this.setSize();
        
        
//        int largura = 100 + 100*d.getDimC();
//        int altura = 30 + 30*d.getDimL();
//        if(largura > 700 )
//            largura = 500;
//        System.out.println("LARGURA: "+largura);
//        System.out.println("ALTURA: "+altura);
//        this.setSize(largura,altura);
        
        this.setSize(500,500);
        
        //this.firePropertyChange(javax.swing.JInternalFrame.IS_MAXIMUM_PROPERTY, false ,true);
        
        this.show();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" C�digo Gerado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        PopUp = new javax.swing.JPopupMenu();
        Scroll = new javax.swing.JScrollPane();
        Barra = new javax.swing.JToolBar();
        BSalvar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().add(Scroll, java.awt.BorderLayout.CENTER);

        Barra.setFloatable(false);
        Barra.setPreferredSize(new java.awt.Dimension(47, 23));
        BSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/salvar.gif")));
        BSalvar.setToolTipText("Salvar");
        BSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSalvarActionPerformed(evt);
            }
        });

        Barra.add(BSalvar);

        getContentPane().add(Barra, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void BSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSalvarActionPerformed
        // TODO add your handling code here:
        leDisco gravar = new leDisco(configuracoes);
        gravar.grava(FSys.Dado_to_Mat(D));
    }//GEN-LAST:event_BSalvarActionPerformed
    
    // Declara��o de vari�veis - n�o modifique//GEN-BEGIN:variables
    private javax.swing.JButton BSalvar;
    private javax.swing.JToolBar Barra;
    private javax.swing.JPopupMenu PopUp;
    private javax.swing.JScrollPane Scroll;
    // Fim da declara��o de vari�veis//GEN-END:variables
    private Configuracoes configuracoes;
    private Dado D;
}

class MyTable extends javax.swing.JTable {
    int[][] posicao=new int[2][2];
    public MyTable(){
    }
    public MyTable( Configuracoes conf, Dado d ) {
        super( d.getDimL()+1, d.getDimC()+1 );
        this.setDefaultRenderer(Object.class, renderer);
        this.setTableHeader(null);
        setColumnSelectionAllowed(true);
        setRowSelectionAllowed(true);
        setAutoResizeMode(AUTO_RESIZE_OFF);
        setRowHeight( 20 );
        configuracoes = conf;
        this.D = d;
        
        this.setSelectionBackground( new java.awt.Color( 230, 230, 255 ) );
        
        String valor;
        
        if( d.eMatriz() ) {
            Matriz M = (Matriz)d;
            for( int l = 0; l < M.getnLinhas(); l++ ) {
                for( int c = 0; c < M.getnColunas(); c++ ) {
                    valor = (String)form3.format( M.M[l][c] );
                    valor = valor.replace(',', '.');
                    if(configuracoes.usarNotacaoCientifica) {
                        //if(M.M[l][c] != 0)
                        //System.out.println(M.M[l][c]);
                        setValueAt( FSys.NotacaoCientifica(M.M[l][c]) ,l+1,c+1 );
                    } else
                        setValueAt( ""+M.M[l][c] ,l+1,c+1 );
                }
            }
        } else {
            if( d.eVetor() ) {
                Vetor V = (Vetor)d;
                for( int c = 0; c < V.getTam(); c++ ) {
                    valor = (String)form3.format( V.getValor(c) );
                    valor = valor.replace(',', '.');
                    setValueAt(FSys.NotacaoCientifica(V.getValor(c)), c+1, 1 );
                }
            } else {
                Constante C = (Constante)d;
                valor = (String)form3.format( C.getValor() );
                valor = valor.replace(',', '.');
                setValueAt( FSys.NotacaoCientifica(C.getValor()) ,1,1 );
            }
        }
        
        if( D.getNomeLinhas() == null ) {
            D.setNomeLinhas( geraNomes( D.getDimL(), "Linha: ", "" ) );
        }
        if( D.getNomeColunas() == null ) {
            D.setNomeColunas( geraNomes( D.getDimC(), "Coluna: ", "" ) );
        }
        
        for( int c = 0; c < D.getDimC(); c++ ) {
            setValueAt( D.getNomeColunas(c), 0, c+1 );
        }
        for( int c = 0; c < D.getDimL(); c++ ) {
            setValueAt( D.getNomeLinhas(c), c+1, 0 );
        }
        
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int col = getSelectedColumn();
                int lin = getSelectedRow();
                atualizalc(0,0,lin,col);
                
                if( col == 0 && lin != 0 ) {
                    setColumnSelectionAllowed(false);
                    setRowSelectionAllowed(true);
                    //setSelectedRow( lin );
                } else {
                    if( lin == 0 && col != 0 ) {
                        setRowSelectionAllowed(false);
                        setColumnSelectionAllowed(true);
                        //setSelectedColumn( col );
                    } else {
                        setColumnSelectionAllowed(true);
                        setRowSelectionAllowed(true);
                    }
                }
            }
            
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
            }
            public void mouseReleased(MouseEvent evt){
                int lin=getSelectedRow();
                int col=getSelectedColumn();
                atualizalc(1,0,lin,col);
            }
        });
    }
    
    public String [] geraNomes( int t, String pre, String suf ) {
        String [] n = new String[t];
        for( int c = 0; c < t; c++ )
            n[c] = pre+c+suf;
        return n;
    }
    // -- Tratar a copia de uma matriz
    public void copyTable() {
        new MyTable(configuracoes,D);
    }
    public void atualizalc(int i,int j,int lin,int col) {
        posicao[i][j]=lin;
        posicao[i][j+1]=lin;
    }
    
    public void editingStopped(ChangeEvent e) {
        // Take in the new value
        
        TableCellEditor editor = getCellEditor();
        if (editor != null) {
            int col = getSelectedColumn();
            int lin = getSelectedRow();
            
            
            Object value = editor.getCellEditorValue();
            if( value == null || value == "" )
                value = "0.0";
            setValueAt(value, editingRow, editingColumn);
            removeEditor();
            
            String temp = value.toString();
            if( temp == null || temp == "" )
                temp = "0.0";
            temp = temp.replace(',','.');
            
            if( col == 0 ) { /* Editanto nome das linhas */
                D.setNomeLinhas( temp, lin-1 );
                return;
            }
            if( lin == 0 ) { /* Editando nome das colunas */
                D.setNomeColunas( temp, col-1 );
                return;
            }
            
            if( D.eMatriz() ) {
                Matriz M = (Matriz)D;
                M.M[lin-1][col-1] = Double.valueOf(temp).doubleValue();
            } else {
                if( D.eVetor() ) {
                    Vetor V = (Vetor)D;
                    V.setValor( lin-1, Double.valueOf(temp).doubleValue() );
                } else {
                    /* � Constante */
                    Constante C = (Constante)D;
                    C.setValor( Double.valueOf(temp).doubleValue() );
                }
            }
        }
    }
    
    private Configuracoes configuracoes;
    private Dado D;
    private DecimalFormat form3 = new DecimalFormat("0.000");
    myRendererTable renderer = new myRendererTable();
}