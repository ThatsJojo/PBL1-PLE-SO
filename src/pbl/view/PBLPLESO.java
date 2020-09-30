package pbl.view;

import java.io.IOException;
import pbl.controller.ArquivoController;
import pbl.controller.ThreadController;
import pbl.util.Contador;

public class PBLPLESO {
    public static void main(String[] args) {
        System.out.println("Sistema iniciado no tempo: "+Contador.getInstance().getTime());
        ArquivoController arquivoController;
        ThreadController t =  ThreadController.getInstance();
        try {
            arquivoController = ArquivoController.getInstance();
        } catch (IOException ex) {
            System.out.println("Impossível criar arquivos.");
            System.out.println("Sistema Fechado");
            return;
        }
//        String data = "Linha 1\nLinha 2\nLinha 3\nLinha 4\nLinha 5";
//        String lido;
//        
//        try {
//            lido = arquivoController.lerArquivo("Arquivo1.txt");
//            System.out.println("A leitura inicial foi realizada.");
//        } catch (NotTrackedFileException ex) {
//            System.out.println("Arquivo não está listado");
//            lido ="";
//        } catch (IOException ex) {
//            System.out.println("Arquivo1 estava vazio.");
//            lido = "";
//        }
//        data = lido + data;
//        try {
//            arquivoController.escreverArquivo("Arquivo1.txt", data);
//            System.out.println("Escrita realizada em Arquivo1");
//        } catch (IOException ex) {
//            System.out.println("Arquivo bugadoS");
//        } catch (NotTrackedFileException ex) {
//            System.out.println("Arquivo não trackeado 1");
//        }
//            
//        
//        
//        try {
//            lido = arquivoController.lerArquivo("Arquivo1.txt");
//            System.out.println("Leitura no Arquivo1:\n"+lido);
//        } catch (NotTrackedFileException ex) {
//            System.out.println("Arquivo não trackeado 2");
//        } catch (IOException ex) {
//            System.out.println("Arquivo vazio");
//        } 
        
        //Contador.getInstance().interrupt();
    }
    
}
