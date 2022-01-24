package com.jeanlima.consumowebhttp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

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

    //Async para fazer requisição e receber o resultado
    //1 - entrada URL (String)
    //2 - não envia nenhum dado para Thread Principal durante o progresso da tarefa assíncrona (Void)
    //3 - retorna endereço - (String)
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
                //1.acessar a API/WebService
                //1.1 passar objeto do tipo URL
                URL url = new URL(stringUrl);
                //1.2 abrir conexao
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                //2. recuperar os dados
                inputStream = conexao.getInputStream();

                //2.1 le dados em bytes e codifica para caracteres
                inputStreamReader = new InputStreamReader(inputStream);

                //2.2 permite fazer a leitua dos caracteres
                BufferedReader reader = new BufferedReader(inputStreamReader);
                buffer = new StringBuffer();
                String linha ="";

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

            //3. tratando os dados JSON

            String logradouro = null;
            String cep = null;
            String complemento = null;
            String bairro = null;
            String localidade = null;
            String uf = null;

            try{

                //3.1 criar objeto JSONObject
                JSONObject jsonObject = new JSONObject(resultado);

                logradouro = jsonObject.getString("logradouro");
                cep = jsonObject.getString("cep");
                complemento = jsonObject.getString("complemento");
                bairro = jsonObject.getString("bairro");
                localidade = jsonObject.getString("localidade");
                uf = jsonObject.getString("uf");



            }catch(JSONException e){
                //caso resultado nao esteja num formato válido
                e.printStackTrace();
            }

            tvResultado.setText(logradouro + " / " +cep+" / "+ localidade+" / "+uf);
        }
    }

}