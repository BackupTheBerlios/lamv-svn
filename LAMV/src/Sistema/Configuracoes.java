/*
 * configuracoes.java
 *
 * Created on 17 de Julho de 2005, 17:28
 */

package Sistema;
import java.util.Vector;

/**
 *
 * @author EngSoares
 */
/**Classe que armazena as informa��es sobre as configura��es do MaLab*/
public class Configuracoes {
    
    /** Creates a new instance of configuracoes */
    public Configuracoes() {
    }
    /**Indica o diret�rio padr�o a ser aberto quando o MaLab � iniciado */
    public String diretorioPadrao = new String("");
    /**Indica se o MenuItem est� indicando se o MaLab Explorer deve 
     * filtrar os arquivos em .txt, ou exibir todos os arquivos*/
    public boolean maLabExplorerFiltrar = true;
    /**Indica se a exibi��o dos dados usar� a nota��o cient�fica */
    public boolean usarNotacaoCientifica = true;
    /**Indica os dados ser�o exibidos em uma �nica janela */
    public boolean janelaUnicaDados = false;
    /**Vetor de endere�o de dados a serem carregados na inicializa��o do MaLab */
    public Vector enderecos = new Vector();
    
}
