package com.example.u2proyectomoviles.Modelo;

public class Usuario {



    private String Nombre;
    private String Contrasenia;

    //Listar
    public Usuario() {
    }

    //EnviarDatos
    public Usuario(String nombre, String contrasenia) {
        Nombre = nombre;
        Contrasenia = contrasenia;
    }


    //Getter and Setter
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        Contrasenia = contrasenia;
    }
}
