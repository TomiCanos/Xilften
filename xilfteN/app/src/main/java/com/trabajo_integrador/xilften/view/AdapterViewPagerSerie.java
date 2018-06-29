package com.trabajo_integrador.xilften.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.trabajo_integrador.xilften.model.Serie.Serie;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPagerSerie extends FragmentStatePagerAdapter{

    private List<Fragment> listaSeriesFragment;


    public AdapterViewPagerSerie(FragmentManager fm, List<Serie> series) {
        super(fm);
        listaSeriesFragment = new ArrayList<>();

        for (Serie serie : series) {
            FragmentDetalleSerie fragmentDetalle = FragmentDetalleSerie.fabricaFragmentSerie(serie);
            listaSeriesFragment.add(fragmentDetalle);
        }
    }



    @Override
    public Fragment getItem(int position) {
        return listaSeriesFragment.get(position);
    }

    @Override
    public int getCount() {
        return listaSeriesFragment.size();
    }

}
