package com.trabajo_integrador.xilften.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.trabajo_integrador.xilften.R;
import com.trabajo_integrador.xilften.model.Pelicula;

import java.util.List;

public class AdapterTMDB extends RecyclerView.Adapter {

    private List<Pelicula> listaDePeliculas;
    private Notificable notificable;

    public AdapterTMDB(List<Pelicula> listaDePeliculas, Notificable notificable) {
        this.listaDePeliculas = listaDePeliculas;
        this.notificable = notificable;
    }

    //creo otro constructor sin el notificable, asi puedo instanciarlo desde el main
    public AdapterTMDB(List<Pelicula> listaDePeliculas) {
        this.listaDePeliculas = listaDePeliculas;
        this.notificable = notificable;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View viewCelda = layoutInflater.inflate(R.layout.celda_mini_poster, parent, false);
        TMDBViewHolder serieViewHolder = new TMDBViewHolder(viewCelda);

        return serieViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pelicula pelicula = listaDePeliculas.get(position);
        TMDBViewHolder serieViewHolder = (TMDBViewHolder) holder;
        serieViewHolder.cargarSerie(pelicula);
    }

    public void cargarPelicula(List<Pelicula> listaDePeliculasACargar){
        listaDePeliculas.addAll(listaDePeliculasACargar);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaDePeliculas.size();
    }

    private class TMDBViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagen;

        public TMDBViewHolder(View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.celda_mini_poster_image);

            imagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notificable.abrirDetalleContactoClickeado(listaDePeliculas, getAdapterPosition());
                }
            });

        }

        public void cargarSerie(Pelicula pelicula) {
            Picasso.get().load("https://image.tmdb.org/t/p/w300" + pelicula.getPoster_path())
                    .error(R.drawable.place_holder)
                    .into(imagen);
        }

    }

    public interface Notificable {
        void abrirDetalleContactoClickeado(List<Pelicula> listaPelicula, Integer positionPelicula);
    }

}