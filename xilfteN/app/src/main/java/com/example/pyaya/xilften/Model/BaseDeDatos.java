package com.example.pyaya.xilften.Model;

import com.example.pyaya.xilften.R;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos {
    private List<Pelicula> listaTrending;
    private List<Pelicula> listaSerie;
    private List<Pelicula> listaReleases;
    private List<Pelicula> listaNationals;
    private List<Pelicula> listaComedies;
    private List<Pelicula> listaBecauseYouWatched;


    private List<Pelicula> crearListaTrending() {

        List<Pelicula> lista = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            lista.add(new Pelicula("Forest Gump", "A man with a low IQ has accomplished great things in his life and been present during significant historic events", R.drawable.forrestgump));
            lista.add(new Pelicula("OverBoard", "A spoiled, wealthy yacht owner is thrown overboard and becomes the target of revenge from his mistreated employee. A remake of the 1987 comedy", R.drawable.overboard));
            lista.add(new Pelicula("Horrible Bosses", "For Nick, Kurt and Dale, the only thing that would make the daily grind more tolerable would be to grind their intolerable bosses into dust.", R.drawable.horrible_bosses));
            lista.add(new Pelicula("Just Go With It", "A plastic surgeon, romancing a much younger schoolteacher, enlists his loyal assistant to pretend to be his soon to be ex-wife", R.drawable.just_go_with_it));
        }
        return this.listaTrending = lista;
    }
    private List<Pelicula> crearListaSerie() {

        List<Pelicula> lista = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lista.add(new Pelicula("Deadpool 2", "Deadpool 2", R.drawable.deadpooldos));
            lista.add(new Pelicula("House of Cards", "House of Cards"));
            lista.add(new Pelicula("Bambi", "Bambi", R.drawable.bambi));
            lista.add(new Pelicula("Amadeus", "Amadeus", R.drawable.amadeus));
            lista.add(new Pelicula("Black Panther", "Black Panther", R.drawable.blackpanther));
        }
        return this.listaSerie = lista;
    }
    private List<Pelicula> crearListaReleases() {

        List<Pelicula> lista = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lista.add(new Pelicula("Death Wish", "Death Wish", R.drawable.deathwish));
            lista.add(new Pelicula("En la Sombra", "En la Sombra", R.drawable.enlasombra));
            lista.add(new Pelicula("Flash", "Flash", R.drawable.flash));
            lista.add(new Pelicula("Forrest Gump", "Forrest Gump", R.drawable.forrestgump));
            lista.add(new Pelicula("Jurassic World", "Jurassic World", R.drawable.jurassicworld));
        }
        return this.listaReleases = lista;
    }
    private List<Pelicula> crearListaNationals() {

        List<Pelicula> lista = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lista.add(new Pelicula("Just Go With It", "Just Go With It", R.drawable.just_go_with_it));
            lista.add(new Pelicula("La Boveda", "La Boveda", R.drawable.laboveda));
            lista.add(new Pelicula("La Casa de Papel", "La Casa de Papel", R.drawable.lacasadepapel));
            lista.add(new Pelicula("La Monja", "La Monja", R.drawable.lamonja));
            lista.add(new Pelicula("Locos de Amor 2", "Locos de Amor 2", R.drawable.locosdeamordos));
        }
        return this.listaNationals = lista;
    }
    private List<Pelicula> crearListaComedies() {

        List<Pelicula> lista = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lista.add(new Pelicula("Noche de Juegos", "Noche de Juegos", R.drawable.nochedejuegos));
            lista.add(new Pelicula("Noche de Venganza", "La Boveda", R.drawable.nochedevenganza));
            lista.add(new Pelicula("Ouija 3", "Ouija 3", R.drawable.ouija3));
            lista.add(new Pelicula("Overboard", "Overboard", R.drawable.overboard));
            lista.add(new Pelicula("Perdida", "Perdida", R.drawable.perdida));
        }
        return this.listaComedies = lista;
    }
    private List<Pelicula> crearListaBecauseYouWatched() {

        List<Pelicula> lista = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lista.add(new Pelicula("Proyecto Rampage", "Proyecto Rampage", R.drawable.proyectorampage));
            lista.add(new Pelicula("Suicide Squad 2", "Suicide Squad 2", R.drawable.suicidesquaddos));
            lista.add(new Pelicula("The Party", "The Party", R.drawable.theparty));
            lista.add(new Pelicula("Tomb Raider", "Tomb Raider", R.drawable.tombraider));
            lista.add(new Pelicula("Verdad o Reto", "Verdad o Reto", R.drawable.verdadoreto));
        }
        return this.listaBecauseYouWatched = lista;
    }


    public List<Pelicula> getListaSerie() {

        return crearListaSerie();
    }
    public List<Pelicula> getListaReleases() {

        return crearListaReleases();
    }
    public List<Pelicula> getListaNationals() {

        return crearListaNationals();
    }
    public List<Pelicula> getListaComedies() {

        return crearListaComedies();
    }
    public List<Pelicula> getListaBecauseYouWatched() {

        return crearListaBecauseYouWatched();
    }
    public List<Pelicula> getListaTrending() {

        return crearListaTrending();
    }
}
