
package Ferramentas;

import java.util.Vector;
import java.awt.Color;
import Dados.*;

/**Classe de métodos que realizam operações de cálculo estatísticos*/
public class Estat {
    
    /**Calcula as médias da matriz M
     @param M Matriz de entrada
     @return Matriz de médias da matriz M*/
    public Matriz Media(Matriz M) {
        Matriz VM = new Matriz(M.getnColunas(),1,"Media_"+M.getName());
        for(int l = 0; l < M.getnColunas(); l++) {
            double d = 1.0D;
            d--;
            for(int k = 0; k < M.getnLinhas(); k++)
                d = M.M[k][l] + d;
            
            VM.M[l][0] = d / (double)M.getnLinhas();
        }
        return VM;
    }
    
    /**Calcula a média de uma coluna
     @param M Matriz de entrada
     @param col coluna da matriz que será calculada a média
     @return média da coluna 'col' da matriz M*/
    public double MediaCol(Matriz M, int col) {
        double soma;
        double ZERO = 1;
        ZERO--;
        soma = 1.0D;
        soma--;
        int l;
        for(l = 0; l < M.getnLinhas(); l++)
            soma += M.M[l][col];
        soma = soma/(double)M.getnLinhas();
        return soma;
    }
    
    /**Calcula a variância de uma coluna de matriz
     @param M Matriz de entrada
     @param col Coluna da matriz M que será calculada a variância
     @return variância da coluna 'col' da matriz M*/
    public double Var( Matriz M, int col) {
        double soma;
        double media;
        int l;
        soma = 1;
        soma--;
        media = MediaCol(M,col);
        
        if(col < M.getnColunas() && col >= 0) { // numero da coluna valido
            for(l = 0; l < M.getnLinhas(); l++) {
                soma = soma + ( ( M.M[l][col] - media )*( M.M[l][col] - media ) );
            }
        }
        return soma/(M.getnLinhas()-1);
    }
    
    /**Calcula a covariancia entre duas colunas de uma matriz
     @param M Matriz de entrada
     @param i primeira coluna
     @param k segunda coluna
     @return Covariância entre as colunas 'i' e 'k' da matriz M*/
    public double Cov( Matriz M, int i, int k) {
        double soma;
        double mediai, mediak;
        int l;
        double ZERO = 1;
        ZERO--;
        soma = 1;
        soma--;
        mediai = MediaCol(M,i);
        mediak = MediaCol(M,k);
        
        if(i < M.getnColunas() && i >= 0 &&  k < M.getnColunas() && k >= 0) { // numero da coluna valido
            for(l = 0; l < M.getnLinhas(); l++) {
                soma = soma + ( (M.M[l][i] - mediai )*( M.M[l][k] - mediak ) );
            }
        }
        return soma/(M.getnLinhas()-1);
    }
    
    /**Retorna a Matriz de Covariancias da matriz M
     @param M Matriz de entrada
     @return Retorna a covariâncias da matriz M*/   
    public Matriz MCov( Matriz M ) {
        int i;
        int k;
        Matriz C = new Matriz(M.getnColunas(),M.getnColunas(),"MCov_"+M.getName());
        for ( i = 0 ; i < M.getnColunas() ; i++ ) {
            for ( k = 0 ; k < M.getnColunas() ; k++ ) {
                C.M[i][k] = Cov(M,i,k);
            }
        }
        return C;
    }
    
    /**Calcula a soma do quadrado dos erros entre duas matrizes sum(Yprev-Y)^2*/
    public static double Sqe(Matriz Residuos) {
        double SQE = 0;
        for(int i = 0; i < Residuos.getnLinhas(); i++)
            for(int j = 0; j < Residuos.getnColunas(); j++) {
                Residuos.M[i][j] = Math.pow(Residuos.M[i][j],2);
                SQE = SQE + Residuos.M[i][j];
            }
        return SQE;
    }
    
    /**Calcula a soma do quadrado dos erros da regressão sum(Yprev - mediaYPrev)^2
     @param X Matriz de absorbâncias
     @param Y Matriz de concentrações
     @param Coeficientes Matriz de coeficientes
     @return retorna o erro da regressão*/
    public static double SQReg(Matriz X,Matriz Y,Matriz Coeficientes) {
        Algebra Alg = new Algebra();
        double SQReg = 0;
        double media = Alg.media_coluna(Y,0);
        Matriz YPred = Alg.Mult(X,Coeficientes);
        for(int i = 0; i < YPred.getnLinhas(); i++) {
            for(int j = 0; j < YPred.getnColunas(); j++) {
                SQReg = SQReg + Math.pow(YPred.M[i][j] - media,2);
            }
        }
        return SQReg;
    }
    
    /**Cálcula a matriz de Correlacao
     @param M Matriz de entrada
     @return Matriz de correlação da matriz M*/
    public Matriz MCorr( Matriz M) {
        Matriz Cov;
        Matriz diag;
        Matriz temp;
        double div;
        double den;
        Cov = MCov(M);
        if(Cov != null) {
            diag = new Matriz(M.getnColunas(),1,"tempDiag");
            for( int i = 0; i< M.getnColunas(); i++) {
                diag.M[i][0] = Cov.M[i][i];
            }
            Algebra Fer = new Algebra();
            temp = Fer.Mult(diag,Fer.Transposta(diag));
            temp = Fer.Sqrt(temp);
            temp = Fer.Divide(Cov,temp);
            temp.setName("Corr_"+M.getName());
            return temp;
        }
        else return null;
    }
    
    public Vector DistFreq( Matriz M ) {
        Vector ret = new Vector();
        Algebra Alg = new Algebra();
        Matriz Plo,Plo1;
        double N,Maior,Menor,Tam;
        float Intervalo;
        float Vetor[];
        float Aux;
        int Num;
        int Freq[];
        int i,j;
        
        Maior = Alg.max(M);
        Menor = Alg.min(M);
        N = java.lang.Math.sqrt(M.getnLinhas()*M.getnColunas());
        Num = (int)N;
        Tam = (Maior-Menor)/N;
        Vetor = new float[(int)N*2];
        Vetor[0] = (float)Menor;
        Freq = new int[(int)N*2];
        for(i = 1;i <= N; i++) {
            Vetor[i] = Vetor[i-1] + (float)Tam;
        }
        Intervalo = (float)Tam;
        String Str;
         /*for(int i = 0;i<= N;i++)
            {
                   Str = Str.concat(" "+Vetor[i]);
            }
          Mensagem("Intervalos",Str); */ //INTERVALOS OK
        for(int k = 0; k<=N ; k++) {
            Freq[k] = 0;
        }
        for(i = 0;i<N;i++) {
            for(j = 0; j<N; j++) {
                for(int k = 1; k < N; k++) {
                    Aux = (float)M.M[i][j];
                    if( ( Aux >= Vetor[k] ) && ( Aux < Vetor[k+1]) )
                        Freq[k]++;
                }
                            /*if(Aux <= Vetor[(int)N])
                                 Freq[(int)N]++;*/
            }
        }
        Str = new String("Valores: ");
        for(i = 0;i < N;i++) {
            Str = Str.concat(" "+Freq[i]);
        }
        //Mensagem("Intervalos",Str);
        //Plo = new Matriz(1,(int)N,"temp023154");
        Plo1 = new Matriz(2,Num*3,"temp0231d4");
        Plo = new Matriz(2,Num*2,"Distribuição de frequências de "+M.getName());
        for(i = 0,j = 0; i<(int)N; i++,j=j+2) {
            // Plo.M[0][0] = Freq[0];   //X
            // Plo.M[0][1] = Freq[1];   //Y
            Plo.M[0][j] = Vetor[i];
            Plo.M[1][j] = Vetor[i+1];
            Plo.M[0][j+1] = Freq[i];
            Plo.M[1][j+1] = Freq[i];
        }
        for(i = 0,j = 0; i < Num; i++,j=j+2) {
            Plo1.M[0][j+1] = Freq[i];
            Plo1.M[1][j+1] = Freq[i+1];
            Plo1.M[0][j] = Vetor[i+1];
            Plo1.M[1][j] = Vetor[i+1];
        }
        return ret;
    }
}


