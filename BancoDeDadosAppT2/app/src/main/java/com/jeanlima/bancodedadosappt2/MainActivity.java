package com.jeanlima.bancodedadosappt2;

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

        try{

            //1. abrir ou criar o banco de dados
            SQLiteDatabase db = openOrCreateDatabase("db_turma2",MODE_PRIVATE,null);

            //2.0 Excluir a tabela
            db.execSQL("DROP TABLE pessoas");

            //2.1 Criar a tabela - SQL
            db.execSQL("CREATE TABLE IF NOT EXISTS pessoas ("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(50), idade INT(3))");

            //3.

            //3.1 inserir dados na tabela
            db.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('José',20)");
            db.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('João',18)");
            db.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Maria',21)");
            db.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Pedro',25)");

            //3.2 Atualização dos dados
            db.execSQL("UPDATE pessoas SET idade=19 WHERE nome='Maria'");

            //4. recuperando dados da tabela
            //String consulta = "SELECT * FROM pessoas";
            String consulta = "SELECT * FROM pessoas WHERE idade >= 20";

            //4.1 define cursor
            Cursor cursor = db.rawQuery(consulta,null);
            cursor.moveToFirst();

            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            while(cursor != null){

                Log.i("Resultado","ID: "+cursor.getString(indiceId)+" / Nome: "
                        +cursor.getString(indiceNome)+" / Idade: "+cursor.getString(indiceIdade));
                cursor.moveToNext();

            }

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}