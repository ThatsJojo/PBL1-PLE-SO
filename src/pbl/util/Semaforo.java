package pbl.util;

public class Semaforo{
    private int flag;
    private final int resources;
    private static Semaforo uniqueInstance;
    
    public Semaforo(int resources){
        this.resources = resources;
    }
    
    public void down(){
        while(true){
            if(flag>0)
                flag--;
        }
    }
    
    public void up(){
        flag++;
    }
    
}
