package com.trabajo_integrador.xilften.view;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class CubeTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        page.setPivotX(position <= 0 ? page.getWidth() : 0.5f);
        page.setPivotY(page.getHeight() * 0.5f);
        page.setRotationY(90f * position);
    }
}