package com.trabajo_integrador.xilften.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.trabajo_integrador.xilften.R;

import static com.facebook.login.widget.ProfilePictureView.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialog extends DialogFragment{
    private Button back;
    private Button login;
    private ReceptorClickLoginDialogo receptorClickLoginDialogo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_dialog, container, false);

        back = view.findViewById(R.id.buttonBackMyDialog);
        login = view.findViewById(R.id.buttonLoginMyDialog);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receptorClickLoginDialogo.recibirClickLoginDialogo();
                dismiss();
            }
        });

        setCancelable(false);
        return view;
    }

    public interface ReceptorClickLoginDialogo{
        public void recibirClickLoginDialogo();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        receptorClickLoginDialogo = (ReceptorClickLoginDialogo) context;
    }
}
