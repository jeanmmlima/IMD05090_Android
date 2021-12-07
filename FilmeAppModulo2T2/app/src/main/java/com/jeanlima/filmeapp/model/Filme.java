package com.jeanlima.filmeapp.model;

import java.io.Serializable;

public class Filme implements Serializable {

    private String nome;
    private String duracao;
    private int ano_lancamento;
    private float estrelas;

    public Filme(){

    }

    public Filme(String nome, String duracao, int ano_lancamento, float estrelas){
        this.nome = nome;
        this.duracao = duracao;
        this.ano_lancamento = ano_lancamento;
        this.estrelas = estrelas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public int getAno_lancamento() {
        return ano_lancamento;
    }

    public void setAno_lancamento(int ano_lancamento) {
        this.ano_lancamento = ano_lancamento;
    }

    public float getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(float estrelas) {
        this.estrelas = estrelas;
    }

    @Override
    public String toString() {
        return nome;
    }
}
