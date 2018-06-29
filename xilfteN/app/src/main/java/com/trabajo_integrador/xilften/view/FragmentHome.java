package com.trabajo_integrador.xilften.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.trabajo_integrador.xilften.controller.PeliculaController;
import com.trabajo_integrador.xilften.controller.SerieController;
import com.trabajo_integrador.xilften.model.Pelicula;
import com.trabajo_integrador.xilften.R;
import com.trabajo_integrador.xilften.model.Serie.Serie;
import com.trabajo_integrador.xilften.utils.ResultListener;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentHome extends Fragment {

    private static final String ID_PELICULACARROUSEL = "ID_PELICULACARROUSEL";



    private List<Serie> LISTASERIESRESULTADOPRUEBA;
    private List<Pelicula> listaTrendingResultado;
    private List<Pelicula> listaReleasesResultado;
    private List<Pelicula> listaNationalResultado;
    private List<Pelicula> listaComedyResultado;
    private List<Serie> LISTASERIEPRUEBA;
    private List<Pelicula> listaTrending;
    private List<Pelicula> listaReleases;
    private List<Pelicula> listaNational;
    private List<Pelicula> listaComedy;
    private AdapterTMDBSerie ADAPTERSERIEPRUEBA;
    private AdapterViewPagerCarrousel adapterViewPagerCarrousel;
    private AdapterTMDB adapterTreinding;
    private AdapterTMDB adapterReleases;
    private AdapterTMDB adapterNational;
    private AdapterTMDB adapterComedy;
    private ViewPager viewPager;
    private ReceptorSerie receptorSerie;
    private Notificable notificable;
    private RecyclerView recyclerView;


    public FragmentHome() {

        LISTASERIESRESULTADOPRUEBA = new ArrayList<>();
        listaTrendingResultado = new ArrayList<>();
        listaReleasesResultado = new ArrayList<>();
        listaNationalResultado = new ArrayList<>();
        listaComedyResultado = new ArrayList<>();
        LISTASERIEPRUEBA = new ArrayList<>();
        listaTrending = new ArrayList<>();
        listaReleases = new ArrayList<>();
        listaNational = new ArrayList<>();
        listaComedy = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.viewPagerCarrousel);

        crearTrendingLista();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new miTimer(), 9000, 8000);

        recyclerView = view.findViewById(R.id.fragment_home_recycler_trending);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterViewPagerCarrousel = new AdapterViewPagerCarrousel(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPagerCarrousel);
        ViewPageCustom viewPageCustom = (ViewPageCustom) viewPager;
        viewPageCustom.setScrollDurationFactor(20);


        adapterTreinding = new AdapterTMDB(new ArrayList<Pelicula>(), new AdapterTMDB.Notificable() {
            @Override
            public void abrirDetalleContactoClickeado(List<Pelicula> listaPelicula, Integer positionPelicula) {
                notificable.abrirDetalleContactoClickeado(listaPelicula, positionPelicula);
            }
        });

        recyclerView.setAdapter(adapterTreinding);
        /////////////////////////////////////////////////

        RecyclerView recyclerViewSerie = view.findViewById(R.id.fragment_home_recycler_series);
        recyclerViewSerie.setHasFixedSize(true);
        recyclerViewSerie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        ADAPTERSERIEPRUEBA = new AdapterTMDBSerie(CREARLISTASERIEPRUEBA(), new AdapterTMDBSerie.ReceptorSerie() {
            @Override
            public void abrirDetalleSerieClickeado(List<Serie> listaPelicula, Integer positionPelicula) {
                receptorSerie.abrirDetalleSerieClickeado(listaPelicula, positionPelicula);
            }
        });

        recyclerViewSerie.setAdapter(ADAPTERSERIEPRUEBA);
        /////////////////////////////////////////////////

        RecyclerView recyclerViewReleases = view.findViewById(R.id.fragment_home_recycler_releases);
        recyclerViewReleases.setHasFixedSize(true);
        recyclerViewReleases.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterReleases = new AdapterTMDB(crearListaReleases(), new AdapterTMDB.Notificable() {
            @Override
            public void abrirDetalleContactoClickeado(List<Pelicula> listaPelicula, Integer positionPelicula) {
                notificable.abrirDetalleContactoClickeado(listaPelicula, positionPelicula);
            }
        });

        recyclerViewReleases.setAdapter(adapterReleases);
        /////////////////////////////////////////////////

        RecyclerView recyclerViewNationals = view.findViewById(R.id.fragment_home_recycler_national);
        recyclerViewNationals.setHasFixedSize(true);
        recyclerViewNationals.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterNational = new AdapterTMDB(crearListaNationals(), new AdapterTMDB.Notificable() {
            @Override
            public void abrirDetalleContactoClickeado(List<Pelicula> listaPelicula, Integer positionPelicula) {
                notificable.abrirDetalleContactoClickeado(listaPelicula, positionPelicula);
            }
        });

        recyclerViewNationals.setAdapter(adapterNational);
        /////////////////////////////////////////////////

        RecyclerView recyclerViewComedies = view.findViewById(R.id.fragment_home_recycler_comedy);
        recyclerViewComedies.setHasFixedSize(true);
        recyclerViewComedies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterComedy = new AdapterTMDB(crearListaComedies(), new AdapterTMDB.Notificable() {
            @Override
            public void abrirDetalleContactoClickeado(List<Pelicula> listaPelicula, Integer positionPelicula) {
                notificable.abrirDetalleContactoClickeado(listaPelicula, positionPelicula);
            }
        });

        recyclerViewComedies.setAdapter(adapterComedy);
        /////////////////////////////////////////////////

        return view;
    }

    private List<Serie> CREARLISTASERIEPRUEBA() {
        if (LISTASERIEPRUEBA.size() == 0) {
            SerieController serieController = new SerieController();

            serieController.obtenerSeries(new ResultListener<List<Serie>>() {
                @Override
                public void finish(List<Serie> resultado) {
                    LISTASERIESRESULTADOPRUEBA = resultado;

                    for (int i = 0; i < LISTASERIESRESULTADOPRUEBA.size(); i++) {
                        LISTASERIEPRUEBA.add(crearObjetoSerie(LISTASERIESRESULTADOPRUEBA.get(i)));
                    }
                    ADAPTERSERIEPRUEBA.notifyDataSetChanged();
                }
            });

        }
        return LISTASERIEPRUEBA;
    }

    private List<Pelicula> crearTrendingLista() {

        if (listaTrending.size() == 0) {

            PeliculaController peliculaController = new PeliculaController();

            peliculaController.obtenerPeliculas(1, new ResultListener<List<Pelicula>>() {
                @Override
                public void finish(List<Pelicula> resultado) {

                adapterTreinding.cargarPelicula(resultado);
                adapterViewPagerCarrousel.setListaPeliculasFragment(resultado);

                }
            });
        }
            listaTrendingResultado = listaTrending;
        return listaTrending;
    }

    private List<Pelicula> crearListaReleases() {

        if (listaReleases.size() == 0) {

            PeliculaController releasesController = new PeliculaController();

            releasesController.obtenerPeliculas(2, new ResultListener<List<Pelicula>>() {
                @Override
                public void finish(List<Pelicula> resultado) {

                    listaReleasesResultado = resultado;

                    for (int i = 0; i < listaReleasesResultado.size(); i++) {
                        listaReleases.add(crearObjetoPelicula(listaReleasesResultado.get(i)));
                    }

                    adapterReleases.notifyDataSetChanged();

                }
            });

        }

        return listaReleases;
    }

    private List<Pelicula> crearListaNationals() {

        if (listaNational.size() == 0) {

            PeliculaController nationalController = new PeliculaController();

            nationalController.obtenerPeliculas(3, new ResultListener<List<Pelicula>>() {
                @Override
                public void finish(List<Pelicula> resultado) {

                    listaNationalResultado = resultado;

                    for (int i = 0; i < listaNationalResultado.size(); i++) {
                        listaNational.add(crearObjetoPelicula(listaNationalResultado.get(i)));
                    }

                    adapterNational.notifyDataSetChanged();
                }
            });

        }

        return listaNational;
    }

    private List<Pelicula> crearListaComedies() {

        if (listaComedy.size() == 0) {
            PeliculaController comedyController = new PeliculaController();

            comedyController.obtenerPeliculas(4, new ResultListener<List<Pelicula>>() {
                @Override
                public void finish(List<Pelicula> resultado) {
                    listaComedyResultado = resultado;

                    for (int i = 0; i < listaComedyResultado.size(); i++) {
                        listaComedy.add(crearObjetoPelicula(listaComedyResultado.get(i)));
                    }
                    adapterComedy.notifyDataSetChanged();
                }
            });
        }

        return listaComedy;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificable = (Notificable) getActivity();
        receptorSerie = (ReceptorSerie) getActivity();
    }

    public interface ReceptorSerie {
        void abrirDetalleSerieClickeado(List<Serie> series, Integer positionSerie);
    }

    public interface Notificable {
        void abrirDetalleContactoClickeado(List<Pelicula> peliculas, Integer positionPelicula);
    }

    public Pelicula crearObjetoPelicula(Pelicula unPelicula) {

        return new Pelicula(unPelicula.getTitle(), unPelicula.getOverview(), unPelicula.getPoster_path(), unPelicula.getBackdrop_path());

    }

    public Serie crearObjetoSerie(Serie unSerie) {

        return new Serie(unSerie.getOriginal_name(), unSerie.getOverview(), unSerie.getPoster_path(), unSerie.getBackdrop_path(), unSerie.getNumber_of_episodes(), unSerie.getNumber_of_seasons()/*, unSerie.getSeasons()*/);

    }

    public class miTimer extends TimerTask {

        @Override
        public void run() {


            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    }
                    else if (viewPager.getCurrentItem() == 2){
                        viewPager.setCurrentItem(3);
                    }
                    else if (viewPager.getCurrentItem() == 3){
                        viewPager.setCurrentItem(4);
                    }
                    else if (viewPager.getCurrentItem() == 4){
                        viewPager.setCurrentItem(5);
                    }else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });

        }
    }






}