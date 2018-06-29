package com.trabajo_integrador.xilften.model.Serie;

/**
 * Created by DH on 9/5/2018.
 */

public class Episode {
    private Integer episode_number;
    private String name;
    private String overview;
    private Integer id;

    public Integer getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(Integer episode_number) {
        this.episode_number = episode_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "name='" + name + '\'' +
                '}';
    }
}
