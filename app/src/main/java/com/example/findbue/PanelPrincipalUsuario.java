package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PanelPrincipalUsuario extends AppCompatActivity {
    public Button consultarUbicacion, consultarRuta;
    public Switch seleccionRol;
    public TextView textEncargado;
    public LinearLayout encargados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_panel_principal_usuario);

        String uid = getIntent().getExtras().getString("uid");
        Toast.makeText(this, "User Uid: "+uid, Toast.LENGTH_SHORT).show();





        seleccionRol = (Switch) findViewById(R.id.switch2);
        textEncargado = (TextView) findViewById(R.id.textViewEncargados);
        encargados = (LinearLayout) findViewById(R.id.linearEncargados);

        seleccionRol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (view.getId() == R.id.switch2){
                    if (seleccionRol.isChecked()){
                        textEncargado.setVisibility(View.GONE);
                        encargados.setVisibility(View.GONE);
                    }else{
                        textEncargado.setVisibility(View.VISIBLE);
                        encargados.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }


}