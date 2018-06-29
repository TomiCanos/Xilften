package com.trabajo_integrador.xilften.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trabajo_integrador.xilften.R;
import com.trabajo_integrador.xilften.model.Pelicula;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSearch extends Fragment {

    private AdapterTMDB adapterTMDB;
    private NotificableSearch notificable;
    private List<Pelicula> listaAventura;


    public FragmentSearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerFragmentSearch);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));


        adapterTMDB = new AdapterTMDB(listaAventura, new AdapterTMDB.Notificable() {
            @Override
            public void abrirDetalleContactoClickeado(List<Pelicula> listaPelicula, Integer positionPelicula) {
                notificable.abrirDetalleContactoClickeado(listaPelicula, positionPelicula);
            }
        });

        recyclerView.setAdapter(adapterTMDB);



        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificable = (FragmentSearch.NotificableSearch) context;
    }

    public interface NotificableSearch {
        void abrirDetalleContactoClickeado(List<Pelicula> peliculas, Integer positionPelicula);
    }


}
