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
        ArrayList<Arquivo> arquivos = ArquivoController.getArquivos();
        arquivos.forEach((arquivo)->{
            try {
                if(arquivo.getLastModify()!=ArquivoController.getLastModify(arquivo.getNome())){
                    arquivos.forEach((arq)->{
                        System.out.println("Arquivo :"+arq.getNome());
                        try {
                            ArquivoController.getInstance().escreverArquivo(arq.getNome(), conteudo);
                        } catch (IOException | NotTrackedFileException ex) {
                            Logger.getLogger(Sincronizador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                }
            } catch (IOException ex) {
                Logger.getLogger(Sincronizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Semaforo.getInstance().up();
        sincronizador = new Sincronizador();
    }

    
    
    @Override
    public void update(Observable Arquivo, Object Conteudo) {
        conteudo = (String) Conteudo;
    }   
}