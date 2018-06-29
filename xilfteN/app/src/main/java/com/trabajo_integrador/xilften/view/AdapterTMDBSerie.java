package com.trabajo_integrador.xilften.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.trabajo_integrador.xilften.R;
import com.trabajo_integrador.xilften.model.Serie.Serie;

import java.util.List;

public class AdapterTMDBSerie extends RecyclerView.Adapter  {

    private List<Serie> listaDeSeries;
    private ReceptorSerie receptorSerie;

    public AdapterTMDBSerie(List<Serie> listaDeSeries, ReceptorSerie receptorSerie) {
        this.listaDeSeries = listaDeSeries;
        this.receptorSerie = receptorSerie;
    }

    //creo otro constructor sin el notificable, asi puedo instanciarlo desde el main
    public AdapterTMDBSerie(List<Serie> listaDeSeries) {
        this.listaDeSeries = listaDeSeries;
        this.receptorSerie = receptorSerie;
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
        Serie serie = listaDeSeries.get(position);
        TMDBViewHolder serieViewHolder = (TMDBViewHolder) holder;
        serieViewHolder.cargarSerie(serie);
    }

    @Override
    public int getItemCount() {
        return listaDeSeries.size();
    }

    private class TMDBViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagen;

        public TMDBViewHolder(View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.celda_mini_poster_image);

            imagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    receptorSerie.abrirDetalleSerieClickeado(listaDeSeries, getAdapterPosition());
                }
            });

        }

        public void cargarSerie(Serie serie) {
            Picasso.get().load("https://image.tmdb.org/t/p/w300" + serie.getPoster_path())
                    .error(R.drawable.place_holder)
                    .into(imagen);
        }

    }

    public interface ReceptorSerie {
        void abrirDetalleSerieClickeado(List<Serie> listaPelicula, Integer positionPelicula);
    }

}
