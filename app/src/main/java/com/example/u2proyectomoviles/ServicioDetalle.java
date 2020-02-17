package com.example.u2proyectomoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.u2proyectomoviles.Modelo.Servicio;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ServicioDetalle extends AppCompatActivity {

    TextView servicio_nombre,servicio_pais,servicio_price,servicio_descripcion,servicio_persona_nombre;
    ImageView servicio_imagen;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnadd;
    ElegantNumberButton numberButton;
    String servicioId="";

    FirebaseDatabase database;
    DatabaseReference servicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_detalle);

        //Firebase
        database = FirebaseDatabase.getInstance();
        servicios = database.getReference("Servicio");
        //Init View
//        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
//        btnadd = (FloatingActionButton)findViewById(R.id.btnadd);

        servicio_descripcion = (TextView)findViewById(R.id.servicio_descripcion);
        servicio_nombre = (TextView)findViewById(R.id.servicio_nombre);
        servicio_price = (TextView)findViewById(R.id.servicio_price);
        servicio_pais = (TextView)findViewById(R.id.servicio_pais);
        servicio_persona_nombre = (TextView)findViewById(R.id.servicio_persona_nombre);

//        servicio_imagen = (ImageView)findViewById(R.id.img_service);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.callpsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CallapsedAppbar);

        //Get Servicio Id from Intent
        if (getIntent() != null)
            servicioId = getIntent().getStringExtra("ServicioId");
        if(!servicioId.isEmpty()){
            getServicioDetalle(servicioId);
        }

    }

    private void getServicioDetalle(String servicioId) {
        servicios.child(servicioId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Servicio servicio = dataSnapshot.getValue(Servicio.class);
                //Set Image
                //Picasso.with(getBaseContext()).load(servicio.getImagen()).into(servicio_imagen);

                collapsingToolbarLayout.setTitle(servicio.getNombre());
                servicio_price.setText(servicio.getPrecio());
                servicio_descripcion.setText(servicio.getDescripcion());
                servicio_pais.setText(servicio.getPais());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
