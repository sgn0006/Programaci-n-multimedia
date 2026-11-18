
package com.example.evaluablebolque2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ventana3 extends AppCompatActivity {

    // Elementos del XML
    TextView txtNombre, txtPuntos;
    Button btnInicio, btnRanking;

    // Datos recibidos
    String nombreJugador;
    int puntuacion;

    // Base de datos
    BaseDatos baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_ventana3);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        // Relacionar elementos Java con XML
        txtNombre = findViewById(R.id.textView5);
        txtPuntos = findViewById(R.id.textView4);

        btnInicio = findViewById(R.id.button3);
        btnRanking = findViewById(R.id.button4);

        // Recibir datos desde Ventana2
        nombreJugador = getIntent().getStringExtra("nombre");
        puntuacion = getIntent().getIntExtra("puntuacion", 0);

        // Mostrar nombre y puntuación
        txtNombre.setText(getString(R.string.resultado_jugador, nombreJugador));

        txtPuntos.setText(getString(R.string.puntos, puntuacion));

        // Guardar automáticamente en SQLite
        // Evitar duplicados si Android recrea la Activity
        if (savedInstanceState == null) {

            baseDatos = new BaseDatos(this);

            long resultado = baseDatos.guardarPartida(nombreJugador, puntuacion);

            if (resultado == -1) {
                Toast.makeText(this,R.string.error_guardar,Toast.LENGTH_SHORT).show();
            }
        }

        // BOTÓN VOLVER AL INICIO
        btnInicio.setOnClickListener(v -> {Intent intent = new Intent(Ventana3.this,MainActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivity(intent);
            finish();
        });

        // BOTÓN VER RANKING
        btnRanking.setOnClickListener(v -> {Intent intent = new Intent(Ventana3.this,Ventana4.class);

            startActivity(intent);
        });
    }
}
