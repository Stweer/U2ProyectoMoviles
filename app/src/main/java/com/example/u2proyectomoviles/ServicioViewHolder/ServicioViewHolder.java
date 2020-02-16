package com.example.u2proyectomoviles.ServicioViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.u2proyectomoviles.Interface.ItemClickListener;
import com.example.u2proyectomoviles.R;

public class ServicioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView txtServicioNombre;
    public ImageView imageView;
    private ItemClickListener itemClickListener;

    public ServicioViewHolder(@NonNull View itemView) {
        super(itemView);

        txtServicioNombre = (TextView)itemView.findViewById(R.id.servicio_nombre);
        imageView = (ImageView)itemView.findViewById(R.id.servicio_image);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick( v ,getAdapterPosition(),false);
    }
}
