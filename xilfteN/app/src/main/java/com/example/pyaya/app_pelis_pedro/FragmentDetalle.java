package com.example.pyaya.app_pelis_pedro;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentDetalle extends Fragment {

    private ImageView imagenDetalle;
    private TextView nombreDetalle;
    private TextView descripcionDetalle;
    private ImageButton botonFavoritoFragmentDetalle;
    private boolean noEstaEnFavoritos;
    private RecibirSerieDesdeElDetalle recibidorSerieDesdeElDetalle;

    public static final String CLAVE_SERIE_DETALLE = "contacto";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        noEstaEnFavoritos = true;
        View view = inflater.inflate(R.layout.fragment_fragment_detalle, container, false);
        Bundle elBundleQueMeMandoLaActivity = getArguments();
        final Serie serie = (Serie) elBundleQueMeMandoLaActivity.getSerializable(CLAVE_SERIE_DETALLE);

        nombreDetalle = view.findViewById(R.id.textViewNombreFragmentDetalle);
        descripcionDetalle = view.findViewById(R.id.textViewDescripcionFragmentDetalle);
        imagenDetalle = view.findViewById(R.id.imagenFragmentDetalle);
        botonFavoritoFragmentDetalle = view.findViewById(R.id.imageButtonFavoritoFragmentDetalle);

        nombreDetalle.setText(serie.getNombre());
        descripcionDetalle.setText(serie.getDescripcion());
        imagenDetalle.setImageResource(serie.getIntImagen());

        botonFavoritoFragmentDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noEstaEnFavoritos) {

                    botonFavoritoFragmentDetalle.setImageResource(R.drawable.corazonfavoritoclickeado);
                    Toast.makeText(getContext(), "Has agregado " + nombreDetalle.getText().toString() + " a favoritos.", Toast.LENGTH_SHORT).show();
                    noEstaEnFavoritos = false;
                    recibidorSerieDesdeElDetalle.recibirSerieDesdeElDetalle(serie);
                } else {
                    botonFavoritoFragmentDetalle.setImageResource(R.drawable.corazonfavorito);
                    Toast.makeText(getContext(), "Has eliminado " + nombreDetalle.getText().toString() + " de favoritos.", Toast.LENGTH_SHORT).show();
                    noEstaEnFavoritos = true;
                    recibidorSerieDesdeElDetalle.recibirSerieDesdeElDetalle(serie);
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        recibidorSerieDesdeElDetalle = (RecibirSerieDesdeElDetalle) context;
    }

    public interface RecibirSerieDesdeElDetalle{
        public void recibirSerieDesdeElDetalle(Serie serie);
    }
}
