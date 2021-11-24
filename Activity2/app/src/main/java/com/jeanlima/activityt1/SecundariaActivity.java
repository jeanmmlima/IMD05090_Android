package com.jeanlima.activityt1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecundariaActivity extends AppCompatActivity {

    Button btnVoltar;

    TextView tvNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);

        btnVoltar = findViewById(R.id.btnVoltar);

        tvNome = findViewById(R.id.tvNome);

        Intent it = getIntent();

        String nome = it.getExtras().getString("nome");

        tvNome.setText(nome);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}