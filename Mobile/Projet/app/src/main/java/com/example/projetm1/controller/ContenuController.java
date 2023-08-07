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

public class ContenuController {

    // Méthode pour ajouter un contenu
    public void addContenu(int id_client, int id_zone, String commentaire, String photo,
                           String video, String date_contenu, final AddContenuCallback callback) {

        String addContenuUrl = ApiConfig.BASE_URL + "/contenu/";
        OkHttpClient client = new OkHttpClient();

        // Construire le corps de la requête POST avec les détails du contenu à ajouter
        RequestBody formBody = new FormBody.Builder()
                .add("id_client", String.valueOf(id_client))
                .add("id_zone", String.valueOf(id_zone))
                .add("commentaire", commentaire)
                .add("photo", photo)
                .add("video", video)
                .add("date_contenu", date_contenu) // Date convertie en millisecondes
                .build();

        // Construire la requête POST
        Request request = new Request.Builder()
                .url(addContenuUrl)
                .post(formBody)
                .build();

        // Exécuter la requête asynchrone
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Gérer les erreurs de la requête
                e.printStackTrace();
                callback.onAddContenuFailure("Erreur lors de l'ajout du contenu");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Récupérer la réponse JSON du serveur
                String jsonResponse = response.body().string();
                Log.d("MyTag", jsonResponse);
                try {
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    boolean success = jsonObject.getBoolean("success");
                    String message = jsonObject.getString("message");

                    // Traiter la réponse JSON en fonction du succès ou de l'échec de l'ajout du contenu
                    if (success) {
                        // L'ajout du contenu a réussi
                        callback.onAddContenuSuccess(message);
                    } else {
                        // L'ajout du contenu a échoué
                        callback.onAddContenuFailure(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onAddContenuFailure("Erreur de traitement de la réponse du serveur");
                }
            }
        });
    }


    // Interface de rappel pour l'ajout de contenu
    public interface AddContenuCallback {
        void onAddContenuSuccess(String successMessage);
        void onAddContenuFailure(String errorMessage);
    }
}
