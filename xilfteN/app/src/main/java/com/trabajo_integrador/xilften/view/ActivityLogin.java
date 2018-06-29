package com.trabajo_integrador.xilften.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.trabajo_integrador.xilften.R;

public class ActivityLogin extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private TextView forgotPassword;
    private Button loginNativo;
    private Button registerNativo;
    private LoginButton loginFacebook;
    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.fragment_login_email_adress);
        password = findViewById(R.id.fragment_login_password);
        forgotPassword = findViewById(R.id.fragment_login_forgot_password);
        loginNativo = findViewById(R.id.fragment_login_button_login);
        loginFacebook = findViewById(R.id.login_button);
        registerNativo = findViewById(R.id.fragment_login_button_register);

            password.setError("Password must have at least 6 characters");

            email.setError("Enter a valid email adress");

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null){
                    onBackPressed();
                }
            }
        };

        loginNativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                if (emailString.isEmpty() && passwordString.isEmpty()){
                    Toast.makeText(ActivityLogin.this, "Enter your email and password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!emailString.contains("@") && !emailString.contains(".com")){
                    Toast.makeText(ActivityLogin.this, "Enter a valid email adress", Toast.LENGTH_SHORT).show();
                } else if (passwordString.length() < 6){
                    Toast.makeText(ActivityLogin.this, "Password must have at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    loguearUsuario(emailString, passwordString);
                }

            }
        });

        registerNativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                if (emailString.isEmpty() && passwordString.isEmpty()){
                    Toast.makeText(ActivityLogin.this, "Enter your email and password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!emailString.contains("@") && !emailString.contains(".com")){
                    Toast.makeText(ActivityLogin.this, "Enter a valid email adress", Toast.LENGTH_SHORT).show();
                } else if (passwordString.length() < 6){
                    Toast.makeText(ActivityLogin.this, "Password must have at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    crearUsuario(emailString, passwordString);
                }
            }
        });

        callbackManager = CallbackManager.Factory.create();

        loginFacebook.setReadPermissions("email");

        // Callback registration
        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(ActivityLogin.this, "Logged In", Toast.LENGTH_SHORT).show();
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(ActivityLogin.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(ActivityLogin.this, "FATAL ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(ActivityLogin.this, "ERROR LOGIN", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void crearUsuario(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("firebase", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(ActivityLogin.this, "welcome to THIRST", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("firebase", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(ActivityLogin.this, "Register authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void loguearUsuario(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ActivityLogin.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
                            Log.d("firebase", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            onBackPressed();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("firebase", "signInWithEmail:failure", task.getException());
                            Toast.makeText(ActivityLogin.this, " Login authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }
}
