package com.example.pyaya.app_pelis_pedro;

import java.io.Serializable;

public class Serie implements Serializable {


    private String nombre;
    private String descripcion;
    private int intImagen;

    public Serie(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.intImagen = R.drawable.houseofcards;

    }

    public Serie(String nombre, String descripcion, int intImagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.intImagen = intImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIntImagen() {
        return intImagen;
    }


}
