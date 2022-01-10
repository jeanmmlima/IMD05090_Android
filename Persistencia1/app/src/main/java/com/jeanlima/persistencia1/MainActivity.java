package com.jeanlima.persistencia1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnEnviar;
    private EditText etNome;
    private TextView tvInformacao;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnviar = findViewById(R.id.btnEnviar);
        etNome = findViewById(R.id.etNome);
        tvInformacao = findViewById(R.id.tvInformacao);

        //recuperar dados salvos

        SharedPreferences preferencias = getSharedPreferences(ARQUIVO_PREFERENCIA,0);

        //verificar se existe informação no arquivo
        if(preferencias.contains("dado1")){
            String informacao = preferencias.getString("dado1","usuário não definido");
            tvInformacao.setText("Olá, "+informacao);
        } else {
            tvInformacao.setText("Olá, usuário não definido.");
        }


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //declarar um objeto do tipo SharedPreferences

                //parametros(nome do arquivo, modo de utilização - 0 privado - só o app tem acesso ao arquivo)
                SharedPreferences preferencias = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
                SharedPreferences.Editor editor = preferencias.edit();

                if(etNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Preencher nome!", Toast.LENGTH_LONG).show();
                } else {
                    String informacao = etNome.getText().toString();

                    //usar o editor para gravar o dado
                    //(chave, conteudo)
                    editor.putString("dado1",informacao);
                    editor.commit();
                    tvInformacao.setText("Olá, "+informacao);
                }



            }
        });
    }
}