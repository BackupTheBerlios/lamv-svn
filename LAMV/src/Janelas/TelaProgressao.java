/*
 * TelaProgressao.java
 *
 * Created on 6 de Maio de 2005, 09:57
 */

//package MaLab.Janelas;

/**
 *
 * @author Nucleo
 */
package Janelas;
import Dados.Matriz;
import Sistema.leDisco;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class TelaProgressao extends Thread implements  ActionListener {
    private JProgressBar progress;
    private JButton button;
    private JLabel label1;
    private JPanel topPanel;
    private File Arquivo;
    private JFileChooser ArqEscolhido;
    private int bytesLidos = 0;
    private String Path, nome;
    private JFrame tela;
    private Matriz Carregada;
    private Matriz element;
    private int rows, columns;
    
    public TelaProgressao(JFrame Tela, String path, String Nome) {
        tela = Tela;
        tela.setLocation(300,300);
        nome = Nome;
        Arquivo = new File(path);
        Path = path;
        tela.setTitle( "Por favor, aguarde..." );
        tela.setSize( 310, 130 );
        tela.setBackground( Color.gray );
        topPanel = new JPanel();
        topPanel.setPreferredSize( new Dimension( 310, 130 ) );
        tela.getContentPane().add( topPanel );
        label1 = new JLabel( "Carregando arquivo..." );
        label1.setPreferredSize( new Dimension( 280, 24 ) );
        topPanel.add( label1 );
        
        progress = new JProgressBar();
        progress.setPreferredSize( new Dimension( 300, 20 ) );
        progress.setMinimum( 0 );
        progress.setMaximum( (int)Arquivo.length() );
        progress.setValue( 0 );
        progress.setBounds( 20, 35, 260, 20 );
        topPanel.add( progress );
        
        button = new JButton( "Cancelar" );
        topPanel.add( button );
        button.addActionListener( this );
        tela.setVisible( true );
        tela.pack();
        tela.show();
    }
        
    public void run() {
        FileInputStream fin = null;
        String temp;       
        String dados = new String("");
        boolean erro = false;
        //abrindo o arquivo para leitura
        try {
            fin =  new FileInputStream(Path);
            try //Inicio try do DataInputStream
            {
                DataInputStream myInput = new DataInputStream(fin);
                try {
                    while ((temp = myInput.readLine()) != null) {
                        bytesLidos = bytesLidos + temp.length();
                        progress.setValue(bytesLidos);
                        Rectangle progressRect = progress.getBounds();
                        progressRect.x = 0;
                        progressRect.y = 0;
                        progress.paintImmediately( progressRect );
                        dados = dados.concat(temp+"\n");
                    }
                } catch (Exception e) {
                    Sistema.FSys.Msg("ERRO!", "Erro no readLine:\n " + e.toString(), 0);
                }
            } //fim try do DataInputStream
            catch (Exception e) {
                Sistema.FSys.Msg("ERRO!", "Erro no InputStream: \n " + e.toString(), 0);
                erro = true;
            }
        } //fim try do FileInputStream
        catch (Exception e) {
            Sistema.FSys.Msg("ERRO!", "Falha ao abrir o arquivo.\n " + Path.trim(), 0);
            erro = true;
        }
        try {
            fin.close();
        } catch (Exception e) {
            Sistema.FSys.Msg("ERRO!", "Falha ao fechar o arquivo. \n " + e.toString(), 0);
            erro = true;
        }
        if(!erro) {
            Matriz M = converte(dados);
            if(M != null)
            {
                Carregada = new Matriz(M.getnLinhas(),M.getnColunas());
                Carregada.setName(nome);
                Carregada.M = M.M;
                Sistema.FSys.insereDadoSistema(Carregada);
            }
        }
        
        //Carregando configurações..
        StringTokenizer PathConf = new StringTokenizer(Path,".");
        temp = PathConf.nextToken();
        try {
            fin =  new FileInputStream(temp+".ml");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        DataInputStream myInput = new DataInputStream(fin);
        try {
            while ((temp = myInput.readLine()) != null) {
                leDisco Disco = new leDisco();
                Disco.le_cabecario(Carregada,temp);
            }
        } catch(Exception e) {
        }

        
        tela.dispose();
        this.stop();
    }
    public void actionPerformed( ActionEvent event ) {
        if( event.getSource() == button ) {
            //usuário cancelou a operação de leitura
            Carregada = null;
            tela.dispose();
            this.stop();
        }
    }
    
    public Matriz converte(String s) {
        String NumTemp;
        Matriz Ret;
        Double Num;
        int NumInt;
        boolean isPot = false;
        Vector row = new Vector();
        Vector col = new Vector();
        s = s + " ;";
        int i = s.length();
        int j;
        int rowCounter = 0;
        int colCounter = 0;
        String sData = new String();
        Double fl;
        char sChar;
        for (j = 0; j<i ; j++) {
            sChar = s.charAt(j);
            if ( (sChar==' ')|| (sChar==',')|| ( (int) sChar==9)||(sChar == ';')||( (int) sChar == 13)||( (int) sChar == 10) ) {
                fl = new Double(0);
                try {
                    boolean testSpace = true;
                    int ii;
                    for(ii=0;ii<sData.length();ii++) {
                        testSpace=testSpace&&(sData.charAt(ii)==' ');
                    }
                    if(testSpace==false) {
                        fl = new Double(sData);
                        col.addElement(sData);
                    }
                    sData = new String();
                } catch (Exception e) {
                    sData = new String();
                }
                if ( ( (sChar == ';')||( (int) sChar == 13)||( (int) sChar == 10) ) &&!col.isEmpty() ) {
                    row.addElement(col);
                    rowCounter = rowCounter + 1;
                    sData = new String();
                    colCounter = col.size();
                    col = new Vector();
                }
            } else {
                if ((Character.isDigit(sChar))||(sChar=='.')||(sChar=='-')) {
                    sData = sData + sChar;
                } else if(sChar == 'e') {
                    NumTemp = new String("");
                    j++;
                    sChar = s.charAt(j);
                    if(sChar == '+') {
                        double Temp;
                        j++;
                        sChar = s.charAt(j);
                        NumTemp = NumTemp + sChar;
                        j++;
                        sChar = s.charAt(j);
                        NumTemp = NumTemp+ sChar;
                        j++;
                        sChar = s.charAt(j);
                        NumTemp = NumTemp+ sChar;
                        Num = new Double(NumTemp);
                        NumInt = Num.intValue();
                        fl = new Double(sData);
                        j++;
                        while( (j < i) && (s.charAt(j) != ' ') && ((int)s.charAt(j) != 10)  && ((int)s.charAt(j) != 13) ) {
                            sChar = s.charAt(j);
                            NumTemp = NumTemp + sChar;
                            j++;
                        }
                        j--;
                        if((int)s.charAt(j) == 10 || (int)s.charAt(j) == 13)
                            
                            Num = new Double(NumTemp);
                        NumInt = Num.intValue();
                        fl = new Double(sData);
                        Temp = fl.doubleValue() * Math.pow(10,NumInt);
                        sData = fl.toString(Temp);
                        fl = new Double(sData);
                        col.addElement(sData);
                        sData = new String("");
                    } else if(sChar == '-') {
                        double Temp;
                        j++;
                        sChar = s.charAt(j);
                        NumTemp = NumTemp + sChar;
                        j++;
                        sChar = s.charAt(j);
                        NumTemp = NumTemp+ sChar;
                        j++;
                        sChar = s.charAt(j);
                        NumTemp = NumTemp+ sChar;
                        j++;
                        Num = new Double(NumTemp);
                        NumInt = Num.intValue();
                        fl = new Double(sData);
                        Temp = fl.doubleValue() /Math.pow(10,NumInt);
                        sData = fl.toString(Temp);
                    }
                }
            }
        }
        rows = rowCounter;
        columns = colCounter;
        element = new Matriz(rows,columns,"Matriz");
        col = new Vector();
        Double d = new Double(0);
        for (j = 0; j<rows; j++) {
            col = (Vector) row.elementAt(j);
            for (i = 0; i<col.size(); i++) {
                d = new Double((String)col.elementAt(i));
                try
                {
                    element.M[j][i] = d.doubleValue();
                }
                catch(java.lang.ArrayIndexOutOfBoundsException ex)
                {
                    Sistema.FSys.Msg("ERRO","Não foi possível converter os dados do arquivo '"+
                                           Arquivo.getAbsolutePath()+"'. \nVerifique se o arquivo está no formato de matriz.",0);
                    return null;
                }
            }
        }
        return element;
    }
    
}


