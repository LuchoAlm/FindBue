package com.example.findbue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditarAdultoMayor extends AppCompatActivity {
    EditText correoAM, direccionDomAM, telefonoMovAM, enfermedadesAM, medicamentosAM, ubicacionDomAM, latitudAM, longitudAM, metrosPermitidosAM;
    Button actualizarAM, eliminarAM;
    TextView nombreCompletoAM;
    RegistrarDatosAdultoMayor adultoMayor = new RegistrarDatosAdultoMayor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_editar_adulto_mayor);

        actualizarAM = (Button) findViewById(R.id.button2);
        eliminarAM = (Button) findViewById(R.id.button3);
       // nombreCompletoAM = (TextView) findViewById(R.id.textView22) ;
        correoAM = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        direccionDomAM = (EditText) findViewById(R.id.editTextTextPersonName2);
        telefonoMovAM = (EditText) findViewById(R.id.editTextPhone);
        enfermedadesAM = (EditText) findViewById(R.id.editTextTextMultiLine4);
        medicamentosAM = (EditText) findViewById(R.id.editTextTextMultiLine5);
        ubicacionDomAM = (EditText) findViewById(R.id.editTextTextPersonName4);
        latitudAM = (EditText) findViewById(R.id.editTextTextPersonName5);
        longitudAM = (EditText) findViewById(R.id.editTextTextPersonName6);
        metrosPermitidosAM = (EditText) findViewById(R.id.editTextNumber2);


       // mostrarDatosUsuario();

       actualizarAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatos();
            }
       });
    }

    private void actualizarDatos() {
      /*  String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Map<String, Object> map = new HashMap<>();
        map.put("direccionDom", direccionNueva.getText().toString());
        map.put("telefonoMov", telefonoNuevo.getText().toString());
        map.put("correo", actualizarAM.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("usuarios")
                .child(uid).updateChildren(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Actualización exitosa!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "No se pudo realizar la actualización", Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    public void mostrarDatosUsuario() {
        Intent intent = getIntent();
        String nombre_AM = intent.getStringExtra("nombreCompletoAM");
        String correo_AM = intent.getStringExtra("correoAM");
        String direccion_AM = intent.getStringExtra("direccionDomAM");
        String telefonoMov_AM = intent.getStringExtra("telefonoMovAM");

        String enfermedades_AM = intent.getStringExtra("enfermedadesAM");
        String medicamentos_AM = intent.getStringExtra("medicamentosAM");
        String ubicacion_AM = intent.getStringExtra("ubicacionDomAM");
        String latitud_AM = intent.getStringExtra("latitudAM");
        String longitud_AM = intent.getStringExtra("longitudAM");
        String metrosPermitidos_AM = intent.getStringExtra("metrosPermitidosAM");

        nombreCompletoAM.setText(nombre_AM);
        correoAM.setText(correo_AM);
        direccionDomAM.setText(direccion_AM);
        telefonoMovAM.setText(telefonoMov_AM);
        enfermedadesAM.setText(enfermedades_AM);
        medicamentosAM.setText(medicamentos_AM);
        ubicacionDomAM.setText(ubicacion_AM);
        latitudAM.setText(latitud_AM);
        longitudAM.setText(longitud_AM);
        metrosPermitidosAM.setText(metrosPermitidos_AM);


    }
}