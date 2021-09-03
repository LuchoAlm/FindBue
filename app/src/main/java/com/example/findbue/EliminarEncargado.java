package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;

import java.util.StringTokenizer;

public class EliminarEncargado extends AppCompatActivity {
    String validacionCorreo = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Button eliminarEncar;
    EditText correoEncargado, correoAM;
    FirebaseUser auth;
    StringTokenizer tokenizer;
    //private static final String TAG = "PanelPrincipalUsuario";
    private static final String TAG = "EliminarEncargado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_eliminar_encargado);

       eliminarEncar = (Button) findViewById(R.id.buttonEliminarEnc);
       correoEncargado = (EditText) findViewById(R.id.editTextCorreoEnc);
       correoAM = (EditText) findViewById(R.id.editTextCorreoAM);

       eliminarEncar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //Validaciones
               if (correoEncargado.getText().toString().isEmpty()) {
                   correoEncargado.setError("El campo no puede estar vacío");
               } else if (!correoEncargado.getText().toString().matches(validacionCorreo)) {
                   correoEncargado.setError("Dirección de correo inválida");
               } else {
                   correoEncargado.setError(null);
               }
               if (correoAM.getText().toString().isEmpty()) {
                   correoAM.setError("El campo no puede estar vacío");
               } else if (!correoAM.getText().toString().matches(validacionCorreo)) {
                   correoAM.setError("Dirección de correo inválida");
               } else {
                   correoAM.setError(null);
                   verificarUsuario();
               }
           }
       });
    }

    private void verificarUsuario() {
        String correoEncIngresado = correoEncargado.getText().toString().trim();
        String correoAMIngresado = correoAM.getText().toString().trim();

        DatabaseReference myref = FirebaseDatabase.getInstance().getReference("encargados");
        Query checkUser = myref.orderByChild("correoEnc").equalTo(correoEncIngresado);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String datos = snapshot.child("").getValue().toString();
                //datos = tokenizer(datos, "{", 0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    /*    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "User acount deleted");
                }
            }
        });*/

        /*String correoEncIngresado = correoEncargado.getText().toString().trim();
        String correoAMIngresado = correoAM.getText().toString().trim();

        /*DatabaseReference myref = FirebaseDatabase.getInstance().getReference("encargados");
        Query checkUser = myref.orderByChild("").equalTo(correoEncIngresado);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String correoEncFromDB = snapshot.child(correoEncIngresado).child("correoEnc").getValue(String.class);
                    String correoAMFromDB = snapshot.child(correoEncIngresado).child("correoAdultoM").getValue(String.class);
                    if (correoEncFromDB.equals(correoEncIngresado) && correoAMFromDB.equals(correoAMFromDB)){
                        Toast.makeText(EliminarEncargado.this, "El encargado ha sido eliminado exitosamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        }};
