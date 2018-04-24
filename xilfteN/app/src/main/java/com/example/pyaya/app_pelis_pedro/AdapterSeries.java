package com.example.pyaya.app_pelis_pedro;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterSeries extends RecyclerView.Adapter  {

    private List<Serie> listaDeSeries;
    private Notificable notificable;

    public AdapterSeries(List<Serie> listaDeSeries,Notificable notificable) {
        this.listaDeSeries = listaDeSeries;
        this.notificable = notificable;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View viewCelda = layoutInflater.inflate(R.layout.celda_serie, parent, false);
        SerieViewHolder serieViewHolder = new SerieViewHolder(viewCelda);

        return  serieViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Serie serie = listaDeSeries.get(position);
        SerieViewHolder serieViewHolder = (SerieViewHolder) holder;
        serieViewHolder.cargarSerie(serie);
    }

    @Override
    public int getItemCount() {
        return listaDeSeries.size();
    }

    private class SerieViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagen;

        public SerieViewHolder(View itemView) {
            super(itemView);

            imagen = (ImageView) itemView.findViewById(R.id.imagenCelda);

            imagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Serie serie = listaDeSeries.get(getAdapterPosition());
                    notificable.abrirDetalleContactoClickeado(listaDeSeries);
                }
            });

        }

        public void cargarSerie(Serie serie){
            imagen.setImageResource(serie.getIntImagen());
        }

    }

    public interface Notificable{
        void abrirDetalleContactoClickeado(List lista);
    }

}