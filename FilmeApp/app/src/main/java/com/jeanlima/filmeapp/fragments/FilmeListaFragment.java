package com.jeanlima.filmeapp.fragments;

import android.os.Bundle;
import android.widget.ArrayAdapter;

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

}