package pbl.view;

import java.io.IOException;
import pbl.controller.ArquivoController;
import pbl.util.NotTrackedFileException;


public class PBLPLESO {
    public static void main(String[] args) {
        ArquivoController arquivoController;
        try {
            arquivoController = new ArquivoController(3);
        } catch (IOException ex) {
            System.out.println("Impossível criar arquivos.");
            System.out.println("Sistema Fechado");
            return;
        }
        String data = "Linha 1\nLinha 2\nLinha 3\nLinha 4\nLinha 5";
        String lido;
        
        try {
            lido = arquivoController.lerArquivo("Arquivo1.txt");
            System.out.println("A leitura inicial foi realizada.");
        } catch (NotTrackedFileException ex) {
            System.out.println("Arquivo não está listado");
            lido ="";
        } catch (IOException ex) {
            System.out.println("Arquivo1 estava vazio.");
            lido = "";
        }
        data = lido + data;
        try {
            arquivoController.escreverArquivo("Arquivo1.txt", data);
            System.out.println("Escrita realizada em Arquivo1");
        } catch (IOException ex) {
            System.out.println("Arquivo bugadoS");
        } catch (NotTrackedFileException ex) {
            System.out.println("Arquivo não trackeado 1");
        }
            
        
        
        try {
            lido = arquivoController.lerArquivo("Arquivo1.txt");
            System.out.println("Leitura no Arquivo1:\n"+lido);
        } catch (NotTrackedFileException ex) {
            System.out.println("Arquivo não trackeado 2");
        } catch (IOException ex) {
            System.out.println("Arquivo vazio");
        }            
    }
    
}
