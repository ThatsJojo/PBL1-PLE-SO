package pbl.model;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class Arquivo extends Observable{
    private final String nome;
    private long lastModify;
    private String conteudo;
    private final LinkedList<Observer> clientes;
    
    public Arquivo(String nome, long lastModify){
        this.nome = nome;
        this.lastModify = lastModify;
        this.clientes = new LinkedList<>();
    }

    public long getLastModify() {
        return lastModify;
    }
    
    public void setLastModify(long lastModify) {
        this.setChanged();
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
        System.out.println("----------Vai notificar os observers------");
        this.notifyObservers();
        this.lastModify = lastModify;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Arquivo)
            return ((Arquivo) o).nome.equals(this.nome);
        return false;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nome);
        return hash;
    }
    
    
     
    
}
