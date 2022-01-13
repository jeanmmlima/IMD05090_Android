package com.jeanlima.todolistapp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_BD = "bd_todolist";
    public static String TABELA_TAREFAS = "tarefas";

    public DBHelper(@Nullable Context context){
        super(context,NOME_BD,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "descricao VARCHAR(50) NOT NULL);";

        try{
            db.execSQL(sql);
            Log.i("INFO DB","Sucesso ao criar ao tabela!");
        }catch(Exception e){
            Log.i("INFO DB","Erro ao criar tabela "+e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABELA_TAREFAS +";";

        try{
            db.execSQL(sql);
            onCreate(db);
            Log.i("INFO DB","Sucesso ao criar ao tabela!");
        }catch(Exception e){
            Log.i("INFO DB","Erro ao criar tabela "+e.getMessage());
        }


    }
}
