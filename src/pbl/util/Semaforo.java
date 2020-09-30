package pbl.util;

public class Semaforo{
    private int flag;
    private final int resources;
    private static Semaforo uniqueInstance;
    
    private Semaforo(int resources){
        this.resources = resources;
        flag = resources;
    }
    
    public static synchronized Semaforo getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new Semaforo(1);
        }
        return uniqueInstance;
    }
    
    public void down(){
        while(true){
            if(flag>0){
                flag--;
                System.out.println("foi feito um down");
                return;
            }try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {}
        }
    }
    
    public void up(){
        if(flag<resources){
            flag++;
            System.out.println("Foi feito um up");
        }
    }
    
}
