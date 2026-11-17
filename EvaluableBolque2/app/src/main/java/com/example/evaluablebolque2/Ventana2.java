
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

public class Ventana2 extends AppCompatActivity {

    // Elementos de la interfaz
    TextView txtPregunta, txtNumeroPregunta, txtPuntuacion;
    Button btnRespuesta1, btnRespuesta2, btnRespuesta3,
            btnRespuesta4, btnSiguiente;

    // Variables del juego
    int preguntaActual = 0;
    int puntuacion = 0;
    int respuestaSeleccionada = -1;

    String nombreJugador;

    // Referencias a las 10 preguntas de strings.xml
    int[] preguntas = {
            R.string.pregunta1,
            R.string.pregunta2,
            R.string.pregunta3,
            R.string.pregunta4,
            R.string.pregunta5,
            R.string.pregunta6,
            R.string.pregunta7,
            R.string.pregunta8,
            R.string.pregunta9,
            R.string.pregunta10
    };

    // Referencias a las 4 respuestas de cada pregunta
    int[][] respuestas = {
            {R.string.p1_r1, R.string.p1_r2,R.string.p1_r3, R.string.p1_r4},
            {R.string.p2_r1, R.string.p2_r2,R.string.p2_r3, R.string.p2_r4},
            {R.string.p3_r1, R.string.p3_r2,R.string.p3_r3, R.string.p3_r4},
            {R.string.p4_r1, R.string.p4_r2,R.string.p4_r3, R.string.p4_r4},
            {R.string.p5_r1, R.string.p5_r2,R.string.p5_r3, R.string.p5_r4},
            {R.string.p6_r1, R.string.p6_r2,R.string.p6_r3, R.string.p6_r4},
            {R.string.p7_r1, R.string.p7_r2,R.string.p7_r3, R.string.p7_r4},
            {R.string.p8_r1, R.string.p8_r2,R.string.p8_r3, R.string.p8_r4},
            {R.string.p9_r1, R.string.p9_r2,R.string.p9_r3, R.string.p9_r4},
            {R.string.p10_r1, R.string.p10_r2,R.string.p10_r3, R.string.p10_r4}
    };

    // Posición de la respuesta correcta de cada pregunta
    // 0 = primera, 1 = segunda, 2 = tercera, 3 = cuarta
    int[] respuestasCorrectas = {0, 0, 1, 2, 0, 0, 1, 3, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_ventana2);

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

        // Recibir el nombre del jugador
        nombreJugador = getIntent().getStringExtra("nombre");

        // Relacionar elementos Java con XML
        txtPregunta = findViewById(R.id.txtPregunta);
        txtNumeroPregunta = findViewById(R.id.txtNumeroPregunta);
        txtPuntuacion = findViewById(R.id.txtPuntuacion);

        btnRespuesta1 = findViewById(R.id.btnRespuesta1);
        btnRespuesta2 = findViewById(R.id.btnRespuesta2);
        btnRespuesta3 = findViewById(R.id.btnRespuesta3);
        btnRespuesta4 = findViewById(R.id.btnRespuesta4);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        // Seleccionar una respuesta
        btnRespuesta1.setOnClickListener(v ->seleccionarRespuesta(0));
        btnRespuesta2.setOnClickListener(v ->seleccionarRespuesta(1));
        btnRespuesta3.setOnClickListener(v ->seleccionarRespuesta(2));
        btnRespuesta4.setOnClickListener(v ->seleccionarRespuesta(3));

        // Botón siguiente pregunta
        btnSiguiente.setOnClickListener(v ->siguientePregunta());

        // Mostrar la primera pregunta
        mostrarPregunta();
    }

    // Mostrar pregunta y sus respuestas
    private void mostrarPregunta() {

        txtPregunta.setText(preguntas[preguntaActual]);

        btnRespuesta1.setText(respuestas[preguntaActual][0]);
        btnRespuesta2.setText(respuestas[preguntaActual][1]);
        btnRespuesta3.setText(respuestas[preguntaActual][2]);
        btnRespuesta4.setText(respuestas[preguntaActual][3]);

        txtNumeroPregunta.setText("Pregunta " + (preguntaActual + 1) + " de " + preguntas.length);
        txtPuntuacion.setText("Puntos: " + puntuacion);
        respuestaSeleccionada = -1;

        // Restaurar estado visual de los botones
        btnRespuesta1.setAlpha(1f);
        btnRespuesta2.setAlpha(1f);
        btnRespuesta3.setAlpha(1f);
        btnRespuesta4.setAlpha(1f);
    }

    // Guardar qué respuesta ha seleccionado
    private void seleccionarRespuesta(int respuesta) {

        respuestaSeleccionada = respuesta;

        // Resaltar la respuesta seleccionada
        btnRespuesta1.setAlpha(respuesta == 0 ? 1f : 0.5f);
        btnRespuesta2.setAlpha(respuesta == 1 ? 1f : 0.5f);
        btnRespuesta3.setAlpha(respuesta == 2 ? 1f : 0.5f);
        btnRespuesta4.setAlpha(respuesta == 3 ? 1f : 0.5f);
    }

    // Comprobar respuesta y avanzar
    private void siguientePregunta() {

        if (respuestaSeleccionada == -1) {
            Toast.makeText(
                    this,
                    "Selecciona una respuesta",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        // Comprobar si ha acertado
        if (respuestaSeleccionada ==
                respuestasCorrectas[preguntaActual]) {puntuacion++;}

        preguntaActual++;

        // Comprobar si quedan preguntas
        if (preguntaActual < preguntas.length) {

            mostrarPregunta();

        } else {

            // Terminar el juego
            Intent intent = new Intent(
                    Ventana2.this,
                    Ventana3.class
            );

            intent.putExtra("nombre", nombreJugador);
            intent.putExtra("puntuacion", puntuacion);

            startActivity(intent);
            finish();
        }
    }
}
