package com.example.findbue;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EdicionYEliminacionAdultoM extends FirebaseRecyclerAdapter<AdultoMayor, EdicionYEliminacionAdultoM.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options1
     */
    public EdicionYEliminacionAdultoM(@NonNull FirebaseRecyclerOptions<AdultoMayor> options1) {
        super(options1);
    }

        @Override
        protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull AdultoMayor model) {//Usuario1
            holder.nombre.setText(model.getNombreCompletoAM());
            holder.correo.setText(model.getCorreoAM());
            holder.telefono.setText(model.getTelefonoMovAM());

            Glide.with(holder.img.getContext())
                    .load(model.getImg())
                    .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                    .circleCrop()
                    .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                    .into(holder.img);
            holder.btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final DialogPlus dialogPlus = DialogPlus.newDialog(holder.nombre.getContext())
                            .setContentHolder(new ViewHolder(R.layout.activity_editar_adulto_mayor))
                            .setExpanded(true, 1720)
                            .create();

                    View view1 = dialogPlus.getHolderView();
                    EditText nombreCompletoAM, correoAM, direccionDomAM, telefonoMovAM,
                             enfermedadesAM, medicamentosAM, ubicacionDomAM, latitudAM,
                             longitudAM, metrosPermitidosAM;
                    nombreCompletoAM = view1.findViewById(R.id.editTextTextPersonName9);
                    correoAM = view1.findViewById(R.id.editTextTextEmailAddress2);
                    direccionDomAM = view1.findViewById(R.id.editTextTextPersonName2);
                    telefonoMovAM = view1.findViewById(R.id.editTextPhone);
                    enfermedadesAM = view1.findViewById(R.id.editTextTextMultiLine4);
                    medicamentosAM = view1.findViewById(R.id.editTextTextMultiLine5);
                    ubicacionDomAM = view1.findViewById(R.id.editTextTextPersonName4);
                    latitudAM = view1.findViewById(R.id.editTextTextPersonName5);
                    longitudAM = view1.findViewById(R.id.editTextTextPersonName6);
                    metrosPermitidosAM = view1.findViewById(R.id.editTextNumber2);

                    Button btnActualizar = view1.findViewById(R.id.button2);

                    nombreCompletoAM.setText(model.getNombreCompletoAM());
                    correoAM.setText(model.getCorreoAM());
                    direccionDomAM.setText(model.getCorreoAM());
                    telefonoMovAM.setText(model.getTelefonoMovAM());
                    enfermedadesAM.setText(model.getEnfermedadesAM());
                    medicamentosAM.setText(model.getMedicamentosAM());
                    ubicacionDomAM.setText(model.getUbicacionDomAM());
                    latitudAM.setText(model.getLatitudAM());
                    longitudAM.setText(model.getLongitudAM());
                    metrosPermitidosAM.setText(model.getMetrosPermitidosAM());

                    dialogPlus.show();

                    btnActualizar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Map<String,Object> map = new HashMap<>();
                            map.put("nombreCompletoAM", nombreCompletoAM.getText().toString());
                            map.put("correoAM", correoAM.getText().toString());
                            map.put("direccionDomAM", direccionDomAM.getText().toString());
                            map.put("telefonoMovAM", telefonoMovAM.getText().toString());
                            map.put("enfermedadesAM", enfermedadesAM.getText().toString());
                            map.put("medicamentosAM", medicamentosAM.getText().toString());
                            map.put("ubicacionDomAM", ubicacionDomAM.getText().toString());
                            map.put("latitudAM", latitudAM.getText().toString());
                            map.put("longitudAM", longitudAM.getText().toString());
                            map.put("metrosPermitidosAM", metrosPermitidosAM.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child("adultosMayores")
                                    .child(getRef(position).getKey()).updateChildren(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(holder.nombre.getContext(), "Datos actualizados exitosamente!", Toast.LENGTH_SHORT).show();
                                            dialogPlus.dismiss();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(holder.nombre.getContext(), "Los datos no se pudieron actualizar", Toast.LENGTH_SHORT).show();
                                            dialogPlus.dismiss();
                                        }
                                    });
                        }
                    });
                }
            });

            holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.nombre.getContext());
                    builder.setTitle("¿Está seguro?");
                    builder.setMessage("Una vez eliminado no puede deshacer los cambios");

                    builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FirebaseDatabase.getInstance().getReference().child("adultosMayores")
                                    .child(getRef(position).getKey()).removeValue();
                            Toast.makeText(holder.nombre.getContext(), "Adulto mayor eliminado exitosamente!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }
            });
        }

    @NonNull
    @Override
    public EdicionYEliminacionAdultoM.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_user, parent, false);//main_item
        return new EdicionYEliminacionAdultoM.myViewHolder(view);
    }

     class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView nombre, correo, telefono;
        Button btnEditar, btnEliminar;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img2);
            nombre = (TextView) itemView.findViewById(R.id.nombreUser);
            correo = (TextView) itemView.findViewById(R.id.correoUser);
            telefono = (TextView) itemView.findViewById(R.id.telefonoUser);
            btnEditar = (Button) itemView.findViewById(R.id.btnEditar);
            btnEliminar = (Button) itemView.findViewById(R.id.btnEliminar);


        }
    }
}
