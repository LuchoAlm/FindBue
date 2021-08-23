package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class PanelPrincipalUsuario extends AppCompatActivity {
    public Button consultarUbicacion, consultarRuta;
    public Switch seleccionRol;
    public TextView textEncargado;
    public LinearLayout encargados;

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

    }
}