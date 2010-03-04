
package Ferramentas;

import java.util.Vector;
import Dados.*;
import Componentes.Grafico.PontoD;

public class Algebra {
    private int iDF = 0;
    
    private int n;
    private boolean Simetrica;
    private double[] d, e;
    private double[][] V;
    private String nome;
    
    
    
    public Constante Soma( Constante c1, Constante c2 ) {
        return new Constante( c1.getValor()+c2.getValor(), c1.getName()+"+"+c2.getName() );
    }
    public Matriz Soma( Matriz A, Matriz B ) {
        if ( A.getnLinhas() != B.getnLinhas() || A.getnColunas() != B.getnColunas() ) {
            /* impossivel somar, dimensões inválidas */
            return null;
        } else {
            int l,c;
            Matriz C = new Matriz( A.getnLinhas(), A.getnColunas(), A.getName()+"+"+B.getName() );
            for ( l = 0 ; l < A.getnLinhas() ; l++ ) {
                for ( c = 0; c < A.getnColunas() ; c++ ) {
                    C.M[l][c] = A.M[l][c] + B.M[l][c];
                }
            }
            return C;
        }
    }
    public Vetor Soma( Vetor v1, Vetor v2 ) {
        if( v1.getTam() != v2.getTam() ) {
            /* Impossivel somar, tamanhos diferentes */
            return null;
        }
        Vetor V = new Vetor( v1.getTam(), v1.getName()+"+"+v2.getName() );
        for( int c = 0; c < v1.getTam(); c++ ) {
            V.setValor( c, v1.getValor(c)+v2.getValor(c) );
        }
        return V;
    }
    public Dado Soma( Dado d1, Dado d2 ) {
        if( d1.eMatriz() && d2.eMatriz() )
            return Soma( (Matriz)d1, (Matriz)d2 );
        if( d1.eVetor() && d2.eVetor() )
            return Soma( (Vetor)d1, (Vetor)d2 );
        if( d1.eConstante() && d2.eConstante() )
            return Soma( (Constante)d1, (Constante)d2 );
        return null;
        
    }
    
    public Constante Sub( Constante c1, Constante c2 ) {
        return new Constante( c1.getValor()-c2.getValor(), c1.getName()+"-"+c2.getName() );
    }
    public Matriz Sub( Matriz A, Matriz B ) {
        if ( A.getnLinhas() != B.getnLinhas() || A.getnColunas() != B.getnColunas() ) {
            /* impossivel somar, dimensões inválidas */
            return null;
        } else {
            int l,c;
            Matriz C = new Matriz( A.getnLinhas(), A.getnColunas(), A.getName()+"-"+B.getName() );
            for ( l = 0 ; l < A.getnLinhas() ; l++ ) {
                for ( c = 0; c < A.getnColunas() ; c++ ) {
                    C.M[l][c] = A.M[l][c] - B.M[l][c];
                }
            }
            return C;
        }
    }
    public Vetor Sub( Vetor v1, Vetor v2 ) {
        if( v1.getTam() != v2.getTam() ) {
            /* Impossivel somar, tamanhos diferentes */
            return null;
        }
        Vetor V = new Vetor( v1.getTam(), v1.getName()+"-"+v2.getName() );
        for( int c = 0; c < v1.getTam(); c++ ) {
            V.setValor( c, v1.getValor(c)-v2.getValor(c) );
        }
        return V;
    }
    public Dado Sub( Dado d1, Dado d2 ) {
        if( d1.eMatriz() && d2.eMatriz() )
            return Sub( (Matriz)d1, (Matriz)d2 );
        if( d1.eVetor() && d2.eVetor() )
            return Sub( (Vetor)d1, (Vetor)d2 );
        if( d1.eConstante() && d2.eConstante() )
            return Sub( (Constante)d1, (Constante)d2 );
        return null;
        
    }
    
    public Constante Mult( Constante c1, Constante c2 ) {
        return new Constante( c1.getValor()*c2.getValor(), c1.getName()+"*"+c2.getName() );
    }
    public Vetor Mult( Constante c, Vetor v ) {
        return Mult( v, c );
    }
    public Vetor Mult( Vetor v, Constante c ) {
        Vetor V = new Vetor( v.getTam(), c.getName()+"*"+v.getName() );
        double val = c.getValor();
        for( int x = 0; x < v.getTam(); x++ ) {
            V.setValor( x, val*v.getValor(x) );
        }
        return V;
    }
    public Matriz Mult( Matriz A, Matriz B ) {
        if(A.getnColunas() != B.getnLinhas() ) {
            // impossivel multiplicar, dimensões inválidas
            return null;
        } else {
            // multiplicacao possivel
            int l,c,k;
            double soma;
            Matriz C = new Matriz(A.getnLinhas(),B.getnColunas(),A.getName()+"*"+B.getName());
            
            for ( l = 0 ; l < A.getnLinhas() ; l++ ) {
                for ( c = 0 ; c < B.getnColunas() ; c++ ) {
                    soma = 1;
                    soma--;
                    for ( k = 0 ; k < A.getnColunas() ; k++ ) {
                        soma += A.M[l][k] * B.M[k][c];
                    }
                    C.M[l][c] = soma;
                }
            }
            return C;
        }
    }
    public Matriz Mult( Vetor v, Matriz m ) {
        Matriz temp = Sistema.FSys.Vet_to_Mat( v );
        return Mult( temp, m );
    }
    public Matriz Mult( Matriz m, Vetor v ) {
        Matriz temp = Sistema.FSys.Vet_to_Mat( v );
        return Mult( m, v );
    }
    public Dado Mult( Dado d1, Dado d2 )  {
        if( d1.eConstante() && d2.eConstante() )
            return Mult( (Constante)d1 , (Constante)d2 );
        if( d1.eConstante() && d2.eVetor() )
            return Mult( (Constante)d1, (Vetor)d2 );
        if( d1.eVetor() && d2.eConstante() )
            return Mult( (Vetor)d1, (Constante)d2 );
        if( d1.eMatriz() && d2.eMatriz() )
            return Mult( (Matriz)d1, (Matriz)d2 );
        if( d1.eVetor() && d2.eMatriz() )
            return Mult( (Vetor)d1, (Matriz)d2 );
        if( d1.eMatriz() && d2.eVetor() )
            return Mult( (Matriz)d1, (Vetor)d2 );
        return null;
        
        
    }
    
    public Matriz Divide( Matriz A, Matriz B ) {
        int l,c,k;
        double soma;
        if( (A.getnLinhas() == B.getnLinhas()) && (A.getnColunas() == B.getnColunas())) {
            //somente matrizes de mesma dimensão
            Matriz C = new Matriz(A.getnLinhas(),A.getnColunas(),A.getName()+"/"+B);
            for ( l = 0 ; l < A.getnLinhas() ; l++ ) {
                for( c = 0; c < A.getnColunas(); c++) {
                    if(B.M[l][c] != 0)//testa numerador diferente de zero
                        C.M[l][c] = A.M[l][c]/B.M[l][c];
                    else return null;
                }
            }
            return C;
        }
        return null;
    }
    public Matriz Divide( Matriz A, double b) {
        int l,c,k;
        double soma;
        if( b != 0 )//testa numerador diferente de zero
        {
            Matriz C = new Matriz(A.getnLinhas(),A.getnColunas(),A.getName()+"/"+b);
            for ( l = 0 ; l < A.getnLinhas() ; l++ ) {
                for( c = 0; c < A.getnColunas(); c++)
                    C.M[l][c] = A.M[l][c]/b;
            }
            return C;
        }
        return null;
    }
    public Dado Divide( Dado d1, Dado d2 ) {
        if( d1.eMatriz() && d2.eMatriz() )
            return Divide( (Matriz)d1, (Matriz)d2 );
        if( d1.eMatriz() && d2.eConstante() )
            return Divide( (Matriz)d1, (Constante)d2 );
        return null;
    }
    
    // Procedimento retorna uma Matriz de zeros N x N
    public Matriz Zeros( int n ) {
        Matriz Z = new Matriz(n,n,"Zeros");
        int l,c;
        for ( l = 0; l < n; l++ )
            for ( c = 0; c < n; c++ ) {
            Z.M[l][c] = 1;
            Z.M[l][c]--;
            }
        return Z;
    }
    
    public Matriz Zera( Matriz A ) {
        int l;
        int c;
        for ( l = 0; l < A.getnLinhas(); l++ ) {
            for ( c = 0; c < A.getnColunas(); c++ ) {
                A.M[l][c] = 0.0D;
            }
        }
        return A;
    }
    
    public Matriz Sqrt(Matriz M) {
        int l;
        int c;
        Matriz A = new Matriz(M.getnLinhas(),M.getnColunas(),"raiz_"+M.getName());
        for ( l = 0; l < A.getnLinhas(); l++ ) {
            for ( c = 0; c < A.getnColunas(); c++ ) {
                A.M[l][c] = Math.sqrt(M.M[l][c]);
            }
        }
        return A;
    }
    
    // Procedimento retorna uma Matriz Identidade N x N
    public Matriz Ident( int n ) {
        Matriz I = Zeros(n);
        int i;
        I.setName("I");
        for ( i = 0; i < n; i++ )
            I.M[i][i] = 1.0;
        return I;
    }
    
    //---------------------------------------------------------------------------
    public Matriz MatrizReduzida( Matriz A , int linha , int coluna ) {
        Matriz MatrizR = new Matriz( A.getnLinhas()-1 , A.getnColunas()-1 ,"Reduz("+linha+","+coluna+")_de_"+A.getName() );
        int l,c,la,ca;
        
        if( linha >= A.getnLinhas() ) {
            System.out.println("Elemento nao pertence a matriz! Erro de indexação!");
            return null;
        }
        l = 0;
        System.out.println();
        
        l = 0;
        for( la = 0; la < A.getnLinhas(); la++ ) {
            c = 0;
            if( la == linha ) la++;
            for( ca = 0; ca < A.getnColunas(); ca++ ) {
                if( ca == coluna ) ca++;
                MatrizR.M[l][c] = A.M[la][ca];
                c++;
            }
            l++;
        }
        return MatrizR;
    }
    
    /*
     *
     *           | a b c |
     * Det(A) =  | d e f |  =  a*e*i + b*f*g + c*d*h - h*f*a - i*d*b - g*e*c
     *           | g h i |
     *
     *           | a b |
     * Det(A) =  | c d |  =  a*d - b*c
     *
     */
    public double Det( Matriz A ) {
        double d = 0d;
        if( A.getnLinhas() == 2 ) {
            d = ( A.M[0][0]*A.M[1][1] ) - ( A.M[0][1]*A.M[1][0] );
            System.out.println(""+d);
            return d;
        } else {
            for( int c = 0; c < A.getnLinhas(); c ++ ) {
                d = d + ( Det( Mcof(A,c,0) ) * java.lang.Math.pow( (-1) , c+1 ) );
            }
            System.out.println(""+d);
            return d;
        }
    }
    
    /*
     * Retorna o produto das colunas
     */
    public Matriz Produto( Matriz A ) {
        Matriz M = new Matriz(1, A.getnColunas() );
        for( int cols = 0; cols < M.getnColunas(); cols++ ) {
            double prod = 1;
            for( int lins = 0; lins < A.getnLinhas(); lins++ ) {
                prod = prod * A.M[lins][cols];
            }
            M.M[0][cols] = prod;
        }
        return M;
    }
    
    public double Cofator( Matriz A ) {
        return 0d;
    }
    
    public void PrintM( Matriz A ) {
        int l;
        int c;
        System.out.println();
        for ( l = 0; l < A.getnLinhas(); l++ ) {
            System.out.println();
            for ( c = 0; c < A.getnColunas(); c++ ) {
                System.out.print(" "+A.M[l][c]+" ");
            }
        }
    }
    
    public Matriz Transposta( Matriz A ) {
        Matriz T = new Matriz(A.getnColunas(), A.getnLinhas(), A.getName()+"'");
        int l;
        int c;
        
        for ( l = 0; l < A.getnLinhas() ; l++)
            for (c = 0; c < A.getnColunas(); c++)
                T.M[c][l] = A.M[l][c];
        return T;
    }
    
    public static double[][] Transposta(double ad[][]) throws IllegalArgumentException {
        int ai[] = new int[1];
        int ai1[] = new int[1];
        int j;
        int k;
        try {
            CheckMatrix(ad, ai, ai1);
            j = ai[0];
            k = ai1[0];
        } catch(IllegalArgumentException illegalargumentexception) {
            throw illegalargumentexception;
        }
        double ad1[][] = new double[k][j];
        for(int i = 0; i < k; i++) {
            for(int l = 0; l < j; l++)
                ad1[i][l] = ad[l][i];
        }
        return ad1;
    }
    // Procedimento retorna uma determinada parte da Matriz A definida por l1,l2,c1,c2
    // Essa nova Matriz compreende os valores da Matriz A que vai de l1 a l2 e de c1 a c2
    public Matriz RecortM( Matriz A, int l1 , int c1, int l2 , int c2 ) {
        if ( l1 < 0  || l1 > A.getnLinhas()  || c1 < 0  || c1 > A.getnColunas()   // Verifica se
        || l2 < 0  || l2 > A.getnLinhas()  || l2 < l1 || c2 < 0           // os indices
                || c2 < c1 || c2 > A.getnColunas() )                              // sao validos
        {
            return null;
        } else {
            Matriz TEMP = new Matriz(l2-(l1-1),c2-(c1-1),"Recorte_"+A.getName());
            int l,c;
            int contl = 0;
            int contc = 0;
            for (l = l1; l <= l2 ; l++ ) {
                contc = 0;
                for (c = c1; c <= c2 ; c++ ) {
                    TEMP.M[contl][contc] = A.M[l][c];
                    contc++;
                }
                contl++;
            }
            return TEMP;
        }
    }
    public Vetor RecortaV( Vetor V, int ini, int off ) {
        if( V == null )
            return null;
        if( off < ini )
            return null;
        if( V.getTam() <= off )
            return null;
        Vetor Temp = new Vetor( off - ini + 1  , "Recorte_"+V.getName() );
        int dim = off - ini + 1;
        System.out.println("Tamanho do Vetor: "+dim+"");
        int cont = 0;
        for( int c = ini; c <= off; c++ ) {
            Temp.setValor( cont, V.getValor(c) );
            cont++;
        }
        return Temp;
    }
    
    //---------------------------------------------------------------------------
    
    // Procedimento retorna a Matriz que define o adjunto do elemento A(l,c)
    public Matriz Mcof( Matriz A, int linha, int coluna ) {
        // retorna a Matriz determina o cofator do elemento A(l,c)
        if ( linha > A.getnLinhas() || linha < 0 || coluna > A.getnColunas() || coluna < 0) {
            // verifica se o elemento e valido
            return null;
        } else {
            Matriz M = new Matriz( A.getnLinhas() - 1, A.getnColunas() - 1, "MCof_"+A.getName() );
            int ll = 0;
            int cc = 0;
            for( int l = 0; l < A.getnLinhas(); l++ ) {
                cc = 0;
                for( int c = 0; c < A.getnColunas(); c++ ) {
                    if( l == linha ) l++;
                    if( c == coluna ) c++;
                    if( l >= A.getnLinhas() ) break;
                    if( c >= A.getnColunas() ) break;
                    M.M[ll][cc] = A.M[l][c];
                    cc++;
                }
                ll++;
            }
            M.info();
            return M;
        }
    }
    
    //---------------------------------------------------------------------------
    
    // Procedimento soma as linhas l1 e l2 da Matriz A,
    // retornando o resultado na Matriz R
    public Matriz SomaLinha( Matriz A, int l1, int l2 ) {
        Matriz R = new Matriz(1,A.getnColunas(),"Sub[ "+A.getName()+"("+l1+",:)"+"-"+A.getName()+"("+l2+",:)"+"]");
        R.Zera();
        int i;
        if ( l1 < 0 && l1 >= A.getnLinhas() || l2 < 0 && l2 >= A.getnLinhas() )
            return null;
        for ( i = 0 ; i < A.getnColunas() ; i++ )
            R.M[0][i] = A.M[l1][i] + A.M[l2][i];
        return R;
    }
    
    //---------------------------------------------------------------------------
    
    // Procedimento subtrai as linhas l1 e l2 da Matriz A,
    // retornando o resultado na Matriz R
    public Matriz SubLinha( Matriz A, int l1, int l2 ) {
        Matriz R = new Matriz(1,A.getnColunas(),"Sub[ "+A.getName()+"("+l1+",:)"+"-"+A.getName()+"("+l2+",:)"+"]");
        int i;
        if ( l1 < 0 && l1 >= A.getnLinhas() || l2 < 0 && l2 >= A.getnLinhas() )
            return null;
        for ( i = 0 ; i < A.getnColunas() ; i++ )
            R.M[0][i] = A.M[l1][i] - A.M[l2][i];
        return R;
    }
    
    //---------------------------------------------------------------------------
    
    // Procedimento multiplica uma linha da Matriz A por uma constante val,
    // retornando o resultado na Matriz R
    public Matriz MultLinha( Matriz A, int l, double val ) {
        Matriz R = new Matriz(1,A.getnColunas(),"Mult["+A.getName()+"("+l+",:)"+"*"+val+"]");
        int i;
        if ( l < 0 && l >= A.getnLinhas() )
            return null;
        for ( i = 0 ; i < A.getnColunas() ; i++ )
            R.M[0][i] = A.M[l][i] * val;
        return R;
    }
    
    //---------------------------------------------------------------------------
    
/*
    public Matriz getElipse( Matriz M ) {
        double a;
        double b;
        double x;
        double y;
        double c;    // Distancia
        double teta; // Angulo
 
        Estat Est = new Estat();
 
        Matriz A = Est.MCov(M);
        Matriz X = new Matriz(2,1);
 
        Matrix A_ = new Matrix(A.M);
 
        EigenvalueDecomposition E =  new EigenvalueDecomposition( A_ );
        double[] Values = E.getRealEigenvalues();
        Matrix V = E.getV();
 
        X.M[0][0] = Values[0];
        X.M[1][0] = Values[1];
 
        Matriz C = Mult( Mult( Transposta(X), A ) , X );
        c = Math.sqrt(C.M[0][0]);
 
        a = c / X.M[0][0];
        b = c / X.M[1][0];
 
        double tt;
        int cont = 0;
        teta = 1.54;
 
        Matriz R = new Matriz((int)(10/0.05)*2+4,2);
 
        double mx = Est.MediaCol(M,0);
        double my = Est.MediaCol(M,0);
 
        for( x = -5 ; x < 5 ; x += 0.05)
        {
            tt = 1 - (x*x)/4;
            if ( tt < 0 )
            {
                y = Math.sqrt( -1*( 1 - (x*x)/(a*a))*(b*b) );
            }
            else{
                y = Math.sqrt( tt );
            }
 
            R.M[cont][0] = x*Math.cos(teta) + y*Math.sin(teta) + mx;
            R.M[cont][1] = -x*Math.sin(teta)+ y*Math.cos(teta) + my;
            cont++;
        }
        for( x = -5 ; x < 5 ; x += 0.05)
        {
            tt = 1 - (x*x)/4;
            if ( tt < 0 )
            {
                y = Math.sqrt( -1*( 1 - (x*x)/(a*a))*(b*b) );
            }
            else{
                y = Math.sqrt( tt );
            }
            R.M[cont][0] = -(x*Math.cos(teta) + y*Math.sin(teta)) + mx;
            R.M[cont][1] = (x*Math.sin(teta)- y*Math.cos(teta)) + my;
            cont++;
        }
 
 
        return R;
    }
 
 */
    
    public double Distancia( PontoD p1, PontoD p2 ) {
        double dis;
        
        double base;
        double altura;
        
        base = p1.x - p2.x;
        if( base < 0 )
            base = base*(-1);
        altura = p1.y - p2.y;
        if( altura < 0 )
            altura = altura*(-1);
        dis = Math.sqrt( base*base + altura*altura );
        return dis;
    }
    
    /*
     * Retorna o ângulo formado com o eixo X pela reta que passa pelos pontos
     * p1 e p2.
     */
    public double getAngulo( PontoD p1, PontoD p2 ) {
        double base;
        double altura;
        
        base = p1.x - p2.x;
        altura = p1.y - p2.y;
        return 0;
        
    }
    
    public Matriz Elipse(double mx, double my, PontoD A, PontoD B, double res ) {
        Matriz M = null;
        
        
        
        return M;
    }
    
    public Matriz Elipse( double mx, double my , double a, double b, double teta, double precisao ) {
        double x,y;
        double tt;
        Matriz M = new Matriz((int)(5*a/precisao)+1,2);
        int c = 0;
        for( x = -a ; x <= a ; x += precisao ) {
            tt = 1 - (x*x)/4;
            if ( tt < 0 ) {
                y = Math.sqrt( -1*( 1 - (x*x)/(a*a))*(b*b) );
            } else{
                y = Math.sqrt( tt );
            }
            M.M[c][0] = x*Math.cos(teta) + y*Math.sin(teta) + mx;
            M.M[c][1] =  -x*Math.sin(teta)+ y*Math.cos(teta) + my;
            c++;
        }
        for( x = -a ; x < a+precisao ; x += precisao ) {
            tt = 1 - (x*x)/4;
            if ( tt < 0 ) {
                y = Math.sqrt( -1*( 1 - (x*x)/(a*a))*(b*b) );
            } else{
                y = Math.sqrt( tt );
            }
            M.M[c][0] = -(x*Math.cos(teta) + y*Math.sin(teta)) + mx;
            M.M[c][1] =  x*Math.sin(teta)- y*Math.cos(teta) + my;
            c++;
        }
        M.setVerPontos(false);
        return M;
    }
    
    public Matriz Elipse2( double a, double b, double teta, double precisao ) {
        double x,y;
        double tt;
        Matriz M = new Matriz((int)(4*a/precisao)+1,2);
        int c = 0;
        for( x = -a ; x <= a ; x += precisao ) {
            tt = 1 - (x*x)/4;
            if ( tt < 0 ) {
                y = Math.sqrt( -1*( 1 - (x*x)/(a*a))*(b*b) );
            } else{
                y = Math.sqrt( tt );
            }
            M.M[c][0] = x*Math.cos(teta) + y*Math.sin(teta);
            M.M[c][1] =  -x*Math.sin(teta)+ y*Math.cos(teta);
            c++;
        }
        for( x = -a ; x < a+precisao ; x += precisao ) {
            tt = 1 - (x*x)/4;
            if ( tt < 0 ) {
                y = Math.sqrt( -1*( 1 - (x*x)/(a*a))*(b*b) );
            } else{
                y = Math.sqrt( tt );
            }
            M.M[c][0] = -(x*Math.cos(teta) + y*Math.sin(teta));
            M.M[c][1] =  x*Math.sin(teta)- y*Math.cos(teta);
            c++;
        }
        M.setVerPontos(false);
        return M;
    }
    
    
    //---------------------------------------------------------------------------
    /** Concatena uma Matriz Identidade com a Matriz A, e retorna o resultado em R */
    public Matriz Concat( Matriz A ) {
        Matriz R = new Matriz( A.getnLinhas() , A.getnColunas() + A.getnLinhas() , A.getName()+"");
        
        Matriz I = Ident(A.getnLinhas());
        int l;
        int c;
        int col;
        for ( l = 0; l < A.getnLinhas(); l++ )
            for ( c = 0; c < A.getnColunas(); c++ )
                R.M[l][c] = A.M[l][c];
        for ( l = 0; l < A.getnLinhas(); l++ )
            for ( c = A.getnColunas() , col = 0; c < R.getnColunas(); c++ , col++)
                R.M[l][c] = I.M[l][col];
        return R;
    }
    
    /** Concatena uma matriz B a direita de uma matriz A
     * @param A Matriz a esquerda
     * @param B Matriz a direita
     * @return Matriz AB */
    public static Matriz ConcatDireita( Matriz A, Matriz B)throws Exception {
        if(A.getDimL() != A.getDimL()) {
            throw new Exception("As duas matrizes devem ter a mesma quantidade de linhas");
        } else {
            Matriz AB = new Matriz(A.getDimL(),A.getDimC()+B.getDimC());
            for(int i = 0; i < A.getDimL(); i++) {
                for(int j = 0; j < A.getDimC() + A.getDimC(); j++) {
                    if( j < A.getDimC()) {
                        AB.M[i][j] = A.M[i][j];
                    } else {
                        AB.M[i][j] = B.M[i][j-A.getDimC()];
                    }
                }
            }
            return AB;
        }
        
    }
    /** Concatena uma matriz B abaixo de uma matriz A
     * @param A Matriz em cima
     * @param B Matriz em baixo
     * @return Matriz AB */
    public static Matriz ConcatAbaixo( Matriz A, Matriz B)throws Exception {
        if(A.getDimC() != B.getDimC()) {
            throw new Exception("As duas matrizes devem ter a mesma quantidade de colunas");
        } else {
            Matriz AB = new Matriz(A.getDimL()+A.getDimL(),A.getDimC());
            for(int i = 0; i < A.getDimL() + B.getDimL(); i++) {
                for(int j = 0; j < A.getDimC(); j++) {
                    if( i < A.getDimL()) {
                        AB.M[i][j] = A.M[i][j];
                    } else {
                        AB.M[i][j] = B.M[i-A.getDimL()][j];
                    }
                }
            }
            return AB;
        }
        
    }
    
    //---------------------------------------------------------------------------
    
    public Matriz Inv( Matriz A ) {
        Matriz X = Concat(A);
        int c;
        int c1;
        int l;
        double pivo = 1;
        double ZERO = 1;
        ZERO--;
        
        if ( A.getnLinhas() != A.getnColunas() )       // A matriz nao e quadrada
            return null;
        for ( c = 0; c < X.getnColunas() - A.getnLinhas(); c++ ) {
            for ( l = c+1; l < X.getnLinhas(); l++ ) {
                if (X.M[l][c] == ZERO ) {
                    pivo = ZERO;
                } else {
                    if ( X.M[c][c] == ZERO )       // indeterinacao na divisao
                    {
                        return null;
                    } else {
                        pivo = X.M[l][c]/X.M[c][c];
                    }
                }
                for ( c1 = 0; c1 < X.getnColunas(); c1++ ) {
                    X.M[l][c1] = X.M[l][c1] - pivo*X.M[c][c1];
                }
            }
        }
        for ( c = 1; c < X.getnColunas() - A.getnLinhas(); c++ ) {
            for ( l = 0; l < c; l++ ) {
                if (X.M[l][c] == ZERO ) {
                    pivo = 1;
                    pivo--;
                } else {
                    if ( X.M[c][c] == ZERO ) {
                        return null;
                    } else {
                        pivo = X.M[l][c]/X.M[c][c];
                    }
                }
                for ( c1 = 0; c1 < X.getnColunas(); c1++ ) {
                    X.M[l][c1] = X.M[l][c1] - pivo*X.M[c][c1];
                }
            }
        }
        for ( l = 0; l < X.getnColunas() - X.getnLinhas(); l++ ) {
            pivo = X.M[l][l];
            for ( c = 0; c < X.getnColunas(); c++ ) {
                X.M[l][c] = X.M[l][c]/pivo;
            }
        }
        Matriz R = RecortM( X , 0, A.getnColunas() , X.getnLinhas()-1 , X.getnColunas()-1 );
        R.setName("Inv_"+A.getName());
        return R;
    }
    
    
    public double media_coluna(Matriz M,int coluna) {
        double soma = 0;
        int i;
        for(i = 0; i<M.getnLinhas(); i++)
            soma = soma + M.M[i][coluna];
        return(soma/i);
    }
    public double max(Matriz M) {
        int i,j;
        double Max = M.M[0][0];
        for(i =0; i<M.getnLinhas(); i++) {
            for(j = 0; j<M.getnColunas(); j++) {
                if(Max < M.M[i][j])
                    Max = M.M[i][j];
            }
        }
        return Max;
    }
    
    public double min(Matriz M) {
        int i,j;
        double Min = M.M[0][0];
        for(i =0; i<M.getnLinhas(); i++) {
            for(j = 0; j<M.getnColunas(); j++) {
                if(Min > M.M[i][j])
                    Min = M.M[i][j];
            }
        }
        return Min;
    }
    public double var(Matriz M,int i) {
        double media_i,tamanho,soma;
        soma = 0;
        tamanho = M.getnColunas();
        media_i = this.media_coluna(M,i);
   /*System.out.println(M.M[0][0]);
   System.out.println(M.M[1][0]);
   System.out.println(M.M[2][0]);*/
        for(int k = 0; k < M.getnLinhas(); k++) {
            soma = soma + Math.pow( M.M[k][i] - media_i, 2);
        }
        soma = soma / tamanho;
        soma = Math.sqrt(soma);
        return soma;
    }
    
    public double var(Matriz M,int i,int j) {
        double media_i,media_j,tamanho,soma;
        soma = 0;
        tamanho = M.getnColunas();
        media_i = this.media_coluna(M,i);
        media_j = this.media_coluna(M,j);
        for(int k = 0; k < i; k++) {
            soma = soma + ( (M.M[k][i] - media_i)*(M.M[k][j] - media_j) );
        }
        soma = soma / (tamanho-1);
        return soma;
    }
    
    /*************************Método para encontrar autovalor e autovetor*****************************/
/*
Se Arg é simétrica, então, A = V*D*V', onde a matriz de autovalores D é
    diagonal e a matriz de autovetores V é orthogonal.
 */
    public Vector Eig(Matriz Arg) {
        double H[][];
        double ort[];
        Vector Ret = new Vector(2);
        nome = Arg.getName();
        double[][] A = Arg.getMatriz();
        n = Arg.getnColunas();
        V = new double[n][n];
        d = new double[n];
        e = new double[n];
        
        Simetrica = true;
        for (int j = 0; (j < n) & Simetrica; j++) {
            for (int i = 0; (i < n) & Simetrica; i++) {
                Simetrica = (A[i][j] == A[j][i]);
            }
        }
        if (Simetrica) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    V[i][j] = A[i][j];
                
            }
            // Tridiagonal
            //this.tred2();
            for (int j = 0; j < n; j++)
                d[j] = V[n-1][j];
            
            // redução de Householder para forma tridiagonal
            for (int i = n-1; i > 0; i--) {
                double scale = 0.0;
                double h = 0.0;
                for (int k = 0; k < i; k++)
                    scale = scale + Math.abs(d[k]);
                if (scale == 0.0) {
                    e[i] = d[i-1];
                    for (int j = 0; j < i; j++) {
                        d[j] = V[i-1][j];
                        V[i][j] = 0.0;
                        V[j][i] = 0.0;
                    }
                } else {
                    // Gera vetor de Householder
                    for (int k = 0; k < i; k++) {
                        d[k] /= scale;
                        h += d[k] * d[k];
                    }
                    double f = d[i-1];
                    double g = Math.sqrt(h);
                    if (f > 0)
                        g = -g;
                    e[i] = scale * g;
                    h = h - f * g;
                    d[i-1] = f - g;
                    for (int j = 0; j < i; j++)
                        e[j] = 0.0;
                    // Aplique transformação semelhante a colunas restantes.
                    for (int j = 0; j < i; j++) {
                        f = d[j];
                        V[j][i] = f;
                        g = e[j] + V[j][j] * f;
                        for (int k = j+1; k <= i-1; k++) {
                            g += V[k][j] * d[k];
                            e[k] += V[k][j] * f;
                        }
                        e[j] = g;
                    }
                    f = 0.0;
                    for (int j = 0; j < i; j++) {
                        e[j] /= h;
                        f += e[j] * d[j];
                    }
                    double hh = f / (h + h);
                    for (int j = 0; j < i; j++)
                        e[j] -= hh * d[j];
                    for (int j = 0; j < i; j++) {
                        f  = d[j];
                        g = e[j];
                        for (int k = j; k <= i-1; k++)
                            V[k][j] -= (f * e[k] + g * d[k]);
                        d[j] = V[i-1][j];
                        V[i][j] = 0.0;
                    }
                }
                d[i] = h;
            }
            
            // Acumula transformações
            for (int i = 0; i < n-1; i++) {
                V[n-1][i] = V[i][i];
                V[i][i] = 1.0;
                double h = d[i+1];
                if (h != 0.0) {
                    for (int k = 0; k <= i; k++)
                        d[k] = V[k][i+1] / h;
                    for (int j = 0; j <= i; j++) {
                        double g = 0.0;
                        for (int k = 0; k <= i; k++)
                            g += V[k][i+1] * V[k][j];
                        for (int k = 0; k <= i; k++)
                            V[k][j] -= g * d[k];
                    }
                }
                for (int k = 0; k <= i; k++)
                    V[k][i+1] = 0.0;
            }
            for (int j = 0; j < n; j++) {
                d[j] = V[n-1][j];
                V[n-1][j] = 0.0;
            }
            V[n-1][n-1] = 1.0;
            e[0] = 0.0;
            /*************************************/
            // Diagonaliza.
            //this.tql2();
            
            for (int i = 1; i < n; i++)
                e[i-1] = e[i];
            e[n-1] = 0.0;
            double f = 0.0;
            double tst1 = 0.0;
            double eps = Math.pow(2.0,-52.0);
            for (int l = 0; l < n; l++) {
                // procura pequena subdiagonal
                tst1 = Math.max(tst1,Math.abs(d[l]) + Math.abs(e[l]));
                int m = l;
                while (m < n) {
                    if (Math.abs(e[m]) <= eps*tst1)
                        break;
                    m++;
                }
                // Se m == l, d[l] é um autovalor
                // caso contrário continua
                if (m > l) {
                    int iter = 0;
                    do
                    {
                        iter = iter + 1;
                        // troca implícita
                        double g = d[l];
                        double p = (d[l+1] - g) / (2.0 * e[l]);
                        double r = this.hypot(p,1.0);
                        if (p < 0)
                            r = -r;
                        d[l] = e[l] / (p + r);
                        d[l+1] = e[l] * (p + r);
                        double dl1 = d[l+1];
                        double h = g - d[l];
                        for (int i = l+2; i < n; i++)
                            d[i] -= h;
                        f = f + h;
                        // QL Transformação
                        p = d[m];
                        double c = 1.0;
                        double c2 = c;
                        double c3 = c;
                        double el1 = e[l+1];
                        double s = 0.0;
                        double s2 = 0.0;
                        for (int i = m-1; i >= l; i--) {
                            c3 = c2;
                            c2 = c;
                            s2 = s;
                            g = c * e[i];
                            h = c * p;
                            r = this.hypot(p,e[i]);
                            e[i+1] = s * r;
                            s = e[i] / r;
                            c = p / r;
                            p = c * d[i] - s * g;
                            d[i+1] = h + s * (c * g + s * d[i]);
                            for (int k = 0; k < n; k++) {
                                h = V[k][i+1];
                                V[k][i+1] = s * V[k][i] + c * h;
                                V[k][i] = c * V[k][i] - s * h;
                            }
                        }
                        p = -s * s2 * c3 * el1 * e[l] / dl1;
                        e[l] = s * p;
                        d[l] = c * p;
                        // verifica convergência
                    } while (Math.abs(e[l]) > eps*tst1);
                }//fim if
                d[l] = d[l] + f;
                e[l] = 0.0;
            }// fim for
            
            // ordena valores e vetores correspondentes.
            for (int i = 0; i < n-1; i++) {
                int k = i;
                double p = d[i];
                for (int j = i+1; j < n; j++) {
                    if (d[j] < p) {
                        k = j;
                        p = d[j];
                    }
                }
                if (k != i) {
                    d[k] = d[i];
                    d[i] = p;
                    for (int j = 0; j < n; j++) {
                        p = V[j][i];
                        V[j][i] = V[j][k];
                        V[j][k] = p;
                    }
                }
            }
            /************************************/
        } else {
            H = new double[n][n];
            ort = new double[n];
            for (int j = 0; j < n; j++)
                for (int i = 0; i < n; i++)
                    H[i][j] = A[i][j];
            // Reduz a forma de Hessemberg
            //this.orthes();
            int low = 0;
            int high = n-1;
            for (int m = low+1; m <= high-1; m++) {
                // Escale coluna.
                double scale = 0.0;
                for (int i = m; i <= high; i++)
                    scale = scale + Math.abs(H[i][m-1]);
                if (scale != 0.0) {
                    // calcula transformação de Householder
                    double h = 0.0;
                    for (int i = high; i >= m; i--) {
                        ort[i] = H[i][m-1]/scale;
                        h += ort[i] * ort[i];
                    }
                    double g = Math.sqrt(h);
                    if (ort[m] > 0)
                        g = -g;
                    h = h - ort[m] * g;
                    ort[m] = ort[m] - g;
                    // H = (I-u*u'/h)*H*(I-u*u')/h)
                    for (int j = m; j < n; j++) {
                        double f = 0.0;
                        for (int i = high; i >= m; i--)
                            f += ort[i]*H[i][j];
                        f = f/h;
                        for (int i = m; i <= high; i++)
                            H[i][j] -= f*ort[i];
                    }
                    for (int i = 0; i <= high; i++) {
                        double f = 0.0;
                        for (int j = high; j >= m; j--)
                            f += ort[j]*H[i][j];
                        f = f/h;
                        for (int j = m; j <= high; j++)
                            H[i][j] -= f*ort[j];
                    }
                    ort[m] = scale*ort[m];
                    H[m][m-1] = scale*g;
                }
            }
            
            
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    V[i][j] = (i == j ? 1.0 : 0.0);
            
            for (int m = high-1; m >= low+1; m--) {
                
                
                
                if (H[m][m-1] != 0.0) {
                    for (int i = m+1; i <= high; i++)
                        ort[i] = H[i][m-1];
                    for (int j = m; j <= high; j++) {
                        double g = 0.0;
                        for (int  i = m; i <= high; i++)
                            g += ort[i] * V[i][j];
                        // divisão de double possivel flow
                        g = (g / ort[m]) / H[m][m-1];
                        for (int i = m; i <= high; i++)
                            V[i][j] += g * ort[i];
                    }
                }
            }
            /************************************/
            // Reduz matriz de  Hessenberg para forma real de Schur
            //this.hqr2();
            int nn = this.n;
            int n = nn-1;
            low = 0;
            high = nn-1;
            double eps = Math.pow(2.0,-52.0);
            double exshift = 0.0;
            double p=0,q=0,r=0,s=0,z=0,t,w,x,y;
            
            // computa rank da matriz
            
            
            double norm = 0.0;
            for (int i = 0; i < nn; i++) {
                if (i < low | i > high) {
                    d[i] = H[i][i];
                    e[i] = 0.0;
                }
                for (int j = Math.max(i-1,0); j < nn; j++)
                    norm = norm + Math.abs(H[i][j]);
            }
            
            int iter = 0;
            while (n >= low) {
                int l = n;
                while (l > low) {
                    s = Math.abs(H[l-1][l-1]) + Math.abs(H[l][l]);
                    if (s == 0.0)
                        s = norm;
                    if (Math.abs(H[l][l-1]) < eps * s)
                        break;
                    l--;
                }
                // verifica convergencia
                //uma raiz encontrada
                if (l == n) {
                    H[n][n] = H[n][n] + exshift;
                    d[n] = H[n][n];
                    e[n] = 0.0;
                    n--;
                    iter = 0;
                    // duas raizes encontradas
                } else if (l == n-1) {
                    w = H[n][n-1] * H[n-1][n];
                    p = (H[n-1][n-1] - H[n][n]) / 2.0;
                    q = p * p + w;
                    z = Math.sqrt(Math.abs(q));
                    H[n][n] = H[n][n] + exshift;
                    H[n-1][n-1] = H[n-1][n-1] + exshift;
                    x = H[n][n];
                    if (q >= 0) {
                        if (p >= 0) {
                            z = p + z;
                        } else {
                            z = p - z;
                        }
                        d[n-1] = x + z;
                        d[n] = d[n-1];
                        if (z != 0.0)
                            d[n] = x - w / z;
                        e[n-1] = 0.0;
                        e[n] = 0.0;
                        x = H[n][n-1];
                        s = Math.abs(x) + Math.abs(z);
                        p = x / s;
                        q = z / s;
                        r = Math.sqrt(p * p+q * q);
                        p = p / r;
                        q = q / r;
                        // Modificação de linha
                        for (int j = n-1; j < nn; j++) {
                            z = H[n-1][j];
                            H[n-1][j] = q * z + p * H[n][j];
                            H[n][j] = q * H[n][j] - p * z;
                        }
                        // modificação de coluna
                        for (int i = 0; i <= n; i++) {
                            z = H[i][n-1];
                            H[i][n-1] = q * z + p * H[i][n];
                            H[i][n] = q * H[i][n] - p * z;
                        }
                        for (int i = low; i <= high; i++) {
                            z = V[i][n-1];
                            V[i][n-1] = q * z + p * V[i][n];
                            V[i][n] = q * V[i][n] - p * z;
                        }
                        
                        // par complexo
                        
                    } else {
                        d[n-1] = x + p;
                        d[n] = x + p;
                        e[n-1] = z;
                        e[n] = -z;
                    }
                    n = n - 2;
                    iter = 0;
                    // nenhuma convergência
                } else {
                    x = H[n][n];
                    y = 0.0;
                    w = 0.0;
                    if (l < n) {
                        y = H[n-1][n-1];
                        w = H[n][n-1] * H[n-1][n];
                    }
                    if (iter == 10) {
                        exshift += x;
                        for (int i = low; i <= n; i++)
                            H[i][i] -= x;
                        s = Math.abs(H[n][n-1]) + Math.abs(H[n-1][n-2]);
                        x = y = 0.75 * s;
                        w = -0.4375 * s * s;
                    }
                    if (iter == 30) {
                        s = (y - x) / 2.0;
                        s = s * s + w;
                        if (s > 0) {
                            
                            s = Math.sqrt(s);
                            if (y < x)
                                
                                s = -s;
                            
                            s = x - w / ((y - x) / 2.0 + s);
                            for (int i = low; i <= n; i++)
                                H[i][i] -= s;
                            exshift += s;
                            x = y = w = 0.964;
                        }
                    }
                    iter = iter + 1;
                    int m = n-2;
                    while (m >= l) {
                        z = H[m][m];
                        r = x - z;
                        s = y - z;
                        p = (r * s - w) / H[m+1][m] + H[m][m+1];
                        q = H[m+1][m+1] - z - r - s;
                        r = H[m+2][m+1];
                        s = Math.abs(p) + Math.abs(q) + Math.abs(r);
                        p = p / s;
                        q = q / s;
                        r = r / s;
                        if (m == l)
                            break;
                        if (Math.abs(H[m][m-1]) * (Math.abs(q) + Math.abs(r)) <
                                eps * (Math.abs(p) * (Math.abs(H[m-1][m-1]) + Math.abs(z) +
                                Math.abs(H[m+1][m+1]))))
                            break;
                        m--;
                    }
                    
                    for (int i = m+2; i <= n; i++) {
                        H[i][i-2] = 0.0;
                        if (i > m+2)
                            H[i][i-3] = 0.0;
                    }
                    
                    for (int k = m; k <= n-1; k++) {
                        boolean notlast = (k != n-1);
                        if (k != m) {
                            p = H[k][k-1];
                            q = H[k+1][k-1];
                            r = (notlast ? H[k+2][k-1] : 0.0);
                            x = Math.abs(p) + Math.abs(q) + Math.abs(r);
                            if (x != 0.0) {
                                p = p / x;
                                q = q / x;
                                r = r / x;
                                
                            }
                        }
                        if (x == 0.0)
                            break;
                        s = Math.sqrt(p * p + q * q + r * r);
                        if (p < 0)
                            s = -s;
                        if (s != 0) {
                            if (k != m)
                                H[k][k-1] = -s * x;
                            else if (l != m)
                                H[k][k-1] = -H[k][k-1];
                            //}
                            p = p + s;
                            x = p / s;
                            y = q / s;
                            z = r / s;
                            q = q / p;
                            r = r / p;
                            
                            //Modificação de linha
                            for (int j = k; j < nn; j++) {
                                p = H[k][j] + q * H[k+1][j];
                                if (notlast) {
                                    p = p + r * H[k+2][j];
                                    H[k+2][j] = H[k+2][j] - p * z;
                                }
                                H[k][j] = H[k][j] - p * x;
                                H[k+1][j] = H[k+1][j] - p * y;
                            }
                            
                            //Modificação de coluna
                            for (int i = 0; i <= Math.min(n,k+3); i++) {
                                p = x * H[i][k] + y * H[i][k+1];
                                if (notlast) {
                                    p = p + z * H[i][k+2];
                                    H[i][k+2] = H[i][k+2] - p * r;
                                }
                                H[i][k] = H[i][k] - p;
                                H[i][k+1] = H[i][k+1] - p * q;
                            }
                            
                            for (int i = low; i <= high; i++) {
                                p = x * V[i][k] + y * V[i][k+1];
                                if (notlast) {
                                    p = p + z * V[i][k+2];
                                    V[i][k+2] = V[i][k+2] - p * r;
                                }
                                V[i][k] = V[i][k] - p;
                                V[i][k+1] = V[i][k+1] - p * q;
                            }
                        }  // (s != 0)
                    }  // k loop
                }  // verifica convergencia
            }  // while (n >= low)
            
            //  para achar vetores de forma triangular superior
            if (norm == 0.0)
                return null;
            for (n = nn-1; n >= 0; n--) {
                p = d[n];
                q = e[n];
                // Vetor real
                if (q == 0) {
                    int l = n;
                    H[n][n] = 1.0;
                    for (int i = n-1; i >= 0; i--) {
                        w = H[i][i] - p;
                        r = 0.0;
                        for (int j = l; j <= n; j++)
                            r = r + H[i][j] * H[j][n];
                        if (e[i] < 0.0) {
                            z = w;
                            s = r;
                        } else {
                            l = i;
                            if (e[i] == 0.0) {
                                if (w != 0.0) {
                                    H[i][n] = -r / w;
                                } else {
                                    H[i][n] = -r / (eps * norm);
                                }
                            } else {
                                x = H[i][i+1];
                                y = H[i+1][i];
                                
                                q = (d[i] - p) * (d[i] - p) + e[i] * e[i];
                                t = (x * s - z * r) / q;
                                H[i][n] = t;
                                if (Math.abs(x) > Math.abs(z)) {
                                    H[i+1][n] = (-r - w * t) / x;
                                } else {
                                    H[i+1][n] = (-s - y * t) / z;
                                }
                            }
                            // Overflow
                            t = Math.abs(H[i][n]);
                            if ((eps * t) * t > 1) {
                                for (int j = i; j <= n; j++) {
                                    H[j][n] = H[j][n] / t;
                                }
                            }
                        }
                    }
                    
                    // vetor complexo
                    
                } else if (q < 0) {
                    int l = n-1;
                    if (Math.abs(H[n][n-1]) > Math.abs(H[n-1][n])) {
                        H[n-1][n-1] = q / H[n][n-1];
                        H[n-1][n] = -(H[n][n] - p) / H[n][n-1];
                    } else {
                        cdiv(0.0,-H[n-1][n],H[n-1][n-1]-p,q);
                        H[n-1][n-1] = cdivr;
                        H[n-1][n] = cdivi;
                    }
                    H[n][n-1] = 0.0;
                    H[n][n] = 1.0;
                    for (int i = n-2; i >= 0; i--) {
                        double ra,sa,vr,vi;
                        ra = 0.0;
                        sa = 0.0;
                        for (int j = l; j <= n; j++)
                            
                            
                            
                            
                            
                        {
                            ra = ra + H[i][j] * H[j][n-1];
                            sa = sa + H[i][j] * H[j][n];
                        }
                        w = H[i][i] - p;
                        if (e[i] < 0.0) {
                            z = w;
                            r = ra;
                            s = sa;
                        } else {
                            l = i;
                            if (e[i] == 0) {
                                cdiv(-ra,-sa,w,q);
                                H[i][n-1] = cdivr;
                                H[i][n] = cdivi;
                            } else {//resolve equações conplexas
                                x = H[i][i+1];
                                y = H[i+1][i];
                                vr = (d[i] - p) * (d[i] - p) + e[i] * e[i] - q * q;
                                vi = (d[i] - p) * 2.0 * q;
                                if (vr == 0.0 & vi == 0.0)
                                    vr = eps * norm * (Math.abs(w) + Math.abs(q) + Math.abs(x) + Math.abs(y) + Math.abs(z));
                                cdiv(x*r-z*ra+q*sa,x*s-z*sa-q*ra,vr,vi);
                                H[i][n-1] = cdivr;
                                H[i][n] = cdivi;
                                if (Math.abs(x) > (Math.abs(z) + Math.abs(q))) {
                                    H[i+1][n-1] = (-ra - w * H[i][n-1] + q * H[i][n]) / x;
                                    H[i+1][n] = (-sa - w * H[i][n] - q * H[i][n-1]) / x;
                                } else {
                                    cdiv(-r-y*H[i][n-1],-s-y*H[i][n],z,q);
                                    H[i+1][n-1] = cdivr;
                                    H[i+1][n] = cdivi;
                                }
                            }
                            // Overflow l
                            t = Math.max(Math.abs(H[i][n-1]),Math.abs(H[i][n]));
                            if ((eps * t) * t > 1) {
                                for (int j = i; j <= n; j++) {
                                    H[j][n-1] = H[j][n-1] / t;
                                    H[j][n] = H[j][n] / t;
                                }
                            }
                        }
                    }
                }
            }
            
            for (int i = 0; i < nn; i++) {
                if (i < low | i > high) {
                    for (int j = i; j < nn; j++) {
                        V[i][j] = H[i][j];
                    }
                }
            }
            for (int j = nn-1; j >= low; j--) {
                for (int i = low; i <= high; i++) {
                    z = 0.0;
                    for (int k = low; k <= Math.min(j,high); k++) {
                        z = z + V[i][k] * H[k][j];
                    }
                    V[i][j] = z;
                }
            }
        }
        //Gera matriz D
        Matriz EigValue = new Matriz(n,n,"EigValues_"+Arg.getName());
        double[][] D = EigValue.getMatriz();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                D[i][j] = 0.0;
            }
            D[i][i] = d[i];
            if (e[i] > 0) {
                D[i][i+1] = e[i];
            } else if (e[i] < 0) {
                D[i][i-1] = e[i];
            }
        }
        
        Matriz EigVector = new Matriz(n,n,"EigVector_"+Arg.getName());
        for(int i = 0;i < n; i++)
            for(int j = 0; j < n; j++)
                EigVector.M[i][j] = V[i][j];
        
        Matriz EigValue_img = new Matriz(n,n,"EigValues_img_"+nome);
        EigValue_img.Zera();
        boolean flag = false;
        for(int i = 0;i < n; i++) {
            EigValue_img.M[i][i] = e[i];
            if(e[i] != 0)
                flag = true;
        }
        Ret.addElement(EigVector);
        Ret.addElement(EigValue);
        if(flag)
            Ret.addElement(EigValue_img); //inclue parte imaginaria se houver
      /*Matriz temp = getImagEigenvalues();
      if(temp != null)
        {
                Ret.addElement(temp);
        }*/
        return Ret;
    }
    // DIvisão escalar complexa.
    private double cdivr, cdivi;
    private void cdiv(double xr, double xi, double yr, double yi) {
        double r,d;
        if (Math.abs(yr) > Math.abs(yi)) {
            r = yi/yr;
            d = yr + r*yi;
            cdivr = (xr + r*xi)/d;
            cdivi = (xi - r*xr)/d;
        } else {
            r = yr/yi;
            d = yi + r*yr;
            cdivr = (r*xr + xi)/d;
            cdivi = (r*xi - xr)/d;
        }
        
    }
    
    
    
    // Redução não-simetrica de  Hessenberg para forma real de Schur
    private static double hypot(double a, double b) {
        double r;
        if (Math.abs(a) > Math.abs(b)) {
            r = b/a;
            r = Math.abs(a)*Math.sqrt(1+r*r);
        } else if (b != 0) {
            r = a/b;
            r = Math.abs(b)*Math.sqrt(1+r*r);
        } else
            r = 0.0;
        return r;
    }
    public Matriz getV() {
        Matriz M = new Matriz(n,n,"EigVector_"+nome);
        for(int i = 0;i < n; i++)
            for(int j = 0; j < n; j++)
                M.M[i][j] = V[i][j];
        return M;
    }
    private double[] getRealEigenvalues() {
        return d;
    }
    
    public Matriz getImagEigenvalues() {
        Matriz M = new Matriz(n,n,"EigValues_img_"+nome);
        M.Zera();
        boolean flag = false;
        for(int i = 0;i < n; i++) {
            M.M[i][i] = e[i];
            if(e[i] != 0)
                flag = true;
        }
        if(flag)
            return M;
        else
            return null;
        //return e;
    }
    public Matriz getD() {
        Matriz X = new Matriz(n,n,"EigValues_"+nome);
        double[][] D = X.getMatriz();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                D[i][j] = 0.0;
            }
            D[i][i] = d[i];
            if (e[i] > 0) {
                D[i][i+1] = e[i];
            } else if (e[i] < 0) {
                D[i][i-1] = e[i];
            }
        }
        return X;
        //return new Matriz(D);
    }
    
    private double norm(Matriz M) {
        double l = 0;
        int i, j;
        for (i = 0; i<M.getnLinhas(); i++) {
            for (j = 0; j<M.getnColunas(); j++) {
                l = l + M.M[i][j] * M.M[i][j];
            }
        }
        l = Math.pow(l, 0.5);
        return l;
    }
    private Matriz householder_mult(Matriz A, Matriz v) {
/* Entranda:   A - matrix
          v - Householder vector
 saída:  A - transforme matrix
              A = A - \(\frac{\text{2}}{\text{v'v}}(\text{vv'})\)A */
        
        Matriz aux;
        aux = this.Mult(this.Transposta(v),v);
        double beta = -2/aux.M[0][0]; //Ponto crítico
        Matriz w = this.Mult(this.Transposta(v),A);
        aux = this.Mult(v,w);
        
        for ( int l = 0; l < aux.getnLinhas(); l++ ) {
            for (int c = 0; c < aux.getnColunas(); c++ ) {
                aux.M[l][c] = aux.M[l][c]*beta;
            }
        }
        Matriz temp = aux;
        Matriz Aa  = this.Soma(A,  aux );
        //A = A + beta*v*w;
        return Aa;
        
    }
    private Matriz householder(Matriz a) {
        double beta;
        int n;
        int l,c;
        n = a.getnLinhas();
        if(a.getnColunas() > n)
            n = a.getnColunas();
        Matriz v = new Matriz(a.getnLinhas(),a.getnColunas(),"temkp");
        v.M = a.M;
        if (a.M[0][0] >= 0)
            beta = a.M[0][0] + norm(a);
        else
            beta = a.M[0][0] - norm(a);
        
        for(int i = 1; i < n; i++)
            v.M[i][0] = 1/beta * v.M[i][0];
        v.M[0][0] = 1;
        return v;
    }
    private Matriz qr_house(Matriz A) {
        // entrada:   A - (n x m) matrix
        // saída:  A - contem a informação necessária do vetor v de Householder
        //[n,m] = size(A);
        int k,max,n,m,i,j;
        n = A.getnLinhas();
        m = A.getnColunas();
        Matriz v,temp,Ret;
        Ret = new Matriz(n,m,"R_"+A.getName());
        Ret.M = A.M;
        if (n-1 < m)
            max = n-1;
        else max = m-1;
        v = new Matriz(n,1,"tpojw");
        for (k = 0;k < max; k++) {
            temp = RecortM(Ret,k,k,n-1,k);
            temp = householder(temp);
            for(int r = k; r < n; r++)
                v.M[r][0] = temp.M[r-k][0];
            temp = householder_mult(RecortM(Ret,k,k,n-1,m-1),RecortM(v,k,0,n-1,0));
            for(int r = k; r < n; r++)
                for(int p = k; p < m; p ++)
                    Ret.M[r][p] = temp.M[r-k][p-k];
            for(int r = k+1; r < n; r++) //A(k+1:n,k) = v(k+1:n,1);
            {
                Ret.M[r][k] = v.M[r][0];
                //System.out.println("Entrei");
            }
        }
        
        return Ret;
    }//Fim qr_house()
    private Matriz q_house(Matriz M) {
        Matriz Q,v,Qk,temp;
        double aux;
        int k,max,n,m,i,j;
        n = M.getnLinhas();
        m = M.getnColunas();
        Q = new Matriz(n,n,"Q_"+M.getName(),1);
        if (n-1 < m)
            max = n-1;
        else
            max = m-1;
        for(k = 0; k < max; k++) {
            v = new Matriz(n-k,1,"tempsfj");
            v.ones();
            for(i = 1; i < n-k; i++)
                v.M[i][0] = M.M[i+k][k];
            
            Qk = new Matriz(n,n,"lkejr",1);
            temp = this.Mult(Transposta(v),v);
            aux = 2/temp.M[0][0];
            temp = this.Mult(v,Transposta(v));
            
            for(i = 0; i < temp.getnLinhas(); i++)
                
                for(j = 0; j < temp.getnColunas(); j++)
                    temp.M[i][j] = temp.M[i][j]*aux;
            temp = this.Sub(new Matriz(n-k,n-k,"tejpw",1),temp);
            for(i = 0; i < temp.getnLinhas(); i++)
                for(j = 0; j < temp.getnColunas(); j++)
                    Qk.M[i+k][j+k] = temp.M[i][j];
            Q = Mult(Qk,Q);
        }
        Q = Transposta(Q);
        Q.setName("Q_"+M.getName());
        return Q;
    }
    public Vector qr(Matriz Arg) {
        Matriz M1 = new Matriz(Arg.getnLinhas(),Arg.getnColunas(),"tespoijf");
        for(int i = 0; i< Arg.getnLinhas(); i++)
            for(int j = 0; j < Arg.getnColunas(); j++)
                M1.M[i][j] = Arg.M[i][j];
        java.util.Vector V = new java.util.Vector();
        Matriz R,Q;
        R = qr_house(M1);
        Q = q_house(R);
        for(int i = 0; i < R.getnLinhas(); i++)
            for(int j = 0; j < i; j++)
                R.M[i][j] = 0;
        V.addElement(Q);
        V.addElement(R);
        return V;
    }
    public double[][] Inverse(double[][] a) {
        // inv(A) = 1/det(A) * adj(A)
        int tms = a.length;
        
        double m[][] = new double[tms][tms];
        double mm[][] =  Adjoint(a);
        
        double det = Determinant(a);
        double dd = 0;
        
        if (det == 0) {
            System.out.println("Determinante igual a zero! Matriz não inversível.");
            // Msg Mensagem = new Msg( new javax.swing.JPanel(),"Erro","Determinante igual a zero! Matriz não inversível.",0);
            return null;
        } else {
            dd = 1/det;
        }
        
        for (int i=0; i < tms; i++)
            for (int j=0; j < tms; j++) {
            m[i][j] = dd * mm[i][j]; }
        
        return m;
    }
    //--------------------------------------------------------------
    public double[][] Adjoint(double[][] a) {
        int tms = a.length;
        
        double m[][] = new double[tms][tms];
        
        int ii, jj, ia, ja;
        double det;
        
        for (int i=0; i < tms; i++)
            for (int j=0; j < tms; j++) {
            ia = ja = 0;
            double ap[][] = new double[tms-1][tms-1];
            for (ii=0; ii < tms; ii++) {
                for (jj=0; jj < tms; jj++) {
                    if ((ii != i) && (jj != j)) {
                        ap[ia][ja] = a[ii][jj];
                        ja++;
                    }
                }
                if ((ii != i ) && (jj != j)) { ia++; }
                ja=0;
            }
            det = Determinant(ap);
            m[i][j] = (float)Math.pow(-1, i+j) * det;
            }
        m = Transpose(m);
        return m;
    }
    public double[][] Transpose(double[][] a) {
        int tms = a.length;
        
        double m[][] = new double[tms][tms];
        
        for (int i=0; i<tms; i++)
            for (int j=0; j<tms; j++) {
            m[i][j] = a[j][i];
            }
        
        return m;
    }
    public double Determinant(double[][] matrix) {
        int tms = matrix.length;
        
        double det=1;
        
        matrix = UpperTriangle(matrix);
        
        for (int i=0; i < tms; i++) {
            det = det * matrix[i][i]; }
        
        det = det * iDF;
        
        return det;
    }
    public double[][] UpperTriangle(double[][] m) {
        double f1 = 0;   double temp = 0;
        int tms = m.length;
        int v=1;
        
        iDF=1;
        
        
        for (int col=0; col < tms-1; col++) {
            for (int row=col+1; row < tms; row++) {
                v=1;
                
                outahere:
                    while(m[col][col] == 0) {
                        if (col+v >= tms) {
                            iDF=0;
                            break outahere;
                        } else {
                            for(int c=0; c < tms; c++) {
                                temp = m[col][c];
                                m[col][c]=m[col+v][c];
                                m[col+v][c] = temp;
                            }
                            v++;
                            iDF = iDF * -1;
                        }
                    }
                    
                    if ( m[col][col] != 0 ) {
                        
                        try {
                            f1 = (-1) * m[row][col] / m[col][col];
                            for (int i=col; i < tms; i++) { m[row][i] = f1*m[col][i] + m[row][i]; }
                        } catch(Exception e) { System.out.println("Erro!!!"); }
                        
                    }
                    
            }
        }
        
        return m;
    }
    static void CheckMatrix(double ad[][], int ai[], int ai1[]) throws IllegalArgumentException {
        ai[0] = ad.length;
        ai1[0] = ad[0].length;
        for(int i = 1; i < ai[0]; i++)
            if(ad[i].length != ai1[0])
                throw new IllegalArgumentException("Os comprimentos das filas da matriz não são consistentes.");
        
    }
}