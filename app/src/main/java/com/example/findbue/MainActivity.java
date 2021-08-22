package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth miFirebaseAuth;
    FirebaseAuth.AuthStateListener miAuthListener;
    public static final int REQUEST_CODE = 54654;
    public Button registrarme, ingresar;

    List<AuthUI.IdpConfig> provider = Arrays.asList(
            new AuthUI.IdpConfig.GoogleBuilder().build()
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //definir hacia cual interface se ba luego de presionar cada uno de los botones
        ingresar = (Button) findViewById(R.id.button2);
        registrarme = (Button) findViewById(R.id.buttonRegistrar);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PanelPrincipalUsuario.class);
                startActivity(intent);
            }
        });

        registrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrarAdultoMayor.class);
                startActivity(intent);
            }
        });


        //Firebase auth with Google
        miFirebaseAuth = FirebaseAuth.getInstance();
        miAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    Toast.makeText(MainActivity.this, "Sesión iniciada exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                   /* startActivityForResult(AuthUI.getInstance()
                            ,createSignInIntentBuilder()
                            ,setAvailableProviders(provider)
                            ,setIsSmartLockEnabled(false)
                            ,build(), REQUEST_CODE);*/
                    Toast.makeText(MainActivity.this, "Sesion no iniciada!!!! Estoy en el else", Toast.LENGTH_SHORT).show();
                }
            }


        };
    }

    /*protected void onResume(){
        super.onResume();
        miFirebaseAuth.addAuthStateListener(miAuthListener);
    }*/

    @Override
    protected void onPause() {
        super.onPause();
        miFirebaseAuth.removeAuthStateListener(miAuthListener);
    }

    public void cerrarSesion (View view){
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}