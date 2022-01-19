package com.jeanlima.tarefasassincronasapp;

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

    //inicia a tarefa assíncrona
    public void inciarAsyncTask(View view){
            MinhaAsyncTask tarefa = new MinhaAsyncTask();
            tarefa.execute(10);
    }

    //Criando a classe asynctask que vai implementar a minha tarefa assíncrona
    //AsyncTask<1,2,3>
    //1 -> TIPO de parâmetro a ser passado para a classe como entrada
    //2 -> TIPO de parâmetro processado durante a execução da tarefa assíncrona
    //3 -> TIPO de parâmetro que vai ser o retorno

    class MinhaAsyncTask extends AsyncTask<Integer,Integer,String>{

        //Executa em um contexto de uma NOVA THREAD.
        // "..." os 3 pontos (var args) permite a passagem de diversos parametros do tipo que é definido
        //recebe o TIPO de parametro 1
        @Override
        protected String doInBackground(Integer... integers) {

            int numero = integers[0];

            for(int i = 0; i <= numero; i++){
                //enviar valor para onProgressUpdate, que tem acesso a UI thread
                //parametro de publishProgress é do TIPO de parametro 2 (passado na criação da classe asynctask)
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
        // configurações iniciais
        //executa no contexto da THREAD PRINCIPAL (UI Thread) (ideal que não tenho código pesado)
        //tem acesso aos componentes visuais
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        //executa no contexto da Thread Principal (UI THread)
        //recebe o TIPO de parametro 2
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        //executa no contexto da THREAD principal (UI thread) -> (evitar código pesado)
        //Recebe como parametro o TIPO de parametro 3 (saída) que é o retorno do método doInBackGround
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}