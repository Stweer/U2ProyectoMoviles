package com.example.u2proyectomoviles.ServicioViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.u2proyectomoviles.Interface.ItemClickListener;
import com.example.u2proyectomoviles.R;

public class ServicioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView txtservicio_nombre_persona;
    public TextView txtservicio_pais_persona;
    public TextView txtservicio_monto_persona;
    public ImageView imageView_persona;

    String corx;
    String cory;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ServicioViewHolder(@NonNull View itemView) {
        super(itemView);
        txtservicio_nombre_persona = (TextView)itemView.findViewById(R.id.person_name);
        txtservicio_pais_persona = (TextView)itemView.findViewById(R.id.pais);
        txtservicio_monto_persona = (TextView)itemView.findViewById(R.id.costo);
        imageView_persona = (ImageView) itemView.findViewById(R.id.person_photo);





        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(itemView,getAdapterPosition(),false);

    }
}
