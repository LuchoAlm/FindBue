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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarUsuario extends AppCompatActivity {
    public Button btnRegistrar, btnCancelar;
    public EditText mail, password, name, location, movil;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;

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
        mail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        name = (EditText) findViewById(R.id.editTextTextPersonName);
        location = (EditText) findViewById(R.id.editTextDireccion);
        movil = (EditText) findViewById(R.id.editTextPhone2);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mail.getText().toString();
                String pass = password.getText().toString();
                if( email.isEmpty() == false && pass.isEmpty() == false){
                    Toast.makeText(RegistrarUsuario.this, "Estoy en el if", Toast.LENGTH_LONG).show();

                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                insertarDatos(mail.getText().toString(),password.getText().toString(), name.getText().toString(), location.getText().toString(), movil.getText().toString());
                                goToPrincipalPanel();
                            }
                        }
                    });
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarUsuario.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void goToPrincipalPanel() {
        Intent i = new Intent(this, PanelPrincipalUsuario.class);
        i.putExtra("mail", mail.getText().toString());
        i.putExtra("password", password.getText().toString());
        i.putExtra("name", name.getText().toString());
        i.putExtra("location", location.getText().toString());
        i.putExtra("movil", movil.getText().toString());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void insertarDatos(String mail, String password, String name, String location, String movil) {
        firebaseDatabase= FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference("usuarios");
        Usuario user = new Usuario(mail, password, name, location, movil);
        ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
        Toast.makeText(this, "Registrado en la BD", Toast.LENGTH_SHORT).show();
    }
}