package com.trabajo_integrador.xilften.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.trabajo_integrador.xilften.model.Pelicula;
import com.trabajo_integrador.xilften.view.FragmentDetalle;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPager extends FragmentStatePagerAdapter {

    private List<Fragment> listaPeliculasFragment;


    public AdapterViewPager(FragmentManager fm, List<Pelicula> peliculas) {
        super(fm);
        listaPeliculasFragment = new ArrayList<>();

        for (Pelicula pelicula : peliculas) {
            FragmentDetalle fragmentDetalle = FragmentDetalle.fabricaFragmentPelicula(pelicula);
            listaPeliculasFragment.add(fragmentDetalle);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return listaPeliculasFragment.get(position);
    }

    @Override
    public int getCount() {
        return listaPeliculasFragment.size();
    }

}