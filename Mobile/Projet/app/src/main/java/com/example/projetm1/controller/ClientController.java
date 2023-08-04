package com.example.projetm1.controller;

import android.util.Log;

import com.example.projetm1.config.ApiConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ClientController {

    // Méthode pour gérer l'authentification (connexion)
    public void login(String username, String password, final LoginCallback callback) {

        String loginUrl = ApiConfig.BASE_URL + "/client/login";
        OkHttpClient client = new OkHttpClient();

        // Construire le corps de la requête POST avec les informations d'identification
        RequestBody formBody = new FormBody.Builder()
                .add("pseudo", username)
                .add("mdp", password)
                .build();

        // Construire la requête POST
        Request request = new Request.Builder()
                .url(loginUrl)
                .post(formBody)
                .build();

        // Exécuter la requête asynchrone
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Gérer les erreurs de la requête
                e.printStackTrace();
                callback.onLoginFailure("Erreur de connexion");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Récupérer la réponse JSON du serveur
                String jsonResponse = response.body().string();
                Log.d("MyTag", jsonResponse);
                try {
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    String success = jsonObject.getString("message");
                    String message = jsonObject.getString("message");

                    // Traiter la réponse JSON en fonction du succès ou de l'échec de l'authentification
                    if (success.equals("Connexion réussie")) {
                        // L'authentification a réussi
                        callback.onLoginSuccess();
                    } else {
                        // L'authentification a échoué
                        callback.onLoginFailure(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onLoginFailure("Erreur de traitement de la réponse du serveur");
                }
            }
        });
    }

    // Interface de rappel pour la connexion
    public interface LoginCallback {
        void onLoginSuccess();
        void onLoginFailure(String errorMessage);
    }
}

