package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RetrieveActivityEditarMiPerfil extends AppCompatActivity {
    EditText nombreCompleto, correo, telefonoMov, direccionDom;
    Button btnActualizar, btnCancelar;
    Usuario usuario = new Usuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_usuariosdelsistema);

        nombreCompleto = findViewById(R.id.editTextTextPersonName7);
        correo = findViewById(R.id.editTextTextEmailAddress5);
        telefonoMov = findViewById(R.id.editTextPhone5);
        direccionDom = findViewById(R.id.editTextTextPersonName8);
        btnActualizar = findViewById(R.id.button11);
        btnCancelar = findViewById(R.id.button4);

        //mostrarDatos();

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount!=null){
            nombreCompleto.setText(signInAccount.getDisplayName());
            correo.setText(signInAccount.getEmail());
            //telefonoMov.setText(signInAccount);
            //direccionDom.setText(signInAccount.get);
        }

    }

    private void mostrarDatos() {
        Intent intent = getIntent();
        String nombreCompletoView = intent.getStringExtra("nombreCompleto");
        String correoView = intent.getStringExtra("correo");
        String telefonoMovView = intent.getStringExtra("telefonoMov");
        String direccionDomView = intent.getStringExtra("direccionDom");
        //String imgView = intent.getStringExtra("img");

        nombreCompleto.setText(nombreCompletoView);
        correo.setText(correoView);
        telefonoMov.setText(telefonoMovView);
        direccionDom.setText(direccionDomView);
    }
}