/*
 * leDisco.java
 *
 * Created on 20 de Abril de 2005, 14:25
 */

package Sistema;

/**
 *
 * @author Barnes
 */
/** Classe que opera métodos de operação i/o no disco*/
import Dados.Matriz;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;


/** Classe de operações I/O no disco rígido*/
public class leDisco extends Thread
 {
    //private ListaM lista;
    double[][] element;
    int rows, columns;    
    private JFrame J;
    private String caminho;
    private JFileChooser f;
    private FileInputStream fin;
    private Matriz retorno;
    private Matriz M2;
    private String nome,t;
    private String info = new String("");
    private Configuracoes conf;
    public leDisco(Configuracoes con)
     {
           conf = con;
     	   f = new JFileChooser();
     	   //this.lista = lista;
     	   J = new JFrame();
     	   f.setFileFilter(new FileFilter() //Filtro de exibição para extensão definida
     	     {  
     	          public boolean accept(File f)
     	             {   
                         return f.getName().toLowerCase().endsWith(".txt")|| f.isDirectory();
     	             }
     	          public String getDescription()
     	             {
     	                return "Txt Files"; 
     	             }
                 });
     }
    public leDisco()
    {}
    
   /**Instância um novo objeto leDisco
    @param filtro especifica os tipos de arquivos que serão visualizados no form de Open/Save
    0 para arquivos de texto e 1 para arquivos de imagem*/
   public leDisco(int filtro)
     {
     	 f = new JFileChooser();
     	 //this.lista = lista;
     	 J = new JFrame();
     	 if(filtro == 0) //texto
     	 {
     	      f.setFileFilter(new FileFilter() //Filtro de exibição para extensão definida
     	         {  
     	           public boolean accept(File f)
     	             {  
     	                 return f.getName().toLowerCase().endsWith(".txt")|| f.isDirectory();
     	             }
     	           public String getDescription()
     	             {
     	                return "Txt Files"; 
     	             }
                 });
         }
         else if(filtro == 1) //imagem
     	 {
     	      f.setFileFilter(new FileFilter() //Filtro de exibição para extensão definida
     	         {  
     	           public boolean accept(File f)
     	             {  
     	                 return f.getName().toLowerCase().endsWith(".eps")|| f.isDirectory();
     	             }
     	           public String getDescription()
     	             {
     	                return "Encapsuled Post Script (eps)"; 
     	             }
                 });
          }
     }
   
   /**Fluxo de execução da classe responsável por exibir o form de abrir/salvar arquivo e abrir a tela
    de progressão*/
   public void run () 
     {
         if(f.APPROVE_OPTION == f.showOpenDialog(J))
           {
                FileInputStream fin = null;
                String temp;
                String Path = new String(f.getSelectedFile().getPath());
                String nome = new String(f.getName(f.getSelectedFile()));
                java.util.StringTokenizer novoNome = new java.util.StringTokenizer(nome,".");
                nome = novoNome.nextToken();
                System.out.println(Path+".ml");
                Janelas.TelaProgressao  Bar = new Janelas.TelaProgressao(new JFrame(), Path,nome);
                Bar.start();

   	   }
     } //fim do método
   
   /**Lê um arquivo em disco
    @param arquivo nome do arquivo
    @return Retorna o conteúdo do arquivo no formato String*/
   private String le(String arquivo)
   {
      String dados = new String("");
      String temp;
      FileInputStream fin=null;
      //abrindo o arquivo para leitura
      try 
       {
           fin =  new FileInputStream(arquivo);
           try //Inicio try do DataInputStream
  	    {
  		 DataInputStream myInput = new DataInputStream(fin);
  		 try 
  		   {
            		while ((temp = myInput.readLine()) != null) 
            	         {
       	    	             dados = dados.concat(temp+"\n");
          	         }
          	   }
          		 catch (Exception e) 
          		  {
      	    		       Sistema.FSys.Msg("ERRO!", "Erro no readLine: " + e.toString(), 0);   
          		  }  
        	  } //fim try do DataInputStream
           catch (Exception e) 
             {
                   Sistema.FSys.Msg("ERRO!", e.toString(), 0);
             }
      } //fim try do FileInputStream
      catch (Exception e) 
       {
                Sistema.FSys.Msg("ERRO!", "Falha ao abrir o arquivo "+arquivo.trim()+"\n ", 0);
       }
      try
       {
       	fin.close();
       }
     catch (Exception e) 
       {
       	        Sistema.FSys.Msg("ERRO!", "Falha ao fechar o arquivo: "+arquivo+"\n "+ e.toString(), 0);
       }
     return dados;
   }

   /**Grava uma matriz no disco*/
   public void grava(Matriz M)
   {
         File arq;
         f.setFileSelectionMode( javax.swing.JFileChooser.FILES_ONLY );
     	   if(f.APPROVE_OPTION == f.showSaveDialog(J))//verifica se clicou em 'ok'
     	     {
     	            File arquivoNome = f.getSelectedFile();
                    java.util.StringTokenizer tokens = new java.util.StringTokenizer(arquivoNome.getParent(),".");
                    if(tokens.countTokens() == 1)
                    {
                        arquivoNome = new File(f.getSelectedFile().getPath()+".txt");
                    }
     	            File arquivonomeConfig = new File(f.getSelectedFile().getPath()+".ml");
     	            if( arquivoNome == null || arquivoNome.getName().equals(""))
       	            javax.swing.JOptionPane.showMessageDialog( null, "Nome de Arquivo Inválido","Nome de Arquivo Inválido", javax.swing.JOptionPane.ERROR_MESSAGE );
     	     	    else
     	     	    {
     	     		try
     	     		{
     	     		     grava_texto(arquivoNome,M); 
                             this.grava_cabecario(arquivonomeConfig,M);
     	     		}
     	     		catch(IOException e)
     	     		{
     	     		     javax.swing.JOptionPane.showMessageDialog( null, "ERRO","Erro em operação de I/O!", javax.swing.JOptionPane.ERROR_MESSAGE );
     	     		}
     	     	    }     	     		
  	     }
   }
   
   /*
   private void grava_bytes(String nome, Matriz M) 
   {
              String dados = new String("");
              String temp;
              File new_file;
              FileOutputStream fin=null;
              int i,j;
              //f.set
              try 
               {
                    new_file = new File(nome);
                    new_file.createNewFile();
               }
              catch(IOException e)
               {
                  Sistema.FSys.Msg("ERRO!", "Falha ao criar o arquivo\n " + e.toString(), 0);	
               }  
                  
             //abrindo o arquivo para leitura
             try 
              {
                 fin =  new FileOutputStream(nome);
                 try //Inicio try do DataInputStream
                   {
                      DataOutputStream myInput = new DataOutputStream(fin);
                      try 
                       {
            	   for(i = 0; i<M.getnLinhas(); i++)
            	     {
            	       for(j = 0; j<M.getnColunas(); j++)
            	         {
            	   		try 
     	     		     		 {
				    		myInput.writeChar('y');
				    		//myInput.writeString((char)M.M[i][j]+" ");
			     		 } 
			   		catch (IOException e) 
			     		 {
			       	  	        Sistema.FSys.Msg("Erro!", "Não foi possível gravar as informações" ,JOptionPane.ERROR_MESSAGE);
			     			return;
			     		 }
			     	  }
			     	  myInput.writeChar('\n');
			     }

          	      }
          	    catch (Exception e) 
          		{
	       	  	        Sistema.FSys.Msg("Erro","Erro no writeLine: " + e.toString() ,JOptionPane.ERROR_MESSAGE);
				return;
			} 
        	  } //fim try do DataInputStream
           catch (Exception e) 
        	  {
        	  	Sistema.FSys.Msg( "Erro", "Erro no OutputStream: " + e.toString(),JOptionPane.ERROR_MESSAGE);
			return;
        	  }
      } //fim try do FileInputStream
      catch (Exception e) 
       {
       	        Sistema.FSys.Msg( "Falha para abrir o arquivo " + nome, "Erro: " + e.toString(),JOptionPane.ERROR_MESSAGE);
		return;
       }
      try
       {
       	 fin.close();
       	
       }
     catch (Exception e) 
       {
       	          Sistema.FSys.Msg( "Falha para fechar o arquivo " + nome, "Error: " + e.toString(),JOptionPane.ERROR_MESSAGE);
		  return;


       }
                  Sistema.FSys.Msg("Salvar", "Matriz armazenada com sucesso!",JOptionPane.INFORMATION_MESSAGE);
    // return 
   }
    */
   public void le_cabecario(Matriz arg,String s) {
       Matriz Ret = new Matriz(arg.getnLinhas(), arg.getnColunas());
       Ret.M = arg.M;
       int Tipo,DimLin,DimCol;
       String Nome;
       String nomeLinhas[] = new String[arg.getDimL()];
       String nomeColunas[] = new String[arg.getDimC()];
       String Palavra;
       StringTokenizer strLinhas, strColunas;
       StringTokenizer str = new StringTokenizer(s,"$");
       System.out.println(str.countTokens());
       System.out.println("Nome: "+str.nextToken());
       System.out.println("DimL: "+str.nextToken());
       System.out.println("DimC: "+str.nextToken());
       //linhas
       String linhas = str.nextToken();
       strLinhas = new StringTokenizer(linhas,",");
       //System.out.print("LINHAS: ");
       for(int i = 0; strLinhas.hasMoreTokens() && i<arg.getDimL(); i++) {
           nomeLinhas[i] = strLinhas.nextToken();
       }
       arg.setNomeLinhas(nomeLinhas);
       //colunas
       String colunas = str.nextToken();
       strColunas = new StringTokenizer(colunas,",");
       for(int i = 0; strColunas.hasMoreTokens() && i < arg.getDimC(); i++) {
           nomeColunas[i] = strColunas.nextToken();
       }
       arg.setNomeColunas(nomeColunas);
   }

public void grava_cabecario(File outputFile,Matriz M)throws IOException
{

        File outputFileConfi;
        FileWriter out = new FileWriter(outputFile);
        double d = 0.965;
        out.write('$');
        out.write(M.getName());
        out.write("$"+M.getDimL());
        out.write("$"+M.getDimC());
        out.write("$");
        for(int i = 0; i < M.getnLinhas(); i++) //grava nome das linhas e das colunas
           {
                 if(i != M.getnLinhas() -1)
                   out.write(M.nomeLinhas[i]+",");
                 else
                   out.write(M.nomeLinhas[i]+"");
           }
        out.write("$");
        for(int i = 0; i < M.getnColunas(); i++)
          {
                 if(i != M.getnColunas() -1)
                   out.write(M.nomeColunas[i]+",");
                 else
                   out.write(M.nomeColunas[i]+"");
          }
        out.write('$');
        out.close();
}

/** Salva as configurações do MaLab no disco*/
public void grava_boot()throws IOException 
{
    File outputFile = new File("c:\\malab_conf.txt");
    FileWriter out = new FileWriter(outputFile);
    System.out.println(conf.enderecos.size());
    for(int i = 0; i < conf.enderecos.size(); i++ )
    {
        out.write((String)conf.enderecos.get(i));
        out.write("    ");
        out.write(13);
        out.write(10);
    }
    out.close();
}

/** */
public void le_boot()throws IOException
{
    String temp = "";
    FileInputStream fin=null;
    try {
            fin =  new FileInputStream("c:\\malab_conf.txt");
            try //Inicio try do DataInputStream
            {
                DataInputStream myInput = new DataInputStream(fin);
                try
                {
                    while ((temp = myInput.readLine()) != null) {
                        if(!temp.trim().equals(""))
                        {
                             conf.enderecos.add(temp);                            
                             Janelas.TelaProgressao Bar = new Janelas.TelaProgressao(new JFrame(), temp,"teste");
                             Thread t = new Thread(Bar);
                             t.start();
                        }
                    }
                    
                }
                catch(Exception e)
                {
                    Sistema.FSys.Msg("Erro","Erro ao ler o arquivo "+temp.trim(), 0);
                }
            }
            catch(Exception e)
            {
                Sistema.FSys.Msg("Erro","Erro ao ler o arquivo "+temp.trim(), 0);
            }
    }
    catch(Exception e)
    {
         Sistema.FSys.Msg("Erro","Não foi possivel obter o arquivo malab_conf.txt", 0);
    }

}

public void grava_texto(File outputFile, Matriz M)throws IOException
 {
        File outputFileConfi;
        FileWriter out = new FileWriter(outputFile);
        //laco para gravar os dados
        for(int i = 0; i < M.getnLinhas(); i++)
           {
                 for(int j = 0; j < M.getnColunas(); j++)
                     {
                           int pot = 0;
                           double n = M.M[i][j];
                           boolean flag = true;
                           String SPot;
                           String Sinal;
                           out.write( FSys.NotacaoCientifica(n) );
                           out.write("    ");
                     }
                 
                 out.write("    ");
                 out.write(13);
                 out.write(10);
           }
        out.close();
    }
    public void salvaJPEG(Container cp, JPanel f,  String Path) {  
              String caminho = "";
              StringTokenizer str = new StringTokenizer(Path,"\\");
              while(str.hasMoreTokens()){
                  if(str.hasMoreTokens())
                      caminho = caminho + str.nextToken() + "\\\\";
                  else
                      caminho = caminho + str.nextToken();                      
              }
              caminho = caminho.substring(0, caminho.length()-2);
              caminho = caminho.toLowerCase().trim();
              File arq = new File(caminho);
              final Component comp = cp.add(f); 
              JPanel south = new JPanel(); 
              try 
                { 
                       int w = comp.getWidth(), h = comp.getHeight(); 
                       BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB); 
                       Graphics2D g2 = image.createGraphics(); 
                       comp.paint(g2); 
                       ImageIO.write(image, "jpeg", arq); 
                       g2.dispose(); 
                 } 
              catch (IOException e) 
              { 
                     System.err.println(e); 
              }
              
       }
}
