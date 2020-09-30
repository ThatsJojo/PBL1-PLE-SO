package pbl.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class Arquivo extends Observable{
    private final String nome;
    private long lastModify;
    private String conteudo;
    private boolean escrevendo;
    private final LinkedList<Observer> clientes;
    
    
    public Arquivo(String conteudo, String nome, long lastModify){
        this.conteudo = conteudo;
        this.nome = nome;
        this.lastModify = lastModify;
        this.clientes = new LinkedList<>();
    }

    public long getLastModify() {
        return lastModify;
    }
    
    public void setLastModify(long lastModify) {
        this.lastModify = lastModify;
    }

    public String getConteudo() {
        return conteudo;
    }

    @Override
    public synchronized void addObserver(Observer obsrvr) {
        clientes.add(obsrvr);
    }

    public void setConteudoAssincrono(String conteudo){
        this.conteudo = conteudo;
    }
    
    public void setConteudo(String conteudo) throws IOException {
        this.conteudo = conteudo;
        clientes.forEach((o) -> {
            o.update(this, conteudo);
        });
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Arquivo)
            return ((Arquivo) o).nome.equals(this.nome);
        return false;
    }
    
    public boolean taEscrevendo() {
        return escrevendo;
    }

    public void setEscrevendo(boolean escrevendo) {
        this.escrevendo = escrevendo;
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