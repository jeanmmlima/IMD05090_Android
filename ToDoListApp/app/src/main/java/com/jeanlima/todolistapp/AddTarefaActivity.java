package com.jeanlima.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanlima.todolistapp.dao.TarefaDAO;
import com.jeanlima.todolistapp.modelo.Tarefa;

public class AddTarefaActivity extends AppCompatActivity {

    private EditText etAddTarefa;
    private Tarefa tarefaAtual;

    private Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarefa);

        etAddTarefa = findViewById(R.id.etAddTarefa);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        Intent it = getIntent();

        try{
            //edita tarefa
            tarefaAtual = (Tarefa) it.getExtras().getSerializable("tarefa");
        }catch(Exception e){
            //criar um nova tarefa
            tarefaAtual = null;
        }

        if(tarefaAtual != null){
            etAddTarefa.setText(tarefaAtual.getDescricao());
        }

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());


                if(tarefaAtual != null){//editar tarefa

                    String descricao = etAddTarefa.getText().toString();
                    if(!descricao.isEmpty()){
                        tarefaAtual.setDescricao(descricao);
                        if(tarefaDAO.atualizar(tarefaAtual)){
                            Toast.makeText(getApplicationContext(), "Tarefa atualizada", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else { //adicionar tarefa
                    String descricao = etAddTarefa.getText().toString();
                    if(!descricao.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setDescricao(descricao);
                        if(tarefaDAO.salvar(tarefa)){
                            Toast.makeText(getApplicationContext(), "Tarefa cadastrada", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao ao cadastrar tarefa", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }
}