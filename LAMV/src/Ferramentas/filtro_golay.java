/*
 * filtro_golay.java
 *
 * Created on 28 de Abril de 2007, 16:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Ferramentas;

import Dados.Matriz;

/**
 *
 * @author Anderson
 */
public class filtro_golay {
    Matriz W, B;
    /** Creates a new instance of filtro_golay */
    public filtro_golay(Matriz x, int k, int F) {
        if(java.lang.Math.round(F) != F)
        {
            Sistema.FSys.Msg("erro","Frame length must be an integer.",0);
            return;
        }
        if((F % 2) != 1) 
        {
            Sistema.FSys.Msg("erro","Frame length must be odd.",0);
            return;
        }
        if(java.lang.Math.round(k) != k) 
        {
            Sistema.FSys.Msg("erro","Polynomial degree must be an integer.",0);
            return;
        }
        if(k > F-1)
        {
            Sistema.FSys.Msg("erro","The degree must be less than the frame length.",0);        
            return;
        }
        W = new Matriz(F,1);
        for(int i = 0; i < F; i++)
        {
            for(int j = 0; j < 1; j++)
            {
                W.M[i][j] = 1;
            }
        }
        //B = sgolay(k,F,W);
        B = sgolay(k,F,W);
        

    }
    
    /**
     * @param args the command line arguments
     */
    //public static void main(String[] args) {
        // TODO code application logic here
    //}

    private Matriz sgolay(int k, int F, Matriz W) {
       Matriz Ww = W;
       // Check for right length of W
       if(W.getDimL() != F)
       {
            Sistema.FSys.Msg("erro","The weight vector must be of the same length as the frame length.",0);
            return null;
       }
       // Check to see if all elements are positive
       //if(min(W) <= 0, error('All the elements of the weight vector must be greater than zero.'), end
       // Diagonalize the vector to form the weighting matrix
       //W = diag(W);
       Matriz newW = new Matriz(F,F);
       for(int i = 0; i < F; i++)
           newW.M[i][i] = 1;
       //vander(-(F-1)/2:(F-1)/2)
       double v[] = new double[((F-1)/2)*2+1];
       for(int i = -(F-1)/2; i <= ((F-1)/2); i ++)
           v[i+((F-1)/2)] = i;
       Matriz Vv = vander(v,0);
       //vander(-(F-1)/2,(F-1)/2);
       Vv = fliper(Vv);
       
        return newW;
    }

/*function A = vander(v)
%VANDER Vandermonde matrix.
%   A = VANDER(V) returns the Vandermonde matrix whose columns
%   are powers of the vector V, that is A(i,j) = v(i)^(n-j).
%
%   Class support for input V:
%      float: double, single

%   Copyright 1984-2004 The MathWorks, Inc. 
%   $Revision: 5.9.4.1 $  $Date: 2004/07/05 17:01:29 $

n = length(v);
v = v(:);
A = ones(n,class(v));
for j = n-1:-1:1
    A(:,j) = v.*A(:,j+1);
end*/

    public static Matriz vander(double a[], int numColumns)
    {
        int arrayLength = a.length;
        int col = 0;
        double temp = 0.0D;
        if(numColumns == 0)
            col = arrayLength;
        else
            col = numColumns;
        Matriz vanderMatrix = new Matriz(arrayLength, col);
        for(int i = 0; i < arrayLength; i++)
        {
            for(int j = 0; j < col; j++)
            {
                temp = Math.pow(a[i], col - 1 - j);
                vanderMatrix.M[i][j] = temp;
            }

        }

        return vanderMatrix;
    }    

    private Matriz fliper(Matriz Vv) {
        Matriz newV = new Matriz(Vv.getnLinhas(),Vv.getnColunas());
        for(int i = 0; i < Vv.getnLinhas(); i++)
            for(int j = 0; j < Vv.getnColunas() ; j++)
                newV.M[i][j] = Vv.M[i][Vv.getnColunas()-j-1];
        return newV;
    }
    
}
