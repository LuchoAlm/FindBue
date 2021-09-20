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

public class EdicionYEliminacionUsuario extends FirebaseRecyclerAdapter<Usuario, EdicionYEliminacionUsuario.myViewHolder> {//Usuario1
    //String mailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String mailpattern = "^(([^<>()\\[\\]\\\\.,;:\\s@”]+(\\.[^<>()\\[\\]\\\\.,;:\\s@”]+)*)|(“.+”))@((\\[[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}])|(([a-zA-Z\\-0–9]+\\.)+[a-zA-Z]{2,}))$";
    String telfPattern = "^\\d{10}$";
    String metrosPattern = "^[0-9]{1,3}$";
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public EdicionYEliminacionUsuario(@NonNull FirebaseRecyclerOptions<Usuario> options) {//Usuario1
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Usuario model) {//Usuario1
        holder.nombre.setText(model.getNombreCompleto());
        holder.correo.setText(model.getCorreo());
        holder.telefono.setText(model.getTelefonoMov());


        Glide.with(holder.img.getContext())
                .load(model.getImg())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_usuariosdelsistema))
                        .setExpanded(true, 1500)
                        .create();

                View view1 = dialogPlus.getHolderView();
                EditText nombreCompleto, correo, telefonoMov, direccionDom;
                nombreCompleto = view1.findViewById(R.id.editTextTextPersonName7);
                correo = view1.findViewById(R.id.editTextTextEmailAddress5);
                telefonoMov = view1.findViewById(R.id.editTextPhone5);
                direccionDom = view1.findViewById(R.id.editTextTextPersonName8);

                Button btnActualizar = view1.findViewById(R.id.button11);
                Button btnCancelar = view1.findViewById(R.id.button4);

                nombreCompleto.setText(model.getNombreCompleto());
                correo.setText(model.getCorreo());
                telefonoMov.setText(model.getTelefonoMov());
                direccionDom.setText(model.getDireccionDom());

                dialogPlus.show();

                btnActualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("nombreCompleto", nombreCompleto.getText().toString());
                        map.put("correo", correo.getText().toString());
                        map.put("telefonoMov", telefonoMov.getText().toString());
                        map.put("direccionDom", direccionDom.getText().toString());

                        //validaciones
                        if(!validarEmail() | !validarNombre() | !validarDireccion() | !validarTelf()){
                            return;
                        }

                        FirebaseDatabase.getInstance().getReference().child("usuarios")
                                .child(getRef(holder.getAdapterPosition()).getKey()).updateChildren(map)
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

                    private boolean validarEmail() {
                        String mailAM = correo.getText().toString();

                        if(mailAM.isEmpty()){
                            correo.setError("Campo obligatorio");
                            correo.requestFocus();
                            return false;
                        }else if(!mailAM.matches(mailpattern)){
                            correo.setError("Correo incorrecto");
                            correo.requestFocus();
                            return false;
                        }else{
                            correo.setError(null);
                            return true;
                        }
                    }

                    private boolean validarNombre() {
                        String nombreAM = nombreCompleto.getText().toString();
                        if(nombreAM.isEmpty()){
                            nombreCompleto.setError("Campo obligatorio");
                            nombreCompleto.requestFocus();
                            return false;
                        }else{
                            nombreCompleto.setError(null);
                            return true;
                        }
                    }

                    private boolean validarDireccion() {
                        String direccionAM = direccionDom.getText().toString();
                        if(direccionAM.isEmpty()){
                            direccionDom.setError("Campo obligatorio");
                            direccionDom.requestFocus();
                            return false;
                        }else{
                            direccionDom.setError(null);
                            return true;
                        }
                    }

                    private boolean validarTelf() {
                        String telf = telefonoMov.getText().toString();

                        if(telf.isEmpty()){
                            telefonoMov.setError("Campo obligatorio");
                            telefonoMov.requestFocus();
                            return false;
                        }else if(!telf.matches(telfPattern)){
                            telefonoMov.setError("Número no válido");
                            telefonoMov.requestFocus();
                            return false;
                        }else{
                            telefonoMov.setError(null);
                            return true;
                        }
                    }
                });

                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(holder.nombre.getContext(), "Cancelando ...", Toast.LENGTH_SHORT).show();
                        dialogPlus.dismiss();
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
                        FirebaseDatabase.getInstance().getReference().child("usuarios")
                                .child(getRef(holder.getAdapterPosition()).getKey()).removeValue();
                        Toast.makeText(holder.nombre.getContext(), "Usuario eliminado exitosamente!", Toast.LENGTH_SHORT).show();
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
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_user, parent, false);//main_item
        return new myViewHolder(view);
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
