/*
 * Inode.java
 *
 * Created on 21 de Janeiro de 2005, 06:01
 */

package Dados;

/**
 *
 * @author  Gustavo
 */

class Inode
{
    public Dado D;
    public Inode next;        // Ponteiro para o proximo n�
    
    Inode( Dado D ) {
        this.D = D;
        this.next = null;
    }
}