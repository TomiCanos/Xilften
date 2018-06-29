package com.trabajo_integrador.xilften.dao;

import android.util.Log;

import com.trabajo_integrador.xilften.model.Serie.Serie;
import com.trabajo_integrador.xilften.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOSerieInternet {

    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final String API_KEY = "cb805b840f7577048a3a2233f64f24ac";
    private Retrofit retrofit;
    private ServiceSerie service;

    public DAOSerieInternet() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();

        service = retrofit.create(ServiceSerie.class);

    }

    public void obtenerSeriesDeInternetAsincronico(final ResultListener<List<Serie>> escuchadorDelControlador) {
        service.getTVOnTheAir(API_KEY).enqueue(new Callback<ContenedorDeSeries>() {
            @Override
            public void onResponse(Call<ContenedorDeSeries> call, Response<ContenedorDeSeries> response) {

                ContenedorDeSeries contenedorDeSeries = response.body();
                List<Serie> listaSeries = contenedorDeSeries.getResults();
                final List<Serie> list = new ArrayList<>();
               for (Serie serie : listaSeries) {
                    service.getTVByID(serie.getId(), API_KEY). enqueue(new Callback<Serie>() {
                        @Override
                        public void onResponse(Call<Serie> call, Response<Serie> response) {
                            Serie serie1 = response.body();
                            list.add(serie1);
                            escuchadorDelControlador.finish(list);
                        }

                        @Override
                        public void onFailure(Call<Serie> call, Throwable t) {
                            Log.e("retrofit", "fallo");
                        }
                    });
               }
            }

            @Override
            public void onFailure(Call<ContenedorDeSeries> call, Throwable t) {
                Log.e("retrofit", "fallo");
            }
        });
    }
}


