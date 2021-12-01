package com.jeanlima.filmeapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jeanlima.filmeapp.fragments.FilmeDetalheFragment;
import com.jeanlima.filmeapp.model.Filme;

public class FilmeDetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme_detalhe);


        Intent it = getIntent();

        //Recupera o dado que veio na intent
        Filme filme = (Filme) it.getExtras().getSerializable("filme");


        //1. Inflando fragment de maneira DINAMICA

        //1.1 Criar instancia do fragmento de detalhe

        FilmeDetalheFragment filmeDetalheFragment = FilmeDetalheFragment.novaInstancia(filme);

        //2 Exibir o fragmento (coloca-lo no layout)

        //2.1 Fragment Manager - gerenciador de fragmentos
        FragmentManager fm = getSupportFragmentManager();

        //2.2 Fragment Transaction - adiciona ou troca um fragmento
        FragmentTransaction ft = fm.beginTransaction();

        //(layout onde será exibido o fragment - id, passar a instancia do fragment, a TAG)
        ft.replace(R.id.detalhe,filmeDetalheFragment,FilmeDetalheFragment.TAG_DETALHE);
        ft.commit();

    }
}