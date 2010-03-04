/*
 * pcr.java
 *
 * Created on 10 de Maio de 2005, 19:35
 */

package Ferramentas;

import Dados.*;

/**
 *
 * @author Barnes
 */
public class pcr extends rlm {
    
    /** Cria uma nova instância de pcr */
    public pcr(Matriz Xcal, Matriz Ycal, int PCs) {
        this.PCs = PCs;
        this.Xcal = Xcal;
        this.Ycal = Ycal;
        Fer =  new Ferramentas.Algebra();
        Ferramentas.svd SVD = new Ferramentas.svd(Xcal);     
        S = SVD.getS();
        V = SVD.getV();      
        D = SVD.getD();
        Matriz Ss = Fer.RecortM(S,0,0,S.getnLinhas()-1,PCs);
        D = Fer.RecortM(D, 0, 0, D.getnLinhas()-1, PCs);
        V = Fer.RecortM(V, 0, 0, PCs, PCs);
        Matriz T = Fer.Inv( V );
        
        Beta = Fer.Mult( D, T );
        Beta = Fer.Mult( Beta, Fer.Transposta( Fer.RecortM(S, 0, 0, S.getnLinhas()-1, PCs) ) );
        Beta = Fer.Mult( Beta, Ycal );
        
        Beta.setName("Coeficientes_"+Xcal.getName()+"_"+Ycal.getName()+"_"+PCs);
        Sistema.FSys.insereDadoSistema( Beta );
        Coeficientes = Beta;

        //codigo -1 quando quer testar para PCs = 1 até PCs número máximo
        /*if(PCs == -1) {
            //for(int cont = 1; cont < Xcal.getColunas(); cont++) {
                
            //}
        } else {
            
        }*/
    }
    private Matriz calcula(int PCs)
    {
        Matriz Ss = Fer.RecortM(S,0,0,S.getnLinhas()-1,PCs);
        D = Fer.RecortM(D, 0, 0, D.getnLinhas()-1, PCs);
        V = Fer.RecortM(V, 0, 0, PCs, PCs);
        Matriz T = Fer.Inv( V );
        
        Beta = Fer.Mult( D, T );
        Beta = Fer.Mult( Beta, Fer.Transposta( Fer.RecortM(S, 0, 0, S.getnLinhas()-1, PCs) ) );
        Beta = Fer.Mult( Beta, Ycal );
        
        Beta.setName("Coeficientes_"+Xcal.getName()+"_"+Ycal.getName()+"_"+PCs);
        Sistema.FSys.insereDadoSistema( Beta );
        Coeficientes = Beta;
        return Coeficientes;
    }
    public int getNumComponentes()
    {
        return PCs;
    }
    private int PCs=0;
    private Ferramentas.Algebra Fer;
    private Matriz Xcal;
    private Matriz Ycal;
    private Matriz S,V,D;
    private Matriz Beta;
}
