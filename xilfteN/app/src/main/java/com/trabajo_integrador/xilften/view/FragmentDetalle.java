package com.trabajo_integrador.xilften.view;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.trabajo_integrador.xilften.model.Pelicula;
import com.trabajo_integrador.xilften.R;
import com.varunest.sparkbutton.SparkButton;


public class FragmentDetalle extends Fragment{

    private static final String ID_PELICULA = "ID_PELICULA";

    private FirebaseAuth mAuth;
    private ImageView imagenDetalle;
    private TextView nombreDetalle;
    private TextView descripcionDetalle;
    private SparkButton botonFavoritoFragmentDetalle;
    private Pelicula pelicula;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        nombreDetalle = view.findViewById(R.id.textViewNombreFragmentDetalle);
        descripcionDetalle = view.findViewById(R.id.textViewDescripcionFragmentDetalle);
        imagenDetalle = view.findViewById(R.id.imagenFragmentDetalle);
        botonFavoritoFragmentDetalle = view.findViewById(R.id.imageButtonFavoritoFragmentDetalle);
        mAuth = FirebaseAuth.getInstance();


        Bundle bundle = getArguments();

        pelicula = (Pelicula) bundle.getSerializable(ID_PELICULA);
        pelicula.setEstaEnFavoritos(false);
        if (mAuth.getCurrentUser() == null){pelicula.setEstaEnFavoritos(false);}
        if (pelicula.getEstaEnFavoritos()){botonFavoritoFragmentDetalle.setChecked(true);}
        nombreDetalle.setText(pelicula.getTitle());
        descripcionDetalle.setText(pelicula.getOverview());

        if (pelicula.getBackdrop_path() !=null){
            Picasso.get().load("https://image.tmdb.org/t/p/w780" + pelicula.getBackdrop_path()).into(imagenDetalle);
        }else{
            Picasso.get().load("https://image.tmdb.org/t/p/w780" + pelicula.getPoster_path()).into(imagenDetalle);
        }

         nombreDetalle.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View v) {
                 mAuth.signOut();
                 LoginManager.getInstance().logOut();
                 Toast.makeText(getContext(), "USER DISCONNECTED FROM APPLICATION", Toast.LENGTH_SHORT).show();
                 return false;
             }
         });
        botonFavoritoFragmentDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (mAuth.getCurrentUser() == null){
                   FragmentManager manager = getFragmentManager();
                   MyDialog myDialog = new MyDialog();
                   myDialog.show(manager, "MyDialog");
                   return;
               }else if (!pelicula.getEstaEnFavoritos()) {
                    Toast.makeText(getContext(), "Has agregado " + nombreDetalle.getText().toString() + " a favoritos.", Toast.LENGTH_SHORT).show();
                    botonFavoritoFragmentDetalle.setChecked(true);
                    botonFavoritoFragmentDetalle.setColors(R.color.Blanco, R.color.Rojo );
                    botonFavoritoFragmentDetalle.playAnimation();
                    pelicula.setEstaEnFavoritos(true);
                } else {
                    Toast.makeText(getContext(), "Has eliminado " + nombreDetalle.getText().toString() + " de favoritos.", Toast.LENGTH_SHORT).show();
                    botonFavoritoFragmentDetalle.setChecked(false);
                    botonFavoritoFragmentDetalle.setColors(R.color.Rojo, R.color.Blanco );
                    botonFavoritoFragmentDetalle.playAnimation();
                    pelicula.setEstaEnFavoritos(false);
                }
            }
        });

        return view;
    }


    public static FragmentDetalle fabricaFragmentPelicula(Pelicula unPelicula) {

        FragmentDetalle fragmentDetalle = new FragmentDetalle();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ID_PELICULA, unPelicula);
        fragmentDetalle.setArguments(bundle);
        return fragmentDetalle;
    }



}