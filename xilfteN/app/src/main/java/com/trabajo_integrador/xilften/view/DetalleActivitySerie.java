package com.trabajo_integrador.xilften.view;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.trabajo_integrador.xilften.R;
import com.trabajo_integrador.xilften.model.Serie.Serie;

import java.util.List;

public class DetalleActivitySerie extends AppCompatActivity implements MyDialog.ReceptorClickLoginDialogo{

    public static final String CLAVE_SERIE = "CLAVE_SERIE";
    public static final String ID_POSITION = "ID_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_serie);




        ViewPager viewPager = findViewById(R.id.detalle_view_pager_serie);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Integer positionAdapter = bundle.getInt(ID_POSITION);
        List<Serie> listaSeries = (List<Serie>) bundle.getSerializable(CLAVE_SERIE);

        AdapterViewPagerSerie adapterViewPagerSerie = new AdapterViewPagerSerie(getSupportFragmentManager(), listaSeries);

        viewPager.setAdapter(adapterViewPagerSerie);
        viewPager.setCurrentItem(positionAdapter);

        viewPager.setPageTransformer(true, new CubeTransformer());
    }

    @Override
    public void recibirClickLoginDialogo() {
        Intent intent = new Intent(DetalleActivitySerie.this, ActivityLogin.class);
        startActivity(intent);
    }
}
