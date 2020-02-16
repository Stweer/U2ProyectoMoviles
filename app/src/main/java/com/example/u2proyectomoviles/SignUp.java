package com.example.u2proyectomoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.u2proyectomoviles.Modelo.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtDni,edtNombre,edtContrasenia;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtNombre = (MaterialEditText)findViewById(R.id.edtNombre);
        edtContrasenia = (MaterialEditText)findViewById(R.id.edtContraseña);
        edtDni = (MaterialEditText)findViewById(R.id.edtDni);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        //Inicializacion de Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tabla_usuario = database.getReference("Usuario");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                progressDialog.setMessage("Porfavor espera ....");
                progressDialog.show();

                tabla_usuario.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Verificamops si el DNI existe
                        if (dataSnapshot.child(edtDni.getText().toString()).exists()){
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this, "El Dni ya se encuentra Registrado", Toast.LENGTH_LONG).show();
                        }else{
                         //Si el Dni no existe registramos el Usuario en la base de datos
                            progressDialog.dismiss();
                            Usuario usuario = new Usuario(edtNombre.getText().toString(),edtContrasenia.getText().toString());
                            tabla_usuario.child(edtDni.getText().toString()).setValue(usuario);
                            Toast.makeText(SignUp.this, "Guardado Correctamente", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
