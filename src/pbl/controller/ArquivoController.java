package pbl.controller;

import pbl.util.NotTrackedFileException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;
import pbl.model.Arquivo;

    
public class ArquivoController {
    private final ArrayList<Arquivo> arquivos;
    private static IOController ioController;
    private static ArquivoController arquivoController;
    
    public static synchronized ArquivoController getInstance() throws IOException{
        if(arquivoController == null){
            arquivoController = new ArquivoController(3);
            return arquivoController;
        }
        return arquivoController;
    }
    
    private ArquivoController(int nArquivos) throws IOException{
        arquivos = new ArrayList<>(nArquivos);
        if(ArquivoController.ioController == null)
            ArquivoController.ioController = new IOController();
        for(int i = 0; i<nArquivos; i++){
            String conteudo = "";
            if(!ArquivoController.ioController.existeArquivo("Arquivo"+i+".txt"))
                ArquivoController.ioController.escreverArquivo("Arquivo"+i+".txt", "");
            else
                conteudo = ArquivoController.ioController.lerArquivo("Arquivo"+i+".txt");
            Arquivo a = new Arquivo("Arquivo"+i+".txt",ArquivoController.ioController.lastModify("Arquivo"+i+".txt"));
            a.setConteudo(conteudo);
            arquivos.add(a);
        }
    }
    
    public String lerArquivo (String nome) throws NotTrackedFileException, FileNotFoundException, IOException{
        if(!arquivos.contains(new Arquivo(nome, 1))){
            throw new NotTrackedFileException();
        }
        return ArquivoController.ioController.lerArquivo(nome);
    }
    
    public void escreverArquivo(String nome, String data) throws IOException, NotTrackedFileException{
        for(Arquivo a: arquivos){
            if(a.getNome().equals(nome)){
                ArquivoController.ioController.escreverArquivo(nome, data);
                a.setConteudo(data);
                a.setLastModify(ArquivoController.ioController.lastModify(nome));
                return;
            }
        }
        throw new NotTrackedFileException();
    }
    
    
    
    private class IOController {
        public String lerArquivo(String nome) throws FileNotFoundException, IOException{
            File arq = new File(nome);
            if(!arq.exists()){
                
                throw new FileNotFoundException();
            }
            String result = new String();
            try (BufferedReader buffReader = new BufferedReader(new FileReader(nome))) {
                Stream<String> a = buffReader.lines();
                Iterator leitor = a.iterator();
                while(leitor.hasNext()){
                    result = result+""+leitor.next()+"\n";
                }
            }
            return result;
        }

        public long escreverArquivo(String nome, String data) throws IOException{
            if(!inicializarArquivo(nome)){
                System.out.println("Falha ao inicializar arquivo "+nome);
                throw new IOException();
            }
            try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter(nome))) {
                buffWriter.write(data);
                //buffWriter.close();
            }
            return (new File(nome)).lastModified();
        }

        public long lastModify(String nome) throws FileNotFoundException{
            File arq = new File(nome);
            if (!arq.exists())
                throw new FileNotFoundException();
            return arq.lastModified();
        }
        
        public boolean existeArquivo(String nome){
            return new File(nome).exists();
        }
        
        public boolean inicializarArquivo(String nome) throws IOException{
            File arq = new File(nome);
            if(arq.exists()) return true;
            return arq.createNewFile();
        }
    }
}