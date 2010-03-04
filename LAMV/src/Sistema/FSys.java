/*
 * FSys.java
 *
 * Created on 28 de Janeiro de 2005, 22:29
 */

package Sistema;

import LAMV.LAMV;
import Dados.*;
import java.awt.Color;

/**
 *
 * @author  Gustavo
 */
public class FSys {
    
    /** Creates a new instance of FSys */
    public FSys() {
    }
    
    public static java.awt.Point get3LocationCenter( java.awt.Component pai, java.awt.Component filho ) {
        java.awt.Dimension DimD = filho.getSize();
        java.awt.Dimension DimProg = pai.getSize();
        java.awt.Point P = pai.getLocation();
        System.out.println( DimD.toString() );
        System.out.println( P );
        return new java.awt.Point(P.x + DimProg.width/2 - DimD.width/2, P.y + DimProg.height/2 - DimD.height/2);
    }
    public static void centraliza( java.awt.Component c  ) {
        java.awt.Dimension DimD = c.getSize();
        java.awt.Dimension DimProg = FramePrincipal.getSize();
        java.awt.Point P = FramePrincipal.getLocation();
        c.setLocation( new java.awt.Point(P.x + DimProg.width/2 - DimD.width/2, P.y + DimProg.height/2 - DimD.height/2));
    }
    
    public static void Msg(javax.swing.JPanel P, java.lang.String title, java.lang.String msg, int cod ) {
        javax.swing.JOptionPane.showMessageDialog( P,  msg, title, cod );
    }
    
    public static void Msg( java.lang.String title, java.lang.String msg, int cod ) {
        javax.swing.JPanel P = new javax.swing.JPanel();
        javax.swing.JOptionPane.showMessageDialog( P,  msg, title, cod );
    }
    public static String novoNome( Lista LD, String nome ) {
        int caux = 0;
        int aux;
        while( LD.Procura(nome) != null ) {
            aux = nome.length();
            aux--;
            for (; aux > 0 ; aux-- ) {
                if ( nome.charAt(aux) == '_' ) {
                    break;
                }
            }
            if ( aux == 0 )   // Nao ha nome com sequencia numerica
            {
                nome = nome+"_"+caux;
            } else {
                nome = nome.substring(0,aux);
                nome = nome+"_"+caux;
                caux++;
            }
        }
        return nome;
    }
    public static String novoNomeGraf( String nome ) {
        int caux = 0;
        int aux;
        while( LAMV.LGraficos.Procura(nome) != null ) {
            aux = nome.length();
            aux--;
            for (; aux > 0 ; aux-- ) {
                if ( nome.charAt(aux) == '_' ) {
                    break;
                }
            }
            if ( aux == 0 )   // Nao ha nome com sequencia numerica
            {
                nome = nome+"_"+caux;
            } else {
                nome = nome.substring(0,aux);
                nome = nome+"_"+caux;
                caux++;
            }
        }
        return nome;
    }
    
    public static boolean verificaNome( String nome ) {
        if( nome.length() == 0 ) return false;
        int c;
        int nEspacos = 0;
        for( c = 0; c < nome.length(); c++) {
            if ( nome.charAt(c) == ' ' ) {
                nEspacos++;
                break;
            }
        }
        if( nEspacos != 0 ) {
            // O nome da matriz contém espaço portanto nao é válido
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean insereDadoSistema( Dado d ) {
        Dado dd = LAMV.LDados.Procura( d.getName() );
        if ( dd != null ) {
            d.setName( novoNome(  LAMV.LDados, d.getName() ) );
        }
        if (LAMV.LDados.Insere( d ) ) {
            LAMV.modelo.addDado( d );
            return true;
        } else {
            return false;
        }
    }
    
    public static Matriz Vet_to_Mat( Vetor v ) {
        if( v == null )
            return null;
        Matriz m = new Matriz( v.getTam(), 1, v.getName() );
        for( int c = 0; c < m.getnLinhas(); c++ ) {
            m.M[c][0] = v.getValor(c);
        }
        return m;
    }
    public static Matriz Const_to_Mat( Constante c ) {
        if( c == null )
            return null;
        Matriz m = new Matriz(1,1,c.getName() );
        m.M[0][0] = c.getValor();
        return m;
    }
    public static Matriz Dado_to_Mat( Dado d ) {
        if( d == null )
            return null;
        if( d.eConstante() ) {
            Constante c = (Constante)d;
            return Const_to_Mat(c);
        }
        if( d.eVetor() ) {
            Vetor v = (Vetor)d;
            return Vet_to_Mat(v);
        }
        if( d.eMatriz() ) {
            Matriz m =(Matriz)d;
            return m;
        }
        return null;
    }
    
    public static Matriz uneMatrizH( Matriz m1, Matriz m2 ) {
        
        if( m1.getnLinhas() != m2.getnLinhas() ) {
            return null;
        }
        int tam = m1.getnColunas()+m2.getnColunas();
        Matriz M = new Matriz( m1.getnLinhas(), tam );
        
        for( int l = 0; l < m1.getnLinhas(); l++ ) {
            for( int c = 0; c < m1.getnColunas(); c++ ) {
                M.M[l][c] = m1.M[l][c];
            }
        }
        int t = m1.getnColunas();
        for( int l = 0; l < m2.getnLinhas(); l++ ) {
            for( int c = 0; c < m2.getnColunas(); c++ ) {
                M.M[l][c+t] = m2.M[l][c];
            }
        }
        return M;
    }
    
    public static Matriz uneMatrizV( Matriz m1, Matriz m2 ) {
        if( m1.getnColunas() != m2.getnColunas() ) {
            return null;
        }
        int tam = m1.getnLinhas()+m2.getnLinhas();
        Matriz M = new Matriz( tam, m1.getnColunas() );
        
        for( int l = 0; l < m1.getnLinhas(); l++ ) {
            for( int c = 0; c < m1.getnColunas(); c++ ) {
                M.M[l][c] = m1.M[l][c];
            }
        }
        int t = m1.getnColunas();
        for( int l = 0; l < m2.getnLinhas(); l++ ) {
            for( int c = 0; c < m2.getnColunas(); c++ ) {
                M.M[l+t][c] = m2.M[l][c];
            }
        }
        return M;
    }
    
    public static Matriz geraMatriz( Vetor v1, Vetor v2 ) {
        Matriz m1 = Vet_to_Mat( v1 );
        Matriz m2 = Vet_to_Mat( v2 );
        Matriz M = uneMatrizH(m1,m2);
        return M;
    }
    
    /** Recebe um número x, e retorna uma string de exibição em notação científica
     * Exemplo: x = 561.5, retorna 5.615e+2*/
    public static String NotacaoCientifica(double x) {

        if( x == 0 )
            return "0";

        String novoNum = new String("");
        int expoente = 0;
        int n = 1;

        String resto, nresto;
        if(x >= 1 ) {//positivos maiores que 1
            while (x/n >= 10) {
                n = n * 10;
                expoente++;
            }
            resto = new String(""+x%n);
            if(resto.length()>4)
                resto = resto.substring(0, 4);
            nresto = new String("");
            for(int p = 0; p < resto.length() && p < 4; p++ ) {
                nresto = nresto+resto.charAt(p);
            }
            resto = nresto;
            // Neste ponto, n == 10^expoente.
            Double r = new Double(x/n);
            novoNum = novoNum + (x/n);
            if(novoNum.length() > 5)
                novoNum = novoNum.substring(0,6);
            else {
                for(int k = novoNum.length(); k <= 5; k++ ) {
                    novoNum = novoNum + "0";
                }
            }
            int cont = expoente;  // armazenará o número de dígitos da parte decimal
            int parteDecimal = (int)x;
            // "arranca" os zeros da direita:
            while (parteDecimal % 10 == 0) {
                parteDecimal = parteDecimal / 10;
                n = n / 10;
                cont--;
            }
            
            // imprime os dígitos da parte decimal (da esquerda para a direita):
            if (cont > 0) {
                while (cont > 0) {
                    n = n / 10;
                    cont--;
                }
            }
            // finalmente, o expoente:
            novoNum = novoNum +"e+"+expoente;
            
        } else //negativos ou menores que 1
        {
            if(x != 0) {
                while (Math.abs(x*n) < 1 && expoente< 1000) {
                    n = n*10;
                    expoente++;
                }
                novoNum = novoNum + x*n;
                nresto = new String("");
                for(int p = 0; p < novoNum.length() && p < 5; p++ ) {
                    nresto = nresto + novoNum.charAt(p);
                }
                if(novoNum.length() > 5)
                    novoNum = novoNum.substring(0,5);
                else {
                    for(int k = novoNum.length(); k <= 5; k++ ) {
                        novoNum = novoNum + "0";
                    }
                }
                novoNum = novoNum + "e-"+expoente;
            }
        }
        return novoNum;
    }
    
    public static javax.swing.JFrame getFramePrincipal() {
        return FramePrincipal;
    }
    public static void setFrameFinal( javax.swing.JFrame f ) {
        FramePrincipal = f;
    }
    
    private static javax.swing.JFrame FramePrincipal;
    public static final Color COR_FUNDO_PADRAO = Color.WHITE;
    public static final Color COR_GRID_PADRAO = new Color(230,230,230);
    public static final Color COR_EIXO_PADRAO = Color.BLACK;
    public static final Color COR_LABEL_PADRAO = Color.BLACK;
    public static final Color COR_NUMEROS_PADRAO = Color.BLACK;
    public static final Color COR_MARCAS_PADRAO = Color.BLACK;
    public static final Color COR_GRAFICO_PADRAO = Color.BLUE;
}
