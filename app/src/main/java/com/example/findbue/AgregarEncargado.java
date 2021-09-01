package com.example.findbue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class AgregarEncargado extends AppCompatActivity {
    Button agregarEncargado, cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_agregar_encargado);

        agregarEncargado = (Button) findViewById(R.id.buttonAgregarEncargado);
        cancelar = (Button) findViewById(R.id.buttonCancelarEncargado);

        agregarEncargado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(AgregarEncargado.this, PanelPrincipalUsuario.class);
                startActivity(intent);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(AgregarEncargado.this, PanelPrincipalUsuario.class);
                startActivity(intent);
            }
        });

    }
}