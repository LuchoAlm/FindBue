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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText correoLogIn, passwordLogIn;
    Button buttonRegistrarme, buttonIngresar, ingresarGoogle;

    private static final int RC_SIGN_IN = 123;
    private GoogleSignInClient mgoogleSignInClient;
    FirebaseDatabase database;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //definir hacia cual interface se ba luego de presionar cada uno de los botones
        buttonIngresar = (Button) findViewById(R.id.buttonIngresar);
        buttonRegistrarme = (Button) findViewById(R.id.buttonRegistrar);
        ingresarGoogle = (Button) findViewById(R.id.buttonGoogle);
        correoLogIn = (EditText) findViewById(R.id.ingresarCorreo);
        passwordLogIn = (EditText) findViewById(R.id.ingresarPwd);

        buttonRegistrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrarUsuario.class);
                startActivity(intent);
            }
        });

        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PanelPrincipalUsuario.class);
                startActivity(intent);
            }
        });

        ingresarGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
                Toast.makeText(MainActivity.this, "Ingresando con Google", Toast.LENGTH_SHORT).show();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        createRequest();
    }
    private void signIn() {
        Intent signInIntent = mgoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately;
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent = new Intent(getApplicationContext(), EditarPerfilPersona.class);
                    startActivity(intent);

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(MainActivity.this, "Sesion no iniciada!!!! Estoy en el else", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



    /*private static final int RC_SIGN_IN = 123;
    private GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase database;
    DatabaseReference myref;
    RegistrarUsuario registrarUsuario = new RegistrarUsuario();

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user!= null){
            Intent intent = new Intent(getApplicationContext(), EditarPerfilPersona.class);
            startActivity(intent);
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

        //definir hacia cual interface se ba luego de presionar cada uno de los botones
        ingresar = (Button) findViewById(R.id.buttonIngresar);
        registrarme = (Button) findViewById(R.id.buttonRegistrar);
        ingresarGoogle = (Button) findViewById(R.id.buttonGoogle);
        correo = (EditText) findViewById(R.id.ingresarCorreo);
        password = (EditText) findViewById(R.id.ingresarPwd);


        registrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrarUsuario.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Estamos ingresando!", Toast.LENGTH_SHORT).show();
            }
        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Validaciones
                if (correo.getText().toString().isEmpty()) {
                    correo.setError("El campo no puede estar vacío");
                } else if (!correo.getText().toString().matches(registrarUsuario.validacionCorreo)) {
                    correo.setError("Dirección de correo inválida");
                } else {
                    correo.setError(null);
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("El campo no puede estar vacío");
                } else{
                    password.setError(null);
                    usuarioRegistrado();
                }
            }
        });


        ingresarGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
                Toast.makeText(MainActivity.this, "Estamos ingresando con Google!", Toast.LENGTH_SHORT).show();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        createRequest();
}

    private void usuarioRegistrado() {
        String usuarioIngresado = correo.getText().toString().trim();
        String passwordIngresado = password.getText().toString().trim();

        DatabaseReference myref = FirebaseDatabase.getInstance().getReference("usuarios");
        Query checkUser = myref.orderByChild("password").equalTo(passwordIngresado);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    correo.setError(null); //Ver que hacr
                    String passwordFromDB = snapshot.child(passwordIngresado).child("password").getValue(String.class);
                    if (passwordFromDB.equals(passwordIngresado)){
                        correo.setError(null);
                        String nombreFromDB = snapshot.child(passwordIngresado).child("nombreCompleto").getValue(String.class);
                        String correoFromDB = snapshot.child(passwordIngresado).child("correo").getValue(String.class);
                        String direccionFromDB = snapshot.child(passwordIngresado).child("direccionDom").getValue(String.class);
                        String telefonoFromDB = snapshot.child(passwordIngresado).child("telefonoMov").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), PerfilUsuario.class); //PerfilUsuario
                        intent.putExtra("nombreCompleto", nombreFromDB);
                        intent.putExtra("correo", correoFromDB);
                        intent.putExtra("direccionDom", direccionFromDB);
                        intent.putExtra("telefonoMov", telefonoFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);
                    }else {
                        password.setError("Contraseña incorrecta");
                        password.requestFocus();
                    }
                }else {
                    System.out.println("NO existe el usuario");
                    correo.setError("No existe el usuario");
                    correo.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void createRequest(){
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately;
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), EditarPerfilPersona.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Sesion no iniciada!!!! Estoy en el else", Toast.LENGTH_SHORT).show();
                            
                        }
                    }
                });
    }*/

    private void createRequest(){
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mgoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
}

