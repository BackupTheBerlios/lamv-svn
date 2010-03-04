/*
 * svd.java
 *
 * Created on 7 de Abril de 2005, 16:22
 */

package Ferramentas;

import Dados.*;
import Ferramentas.BLAS;
/**
 *
 * @author Barnes
 */

/** Classe responsável pela decomposição SVD(Singular Values Decomposition) de uma matriz */
public class svd
{
    private BLAS BLASS = new BLAS();
    private Ferramentas.Algebra Alg;
    private double a[][],u[][], v[][],s[];
    boolean usean,wantu, wantv;
    double ztest, e[], work[], stol, anorm, scale, sm, smm1, t, test, emm1,c, sl,el, b, shift, t1[];
    double f[], g[], cs[], sn[];
    int irank,ji,nra, nca, ipath, nctp1, nrtp1, ncu, jnfo, nrt, nct, i, iter, j, jobu;
    int k, kase, kk, l, ll, lu, lls, ls, m, maxit, minmn, mm, mm1, nr[], nc[];
    private static final double SMALLEST = 2.2250738585071999E-308D;
    private static final double LARGEST = 1.7976931348622999E+308D;
    private static final double EPSILON_LARGE = 2.2204460492503E-016D;
    private Matriz X;

    /*public svd(Matriz ad)//passa vetor mais precisão
      {
           X = ad;
           this(X.M, 2.2204460492503001E-014D);
      }*/

    /** Faz a decomposição em três matriz S, V, e D da matriz de entrada XCal
     @param Xcal Matriz de entrada*/
    public svd(Matriz Xcal)
    {
        X = Xcal;
        double ad[][] = X.M;
        Alg = new Ferramentas.Algebra();    
        Matriz M = new Matriz(1,1,"sk");
        double d = 2.2204460492503001E-014D;
        i = 0;
        ipath = 11;
        anorm = 0.0D;
        nr = new int[1];
        nc = new int[1];
        try
        {
            CheckMatrix(ad, nr, nc);
            nra = nr[0];
            nca = nc[0];
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw illegalargumentexception;
        }
        for(i = 0; i < nra; i++)
            anorm = Math.max(anorm, BLASS.dasum(nca, ad[i], 0, 1));

        usean = false;
        if(d < 0.0D)
            stol = Math.abs(d) * anorm;
        else
            stol = d;
        maxit = 30;
        wantu = false;
        wantv = false;
        jobu = (ipath % 100) / 10;
        ncu = nra;
        if(jobu == 2)
            ncu = Math.min(nra, nca);
        if(jobu != 0)
            wantu = true;
        if(ipath % 10 != 0)
            wantv = true;
        jnfo = 0;
        nct = Math.min(nra - 1, nca);
        nrt = Math.max(0, Math.min(nca - 2, nra));
        lu = Math.max(nct, nrt);
        e = new double[nca];
        work = new double[nra + Math.max(nra, nca)];
        s = new double[Math.min(nra + 1, nca)];
        u = new double[ncu][nra];
        v = new double[nca][nca];
        t1 = new double[1];
        f = new double[1];
        g = new double[1];
        cs = new double[1];
        sn = new double[1];
        a = Alg.Transposta(ad);
        if(lu >= 1)
            for(l = 1; l <= lu; l++)
            {
                s[l - 1] = 0.0D;
                if(l <= nct)
                {
                    s[l - 1] = BLASS.dnrm2((nra - l) + 1, a[l - 1], l - 1, 1);
                    if(s[l - 1] >= 2.2250738585071999E-308D)
                    {
                        if(a[l - 1][l - 1] != 0.0D)
                        {
                            s[l - 1] = Math.abs(s[l - 1]);
                            if(a[l - 1][l - 1] < 0.0D)
                                s[l - 1] = -s[l - 1];
                        }
                        BLASS.dscal((nra - l) + 1, 1.0D / s[l - 1], a[l - 1], l - 1, 1);
                        a[l - 1][l - 1] = 1.0D + a[l - 1][l - 1];
                    }
                    s[l - 1] = -s[l - 1];
                }
                if(nca >= l + 1)
                {
                    if(l <= nct && Math.abs(s[l - 1]) >= 2.2250738585071999E-308D)
                    {
                        BLASS.dgemv('T', (nra - l) + 1, (nca - (l + 1)) + 1, -1D / a[l - 1][l - 1], a, l, l - 1, nra, a[l - 1], l - 1, 1, 0.0D, work, nra, 1);
                        BLASS.dger((nra - l) + 1, (nca - (l + 1)) + 1, 1.0D, a[l - 1], l - 1, 1, work, nra, 1, a, l, l - 1, nra);
                    }
                    for(i = l; i < nca; i++)
                        e[i] = a[i][l - 1];

                }
                if(wantu && l <= nct)
                    for(ji = l - 1; ji < nra; ji++)
                        u[l - 1][ji] = a[l - 1][ji];

                if(l <= nrt)
                {
                    e[l - 1] = BLASS.dnrm2(nca - l, e, l, 1);
                    if(e[l - 1] >= 2.2250738585071999E-308D)
                    {
                        if(e[l] != 0.0D)
                        {
                            e[l - 1] = Math.abs(e[l - 1]);
                            if(e[l] < 0.0D)
                                e[l - 1] = -e[l - 1];
                        }
                        BLASS.dscal(nca - l, 1.0D / e[l - 1], e, l, 1);
                        e[l] = 1.0D + e[l];
                    }
                    e[l - 1] = -e[l - 1];
                    if(l + 1 <= nra && Math.abs(e[l - 1]) >= 2.2250738585071999E-308D)
                    {
                        BLASS.dgemv('N', nra - l, (nca - (l + 1)) + 1, 1.0D, a, l, l, nra, e, l, 1, 0.0D, work, l, 1);
                        BLASS.dger(nra - l, (nca - (l + 1)) + 1, -1D / e[l], work, l, 1, e, l, 1, a, l, l, nra);
                    }
                    if(wantv)
                        for(ji = l; ji < nca; ji++)
                            v[l - 1][ji] = e[ji];

                }
            }

        m = Math.min(nca, nra + 1);
        nctp1 = nct + 1;
        nrtp1 = nrt + 1;
        if(nct < nca)
            s[nct] = a[nct][nct];
        if(nra < m)
            s[m - 1] = 0.0D;
        if(nrtp1 < m)
            e[nrt] = a[m - 1][nrt];
        e[m - 1] = 0.0D;
        if(wantu)
        {
            if(ncu >= nctp1)
                for(j = nct; j < ncu; j++)
                    u[j][j] = 1.0D;

            if(nct >= 1)
                for(ll = 1; ll <= nct; ll++)
                {
                    l = (nct - ll) + 1;
                    if(Math.abs(s[l - 1]) >= 2.2250738585071999E-308D)
                    {
                        if(ncu >= l + 1)
                        {
                            BLASS.dgemv('T', (nra - l) + 1, (ncu - (l + 1)) + 1, -1D / u[l - 1][l - 1], u, l, l - 1, nra, u[l - 1], l - 1, 1, 0.0D, work, nra, 1);
                            BLASS.dger((nra - l) + 1, (ncu - (l + 1)) + 1, 1.0D, u[l - 1], l - 1, 1, work, nra, 1, u, l, l - 1, nra);
                        }
                        BLASS.dscal((nra - l) + 1, -1D, u[l - 1], l - 1, 1);
                        u[l - 1][l - 1] = 1.0D + u[l - 1][l - 1];
                        if(l - 1 >= 1)
                            BLASS.dset(l - 1, 0.0D, u[l - 1], 0, 1);
                    } else
                    {
                        BLASS.dset(nra, 0.0D, u[l - 1], 0, 1);
                        u[l - 1][l - 1] = 1.0D;
                    }
                }

        }
        if(wantv)
            for(ll = 1; ll <= nca; ll++)
            {
                l = (nca - ll) + 1;
                if(l <= nrt && Math.abs(e[l - 1]) >= 2.2250738585071999E-308D)
                {
                    BLASS.dgemv('T', nca - l, (nca - (l + 1)) + 1, -1D / v[l - 1][l], v, l, l, nca, v[l - 1], l, 1, 0.0D, work, nra, 1);
                    BLASS.dger(nca - l, (nca - (l + 1)) + 1, 1.0D, v[l - 1], l, 1, work, nra, 1, v, l, l, nca);
                }
                BLASS.dset(nca, 0.0D, v[l - 1], 0, 1);
                v[l - 1][l - 1] = 1.0D;
            }

        mm = m;
        for(iter = 0; m != 0 && iter <= maxit;)
        {
            double zteste;
            for(ll = 1; ll <= m; ll++)
            {
                l = m - ll;
                if(l == 0)
                    break;
                test = Math.abs(s[l - 1]) + Math.abs(s[l]);
                ztest = test + Math.abs(e[l - 1]);
                usean = usean || iter == maxit - 1;
                if(Math.abs(ztest - test) > 2.2204460492503E-016D * ztest && (!usean || Math.abs(e[l - 1]) > 2.2204460492503E-016D * anorm))
                    continue;
                e[l - 1] = 0.0D;
                break;
            }

            if(l == m - 1)
            {
                kase = 4;
            } else
            {
                for(lls = l + 1; lls <= m + 1; lls++)
                {
                    ls = (m - lls) + (l + 1);
                    if(ls == l)
                        break;
                    test = 0.0D;
                    if(ls != m)
                        test = test + Math.abs(e[ls - 1]);
                    if(ls != l + 1)
                        test = test + Math.abs(e[ls - 2]);
                    ztest = test + Math.abs(s[ls - 1]);
                    if(ztest != test)
                        continue;
                    s[ls - 1] = 0.0D;
                    break;
                }

                if(ls == l)
                    kase = 3;
                else
                if(ls == m)
                {
                    kase = 1;
                } else
                {
                    kase = 2;
                    l = ls;
                }
            }
            l = l + 1;
            if(kase == 1)
            {
                mm1 = m - 1;
                f[0] = e[m - 2];
                e[m - 2] = 0.0D;
                for(kk = l; kk <= mm1; kk++)
                {
                    k = (mm1 - kk) + l;
                    t1[0] = s[k - 1];
                    BLASS.drotg(t1, f, cs, sn);
                    s[k - 1] = t1[0];
                    if(k != l)
                    {
                        f[0] = -sn[0] * e[k - 2];
                        e[k - 2] = cs[0] * e[k - 2];
                    }
                    if(wantv)
                        BLASS.drot(nca, v[k - 1], 0, 1, v[m - 1], 0, 1, cs, sn);
                }

            } else
            if(kase == 2)
            {
                f[0] = e[l - 2];
                e[l - 2] = 0.0D;
                for(k = l; k <= m; k++)
                {
                    t1[0] = s[k - 1];
                    BLASS.drotg(t1, f, cs, sn);
                    s[k - 1] = t1[0];
                    f[0] = -sn[0] * e[k - 1];
                    e[k - 1] = cs[0] * e[k - 1];
                    if(wantu)
                        BLASS.drot(nra, u[k - 1], 0, 1, u[l - 2], 0, 1, cs, sn);
                }

            } else
            if(kase == 3)
            {
                scale = Math.max(Math.abs(s[m - 1]), Math.abs(s[m - 2]));
                scale = Math.max(scale, Math.abs(e[m - 2]));
                scale = Math.max(scale, Math.abs(s[l - 1]));
                scale = Math.max(scale, Math.abs(e[l - 1]));
                sm = s[m - 1] / scale;
                smm1 = s[m - 2] / scale;
                emm1 = e[m - 2] / scale;
                sl = s[l - 1] / scale;
                el = e[l - 1] / scale;
                b = ((smm1 + sm) * (smm1 - sm) + emm1 * emm1) / 2D;
                c = sm * emm1 * (sm * emm1);
                shift = 0.0D;
                if(b != 0.0D || c != 0.0D)
                {
                    shift = Math.sqrt(b * b + c);
                    if(b < 0.0D)
                        shift = -shift;
                    shift = c / (b + shift);
                }
                f[0] = (sl + sm) * (sl - sm) - shift;
                g[0] = sl * el;
                mm1 = m - 1;
                for(k = l; k <= mm1; k++)
                {
                    BLASS.drotg(f, g, cs, sn);
                    if(k != l)
                        e[k - 2] = f[0];
                    f[0] = cs[0] * s[k - 1] + sn[0] * e[k - 1];
                    e[k - 1] = cs[0] * e[k - 1] - sn[0] * s[k - 1];
                    g[0] = sn[0] * s[k];
                    s[k] = cs[0] * s[k];
                    if(wantv)
                        BLASS.drot(nca, v[k - 1], 0, 1, v[k], 0, 1, cs, sn);
                    BLASS.drotg(f, g, cs, sn);
                    s[k - 1] = f[0];
                    f[0] = cs[0] * e[k - 1] + sn[0] * s[k];
                    s[k] = -sn[0] * e[k - 1] + cs[0] * s[k];
                    g[0] = sn[0] * e[k];
                    e[k] = cs[0] * e[k];
                    if(wantu && k < nra)
                        BLASS.drot(nra, u[k - 1], 0, 1, u[k], 0, 1, cs, sn);
                }

                e[m - 2] = f[0];
                iter = iter + 1;
            } else
            if(kase == 4)
            {
                if(s[l - 1] < 0.0D)
                {
                    s[l - 1] = -s[l - 1];
                    if(wantv)
                        BLASS.dscal(nca, -1D, v[l - 1], 0, 1);
                }
                for(; l != mm; l = l + 1)
                    if(s[l - 1] < s[l])
                    {
                        t = s[l - 1];
                        s[l - 1] = s[l];
                        s[l] = t;
                        if(wantv && l < nca)
                            BLASS.dswap(nca, v[l - 1], 0, 1, v[l], 0, 1);
                        if(wantu && l < nra)
                            BLASS.dswap(nra, u[l - 1], 0, 1, u[l], 0, 1);
                    }

                iter = 0;
                m = m - 1;
            }
        }

        if(iter > maxit)
            jnfo = m;
    }
    
    /** Obtém a pseudo Inversa da matriz de entrada utilizando decomposição SVD
     @return Matriz Pseudo inverda da matriz de entrada*/
    private Matriz pseudoInversa() 
    {
        double ad[][] = getS().M;
        double ad1[][] = getD().M;
        double ad2[] = getVar();
        double ad3[][] = Alg.Transposta(ad1);
        int i1 = 0;
        i1 = rank();
        int j1 = ad2.length;
        double ad7[] = new double[j1];
        for(int k1 = 0; k1 < j1; k1++)
            if(Math.abs(ad2[k1]) < stol)
                ad7[k1] = 0.0D;
            else
                ad7[k1] = 1.0D / ad2[k1];
        Matriz M1 = new Matriz(nca,nra,"pseudo_Inversa_"+X.getName());
        double ad6[][] = M1.M;
        if(i1 != 0)
        {
            for(int l1 = 0; l1 < Math.min(nca, nra); l1++)
                scale(ad7[l1], ad3[l1]);

            double ad5[][] = Alg.Transposta(ad3);
            double ad4[][] = Alg.Transposta(ad);
            for(int i2 = 0; i2 < ad5.length; i2++)
            {
                for(int j2 = 0; j2 < ad4[0].length; j2++)
                {
                    for(int k2 = 0; k2 < Math.min(ad5[0].length, ad4.length); k2++)
                       ad6[i2][j2] += ad5[i2][k2] * ad4[k2][j2];
                }
            }
        }
        M1.M = ad6;
        return M1;
    }

    /** Obtém o rank da matriz de entrada
     @return Rank da matriz de entrada*/
    public int rank()
    {
        if(jnfo == 0)
        {
            ls = Math.min(nca + 1, nra);
            minmn = Math.min(nca, nra);
            irank = minmn;
            for(i = 1; i <= minmn; i++)
                if(Math.abs(s[i - 1]) <= stol)
                {
                    irank = i - 1;
                    return irank;
                }

        } else
        {
            jnfo = jnfo + 1;
            //throw new MathException("Rank can not be determined because convergence can only be obtained for the " + jnfo + ",...," + Math.min(nra, nca) + " singular values and their corresponding singular vectors.");
        }
        return irank;
    }
    
    static void scale(double d, double ad[])
    {
        for(int i = 0; i < ad.length; i++)
            ad[i] *= d;

    }
   
    static void CheckMatrix(double ad[][], int ai[], int ai1[]) throws IllegalArgumentException
    {
        ai[0] = ad.length;
        ai1[0] = ad[0].length;
        for(int i = 1; i < ai[0]; i++)
            if(ad[i].length != ai1[0])
                throw new IllegalArgumentException("Os comprimentos das filas da matriz não são consistentes.");

    }
   
   /** Retorna vetor de variancia da matriz de entrada. Corresponde a diagonal principal da matriz V. */
   public double[] getVar()
   {
       return s;
   }
   
   /** Retorna a matriz diagonal V. A matriz V contém os autovalores da matriz de entrada.*/
    public Matriz getV() throws java.lang.IndexOutOfBoundsException
    {
        Matriz V = new Matriz(X.getnLinhas(),X.getnColunas(),"V_"+X.getName());
        for(int k = 0 ; k < X.getnLinhas() && k < X.getnColunas(); k++)
        {
            V.M[k][k] = s[k];
        } 
        return V;
    }
    
    /** Retorna a matriz ortogonal S. A matriz S contém autovetores da matriz de entrada*/
    public Matriz getS()
    {
        Matriz S = new Matriz(X.getnLinhas(),X.getnLinhas(),"S_"+X.getName());
        S.M = Alg.Transposta(u);
        return S;
    }
    
    /** Retorna a matriz ortogonal D. A matriz D contém autovetores da matriz de entrada*/
    public Matriz getD()
    {
        Matriz D = new Matriz(X.getnColunas(), X.getnColunas(), "D_"+X.getName());
        D.M = Alg.Transposta(v);
        return D;
    }
}
