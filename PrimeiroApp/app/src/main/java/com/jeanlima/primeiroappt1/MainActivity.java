package com.jeanlima.primeiroappt1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanlima.utilidade.GeraNumero;

public class MainActivity extends AppCompatActivity {

    //Declarar componentes visuais
    //exemplo1
    EditText etNome;
    Button btnEnviar;
    TextView tvSaudacao;

    //exemplo2 - gerar numero
    Button btnNumero;
    TextView tvNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        btnEnviar = findViewById(R.id.btnEnviar);
        tvSaudacao = findViewById(R.id.tvSaudacao);

        //exemplo 2
        btnNumero = findViewById(R.id.btnGerarNumero);
        tvNumero = findViewById(R.id.tvNumero);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = etNome.getText().toString();
                tvSaudacao.setText("Bem-vindo, "+nome);

            }
        });

        btnNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeraNumero gerarNumero = new GeraNumero();

                tvNumero.setText("Número: "+ gerarNumero.getNumero());
            }
        });


    }

    /*
    public void enviar(View view){
        String nome = etNome.getText().toString();
        tvSaudacao.setText("Bem-vindo, "+nome);
    }
     */
}