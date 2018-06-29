package com.trabajo_integrador.xilften.dao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServicePelicula {

    @GET("/sites/MLA/search")
    Call<ContenedorDePeliculas> getPeliculas(@Query("q") String terminoABuscar); //, @Query("api_key") String api_key

    @GET("/3/movie/now_playing")
    Call<ContenedorDePeliculas> getPeliculasTrending(@Query("api_key") String apiKey, @Query("page") Integer unPage);

    @GET("/3/tv/on_the_air")
    Call<ContenedorDePeliculas> getTVOnTheAir(@Query("api_key") String apiKey);

    @GET("/3/genre/{genre_id}/movies")
    Call<ContenedorDePeliculas> getPeliculasGenero(@Path("genre_id") Integer unGenero, @Query("api_key") String apiKey);

    @GET("genre/list")
    Call<ContenedorDeGeneros> getGenreList(@Query("api_key") String apiKey,
                                           @Query("language") String language);

    //el getPeliculas puede ir vacio y termina en search
    //sino el @PATH
    /* @GET("/sites/MLA/search/{category}")
    Call<ContenedorDePeliculas> getPeliculaCategorias(@Path("category") String categoriaABuscar);
    */

}