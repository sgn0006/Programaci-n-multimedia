
package com.example.evaluablebolque2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Elementos de la pantalla
    EditText txtNombre;
    Button btnJugar, btnRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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

        // Relacionar los elementos con el XML
        txtNombre = findViewById(R.id.editTextText);
        btnJugar = findViewById(R.id.button2);
        btnRanking = findViewById(R.id.button);

        // BOTÓN JUGAR
        btnJugar.setOnClickListener(v -> {

            // Obtener el nombre introducido
            String nombre = txtNombre.getText()
                    .toString()
                    .trim();

            // Comprobar que el nombre no esté vacío
            if (nombre.isEmpty()) {

                txtNombre.setError(getString(R.string.error_nombre));

                txtNombre.requestFocus();

            } else {

                // Abrir la pantalla de preguntas
                Intent intent = new Intent(
                        MainActivity.this,
                        Ventana2.class
                );

                // Enviar el nombre del jugador
                intent.putExtra("nombre", nombre);

                startActivity(intent);
            }
        });

        // BOTÓN RANKING
        btnRanking.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    Ventana4.class
            );

            startActivity(intent);
        });
    }
}
