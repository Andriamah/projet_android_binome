package com.example.projetm1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetm1.R;
import com.example.projetm1.SplashScreenActivity;
import com.example.projetm1.controller.ClientController;
import com.example.projetm1.model.Client;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

public class AuthActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private ClientController clientController;
    private TextView registerLinkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_auth);
        clientController = new ClientController();
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerLinkTextView = findViewById(R.id.registerLink);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Appeler la méthode de connexion du contrôleur
                clientController.login(username, password, new ClientController.LoginCallback() {
                    @Override
                    public void onLoginSuccess(final Client client) {
                        // L'authentification a réussi
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Gson gson = new Gson();
                                String json = gson.toJson(client);
                                SharedPreferences preferences = getSharedPreferences("session", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("user_data", json);
                                editor.apply();

                                String savedJson = preferences.getString("user_data", "");
                                Client savedUser = gson.fromJson(savedJson, Client.class);

                                Log.d("tyyyyyyyyyyyyyyy",savedUser.getNom());

                                Toast.makeText(AuthActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AuthActivity.this, Accueil.class));
                            }
                        });
                    }

                    @Override
                    public void onLoginFailure(final String errorMessage) {
                        // L'authentification a échoué
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AuthActivity.this, "Erreur de connexion : " + errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        registerLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lorsque le texte est cliqué, démarrez l'activité RegisterActivity
                Intent intent = new Intent(AuthActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        TextInputLayout textInputLayout = findViewById(R.id.usernameEdit);
        EditText usernameEditText = findViewById(R.id.usernameEditText);

    }
}