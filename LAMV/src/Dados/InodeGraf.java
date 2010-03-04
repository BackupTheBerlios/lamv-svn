/*
 * InodeGraf.java
 *
 * Created on 21 de Janeiro de 2005, 06:01
 */

package Dados;

/**
 *
 * @author  Gustavo
 */

import Janelas.IFGrafico;


class InodeGraf
{
    public IFGrafico G;
    public InodeGraf next;        // Ponteiro para o proximo nó
    
    public InodeGraf( IFGrafico _G ) {
        this.G = _G;
        this.next = null;
    }
}