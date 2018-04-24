package com.example.pyaya.app_pelis_pedro;

import java.io.Serializable;

public class Pelicula implements Serializable {

    private String titulo;
    private String bios;
    private Integer imagePelicula;

    public Pelicula(String unTitulo, String unBios, Integer unImagePelicula) {
        titulo = unTitulo;
        bios = unBios;
        imagePelicula = unImagePelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getBios() {
        return bios;
    }

    public void setBios(String bios) {
        this.bios = bios;
    }

    public Integer getImagePelicula() {
        return imagePelicula;
    }

    public void setImagePelicula(Integer imagePelicula) {
        this.imagePelicula = imagePelicula;
    }
}