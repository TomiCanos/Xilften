package com.trabajo_integrador.xilften.view;


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


import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.trabajo_integrador.xilften.R;
import com.trabajo_integrador.xilften.model.Serie.Serie;
import com.varunest.sparkbutton.SparkButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleSerie extends Fragment{
    private static final String ID_SERIE = "ID_SERIE";

    private FirebaseAuth mAuth;
    private TextView cantidadSeasons;
    private TextView cantidadEpisodes;
    private ImageView imagenDetalle;
    private TextView nombreDetalle;
    private TextView descripcionDetalle;
    private SparkButton botonFavoritoFragmentDetalle;
    private Serie serie;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_detalle_serie, container, false);

        nombreDetalle = view.findViewById(R.id.textViewNombreFragmentDetalle_Serie);
        descripcionDetalle = view.findViewById(R.id.textViewDescripcionFragmentDetalleSerie);
        imagenDetalle = view.findViewById(R.id.imagenFragmentDetalle_Serie);
        botonFavoritoFragmentDetalle = view.findViewById(R.id.imageButtonFavoritoFragmentDetalle_Serie);
        cantidadEpisodes = view.findViewById(R.id.textViewCantidadEpisodesDetalleSerie);
        cantidadSeasons = view.findViewById(R.id.textViewCantidadSeasonsDetalleSerie);
        mAuth = FirebaseAuth.getInstance();

        Bundle bundle = getArguments();

        serie = (Serie) bundle.getSerializable(ID_SERIE);

        if (mAuth.getCurrentUser() == null){serie.setEstaEnFavoritos(false);}
        if (serie.getEstaEnFavoritos()) {botonFavoritoFragmentDetalle.setChecked(true);}
        nombreDetalle.setText(serie.getOriginal_name());
        descripcionDetalle.setText(serie.getOverview());
        cantidadSeasons.setText("Seasons: " + serie.getNumber_of_seasons().toString());
        cantidadEpisodes.setText("Total episodes: " + serie.getNumber_of_episodes().toString());

        if (serie.getBackdrop_path() !=null){
            Picasso.get().load("https://image.tmdb.org/t/p/w780" + serie.getBackdrop_path()).into(imagenDetalle);
        }else{
            Picasso.get().load("https://image.tmdb.org/t/p/w780" + serie.getPoster_path()).into(imagenDetalle);
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
                }else if (!serie.getEstaEnFavoritos()) {
                    Toast.makeText(getContext(), "Has agregado " + nombreDetalle.getText().toString() + " a favoritos.", Toast.LENGTH_SHORT).show();
                    botonFavoritoFragmentDetalle.setChecked(true);
                    botonFavoritoFragmentDetalle.setColors(R.color.Blanco, R.color.Rojo );
                    botonFavoritoFragmentDetalle.playAnimation();
                    serie.setEstaEnFavoritos(true);
                } else {
                    Toast.makeText(getContext(), "Has eliminado " + nombreDetalle.getText().toString() + " de favoritos.", Toast.LENGTH_SHORT).show();
                    botonFavoritoFragmentDetalle.setChecked(false);
                    botonFavoritoFragmentDetalle.setColors(R.color.Rojo, R.color.Blanco );
                    botonFavoritoFragmentDetalle.playAnimation();
                    serie.setEstaEnFavoritos(false);
                }
            }
        });

        return view;
    }

    public static FragmentDetalleSerie fabricaFragmentSerie(Serie unSerie) {

        FragmentDetalleSerie fragmentDetalle = new FragmentDetalleSerie();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ID_SERIE, unSerie);
        fragmentDetalle.setArguments(bundle);
        return fragmentDetalle;
    }

}
