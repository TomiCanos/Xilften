package com.trabajo_integrador.xilften.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pelicula implements Serializable {

    private Integer id;
    @SerializedName(value = "title", alternate = "original_name")
    private String title;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private Boolean estaEnFavoritos;

    public Pelicula(String title, String overview, String poster_path, String unBackdrop_path) {
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        backdrop_path = unBackdrop_path;
        estaEnFavoritos = false;
    }

    public Boolean getEstaEnFavoritos() {
        return estaEnFavoritos;
    }

    public void setEstaEnFavoritos(Boolean estaEnFavoritos) {
        this.estaEnFavoritos = estaEnFavoritos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "title='" + title + '}';
    }

}