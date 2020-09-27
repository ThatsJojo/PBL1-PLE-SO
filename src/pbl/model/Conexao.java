package pbl.model;
import java.util.Observable;
import java.util.Observer;
import pbl.util.Contador;


public class Conexao extends Thread implements Observer, Comparable{
    private final int id;
    private final int readOrWrite;
    private final int tempoExecucao;
    private final int tempoInicio;

    public int getInternalID(){
        return id;
    }

    public int getTempoInicio() {
        return tempoInicio;
    }

    public int getTempoExecucao() {
        return tempoExecucao;
    }
    
    public Conexao(int id, int readOrWrite, int tempoExecucao, int tempoInicio) {
        this.id = id;
        this.readOrWrite = readOrWrite;
        this.tempoExecucao = tempoExecucao;
        this.tempoInicio = tempoInicio;
    }
    
    @Override
    public void run(){
        try {
            while(Contador.getInstance().getAbsoluteTime()!=tempoInicio){
                Thread.sleep(1000);
            }
            System.out.println("Thread "+id+" Iniciando execução no tempo: " + Contador.getInstance().getTime()+".");
            System.out.println("Tempo de execução: "+tempoExecucao);
            Thread.sleep(tempoExecucao*1000);
            System.out.println("Thread "+id+" finalizando execução no tempo: "+ Contador.getInstance().getTime()+".");
        } catch (InterruptedException ex) {
            System.out.println("Thread "+id+" foi interrompida no tempo "+ Contador.getInstance().getTime()+".");
        }
        
    }
    
    public void conectar(){
        
    }

    @Override
    public void update(Observable o, Object o1) {
        
    }

    @Override
    public int compareTo(Object t) {
        if(t instanceof Conexao){
            if(this.tempoInicio!=((Conexao) t).tempoInicio)
                return this.tempoInicio-((Conexao) t).tempoInicio;
            return this.id-((Conexao) t).id;
        }
        return 0;
    }
    
}