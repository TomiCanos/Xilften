package com.trabajo_integrador.xilften.model.Serie;

import java.util.List;

/**
 * Created by DH on 9/5/2018.
 */

public class Season {
    private Integer id;
    private String name;
    private Integer season_number;
    private List<Episode> episodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeason_number() {
        return season_number;
    }

    public void setSeason_number(Integer season_number) {
        this.season_number = season_number;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }


    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", season_number=" + season_number +
                '}';
    }
}
