/*
 * FiltrosEstatisticos.java
 *
 * Created on 22 de Agosto de 2005, 15:05
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package Ferramentas;

import Dados.Matriz;
/**
 *
 * @author EngSoares
 */
public class FiltrosEstatisticos extends Estat {
    
    /** Cria uma nova instância de FiltrosEstatisticos */
    public FiltrosEstatisticos() {
        Alg = new Algebra();
    }
    
    /**Filtra uma matriz M pelo método da média móvel
     * @param M Matriz de entrada a ser filtrada
     * @param Janela Tamanho da janela móvel a ser utilizada
     * @return Matriz filtrada
     */
    public Matriz mediaMovel(Matriz M, int Janela)
    {
        Matriz Mtrans = Alg.Transposta(M);
        Matriz Ret;
        int aux1;
        int aux2;
        int J = Mtrans.getDimL();
        int I = Mtrans.getDimC();
        int K = (int)(J/(Janela+1)-0.5);
        Ret = new Matriz(M.getDimL(),M.getDimC()/(Janela+1));
        //System.out.println("Linhas de Ret: "+Ret.getDimL()+" Colunas de Ret: "+Ret.getDimC());
        for( J = 1; J <= K; J++)
        {
            aux1 = J*Janela+J;
            aux2 = (J-1) * Janela + J;
            double soma = 0;
            for( int linha = 0; linha < Mtrans.getDimC(); linha++ )
            {
                for(int col = aux2; col < aux1; col++)
                {
                    soma += Mtrans.M[col][linha];
                }
            }
           Ret.M[0][J-1] = (soma/(aux2-aux1));
        }
        Ret = Alg.RecortM(Ret, 0, 0, Ret.getDimL()-1, J-2);
        Ret.setName(M.getName()+"_filMediaMovel");
        return Ret;
    }
    public Algebra Alg;
    
}
