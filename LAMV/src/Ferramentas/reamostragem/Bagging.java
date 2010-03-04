/*
 * Bagging.java
 *
 * Created on 20 de Novembro de 2007, 15:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Ferramentas.reamostragem;

import Dados.Matriz;

/**
 *
 * @author An
 */
public class Bagging {
    /** Creates a new instance of Bagging */
    /*Matriz Xcal, Ycal,B;
    int N_replicacoes = 100;
    public Bagging(Matriz Xcal, Matriz Ycal,int analito) {
        Matriz novoXcal = null;
        this.Xcal = Xcal;
        this.Ycal = Ycal;
        for(int i = 0; i < N_replicacoes; i++)
        {
            //Seleciona a amostra inicial, e coloca na matriz
            int amostra = (int)(Math.round(Math.random()*Xcal.getnLinhas()));
            System.out.println("Amostra: "+amostra);
            novoXcal = Ferramentas.Algebra.RecortM(Xcal,amostra,0,amostra,Xcal.getnColunas());            
            //Seleciona o restante das bootstraps, e concatena com a matriz amostras
            //No bagging o número de bootstraps sempre é igual ao número de amostras originais de Xcal
            for(int n = 0; i < Xcal.getnLinhas(); i++)
            {
                amostra = (int)Math.round(Math.random()*Xcal.getnLinhas());
                System.out.println("Amostra: "+amostra);                
                try
                {
                    novoXcal = Ferramentas.Algebra.ConcatAbaixo(novoXcal,Ferramentas.Algebra.RecortM(Xcal,amostra,0,amostra,Xcal.getnColunas()));
                }
                catch(Exception e)
                {
                    Sistema.FSys.Msg("Erro na reamostragem","Erro ao fazer a reamostragem do Bagging",0);
                }
            }
        }
        System.out.println("Dimensão: "+novoXcal.getnLinhas());
        /*for i=1:N_replicacoes
            amostras = randsample(size(Xcal,1),N_boostraps,1);%100 amostras com reposição
            X = Xcal(amostras,:);
            Y = Ycal(amostras,:);
            coef = X\Y;                               %Faz a regressão
            Ypred(i,:) = XtesteMn*coef(:,1); 
        end    */
    /*}
    
    public void getCoeficientesBagging(){
        
    }*/
    
    
}
