package com.jeanlima.filmeapp2.fragmentos;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.jeanlima.filmeapp2.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class FilmeListaFragment extends ListFragment {

    List<Filme> mFilmes;
    ArrayAdapter<Filme> mAdapter;

    public FilmeListaFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFilmes = carregaFilme();

        //Configuração do adaptador --> (atividade, layout => simple_list_item_1, lista de dados)
        mAdapter = new ArrayAdapter<Filme>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mFilmes
        );

        //Fazendo adaptador carregador os dados
        setListAdapter(mAdapter);

    }

    private List<Filme> carregaFilme(){

        List<Filme> filmes = new ArrayList<Filme>();

        filmes.add(new Filme("Os Vigadores","2h15",2012,4.5f));
        filmes.add(new Filme("O senhor dos aneis","2h35",2004,4.0f));
        filmes.add(new Filme("Duna","2h10",2021,3.5f));
        filmes.add(new Filme("Star Wars","2h00",2019,5.0f));


        return filmes;
    }


}