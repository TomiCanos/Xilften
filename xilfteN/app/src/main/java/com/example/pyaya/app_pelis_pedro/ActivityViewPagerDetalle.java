package com.example.pyaya.app_pelis_pedro;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class ActivityViewPagerDetalle extends AppCompatActivity {
    public static final String CLAVE_LISTA_SERIES = "listaDeSeries";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_detalle);

        Intent intent = getIntent();
        Bundle bundle= intent.getExtras();
        List list = (List) bundle.getSerializable("listaDeSeries");

        ViewPager viewPager = findViewById(R.id.viewPagerDetalle);
        AdapterViewPagerDetalle adapterViewPagerDetalle = new AdapterViewPagerDetalle(getSupportFragmentManager(),list);
        viewPager.setAdapter(adapterViewPagerDetalle);

    }
}
