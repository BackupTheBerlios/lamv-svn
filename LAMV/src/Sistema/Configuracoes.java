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
/**Classe que armazena as informações sobre as configurações do MaLab*/
public class Configuracoes {
    
    /** Creates a new instance of configuracoes */
    public Configuracoes() {
    }
    /**Indica o diretório padrão a ser aberto quando o MaLab é iniciado */
    public String diretorioPadrao = new String("");
    /**Indica se o MenuItem está indicando se o MaLab Explorer deve 
     * filtrar os arquivos em .txt, ou exibir todos os arquivos*/
    public boolean maLabExplorerFiltrar = true;
    /**Indica se a exibição dos dados usará a notação científica */
    public boolean usarNotacaoCientifica = true;
    /**Indica os dados serão exibidos em uma única janela */
    public boolean janelaUnicaDados = false;
    /**Vetor de endereço de dados a serem carregados na inicialização do MaLab */
    public Vector enderecos = new Vector();
    
}
