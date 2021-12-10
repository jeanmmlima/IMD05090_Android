package com.jeanlima.filmeapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeanlima.filmeapp.R;
import com.jeanlima.filmeapp.adapters.AdapterMod;
import com.jeanlima.filmeapp.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class FilmeListaModFragment extends Fragment {

    RecyclerView rvFilmes;

    List<Filme> mFilmes = new ArrayList<Filme>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.fragment_filme_lista_mod, container, false);

        rvFilmes = layout.findViewById(R.id.rvFilmes);

        mFilmes = carregaFilmes();

        //1. Configurar Adapter modificado
        AdapterMod adapterMod = new AdapterMod(mFilmes);


        //2. Configurar RecyclerView para receber o adapter modificado
        //2.1 definir o layout padrão do RV - LAYOUT MANAGER
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvFilmes.setLayoutManager(layoutManager);

        //2.2 tamanho fixo
        rvFilmes.setHasFixedSize(true);

        //2.3 set adapter
        rvFilmes.setAdapter(adapterMod);

        return layout;
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