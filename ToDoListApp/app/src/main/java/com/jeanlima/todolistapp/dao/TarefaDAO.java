package com.jeanlima.todolistapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jeanlima.todolistapp.modelo.Tarefa;
import com.jeanlima.todolistapp.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    private final SQLiteDatabase escreve;
    private final SQLiteDatabase le;

    public TarefaDAO(Context context){
        DBHelper dbHelper = new DBHelper(context);
        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
    }

    public boolean salvar(Tarefa tarefa){

        //1. definir o conteudo a ser salvo
        ContentValues cv = new ContentValues();
        cv.put("descricao",tarefa.getDescricao());

        try{
            escreve.insert(DBHelper.TABELA_TAREFAS,null,cv);
            Log.i("INFO","Registro salvo com sucesso!");
        }catch(Exception e){
            Log.i("INFO","Erro ao salvar registro: "+e.getMessage());
            return false;
        }
        return true;
    }

    public List<Tarefa> listar(){

        List<Tarefa> tarefas = new ArrayList<>();

        //1. string sql de consulta
        String sql = "SELECT * FROM "+DBHelper.TABELA_TAREFAS+ ";";

        //2. Cursor para acesso aos dados
        Cursor c = le.rawQuery(sql,null);

        //3. percorrer o cursor

        while(c.moveToNext()){

            Tarefa tarefa = new Tarefa();

            //Long id = c.getLong( 0 );
            Long id = c.getLong( c.getColumnIndexOrThrow("id") );
            String descricao = c.getString(c.getColumnIndexOrThrow("descricao"));

            tarefa.setId(id);
            tarefa.setDescricao(descricao);

            tarefas.add(tarefa);
        }
        c.close();
        return tarefas;
    }
}
