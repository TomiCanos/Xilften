package com.trabajo_integrador.xilften.view;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.trabajo_integrador.xilften.R;
import com.trabajo_integrador.xilften.model.Pelicula;

//import com.example.pyaya.app_pelis_pedro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCarrousel extends Fragment {


    private static final String ID_PELICULACARROUSEL = "ID_PELICULACARROUSEL";

    private Pelicula pelicula;
    private ImageView imageViewCarrousel;

    public FragmentCarrousel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carrousel, container, false);


        imageViewCarrousel = view.findViewById(R.id.imageViewCarrousel);


        Bundle bundle = getArguments();

        pelicula = (Pelicula) bundle.getSerializable(ID_PELICULACARROUSEL);


        if (pelicula.getBackdrop_path() !=null){
            Picasso.get().load("https://image.tmdb.org/t/p/w780" + pelicula.getBackdrop_path()).into(imageViewCarrousel);
        }else{
            Picasso.get().load("https://image.tmdb.org/t/p/w780" + pelicula.getPoster_path()).into(imageViewCarrousel);
        }


        return view;
    }

    public static FragmentCarrousel fabricaDeSliderCarrousel(Pelicula unPelicula) {

        FragmentCarrousel fragmentCarrousel = new FragmentCarrousel();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ID_PELICULACARROUSEL, unPelicula);
        fragmentCarrousel.setArguments(bundle);
        return fragmentCarrousel;
    }



}
