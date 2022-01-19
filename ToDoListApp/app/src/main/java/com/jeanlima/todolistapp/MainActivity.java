package com.jeanlima.todolistapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jeanlima.todolistapp.adapters.AdapterMod;
import com.jeanlima.todolistapp.dao.TarefaDAO;
import com.jeanlima.todolistapp.modelo.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabAdd;
    RecyclerView rvTarefas;

    AdapterMod adapterMod;

    List<Tarefa> mTarefas = new ArrayList<Tarefa>();

    Tarefa tarefaSelecionada = new Tarefa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAdd = findViewById(R.id.fabAdd);
        rvTarefas = findViewById(R.id.rvTarefas);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),AddTarefaActivity.class);
                startActivity(it);
            }
        });
    }

    public void carregarTarefas(){

        //1. preenchendo mTarefas com as tarefas cadastradas no banco
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        mTarefas = tarefaDAO.listar();

        //2. Exibir a lista de tarefas

        //2.1 Configurar o adapter

        adapterMod = new AdapterMod(mTarefas);

        adapterMod.implementaAoClicarNoItem(new AdapterMod.AoClicarNoItem() {
            @Override
            public void clicouNaTarefa(int pos) {
                //Editar tarefa

                Tarefa tarefa = new Tarefa();
                tarefa = mTarefas.get(pos);

                Intent it = new Intent(getApplicationContext(), AddTarefaActivity.class);
                it.putExtra("tarefa",tarefa);
                startActivity(it);
            }

            @Override
            public void pressionouTarefa(int pos) {

                tarefaSelecionada = mTarefas.get(pos);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setTitle("Confirmar Exclusão");

                dialog.setMessage("Deseja excluir a tarefa: "+tarefaSelecionada.getDescricao()+ " ?");

                dialog.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                        if(tarefaDAO.deletar(tarefaSelecionada)){
                            Toast.makeText(getApplicationContext(), "Tarefa foi removida", Toast.LENGTH_SHORT).show();
                            carregarTarefas();
                        } else {
                            Toast.makeText(getApplicationContext(), "rro ao deletar tarefa", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setNegativeButton("Não",null);

                dialog.create();
                dialog.show();

            }
        });

        //2. configuração recycler view

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvTarefas.setLayoutManager(layoutManager);
        rvTarefas.setHasFixedSize(true);
        rvTarefas.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        rvTarefas.setAdapter(adapterMod);

    }

    @Override
    protected void onStart() {
        this.carregarTarefas();
        super.onStart();
    }
}