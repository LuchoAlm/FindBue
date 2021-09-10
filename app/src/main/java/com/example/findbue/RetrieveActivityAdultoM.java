package com.example.findbue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RetrieveActivityAdultoM extends AppCompatActivity {
    RecyclerView recyclerView;
    EdicionYEliminacionAdultoM mainAdapterAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retrieve_adulto_m);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<AdultoMayor> options1 =
                new FirebaseRecyclerOptions.Builder<AdultoMayor>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("adultosMayores"), AdultoMayor.class)
                        .build();

        mainAdapterAM = new EdicionYEliminacionAdultoM(options1);
        recyclerView.setAdapter(mainAdapterAM);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapterAM.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapterAM.stopListening();
    }
}