package com.trabajo_integrador.xilften.controller;

import com.trabajo_integrador.xilften.dao.DAOPeliculaInternet;
import com.trabajo_integrador.xilften.model.Genero;
import com.trabajo_integrador.xilften.model.Pelicula;
import com.trabajo_integrador.xilften.utils.ResultListener;

import java.util.List;

public class PeliculaController {

    public void obtenerPeliculas(Integer unPage, final ResultListener<List<Pelicula>> escuchadorDeLaVista) {

        if (hayInternet()) {

            DAOPeliculaInternet daoPeliculaInternet = new DAOPeliculaInternet();

            daoPeliculaInternet.obtenerPeliculasDeInternetAsincronico(unPage, new ResultListener<List<Pelicula>>() {
                @Override
                public void finish(List<Pelicula> resultado) {
                    escuchadorDeLaVista.finish(resultado);
                }
            });

        }

    }

    public void obtenerSeries(final ResultListener<List<Pelicula>> escuchadorDeLaVistaSeries) {

        if (hayInternet()) {

            DAOPeliculaInternet daoPeliculaInternet = new DAOPeliculaInternet();

            daoPeliculaInternet.obtenerSeriesDeInternetAsincronico(new ResultListener<List<Pelicula>>() {

                @Override
                public void finish(List<Pelicula> resultado) {
                    escuchadorDeLaVistaSeries.finish(resultado);
                }

            });

        }

    }

    public void obtenerPeliculasPorGenero(Integer unGenero, final ResultListener<List<Pelicula>> escuchadorDeLaVistaSeries) {

        if (hayInternet()) {

            DAOPeliculaInternet daoPeliculaPorGeneroInternet = new DAOPeliculaInternet();

            daoPeliculaPorGeneroInternet.obtenerPeliculasPorGeneroAsincronico(unGenero, new ResultListener<List<Pelicula>>() {

                @Override
                public void finish(List<Pelicula> resultado) {
                    escuchadorDeLaVistaSeries.finish(resultado);
                }

            });

        }

    }

   /* public void obtenerListaGeneros( final ResultListener<List<Genero>> escuchadorDeLaVistaSeries) {

        if (hayInternet()) {

            DAOPeliculaInternet daoGenerosInternet = new DAOPeliculaInternet();

            daoGenerosInternet.obtenerListaGenerosAsincronico( new ResultListener<List<Genero>>() {

                @Override
                public void finish(List<Genero> resultado) {
                    escuchadorDeLaVistaSeries.finish(resultado);
                }

            });

        }

    }*/


    public Boolean hayInternet() {
        return true;
    }

}