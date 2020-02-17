package com.example.u2proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.u2proyectomoviles.Interface.ItemClickListener;
import com.example.u2proyectomoviles.Modelo.Servicio;
import com.example.u2proyectomoviles.ServicioViewHolder.ServicioViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ServicioLista extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference servicioslista;

    String categoriaId="";
    FirebaseRecyclerAdapter<Servicio, ServicioViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_lista);

        //FireBase
        database = FirebaseDatabase.getInstance();
        servicioslista = database.getReference("Servicio");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_servicio);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Obtenga Intención aquí
        if (getIntent() != null)
            categoriaId = getIntent().getStringExtra("CategoriaId");
        if (!categoriaId.isEmpty() && categoriaId != null){
            LoadServicioLista(categoriaId);
        }


    }

    private void LoadServicioLista(String categoriaId) {
        //Escogemos layout para enviar nuestro modelo y ViewHolder
        adapter = new FirebaseRecyclerAdapter<Servicio, ServicioViewHolder>(Servicio.class,
                R.layout.servicio_lista_item_card,
                ServicioViewHolder.class,
                //Me gusta: selecciona * de Servicios donde CategoriaId sea ==
                servicioslista.orderByChild("CategoriaId").equalTo(categoriaId)) {
            @Override
            protected void populateViewHolder(ServicioViewHolder servicioViewHolder, Servicio servicio, int i) {
                servicioViewHolder.txtservicio_nombre_persona.setText(servicio.getNombre());
                servicioViewHolder.txtservicio_monto_persona.setText(" $/" + servicio.getPrecio());
                servicioViewHolder.txtservicio_pais_persona.setText(servicio.getPais());
                Picasso.with(getBaseContext()).load(servicio.getImagen()).into(servicioViewHolder.imageView_persona);

                final Servicio local = servicio;
                servicioViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(ServicioLista.this, "" + local.getNombre(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
        };

        //Set Adapter
        Log.d("TAG", ""+ adapter.getItemCount());
        recyclerView.setAdapter(adapter);
    }
}
