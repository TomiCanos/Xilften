package com.example.pyaya.xilften.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pyaya.xilften.Model.Pelicula;
import com.example.pyaya.xilften.R;


public class FragmentDetalle extends Fragment {

    private static final String ID_PELICULA = "ID_PELICULA";

    private ImageView imagenDetalle;
    private TextView nombreDetalle;
    private TextView descripcionDetalle;
    private ImageButton botonFavoritoFragmentDetalle;
    private boolean noEstaEnFavoritos;
    private Pelicula pelicula;
    //private RecibirPeliculaDesdeElDetalle recibidorPeliculaDesdeElDetalle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        nombreDetalle = view.findViewById(R.id.textViewNombreFragmentDetalle);
        descripcionDetalle = view.findViewById(R.id.textViewDescripcionFragmentDetalle);
        imagenDetalle = view.findViewById(R.id.imagenFragmentDetalle);
        botonFavoritoFragmentDetalle = view.findViewById(R.id.imageButtonFavoritoFragmentDetalle);

        noEstaEnFavoritos = true;

        Bundle bundle = getArguments();

        pelicula = (Pelicula) bundle.getSerializable(ID_PELICULA);

        nombreDetalle.setText(pelicula.getNombre());
        descripcionDetalle.setText(pelicula.getDescripcion());
        imagenDetalle.setImageResource(pelicula.getIntImagen());

        botonFavoritoFragmentDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noEstaEnFavoritos) {

                    botonFavoritoFragmentDetalle.setImageResource(R.drawable.corazonfavoritoclickeado);
                    Toast.makeText(getContext(), "Has agregado " + nombreDetalle.getText().toString() + " a favoritos.", Toast.LENGTH_SHORT).show();
                    noEstaEnFavoritos = false;
                    //recibidorPeliculaDesdeElDetalle.recibirPeliculaDesdeElDetalle(Pelicula);
                } else {
                    botonFavoritoFragmentDetalle.setImageResource(R.drawable.corazonfavorito);
                    Toast.makeText(getContext(), "Has eliminado " + nombreDetalle.getText().toString() + " de favoritos.", Toast.LENGTH_SHORT).show();
                    noEstaEnFavoritos = true;
                   // recibidorPeliculaDesdeElDetalle.recibirPeliculaDesdeElDetalle(Pelicula);
                }
            }
        });

        return view;
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        recibidorPeliculaDesdeElDetalle = (RecibirPeliculaDesdeElDetalle) context;
    }

    public interface RecibirPeliculaDesdeElDetalle{
        void recibirPeliculaDesdeElDetalle(Pelicula Pelicula);
    }*/

    public static FragmentDetalle fabricaFragmentPelicula(Pelicula unPelicula) {

        FragmentDetalle fragmentDetalle = new FragmentDetalle();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ID_PELICULA, unPelicula);
        fragmentDetalle.setArguments(bundle);
        return fragmentDetalle;
    }

}