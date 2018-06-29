package com.trabajo_integrador.xilften.model.Serie;


import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by DH on 9/5/2018.
 */

public class Serie implements Serializable{
    private Integer id;
    private String original_name;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private Boolean estaEnFavoritos;
    private Integer number_of_episodes;
    private Integer number_of_seasons;
    private List<Season> seasons;

    public Serie(String original_name, String overview, String poster_path, String backdrop_path, Integer number_of_episodes, Integer number_of_seasons/*, List<Season> seasons*/) {

        this.original_name = original_name;
        this.overview = overview;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.number_of_episodes = number_of_episodes;
        this.number_of_seasons = number_of_seasons;
        /*this.seasons = seasons;*/
        estaEnFavoritos = false;
    }

    public Serie() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Boolean getEstaEnFavoritos() {
        return estaEnFavoritos;
    }

    public void setEstaEnFavoritos(Boolean estaEnFavoritos) {
        this.estaEnFavoritos = estaEnFavoritos;
    }

    public Integer getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(Integer number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public Integer getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(Integer number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "original_name='" + original_name + '\'' +
                '}';
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return Objects.equals(original_name, serie.original_name);
    }

}
