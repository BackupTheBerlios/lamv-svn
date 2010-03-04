/*
 * ListaGraf.java
 *
 * Created on 21 de Janeiro de 2005, 06:01
 */

package Dados;

/**
 *
 * @author  Gustavo
 */

import java.awt.*;
import java.applet.*;
import Janelas.IFGrafico;

//---------------------------------------------------------------------------

public class ListaGraf    // Classe de lista simplesmente encadeada, chave de busca : nome da Matriz
{
    InodeGraf inicio;
    InodeGraf fim;
    private int nNos;           // numero de nos

    public ListaGraf() 
    {
        inicio = null;
        fim = null;
        nNos = 0;  
    }
    public int getnNos()
    {
        return nNos;
    }
    public boolean Insere( IFGrafico nova )
    {
        InodeGraf No = new InodeGraf(nova);
        IFGrafico temp;
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
    public IFGrafico Procura(String nome)        // retorna a Matriz buscada pelo nome, ou null quando nao a encontra
    {
       InodeGraf temp = inicio;
       while(temp != null)
       {
           if(temp.G.getName().equals(nome))
               return temp.G;           // achou o no procurado e retorna sua posicao
           temp = temp.next;            // vai para o proximo no
       }
       return null;
    }

    public IFGrafico getD( int index )
    {
        if( index >= nNos || index < 0 )
        {
            return null;
        }

        int c = 0;
        InodeGraf no = inicio; 
        while( index != c && no != null )
        {
            no = no.next;
            c++;
        }
        if( no == null )
            return null;
        if( index == c )
            return no.G;
        else
            return null;
    }

    public boolean Remove(String nome)       // remove uma matriz atraves do nome
    {
        InodeGraf atual;
        InodeGraf ant;
        
        atual = inicio;
        ant = null;
        while(!atual.G.getName().equals(nome) && atual != null )
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
                return true;
            }
            else                 // remover no meio da lista
            {
                ant.next = atual.next;
                atual = null;
                System.gc();
                return true; 
            }
        }
    }
    
    public void info() {
        System.out.println("Listando IFGraficos:");
        IFGrafico d;
        for( int c = 0; c < nNos; c++ ) {
            d = getD(c);
            if( d == null )
                return;
            System.out.println(d.getName());
        }
    }
}

//---------------------------------------------------------------------------