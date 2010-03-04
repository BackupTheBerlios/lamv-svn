

package Dados;

import java.awt.*;
import java.applet.*;

//---------------------------------------------------------------------------

public class Lista    // Classe de lista simplesmente encadeada, chave de busca : nome da Matriz
{
    Inode inicio;
    Inode fim;
    private int nNos;           // numero de nos

    public Lista() 
    {
        inicio = null;
        fim = null;
        nNos = 0;  
    }
    public int getnNos()
    {
        return nNos;
    }
    public boolean Insere( Dado nova )
    {
        Inode No = new Inode(nova);
        Dado temp;
        temp = Procura(nova.getName());
        if(temp != null)          // ja existe uma matriz com esse nome
            return false;        

        No.next = null;
        if(inicio == null)
            inicio = No;
        if(fim != null)
            fim.next = No;
        fim = No;
        nNos++;
        return true;             // sucesso na insercao
    }
    public Dado Procura(String nome)        // retorna a Matriz buscada pelo nome, ou null quando nao a encontra
    {
       Inode temp = inicio;
       while(temp != null)
       {
           if(temp.D.getName().equals(nome))
               return temp.D;           // achou o no procurado e retorna sua posicao
           temp = temp.next;            // vai para o proximo no
       }
       return null;
    }

    public Dado getD( int index )
    {
        if( index >= nNos || index < 0 )
        {
            return null;
        }

        int c = 0;
        Inode no = inicio; 
        while( index != c && no != null )
        {
            no = no.next;
            c++;
        }
        if( no == null )
            return null;
        if( index == c )
            return no.D;
        else
            return null;
    }

    public boolean Remove(String nome)       // remove uma matriz atraves do nome
    {
        Inode atual;
        Inode ant;
        
        atual = inicio;
        ant = null;
        while(!atual.D.getName().equals(nome) && atual != null )
        {
            ant = atual;
            atual = atual.next;
        }
        if(atual == null)         // lista vazia
        {
            return false;
        }
        if(atual == inicio)       // remover a raiz da lista
        {
            inicio = inicio.next;
            atual = null;
            System.gc();         // chama a coleta de lixo Java
            nNos--;
            return true;
        }
        else
        {
            if(atual == fim)      // remover o fim da lista
            {
                fim = ant;
                fim.next = null;
                atual = null;
                System.gc();
                nNos--;
                return true;
            }
            else                 // remover no meio da lista
            {
                ant.next = atual.next;
                atual = null;
                System.gc();
                nNos--;
                return true; 
            }
        }
    }
    
    public void info() {
        System.out.println("Listando dados:");
        Dado d;
        for( int c = 0; c < nNos; c++ ) {
            d = getD(c);
            if( d == null )
                return;
            System.out.println(d.getName());
        }
    }
}

//---------------------------------------------------------------------------