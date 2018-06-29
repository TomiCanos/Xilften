package com.trabajo_integrador.xilften.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.trabajo_integrador.xilften.R;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragmentBuilder;

public class IntroActivity extends MaterialIntroActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.colorPrimaryDark)
                        .buttonsColor(R.color.Gris)
                        .image(R.drawable.fast1)

                        .build(),
                null);

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.colorPrimaryDark)
                        .buttonsColor(R.color.Gris)
                        .image(R.drawable.dynamic1)

                        .build(),
                null);

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.colorPrimaryDark)
                        .buttonsColor(R.color.Gris)
                        .image(R.drawable.search1)

                        .build(),
                null);

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.colorPrimaryDark)
                        .buttonsColor(R.color.Gris)
                        .image(R.drawable.solid1)

                        .build(),
                null);

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.colorPrimaryDark)
                        .buttonsColor(R.color.Gris)
                        .image(R.drawable.sexy1)

                        .build(),
                null);



    }


    @Override
    public void onFinish() {
        super.onFinish();
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
