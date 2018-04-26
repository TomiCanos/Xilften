package com.example.pyaya.xilften.View;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pyaya.xilften.Controller.AdapterViewPager;
import com.example.pyaya.xilften.Model.Pelicula;
import com.example.pyaya.xilften.R;

import java.util.List;
import java.util.Objects;

public class DetalleActivity extends AppCompatActivity {

    public static final String CLAVE_PELICULA = "CLAVE_PELICULA";
    public static final String ID_POSITION = "ID_POSITION";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Objects.requireNonNull(getSupportActionBar()).hide();


        ViewPager viewPager = findViewById(R.id.detalle_view_pager);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Integer positionAdapter = bundle.getInt(ID_POSITION);
        List<Pelicula> listaPeliculas = (List<Pelicula>) bundle.getSerializable(CLAVE_PELICULA);

        AdapterViewPager adapterViewPager = new AdapterViewPager(getSupportFragmentManager(), listaPeliculas); //listaRecetas //getSupportFragmentManager(),fragmentColorList

        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(positionAdapter);

    }

}