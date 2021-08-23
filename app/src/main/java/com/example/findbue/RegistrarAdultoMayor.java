package com.example.findbue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class RegistrarAdultoMayor extends AppCompatActivity {
    public Button continuar, cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_registrar_adulto_mayor);

        continuar = (Button) findViewById(R.id.buttonRegistrarFam);
        cancelar = (Button) findViewById(R.id.buttonCancelar);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarAdultoMayor.this, RegUbiAdultoMayor.class);
                Toast.makeText(RegistrarAdultoMayor.this, "Vamos a agregar datos de ubicación!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarAdultoMayor.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}