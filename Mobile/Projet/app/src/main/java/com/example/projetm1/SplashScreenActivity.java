package com.example.projetm1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.projetm1.view.AuthActivity;


// java/com/yourpackage/SplashScreenActivity.java
public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DELAY = 3000; // 3 secondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Démarrer l'activité suivante après un délai
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Vérifier si le client est connecté (remplacez cette logique avec votre propre vérification)
                if (isClientConnected()) {
                    // Client connecté, démarrer HomeActivity
//                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                    startActivity(new Intent(SplashScreenActivity.this, AuthActivity.class));
                } else {
                    // Client non connecté, démarrer LoginActivity

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
