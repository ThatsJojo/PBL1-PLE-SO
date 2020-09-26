package pbl.model;

import java.util.concurrent.Semaphore;

public class Arquivo {
    private String nome;
    private long lastModify;
    private Semaphore semaforo;
    
    public Arquivo(){
    }
}
