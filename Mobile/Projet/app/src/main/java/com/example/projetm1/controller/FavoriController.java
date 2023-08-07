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

public class FavoriController {

    public void addFavori(int id,int id_client,int id_contenu,final AddFavoriCallBack callBack){
        String addFavoriUrl = ApiConfig.BASE_URL + "/favori/";
        OkHttpClient client = new OkHttpClient();

        // Construire le corps de la requête POST avec les informations d'identification
        RequestBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(id))
                .add("id_client", String.valueOf(id_client))
                .add("id_contenu", String.valueOf(id_contenu))
                .build();

        // Construire la requête POST
        Request request = new Request.Builder()
                .url(addFavoriUrl)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Gérer les erreurs de la requête
                e.printStackTrace();
                callBack.onAddFavoriFailure("Erreur de connexion");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Récupérer la réponse JSON du serveur
                String jsonResponse = response.body().string();
                Log.d("MyTag", jsonResponse);
                try {
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    boolean success = jsonObject.getBoolean("success");
                    String message = jsonObject.getString("error");

                    // Traiter la réponse JSON en fonction du succès ou de l'échec de l'authentification
                    if (success) {
                        // L'authentification a réussi
                        callBack.onAddFavoriSuccess();
                    } else {
                        // L'authentification a échoué
                        callBack.onAddFavoriFailure(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    callBack.onAddFavoriFailure("Erreur de traitement de la réponse du serveur");
                }
            }
        });
    }

    public interface AddFavoriCallBack{
        void onAddFavoriSuccess();
        void onAddFavoriFailure(String message);

    }
}
