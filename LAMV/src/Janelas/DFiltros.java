/*
 * DFiltros.java
 *
 * Created on 22 de Agosto de 2005, 17:13
 */

package Janelas;

import Dados.*;
import Ferramentas.Algebra;
/**
 *
 * @author  EngSoares
 */
public class DFiltros extends javax.swing.JDialog {
    
    /** Cria um novo form DFiltros */
    public DFiltros(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initVals();
        this.setSize(595,195);
    }
    
    public void initVals()
    {
        int tam = LAMV.LAMV.LDados.getnNos();
        String nome;
        Dados.Dado d;
        CBMatrizes.removeAllItems();
        CBMetodos.removeAllItems();
        CBMatrizes.addItem("-- Selecione --");
        for( int c = 0; c < tam; c++ ) {
            d = LAMV.LAMV.LDados.getD(c);
            if( d== null )
                break;
            nome = d.getName();
            CBMatrizes.addItem( nome );
        }
        CBMetodos.addItem("-- Selecione --");
        CBMetodos.addItem("Média móvel");
        CBMetodos.addItem("Savitzky-Golay");
        CBPontos.setEnabled(false);
    }
    
    /** Este método é chamado na inicialização do construtor para criar uma nova 
     * instância dos objetos da classe
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        CBMetodos = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        JFiltrosEstatisticos = new javax.swing.JButton();
        TFNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        CBPontos = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        BCancelar = new javax.swing.JButton();
        CBMatrizes = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(null);

        jPanel2.setLayout(null);

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Filtros estat\u00edsticos"));
        CBMetodos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBMetodosItemStateChanged(evt);
            }
        });
        CBMetodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBMetodosActionPerformed(evt);
            }
        });

        jPanel2.add(CBMetodos);
        CBMetodos.setBounds(170, 40, 120, 19);

        jLabel1.setText("Selecione:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(170, 20, 120, 15);

        JFiltrosEstatisticos.setText("Filtrar");
        JFiltrosEstatisticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFiltrosEstatisticosActionPerformed(evt);
            }
        });

        jPanel2.add(JFiltrosEstatisticos);
        JFiltrosEstatisticos.setBounds(480, 40, 80, 23);

        TFNome.setText("Digite aqui...");
        jPanel2.add(TFNome);
        TFNome.setBounds(320, 40, 130, 20);

        jLabel2.setText("Nome da nova matriz");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(320, 20, 140, 15);

        jPanel2.add(CBPontos);
        CBPontos.setBounds(20, 100, 70, 19);

        jLabel3.setText("Dimens\u00e3o da janela");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(20, 80, 140, 15);

        BCancelar.setText("Cancelar");
        BCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelarActionPerformed(evt);
            }
        });

        jPanel2.add(BCancelar);
        BCancelar.setBounds(480, 100, 80, 23);

        jPanel2.add(CBMatrizes);
        CBMatrizes.setBounds(20, 40, 130, 19);

        jLabel4.setText("Matriz de entrada:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 20, 110, 15);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 10, 570, 140);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void JFiltrosEstatisticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JFiltrosEstatisticosActionPerformed
        // TODO add your handling code here:       
        if(CBMetodos.getSelectedItem().toString().equals("Savitzky-Golay"))
        {
            Dado d = LAMV.LAMV.LDados.Procura( (String)CBMatrizes.getSelectedItem() );
            Matriz M = Sistema.FSys.Dado_to_Mat(d);
            Ferramentas.filtro_golay fg = new Ferramentas.filtro_golay(M,3,11);
                
        }
        return;/*
        Ferramentas.FiltrosEstatisticos filtro = new Ferramentas.FiltrosEstatisticos();
        Dado d = LAMV.LAMV.LDados.Procura( (String)CBMatrizes.getSelectedItem() );
        Matriz MEntrada = Sistema.FSys.Dado_to_Mat(d);
        CBPontos.getSelectedItem().toString();
        Integer i = new Integer( CBPontos.getSelectedItem().toString() );
        Algebra Alg = new Algebra();
        Matriz filtrada = null;
 
        //Filtra a primeira coluna
        try
        {
             //Matriz do tipo 1x300
             if(MEntrada.getDimL() <= 1 )
             {
                 MEntrada = Alg.Transposta(MEntrada);
                 //LAMV.System.FSys.Msg("Erro", "Número de linhas insuficiente para realizar a operação", 0);
                 //return;
             }
             Matriz temp = Alg.RecortM(MEntrada, 0, 0, MEntrada.getDimL()-1, 0);
             if(temp != null)
             {
                 filtrada = filtro.mediaMovel(Alg.Transposta(temp) , i.intValue() );
             }   
             else
             {
                 System.out.println("Está null");
             }     
             
        }
        catch(Exception e)
        {
            Sistema.FSys.Msg("Erro", e.getMessage(), 0);
        }
        if(filtrada != null)
        {
                for( int k = 1; k < MEntrada.getDimC() && MEntrada.getDimL() > 1; k++ )
                  {
                     try
                     {
                        filtrada = Alg.ConcatDireita(Alg.Transposta(filtrada), Alg.Transposta(filtro.mediaMovel( Alg.Transposta(Alg.RecortM(MEntrada, 0, k, MEntrada.getDimL()-1, k)), i.intValue())));               
                     }
                     catch(Exception e)
                     {
                        Sistema.FSys.Msg("Erro", e.getMessage(), 0);
                     }
                }
        }
        if(filtrada != null)
        {
            //Usuário digitou o nome, conteúdo do Text Field foi alterado
            if(!TFNome.equals("Digite aqui..."))
            {
                filtrada.setName(TFNome.getText());
            }
            else
            {
                filtrada.setName(TFNome.getText());
            }
            Sistema.FSys.insereDadoSistema(filtrada);
        }*/
    }//GEN-LAST:event_JFiltrosEstatisticosActionPerformed

    private void CBMetodosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBMetodosItemStateChanged
    // TODO add your handling code here:  
    }//GEN-LAST:event_CBMetodosItemStateChanged

    private void BCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCancelarActionPerformed
    // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BCancelarActionPerformed

    private void CBMetodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBMetodosActionPerformed
    // TODO add your handling code here:
        if(CBMetodos.getSelectedItem() != null)
        {
        /*if(CBMetodos.getSelectedItem().toString().equals("Média móvel"))
        {
            CBPontos.setEnabled(true);
            Dado d = LAMV.LAMV.LDados.Procura( (String)CBMatrizes.getSelectedItem() );
            Matriz M = Sistema.FSys.Dado_to_Mat(d);
            CBPontos.addItem("5");
            for(int k = 10; k < M.getDimL()/5; k+=5 )
            {
                  CBPontos.addItem(""+k);
            }
        }
        else
        {*/
            CBPontos.setEnabled(false);
        //}
        }
    }//GEN-LAST:event_CBMetodosActionPerformed
    
    /** Fecha o Form */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCancelar;
    private javax.swing.JComboBox CBMatrizes;
    private javax.swing.JComboBox CBMetodos;
    private javax.swing.JComboBox CBPontos;
    private javax.swing.JButton JFiltrosEstatisticos;
    private javax.swing.JTextField TFNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
    
}
