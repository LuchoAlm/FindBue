package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegistrarDatosAdultoMayor extends AppCompatActivity {
    Button registarAM, fechaPicker, btnCancelar;
    ImageButton img;
    EditText nombreCompletoAM, correoAM, direccionDomAM, telefonoMovAM,
             enfermedadesAM, medicamentosAM, personaEncargadaAM,
             descripcionFisicaAM,ubicacionDomAM, latitudAM, longitudAM, metrosPermitidosAM,imag;
    Spinner seleccionarSexo;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("adultosMayores");

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registrar_datos_adulto_mayor);
        SeleccionarFecha();

        //img = (ImageButton) findViewById(R.id.imageButton3);
        registarAM = (Button) findViewById(R.id.button10);
        fechaPicker = (Button) findViewById(R.id.button9);
        nombreCompletoAM = (EditText) findViewById(R.id.editTextTextPersonName16);
        correoAM = (EditText) findViewById(R.id.editTextTextEmailAddress3);
        direccionDomAM = (EditText) findViewById(R.id.editTextTextPersonName17);
        telefonoMovAM = (EditText) findViewById(R.id.editTextPhone3);
        seleccionarSexo = (Spinner) findViewById(R.id.spinner);
        enfermedadesAM = (EditText) findViewById(R.id.editTextTextMultiLine);
        medicamentosAM = (EditText) findViewById(R.id.editTextTextMultiLine2);
        personaEncargadaAM = (EditText) findViewById(R.id.editTextTextPersonName18);
        descripcionFisicaAM = (EditText) findViewById(R.id.editTextTextMultiLine3);
        ubicacionDomAM = (EditText) findViewById(R.id.editTextTextPersonName19);
        latitudAM = (EditText) findViewById(R.id.editTextTextPersonName20);
        longitudAM = (EditText) findViewById(R.id.editTextTextPersonName21);
        metrosPermitidosAM = (EditText) findViewById(R.id.editTextNumber);
        btnCancelar = (Button) findViewById(R.id.button5);

        registarAM.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //Validaciones
               //String imagem = img.get
               String imagen = "https://img.freepik.com/foto-gratis/viejo-mirando-camara_23-2148239005.jpg?size=626&ext=jpg";
               String nombreCompletoBD = nombreCompletoAM.getText().toString();
               String correoBD = correoAM.getText().toString();
               String direccionDomBD = direccionDomAM.getText().toString();
               String telefonoMovBD = telefonoMovAM.getText().toString();
               String fechaNacBD = fechaPicker.getText().toString();
               String sexoBD = seleccionarSexo.getSelectedItem().toString();
               String enfermedadesBD = enfermedadesAM.getText().toString();
               String medicamentosBD = medicamentosAM.getText().toString();
               String personaEncargadaBD = personaEncargadaAM.getText().toString();
               String descripcionFisicaBD = descripcionFisicaAM.getText().toString();
               String ubicacionDomBD = ubicacionDomAM.getText().toString();
               String latitudBD = latitudAM.getText().toString();
               String longitudBD = longitudAM.getText().toString();
               String metrosPermitidosBD = metrosPermitidosAM.getText().toString();

               insertarDatos(imagen, nombreCompletoBD, correoBD, direccionDomBD, telefonoMovBD,
                       fechaNacBD, sexoBD, enfermedadesBD, medicamentosBD, personaEncargadaBD,
                       descripcionFisicaBD, ubicacionDomBD, latitudBD, longitudBD, metrosPermitidosBD);
               limpiarCasillas();
               //goToPrincipalPanel();
           }
       });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegistrarDatosAdultoMayor.this, "Cancelando ...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrarDatosAdultoMayor.this, PanelPrincipalUsuario.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sexo, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seleccionarSexo.setAdapter(adapter);

        seleccionarSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void insertarDatos(String imagen, String nombreCompletoAM, String correoAM, String direccionDomAM,
                               String telefonoMovAM, String fechaNacAM, String sexoAM, String enfermedadesAM,
                               String medicamentosAM, String personaEncargadaAM, String descripcionFisicaAM,
                               String ubicacionDomAM, String latitudAM, String longitudAM, String metrosPermitidosAM) {

        Map<String, Object> map = new HashMap<>();
        map.put("imagen", imagen);
        map.put("nombreCompletoAM", nombreCompletoAM);
        map.put("correoAM", correoAM);
        map.put("direccionDomAM", direccionDomAM);
        map.put("telefonoMovAM", telefonoMovAM);
        map.put("fechaPicker", fechaNacAM);
        map.put("sexoAM", sexoAM);
        map.put("enfermedadesAM", enfermedadesAM);
        map.put("medicamentosAM", medicamentosAM);
        map.put("personaEncargadaAM", personaEncargadaAM);
        map.put("descripcionFisicaAM", descripcionFisicaAM);
        map.put("ubicacionDomAM", ubicacionDomAM);
        map.put("latitudAM", latitudAM);
        map.put("longitudAM", longitudAM);
        map.put("metrosPermitidosAM", metrosPermitidosAM);


        FirebaseDatabase.getInstance().getReference().child("adultosMayores")
                .push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(RegistrarDatosAdultoMayor.this, "Adulto mayor registrado exitosamente!", Toast.LENGTH_SHORT).show();
                        limpiarCasillas();
                        Intent intent = new Intent(RegistrarDatosAdultoMayor.this, PanelPrincipalUsuario.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistrarDatosAdultoMayor.this, "Error de registro de usuario", Toast.LENGTH_SHORT).show();
                    }
                });

    }



    public void limpiarCasillas(){
        nombreCompletoAM.setText("");
        correoAM.setText("");
        direccionDomAM.setText("");
        telefonoMovAM.setText("");
        fechaPicker.setText("");
        enfermedadesAM.setText("");
        medicamentosAM.setText("");
        personaEncargadaAM.setText("");
        descripcionFisicaAM.setText("");
        ubicacionDomAM.setText("");
        latitudAM.setText("");
        longitudAM.setText("");
        medicamentosAM.setText("");

    };

    private String obtenerFechaActual() {
        Calendar calendar = Calendar.getInstance();
        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        mes = mes + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        return  makeDateString(dia, mes, anio);
    }

    public void SeleccionarFecha(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                mes = mes + 1;
                String fecha =  makeDateString(dia, mes, anio);
                fechaPicker.setText(fecha);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, dateSetListener, anio, mes, dia);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int dia, int mes, int anio) {
        return dia + " " + getFormatoMes(mes) + " " + anio;
    }

    private String getFormatoMes(int mes) {
        if(mes == 1)
            return "enero";
        if(mes == 2)
            return "febrero";
        if(mes == 3)
            return "marzo";
        if(mes == 4)
            return "abril";
        if(mes == 5)
            return "mayo";
        if(mes == 6)
            return "junio";
        if(mes == 7)
            return "julio";
        if(mes == 8)
            return "agosto";
        if(mes == 9)
            return "septiembre";
        if(mes == 10)
            return "octubre";
        if(mes == 11)
            return "noviembre";
        if(mes == 12)
            return "diciembre";

        return null;
    }

    public void abrirDatePicker(View view){
        datePickerDialog.show();
    }
}