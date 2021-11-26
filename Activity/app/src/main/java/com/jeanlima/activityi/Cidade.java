package com.jeanlima.activityi;

import java.io.Serializable;

public class Cidade implements Serializable {

    private String nome;

    public Cidade(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
