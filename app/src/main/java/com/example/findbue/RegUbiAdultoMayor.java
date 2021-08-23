package com.example.findbue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class RegUbiAdultoMayor extends AppCompatActivity {
    public Button registrar, cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_reg_ubi_adulto_mayor);

        cancelar = (Button) findViewById(R.id.buttonCancelarAM);
        registrar = (Button) findViewById(R.id.buttonRegistrarAM);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegUbiAdultoMayor.this, PanelPrincipalUsuario.class);
                Toast.makeText(RegUbiAdultoMayor.this, "Registrando adulto mayor!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegUbiAdultoMayor.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}