package com.jeanlima.todolistappt2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jeanlima.todolistappt2.model.Tarefa;
import com.jeanlima.todolistappt2.util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    private final SQLiteDatabase escreve;
    private final SQLiteDatabase le;

    public TarefaDAO(Context contexto){
        DBHelper dbHelper = new DBHelper(contexto);
        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
    }

    public boolean salvar(Tarefa tarefa){

        //1. definir conteudo a ser salvo
        ContentValues cv = new ContentValues();
        cv.put("descricao",tarefa.getDescricao());

        try{
            escreve.insert(DBHelper.TABELA_TAREFAS,null,cv);
            Log.i("Info","Registro salvo com sucesso!");
        }catch(Exception e){
            Log.i("Info","Erro ao salvar! " + e.getMessage());
            return false;
        }
        return true;
    }


    public List<Tarefa> listar(){

        List<Tarefa> tarefas = new ArrayList<>();

        //1. string sql da consulta
        String sql = "SELECT * FROM " + DBHelper.TABELA_TAREFAS + ";";

        //2. Cursor para percorrer os dados
        Cursor c = le.rawQuery(sql,null);

        //3. percorrer o cursor
        c.moveToFirst();
        while(c.moveToNext()){

            Tarefa tarefa = new Tarefa();
            //exemplo 1
            //Long id = c.getLong(0)
            //exemplo 2
            //int indiceId = c.getColumnIndexOrThrow("id")
            //Long id = c.getLong(indiceId)
            Long id = c.getLong( c.getColumnIndexOrThrow("id") );
            String descricao = c.getString( c.getColumnIndexOrThrow("descricao"));

            tarefa.setId(id);
            tarefa.setDescricao(descricao);

            tarefas.add(tarefa);

        }
        c.close();
        return tarefas;
    }

}
