package com.trabajo_integrador.xilften.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.trabajo_integrador.xilften.model.Pelicula;
import com.trabajo_integrador.xilften.R;

import java.util.List;

public class DetalleActivity extends AppCompatActivity implements MyDialog.ReceptorClickLoginDialogo {

    public static final String CLAVE_PELICULA = "CLAVE_PELICULA";
    public static final String ID_POSITION = "ID_POSITION";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        ViewPager viewPager = findViewById(R.id.detalle_view_pager);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Integer positionAdapter = bundle.getInt(ID_POSITION);
        List<Pelicula> listaPeliculas = (List<Pelicula>) bundle.getSerializable(CLAVE_PELICULA);

        AdapterViewPager adapterViewPager = new AdapterViewPager(getSupportFragmentManager(), listaPeliculas); //listaRecetas //getSupportFragmentManager(),fragmentColorList

        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(positionAdapter);

        viewPager.setPageTransformer(true, new CubeTransformer());

    }

    @Override
    public void recibirClickLoginDialogo() {
        Intent intent = new Intent(DetalleActivity.this, ActivityLogin.class);
        startActivity(intent);
    }

}