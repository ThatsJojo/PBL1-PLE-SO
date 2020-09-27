package pbl.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import pbl.model.Conexao;
import pbl.util.Contador;

public class ThreadController {
    private static ArquivoController arquivoController;
    private static Conexao[] Conexoes;
    private static LinkedList<Conexao> conexoesProntas;
    private static boolean[] filaPronta;
    private static ThreadController instance;
    
    public static synchronized ThreadController getInstance(){
        if(instance == null)
            instance = new ThreadController();
        return instance;
    }
    
    private ThreadController() {
        Random random = new Random();
        conexoesProntas = new LinkedList<>();
        int nConexoes = 3+random.nextInt(17);
        Conexoes = new Conexao[nConexoes];
        filaPronta = new boolean[1];
        filaPronta[0] = true;
        for(int i =0; i<nConexoes;i++){
            Conexoes[i] = new Conexao(i, random.nextInt(1), (5+random.nextInt(6)), (0+random.nextInt(10)));
        }
        adicionarThreads();
        inicializarThreads();
        try {
            arquivoController = ArquivoController.getInstance();
        } catch (IOException ex) {
            System.out.println("Problema na criação dos arquivos");
        }
    }
    
    private static void inicializarThreads(){
        new Thread(){
            @Override
            public void run(){
                while(filaPronta[0]||!(conexoesProntas.isEmpty())){
                    try{
                        conexoesProntas.removeFirst().start();
                        
                        
                    }
                    catch(NoSuchElementException ex){
                }
                }
            }
        }.start();
        
        
    }
    
    
    private static void adicionarThreads(){
        int nConexoes = Conexoes.length;
        for(int i=0;i<nConexoes;i++){
            for(int j=0;j<nConexoes;j++){
                if(Conexoes[j].compareTo(Conexoes[i])>0){
                    Conexao temp = Conexoes[i];
                    Conexoes[i] = Conexoes[j];
                    Conexoes[j]=temp;
                }
            }
        }
        
        for(Conexao c: Conexoes){
            System.out.println("Conexão "+c.getInternalID()+"   Tempo de chegada "+c.getTempoInicio()+"     Tempo de execução "+c.getTempoExecucao());
        }
        
        new Thread(){
            @Override
            public void run(){
                int idConexao=0;
                while((idConexao<nConexoes)&&(Contador.getInstance().getAbsoluteTime()<=Conexoes[nConexoes-1].getTempoInicio())){
                    try {
                        if(Conexoes[idConexao].getTempoInicio() == Contador.getInstance().getAbsoluteTime()){
                            do{
                                System.out.println("A Conexão: "+Conexoes[idConexao]
                                        .getInternalID()+" foi  adicionada à fila de execução tempo: "
                                        +Contador.getInstance().getAbsoluteTime());
                                conexoesProntas.add(Conexoes[idConexao]);
                                idConexao++;
                            }while((idConexao<nConexoes)&&Conexoes[idConexao].getInternalID()==Contador.getInstance().getAbsoluteTime());
                        }
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        System.out.println("Erro ao inicializar conecções: "+ex);
                    }
                }
                filaPronta[0]=false;
                System.out.println("-----------------------------------------------------");
                System.out.println("     Todas as conecções foram adicionados à fila");
                System.out.println("-----------------------------------------------------");
            }
        }.start();
    }
  
}