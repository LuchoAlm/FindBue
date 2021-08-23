package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class PanelPrincipalUsuario extends AppCompatActivity {
    public Button consultarUbicacion, consultarRuta;
    public Switch seleccionRol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_panel_principal_usuario);

        seleccionRol = (Switch) findViewById(R.id.switch2);
        seleccionRol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (view.getId() == R.id.switch2){
                    if (seleccionRol.isChecked()){
                        //intent = new Intent(PanelPrincipalUsuario.this, RegistrarAdultoMayor.class);
                        //startActivity(intent);
                        //intent = new Intent (PanelPrincipalUsuario, Encargado);
                        Toast.makeText(PanelPrincipalUsuario.this, "Vamos a asociar un encargado con un AM!", Toast.LENGTH_SHORT).show();
                    }else{
                        intent = new Intent(PanelPrincipalUsuario.this, RegistrarAdultoMayor.class);
                        Toast.makeText(PanelPrincipalUsuario.this, "Vamos a agregar a un AM!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }
            }
        });

    }
}