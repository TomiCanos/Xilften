package com.example.pyaya.app_pelis_pedro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class AdapterViewPagerDetalle extends FragmentStatePagerAdapter {
    private List<Fragment> series;

    public AdapterViewPagerDetalle(FragmentManager fm, List<Fragment> series) {
        super(fm);
        this.series = series;
    }

    @Override
    public Fragment getItem(int position) {
        return series.get(position);
    }

    @Override
    public int getCount() {
        return series.size();
    }
}
