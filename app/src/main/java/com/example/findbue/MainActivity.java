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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    public Button registrarme, ingresar;
    public ImageButton ingresarGoogle;
    private EditText email, password;
    private FirebaseAuth mAuth;


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!= null){
            goToPrincipalPanel(user.getUid());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        //Instanciamos los elementos de la UI
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.ingresarPwd);
        ingresar = (Button) findViewById(R.id.buttonRegistrarme);
        registrarme = (Button) findViewById(R.id.buttonRegistrar);
        ingresarGoogle = (ImageButton) findViewById(R.id.buttonGoogle);

        //Evento del boton registrarme
        registrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrarUsuario.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Vamos a registrarnos!", Toast.LENGTH_SHORT).show();
            }
        });


        //Evento del boton ingresar
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                //Comprueba si el usuario existe con ese correo y la contraseña
                mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //Si el usuario existe
                            goToPrincipalPanel(mAuth.getUid());
                            Toast.makeText(MainActivity.this, "Estamos ingresando!", Toast.LENGTH_SHORT).show();
                        }else{
                            //Si el usuario no existe, o está mal la contraseña
                            Toast.makeText(MainActivity.this, "Error al iniciar sesión en el sistema", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


}

    //Metodo para enviar la UID de la sesión actual
        //Cuando el usuario ya ha iniciado sesión
    private void goToPrincipalPanel(String uid) {
        Intent i = new Intent(this, PanelPrincipalUsuario.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("uid", uid);
        startActivity(i);
    }

}