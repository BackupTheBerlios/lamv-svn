/*
 * DCalibra.java
 *
 * Created on 5 de Abril de 2005, 19:56
 */

package Janelas;

import Dados.*;
import Ferramentas.reamostragem.Bagging;
import javax.swing.JOptionPane;
import java.awt.event.*;
import javax.swing.JTextField;
/**
 *
 * @author  Barnes
 */

/**Esta classe monta exibe a tela de op��es de processamento na calibra��o multivariada*/
public class DCalibra extends javax.swing.JDialog {
    
    /** Creates new form DCalibra */
    public DCalibra(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initVals();
        CBCal.addItem("Regress�o Linear M�ltipla");
        CBCal.addItem("Regress�o Linear M�ltipla - Bagging");        
//        CBCal.addItem("Regress�o Linear M�ltipla - Subagging");                
        CBCal.addItem("Regress�o em Componentes Principais");
        CBCal.addItem("Regress�o em Minimos Quadrados Parciais");
        this.setSize( 610, 500 );
    }
    public void initVals() {
        int tam = LAMV.LAMV.LDados.getnNos();
        String nome;
        Dado d;
        CBCoeficientes.removeAllItems();
        CBMatTestX.removeAllItems();
        CBMatTestY.removeAllItems();
        CBMatCalX.removeAllItems();
        CBMatCalY.removeAllItems();
        CBFiltros.removeAllItems();
        CBCoeficientes.addItem("N�o Validar");
        CBMatTestX.addItem("N�o Validar");
        CBMatTestY.addItem("N�o Validar");
        CBMatCalX.addItem("N�o Calibrar");
        CBMatCalY.addItem("N�o Calibrar");
        for( int c = 0; c < tam; c++ ) {
            d = LAMV.LAMV.LDados.getD(c);
            if( d== null )
                break;
            nome = d.getName();
            CBMatTestX.addItem( nome );
            CBMatTestY.addItem( nome );
            CBMatCalX.addItem( nome );
            CBMatCalY.addItem( nome );
            CBCoeficientes.addItem( nome );
        }
        
        CBFiltros.addItem("Nenhum Filtro");
        CBFiltros.addItem("Filtro 1");
        CBFiltros.addItem("Filtro 2");
        CBFiltros.addItem("Filtro 3");
     }
    
    /**Atualiza o combobox de escolha da quantidade de analitos. Este m�todo � chamado todas as vezes que as matrizes
     de calibra��o s�o alteradas pelo usu�rio*/
    private void atualizaAnalitos() {
        Dado d;
        int tam;
        CBAnalito.removeAllItems();
        d = LAMV.LAMV.LDados.Procura( (String)CBCoeficientes.getSelectedItem() );
        if( d == null ) {
            //pode ser matriz de coeficientes que ainda n�o foi gerada
            Object Nome = CBMatCalY.getSelectedItem();
            if(Nome != null) {
                d = LAMV.LAMV.LDados.Procura(Nome.toString());
                if(d == null)
                    return;//n�o �...
            } else return;
        }
        tam = d.getDimC();
        CBAnalito.addItem("Todos");
        for( int c = 0; c < tam; c++ ) {
            CBAnalito.addItem(""+c);
        }
    }
    
   /**Este m�todo � chamado quando o m�todo de calibra��o escolhido � a regress�o linear m�ltipla. O m�todo
    atualiza o combobox de quantidade de componentes principais para o c�lculo de acordo com as matrizes de calibra��o
    escolhida*/
   private void atualizaQtdComponentes() {
        Dado d;
        int tam;
        CBQtdComponentes.removeAllItems();
        d = LAMV.LAMV.LDados.Procura( (String)CBMatCalX.getSelectedItem() );
        if( d == null ) {
            //pode ser matriz de coeficientes que ainda n�o foi gerada
            Object Nome = CBMatCalY.getSelectedItem();
            if(Nome != null) {
                d = LAMV.LAMV.LDados.Procura(Nome.toString());
                if(d == null)
                    return;//n�o �...
            } else return;
        }
        tam = d.getDimL();
        CBAnalito.addItem("Todos");
        for( int c = 1; c <= tam; c++ ) {
            CBQtdComponentes.addItem(""+c);
        }
        CBQtdComponentes.addItem("Testar todos");
    }

    /** Este m�todo � chamdo pelo construtor do form. Instancia todos os objetos necess�rios a classe*/
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        PCalibra = new javax.swing.JPanel();
        CBMatCalY = new javax.swing.JComboBox();
        CBMatCalX = new javax.swing.JComboBox();
        LXo1 = new javax.swing.JLabel();
        LYo1 = new javax.swing.JLabel();
        TFNomeCoeficientes = new javax.swing.JTextField();
        TFNomeCoeficientes.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField)e.getSource();
                Dados.Dado d;
                d = LAMV.LAMV.LDados.Procura( (String)CBCoeficientes.getSelectedItem() );
                if(d == null)
                {
                    CBCoeficientes.addItem(textField.getText());
                }
                else
                {
                    CBCoeficientes.addItem(textField.getText()+"_1");
                }
            }
        });
        LabelNomeCoeficientes = new javax.swing.JLabel();
        PValida = new javax.swing.JPanel();
        CBMatTestY = new javax.swing.JComboBox();
        CBMatTestX = new javax.swing.JComboBox();
        LXo2 = new javax.swing.JLabel();
        LYo2 = new javax.swing.JLabel();
        CBAnalito = new javax.swing.JComboBox();
        LabelAnalito = new javax.swing.JLabel();
        CBCoeficientes = new javax.swing.JComboBox();
        LabelMatCoeficientes = new javax.swing.JLabel();
        POpcResultados = new javax.swing.JPanel();
        JCRmsep = new javax.swing.JCheckBox();
        JCRmsrep = new javax.swing.JCheckBox();
        JCPlot = new javax.swing.JCheckBox();
        JCqe = new javax.swing.JCheckBox();
        JCqr = new javax.swing.JCheckBox();
        JCcc = new javax.swing.JCheckBox();
        JCPlotRMSEP = new javax.swing.JCheckBox();
        BOk = new javax.swing.JButton();
        BCancelar = new javax.swing.JButton();
        POpcoes = new javax.swing.JPanel();
        CBCal = new javax.swing.JComboBox();
        CBFiltros = new javax.swing.JComboBox();
        LXo = new javax.swing.JLabel();
        LYo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        CBQtdComponentes = new javax.swing.JComboBox();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        PCalibra.setLayout(null);

        PCalibra.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione matrizes de calibra\u00e7\u00e3o"));
        CBMatCalY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBMatCalXActionPerformed(evt);
            }
        });

        PCalibra.add(CBMatCalY);
        CBMatCalY.setBounds(10, 100, 180, 20);

        CBMatCalX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBMatCalXActionPerformed(evt);
            }
        });

        PCalibra.add(CBMatCalX);
        CBMatCalX.setBounds(10, 40, 180, 20);

        LXo1.setText("Matriz X");
        PCalibra.add(LXo1);
        LXo1.setBounds(10, 20, 140, 14);

        LYo1.setText("Matriz Y");
        PCalibra.add(LYo1);
        LYo1.setBounds(10, 80, 110, 14);

        TFNomeCoeficientes.setText("N\u00e3o_Calibrar");
        TFNomeCoeficientes.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        TFNomeCoeficientes.setEnabled(false);
        PCalibra.add(TFNomeCoeficientes);
        TFNomeCoeficientes.setBounds(10, 160, 210, 20);

        LabelNomeCoeficientes.setText("Nome da matriz de coeficientes a ser gerada");
        PCalibra.add(LabelNomeCoeficientes);
        LabelNomeCoeficientes.setBounds(10, 140, 240, 14);

        getContentPane().add(PCalibra);
        PCalibra.setBounds(10, 0, 300, 200);

        PValida.setLayout(null);

        PValida.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione matrizes de valida\u00e7\u00e3o (Opcional)"));
        CBMatTestY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBMatTestXActionPerformed(evt);
            }
        });

        PValida.add(CBMatTestY);
        CBMatTestY.setBounds(10, 160, 180, 20);

        CBMatTestX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBMatTestXActionPerformed(evt);
            }
        });

        PValida.add(CBMatTestX);
        CBMatTestX.setBounds(10, 110, 180, 20);

        LXo2.setText("Matriz Xteste");
        PValida.add(LXo2);
        LXo2.setBounds(10, 90, 140, 14);

        LYo2.setText("Matriz Yteste");
        PValida.add(LYo2);
        LYo2.setBounds(10, 140, 110, 14);

        PValida.add(CBAnalito);
        CBAnalito.setBounds(210, 60, 70, 20);

        LabelAnalito.setText("Analito");
        PValida.add(LabelAnalito);
        LabelAnalito.setBounds(210, 40, 50, 14);

        CBCoeficientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBCoeficientesActionPerformed(evt);
            }
        });

        PValida.add(CBCoeficientes);
        CBCoeficientes.setBounds(10, 60, 180, 20);

        LabelMatCoeficientes.setText("Matriz de Coeficientes");
        PValida.add(LabelMatCoeficientes);
        LabelMatCoeficientes.setBounds(10, 40, 140, 14);

        getContentPane().add(PValida);
        PValida.setBounds(10, 210, 300, 210);

        POpcResultados.setLayout(null);

        POpcResultados.setBorder(javax.swing.BorderFactory.createTitledBorder("Op\u00e7\u00f5es de resultado da fase de valida\u00e7\u00e3o"));
        JCRmsep.setText("RMSEP");
        POpcResultados.add(JCRmsep);
        JCRmsep.setBounds(10, 20, 90, 23);

        JCRmsrep.setText("RMSREP");
        POpcResultados.add(JCRmsrep);
        JCRmsrep.setBounds(10, 40, 90, 23);

        JCPlot.setText("Gerar matriz de res\u00edduos");
        POpcResultados.add(JCPlot);
        JCPlot.setBounds(10, 60, 190, 23);

        JCqe.setText("Quadrados dos erros");
        POpcResultados.add(JCqe);
        JCqe.setBounds(10, 80, 180, 23);

        JCqr.setText("Quadrados da regress\u00e3o");
        POpcResultados.add(JCqr);
        JCqr.setBounds(10, 100, 180, 23);

        JCcc.setText("Coeficiente de correla\u00e7\u00e3o");
        POpcResultados.add(JCcc);
        JCcc.setBounds(10, 120, 190, 23);

        JCPlotRMSEP.setText("Plotar varia\u00e7\u00e3o do RMSEP");
        JCPlotRMSEP.setEnabled(false);
        POpcResultados.add(JCPlotRMSEP);
        JCPlotRMSEP.setBounds(10, 140, 240, 23);

        getContentPane().add(POpcResultados);
        POpcResultados.setBounds(330, 210, 270, 210);
        POpcResultados.getAccessibleContext().setAccessibleName("Op\u00e7\u00f5es de resultado da valida\u00e7\u00e3o");

        BOk.setText("Ok");
        BOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOkActionPerformed(evt);
            }
        });

        getContentPane().add(BOk);
        BOk.setBounds(490, 430, 100, 30);

        BCancelar.setText("Sair");
        BCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelarActionPerformed(evt);
            }
        });

        getContentPane().add(BCancelar);
        BCancelar.setBounds(330, 430, 100, 30);

        POpcoes.setLayout(null);

        POpcoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Op\u00e7\u00f5es de Calibra\u00e7\u00e3o"));
        CBCal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBCalActionPerformed(evt);
            }
        });

        POpcoes.add(CBCal);
        CBCal.setBounds(10, 110, 250, 20);

        CBFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBFiltrosActionPerformed(evt);
            }
        });

        POpcoes.add(CBFiltros);
        CBFiltros.setBounds(10, 40, 220, 20);

        LXo.setText("Filtro de pr\u00e9-processamento");
        POpcoes.add(LXo);
        LXo.setBounds(10, 25, 180, 14);

        LYo.setText("M\u00e9todo de calibra\u00e7\u00e3o");
        POpcoes.add(LYo);
        LYo.setBounds(10, 90, 170, 14);

        jLabel1.setText("N\u00famero de componentes (PCR)");
        POpcoes.add(jLabel1);
        jLabel1.setBounds(10, 160, 170, 14);

        CBQtdComponentes.setEnabled(false);
        POpcoes.add(CBQtdComponentes);
        CBQtdComponentes.setBounds(170, 160, 90, 20);

        getContentPane().add(POpcoes);
        POpcoes.setBounds(330, 0, 270, 200);
        POpcoes.getAccessibleContext().setAccessibleName("opcoes");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBCalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBCalActionPerformed
        // TODO add your handling code here:
        //System.out.println(CBCal.getSelectedItem().toString());
        if(CBCal.getSelectedItem().toString().equals(PCR))
        {
            CBQtdComponentes.setEnabled(true);
            atualizaQtdComponentes();
        }
        else
        {
            CBQtdComponentes.setEnabled(false);
        }
    }//GEN-LAST:event_CBCalActionPerformed
    
    private void CBCoeficientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBCoeficientesActionPerformed
        // TODO add your handling code here:
        atualizaAnalitos();
    }//GEN-LAST:event_CBCoeficientesActionPerformed
    /**Recebe o objeto m�todo com os resultados, e uma string msg a ser preenchida com as informa��es de resultado
     **/
    private void geraRelatorio( Ferramentas.rlm r) {
        //gerando relat�rio de resultados:      
        String msg = new String("Laborat�rio de an�lise multivariada - LAMV\n");
        msg = (msg+"Resultados da analise de valida��o\n\n");
        msg = (msg+"\nMatrizes utilizadas na fase de calibra��o: "+NomeCalX+" e "+NomeCalX);        
        //msg = (msg+"\nMatrizes utilizadas na fase de calibra��o: "+r.getXcal()+" e "+r.getYcal());
        msg = (msg+"\nMatriz de coeficientes gerada: "+r.getCoeficientes().getName());
        if(CBCal.getSelectedItem().toString().equals(PCR))
        {
            msg = (msg+"\nN�mero de componentes principais: "+CBQtdComponentes.getSelectedItem().toString());
        }
        if(r.getYteste() != null && r.getXteste() != null) //matrizes de valida��o presentes
        {
            double c = 1;
            if(CBCal.getSelectedItem().toString().equals(MLR))
                c = 1.7;
            msg = (msg+"\nMatrizes utilizadas na fase de valida��o: "+r.getXteste()+" e "+r.getYteste()+"\n");
            msg = (msg+"Analito de teste: "+CBAnalito.getSelectedItem().toString()+"\n");
            if(JCRmsep.isSelected()) {
                msg = (msg+"\nRoot mean square error (RMSEP): "+ Sistema.FSys.NotacaoCientifica(r.getRmsep()*c));
            }
            if(JCRmsrep.isSelected())
            {
                msg = (msg+"\nRoot-mean-square relative error of prediction (RMSREP): "+ Sistema.FSys.NotacaoCientifica(r.getRmsrep()*c));
            }
            if(JCqe.isSelected()) {
                msg = (msg+"\nSoma dos quadrados dos erros(SQE): "+ Sistema.FSys.NotacaoCientifica(r.getSqe()*c));
            }
            if(JCqr.isSelected()) {
                msg = (msg+"\nSoma dos quadrados da regress�o(SQR): "+ Sistema.FSys.NotacaoCientifica(r.getSQReg()*c));
            }
            if(JCcc.isSelected()) {
                msg = (msg+"\nCoeficientes de correla��o: "+ Sistema.FSys.NotacaoCientifica(r.getCorrelacao()));
            }
        }
        IFResultado Analise = new IFResultado("Resultados",msg);
        LAMV.LAMV.TPCentro.setSelectedIndex(2);
        Analise.show();
        LAMV.LAMV.DPResult.add(Analise,0);
    }

    /**Recebe o objeto m�todo com os resultados, e uma string msg a ser preenchida com as informa��es de resultado
     **/
    private void geraRelatorioBagging( Ferramentas.rlm r) {
        //gerando relat�rio de resultados:      
        String msg = new String("Laborat�rio de an�lise multivariada - LAMV\n");
        msg = (msg+"Resultados da analise de valida��o\n\n");
        msg = (msg+"\nMatrizes utilizadas na fase de calibra��o: "+NomeCalX+" e "+NomeCalX);        
        //msg = (msg+"\nMatrizes utilizadas na fase de calibra��o: "+r.getXcal()+" e "+r.getYcal());
        msg = (msg+"\nMatriz de coeficientes gerada: "+r.getCoeficientes().getName());
        if(CBCal.getSelectedItem().toString().equals(PCR))
        {
            msg = (msg+"\nN�mero de componentes principais: "+CBQtdComponentes.getSelectedItem().toString());
        }
        if(r.getYteste() != null && r.getXteste() != null) //matrizes de valida��o presentes
        {
            msg = (msg+"\nMatrizes utilizadas na fase de valida��o: "+r.getXteste()+" e "+r.getYteste()+"\n");
            msg = (msg+"Analito de teste: "+CBAnalito.getSelectedItem().toString()+"\n");
            if(JCRmsep.isSelected()) {
                msg = (msg+"\nRoot mean square error (RMSEP): "+ Sistema.FSys.NotacaoCientifica(r.getRmsep()));
            }
            if(JCRmsrep.isSelected())
            {
                msg = (msg+"\nRoot-mean-square relative error of prediction (RMSREP): "+ Sistema.FSys.NotacaoCientifica(r.getRmsrep()));
            }
            if(JCqe.isSelected()) {
                msg = (msg+"\nSoma dos quadrados dos erros(SQE): "+ Sistema.FSys.NotacaoCientifica(r.getSqe()));
            }
            if(JCqr.isSelected()) {
                msg = (msg+"\nSoma dos quadrados da regress�o(SQR): "+ Sistema.FSys.NotacaoCientifica(r.getSQReg()));
            }
            if(JCcc.isSelected()) {
                msg = (msg+"\nCoeficientes de correla��o: "+ Sistema.FSys.NotacaoCientifica(r.getCorrelacao()));
            }
        }
        IFResultado Analise = new IFResultado("Resultados",msg);
        LAMV.LAMV.TPCentro.setSelectedIndex(2);
        Analise.show();
        LAMV.LAMV.DPResult.add(Analise,0);
    }
    
    /**M�todo que valida escolhas dos menus para calibra��o
     @param r Objeto rlm que cont�m as informa��es referentes a calibra��o e valida��o
     @param Xteste Matriz de absorb�ncia de teste 
     @param Yteste Matriz de concentra��es de teste*/
    private void validacao( Ferramentas.rlm r, Matriz Xteste, Matriz Yteste) {
        Matriz Ypred;
        Matriz Resul = r.getCoeficientes();
        if(Xteste == null) {
            Sistema.FSys.Msg("ERRO!", "N�o foi poss�vel obter "+Xteste.getName(), 0);
        } else if(Yteste == null) {
            Sistema.FSys.Msg("ERRO!", "N�o foi poss�vel obter "+Yteste.getName(), 0);
        }
        else if(Xteste.getnColunas() == Resul.getnLinhas() ) //� poss�vel multiplicar
        {
            Ferramentas.Algebra Algs = new Ferramentas.Algebra();
            if(CBAnalito.getSelectedItem() == "Todos") {
                if(Xteste.getnColunas() == Resul.getnLinhas()) {
                    Ypred = Algs.Mult(Xteste,Resul);
                    if(Ypred.getnLinhas() != Yteste.getnLinhas()) {
                        Sistema.FSys.Msg("Aten��o!", "Dimens�es imcompat�veis de "+Yteste.getName()+" e a matriz de de valida��o a ser gerada.\n N�mero de amostras(linhas) s�o diferentes.", JOptionPane.INFORMATION_MESSAGE);
                    } else if(Ypred.getnColunas() != Yteste.getnColunas()) {
                        Sistema.FSys.Msg("Aten��o!", "Dimens�es imcompat�veis de "+Yteste.getName()+" e a matriz de valida��o a ser gerada.\n Provavelmente voc� deve selecionar um analito na matriz de coeficientes.", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        r.valida(Xteste,Yteste,r.getCoeficientes());
                        /*if(CBCal.getSelectedItem().toString().equals(MLRBagging))
                        {*/
                            this.geraRelatorioBagging(r);
                        /*}
                        else
                        {
                            this.geraRelatorio(r);                            
                        }*/
                    }
                }
                
                else
                    Sistema.FSys.Msg("ERRO!", "Dimens�es imcompat�veis de "+Xteste.getName()+" e a matriz de coeficientes", 0);
            } else { //foi escolhido um analito para valida��o
                double temp[] = Resul.getColuna(CBAnalito.getSelectedIndex()-1);
                if(temp != null)//conseguiu obter a vari�vel(coluna) do analito
                {
                     Dados.Vetor Analito = new Dados.Vetor(Resul.getnLinhas());
                     Analito.setVetor(temp);
                     Matriz Analito2 = Sistema.FSys.Vet_to_Mat(Analito);
                     r.valida(Xteste,Yteste,Analito2);
                     this.geraRelatorio(r);
                     if(this.JCPlot.isSelected()) {
                        Matriz Resid = r.getResiduos();
                        Resid.setName("Res�duos_"+Yteste.getName());
                        Sistema.FSys.insereDadoSistema(Resid);
                    }
                } else {
                    Sistema.FSys.Msg("ERRO!", "Erro interno. N�o foi poss�vel separar o analito.", 0);
                }
            }
        }
        else //Matriz Xteste de valida��o n�o tem dimens�o compat�vel com matriz de coeficientes
        {
            Sistema.FSys.Msg("ERRO!","Dimens�es imcompat�veis de "+Xteste.getName()+" e "+Resul.getName(),JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /***/
    private void CBMatCalXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBMatCalXActionPerformed
        // TODO add your handling code here:
        if( (CBMatCalX.getSelectedItem()!="N�o Calibrar") && (CBMatCalY.getSelectedItem()!="N�o Calibrar") ) {
            if(CBMatCalX.getSelectedItem() != null && CBMatCalY.getSelectedItem() != null )
            {
                for(int c = 0; c < 1000; c++){
                    String nome = new String("Coeficientes_"+CBMatCalX.getSelectedItem()+"_"+CBMatCalY.getSelectedItem()+""+c);
                    Dado d;
                    d = LAMV.LAMV.LDados.Procura(nome);
                    if(d == null)
                    {
                        TFNomeCoeficientes.setText(nome);
                        break;
                    }
                }
            }
            CBCoeficientes.addItem(TFNomeCoeficientes.getText());
            Object Nome = CBMatCalY.getSelectedItem();
            Dado Temp;
            if(Nome != null)
            {
                 Temp = LAMV.LAMV.LDados.Procura(Nome.toString());
                 int tam = Temp.getDimC();
                 CBAnalito.removeAllItems();
                 if(CBAnalito.getSelectedItem() != "N�o Validar") {
                   CBAnalito.addItem("Todos");
                   for( int c = 0; c < tam; c++ ) {
                    CBAnalito.addItem(""+c);
                }
               }
            }
        }
    }//GEN-LAST:event_CBMatCalXActionPerformed
    
    /**Abilita e desabilita os bot�es de op��es de resultados, de acordo com o script definido pelo usu�rio*/
    private void CBMatTestXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBMatTestXActionPerformed
        // TODO add your handling code here:
        if( (CBMatTestX.getSelectedItem()=="N�o Validar") ||(CBMatTestY.getSelectedItem()=="N�o Validar")) {
            //JOptionPane.showMessageDialog(this,"Entrei ","",JOptionPane.INFORMATION_MESSAGE);
            JCRmsep.setEnabled(false);
            JCRmsrep.setEnabled(false);
            JCPlot.setEnabled(false);
            JCqe.setEnabled(false);
            JCqr.setEnabled(false);
            JCcc.setEnabled(false);
        } else {
            JCRmsep.setEnabled(true);
            JCRmsrep.setEnabled(true);
            JCPlot.setEnabled(true);
            JCqe.setEnabled(true);
            JCqr.setEnabled(true);
            JCcc.setEnabled(true);
        }
    }//GEN-LAST:event_CBMatTestXActionPerformed
    
    /**Valida escolhas e executa script de a��es definidas pelo usu�rio*/
    private void BOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BOkActionPerformed
        Ferramentas.rlm r = null;
        final String VAL="N�o Validar"; //op��es de menu para fun��es que n�o fazem nada
        final String CAL="N�o Validar";
        Object x=CBMatTestX.getSelectedItem();
        Object y=CBMatTestY.getSelectedItem();
        
        Object CBCalX=CBMatCalX.getSelectedItem();
        Object CBCalY=CBMatCalY.getSelectedItem();
        
        String NomeValX=x.toString();
        String NomeValY=y.toString();
        
        NomeCalX=CBCalX.toString();
        NomeCalY=CBCalY.toString();
        
        Dado Xc = LAMV.LAMV.LDados.Procura(NomeCalX);
        Dado Yc = LAMV.LAMV.LDados.Procura(NomeCalY);
        Matriz Xcal = Sistema.FSys.Dado_to_Mat(Xc);
        Matriz Ycal = Sistema.FSys.Dado_to_Mat(Yc);
        
        //Testa se selecionou as matrizes de calibra��o s�o diferentes, ent�o est� querendo obter coeficientes primeiro
        if(!NomeCalX.equals(NomeCalY) ) {
            //LAMV.Janelas.TelaAguarde tela = new LAMV.Janelas.TelaAguarde(new java.awt.Frame(),true);
            //Thread t = new Thread(tela);
            //t.start();
            if(Xcal == null) {
                Sistema.FSys.Msg("ERRO!", "N�o foi poss�vel obter "+NomeCalX, 0);
                return;
            } else if(Ycal == null) {
                Sistema.FSys.Msg("ERRO!", "N�o foi poss�vel obter "+NomeCalY, 0);
                return;
            } else {
                //testa se dimens�es s�o compat�veis
                if(Xcal.getnLinhas() == Ycal.getnLinhas()) //Xcal ser� invertida, ent�o linha dimens�o da linha ir� tornar-se dimens�o da coluna
                {
                    if(CBCal.getSelectedItem().toString().equals(MLRBagging)){
                        //cria objeto para fazer a regress�o
                        r = new Ferramentas.rlm(Xcal,Ycal);
                        Thread regressao = new Thread(r);
                        
                        /*LAMV.Janelas.TelaAguarde tela = new LAMV.Janelas.TelaAguarde(new java.awt.Frame(),true);
                        Thread t = new Thread(tela);
                        t.start();*/
                        
                        regressao.start();
                        //Espera a thread gerar os coeficientes...
                        while(regressao.isAlive())
                        {}
                        //tela.dispose();
                        Matriz Resul = r.getCoeficientes();//obtem o coeficientes de regress�o
                        if(Resul != null){
                            Resul.setName(TFNomeCoeficientes.getText());
                            Sistema.FSys.insereDadoSistema(Resul);
                            //tela.dispose();
                            Sistema.FSys.Msg("Resultado","Calibra��o foi realizada",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            Sistema.FSys.Msg("ERRO!", "N�o foi poss�vel obter os coeficientes", 0);
                        }
                    }
                    //testa se � regress�o Linear M�ltipla
                    else if(CBCal.getSelectedItem().toString().equals(MLR) ) {
                        //cria objeto para fazer a regress�o
                        r = new Ferramentas.rlm(Xcal,Ycal);
                        Thread regressao = new Thread(r);
                        
                        /*LAMV.Janelas.TelaAguarde tela = new LAMV.Janelas.TelaAguarde(new java.awt.Frame(),true);
                        Thread t = new Thread(tela);
                        t.start();*/
                        
                        regressao.start();
                        //Espera a thread gerar os coeficientes...
                        while(regressao.isAlive())
                        {}
                        //tela.dispose();
                        Matriz Resul = r.getCoeficientes();//obtem o coeficientes de regress�o
                        if(Resul != null){
                            Resul.setName(TFNomeCoeficientes.getText());
                            Sistema.FSys.insereDadoSistema(Resul);
                            //tela.dispose();
                            Sistema.FSys.Msg("Resultado","Calibra��o foi realizada",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            Sistema.FSys.Msg("ERRO!", "N�o foi poss�vel obter os coeficientes", 0);
                        }
                    }
                    //testa se � regress�o em componentes principais
                    else if(CBCal.getSelectedItem().toString().equals(PCR))
                    {
                        String qtd = CBQtdComponentes.getSelectedItem().toString();
                        if(qtd.equals("Testar todos"))
                        {
                            Matriz rmseps = new Matriz(CBQtdComponentes.getSelectedIndex(),1);
                            Matriz rmsrep = new Matriz(CBQtdComponentes.getSelectedIndex(),1);
                            for(int i = 0; i < CBQtdComponentes.getSelectedIndex(); i++ ) {
                                r = new Ferramentas.pcr(Xcal,Ycal,i);
                                //testa se est� querendo validar
                                Matriz Coef = Sistema.FSys.Dado_to_Mat(LAMV.LAMV.LDados.Procura(CBCoeficientes.getSelectedItem().toString()));
                                if(!NomeValX.equals(VAL) && !NomeValY.equals(VAL) && (!NomeValX.equals(NomeValY)) &&  r != null ) {
                                    //Validar - Escolheu duas matrizes distintas
                                    Dado Xv = LAMV.LAMV.LDados.Procura(NomeValX);
                                    Dado Yv = LAMV.LAMV.LDados.Procura(NomeValY);
                                    Matriz Xteste, Yteste;
                                    if(Xv != null) {
                                        Xteste = Sistema.FSys.Dado_to_Mat(Xv);
                                    } else {
                                        Sistema.FSys.Msg("Erro","N�o foi poss�vel obter matriz de teste X selecionada", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    if(Yv != null) {
                                        Yteste = Sistema.FSys.Dado_to_Mat(Yv);
                                    } else {
                                        Sistema.FSys.Msg("Erro","N�o foi poss�vel obter matriz de teste Y selecionada", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    this.validacao(r,Xteste,Yteste);
                                }
                                rmseps.M[i][0] = r.getRmsep();
                                rmsrep.M[i][0] = r.getRmsrep();
                            }
                            rmseps.setName("RMSEPs");
                            rmsrep.setName("RMSREPs");
                            Sistema.FSys.insereDadoSistema(rmsrep);
                            Sistema.FSys.insereDadoSistema(rmseps);
                            return;
                        } else {
                            int j = CBQtdComponentes.getSelectedIndex();
                            r = new Ferramentas.pcr(Xcal,Ycal,j);
                        }
                    }
                } else {
                    Sistema.FSys.Msg("Aten��o!", "As dimens�es de "+NomeCalX+" e "+NomeCalY+" s�o imcompat�veis.", 0);
                }
            }
        }
        //testa se est� querendo validar
        Matriz Coef = Sistema.FSys.Dado_to_Mat(LAMV.LAMV.LDados.Procura(CBCoeficientes.getSelectedItem().toString()));
        if( r== null && Coef != null )
        {
            r = new Ferramentas.rlm();
            r.setCoeficientes(Coef);
        }
        if(!NomeValX.equals(VAL) && !NomeValY.equals(VAL) && (!NomeValX.equals(NomeValY)) &&  r != null ) {
            //Validar - Escolheu duas matrizes distintas
            //LAMV.Ferramentas.rlm r = new LAMV.Ferramentas.rlm();
            //r.setCoeficientes(Coef);
            Dado Xv = LAMV.LAMV.LDados.Procura(NomeValX);
            Dado Yv = LAMV.LAMV.LDados.Procura(NomeValY);
            Matriz Xteste, Yteste;
            if(Xv != null) {
                Xteste = Sistema.FSys.Dado_to_Mat(Xv);
            } else {
                Sistema.FSys.Msg("Erro","N�o foi poss�vel obter matriz de teste X selecionada", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(Yv != null) {
                Yteste = Sistema.FSys.Dado_to_Mat(Yv);
            } else {
                Sistema.FSys.Msg("Erro","N�o foi poss�vel obter matriz de teste Y selecionada", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.validacao(r,Xteste,Yteste);
            //this.geraRelatorio(r);
            javax.swing.JOptionPane.showMessageDialog(this,"Valida��o foi realizada","Resultado",javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_BOkActionPerformed
    
    private void CBFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBFiltrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBFiltrosActionPerformed
    
    /**Encerra tela sem realizar nenhuma a��o*/
    private void BCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_BCancelarActionPerformed
    
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCancelar;
    private javax.swing.JButton BOk;
    private javax.swing.JComboBox CBAnalito;
    private javax.swing.JComboBox CBCal;
    private javax.swing.JComboBox CBCoeficientes;
    private javax.swing.JComboBox CBFiltros;
    private javax.swing.JComboBox CBMatCalX;
    private javax.swing.JComboBox CBMatCalY;
    private javax.swing.JComboBox CBMatTestX;
    private javax.swing.JComboBox CBMatTestY;
    private javax.swing.JComboBox CBQtdComponentes;
    private javax.swing.JCheckBox JCPlot;
    private javax.swing.JCheckBox JCPlotRMSEP;
    private javax.swing.JCheckBox JCRmsep;
    private javax.swing.JCheckBox JCRmsrep;
    private javax.swing.JCheckBox JCcc;
    private javax.swing.JCheckBox JCqe;
    private javax.swing.JCheckBox JCqr;
    private javax.swing.JLabel LXo;
    private javax.swing.JLabel LXo1;
    private javax.swing.JLabel LXo2;
    private javax.swing.JLabel LYo;
    private javax.swing.JLabel LYo1;
    private javax.swing.JLabel LYo2;
    private javax.swing.JLabel LabelAnalito;
    private javax.swing.JLabel LabelMatCoeficientes;
    private javax.swing.JLabel LabelNomeCoeficientes;
    private javax.swing.JPanel PCalibra;
    private javax.swing.JPanel POpcResultados;
    private javax.swing.JPanel POpcoes;
    private javax.swing.JPanel PValida;
    private javax.swing.JTextField TFNomeCoeficientes;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
    private static String MLR = new String("Regress�o Linear M�ltipla");
    private static String MLRBagging = new String("Regress�o Linear M�ltipla - Bagging");
    private static String MLRSubagging = new String("Regress�o Linear M�ltipla - Subagging");    
    private static String PCR = new String("Regress�o em Componentes Principais");
    private static String PLS1 = new String("Regress�o em M�nimos Quadrados Parciais");
    String NomeCalX;
    String NomeCalY;
}
