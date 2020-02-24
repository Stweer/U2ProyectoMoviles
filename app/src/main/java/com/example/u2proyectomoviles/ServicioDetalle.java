package com.example.u2proyectomoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.u2proyectomoviles.Modelo.Servicio;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ServicioDetalle extends AppCompatActivity implements OnMapReadyCallback {

    TextView servicio_nombre,servicio_pais,servicio_price,servicio_descripcion,servicio_persona_nombre;
    ImageView servicio_imagen;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnadd;
    ElegantNumberButton numberButton;
    String servicioId="";

    FirebaseDatabase database;
    DatabaseReference servicios;

    TextView coordenadax,coordenaday,telefono,estado;

    public static Double cx;
    public static Double cy;
    private String e;
    private String t;

    //double cx;
    //double cy;
    public LatLng Tacna;
    private DatabaseReference mDatabase;
    Button btnllamar;


    public TextView txtservicio_estado;
    public TextView txtservicio_coordenadax;
    public TextView txtservicio_coordenaday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_servicio_detalle);




        //Firebase
        database = FirebaseDatabase.getInstance();
        servicios = database.getReference("Servicio");
        //Init View
       // btnadd = (FloatingActionButton)findViewById(R.id.btnadd);

        servicio_descripcion = (TextView)findViewById(R.id.servicio_descripcion);
        servicio_nombre = (TextView)findViewById(R.id.servicio_nombre);
        servicio_price = (TextView)findViewById(R.id.servicio_price);
        servicio_pais = (TextView)findViewById(R.id.servicio_pais);
        servicio_persona_nombre = (TextView)findViewById(R.id.servicio_persona_nombre);


        //
        //coordenadax =(TextView)findViewById(R.id.txt_coordenadax);
        //coordenaday =(TextView)findViewById(R.id.txt_coordenaday);
        telefono =(TextView)findViewById(R.id.txt_telefono);
        estado =(TextView)findViewById(R.id.txt_estado);
        //


        btnllamar = (Button)findViewById(R.id.Llamar);

        servicio_imagen = (ImageView)findViewById(R.id.img_service);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.callpsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CallapsedAppbar);





        //Get Servicio Id from Intent
        if (getIntent() != null)
            servicioId = getIntent().getStringExtra("ServicioId");
        if(!servicioId.isEmpty()){
            getServicioDetalle(servicioId);
        }

        // Obtenemos el mapa de forma asíncrona (notificará cuando esté listo)

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {


        double a = -18.011737;
        double b = -70.253529;
        GoogleMap mapa = googleMap;

        LatLng Tacna = new LatLng(a, b); //Nos ubicamos en el centro de TAcna
        mapa.addMarker(new MarkerOptions().position(Tacna).title("Marcador Tacna"));
        mapa.moveCamera(CameraUpdateFactory.newLatLng(Tacna));

    }


    private void getServicioDetalle(String servicioId) {


        servicios.child(servicioId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Servicio servicio = dataSnapshot.getValue(Servicio.class);
                //Set Image
                Picasso.with(getBaseContext()).load(servicio.getImagen()).into(servicio_imagen);

                collapsingToolbarLayout.setTitle(servicio.getNombre());
                servicio_price.setText(servicio.getPrecio());
                servicio_descripcion.setText(servicio.getDescripcion());
                servicio_pais.setText(servicio.getPais());

                telefono.setText(servicio.getTelefono());
                estado.setText(servicio.getEstado());


                t = servicio.getTelefono();



                btnllamar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String numero = "925618029";
                        //String numero = servicio.getTelefono();

                        //Se tiene que activar por el momento manualmente el servicio
                        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + t));
                        if(ActivityCompat.checkSelfPermission(ServicioDetalle.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                            return;
                        startActivity(i);

                    }
                });
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void llamadon(View view) {


    }
}
