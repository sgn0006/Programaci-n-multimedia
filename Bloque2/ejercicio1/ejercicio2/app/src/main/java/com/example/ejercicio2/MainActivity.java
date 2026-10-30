package com.example.ejercicio2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout fondo;
    private TextView texto;
    private Button botonToast;
    private Button botonCrecer;
    private Button botonDecrecer;
    private Button botonColor;
    private Button botonLog;
    private CheckBox comprobacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //enlazar java con xml
        fondo = findViewById(R.id.pantalla);
        texto = findViewById(R.id.texto);
        botonToast = findViewById(R.id.boton_Toast);
        botonCrecer = findViewById(R.id.boton_Crece);
        botonDecrecer = findViewById(R.id.boton_Decrece);
        botonColor = findViewById(R.id.boton_Color);
        botonLog = findViewById(R.id.boton_Log);
        comprobacion = findViewById(R.id.comprobacion);

        //eventos

        botonCrecer.setOnClickListener(v -> {
            texto.setTextSize(TypedValue.COMPLEX_UNIT_PX, texto.getTextSize()+20);
        });

        botonDecrecer.setOnClickListener(v -> {
            texto.setTextSize(TypedValue.COMPLEX_UNIT_PX, texto.getTextSize()-20);
        });

        botonColor.setOnClickListener(v -> {
            int randomColor = Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random()));
            fondo.setBackgroundColor(randomColor);
        });

        botonToast.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "HOLA!", Toast.LENGTH_SHORT).show();
        });

        botonLog.setOnClickListener(v -> {
            //comprobar si el checkbox esta marcado o no
            //mostrar mensaje por consola diciendolo
        });

    }
}