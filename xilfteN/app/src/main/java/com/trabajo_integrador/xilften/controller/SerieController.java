package com.trabajo_integrador.xilften.controller;

import com.trabajo_integrador.xilften.dao.DAOSerieInternet;
import com.trabajo_integrador.xilften.model.Serie.Serie;
import com.trabajo_integrador.xilften.utils.ResultListener;

import java.util.List;

public class SerieController {

    public void obtenerSeries(final ResultListener<List<Serie>> escuchadorDeLaVistaSeries) {

        if (hayInternet()) {

            DAOSerieInternet daoSerieInternet = new DAOSerieInternet();

            daoSerieInternet.obtenerSeriesDeInternetAsincronico(new ResultListener<List<Serie>>() {

                @Override
                public void finish(List<Serie> resultado) {
                    escuchadorDeLaVistaSeries.finish(resultado);
                }

            });

        }

    }

    public Boolean hayInternet() {
        return true;
    }

}
