package com.example.pyaya.app_pelis_pedro;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterPelicula extends RecyclerView.Adapter {

    private List<Pelicula> listaPeliculas;
    private NotificableDelClickRecycler notificableDelClickRecycler;

    public AdapterPelicula(List<Pelicula> peliculas, NotificableDelClickRecycler notif) {
        listaPeliculas = peliculas;
        this.notificableDelClickRecycler = notif;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View celda = layoutInflater.inflate(R.layout.celda_trending, parent, false);
        return new ViewHolderPelicula(celda);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pelicula pelicula = listaPeliculas.get(position);
        ViewHolderPelicula viewHolderPelicula = (ViewHolderPelicula) holder;
        viewHolderPelicula.cargarPelicula(pelicula);
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    private class ViewHolderPelicula extends RecyclerView.ViewHolder {

        private ImageView imageView;


        private ViewHolderPelicula (final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.celda_trending_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pelicula peliculaClickeado = listaPeliculas.get(getAdapterPosition());
                    notificableDelClickRecycler.notificaClick(peliculaClickeado);

                }
            });

        }

        private void cargarPelicula(Pelicula unPelicula) {
            imageView.setImageResource(unPelicula.getImagePelicula());

        }

    }

    public interface NotificableDelClickRecycler{
        void notificaClick(Pelicula pelicula);
    }

}