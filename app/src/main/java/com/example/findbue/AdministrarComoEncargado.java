package com.example.findbue;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AdministrarComoEncargado extends AppCompatActivity {
    EditText correoUsuario, direccionUsuario, telefonoMovUsuario;
    TextView nombreUsuario;
    Button actualizarUsuario;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_administrar_encargado);

        correoUsuario = findViewById(R.id.textView28);
        direccionUsuario = findViewById(R.id.textView29);
        telefonoMovUsuario = findViewById(R.id.textView30);
        nombreUsuario = findViewById(R.id.textView23);
        actualizarUsuario = (Button) findViewById(R.id.button7);
        mostrarDatosUsuario();

        actualizarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String direccionNueva = direccionUsuario.getText().toString();
                String telefonoNuevo = telefonoMovUsuario.getText().toString();
                String correoNuevo = correoUsuario.getText().toString();

                actualizarDatos(direccionNueva, telefonoNuevo, correoNuevo);
            }
        });
    }

    private void actualizarDatos(String direccionNueva, String telefonoNuevo, String correoNuevo) {
        /*String key = mDatabase.child("usuarios").push().getKey();
        Usuario usuario = new Usuario(direccionNueva, telefonoNuevo, correoNuevo);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);*/
    }


    private void mostrarDatosUsuario() {
        Intent intent = getIntent();
        String nombre_user = intent.getStringExtra("nombreCompleto");
        String direccionDom_user = intent.getStringExtra("direccionDom");
        String telefonoMov_user = intent.getStringExtra("telefonoMov");
        String correo_user = intent.getStringExtra("correo");

        nombreUsuario.setText(nombre_user);
        nombreUsuario.setFocusableInTouchMode(true);
        direccionUsuario.setText(direccionDom_user);
        telefonoMovUsuario.setText(telefonoMov_user);
        correoUsuario.setText(correo_user);

    }
}