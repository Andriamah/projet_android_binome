package com.example.projetm1.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.projetm1.R;
import com.example.projetm1.controller.ClientController;
import com.example.projetm1.model.Client;

public class RegisterActivity extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private EditText pseudo;
    private EditText mail;
    private EditText mdp;
    private EditText pdp;

    private ClientController clientController;

    // Déclarez d'autres EditText pour les autres champs (nom, prénom, etc.)
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        Enlever le header
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        clientController = new ClientController();
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        pseudo = findViewById(R.id.pseudo);
        mail = findViewById(R.id.mail);
        mdp = findViewById(R.id.mdp);
//        pdp = findViewById(R.id.pdp);

        // Initialisez les autres EditText

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérez les valeurs des champs EditText
                String _nom = nom.getText().toString();
                String _prenom = prenom.getText().toString();
                String _pseudo = pseudo.getText().toString();
                String _mail = mail.getText().toString();
                String _mdp = mdp.getText().toString();
                // Récupérez les autres valeurs

                // Créez un nouvel objet Client
                Client client = new Client(_nom,_prenom,_pseudo,_mail,_mdp,"haha");
                clientController.register(client,
                        new ClientController.RegisterCallback() {
                            @Override
                            public void onRegisterSuccess() {
                                // L'authentification a réussi
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(AuthActivity.this, Accueil.class));
                                    }
                                });
                            }
                            @Override
                            public void onRegisterFailure(final String errorMessage) {
                                // L'authentification a échoué
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "Erreur  : " + errorMessage, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });

                // Enregistrez le client dans la base de données ou effectuez d'autres actions

            }
        });
        TextView authLinkTextView = findViewById(R.id.authLinkTextView);
        authLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lorsque le texte est cliqué, démarrez l'activité RegisterActivity
                Intent intent = new Intent(RegisterActivity.this, AuthActivity.class);
                startActivity(intent);
            }
        });
    }
}
