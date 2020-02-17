package com.example.u2proyectomoviles.Modelo;

public class Servicio {

    private String Nombre;
    private String Imagen;
    private String Descripcion;
    private String Pais;
    private String Precio;
    private String Discount;
    private String CategoriaId;

    public Servicio() {
    }

    public Servicio(String nombre, String imagen, String descripcion, String pais, String precio, String discount, String categoriaId) {
        Nombre = nombre;
        Imagen = imagen;
        Descripcion = descripcion;
        Pais = pais;
        Precio = precio;
        Discount = discount;
        CategoriaId = categoriaId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getCategoriaId() {
        return CategoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        CategoriaId = categoriaId;
    }
}