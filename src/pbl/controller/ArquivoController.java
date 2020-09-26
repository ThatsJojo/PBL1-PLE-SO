package pbl.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

public class ArquivoController {
    
    public String lerArquivo(String nome) throws FileNotFoundException{
        File arq = new File(nome);
        if(!arq.exists()){
            throw new FileNotFoundException();
        }
        BufferedReader buffReader = new BufferedReader(new FileReader(nome));
        Stream<String> a = buffReader.lines();
        Iterator leitor = a.iterator();
        String result = new String();
        while(leitor.hasNext()){
            result = result+""+leitor.next()+"\n";
        }
        return result;
    }
    
    public void escreverArquivo(String nome, String data) throws IOException{
        if(!InicializarArquivo(nome)){
            System.out.println("Falha ao inicializar arquivo "+nome);
            return;
        }
        BufferedWriter buffWriter = new BufferedWriter(new FileWriter(nome));
        buffWriter.write(data);
        buffWriter.close();
    }
    
    public boolean InicializarArquivo(String nome) throws IOException{
        File arq = new File(nome);
        if(arq.exists()) return true;
        return arq.createNewFile();
    }
    
    
        
    
    
    
}
