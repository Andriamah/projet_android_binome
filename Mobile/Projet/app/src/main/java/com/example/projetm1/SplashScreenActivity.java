package com.example.projetm1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetm1.outils.SSLUtils;
import com.example.projetm1.view.Accueil;
import com.example.projetm1.view.AuthActivity;


// java/com/yourpackage/SplashScreenActivity.java
public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DELAY = 3000; // 3 secondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SSLUtils.disableSSLVerification();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // Démarrer l'activité suivante après un délai
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Vérifier si le client est connecté (remplacez cette logique avec votre propre vérification)
                SharedPreferences preferences = getSharedPreferences("session", Context.MODE_PRIVATE);
                String savedJson = preferences.getString("user_data", "");
                if (!savedJson.equals("")) {
                    // Client connecté, démarrer HomeActivity
//                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                    startActivity(new Intent(SplashScreenActivity.this, Accueil.class));
                } else {
                    // Client non connecté, démarrer LoginActivity
                    startActivity(new Intent(SplashScreenActivity.this, AuthActivity.class));
                }
                // Terminer l'activité SplashScreen pour qu'elle ne revienne pas lorsque l'utilisateur appuie sur le bouton Retour
                finish();
            }
        }, SPLASH_SCREEN_DELAY);
    }

    // Méthode de vérification de la connexion (vous devrez remplacer cette logique avec votre propre vérification)
    private boolean isClientConnected() {
        // Utilisez Retrofit pour effectuer un appel au Web Service REST pour vérifier si le client est connecté.
        // Par exemple, vous pouvez utiliser une méthode dans votre API Retrofit pour vérifier la connexion
        // et retourner true si le client est connecté et false sinon.
        // return YourRetrofitApiClient.isClientConnected();
        // Ici, nous allons simplement retourner true pour les besoins de l'exemple.
        return true;
    }
}
