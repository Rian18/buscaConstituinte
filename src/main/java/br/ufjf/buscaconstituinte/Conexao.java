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
                gitHub=GitHub.connectUsingOAuth("b126eeba42a0b5738e58855ddfbc1123c411919e");
            }
            return gitHub;
        }
}
