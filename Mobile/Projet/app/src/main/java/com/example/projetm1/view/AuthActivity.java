package com.example.projetm1.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetm1.R;
import com.example.projetm1.controller.ClientController;

public class AuthActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private ClientController clientController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        clientController = new ClientController();

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AuthActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Appeler la méthode de connexion du contrôleur
                clientController.login(username, password, new ClientController.LoginCallback() {
                    @Override
                    public void onLoginSuccess() {
                        // L'authentification a réussi
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AuthActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
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
    }
}