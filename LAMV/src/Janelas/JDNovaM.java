/*
 * NovaM.java
 *
 * Created on 28 de Janeiro de 2005, 12:19
 */

package Janelas;

import Dados.Dado;
import Dados.Matriz;
import Dados.Vetor;
import Dados.Constante;
import Sistema.FSys;

/**
 *
 * @author  Gustavo
 */
public class JDNovaM extends javax.swing.JDialog {
    
    /**
     * 
     * Creates new form NovaM
     */
    public JDNovaM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        newD = null;
        initComponents();
        this.setSize( 515, 235 );
        this.setTitle("Novo dado");
        TAValor.setBackground( new java.awt.Color(200,200,200) );
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        BGrupoTipo = new javax.swing.ButtonGroup();
        BGrupoDado = new javax.swing.ButtonGroup();
        PValor = new javax.swing.JPanel();
        SPValor = new javax.swing.JScrollPane();
        TAValor = new javax.swing.JTextArea();
        PParanetros = new javax.swing.JPanel();
        PTipo = new javax.swing.JPanel();
        RBNula = new javax.swing.JRadioButton();
        RBAleatoria = new javax.swing.JRadioButton();
        RBIdentidade = new javax.swing.JRadioButton();
        RBUns = new javax.swing.JRadioButton();
        RBCustom = new javax.swing.JRadioButton();
        PParametros = new javax.swing.JPanel();
        TFNome = new javax.swing.JTextField();
        TFColunas = new javax.swing.JTextField();
        TFLinhas = new javax.swing.JTextField();
        BCria = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PDado = new javax.swing.JPanel();
        RBMatriz = new javax.swing.JRadioButton();
        RBVetor = new javax.swing.JRadioButton();
        RBConstante = new javax.swing.JRadioButton();
        BSairM = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo dado");
        setModal(true);
        PValor.setLayout(new java.awt.BorderLayout());

        PValor.setBorder(new javax.swing.border.TitledBorder("Valor"));
        PValor.setMinimumSize(new java.awt.Dimension(100, 2));
        PValor.setPreferredSize(new java.awt.Dimension(150, 20));
        TAValor.setDragEnabled(true);
        TAValor.setEnabled(false);
        SPValor.setViewportView(TAValor);

        PValor.add(SPValor, java.awt.BorderLayout.CENTER);

        getContentPane().add(PValor, java.awt.BorderLayout.CENTER);

        PParanetros.setLayout(null);

        PParanetros.setPreferredSize(new java.awt.Dimension(330, 0));
        PTipo.setLayout(null);

        PTipo.setBorder(new javax.swing.border.TitledBorder("Tipo"));
        BGrupoTipo.add(RBNula);
        RBNula.setSelected(true);
        RBNula.setText("Nula");
        RBNula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBNulaActionPerformed(evt);
            }
        });

        PTipo.add(RBNula);
        RBNula.setBounds(10, 20, 90, 23);

        BGrupoTipo.add(RBAleatoria);
        RBAleatoria.setText("Aleat\u00f3ria");
        RBAleatoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBAleatoriaActionPerformed(evt);
            }
        });

        PTipo.add(RBAleatoria);
        RBAleatoria.setBounds(100, 20, 80, 23);

        BGrupoTipo.add(RBIdentidade);
        RBIdentidade.setText("Identidade");
        RBIdentidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBIdentidadeActionPerformed(evt);
            }
        });

        PTipo.add(RBIdentidade);
        RBIdentidade.setBounds(10, 40, 90, 23);

        BGrupoTipo.add(RBUns);
        RBUns.setText("Uns");
        RBUns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBUnsActionPerformed(evt);
            }
        });

        PTipo.add(RBUns);
        RBUns.setBounds(100, 40, 80, 23);

        BGrupoTipo.add(RBCustom);
        RBCustom.setText("Custom");
        RBCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBCustomActionPerformed(evt);
            }
        });

        PTipo.add(RBCustom);
        RBCustom.setBounds(10, 60, 100, 23);

        PParanetros.add(PTipo);
        PTipo.setBounds(130, 0, 195, 90);

        PParametros.setLayout(null);

        PParametros.setBorder(new javax.swing.border.TitledBorder("Par\u00e2metros"));
        TFNome.setText("Dado");
        TFNome.setToolTipText("Nome do novo dado");
        TFNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TFNomeFocusGained(evt);
            }
        });

        PParametros.add(TFNome);
        TFNome.setBounds(125, 50, 100, 19);

        TFColunas.setText("3");
        TFColunas.setToolTipText("Colunas");
        TFColunas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TFColunasFocusGained(evt);
            }
        });

        PParametros.add(TFColunas);
        TFColunas.setBounds(176, 28, 49, 19);

        TFLinhas.setText("3");
        TFLinhas.setToolTipText("Linhas");
        TFLinhas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TFLinhasFocusGained(evt);
            }
        });

        PParametros.add(TFLinhas);
        TFLinhas.setBounds(125, 28, 49, 19);

        BCria.setText("Criar");
        BCria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCriaActionPerformed(evt);
            }
        });

        PParametros.add(BCria);
        BCria.setBounds(10, 30, 110, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 11));
        jLabel1.setText("Linhas:");
        PParametros.add(jLabel1);
        jLabel1.setBounds(125, 15, 50, 14);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 11));
        jLabel2.setText("Colunas:");
        PParametros.add(jLabel2);
        jLabel2.setBounds(175, 15, 60, 14);

        PParanetros.add(PParametros);
        PParametros.setBounds(10, 90, 240, 80);

        PDado.setLayout(null);

        PDado.setBorder(new javax.swing.border.TitledBorder("Dado"));
        BGrupoDado.add(RBMatriz);
        RBMatriz.setSelected(true);
        RBMatriz.setText("Matriz");
        RBMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBMatrizActionPerformed(evt);
            }
        });

        PDado.add(RBMatriz);
        RBMatriz.setBounds(10, 20, 90, 23);

        BGrupoDado.add(RBVetor);
        RBVetor.setText("Vetor");
        RBVetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBVetorActionPerformed(evt);
            }
        });

        PDado.add(RBVetor);
        RBVetor.setBounds(10, 40, 100, 23);

        BGrupoDado.add(RBConstante);
        RBConstante.setText("Constante");
        RBConstante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBConstanteActionPerformed(evt);
            }
        });

        PDado.add(RBConstante);
        RBConstante.setBounds(10, 60, 100, 23);

        PParanetros.add(PDado);
        PDado.setBounds(10, 0, 120, 90);

        BSairM.setText("Sair");
        BSairM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSairMActionPerformed(evt);
            }
        });

        PParanetros.add(BSairM);
        BSairM.setBounds(255, 100, 70, 70);

        getContentPane().add(PParanetros, java.awt.BorderLayout.WEST);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void RBCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBCustomActionPerformed
        // TODO add your handling code here:
        this.TIPO = CUSTOM;
        TAValor.setEnabled(true);
        TFLinhas.setEnabled(false);
        TFColunas.setEnabled(false);
        TAValor.setBackground( java.awt.Color.WHITE );
    }//GEN-LAST:event_RBCustomActionPerformed

    private void RBConstanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBConstanteActionPerformed
        // TODO add your handling code here:
        TFLinhas.setText("1");
        TFColunas.setText("1");
        TFLinhas.setEnabled(false);
        TFColunas.setEnabled(false);
        RBIdentidade.setEnabled(false);
    }//GEN-LAST:event_RBConstanteActionPerformed

    private void RBVetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBVetorActionPerformed
        // TODO add your handling code here:
        TFLinhas.setEnabled(true);
        TFColunas.setText("1");
        TFColunas.setEnabled(false);
        RBIdentidade.setEnabled(false);
    }//GEN-LAST:event_RBVetorActionPerformed

    private void RBMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBMatrizActionPerformed
        // TODO add your handling code here:
        TFLinhas.setEnabled(true);
        TFColunas.setEnabled(true);
        RBIdentidade.setEnabled(true);
    }//GEN-LAST:event_RBMatrizActionPerformed

    private void RBUnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBUnsActionPerformed
        // TODO add your handling code here:
        this.TIPO = Dado.UNS;
        TAValor.setEnabled(false);
        TFLinhas.setEnabled(true);
        TFColunas.setEnabled(true);
        TAValor.setBackground( new java.awt.Color(200,200,200) );
    }//GEN-LAST:event_RBUnsActionPerformed

    private void RBAleatoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBAleatoriaActionPerformed
        // TODO add your handling code here:
        this.TIPO = Dado.ALEATORIO;
        TAValor.setEnabled(false);
        TFLinhas.setEnabled(true);
        TFColunas.setEnabled(true);
        TAValor.setBackground( new java.awt.Color(200,200,200) );
    }//GEN-LAST:event_RBAleatoriaActionPerformed

    private void RBIdentidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBIdentidadeActionPerformed
        // TODO add your handling code here:
        this.TIPO = Dado.IDENTIDADE;
        TAValor.setEnabled(false);
        TFLinhas.setEnabled(true);
        TFColunas.setEnabled(true);
        TAValor.setBackground( new java.awt.Color(200,200,200) );
    }//GEN-LAST:event_RBIdentidadeActionPerformed

    private void RBNulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBNulaActionPerformed
        // TODO add your handling code here:
        this.TIPO = Dado.NULO;
        TAValor.setEnabled(false);
        TFLinhas.setEnabled(true);
        TFColunas.setEnabled(true);
        TAValor.setBackground( new java.awt.Color(200,200,200) );
    }//GEN-LAST:event_RBNulaActionPerformed

    private void BCriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCriaActionPerformed
        // TODO add your handling code here:
        if( RBMatriz.isSelected() ) {
            if( getParametros() ) {
                if( TIPO != CUSTOM ) {
                    newD = new Matriz( lins, cols, nome, TIPO );
                    if( newD != null ) {
                        if ( !FSys.insereDadoSistema(newD) ) {
                            FSys.Msg( new javax.swing.JPanel(), "Erro", "N�o foi poss�vel inserir o dado.", 1 );
                        }
                    }
                    //this.dispose();
                } else {
                    //...
                }
            }
        } else {
            if( RBVetor.isSelected() ) {
                if( getParametros() ) {
                    if( TIPO != CUSTOM ) {
                        newD = new Vetor( lins, nome, TIPO );
                        //this.dispose();
                        if( !FSys.insereDadoSistema(newD) ) {
                            FSys.Msg("Erro", "N�o foi poss�vel inserir o dado.",1);
                        }
                    }
                } else {
                    //...
                }
            } else { // RBConstante Selecionado
                if( getParametros() ) {
                    if( TIPO != CUSTOM ) {
                        newD = new Constante( nome, TIPO );
                        //this.dispose();
                        if( !FSys.insereDadoSistema(newD) ) {
                            FSys.Msg("Erro", "N�o foi poss�vel inserir o dado.",1);
                        }
                    }
                } else {
                    //...
                }
            }
        }
    }//GEN-LAST:event_BCriaActionPerformed

    private void TFNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFNomeFocusGained
        // TODO add your handling code here:
        this.TFNome.selectAll();
    }//GEN-LAST:event_TFNomeFocusGained

    private void TFColunasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFColunasFocusGained
        // TODO add your handling code here:
        this.TFColunas.selectAll();
    }//GEN-LAST:event_TFColunasFocusGained

    private void TFLinhasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFLinhasFocusGained
        // TODO add your handling code here:
        this.TFLinhas.selectAll();
    }//GEN-LAST:event_TFLinhasFocusGained

    private void BSairMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSairMActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BSairMActionPerformed

    /* -- M�todos de controle -- */
    private boolean getParametros() {
        lins  = java.lang.Integer.valueOf(TFLinhas.getText()).intValue();
        try {// L� o valor de linhas e colunas nas caixas de texto
            lins  = java.lang.Integer.valueOf(TFLinhas.getText()).intValue();
            cols  = java.lang.Integer.valueOf(TFColunas.getText()).intValue();
        } catch(NumberFormatException e) {
            FSys.Msg(new javax.swing.JPanel(),"Erro","N�mero de linhas ou colunas inv�lido!",0);
            lins = 0;
            cols = 0;
            nome = null;
            return false;
        }
        if( lins == 0 || cols == 0 ) {
            FSys.Msg(new javax.swing.JPanel(),"Erro","Dimens�o incorreta! Digite um n�mero diferente de 0 (zero)!",0);
            lins = 0;
            cols = 0;
            nome = null;
            return false;            
        }
        nome = TFNome.getText();
        if( !FSys.verificaNome(nome) ) {
            FSys.Msg(new javax.swing.JPanel(),"Erro","Nome inv�lido para o dado! Retire os espa�os do nome da Matriz!",0);
            return false;
        }
        return true;
    }
    
    public Dado getDado() {
        return newD;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCria;
    private javax.swing.ButtonGroup BGrupoDado;
    private javax.swing.ButtonGroup BGrupoTipo;
    private javax.swing.JButton BSairM;
    private javax.swing.JPanel PDado;
    private javax.swing.JPanel PParametros;
    private javax.swing.JPanel PParanetros;
    private javax.swing.JPanel PTipo;
    private javax.swing.JPanel PValor;
    private javax.swing.JRadioButton RBAleatoria;
    private javax.swing.JRadioButton RBConstante;
    private javax.swing.JRadioButton RBCustom;
    private javax.swing.JRadioButton RBIdentidade;
    private javax.swing.JRadioButton RBMatriz;
    private javax.swing.JRadioButton RBNula;
    private javax.swing.JRadioButton RBUns;
    private javax.swing.JRadioButton RBVetor;
    private javax.swing.JScrollPane SPValor;
    private javax.swing.JTextArea TAValor;
    private javax.swing.JTextField TFColunas;
    private javax.swing.JTextField TFLinhas;
    private javax.swing.JTextField TFNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
    private int TIPO = Dado.NULO;
    private static int CUSTOM = -1;
    private Dado newD;
    private int lins;
    private int cols;
    private String nome;
}
