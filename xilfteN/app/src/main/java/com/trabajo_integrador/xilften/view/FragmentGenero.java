package com.trabajo_integrador.xilften.view;

import android.content.Context;
import android.location.GnssMeasurementsEvent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trabajo_integrador.xilften.controller.PeliculaController;
import com.trabajo_integrador.xilften.model.Genero;
import com.trabajo_integrador.xilften.model.Pelicula;
import com.trabajo_integrador.xilften.R;
import com.trabajo_integrador.xilften.utils.ResultListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FragmentGenero extends Fragment {

    private static final String ID_GENERO = "ID_GENERO";
    private static final String ID_NOMBRE_GENERO = "ID_NOMBRE_GENERO";

    private AdapterTMDB adapterTMDB;
    private NotificableGenero notificable;
    private TextView textViewGenero;
    private List<Pelicula> listaGeneroResultado;
    private List<Pelicula> listaGenero;
    private List<Pelicula> listaAccion;
    private List<Pelicula> listaAventura;
    private List<Pelicula> listaAnimacion;
    private List<Pelicula> listaComedia;
    private List<Pelicula> listaCienciaFiccion;
    private Integer mostrarGeneroElegido;

    public FragmentGenero() {
        listaGeneroResultado = new ArrayList<>();
        listaGenero = new ArrayList<>();
        listaAccion = new ArrayList<>();
        listaAventura = new ArrayList<>();
        listaAnimacion = new ArrayList<>();
        listaComedia = new ArrayList<>();
        listaCienciaFiccion = new ArrayList<>();
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_genero, container, false);
        textViewGenero = view.findViewById(R.id.fragment_gener_titulo);

        Bundle bundle = getArguments();
        mostrarGeneroElegido = bundle.getInt(ID_GENERO);

        //textViewGenero.setText(bundle.getString(ID_NOMBRE_GENERO));

        // CONSIGO LOS PARAMETROS
        //List<Pelicula> listaGenero = (List<Pelicula>) bundle.getSerializable(ID_GENERO);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerFragmentGenerosNavigationView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        adapterTMDB = new AdapterTMDB(crearListaGenero(mostrarGeneroElegido), new AdapterTMDB.Notificable() {
            @Override
            public void abrirDetalleContactoClickeado(List<Pelicula> listaPelicula, Integer positionPelicula) {
                notificable.abrirDetalleContactoClickeado(listaPelicula, positionPelicula);
            }
        });

        recyclerView.setAdapter(adapterTMDB);

        return view;
    }

    private List<Pelicula> crearListaGenero(Integer unGenero) {

        switch (unGenero) {
            case 28:
                textViewGenero.setText("Accion");
                if (listaAccion.size() == 0) {
                    return llamarApiPorGenero(unGenero);
                } else {
                    return listaAccion;
                }
            case 12:
                textViewGenero.setText("Aventura");
                if (listaAventura.size() == 0) {
                    return llamarApiPorGenero(unGenero);
                } else {
                    return listaAventura;
                }
            case 16:
                textViewGenero.setText("Animacion");
                if (listaAnimacion.size() == 0) {
                    return llamarApiPorGenero(unGenero);
                } else {
                    return listaAnimacion;
                }
            case 35:
                textViewGenero.setText("Comedia");
                if (listaComedia.size() == 0) {
                    return llamarApiPorGenero(unGenero);
                } else {
                    return listaComedia;
                }

            case 14:
                textViewGenero.setText("Ciencia Ficcion");
                if (listaCienciaFiccion.size() == 0) {
                    return llamarApiPorGenero(unGenero);
                } else {
                    return listaCienciaFiccion;
                }
        }

        return null;
    }

    public List<Pelicula> llamarApiPorGenero(Integer unGenero) {

        listaGenero.clear();

        PeliculaController generoController = new PeliculaController();

        generoController.obtenerPeliculasPorGenero(unGenero, new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {

                listaGeneroResultado = resultado;

                for (int i = 0; i < listaGeneroResultado.size(); i++) {

                    listaGenero.add(crearObjetoPelicula(listaGeneroResultado.get(i)));

                }
                adapterTMDB.notifyDataSetChanged();
            }
        });

        return listaGenero;
    }




    public Pelicula crearObjetoPelicula(Pelicula unPelicula) {

        return new Pelicula(unPelicula.getTitle(), unPelicula.getOverview(), unPelicula.getPoster_path(), unPelicula.getBackdrop_path());

    }




    public static FragmentGenero fabricaFragmentGenero(Integer unGenero) {
        FragmentGenero fragmentGenero = new FragmentGenero();
        Bundle bundle = new Bundle();
        //bundle.putString(ID_NOMBRE_GENERO, unGenero);
        bundle.putInt(ID_GENERO, unGenero);
        //bundle.putSerializable(ID_GENERO, (Serializable) unListGeneroPelicula);
        fragmentGenero.setArguments(bundle);
        return fragmentGenero;

        /*switch (unGenero) {
            case "Accion":
                bundle.putSerializable(ID_GENERO, (Serializable) crearListaGenero(28));
                fragmentGenero.setArguments(bundle);
                return fragmentGenero;
            case "Aventura":
                bundle.putSerializable(ID_GENERO, (Serializable) crearListaGenero(12));
                fragmentGenero.setArguments(bundle);
                return fragmentGenero;
            case "Animacion":
                bundle.putSerializable(ID_GENERO, (Serializable) crearListaGenero(16));
                fragmentGenero.setArguments(bundle);
                return fragmentGenero;
            case "Comedia":
                bundle.putSerializable(ID_GENERO, (Serializable) crearListaGenero(35));
                fragmentGenero.setArguments(bundle);
                return fragmentGenero;
            case "ScienceFiction":
                bundle.putSerializable(ID_GENERO, (Serializable) crearListaGenero(14));
                fragmentGenero.setArguments(bundle);
                return fragmentGenero;
        }

        return null;*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificable = (NotificableGenero) context;
    }

    public interface NotificableGenero {
        void abrirDetalleContactoClickeado(List<Pelicula> peliculas, Integer positionPelicula);
    }

}