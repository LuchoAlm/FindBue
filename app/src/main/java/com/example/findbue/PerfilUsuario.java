package com.example.findbue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class PerfilUsuario extends AppCompatActivity {
    TextInputLayout correoUsuario, direccionUsuario, telefonoMovUsuario;
    TextView nombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_perfil_usuario);

        correoUsuario = (TextInputLayout) findViewById(R.id.textInputCorreoUsuario);
        direccionUsuario = (TextInputLayout) findViewById(R.id.textInputDirecUsuario);
        telefonoMovUsuario = (TextInputLayout) findViewById(R.id.textInputTelMovUsuario);
        nombreUsuario = (TextView) findViewById(R.id.textViewNombreUsuario);
    }
}