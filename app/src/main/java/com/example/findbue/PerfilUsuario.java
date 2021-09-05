package com.example.findbue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class PerfilUsuario extends AppCompatActivity {
    TextView nombreUsuario, correoUsuario, direccionUsuario, telefonoMovUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_perfil_usuario);

        correoUsuario = findViewById(R.id.textInputCorreoUsuario);
        direccionUsuario = findViewById(R.id.textInputDirecUsuario);
        telefonoMovUsuario = findViewById(R.id.textInputTelMovUsuario);
        nombreUsuario = findViewById(R.id.textViewNombreUsuario);
        
        mostrarDatosUsuario();
    }

    private void mostrarDatosUsuario() {
        Intent intent = getIntent();
        String nombre_user = intent.getStringExtra("nombreCompleto");
        String correo_user = intent.getStringExtra("correo");
        String direccionDom_user = intent.getStringExtra("direccionDom");
        String telefonoMov_user = intent.getStringExtra("telefonoMov");
        String password_user = intent.getStringExtra("password");

        nombreUsuario.setText(nombre_user);
        correoUsuario.setText(correo_user);
        direccionUsuario.setText(direccionDom_user);
        telefonoMovUsuario.setText(telefonoMov_user);


    }
}