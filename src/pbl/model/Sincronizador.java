package pbl.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import pbl.controller.ArquivoController;
import pbl.util.Contador;
import pbl.util.NotTrackedFileException;
import pbl.util.Semaforo;

public class Sincronizador extends Thread implements Observer{
    private static Sincronizador sincronizador;
    private static String conteudo;
    
    private Sincronizador() {
    }
    
    public static synchronized Sincronizador getInstance(){
        if(sincronizador == null)
            sincronizador = new Sincronizador();
        return sincronizador;
    }
        
    
    
    @Override
    public void run() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Thread sincronizadora ativa no tempo "+Contador.getInstance().getTime()+"\n");        
        ArrayList<Arquivo> arquivos = ArquivoController.getArquivos();
        arquivos.forEach((arquivo)->{
            try {
                if(arquivo.getLastModify()!=ArquivoController.getLastModify(arquivo.getNome())){
                    System.out.println("O "+arquivo.getNome()+" é o mais atual");
                    arquivos.forEach((arq)->{
                        if(!arquivo.equals(arq)){
                            System.out.println("Copiando conteúdo de "+arquivo.getNome()+" para "+arq.getNome()+
                                    " no tempo "+Contador.getInstance().getTime());
                            try {
                                ArquivoController.getInstance().escreverArquivo(arq.getNome(), conteudo);
                            } catch (IOException | NotTrackedFileException ex) {
                                Logger.getLogger(Sincronizador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                }
            } catch (IOException ex) {
                Logger.getLogger(Sincronizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Semaforo.getInstance().up();
        sincronizador = new Sincronizador();
        System.out.println("-------------------------------------------------------------------");
    }

    
    
    @Override
    public void update(Observable Arquivo, Object Conteudo) {
        conteudo = (String) Conteudo;
    }   
}