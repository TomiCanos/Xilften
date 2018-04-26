package com.example.pyaya.xilften.Model;
import com.example.pyaya.xilften.R;

import java.io.Serializable;

public class Pelicula implements Serializable {

    private String nombre;
    private String descripcion;
    private int intImagen;

    public Pelicula(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.intImagen = R.drawable.houseofcards;

    }

    public Pelicula(String nombre, String descripcion, int intImagen) {
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