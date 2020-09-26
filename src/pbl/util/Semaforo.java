package pbl.util;

public class Semaforo{
    private int flag;
    private final int resources;
    
    public Semaforo(){
        resources = 1;
    }
    
    public Semaforo(int number){
        resources = number;
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
