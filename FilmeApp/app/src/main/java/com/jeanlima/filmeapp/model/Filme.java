package com.jeanlima.filmeapp.model;

import java.io.Serializable;

public class Filme implements Serializable {

    public String nome;
    public String duracao;
    public int ano_lancamento;
    public float estrelas;

    public Filme(String nome, String duracao, int ano_lancamento, float estrelas){
        this.nome = nome;
        this.duracao = duracao;
        this.ano_lancamento = ano_lancamento;
        this.estrelas = estrelas;
    }

    @Override
    public String toString() {
        return nome;
    }
}
