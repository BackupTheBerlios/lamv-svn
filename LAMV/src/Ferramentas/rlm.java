/*
 * rlm.java
 *
 * Created on 7 de Abril de 2005, 16:36
 */
package Ferramentas;

import Ferramentas.Estat;
import Ferramentas.Algebra;
import Dados.*;
/**
 *
 * @author Barnes
 */

/**Classe responsável pela calibração multivariada utilizando o método rlm(Regressão linear múltipla)*/
public class rlm extends Thread{
    
 /**Este construtor inicializa somente os objetos internos da classe sem nenhuma matriz de cálculo*/
 public rlm()
 {
     Alg = new Algebra();
     String msg = new String("");
 }
 
 /**Este construtor inicializa as os objetos internos da classe e copia as matrizes X e Y 
  * para as matrizes de cálculo interna  
  * @param X Matriz de absorbâncias do modelo
  * @param Y Matriz de concentrações do modelo */
 public rlm(Matriz X, Matriz Y)
  {
        SQE = 0;
        SQReg = 0;
        RMSEP = 0;
        SQT = 0;
        corelacao = 0;
        Residuos = null;
        YPred = null;
        this.X = X;
        this.Y = Y;
        Alg = new Algebra();
        String msg = new String("");
     	String n1,n2;
 	double []temp;
	msg = msg.concat("Resultados estatísticos da análise de regressão aplicada as matrizes "+X.getName()+" "+Y.getName());
  }
 
 /**Fluxo de processamento da thread. Calcula a matriz de coeficientes*/
 public void run()
 {
     //decomposicao SVD
     svd Svd = new svd(X);
     S = new Matriz(X.getnLinhas(),X.getnLinhas(),"S");
     V = new Matriz(X.getnLinhas(),X.getnColunas(),"V");
     D = new Matriz(X.getnColunas(),X.getnColunas(),"D");
     S = Svd.getS();
     V = Svd.getV();
     D = Svd.getD();
     D = Alg.Transposta(D);
     for(int i=0; i < V.getnLinhas() && i < V.getnColunas() ; i++)
       {
            if(V.M[i][i] != 0)
               if(V.M[i][i] > 0.00000000000001)
                   V.M[i][i] = 1/V.M[i][i];
       }
     Aux2 = Alg.Mult(S,V);
     Aux2 = Alg.Mult(Aux2,D);
     Aux2 = Alg.Transposta(Aux2);
     Coeficientes = Alg.Mult(Aux2,Y);
     Coeficientes.setName("Coeficientes_"+X.getName()+"_"+Y.getName());   
 }
 
 /**Faz a validação do modelo calculado
  * @param Xt matriz de absorbâncias de teste
  * @param Yt matriz de concentrações de teste
  * @param Coef matriz de coeficientes
  * @return Retorna true caso todos os resultados possam ser obtidos
  *         Retorna false caso algum resultado não possa ser obtido*/
 public boolean valida(Matriz Xt, Matriz Yt, Matriz Coef)
 {
            this.Xteste = Xt;
            this.Yteste = Yt;
            javax.swing.JPanel P = new javax.swing.JPanel();
            javax.swing.JTextArea A = new javax.swing.JTextArea();
            //obtem valores de Y previstos pelo modelo
            if(Xt.getnColunas() != Coef.getnLinhas())
                return false;
            YPred = Alg.Mult(Xt,Coef);
            YPred.setName(Yt.getName()+"_PRED");
            Sistema.FSys.insereDadoSistema(YPred);
            
            //obtem o resíduo do modelo (Y - YPred)
            Residuos = new Matriz(YPred.getnLinhas(),YPred.getnColunas(),"Residuos");
            for(int i= 0; i < YPred.getnLinhas(); i++)
            {
                for(int j = 0; j < YPred.getnColunas(); j++)
                {  
                    Residuos.M[i][j] = Yt.M[i][j] - YPred.M[i][j] ;
                }
            }          
            //obtem SQE (Y-YPred)^2
            SQE = 0;
            for(int i= 0; i < Yt.getnLinhas(); i++)
            {
                for(int j = 0; j < YPred.getnColunas(); j++)
                {
                    SQE = SQE + Math.pow( (Yt.M[i][j] - YPred.M[i][j]), 2 );
                }
            }
            //obtem rmsrep
            RMSREP = 0;
            for(int i = 0; i < YPred.getnLinhas(); i++)
            {
                double temp;
                for(int j = 0; j < YPred.getnColunas(); j++)
                {
                     temp = SQE/Yt.M[i][j];
                     RMSREP = RMSREP + Math.pow(temp,2);
                }
            }
            RMSREP = RMSREP / (YPred.getnLinhas()*YPred.getnColunas());
            //obtem rmsep
            double aux = 0;
            for(int i = 0; i < Residuos.getnLinhas(); i++)
            {
                for(int j = 0; j < Residuos.getnColunas(); j++)
                {
                    aux = aux + Math.pow(Residuos.M[i][j],2);
                }
            }
            aux = aux / Residuos.getnLinhas();
            RMSEP = Math.sqrt(aux);
            
            //RMSEP = SQE/(YPred.getnLinhas()*YPred.getnColunas());
            //obtem SQReg (YPred - YMedia)^2
            soma = 0;
            for(int i = 0; i < YPred.getnLinhas(); i++)
            {
                for(int j = 0; j < YPred.getnColunas(); j++)
                {
                    soma = soma + YPred.M[i][j];
                }
            }
            media = soma / (YPred.getnLinhas()*YPred.getnColunas());
            SQReg = 0;
            for(int i = 0; i < YPred.getnLinhas(); i++)
            {
                for(int j = 0; j < YPred.getnColunas(); j++)
                {
                    SQReg = SQReg + Math.pow( (YPred.M[i][j] - media), 2 );
                }
            }
            //obtem indice de correlação
            SQT = SQReg + SQE;
            corelacao = SQReg / SQT;
            corelacao = Math.sqrt(corelacao);
            return true;
 }
 
 /**Seta os coeficientes que foram gerados externamente a classe*/
 public void setCoeficientes(Matriz s)
 {
     Coeficientes = s;
 }
 
 /**Retorna os coeficientes que estão internos a classe*/
 public Matriz getCoeficientes()
 {
     return Coeficientes;
 }
 
 /**Retorna o erro quadrado médio absoluo*/
 public double getRmsep()
 {
     return RMSEP;
 }
 
 /**Retorna o erro quadrado médio relativo*/
 public double getRmsrep()
 {
     return RMSREP;
 }
 
 /**Retorna a soma dos quadrados dos erros*/
 public double getSqe()
 {
     return SQE;
 }
 
 /**Retorna a soma dos quadrados da regressão*/
 public double getSQReg()
 {
     return SQReg;
 }
 
 /**Retorna a soma total dos quadrados*/
 public double getSQT()
 {
     return SQT;
 }
 
 /**Retorna a correlação calcula entre os coeficientes e os dados reais de que foram informados*/
 public double getCorrelacao()
 {
    return corelacao;
 }
 
 /**Retorna a matriz de erros entre a matriz prevista pelo modelo(meuX) e a matriz real(Xt)*/
 public Matriz getResiduos()
 {
     return Residuos;
 }
 
 /**Retorna a matriz de absorbância utilizada na calibração*/
 public String getXcal()
 {
     if(X != null)
         return X.getName();
     else 
         return null;
         
 }
 
 /**Retorna a matriz de concentrações utilizada na calibração*/
 public String getYcal()
 {
     if( Y!= null)
       return Y.getName();
     else 
       return null;
 }

 /**Retorna a matriz de absorbâncias utilizada na validação*/
 public String getXteste()
 {
     if( Xteste != null)
         return Xteste.getName();
     else
         return null;
 }
 
 /**Retorna a matriz de concentrações prevista pelo modelo*/
 public Matriz getYPred()
 {
    return YPred;
 }
 
 /**Retorna a matriz de concentrações utilizada na validação*/
 public String getYteste()
 {
     if( Yteste != null )
         return Yteste.getName();
     else
         return null;
 }
 
 private double soma,SQE,SQReg,RMSEP,RMSREP,SQT,corelacao,media;
 private Matriz Q,R,D, U,S,V,Aux1,Aux2, Residuos, YPred, Xteste = null, Yteste = null;
 
 /**Matriz de coeficientes gerada com as matrizes de calibração Xcal e Ycal*/
 public Matriz Coeficientes;
 
 /**Matriz que armazena Ycal*/
  public Matriz Y;

 /**Matriz que armazena Xcal*/
  public Matriz X;
 
 private Algebra Alg;

}

    

