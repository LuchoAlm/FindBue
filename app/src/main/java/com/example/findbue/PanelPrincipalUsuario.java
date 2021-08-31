package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class PanelPrincipalUsuario extends AppCompatActivity {
    Button consultarUbicacion, consultarRuta;
    ImageButton agregarAM, agregarEncargado, eliminarAM, eliminarEncargado, verPerfilUsuario;
    Switch seleccionRol;
    TextView textEncargado;
    LinearLayout encargados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_panel_principal_usuario);

        seleccionRol = (Switch) findViewById(R.id.switch2);
        textEncargado = (TextView) findViewById(R.id.textViewEncargados);
        encargados = (LinearLayout) findViewById(R.id.linearEncargados);
        agregarAM = (ImageButton) findViewById(R.id.btnAddAdulto);
        agregarEncargado = (ImageButton) findViewById(R.id.btnAddEncargado);
        eliminarAM = (ImageButton) findViewById(R.id.btnRemoveAdulto);
        eliminarEncargado = (ImageButton) findViewById(R.id.btnRemoveEncargado);
        verPerfilUsuario = (ImageButton) findViewById(R.id.imageButtonVerUsuario);

        seleccionRol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (view.getId() == R.id.switch2){
                    if (seleccionRol.isChecked()){
                        textEncargado.setVisibility(View.GONE);
                        encargados.setVisibility(View.GONE);
                    }else{
                        textEncargado.setVisibility(View.VISIBLE);
                        encargados.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        agregarAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanelPrincipalUsuario.this, RegistrarAdultoMayor.class);
                startActivity(intent);
            }
        });

        agregarEncargado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanelPrincipalUsuario.this, AgregarEncargado.class);
                startActivity(intent);
            }
        });

        eliminarEncargado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanelPrincipalUsuario.this, EliminarEncargado.class);
                startActivity(intent);
            }
        });

        verPerfilUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanelPrincipalUsuario.this, PerfilUsuario.class);
                startActivity(intent);
            }
        });

    }
}