package com.example.findbue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class AdministrarComoFamiliar extends AppCompatActivity {
    Button editarMiInfoPersonal, editarAM, eliminarAM;
    PanelPrincipalUsuario panelPrincipalUsuario = new PanelPrincipalUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_administrar_familiar);


        editarMiInfoPersonal = (Button) findViewById(R.id.buttonEditarInfoPerson);
        editarAM = (Button) findViewById(R.id.buttonEditarAM);
        eliminarAM = (Button) findViewById(R.id.buttonEliminarAM);

        editarMiInfoPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdministrarComoFamiliar.this, AdministrarComoEncargado.class);
                startActivity(intent);
            }
        });

    }
}