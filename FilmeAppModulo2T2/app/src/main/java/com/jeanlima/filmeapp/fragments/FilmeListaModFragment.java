package com.jeanlima.filmeapp.fragments;

import android.app.Activity;
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

    AdapterMod adapterMod;



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
        adapterMod = new AdapterMod(mFilmes);


        //2. Configurar RecyclerView para receber o adapter modificado
        //2.1 definir o layout padrão do RV - LAYOUT MANAGER
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvFilmes.setLayoutManager(layoutManager);

        //2.2 tamanho fixo
        rvFilmes.setHasFixedSize(true);

        //2.3 set adapter
        rvFilmes.setAdapter(adapterMod);

        //3. Implementar a interface AoClicarNaLista para OUVIR os eventos de click
        //nos elementos do recycler view (card view)
        adapterMod.implementaAoClicarNaLista(new AdapterMod.AoClicarNaLista() {
            @Override
            public void clicouNoElemento(int position) {
                //Toast.makeText(getActivity(), mFilmes.get(position).getNome(),Toast.LENGTH_SHORT).show();

                //1. Verificar se a atividade IMPLEMENTA a interface AoClicarNoFilmeMod
                Activity activity = getActivity();

                if(activity instanceof AoClicarNoFilmeMod){

                    //2. listener do tipo da interface para estabelecer a comunicação entre
                    //fragmento e atividade

                    AoClicarNoFilmeMod listener = (AoClicarNoFilmeMod) activity;
                    listener.clicouNoFilmeMod(mFilmes.get(position));
                }

            }
        });


        return layout;
    }

    public interface AoClicarNoFilmeMod{
        public void clicouNoFilmeMod(Filme filme);
    }

    private List<Filme> carregaFilmes(){

        List<Filme> filmes = new ArrayList<Filme>();

        filmes.add(new Filme("Star Wars","2h10min",2019,3.5f));
        filmes.add(new Filme("Duna","2h11min",2021,4.0f));
        filmes.add(new Filme("Os Vingadores","3h00min",2012,4.5f));
        filmes.add(new Filme("O Senhor dos Aneis","2h30min",2004,5.0f));

        return filmes;
    }

    public void adicionar(Filme filme){
        mFilmes.add(filme);
        adapterMod.notifyDataSetChanged();
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
        adapterMod = new AdapterMod(filmesEncontrados);
        rvFilmes.setAdapter(adapterMod);

    }

    public void limpaBusca(){
        adapterMod = new AdapterMod(mFilmes);
        rvFilmes.setAdapter(adapterMod);
    }
}