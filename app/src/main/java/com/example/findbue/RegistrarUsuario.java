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
    ImageButton img;
    EditText correo, password, nombreCompleto, direccionDom, telefonoMov;

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
                Toast.makeText(RegistrarUsuario.this, "Cancelando ...", Toast.LENGTH_SHORT).show();
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

        if(!validarEmail() | !validarContrasenia() | !validarNombre() | !validarDireccion() |
        !validarTelf()){
            return;
        }

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

    public Boolean validarEmail(){
        String mail = correo.getText().toString();
        String mailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(mail.isEmpty()){
            correo.setError("Campo obligatorio");
            correo.requestFocus();
            return false;
        }else if(!mail.matches(mailpattern)){
            correo.setError("Correo incorrecto");
            correo.requestFocus();
            return false;
        }else{
            correo.setError(null);
            return true;
        }
    }

    public Boolean validarContrasenia(){
        String contra = password.getText().toString();
        String contraPattern = "^" +
                //"(?=.*[0-9])" +       //al menos un número
                //"(?=.*[a-z])" +       //al menos 1 minúscula
                //"(?=.*[A-Z])" +       //al menos 1 mayúscula
                "(?=.*[a-zA-Z])" +      //cualquier letra
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{4,}" +
                "$";
        if(contra.isEmpty()){
            password.setError("Campo obligatorio");
            password.requestFocus();
            return false;
        }else if(!contra.matches(contraPattern)){
            password.setError("Contraseña muy débil, debe contener al menos" +
                    "un número, una mayúscula, una minúscula, y un caracter especial");
            return false;
        }else{
            password.setError(null);
            return true;
        }
    }

    public Boolean validarNombre(){
        String nombre = nombreCompleto.getText().toString();
        if(nombre.isEmpty()){
            nombreCompleto.setError("Campo obligatorio");
            nombreCompleto.requestFocus();
            return false;
        }else{
            nombreCompleto.setError(null);
            return true;
        }
    }

    public Boolean validarDireccion(){
        String direccion = direccionDom.getText().toString();
        if(direccion.isEmpty()){
            direccionDom.setError("Campo obligatorio");
            direccionDom.requestFocus();
            return false;
        }else{
            direccionDom.setError(null);
            return true;
        }
    }

    public Boolean validarTelf(){
        String telf = telefonoMov.getText().toString();
        String telfPattern = "^\\d{10}$";
        if(telf.isEmpty()){
            telefonoMov.setError("Campo obligatorio");
            telefonoMov.requestFocus();
            return false;
        }else if(!telf.matches(telfPattern)){
            telefonoMov.setError("Número no válido");
            telefonoMov.requestFocus();
            return false;
        }else{
            telefonoMov.setError(null);
            return true;
        }
    }
}