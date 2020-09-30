package pbl.model;
import java.io.IOException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import pbl.util.Contador;


public class Conexao extends Thread implements Comparable{
    private final int id;
    private final int readOrWrite;
    private final int tempoExecucao;
    private final int tempoInicio;
    private Arquivo arquivo;
    private boolean conectado;
    

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
        this.readOrWrite = readOrWrite;//Leitura é 0. Escrita é 1.
        this.tempoExecucao = tempoExecucao;
        this.tempoInicio = tempoInicio;
        this.conectado = false;
    }

    public int getReadOrWrite() {
        return readOrWrite;
    }
    
    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void run(){
        if(!conectado){
            System.out.println("Thread não está conectada a um arquivo");
            return;
        }
        try {
            if(readConexao()){
                System.out.println("Thread "+id+" Iniciando operação de leitura em "+arquivo.getNome()+" no tempo: " + Contador.getInstance().getTime()+".");
                System.out.println(arquivo.getConteudo());
                Thread.sleep(tempoExecucao*1000);
                System.out.println("Thread "+id+" finalizando execução no tempo: "+ Contador.getInstance().getTime()+".");
            }else{
                System.out.println("Thread "+id+" Iniciando operação de escrita em "+arquivo.getNome()+" no tempo: " + Contador.getInstance().getTime()+".");
                String texto = arquivo.getConteudo();
                Date d = new Date();
                String data = ""+d.getHours()+"h "+d.getMinutes()+"min "+d.getSeconds()+" do dia "+d.getDay()+" no mês "+d.getMonth()+" do ano de "+d.getYear();
                texto = texto + "A Thread "+id+" iniciou uma operação de escrita no arquivo \""+arquivo.getNome()+"\" no instante "+d+". - Tempo de execução"+Contador.getInstance().getTime()+".\n";
                Thread.sleep(tempoExecucao*1000);
                texto = texto + "A Thread "+id+" finalizou uma operação de escrita no arquivo \""+arquivo.getNome()+"\" no instante "+d+". - Tempo de execução"+Contador.getInstance().getTime()+".\n";
                System.out.println("Thread "+id+" finalizando execução no tempo: "+ Contador.getInstance().getTime()+".");
                arquivo.setConteudo(texto);
            }
        } catch (InterruptedException ex) {
            
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public boolean readConexao(){
        return readOrWrite == 0;
    }
    
    public void conectar(Arquivo arquivo){
        if (arquivo == null)
            return;
        this.arquivo = arquivo;
        this.conectado = true;
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

    @Override
    public boolean equals(Object o) {
        if(o instanceof Conexao){
            return ((Conexao) o).id == this.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        return hash;
    }
    
    
    
}