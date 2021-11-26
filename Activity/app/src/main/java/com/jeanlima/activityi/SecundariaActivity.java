package com.jeanlima.activityi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecundariaActivity extends AppCompatActivity {

    Button btnVoltar;

    TextView tvNome;
    EditText etInfo;

    ImageView imgCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);

        btnVoltar = findViewById(R.id.btnVoltar);
        tvNome = findViewById(R.id.tvNome);
        etInfo = findViewById(R.id.etInfo);
        imgCidade = findViewById(R.id.imgCidade);

        Intent it = getIntent();

        //String nome = it.getExtras().getString("nome");

        //tvNome.setText(nome);

        Bundle dados = it.getExtras();

        Cidade cidade = (Cidade) dados.getSerializable("cidade");

        tvNome.setText(cidade.getNome());

        if(cidade.getNome().equals("Natal")){
            imgCidade.setImageResource(R.drawable.java_logo_640);
        } else if(cidade.getNome().equals("Fortaleza")){
            imgCidade.setImageResource(R.drawable.java_logo_640);
        }



        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String info = etInfo.getText().toString();

                it.putExtra("informacao",info);

                setResult(1,it);

                finish();

            }
        });
    }
}