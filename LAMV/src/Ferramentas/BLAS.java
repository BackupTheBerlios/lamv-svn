/*
 * BLAS.java
 *
 * Created on 7 de Abril de 2005, 16:28
 */

package Ferramentas;

/**
 *
 * @author Nucleo
 */
import java.io.PrintStream;

final class BLAS
{
    static final double dasum(int i, double ad[], int j, int k)
      {
            double d = 0.0D;
            if(i > 0)
                if(k == 1)
                    {
                          int k1 = i % 6;
                          for(int l = 0; l < k1; l++)
                              d += Math.abs(ad[l + j]);
                          int l1 = k1 + j;
                          for(int i1 = l1; i1 < i + j; i1 += 6)
                          d = d + Math.abs(ad[i1]) + Math.abs(ad[i1 + 1]) + Math.abs(ad[i1 + 2]) + Math.abs(ad[i1 + 3]) + Math.abs(ad[i1 + 4]) + Math.abs(ad[i1 + 5]);
                    } 
                else
                   {
                         int i2 = i * k;
                         for(int j1 = 0; j1 < i2; j1 += k)
                            d += Math.abs(ad[j1 + j]);
                   }
               return d;
    }
static final void daxpy(int i, double d, double ad[], int j, int k, double ad1[], int l, int i1)
{
        if(i > 0 && d != 0.0D)
            if(k != 1 || i1 != 1)
              {
                    int j1 = j;
                    int k1 = l;
                    if(k < 0)
                       j1 = (-i + 1) * k + j;
                    if(i1 < 0)
                       k1 = (-i + 1) * i1 + l;
                    for(int i2 = 0; i2 < i; i2++)
                      {
                             ad1[k1] += d * ad[j1];
                             j1 += k;
                             k1 += i1;
                      }
              } 
            else
              {
                    int l1 = i % 4;
                    for(int j2 = 0; j2 < l1; j2++)
                       ad1[j2 + l] += d * ad[j2 + j];
                    for(int k2 = l1; k2 < i; k2 += 4)
                      {
                             int l2 = k2 + j;
                             int i3 = k2 + l;
                             ad1[i3] += d * ad[l2];
                             ad1[i3 + 1] += d * ad[l2 + 1];
                             ad1[i3 + 2] += d * ad[l2 + 2];
                             ad1[i3 + 3] += d * ad[l2 + 3];
                      }
            }
    }
static void dcopy(int i, double ad[], int j, int k, double ad1[], int l, int i1)
  {
        if(i > 0)
            if(k != 1 || i1 != 1)
              {
                    int l1 = j;
                    int j2 = l;
                    if(k < 0)
                       l1 = (-i + 1) * k + j;
                    if(i1 < 0)
                       j2 = (-i + 1) * i1 + l;
                    for(int j1 = 0; j1 < i; j1++)
                       {
                              ad1[j2] = ad[l1];
                              l1 += k;
                              j2 += i1;
                       }

               } 
            else
              {
                     int l2 = i % 7;
                     int i2 = j;
                     int k2 = l;
                     System.arraycopy(ad, i2, ad1, k2, l2);
                     for(int k1 = l2; k1 < i; k1 += 7)
                        {
                                int i3 = k1 + j;
                                int j3 = k1 + l;
                                ad1[j3] = ad[i3];
                                ad1[j3 + 1] = ad[i3 + 1];
                                ad1[j3 + 2] = ad[i3 + 2];
                                ad1[j3 + 3] = ad[i3 + 3];
                                ad1[j3 + 4] = ad[i3 + 4];
                                ad1[j3 + 5] = ad[i3 + 5];
                                ad1[j3 + 6] = ad[i3 + 6];
                        }
              }
    }
static final double ddot(int i, double ad[], int j, int k, double ad1[], int l, int i1)
    {
        double d = 0.0D;
        if(i > 0)
            if(k != 1 || i1 != 1)
              {
                    int j1 = j;
                    int k1 = l;
                    if(k < 0)
                        j1 = (-i + 1) * k + j;
                    if(i1 < 0)
                        k1 = (-i + 1) * i1 + l;
                    for(int i2 = 0; i2 < i; i2++)
                       {
                             d += ad[j1] * ad1[k1];
                              j1 += k;
                             k1 += i1;
                       }
              } 
            else
               {
                        int l1 = i % 5;
                        for(int j2 = 0; j2 < l1; j2++)
                          d += ad[j2 + j] * ad1[j2 + l];
                        for(int k2 = l1; k2 < i; k2 += 5)
                          {
                                 int l2 = k2 + j;
                                 int i3 = k2 + l;
                                 d += ad[l2] * ad1[i3] + ad[l2 + 1] * ad1[i3 + 1] + ad[l2 + 2] * ad1[i3 + 2] + ad[l2 + 3] * ad1[i3 + 3] + ad[l2 + 4] * ad1[i3 + 4];
                          }
             }
        return d;
    }
static final void dgemv(char c, int i, int j, double d, double ad[][], int k, int l, int i1, double ad1[], int j1, int k1, double d1, double ad2[], int l1, int i2)
    {
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag = false;
            if(c == 'N' || c == 'n')
                flag1 = true;
            if(c == 'T' || c == 't')
                flag2 = true;
            if(c == 'C' || c == 'c')
                flag = true;
            if(i >= 0 && j >= 0 && i1 >= i && i1 != 0 && k1 != 0 && i2 != 0 && (flag1 || flag2 || flag) && i != 0 && j != 0 && (d != 0.0D || d1 != 1.0D))
               {
                     int l3;
                     int i4;
                     if(flag1)
                       {
                              l3 = j;
                              i4 = i;
                       } 
                     else
                       {
                             l3 = i;
                             i4 = j;
                       }
            double ad3[] = new double[Math.max(i, j)];
            int l2 = 1 + j1;
            int i3 = 1 + l1;
            if(k1 < 0)
                l2 = (-l3 + 1) * k1 + 1 + j1;
            if(i2 < 0)
                i3 = (-i4 + 1) * i2 + 1 + l1;
            if(d1 != 1.0D)
                if(i2 == 0)
                  {
                       if(d1 == 0.0D)
                           ad2[l1] = 0.0D;
                      else
                           ad2[l1] = Math.pow(d1, (double)i4 * ad2[0]);
                  } 
                else if(d1 == 0.0D)
                    dset(i4, 0.0D, ad2, l1, Math.abs(i2));
                else
                    dscal(i4, d1, ad2, l1, Math.abs(i2));
                if(d != 0.0D)
                if(flag1)
                {
                      int j3 = l2;
                      for(int j2 = 1; j2 <= j; j2++)
                          {
                                 System.arraycopy(ad[(k + j2) - 1], l, ad3, 0, i);
                                 daxpy(i, d * ad1[j3 - 1], ad3, 0, 1, ad2, l1, i2);
                                 System.arraycopy(ad3, 0, ad[(k + j2) - 1], l, i);
                                 j3 += k1;
                          }
                 } 
               else
                  {
                           int k3 = i3;
                           for(int k2 = 1; k2 <= j; k2++)
                              {
                                     System.arraycopy(ad[(k + k2) - 1], l, ad3, 0, i);
                                     ad2[k3 - 1] = ad2[k3 - 1] + d * ddot(i, ad3, 0, 1, ad1, j1, k1);
                                     System.arraycopy(ad3, 0, ad[(k + k2) - 1], l, i);
                                     k3 += i2;
                               }
                }
         }
    }
/**/
    static final void dger(int i, int j, double d, double ad[], int k, int l, double ad1[], 
            int i1, int j1, double ad2[][], int k1, int l1, int i2)
    {
        if(i >= 0 && j >= 0 && i2 >= i && i2 != 0 && l != 0 && j1 != 0 && i != 0 && j != 0 && d != 0.0D)
        {
            double ad3[] = new double[Math.max(i, j)];
            int j2 = 1 + i1;
            if(j1 < 0)
                j2 = (-j + 1) * j1 + 1 + i1;
            for(int k2 = 1; k2 <= j; k2++)
            {
                System.arraycopy(ad2[(k1 + k2) - 1], l1, ad3, 0, i);
                daxpy(i, d * ad1[j2 - 1], ad, k, l, ad3, 0, 1);
                System.arraycopy(ad3, 0, ad2[(k1 + k2) - 1], l1, i);
                j2 += j1;
            }

        }
    }

    static final double dnr1rr(int i, int j, double ad[][])
    {
        if(i <= 0)
        {
            System.out.println("From dnr1rr: The number of rows of the input matrix must be ");
            System.out.println("greater than zero but nra = " + i);
        }
        if(j <= 0)
        {
            System.out.println("From dnr1rr: The number of columns of the input matrix must ");
            System.out.println("be greater than zero but nca = " + j);
        }
        double d = 0.0D;
        for(int l = 0; l < j; l++)
        {
            double d1 = 0.0D;
            for(int k = 0; k < i; k++)
                d1 += Math.abs(ad[k][l]);

            d = Math.max(d1, d);
        }

        return d;
    }

    static final double dnrm2(int i, double ad[], int j, int k)
    {
        double ad1[] = new double[6];
        double d6 = 0.0D;
        double d = 1.0D;
        ad1[0] = 1.0010415475916E-146D;
        ad1[1] = 4.4989137945432004E+161D;
        ad1[2] = 2.2227587494851002E-162D;
        ad1[3] = 1.9979190722022E+146D;
        ad1[4] = 5.0104209000224002E-293D;
        ad1[5] = 1.9958403095347001E+292D;
        double d5 = d6;
        if(i > 0 && k >= 0)
            if(k == 0)
            {
                d5 = i;
                d5 = Math.abs(ad[0]) * Math.sqrt(d5);
            } else
            {
                double d1 = d6;
                for(int l = 1; l <= i * k; l++)
                {
                    d1 += Math.abs(ad[(l - 1) + j]);
                    if(d1 > ad1[3])
                    {
                        d1 = ad1[4];
                        double d3 = ad1[5];
                        d5 = d6;
                        for(int j1 = 1; j1 <= i * k; j1++)
                            d5 += d1 * ad[(j1 - 1) + j] * (d1 * ad[(j1 - 1) + j]);

                        d5 = Math.sqrt(d5) * d3;
                        return d5;
                    }
                }

                if(d1 < (double)i * ad1[0])
                {
                    double d2 = ad1[1];
                    double d4 = ad1[2];
                    d5 = d6;
                    for(int k1 = 1; k1 <= i * k; k1++)
                        d5 += d2 * ad[(k1 - 1) + j] * (d2 * ad[(k1 - 1) + j]);

                    d5 = Math.sqrt(d5) * d4;
                    return d5;
                }
                for(int i1 = 1; i1 <= i * k; i1++)
                    d5 += ad[(i1 - 1) + j] * ad[(i1 - 1) + j];

                d5 = Math.sqrt(d5);
                return d5;
            }
        return d5;
    }

    static final void drot(int i, double ad[], int j, int k, double ad1[], int l, int i1, double ad2[], 
            double ad3[])
    {
        if(i > 0)
            if(k != 1 || i1 != 1)
            {
                int l1 = 1 + j;
                int i2 = 1 + l;
                if(k < 0)
                    l1 = (-i + 1) * k + 1 + j;
                if(i1 < 0)
                    i2 = (-i + 1) * i1 + 1 + l;
                for(int j1 = 0; j1 < i; j1++)
                {
                    double d = ad2[0] * ad[l1] + ad3[0] * ad1[i2];
                    ad1[i2] = ad2[0] * ad1[i2] - ad3[0] * ad[l1];
                    ad[l1] = d;
                    l1 += k;
                    i2 += i1;
                }

            } else
            {
                for(int k1 = 0; k1 < i; k1++)
                {
                    double d1 = ad2[0] * ad[k1 + j] + ad3[0] * ad1[k1 + l];
                    ad1[k1 + l] = ad2[0] * ad1[k1 + l] - ad3[0] * ad[k1 + j];
                    ad[k1 + j] = d1;
                }

            }
    }

    static final void drotg(double ad[], double ad1[], double ad2[], double ad3[])
    {
        if(Math.abs(ad[0]) > Math.abs(ad1[0]))
        {
            double d1 = ad[0] + ad[0];
            double d3 = ad1[0] / d1;
            double d = Math.sqrt(0.25D + d3 * d3) * d1;
            ad2[0] = ad[0] / d;
            ad3[0] = d3 * (ad2[0] + ad2[0]);
            ad1[0] = ad3[0];
            ad[0] = d;
        } else
        if(ad1[0] == 0.0D)
        {
            ad2[0] = 1.0D;
            ad3[0] = 0.0D;
            ad[0] = 0.0D;
            ad1[0] = 0.0D;
        } else
        {
            double d2 = ad1[0] + ad1[0];
            double d4 = ad[0] / d2;
            ad[0] = Math.sqrt(0.25D + d4 * d4) * d2;
            ad3[0] = ad1[0] / ad[0];
            ad2[0] = d4 * (ad3[0] + ad3[0]);
            if(ad2[0] == 0.0D)
                ad1[0] = 1.0D;
            else
                ad1[0] = 1.0D / ad2[0];
        }
    }

    static final void dscal(int i, double d, double ad[], int j, int k)
    {
        if(i > 0)
            if(k != 1)
            {
                int i2 = i * k;
                for(int l = 0; l < i2; l += k)
                    ad[l + j] *= d;

            } else
            {
                int l1 = i - (i / 5) * 5;
                for(int i1 = 0; i1 < l1; i1++)
                    ad[i1 + j] *= d;

                for(int j1 = l1; j1 < i; j1 += 5)
                {
                    int k1 = j1 + j;
                    ad[k1] *= d;
                    ad[k1 + 1] *= d;
                    ad[k1 + 2] *= d;
                    ad[k1 + 3] *= d;
                    ad[k1 + 4] *= d;
                }

            }
    }

    static final void dset(int i, double d, double ad[], int j, int k)
    {
        if(i > 0)
            if(k == 1)
            {
                int k1 = i % 8 + j;
                for(int l = j; l < k1; l++)
                    ad[l] = d;

                int l1 = k1 + 1;
                for(int i1 = k1; i1 < i + j; i1 += 8)
                {
                    ad[i1] = d;
                    ad[i1 + 1] = d;
                    ad[i1 + 2] = d;
                    ad[i1 + 3] = d;
                    ad[i1 + 4] = d;
                    ad[i1 + 5] = d;
                    ad[i1 + 6] = d;
                    ad[i1 + 7] = d;
                }

            } else
            {
                int i2 = i * k + j;
                for(int j1 = j; j1 < i2; j1 += k)
                    ad[j1] = d;

            }
    }

    static final void dswap(int i, double ad[], int j, int k, double ad1[], int l, int i1)
    {
        if(i > 0)
            if(k != 1 || i1 != 1)
            {
                int i2 = j;
                int j2 = l;
                if(k < 0)
                    i2 = (-i + 1) * k + j;
                if(i1 < 0)
                    j2 = (-i + 1) * i1 + l;
                for(int j1 = 0; j1 < i; j1++)
                {
                    double d = ad[i2];
                    ad[i2] = ad1[j2];
                    ad1[j2] = d;
                    i2 += k;
                    j2 += i1;
                }

            } else
            {
                int k2 = i % 3;
                for(int k1 = 0; k1 < k2; k1++)
                {
                    double d1 = ad[k1 + j];
                    ad[k1 + j] = ad1[k1 + l];
                    ad1[k1 + l] = d1;
                }

                for(int l1 = k2; l1 < i; l1 += 3)
                {
                    double d2 = ad[l1 + j];
                    ad[l1 + j] = ad1[l1 + l];
                    ad1[l1 + l] = d2;
                    d2 = ad[l1 + 1 + j];
                    ad[l1 + 1 + j] = ad1[l1 + 1 + l];
                    ad1[l1 + 1 + l] = d2;
                    d2 = ad[l1 + 2 + j];
                    ad[l1 + 2 + j] = ad1[l1 + 2 + l];
                    ad1[l1 + 2 + l] = d2;
                }

            }
    }

    /*static final double dzasum(int i, Complex acomplex[], int j, int k)
    {
        double d = 0.0D;
        if(i > 0)
            if(k == 1)
            {
                int k1 = i % 6;
                for(int l = 0; l < k1; l++)
                    d += Complex.zabs1(acomplex[l + j]);

                int l1 = k1 + j;
                for(int i1 = l1; i1 < i + j; i1 += 6)
                    d += Complex.zabs1(acomplex[i1]) + Complex.zabs1(acomplex[i1 + 1]) + Complex.zabs1(acomplex[i1 + 2]) + Complex.zabs1(acomplex[i1 + 3]) + Complex.zabs1(acomplex[i1 + 4]) + Complex.zabs1(acomplex[i1 + 5]);

            } else
            {
                int i2 = i * k;
                for(int j1 = 0; j1 < i2; j1 += k)
                    d += Complex.zabs1(acomplex[j1 + j]);

            }
        return d;
    }

    static final double dznrm2(int i, Complex acomplex[], int j, int k)
    {
        double ad[] = new double[6];
        double d6 = 0.0D;
        double d = 1.0D;
        ad[0] = 1.0010415475916E-146D;
        ad[1] = 4.4989137945432004E+161D;
        ad[2] = 2.2227587494851002E-162D;
        ad[3] = 1.9979190722022E+146D;
        ad[4] = 5.0104209000224002E-293D;
        ad[5] = 1.9958403095347001E+292D;
        double d5 = d6;
        if(i > 0 && k >= 0)
            if(k == 0)
            {
                d5 = i;
                d5 = Complex.abs(acomplex[0]) * Math.sqrt(d5);
            } else
            {
                double d1 = d6;
                for(int l = 1; l <= i * k; l++)
                {
                    d1 += Complex.zabs1(acomplex[(l - 1) + j]);
                    if(d1 > ad[3])
                    {
                        d1 = ad[4];
                        double d3 = ad[5];
                        d5 = d6;
                        for(int j1 = 1; j1 <= i * k; j1++)
                            d5 = d5 + d1 * acomplex[(j1 - 1) + j].re * (d1 * acomplex[(j1 - 1) + j].re) + d1 * acomplex[(j1 - 1) + j].im * (d1 * acomplex[(j1 - 1) + j].im);

                        d5 = Math.sqrt(d5) * d3;
                        return d5;
                    }
                }

                double d7 = (double)i * ad[0];
                if(d1 < d7)
                {
                    double d2 = ad[1];
                    double d4 = ad[2];
                    d5 = d6;
                    for(int k1 = 1; k1 <= i * k; k1++)
                        d5 = d5 + d2 * acomplex[(k1 - 1) + j].re * (d2 * acomplex[(k1 - 1) + j].re) + d2 * acomplex[(k1 - 1) + j].im * (d2 * acomplex[(k1 - 1) + j].im);

                    d5 = Math.sqrt(d5) * d4;
                    return d5;
                }
                for(int i1 = 1; i1 <= i * k; i1++)
                    d5 = d5 + acomplex[(i1 - 1) + j].re * acomplex[(i1 - 1) + j].re + acomplex[(i1 - 1) + j].im * acomplex[(i1 - 1) + j].im;

                d5 = Math.sqrt(d5);
                return d5;
            }
        return d5;
    }*/

    static final int idamax(int i, double ad[], int j, int k)
    {
        int i1 = -1;
        if(i >= 1)
        {
            i1 = 0;
            if(i > 1)
                if(k != 1)
                {
                    double d = Math.abs(ad[j]);
                    int l = i * k;
                    int j1 = 0;
                    for(int l1 = 0; l1 < l; l1 += k)
                    {
                        double d2 = Math.abs(ad[l1 + j]);
                        if(d2 > d)
                        {
                            i1 = j1;
                            d = d2;
                        }
                        j1 += k;
                    }

                } else
                {
                    double d1 = Math.abs(ad[j]);
                    for(int k1 = 1; k1 < i; k1++)
                    {
                        double d3 = Math.abs(ad[k1 + j]);
                        if(d3 > d1)
                        {
                            i1 = k1;
                            d1 = d3;
                        }
                    }

                }
        }
        return i1;
    }

    /*static final int izamax(int i, Complex acomplex[], int j, int k)
    {
        int k1 = 0;
        if(i < 1)
            k1 = -1;
        else
        if(i == 1)
            k1 = 0;
        else
        if(k != 1)
        {
            double d = Complex.zabs1(acomplex[j]);
            int j1 = 1 + k;
            for(int l = 1; l < i; l++)
            {
                double d2 = Complex.zabs1(acomplex[j1 + j]);
                if(d2 > d)
                {
                    k1 = l;
                    d = d2;
                }
                j1 += k;
            }

        } else
        {
            k1 = 0;
            double d1 = Complex.zabs1(acomplex[j]);
            for(int i1 = 1; i1 < i; i1++)
            {
                double d3 = Complex.zabs1(acomplex[i1 + j]);
                if(d3 > d1)
                {
                    k1 = i1;
                    d1 = d3;
                }
            }

        }
        return k1;
    }

    static final void zaxpy(int i, Complex complex, Complex acomplex[], int j, int k, Complex acomplex1[], int l, int i1)
    {
        Complex complex1 = new Complex();
        if(i > 0 && Complex.zabs1(complex) != 0.0D)
        {
            if(k != 1 || i1 != 1)
            {
                int l1 = 0;
                int i2 = 0;
                if(k < 0)
                    l1 = (-i + 1) * k;
                if(i1 < 0)
                    i2 = (-i + 1) * i1;
                for(int j1 = 0; j1 < i; j1++)
                {
                    complex1.re = complex.re * acomplex[l1 + j].re - complex.im * acomplex[l1 + j].im;
                    complex1.im = complex.re * acomplex[l1 + j].im + complex.im * acomplex[l1 + j].re;
                    acomplex1[i2 + l].add(complex1);
                    l1 += k;
                    i2 += i1;
                }

                return;
            }
            for(int k1 = 0; k1 < i; k1++)
            {
                complex1.re = complex.re * acomplex[k1 + j].re - complex.im * acomplex[k1 + j].im;
                complex1.im = complex.re * acomplex[k1 + j].im + complex.im * acomplex[k1 + j].re;
                acomplex1[k1 + l].add(complex1);
            }

        }
    }

    static final Complex zdot(int i, Complex acomplex[], int j, int k, Complex acomplex1[], int l, int i1)
    {
        Complex complex = new Complex();
        Complex complex1 = new Complex();
        if(i > 0)
            if(k != 1 || i1 != 1)
            {
                int l1 = 0;
                int i2 = 0;
                if(k < 0)
                    l1 = (-i + 1) * k;
                if(i1 < 0)
                    i2 = (-i + 1) * i1;
                for(int j1 = 0; j1 < i; j1++)
                {
                    complex1.re = acomplex[l1 + j].re * acomplex1[i2 + l].re - acomplex[l1 + j].im * acomplex1[i2 + l].im;
                    complex1.im = acomplex[l1 + j].re * acomplex1[i2 + l].im + acomplex[l1 + j].im * acomplex1[i2 + l].re;
                    complex.add(complex1);
                    l1 += k;
                    i2 += i1;
                }

            } else
            {
                for(int k1 = 0; k1 < i; k1++)
                {
                    complex1.re = acomplex[k1 + j].re * acomplex1[k1 + l].re - acomplex[k1 + j].im * acomplex1[k1 + l].im;
                    complex1.im = acomplex[k1 + j].re * acomplex1[k1 + l].im + acomplex[k1 + j].im * acomplex1[k1 + l].re;
                    complex.add(complex1);
                }

            }
        return complex;
    }

    static final Complex zdotc(int i, Complex acomplex[], int j, int k, Complex acomplex1[], int l, int i1)
    {
        Complex complex = new Complex();
        Complex complex1 = new Complex();
        if(i > 0)
            if(k != 1 || i1 != 1)
            {
                int l1 = 0;
                int i2 = 0;

                if(k < 0)
                    l1 = (-i + 1) * k;
                if(i1 < 0)
                    i2 = (-i + 1) * i1;
                for(int j1 = 0; j1 < i; j1++)
                {
                    complex1.re = acomplex[l1 + j].re;
                    complex1.im = -acomplex[l1 + j].im;
                    complex1.multiply(acomplex1[i2 + l]);
                    complex.add(complex1);
                    l1 += k;
                    i2 += i1;
                }

            } else
            {
                for(int k1 = 0; k1 < i; k1++)
                {
                    complex1.re = acomplex[k1 + j].re;
                    complex1.im = -acomplex[k1 + j].im;
                    complex1.multiply(acomplex1[k1 + l]);
                    complex.add(complex1);
                }

            }
        return complex;
    }

    static final Complex zdotu(int i, Complex acomplex[], int j, int k, Complex acomplex1[], int l, int i1)
    {
        Complex complex = new Complex();
        Complex complex1 = new Complex();
        if(i > 0)
            if(k != 1 || i1 != 1)
            {
                int l1 = 0;
                int i2 = 0;
                if(k < 0)
                    l1 = (-i + 1) * k;
                if(i1 < 0)
                    i2 = (-i + 1) * i1;
                for(int j1 = 0; j1 < i; j1++)
                {
                    complex1.re = acomplex[l1 + j].re * acomplex1[i2 + l].re - acomplex[l1 + j].im * acomplex1[i2 + l].im;
                    complex1.im = acomplex[l1 + j].re * acomplex1[i2 + l].im + acomplex[l1 + j].im * acomplex1[i2 + l].re;
                    complex.add(complex1);
                    l1 += k;
                    i2 += i1;
                }

            } else
            {
                for(int k1 = 0; k1 < i; k1++)
                {
                    complex1.re = acomplex[k1 + j].re * acomplex1[k1 + l].re - acomplex[k1 + j].im * acomplex1[k1 + l].im;
                    complex1.im = acomplex[k1 + j].re * acomplex1[k1 + l].im + acomplex[k1 + j].im * acomplex1[k1 + l].re;
                    complex.add(complex1);
                }

            }
        return complex;
    }

    static final void zdscal(int i, double d, Complex acomplex[], int j, int k)
    {
        if(i > 0)
            if(k != 1)
            {
                int j1 = i * k;
                for(int l = 0; l < j1; l += k)
                    acomplex[l + j].multiply(d);

            } else
            {
                for(int i1 = 0; i1 < i; i1++)
                    acomplex[i1 + j].multiply(d);


            }
    }


    static final void zgemv(char c, int i, int j, Complex complex, Complex acomplex[][], int k, int l, int i1, 
            Complex acomplex1[], int j1, int k1, Complex complex1, Complex acomplex2[], int l1, int i2)
    {
        Complex complex2 = new Complex();
        Complex complex5 = new Complex();
        Complex complex6 = new Complex();
        Complex complex7 = new Complex(1.0D, 0.0D);
        Complex complex8 = new Complex();
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag = false;
        if(c == 'N' || c == 'n')
            flag1 = true;
        if(c == 'T' || c == 't')
            flag2 = true;
        if(c == 'C' || c == 'c')
            flag = true;
        if(i >= 0 && j >= 0 && i1 >= i && i1 != 0 && k1 != 0 && i2 != 0 && (flag1 || flag2 || flag) && i != 0 && j != 0 && (!complex.equals(complex6) || !complex1.equals(complex7)))
        {
            int k5;
            int l5;
            if(flag1)
            {
                k5 = j;
                l5 = i;
            } else
            {
                k5 = i;
                l5 = j;
            }
            Complex acomplex3[] = new Complex[Math.max(i, j)];
            int i3 = 1 + j1;
            int j3 = 1 + l1;
            if(k1 < 0)
                i3 = (-k5 + 1) * k1 + 1 + j1;
            if(i2 < 0)
                j3 = (-l5 + 1) * i2 + 1 + l1;
            if(!complex1.equals(complex7))
                if(i2 == 0)
                {
                    if(complex1.equals(complex6))
                        acomplex2[l1] = new Complex();
                    else
                        acomplex2[l1] = Complex.pow(complex1, Complex.multiply(l5, acomplex2[0]));
                } else
                if(complex1.equals(complex6))
                {
                    int k6 = Math.abs(i2);
                    int i6 = 0;
                    for(int i7 = 0; i7 < l5; i7++)
                    {
                        acomplex2[i6 + l1] = new Complex();
                        i6 += k6;
                    }

                } else
                {
                    int l6 = Math.abs(i2);
                    int j6 = 0;
                    for(int j7 = 0; j7 < l5; j7++)
                    {
                        acomplex2[j6 + l1] = Complex.multiply(acomplex2[j6 + l1], complex1);
                        j6 += l6;
                    }

                }
            if(!complex.equals(complex6))
                if(flag1)
                {
                    int l4 = i3;
                    for(int j2 = 1; j2 <= j; j2++)
                    {
                        for(int k3 = 0; k3 < i; k3++)
                            acomplex3[k3] = new Complex(acomplex[(k + j2) - 1][l + k3]);

                        complex2.re = complex.re * acomplex1[l4 - 1].re - complex.im * acomplex1[l4 - 1].im;
                        complex2.im = complex.re * acomplex1[l4 - 1].im + complex.im * acomplex1[l4 - 1].re;
                        zaxpy(i, complex2, acomplex3, 0, 1, acomplex2, l1, i2);
                        for(int l3 = 0; l3 < i; l3++)
                            acomplex[(k + j2) - 1][l + l3] = new Complex(acomplex3[l3]);

                        l4 += k1;
                    }

                } else
                if(flag2)
                {
                    int i5 = j3;
                    for(int k2 = 1; k2 <= j; k2++)
                    {
                        for(int i4 = 0; i4 < i; i4++)
                            acomplex3[i4] = new Complex(acomplex[(k + k2) - 1][l + i4]);

                        Complex complex3 = zdotu(i, acomplex3, 0, 1, acomplex1, j1, k1);
                        complex5.re = complex.re * complex3.re - complex.im * complex3.im;
                        complex5.im = complex.re * complex3.im + complex.im * complex3.re;
                        acomplex2[i5 - 1].add(complex5);
                        for(int j4 = 0; j4 < i; j4++)
                            acomplex[(k + k2) - 1][l + j4] = new Complex(acomplex3[j4]);

                        i5 += i2;
                    }

                } else
                {
                    int j5 = j3;
                    for(int l2 = 1; l2 <= j; l2++)
                    {
                        for(int k4 = 0; k4 < i; k4++)
                            acomplex3[k4] = new Complex(acomplex[(k + l2) - 1][l + k4]);

                        Complex complex4 = zdotc(i, acomplex3, 0, 1, acomplex1, j1, k1);
                        complex5.re = complex.re * complex4.re - complex.im * complex4.im;
                        complex5.im = complex.re * complex4.im + complex.im * complex4.re;
                        acomplex2[j5 - 1].add(complex5);
                        j5 += i2;
                    }

                }
        }
    }

    static final void zgerc(int i, int j, Complex complex, Complex acomplex[], int k, int l, Complex acomplex1[], int i1, 
            int j1, Complex acomplex2[][], int k1, int l1, int i2)
    {
        Complex complex1 = new Complex();
        Complex complex2 = new Complex();
        if(i >= 0 && j >= 0 && i2 >= i && i2 != 0 && l != 0 && j1 != 0 && i != 0 && j != 0 && !complex.equals(complex1))
        {
            Complex acomplex3[] = new Complex[Math.max(i, j)];
            int j2 = 1 + i1;
            if(j1 < 0)
                j2 = (-j + 1) * j1 + 1 + i1;
            for(int k2 = 1; k2 <= j; k2++)
            {
                for(int l2 = 0; l2 < i; l2++)
                    acomplex3[l2] = new Complex(acomplex2[(k1 + k2) - 1][l1 + l2]);

                Complex complex3 = Complex.multiply(complex, Complex.conjugate(acomplex1[j2 - 1]));
                zaxpy(i, complex3, acomplex, k, l, acomplex3, 0, 1);
                for(int i3 = 0; i3 < i; i3++)
                    acomplex2[(k1 + k2) - 1][l1 + i3] = new Complex(acomplex3[i3]);

                j2 += j1;
            }

        }
    }

    static final double znr1rr(int i, int j, Complex acomplex[][])
    {
        if(i <= 0)
        {
            System.out.println("From znr1rr: The number of rows of the input matrix must be ");
            System.out.println("greater than zero but nra = " + i);
        }
        if(j <= 0)
        {
            System.out.println("From znr1rr: The number of columns of the input matrix must ");
            System.out.println("be greater than zero but nca = " + j);
        }
        double d = 0.0D;
        for(int l = 0; l < j; l++)
        {
            double d1 = 0.0D;
            for(int k = 0; k < i; k++)
                d1 += Complex.zabs1(acomplex[k][l]);

            d = Math.max(d1, d);
        }

        return d;
    }

    static final void zscal(int i, Complex complex, Complex acomplex[], int j, int k)
    {
        if(i > 0)
            if(k != 1)
            {
                int j1 = i * k;
                for(int l = 0; l < j1; l += k)
                    acomplex[l + j].multiply(complex);

            } else
            {
                for(int i1 = 0; i1 < i; i1++)
                    acomplex[i1 + j].multiply(complex);

            }
    }

    static final void zset(int i, Complex complex, Complex acomplex[], int j, int k)
    {
        if(i > 0)
            if(k == 1)
            {
                int k1 = i % 8 + j;
                for(int l = j; l < k1; l++)
                    acomplex[l] = complex;

                int l1 = k1 + 1;
                for(int i1 = k1; i1 < i + j; i1 += 8)
                {
                    acomplex[i1] = complex;
                    acomplex[i1 + 1] = complex;
                    acomplex[i1 + 2] = complex;
                    acomplex[i1 + 3] = complex;
                    acomplex[i1 + 4] = complex;
                    acomplex[i1 + 5] = complex;
                    acomplex[i1 + 6] = complex;
                    acomplex[i1 + 7] = complex;
                }

            } else
            {
                int i2 = i * k + j;
                for(int j1 = j; j1 < i2; j1 += k)
                    acomplex[j1] = complex;

            }
    }

    static final void zswap(int i, Complex acomplex[], int j, int k, Complex acomplex1[], int l, int i1)
    {
        Complex complex = new Complex();
        if(i > 0)
            if(k != 1 || i1 != 1)
            {
                int i2 = j;
                int j2 = l;
                if(k < 0)
                    i2 = (-i + 1) * k + j;
                if(i1 < 0)
                    j2 = (-i + 1) * i1 + l;
                for(int j1 = 0; j1 < i; j1++)
                {
                    Complex complex1 = acomplex[i2];
                    acomplex[i2] = acomplex1[j2];
                    acomplex1[j2] = complex1;
                    i2 += k;
                    j2 += i1;
                }

            } else
            {
                int k2 = i % 3;
                for(int k1 = 0; k1 < k2; k1++)
                {
                    Complex complex2 = acomplex[k1 + j];
                    acomplex[k1 + j] = acomplex1[k1 + l];
                    acomplex1[k1 + l] = complex2;
                }

                for(int l1 = k2; l1 < i; l1 += 3)
                {
                    Complex complex3 = acomplex[l1 + j];
                    acomplex[l1 + j] = acomplex1[l1 + l];
                    acomplex1[l1 + l] = complex3;
                    complex3 = acomplex[l1 + 1 + j];
                    acomplex[l1 + 1 + j] = acomplex1[l1 + 1 + l];
                    acomplex1[l1 + 1 + l] = complex3;
                    complex3 = acomplex[l1 + 2 + j];
                    acomplex[l1 + 2 + j] = acomplex1[l1 + 2 + l];
                    acomplex1[l1 + 2 + l] = complex3;
                }

            }
    }*/
}