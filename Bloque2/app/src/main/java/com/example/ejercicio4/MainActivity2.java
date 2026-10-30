package com.example.ejercicio4;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    protectod void onCreate(BundleInstanceState){
        super.onCreate(savedInstance);
        textoNombre = findViewById(R.idtextView3);
        textoNombre = findViewById(R.idtextView4);
    }




    SharedPreferences prefs = getSharedPreferences("Datos", MODE_PRIVATE);
    textoNombre.setText(prefs.getString("nombre", ""));
    textoApellido.setText
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}