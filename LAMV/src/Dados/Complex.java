
package Dados;

/**
 *
 * @author anderson
 * Esta classe faz a manipulação de números complexos
 *  a = a = 5.0 + 6.0i
 *  b = -3.0 + 4.0i
 *  c = -39.0 + 2.0i
 *  d = -39.0 + -2.0i
 *  e = 5.0
 *
 *************************************************************************/

public class Complex {
    public final double re;   // Parte real
    public final double im;   // Parte imaginaria

    public Complex(){
        this.re = 0;
        this.im = 0;
    }
    /** Cria um novo objeto com a parte real e imaginária*/
    public Complex(double real, double imag) {
        this.re = real;
        this.im = imag;
    }

    /* Retorna uma string representando um objeto invocado*/
    public String toString()  { return re + " + " + im + "i"; }

    // return |this|
    public double abs() { return Math.sqrt(re*re + im*im);  }

    /** Retorna um objeto novo cujo valor seja (this + b)*/
    public Complex plus(Complex b) { 
        Complex a = this;             
        double real = a.re + b.re;
        double imag = a.im + b.im;
        Complex sum = new Complex(real, imag);
        return sum;
    }

    /** Retorn um novo objeto cujo valor seja (this - b)*/
    public Complex minus(Complex b) { 
        Complex a = this;   
        double real = a.re - b.re;
        double imag = a.im - b.im;
        Complex diff = new Complex(real, imag);
        return diff;
    }

    /** Retorn um novo objeto cujo valor seja (this * b)*/
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        Complex prod = new Complex(real, imag);
        return prod;
    }

    /** Retorn um novo objeto cujo valor seja (this * alpha)*/
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    /** Retorn um novo objeto cujo valor seja o conjugado deste*/    
    public Complex conjugate() {  return new Complex(re, -im); }
}

