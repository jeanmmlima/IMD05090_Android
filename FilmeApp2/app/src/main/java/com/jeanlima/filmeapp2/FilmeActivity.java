package com.jeanlima.filmeapp2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jeanlima.filmeapp2.fragmentos.FilmeDetalheFragment;
import com.jeanlima.filmeapp2.fragmentos.FilmeListaFragment;
import com.jeanlima.filmeapp2.model.Filme;

public class FilmeActivity extends AppCompatActivity
        implements FilmeListaFragment.AoClicarNoFilme {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);
    }

    @Override
    public void clicouNoFilme(Filme filme) {

        if(isTablet()){

            //1. Criar uma instancia do fragmento de detalhe
            FilmeDetalheFragment filmeDetalheFragment = FilmeDetalheFragment.novaInstancia(filme);


            //2. Exibir (inflar) fragmento

            //2.1 Fragment Manager - gerenciador de fragmentos
            FragmentManager fm = getSupportFragmentManager();

            //2.2 Fragment Transaction - adiciona ou troca fragmentos em uma porção de tela
            FragmentTransaction ft = fm.beginTransaction();

            //replace(espaço no layout da atividade onde o fragmento vai estar,instancia do fragmento, TAG)
            ft.replace(R.id.detalhe,filmeDetalheFragment,FilmeDetalheFragment.TAG_DETALHE);

            ft.commit();

        } else {
            //o parametro filme é o filme que o usuário clicou - comunicação via interface

            Intent it = new Intent(this,FilmeDetalheActivity.class);

            it.putExtra("filme",filme);

            startActivity(it);
        }



    }

    private boolean isTablet(){
        return findViewById(R.id.detalhe) != null;
    }
}