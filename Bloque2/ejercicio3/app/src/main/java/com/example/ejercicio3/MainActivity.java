package com.example.ejercicio3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Crear todos los objetos
    private Button botonTlf;
    private Button botonUrl;
    private Button botonMail;

    private EditText textoTelefono;
    private EditText textoUrl;
    private EditText textoMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    botonTlf = findViewById(R.id.botonTlf);
    botonMail = findViewById(R.id.botonMail);
    botonUrl = findViewById(R.id.botonUrl);


    textoTelefono = findViewById(R.id.textoTlf);
    textoMail = findViewById(R.id.textoMail);
    textoUrl = findViewById(R.id.textoUrl);


        botonTlf.setonClickListener(new View.OnClickListener()){
            @Override
                    public void onClick(View view){

                Intent tlfIntent = new Intent(Intent.ACTION_DIAL);
                tlfIntent.setData(Uri.parse("tel: " + textoTelefono.getText()));
                startActivities(tlfIntent);
            }
        }

        botonUrl.setOnClickListener(new View.OnClickListener()){
            @Override
                    public void onClick(View view){

                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                urlIntent.setData(Uri.parse(textoUrl.getText().toString()));
                startActivities(urlIntent);
            }
        }

        botonMail.setOnClickListener(new View.OnClickListener()){
            @Override
            public void onClick(View view){

                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                mailIntent.setData(Uri.parse("mailto: " + textoMail.getText()));
                startActivities(mailIntent);
            }
        }

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}