package com.jeanlima.activityi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAbrir;

    EditText etNome;
    TextView tvInfo;

    ListView lvCidades;

    ArrayList<Cidade> cidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAbrir = findViewById(R.id.btnAbrirSecundaria);
        etNome = findViewById(R.id.etNome);
        tvInfo = findViewById(R.id.tvInfo);

        lvCidades = findViewById(R.id.lvCidades);

        //preenchendo lista de cidades

        cidades = new ArrayList<Cidade>();
        cidades.add(new Cidade("Natal"));
        cidades.add(new Cidade("Fortaleza"));
        cidades.add(new Cidade("Recife"));

        //adapter

        ArrayAdapter<Cidade> adapter = new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_1,cidades
        );
        lvCidades.setAdapter(adapter);

        lvCidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cidade cidade = (Cidade) lvCidades.getItemAtPosition(position);

                Intent it = new Intent(getApplicationContext(),SecundariaActivity.class);

                it.putExtra("cidade", cidade);

                startActivityForResult(it,1);
            }
        });


        btnAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = etNome.getText().toString();


                Intent it = new Intent(getApplicationContext(),SecundariaActivity.class);

                it.putExtra("nome",nome);

                startActivityForResult(it,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == 1 && data != null){

            tvInfo.setText(data.getExtras().getString("informacao"));


        } else {
            Toast.makeText(this,"Resultado inválido",Toast.LENGTH_SHORT).show();
        }
    }
}