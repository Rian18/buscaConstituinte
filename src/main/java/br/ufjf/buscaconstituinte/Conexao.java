package br.ufjf.buscaconstituinte;

import java.io.IOException;
import org.kohsuke.github.GitHub;

/**
 *
 * @author Rian Alves
 */
public class Conexao {
    GitHub gitHub;
        
        public GitHub getConexao() throws IOException{
            if(gitHub==null){
                gitHub=GitHub.connectUsingOAuth("58c13b1267705cac0912700bd31b9aac6a7f6bc3");
            }
            return gitHub;
        }
}
