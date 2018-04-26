package com.example.pyaya.xilften.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pyaya.xilften.Controller.AdapterTMDB;
import com.example.pyaya.xilften.Model.BaseDeDatos;
import com.example.pyaya.xilften.Model.Pelicula;
import com.example.pyaya.xilften.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    private AdapterTMDB adapterTreinding;
    private AdapterTMDB adapterSerie;
    private AdapterTMDB adapterReleases;
    private AdapterTMDB adapterNational;
    private AdapterTMDB adapterComedy;
    private AdapterTMDB adapterBecauseYouWatched;
    private Notificable notificable;
    private BaseDeDatos baseDeDatos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        baseDeDatos = new BaseDeDatos();

        RecyclerView recyclerView = view.findViewById(R.id.fragment_home_recycler_trending);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterTreinding = new AdapterTMDB(baseDeDatos.getListaTrending(), new AdapterTMDB.Notificable() {
            @Override
            public void abrirDetalleContactoClickeado(List<Pelicula> listaPelicula, Integer positionPelicula) {
                notificable.abrirDetalleContactoClickeado(listaPelicula, positionPelicula);
            }
        });

        recyclerView.setAdapter(adapterTreinding);

        //Recycler & AdapterPelicula para Trending Now. @Dani

        RecyclerView recyclerViewTrending = view.findViewById(R.id.fragment_home_recycler_series);
        recyclerViewTrending.setHasFixedSize(true);
        recyclerViewTrending.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterSerie = new AdapterTMDB(baseDeDatos.getListaSerie(), new AdapterTMDB.Notificable() {
            @Override
            public void abrirDetalleContactoClickeado(List<Pelicula> listaPelicula, Integer positionPelicula) {
                notificable.abrirDetalleContactoClickeado(listaPelicula, positionPelicula);
            }
        });

        recyclerViewTrending.setAdapter(adapterSerie);
        //////////

        RecyclerView recyclerViewReleases = view.findViewById(R.id.fragment_home_recycler_releases);
        recyclerViewReleases.setHasFixedSize(true);
        recyclerViewReleases.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterReleases = new AdapterTMDB(baseDeDatos.getListaReleases(), new AdapterTMDB.Notificable() {
            @Override
            public void abrirDetalleContactoClickeado(List<Pelicula> listaPelicula, Integer positionPelicula) {
                notificable.abrirDetalleContactoClickeado(listaPelicula, positionPelicula);
            }
        });

        recyclerViewReleases.setAdapter(adapterReleases);
        /////////////////////////

        RecyclerView recyclerViewNationals = view.findViewById(R.id.fragment_home_recycler_national);
        recyclerViewNationals.setHasFixedSize(true);
        recyclerViewNationals.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterNational = new AdapterTMDB(baseDeDatos.getListaNationals(), new AdapterTMDB.Notificable() {
            @Override
            public void abrirDetalleContactoClickeado(List<Pelicula> listaPelicula, Integer positionPelicula) {
                notificable.abrirDetalleContactoClickeado(listaPelicula, positionPelicula);
            }
        });

        recyclerViewNationals.setAdapter(adapterNational);
        /////////////

        RecyclerView recyclerViewComedies = view.findViewById(R.id.fragment_home_recycler_comedy);
        recyclerViewComedies.setHasFixedSize(true);
        recyclerViewComedies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterComedy = new AdapterTMDB(baseDeDatos.getListaComedies(), new AdapterTMDB.Notificable() {
            @Override
            public void abrirDetalleContactoClickeado(List<Pelicula> listaPelicula, Integer positionPelicula) {
                notificable.abrirDetalleContactoClickeado(listaPelicula, positionPelicula);
            }
        });

        recyclerViewComedies.setAdapter(adapterComedy);
        ///////////////

        RecyclerView recyclerViewBecause = view.findViewById(R.id.fragment_home_recycler_saw);
        recyclerViewBecause.setHasFixedSize(true);
        recyclerViewBecause.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterBecauseYouWatched = new AdapterTMDB(baseDeDatos.getListaBecauseYouWatched(), new AdapterTMDB.Notificable() {
            @Override
            public void abrirDetalleContactoClickeado(List<Pelicula> listaPelicula, Integer positionPelicula) {
                notificable.abrirDetalleContactoClickeado(listaPelicula, positionPelicula);
            }
        });

        recyclerViewBecause.setAdapter(adapterBecauseYouWatched);

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificable = (Notificable) getActivity();
    }

    public interface Notificable {
        void abrirDetalleContactoClickeado(List<Pelicula> peliculas, Integer positionPelicula);
    }

}