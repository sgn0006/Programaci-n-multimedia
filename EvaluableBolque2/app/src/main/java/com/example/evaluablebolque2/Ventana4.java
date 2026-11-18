
package com.example.evaluablebolque2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Ventana4 extends AppCompatActivity {

    // Elementos de la interfaz
    ListView lvRanking;
    Button btnVolver;
    TextView txtSinPartidas;

    // Base de datos
    BaseDatos baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_ventana4);

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

        // Relacionar los elementos del XML
        lvRanking = findViewById(R.id.listView);
        btnVolver = findViewById(R.id.btnVolver);
        txtSinPartidas = findViewById(R.id.txtSinPartidas);

        // Crear conexión con SQLite
        baseDatos = new BaseDatos(this);

        // Botón volver al inicio
        btnVolver.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Ventana4.this,
                    MainActivity.class
            );

            intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
            );

            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Actualizar el ranking al entrar
        cargarRanking();
    }

    private void cargarRanking() {

        // Recuperar las partidas guardadas
        ArrayList<String> partidas =
                baseDatos.obtenerRanking();

        // Crear el adaptador
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<>(
                        this,
                        R.layout.ranking,
                        R.id.txtRanking,
                        partidas
                );

        // Asociar el adaptador al ListView
        lvRanking.setAdapter(adaptador);

        // Mostrar mensaje si no hay partidas
        if (partidas.isEmpty()) {

            txtSinPartidas.setVisibility(
                    android.view.View.VISIBLE
            );

            lvRanking.setVisibility(
                    android.view.View.GONE
            );

        } else {

            txtSinPartidas.setVisibility(
                    android.view.View.GONE
            );

            lvRanking.setVisibility(
                    android.view.View.VISIBLE
            );
        }
    }

    @Override
    protected void onDestroy() {
        if (baseDatos != null) {
            baseDatos.close();
        }
        super.onDestroy();
    }
}
