package com.jeanlima.filmeapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jeanlima.filmeapp.fragments.FilmeDetalheFragment;
import com.jeanlima.filmeapp.fragments.FilmeDialogFragment;
import com.jeanlima.filmeapp.fragments.FilmeListaFragment;
import com.jeanlima.filmeapp.fragments.InfoDialogFragment;
import com.jeanlima.filmeapp.model.Filme;

public class FilmeActivity extends AppCompatActivity
        implements FilmeListaFragment.AoClicarNoFilme, FilmeDialogFragment.AoSalvarFilme,
                    SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener,
InfoDialogFragment.AoClicarEmInfo{

    private FilmeListaFragment filmeListaFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFragmentManager = getSupportFragmentManager();

        filmeListaFragment = (FilmeListaFragment) mFragmentManager.findFragmentById(R.id.fragmentLista);


    }

    @Override
    public void clicouNoFilme(Filme filme) {

        if(isTablet()){

            //1. instanciar o fragmento desejado
            FilmeDetalheFragment filmeDetalheFragment = FilmeDetalheFragment.novaInstancia(filme);

            //2 Exibir o fragmento (coloca-lo no layout)

            //2.1 Fragment Manager - gerenciador de fragmentos

            //FragmentManager fm = getSupportFragmentManager();

            //2.2 Fragment Transaction - adiciona ou troca um fragmento
            FragmentTransaction ft = mFragmentManager.beginTransaction();

            //(layout onde será exibido o fragment - id, passar a instancia do fragment, a TAG)
            ft.replace(R.id.detalhe,filmeDetalheFragment,FilmeDetalheFragment.TAG_DETALHE);
            ft.commit();


        } else {
            Intent it = new Intent(this, FilmeDetalheActivity.class);
            it.putExtra("filme",filme);
            startActivity(it);
        }

    }

    private boolean isTablet(){
        return findViewById(R.id.detalhe) != null;
    }

    //1. Carregar o Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflar layout do menu na atividade (na action bar)

        getMenuInflater().inflate(R.menu.menu,menu);

        //1. criar objeto do tipo MenuItem e associa-lo ao tem pesquisar no layout
        MenuItem searchItem = menu.findItem(R.id.acao_pesquisar);

        //2. Criar objeto do tipo de campo busca (Search View)

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        //3. FilmeActivity vai OUVIR os eventos de SearchView
        searchView.setOnQueryTextListener(this);

        //3.1 - hint/placeholder do campo pequisar
        searchView.setQueryHint("Procurar...");

        //4. FilmeActivity vai OUVIR os eventos de expandir e recolher o item na action bar
        MenuItemCompat.setOnActionExpandListener(searchItem,this);
        return true;
    }

    //2. Implementa acoes quando algum item do menu é clicado

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.acao_novo:

                FilmeDialogFragment filmeDialogFragment =
                        FilmeDialogFragment.novaInstancia();
                filmeDialogFragment.show(mFragmentManager,FilmeDialogFragment.DIALOG_TAG);
                break;
            case R.id.acao_info:
                //1. instancia o dialog
                InfoDialogFragment infoDialogFragment = new InfoDialogFragment();
                infoDialogFragment.show(mFragmentManager,"INFO");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void salvouFilme(Filme filme) {
        filmeListaFragment.adicionar(filme);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }
    //chamado toda vez que o parametro da busca muda: usuario inserindo carcteres
    @Override
    public boolean onQueryTextChange(String newText) {
        filmeListaFragment.buscar(newText);
        return false;
    }


    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        filmeListaFragment.limpaBusca();
        return true;
    }

    @Override
    public void aoClicar(int botao) {

        if(botao == DialogInterface.BUTTON_POSITIVE){
            Intent it = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.imd.ufrn.br"));
            startActivity(it);
        }

    }
}