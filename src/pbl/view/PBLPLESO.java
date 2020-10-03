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
    }
    
}
