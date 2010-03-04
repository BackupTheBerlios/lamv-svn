/*
 * Fourier.java
 *
 * Created on 12 de Outubro de 2005, 14:20
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package Ferramentas;
import Dados.*;
/**
 *
 * @author anderson
 */
/*************************************************************************
 *  Calcula a FFT e IFFT de um tabamnho N de número complexo
 *  Limitações: Assume que N N é uma potência de 2 
 *************************************************************************/

public class Fourier {

    public Fourier(){
    }

    /*Computa a FFT de x[], assumindo que o tamanho é potência de 2*/
    public static Complex[] fft(Complex[] x) {
        int N = x.length;
        Complex[] y = new Complex[N];

        // base case
        if (N == 1) {
            y[0] = x[0];
            return y;
        }

        // radix 2 FFT
        if (N % 2 != 0) throw new RuntimeException("Tamanho nao e potência de 2");
        Complex[] even = new Complex[N/2];
        Complex[] odd  = new Complex[N/2];
        for (int k = 0; k < N/2; k++) even[k] = x[2*k];
        for (int k = 0; k < N/2; k++) odd[k]  = x[2*k + 1];

        Complex[] q = fft(even);
        Complex[] r = fft(odd);

        for (int k = 0; k < N/2; k++) {
            double kth = -2 * k * Math.PI / N;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k]       = q[k].plus(wk.times(r[k]));
            y[k + N/2] = q[k].minus(wk.times(r[k]));
        }
        return y;
    }

    /**Computa IFFT de x[] assumindo que o tamanho é potência de 2*/
    public static Complex[] ifft(Complex[] x) {
        int N = x.length;

        // pega o conjugado
        for (int i = 0; i < N; i++)
            x[i] = x[i].conjugate();

        // calcula FFT
        Complex[] y = fft(x);

        // take conjugate again
        for (int i = 0; i < N; i++)
            y[i] = y[i].conjugate();

        // divide pelo tamanho N
        for (int i = 0; i < N; i++)
            y[i] = y[i].times(1.0 / N);

        return y;

    }

    // Computa a convolução de x e y
    public static Complex[] convolve(Complex[] x, Complex[] y) {
        if (x.length != y.length) throw new RuntimeException("Dimensões incompatíveis");
        int N = x.length;

        // Computa a FFT para cada seqüência
        Complex[] a = fft(x);
        Complex[] b = fft(y);

        // Multiplicação ponto a ponto
        Complex[] c = new Complex[N];
        for (int i = 0; i < N; i++)
            c[i] = a[i].times(b[i]);

        // Computa a inversa de IFFT
        return ifft(c);
    }
    private int bitrev(int j) {

        int j2;
        int j1 = j;
        int k = 0;
        for (int i = 1; i <= nu; i++) {
            j2 = j1/2;
            k  = 2*k + j1 - 2*j2;
            j1 = j2;
        }
        return k;
    }

    public final double[] fftMag(double[] x) {
        // assume n is a power of 2
        n = x.length;
        nu = (int)(Math.log(n)/Math.log(2));
        int n2 = n/2;
        int nu1 = nu - 1;
        double[] xre = new double[n];
        double[] xim = new double[n];
        double[] mag = new double[n2];
        double tr, ti, p, arg, c, s;
        for (int i = 0; i < n; i++) {
            xre[i] = x[i];
            xim[i] = 0.0f;
        }
        int k = 0;

        for (int l = 1; l <= nu; l++) {
            while (k < n) {
                for (int i = 1; i <= n2; i++) {
                    p = bitrev (k >> nu1);
                    arg = 2 * (double) Math.PI * p / n;
                    c = (double) Math.cos (arg);
                    s = (double) Math.sin (arg);
                    tr = xre[k+n2]*c + xim[k+n2]*s;
                    ti = xim[k+n2]*c - xre[k+n2]*s;
                    xre[k+n2] = xre[k] - tr;
                    xim[k+n2] = xim[k] - ti;
                    xre[k] += tr;
                    xim[k] += ti;
                    k++;
                }
                k += n2;
            }
            k = 0;
            nu1--;
            n2 = n2/2;
        }
        k = 0;
        int r;
        while (k < n) {
            r = bitrev (k);
            if (r > k) {
                tr = xre[k];
                ti = xim[k];
                xre[k] = xre[r];
                xim[k] = xim[r];
                xre[r] = tr;
                xim[r] = ti;
            }
            k++;
        }

        mag[0] = (double) (Math.sqrt(xre[0]*xre[0] + xim[0]*xim[0]))/n;
        for (int i = 1; i < n/2; i++)
            mag[i]= 2 * (double) (Math.sqrt(xre[i]*xre[i] + xim[i]*xim[i]))/n;
        return mag;
    }
    // teste
    /*public static void main(String[] args) { 
        //int N = Integer.parseInt(args[0]);
        int N = 64;
        Complex[] x = new Complex[N];

        // dados originais
        for (int i = 0; i < N; i++) {
            x[i] = new Complex(i, 0);
        }
        for (int i = 0; i < N; i++)
            System.out.println(x[i]);
        System.out.println();

        // FFT dos dados originais
        Complex[] y = fft(x);
        for (int i = 0; i < N; i++)
            System.out.println(y[i]);
        System.out.println();

        // calcula ifft
        Complex[] z = ifft(y);
        for (int i = 0; i < N; i++)
            System.out.println(z[i]);
        System.out.println();

        Complex[] c = convolve(x, x);
        for (int i = 0; i < N; i++)
            System.out.println(c[i]);

    }*/
    int n, nu;   
}

