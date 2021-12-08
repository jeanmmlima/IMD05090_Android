package com.jeanlima.filmeapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.jeanlima.filmeapp.model.Filme;

import java.util.ArrayList;
import java.util.List;


public class FilmeListaFragment extends ListFragment {

    List<Filme> mFilmes;
    ArrayAdapter<Filme> mAdapter;



    public FilmeListaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFilmes = carregaFilmes();

        //(atividade, layout - simple_list_item_1, lista com dados)
        mAdapter = new ArrayAdapter<Filme>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mFilmes
        );
        //para os dados para a ListView
        setListAdapter(mAdapter);



    }

    private List<Filme> carregaFilmes(){

        List<Filme> filmes = new ArrayList<Filme>();

        filmes.add(new Filme("Star Wars","2h10min",2019,3.5f));
        filmes.add(new Filme("Duna","2h11min",2021,4.0f));
        filmes.add(new Filme("Os Vingadores","3h00min",2012,4.5f));
        filmes.add(new Filme("O Senhor dos Aneis","2h30min",2004,5.0f));

        return filmes;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //1. Comunicar a atividade filmeActivity que um item foi clicado

        Activity activity = getActivity();

        //2. Verificar se a atividade IMPLEMENTA a interface: AoClicarNoFilme

        if(activity instanceof AoClicarNoFilme){

            //2.1 - colocando na variavel filme o elemento da lista que foi clicado
            Filme filme = (Filme) l.getItemAtPosition(position);

            //2.2 - Passar o filme VIA interface

            AoClicarNoFilme listener = (AoClicarNoFilme) activity;
            listener.clicouNoFilme(filme);


        }

    }

    public interface AoClicarNoFilme{
        void clicouNoFilme(Filme filme);
    }

    public void adicionar(Filme filme){
        mFilmes.add(filme);
        mAdapter.notifyDataSetChanged();
    }

    public void buscar(String s){

        //1. validar a string de busca

        if(s == null || s.trim().equals("")){
            limpaBusca();
            return;
        }

        //2. Criar uma lista modificada de filmes de acordo com o parametro de busca (s)

        List<Filme> filmesEncontrados = new ArrayList<Filme>(mFilmes);

        //2.1 percorrer a lista e retirar os filmes NÃO relacionados ao parametro de busca (s)

        for(int i = filmesEncontrados.size()-1; i >= 0; i--){
            Filme filme = filmesEncontrados.get(i);
            //verificar se o filme na posicao i contem em SEU nome o trecho buscado
            //que é representado por s. Se não tiver, filme i sai da lista

            if(!filme.getNome().toUpperCase().contains(s.toUpperCase())){
                filmesEncontrados.remove(filme);
            }
        }
        //Configurar o adapter
        mAdapter = new ArrayAdapter<Filme>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                filmesEncontrados
        );

        setListAdapter(mAdapter);

    }

    public void limpaBusca(){
        //(atividade, layout - simple_list_item_1, lista com dados)
        mAdapter = new ArrayAdapter<Filme>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mFilmes
        );
        //para os dados para a ListView
        setListAdapter(mAdapter);
    }
}