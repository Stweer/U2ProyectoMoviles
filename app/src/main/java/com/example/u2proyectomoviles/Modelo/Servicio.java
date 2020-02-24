package com.example.u2proyectomoviles.Modelo;

public class Servicio {

    private String Nombre;
    private String Imagen;
    private String Descripcion;
    private String Pais;
    private String Precio;
    private String Discount;
    private Double CoordenadaX;
    private Double CoordenadaY;
    private String Telefono;
    private String Estado;
    private String CategoriaId;


    public Servicio() {
    }

    public Servicio(String nombre, String imagen, String descripcion, String pais, String precio, String discount, Double coordenadaX, Double coordenadaY, String telefono, String estado, String categoriaId) {
        Nombre = nombre;
        Imagen = imagen;
        Descripcion = descripcion;
        Pais = pais;
        Precio = precio;
        Discount = discount;
        CoordenadaX = coordenadaX;
        CoordenadaY = coordenadaY;
        Telefono = telefono;
        Estado = estado;
        CategoriaId = categoriaId;
    }

    //    public Servicio(String nombre, String imagen, String descripcion, String pais, String precio, String discount, String categoriaId) {
//        Nombre = nombre;
//        Imagen = imagen;
//        Descripcion = descripcion;
//        Pais = pais;
//        Precio = precio;
//        Discount = discount;
//        CategoriaId = categoriaId;
//    }

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




    public Double getCoordenadaX() {
        return CoordenadaX;
    }

    public void setCoordenadaX(Double coordenadaX) {
        CoordenadaX = coordenadaX;
    }

    public Double getCoordenadaY() {
        return CoordenadaY;
    }

    public void setCoordenadaY(Double coordenadaY) {
        CoordenadaY = coordenadaY;
    }


    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}