package com.example.u2proyectomoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.u2proyectomoviles.Modelo.Common;
import com.example.u2proyectomoviles.Modelo.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    EditText edtDni,edtContraseña;
    Button btnSignIn;
    TextView txt_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtContraseña = (MaterialEditText)findViewById(R.id.edtContraseña);
        edtDni = (MaterialEditText)findViewById(R.id.edtDni);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);

        txt_s = (TextView)findViewById(R.id.txt_s);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/nabilla.ttf");
        txt_s.setTypeface(face);

        //Inicializacion de Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tabla_usuario = database.getReference("Usuario");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(SignIn.this);
                progressDialog.setMessage("Porfavor espera ....");
                progressDialog.show();

                //Agregrando valor a lista Usuario
                tabla_usuario.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //Comprabando si el usuario con el campo "DNI" existe en la base de datos
                        if (dataSnapshot.child(edtDni.getText().toString()).exists()){

                            //Obtener Informacion del Usuario
                            progressDialog.dismiss();
                            Usuario usuario = dataSnapshot.child(edtDni.getText().toString()).getValue(Usuario.class);
                            if (usuario.getContrasenia().equals(edtContraseña.getText().toString())){
                                Toast.makeText(SignIn.this, "Sing in Exito !", Toast.LENGTH_SHORT).show();


                                Intent intent = new Intent(SignIn.this,Home.class);
                                Common.currentUser = usuario;
                                startActivity(intent);
                                finish();

                            }
                            else {
                                Toast.makeText(SignIn.this, "Contarseña Incorrecta", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {

                            Toast.makeText(SignIn.this, "Usuario no existe en la base de datos", Toast.LENGTH_SHORT).show();
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
