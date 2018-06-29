package com.trabajo_integrador.xilften.model;

/**
 * Created by DH on 24/5/2018.
 */

public class Genero {
    private String genero;
    private int id;

    public Genero(String genero, int id) {
        this.genero = genero;
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public int getId() {
        return id;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setId(int id) {
        this.id = id;
    }
}
