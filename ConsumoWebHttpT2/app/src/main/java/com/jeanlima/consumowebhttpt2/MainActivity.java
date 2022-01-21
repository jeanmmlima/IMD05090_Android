package com.jeanlima.consumowebhttpt2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnAcessar;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAcessar = findViewById(R.id.btnAcessar);
        tvResultado = findViewById(R.id.tvResultado);

        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MinhaTarefa tarefa = new MinhaTarefa();

                String cep = "01001000";
                String urlApi = "https://viacep.com.br/ws/"+cep+"/json/";
                tarefa.execute(urlApi);

            }
        });
    }

    class MinhaTarefa extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String stringUrl = strings[0];
            InputStream inputStream = null;

            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = null;

            try{
                //1. acessar a API/Web service
                //1.1 passar o objeto do tipo URL
                URL url = new URL(stringUrl);

                //1.2 abre conexao, conectar do serviço e recupera resposta (dados)
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                //2. recuperar os dados
                inputStream = conexao.getInputStream();

                //2.1 bytes -> caracteres
                inputStreamReader = new InputStreamReader(inputStream);

                //2.2 fazer leitura dos caracteres

                BufferedReader reader = new BufferedReader(inputStreamReader);

                buffer = new StringBuffer();
                //2.3 usar o reader para ler as informações e armazenar no buffer

                String linha = "";

                while((linha = reader.readLine()) != null){
                    buffer.append(linha);
                }




            }catch(MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            tvResultado.setText(resultado);
        }
    }
}