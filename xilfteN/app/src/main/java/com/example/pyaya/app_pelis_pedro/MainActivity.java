package com.example.pyaya.app_pelis_pedro;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentHome.Notificable2 {

    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    @Override
    public void abrirDetalleContactoClickeado2(List listadeSeries) {
        Intent intent = new Intent(MainActivity.this, ActivityViewPagerDetalle.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable(ActivityViewPagerDetalle.CLAVE_LISTA_SERIES, (Serializable) listadeSeries);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void cargadorDeFragments(Fragment unFragment) {
       /* android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.aca_va_el_fragment,unFragment);
        fragmentTransaction.commit();*/
    }


}