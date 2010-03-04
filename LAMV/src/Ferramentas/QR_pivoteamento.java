/*
 * QR_pivoteamento.java
 *
 * Created on 9 de Janeiro de 2007, 11:01
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Ferramentas;

import Dados.Matriz;
import java.util.Vector;

/**
 *
 * @author Anderson e Gustavo
 */
public class QR_pivoteamento {
    //private int m = 4, n = 2, mn = Math.min(m,n), E[] = new int[n];;
    //double A[][] = new double[m][n];
    private int m, n, mn, E[];
    String nome = "";
    double A[][];
    double Q[][], R[][];
    double SAFMIN = (2 * 2.2251e-308) / 2.2204e-016;
    double RSAFMN = 1 / SAFMIN;
    boolean pivoteamento;
    
    /** Creates a new instance of QR_pivoteamento */
    public QR_pivoteamento(Matriz M, boolean pivoteamento) {
        this.pivoteamento = pivoteamento;
        m = M.data.length;
        n = M.data[0].length;
        //m = 4;
        //n = 2;
        nome = M.getName();
        A = new double[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                A[i][j] = M.data[i][j];
            }
        }
        E = new int[n];
        this.qr_fatorizacao(pivoteamento);
    }
    
    void qr_fatorizacao(boolean pivo){
        
        int mn = java.lang.Math.min(m,n);
        double [][]tau = new double[mn][1];
        double []vn1 = new double[n];
        double []vn2 = new double[n];
        int i1,i2 = 0;
        
        if(pivo) {
            //Inicializa pivo
            for(int j = 1;j < n ;j++)
                E[j] = j;
            //Para toda coluna, calcula norma
            for(int j = 0; j < n; j++) {
                i1 = i2 + 1;
                i2 = i2 + m;
                vn1[j] = sub2norm(A, i1,i2);
                vn2[j] = vn1[j];
            }
        }
        for(int i= 0; i < mn; i++) {
            if(pivo) {
                
                // Determine ith pivo
                int pvt = i;
                double colmax = vn1[i];
                for(int k = i+1; k < n; k++) {
                    if(vn1[k] > colmax) {
                        pvt = k;
                        colmax = vn1[k];
                    }
                }
                /**     PARTE DE CÓDIGO NÃO TESTADA     **/
                if(pvt != i) {
                    for(int k = 0; k < m; k++) {
                        double temp = A[k][pvt];
                        A[k][pvt] = A[k][i];
                        A[k][i] = temp;
                    }
                    int itemp = E[pvt];
                    E[pvt] = E[i];
                    E[i] = itemp;
                    vn1[pvt] = vn1[i]; // Troca não necessária.
                    vn2[pvt] = vn2[i]; // Troca não necessária.
                }
                /**     FIM DA PARTE DO CÓDIGO NÃO TESTADA**/
            }
            
            //Gera H(i).
            i2 = (i+1) * m;
            i1 = i2 + 1 - (m - (i+1));
            double xnorm = sub2norm(A,i1,i2);
            double alphr = A[i][i];
            /** Parte imaginária **/
            //double alphi = imag(A(i,i));
            if(xnorm == 0)// && alphi == 0)
            {
                // H  =  I
                tau[i][0] = 0.0;
            } else {
                double beta = -fsign(JavaLapack(alphr, xnorm), alphr);
                double knt = 0;
                /** PARTE DE CÓDIGO NÃO TESTADA **/
                while(Math.abs(beta) < SAFMIN) {
                    knt = knt + 1;
                    for(int k = i1; k < i2; k++) {
                        int linha = k%m;
                        int coluna = k/m;
                        A[linha][coluna] = A[linha][coluna] * RSAFMN;
                    }
                    beta = beta * RSAFMN;
                    //alphi = alphi * RSAFMN;
                    alphr = alphr * RSAFMN;
                }
                if(knt > 0) {
                    xnorm = sub2norm(A, i1, i2);
                    //if isreal(A)
                    beta = -fsign(JavaLapack(alphr, xnorm), alphr);
                    //else
                    //    beta = -fsign(dlapy3(alphr, alphi, xnorm), alphr);
                }
                /** FIM DA PARTE DE CÓDIGO NÃO TESTADA **/
                //if isreal(A)
                int linha = i%m;
                int coluna = i/m;
                tau[linha][coluna] = (beta - alphr) / beta;
                double alpha = 1 / (alphr - beta);
                //else
                //tau(i) = complex((beta - alphr) / beta, -alphi / beta);
                //alpha = 1 / complex(alphr - beta, alphi);
                for(int k = i1-1; k < i2; k++) {
                    linha = k%m;
                    coluna = k/m;
                    A[linha][coluna] = A[linha][coluna] * alpha;
                }
                for(int k = 0;k < knt; k++)
                    beta = beta * SAFMIN;
                linha = i%m;
                coluna = i/m;
                A[linha][linha] = beta;
            }
            if(i < n)
                A = Householder(A,i,tau[i][0],m,n);
            if(pivo) {
                // Atualiza norma das colunas
                double temp1, temp2, jm;
                for(int j = i+1;j < n; j++) {
                    if(vn1[j] != 0) {
                        temp1 = 1 - Math.pow((Math.abs(A[i][j])/vn1[j]),2);
                        if(temp1 < 0)
                            temp1 = 0;
                        temp2 = 1 + 0.05*temp1*Math.pow((vn1[j]/vn2[j]),2);
                        if(temp2 == 1) {
                            if(i < m) {
                                jm = j*m;
                                vn1[j] = sub2norm(A, (int)jm-m+i+1, (int)jm);
                                vn2[j] = vn1[j];
                            } else {
                                vn1[j] = 0;
                                vn2[j] = 0;
                            }
                        } else
                            vn1[j] = vn1[j] * Math.sqrt(temp1);
                    }
                }
            }
        }
        //Extrai a matriz R ortonormal superior
        if(m > n) {
            R = extrairR(A,n,n);
            Q = extrairQ(A,tau);
        } else {
            R = extrairR(A,m,n);
            Q = extrairQ(A,tau);
        }
    }
    public Vector getQR(){
        Vector Ret = new Vector();
        Matriz Q;
        Matriz R;
        if(m > n) {
            Q = new Matriz(m,n,"Q_"+nome);
            R = new Matriz(n,n,"R_"+nome);
        } else {
            Q = new Matriz(m,m,"Q_"+nome);
            R = new Matriz(m,n,"R_"+nome);
        }
        Q.data = this.getQ();
        R.data = this.getR();
        if(pivoteamento) {
            Matriz EE = new Matriz(1,n,"E_"+nome);
            for(int k=0; k < n; k++)
                EE.data[0][k] = E[k];
            Ret.add(EE);
        }
        Ret.add(Q);
        Ret.add(R);
        return Ret;
    }
    public double[][] getR(){
        return R;
    }
    
    public double[][] getQ(){
        return Q;
    }
    
    public int[] getE(){
        return E;
    }
    
    
    //Calcula a norma do vetor coluna da matriz x
    private double sub2norm(double[][] x, int istart, int iend) {
        double scale = 0;
        double y = scale;
        int linha, coluna;
        if(istart > iend)
            return 0;
        for(int k = istart-1; k < iend; k++) {
            linha = k%m;
            coluna = k/m;
            double absx = java.lang.Math.abs(x[linha][coluna]);
            if(absx > 0) {
                if(scale < absx) {
                    y = 1 + y*java.lang.Math.pow(scale/absx,2);
                    scale = absx;
                } else
                    y = y + java.lang.Math.pow(absx/scale,2);
            }
        }
        y = scale * java.lang.Math.sqrt(y);
        return y;
    }
    
    /**************************************************************************
     * JavaLapack é uma implementação java da rotina JLapack do pacote original
     * Lapack escrito em Fortran. A seguir a descrição original do código fonte
     * em Fortran.
     *
     * "This interface converts 2D row-major arrays into
     * the 1D column-major linearized arrays expected by the lower
     * level JavaLapack routines.  Using this interface also allows you
     * to omit offset and leading dimension arguments.  However, because
     * of these conversions, these routines will be slower than the low
     * level ones.  Following is the description from the original Fortran
     * source."
     **************************************************************************/
    public double JavaLapack(double x1, double x2) {
        double a = Math.abs(x1);
        double b = Math.abs(x2);
        double y;
        if(a == 0) {
            y = b;
            return y;
        } else if(b == 0) {
            y = a;
            return y;
        } else if(b > a) {
            a = a / b;
            y = b * Math.sqrt(1 + a*a);
            return y;
        }
        b = b / a;
        y = a * Math.sqrt(b*b + 1);
        return y;
    }
    
    /** Troca o sinal se estiver negativo **/
    public double fsign(double a, double b) {
        double y = a;
        if( b < 0 ) {
            y = -y;
        }
        return y;
    }
    
    private double[][] Householder(double[][] A, int k, double alpha, int mm, int nn) {
        //Householder H(k)' em A(k:m,k+1:n) divisão a esquerda onde
        //H(k) = autovalor(m-k+1) - alpha*v*v' and v = [1;A(k+1:m,k)].
        double wj;
        if(alpha != 0) {
            for(int j = k +1; j < nn; j++) {
                wj = A[k][j];
                for(int i = k +1; i < mm; i++)
                    wj = wj + A[i][j]*A[i][k];
                wj = alpha*wj;
                if(wj != 0) {
                    A[k][j] = A[k][j] - wj;
                    for(int i = k + 1;i < mm; i++)
                        A[i][j] = A[i][j] - A[i][k]*wj;
                }
            }
        }
        return A;
    }
    
    //Extrair a matriz R
    private double[][] extrairR(double[][] A, int m, int n) {
        double [][]R = new double [m][n];
        for(int j = 0; j < n; j++) {
            for(int i = 0; i <= Math.min(j,m-1);i++)
                R[i][j] = A[i][j];
        }
        return R;
    }
    
    private double[][] extrairQ(double[][] A, double[][] tau) {
        int mm = m;
        int nn = Math.min(m,n);
        double [][]Q = new double[mm][nn];
        for(int i = 0; i < mm; i++)
            for(int j = 0; j < nn; j++)
                Q[i][j] = A[i][j];
        for (int i = nn-1; i >= 0 ; i--)//:-1:1
        {
            Q = Householder(Q,i,tau[i][0],mm,nn);
            double mtaui = -tau[i][0];
            if(i < mm-1) {
                for(int k = i+1;k < mm; k++)//:m
                {
                    Q[k][i] = Q[k][i]*mtaui;
                }
            }
            Q[i][i] = 1 + mtaui;
            for(int k = 0; k <= i-1; k++)//:i-1
            {
                Q[k][i] = 0;
            }
        }
        return Q;
    }
}