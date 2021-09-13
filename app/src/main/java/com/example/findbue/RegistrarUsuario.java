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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrarUsuario extends AppCompatActivity {
    public Button btnRegistrar, btnCancelar;
    public EditText correo, password, nombreCompleto, direccionDom, telefonoMov;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_registrar_usuarios);

        firebaseAuth = FirebaseAuth.getInstance();


        btnRegistrar = (Button) findViewById(R.id.buttonIngresar);
        btnCancelar = (Button) findViewById(R.id.buttonCancelarRegistro);
        correo = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        nombreCompleto = (EditText) findViewById(R.id.editTextTextPersonName);
        direccionDom = (EditText) findViewById(R.id.editTextDireccion);
        telefonoMov = (EditText) findViewById(R.id.editTextPhone2);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarDatos();
            }
        });
       /* btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = correo.getText().toString();
                String pass = password.getText().toString();
                if( email.isEmpty() == false && pass.isEmpty() == false){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                              insertarDatos();
                            }
                        }
                    });
                }
            }
        });*/

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarUsuario.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void insertarDatos() {
        Map<String, Object> map = new HashMap<>();
        map.put("correo", correo.getText().toString());
        map.put("password", password.getText().toString());
        map.put("nombreCompleto", nombreCompleto.getText().toString());
        map.put("direccionDom", direccionDom.getText().toString());
        map.put("telefonoMov", telefonoMov.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("usuarios")
                .push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(RegistrarUsuario.this, "Usuario registrado exitosamente!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrarUsuario.this, PanelPrincipalUsuario.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistrarUsuario.this, "Error de registro de usuario", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}