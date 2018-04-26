package com.example.pyaya.xilften.View;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pyaya.xilften.Model.Pelicula;
import com.example.pyaya.xilften.R;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements FragmentHome.Notificable {

    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        FragmentHome fragmentHome = new FragmentHome();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.contenedor_fragment_home, fragmentHome);

        fragmentTransaction.commit();

        //MENU DESPLEGABLE DE COSTADO:
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigationItemAccion:
                        Toast.makeText(MainActivity.this, "ACCION", Toast.LENGTH_SHORT).show();
                        break;

                }

                drawerLayout.closeDrawers();
                return true;
            }
        });

        drawerLayout =  findViewById(R.id.drawerLayout);

    }

    private void cargadorDeFragments(Fragment unFragment) {
       /* android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.aca_va_el_fragment,unFragment);
        fragmentTransaction.commit();*/
    }


    @Override
    public void abrirDetalleContactoClickeado(List<Pelicula> peliculas, Integer positionPelicula) {

        Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalleActivity.CLAVE_PELICULA,(Serializable) peliculas);
        bundle.putInt(DetalleActivity.ID_POSITION, positionPelicula);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}