package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int contador = 0;
    private TextView contadorTextView;
    private Button botonContar;
    private Button botonReiniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //conectar esta clase de java a su correspondiente XML
        setContentView(R.layout.activity_main);

        //Asociar cada objeto de java al elemento correspondiente XML
        contadorTextView = findViewById(R.id.textView_Numero);
        botonContar = findViewById(R.id.button_Contar);
        botonReiniciar = findViewById(R.id.button_Reiniciar);

        //Evento clasico
        /*
        botonContar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                contador++;
                contadorTextView.setText(Integer.toString(contador));
            }
        });
        */

        //evento con funcion lambda o funcion de flecha
            botonContar.setOnClickListener(v -> {
                contador++;
                contadorTextView.setText(String.valueOf(contador));
            });

            botonReiniciar.setOnClickListener(v -> {
                contador = 0;
                contadorTextView.setText(String.valueOf(contador));
            });

        }

}