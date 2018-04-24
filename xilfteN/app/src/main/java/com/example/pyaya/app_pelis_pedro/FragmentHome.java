package com.example.pyaya.app_pelis_pedro;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements AdapterSeries.Notificable {

    private AdapterSeries adapterSeries;
    private AdapterPelicula adapterPelicula;
    private Notificable2 notificable2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        adapterSeries = new AdapterSeries(crearLista(), this);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFragment1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView.setAdapter(adapterSeries);

        //Recycler & AdapterPelicula para Trending Now. @Dani

        adapterPelicula = new AdapterPelicula(crearTrendingLista(), new AdapterPelicula.NotificableDelClickRecycler() {
            @Override
            public void notificaClick(Pelicula pelicula) {
                Toast.makeText(getActivity(), "CLICN ON TRENDING NOW MOVIE", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerViewTrending = view.findViewById(R.id.fragment_home_trending);
        recyclerViewTrending.setHasFixedSize(true);
        recyclerViewTrending.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerViewTrending.setAdapter(adapterPelicula);
        //
        return view;
    }

    private List<Serie> crearLista() {

        List<Serie> lista = new ArrayList<>();

        lista.add(new Serie("asdas", "slaaaam"));
        lista.add(new Serie("VIadGS", "saasdm"));
        lista.add(new Serie("ASDASD", "saaad"));
        lista.add(new Serie("ADASD", "slaadim"));
        lista.add(new Serie("asdas", "slaaaam"));
        lista.add(new Serie("VIadGS", "saasdm"));
        lista.add(new Serie("ASDASD", "saaad"));
        lista.add(new Serie("ADASD", "slaadim"));
        lista.add(new Serie("asdas", "slaaaam"));
        lista.add(new Serie("VIadGS", "saasdm"));
        lista.add(new Serie("ASDASD", "saaad"));
        lista.add(new Serie("ADASD", "slaadim"));
	lista.add(new Serie("ADASD", "dim"));

        return lista;
    }

    //Agrego la lista de pelis a mostrar en la seccion Trending Now. @Dani
    private List<Pelicula> crearTrendingLista() {

        List<Pelicula> listaTrending = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            listaTrending.add(new Pelicula("Forest Gump", "A man with a low IQ has accomplished great things in his life and been present during significant historic events", R.drawable.forrestgump));
            listaTrending.add(new Pelicula("OverBoard", "A spoiled, wealthy yacht owner is thrown overboard and becomes the target of revenge from his mistreated employee. A remake of the 1987 comedy",R.drawable.overboard));
            listaTrending.add(new Pelicula("Horrible Bosses", "For Nick, Kurt and Dale, the only thing that would make the daily grind more tolerable would be to grind their intolerable bosses into dust.", R.drawable.horrible_bosses));
            listaTrending.add(new Pelicula("Just Go With It", "A plastic surgeon, romancing a much younger schoolteacher, enlists his loyal assistant to pretend to be his soon to be ex-wife",R.drawable.just_go_with_it));
        }

        return listaTrending;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificable2 = (Notificable2) getActivity();
    }

    @Override
    public void abrirDetalleContactoClickeado(List lista) {
        notificable2.abrirDetalleContactoClickeado2(lista);
    }

    public interface Notificable2 {
        void abrirDetalleContactoClickeado2(List listadeSeries);
    }

}