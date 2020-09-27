package pbl.model;

import java.util.Observable;
import java.util.Observer;

public class Sincronizador implements Observer{
    Observable[] arquivos;
    Observable[] threads;
    
    public Sincronizador(Observable[] arquivos) {
        this.arquivos = arquivos;
        for(Observable a: arquivos){
            a.addObserver(this);
        }
    }
    
    
    
    @Override
    public void update(Observable o, Object o1) {
        
    }
    
    
}
