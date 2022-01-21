package com.jeanlima.tarefaassincronaappt2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
    }

    //iniciar a tarefa assíncrona
    public void iniciarAsyncTask(View view){

        MinhaAsyncTask tarefa = new MinhaAsyncTask();
        tarefa.execute(10);

    }

    //Criando a classe asynctask que vai implementar a minha tarefa assíncrona
    //AsyncTask<1,2,3>

    //1 -> TIPO de parametro a ser passado para a classe MinhaAsyncTask como entrada
    //2 -> TIPO do parametro processado durante a execução da tarefa assíncrona
    //3 - > TIPO do parametro que vai ser o retorno

    class MinhaAsyncTask extends AsyncTask<Integer,Integer,String>{

        //doInbackground executa no contexto de uma Nova Thread (não é no contexto da thread principal)
        //"..." 3 pontos (var args) permite a passagem de diversos parametros do tipo que é defino
        //recebe o TIPO de parametro 1
        @Override
        protected String doInBackground(Integer... integers) {

            int numero = integers[0];

            for(int i = 0; i <= numero; i++){
                //enviar valor para o onProgressUpdate, que tem acesso a UI thread.
                //parametro de publishProgress é o TIPo de parametro 2 (passado na criação da classe)
                publishProgress(i);
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            return "Finalizado";
        }

        //executa ANTES do doInBackGround
        //executa no contexto da thread principal (UI THREAD) (ideal que não tenha codigo pesado)
        //tem acesso aos componentes visuais
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        //executa no contexto da thread principal (UI Thread)
        //Executa durante a execução do doInBackGround
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        //executa no contexto da thread principal (UI THREAD)
        //recebe como parametro o TIPO 3 (retorno do método doInBackGround)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}