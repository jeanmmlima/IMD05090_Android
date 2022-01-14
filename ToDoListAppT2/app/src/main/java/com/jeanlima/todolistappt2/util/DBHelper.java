package com.jeanlima.todolistappt2.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "db_todolist";
    public static String TABELA_TAREFAS = "tarefas";

    public DBHelper(@Nullable Context contexto){
        super(contexto,NOME_DB,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "descricao VARCHAR(50) NOT NULL);";

        try{
            db.execSQL(sql);
            Log.i("INFO DB","Sucesso ao criar a tabela!");
        }catch(Exception e){
            Log.i("INFO DB","Erro ao criar tabela.");
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS " + TABELA_TAREFAS +";";

        try{
            db.execSQL(sql);
            onCreate(db);
        }catch(Exception e){
            Log.i("INFO DB","Erro ao apagar tabela.");
        }

    }
}
