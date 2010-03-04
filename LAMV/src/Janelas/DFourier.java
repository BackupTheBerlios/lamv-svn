/*
 * DFourier.java
 *
 * Created on 16 de Outubro de 2005, 16:22
 */

package Janelas;

import Dados.*;
import Ferramentas.Fourier;
import javax.swing.JOptionPane;
import java.awt.event.*;
import javax.swing.JTextField;
/**
 *
 * @author  anderson
 */
public class DFourier extends javax.swing.JDialog{
    
    /** Creates new form DFourier */
    public DFourier(java.awt.Frame parent, boolean modal) {
        super(parent,modal);
        initComponents();
        initVals();
        CBOperacao.addItem("Obter dom�nio da freq�encia (FFT)");
        CBOperacao.addItem("Obter magnitude (FFT)");
        CBOperacao.addItem("Obter dom�nio do tempo (IFFT)"); 
        this.setSize(508, 250);
    }
    public void initVals(){
        int tam = LAMV.LAMV.LDados.getnNos();
        if( tam == 0 ) {
            return;
        }
        CBMatrizes.removeAllItems();
        CBMatrizesComplexa.removeAllItems();
        CBMatrizes.addItem("-- Selecione --");
        CBMatrizesComplexa.addItem("-- Selecione -- ");
        Dado dado = LAMV.LAMV.LDados.getD(0);
        Dado _d;
        for( int c = 0; c < tam; c++ ) {
            _d = LAMV.LAMV.LDados.getD(c);
            if(_d.isComplexa())
                CBMatrizesComplexa.addItem( _d.getName() );
            else CBMatrizes.addItem( _d.getName() );
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        CBMatrizes = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CBOperacao = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        BOk = new javax.swing.JButton();
        BCancelar = new javax.swing.JButton();
        CBMatrizesComplexa = new javax.swing.JComboBox();

        getContentPane().setLayout(null);

        setTitle("Ferramentas de Fourier");
        jPanel1.setLayout(null);

        jPanel1.setBorder(new javax.swing.border.TitledBorder("Ferramentas de Fourier"));
        jPanel1.add(CBMatrizes);
        CBMatrizes.setBounds(10, 40, 190, 21);

        jLabel1.setText("Matriz de entrada(Real)");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(14, 20, 160, 15);

        jLabel2.setText("Matriz de entrada(Complexa)");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(244, 20, 180, 15);

        jPanel1.add(CBOperacao);
        CBOperacao.setBounds(10, 100, 190, 21);

        jLabel3.setText("Opera\u00e7\u00e3o");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(14, 80, 180, 15);

        BOk.setText("Calcular");
        BOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOkActionPerformed(evt);
            }
        });

        jPanel1.add(BOk);
        BOk.setBounds(240, 100, 90, 25);

        BCancelar.setText("Retornar");
        BCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelarActionPerformed(evt);
            }
        });

        jPanel1.add(BCancelar);
        BCancelar.setBounds(340, 100, 90, 25);

        jPanel1.add(CBMatrizesComplexa);
        CBMatrizesComplexa.setBounds(240, 40, 190, 21);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 480, 200);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void BCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCancelarActionPerformed
// TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_BCancelarActionPerformed

    private void BOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BOkActionPerformed
// TODO add your handling code here:
     Fourier F = new Fourier();
     Dado d = LAMV.LAMV.LDados.Procura( (String)CBMatrizes.getSelectedItem() );
     Matriz MEntrada = Sistema.FSys.Dado_to_Mat(d);
     d = LAMV.LAMV.LDados.Procura( (String)CBMatrizes.getSelectedItem() );
     Matriz MEntradaComplexa = Sistema.FSys.Dado_to_Mat(d);
     Matriz MSaidaReal = new Matriz(MEntrada.getDimL(),MEntrada.getDimC(),"Freq"+MEntrada.getName()+"_real");
     Matriz MSaidaImg = new Matriz(MEntrada.getDimL(),MEntrada.getDimC(),"Freq"+MEntrada.getName()+"_img");
     MSaidaImg.setComplexa(true);
     if( MEntrada != null )
     {
         if( CBOperacao.getSelectedItem().toString().equals("Obter dom�nio da freq�encia (FFT)") )
         {
                Complex[] y;
                for(int col = 0; col < MEntrada.getDimC(); col++ )
                {
                     Complex[] x = new Complex[MEntrada.getDimL()];
                     for (int i = 0; i < MEntrada.getDimL(); i++) {
                         x[i] = new Complex(MEntrada.M[i][col], 0);
                         System.out.println(x[i]);
                     }
                     y = F.fft(x);
                     for (int k = 0; k < MEntrada.getDimL(); k++)
                     {
                         MSaidaReal.M[k][col] = y[k].re;
                         MSaidaImg.M[k][col] = y[k].im;
                         System.out.println(y[k]);
                     }
                     Sistema.FSys.insereDadoSistema(MSaidaReal);
                     Sistema.FSys.insereDadoSistema(MSaidaImg);
                }
         }
         else if(CBOperacao.getSelectedItem().toString().equals("Obter dom�nio do tempo (IFFT)"))
         {
             d = LAMV.LAMV.LDados.Procura( (String)CBMatrizesComplexa.getSelectedItem() );
             if( d != null )
                 MEntradaComplexa = Sistema.FSys.Dado_to_Mat(d);
             else
                 MEntradaComplexa = null;
             for(int col = 0; col < MEntrada.getDimC(); col++ )
              {
                   Complex[] x = new Complex[MEntrada.getDimL()];
                   for (int i = 0; i < MEntrada.getDimL(); i++) 
                   {
                         if(MEntradaComplexa != null)
                             x[i] = new Complex(MEntrada.M[i][col], MEntradaComplexa.M[i][col]);
                         else
                             x[i] = new Complex(MEntrada.M[i][col], 0);
                   }
                   for (int i = 0; i < MEntrada.getDimL(); i++) 
                   {
                       System.out.println(x[i]);
                   }
                   Complex[] z = F.ifft(x);
                   for (int k = 0; k < MEntrada.getDimL(); k++)
                     {
                         MSaidaReal.M[k][col] = z[k].re;
                         MSaidaImg.M[k][col] = z[k].im;
                         System.out.println(z[k]);
                     }
                     Sistema.FSys.insereDadoSistema(MSaidaReal);
                     Sistema.FSys.insereDadoSistema(MSaidaImg);
              }
         }
         else if(CBOperacao.getSelectedItem().toString().equals("Obter magnitude (FFT)"))
         {
             Matriz M = new Matriz(MEntrada.getDimL(),MEntrada.getDimC(),"TesteMag");
             for(int col = 0 ; col < MEntrada.getDimC(); col++)
             {
                 double ve[] = F.fftMag(MEntrada.getColuna(col));
                 for(int k = 0 ; k < ve.length; k ++)
                 {
                    M.M[k][col] = ve[k];
                 }
                 Sistema.FSys.insereDadoSistema(M);
             }
         }
     }
    }//GEN-LAST:event_BOkActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCancelar;
    private javax.swing.JButton BOk;
    private javax.swing.JComboBox CBMatrizes;
    private javax.swing.JComboBox CBMatrizesComplexa;
    private javax.swing.JComboBox CBOperacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    
}
