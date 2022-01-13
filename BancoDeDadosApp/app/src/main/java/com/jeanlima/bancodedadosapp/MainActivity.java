package com.jeanlima.bancodedadosapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {

            //1. criar ou abrir o banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("db_aula",MODE_PRIVATE,null);

            //Apagando tabela
            bancoDados.execSQL("DROP TABLE pessoas");

            //2. criar a tabela - SQL
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(50), idade INT(3))");

            //3.1 inserir dados na tabela
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('José',20)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('João',18)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Maria',21)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Pedro',25)");

            //3.2 atualizar dados na tabela
            bancoDados.execSQL("UPDATE pessoas SET idade=19 WHERE nome='Maria'");

            //4. recuperar dados da tabela

            //4.1 string de consulta
            //String consulta = "SELECT * FROM pessoas";
            //String consulta = "SELECT * FROM pessoas WHERE idade > 20";
            String consulta = "SELECT * FROM pessoas ORDER BY nome ASC";
            //4.2 define o cursor
            Cursor cursor = bancoDados.rawQuery(consulta,null);
            cursor.moveToFirst();

            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            while(cursor != null){

                /*
                Log.i("Resultado","ID: "+cursor.getString(0));
                Log.i("Resultado","Nome: "+cursor.getString(1));
                Log.i("Resultado","Idade: "+cursor.getString(2));
                cursor.moveToNext();
                 */

                String id = cursor.getString(indiceId);
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);

                Log.i("Resultado","id: "+id+" / Nome: "+nome+" / Idade: "+idade);
                cursor.moveToNext();
            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}