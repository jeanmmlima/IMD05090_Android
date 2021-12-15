package com.jeanlima.snackbart2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button btnAbrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAbrir = findViewById(R.id.btnAbrir);

        btnAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(
                        v,
                        "Olá SnackBar!",
                        Snackbar.LENGTH_INDEFINITE
                ).setAction("Confirmar?", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnAbrir.setText("Abrir MOD");
                    }
                }).setActionTextColor(getResources().getColor(R.color.design_default_color_error)).show();

            }
        });
    }
}