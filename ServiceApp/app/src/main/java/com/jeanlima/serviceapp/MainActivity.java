package com.jeanlima.serviceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etMsg;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMsg = findViewById(R.id.etMsg);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String msg = etMsg.getText().toString();
                 Intent it = new Intent(getApplicationContext(), MensagemAtrasadaService.class);
                 it.putExtra(MensagemAtrasadaService.MSG,msg);

                 startService(it);
            }
        });
    }
}