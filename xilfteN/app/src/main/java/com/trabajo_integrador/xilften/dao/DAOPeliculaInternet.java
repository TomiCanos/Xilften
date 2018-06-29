package com.trabajo_integrador.xilften.dao;

import android.util.Log;

import com.trabajo_integrador.xilften.model.Genero;
import com.trabajo_integrador.xilften.model.Pelicula;
import com.trabajo_integrador.xilften.utils.ResultListener;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOPeliculaInternet {

    private static final String LISTA_GENEROS = "generosList";
    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final String API_KEY = "cb805b840f7577048a3a2233f64f24ac";
    private Retrofit retrofit;
    private ServicePelicula service;

    public DAOPeliculaInternet() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();

        service = retrofit.create(ServicePelicula.class);

    }

    public void obtenerPeliculasDeInternetAsincronico(Integer unPage, final ResultListener<List<Pelicula>> escuchadorDelControlador) {

        service.getPeliculasTrending(API_KEY, unPage).enqueue(new Callback<ContenedorDePeliculas>() {
            @Override
            public void onResponse(Call<ContenedorDePeliculas> call, Response<ContenedorDePeliculas> response) {

                ContenedorDePeliculas contenedorDePeliculas = response.body();
                List<Pelicula> listaPeliculas = contenedorDePeliculas.getResults();
                escuchadorDelControlador.finish(listaPeliculas);
            }

            @Override
            public void onFailure(Call<ContenedorDePeliculas> call, Throwable t) {
                Log.e("retrofit", "fallo");
            }
        });

    }

    public void obtenerSeriesDeInternetAsincronico(final ResultListener<List<Pelicula>> escuchadorSeriesDelConstrolador) {
        service.getTVOnTheAir(API_KEY).enqueue(new Callback<ContenedorDePeliculas>() {
            @Override
            public void onResponse(Call<ContenedorDePeliculas> call, Response<ContenedorDePeliculas> response) {
                ContenedorDePeliculas contenedorDeSeries = response.body();
                List<Pelicula> listaSeries = contenedorDeSeries.getResults();
                escuchadorSeriesDelConstrolador.finish(listaSeries);
            }

            @Override
            public void onFailure(Call<ContenedorDePeliculas> call, Throwable t) {
                Log.e("retrofit", "fallo");
            }
        });
    }


    public void obtenerPeliculasPorGeneroAsincronico(Integer unGenero, final ResultListener<List<Pelicula>> escuchadorGenerosDelConstrolador) {
        service.getPeliculasGenero(unGenero, API_KEY).enqueue(new Callback<ContenedorDePeliculas>() {
            @Override
            public void onResponse(Call<ContenedorDePeliculas> call, Response<ContenedorDePeliculas> response) {
                ContenedorDePeliculas contenedorGeneros = response.body();
                List<Pelicula> listaGenero = contenedorGeneros.getResults();
                escuchadorGenerosDelConstrolador.finish(listaGenero);
            }

            @Override
            public void onFailure(Call<ContenedorDePeliculas> call, Throwable t) {
                Log.e("retrofit", "fallo");
            }
        });
    }





   /* public void obtenerListaGenerosAsincronico(final ResultListener<List<Genero>> escuchadorSeriesDelConstrolador) {
        service.getGenreList(API_KEY, "EU").enqueue(new Callback<ContenedorDeGeneros>() {
            @Override
            public void onResponse(Call<ContenedorDeGeneros> call, Response<ContenedorDeGeneros> response) {
                ContenedorDeGeneros contenedorDeGeneros = response.body();
              List<Genero> listaGeneros = contenedorDeGeneros.getResults();
                escuchadorSeriesDelConstrolador.finish(listaGeneros);
            }

            @Override
            public void onFailure(Call<ContenedorDeGeneros> call, Throwable t) {
                Log.e("retrofit", "fallo");
            }
        });
    }*/



}