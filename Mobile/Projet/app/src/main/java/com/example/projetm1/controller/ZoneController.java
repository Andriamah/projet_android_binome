package com.example.projetm1.controller;

import androidx.annotation.NonNull;

import com.example.projetm1.config.ApiConfig;
import com.example.projetm1.model.Zone;
import com.example.projetm1.outils.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZoneController {
    public void getListFavoriClient(final ZoneController.GetZoneCallBack callBack) {
        String favoriUrl = ApiConfig.BASE_URL + "/zone/";

        // Créer une instance d'OkHttpClient pour effectuer la requête
        OkHttpClient client = OkHttpUtils.getUnsafeOkHttpClient();

        // Construire la requête GET pour récupérer les détails du client
        Request request = new Request.Builder()
                .url(favoriUrl)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // Vérifier si la réponse est réussie (code de statut 200) avant de traiter la
                // réponse
                if (response.isSuccessful()) {
                    // Récupérer la réponse JSON du serveur
                    String jsonResponse = response.body().string();

                    // Analyser la réponse JSON en une liste d'objets Favori à l'aide de Gson
                    ArrayList<Zone> favoris = new Gson().fromJson(jsonResponse, new TypeToken<ArrayList<Zone>>() {
                    }.getType());

                    // Appeler le callback avec la liste de favoris
                    callBack.onGetZoneClientSuccess(favoris);
                } else {
                    // Appeler le callback avec un message d'erreur en cas de réponse non réussie
                    callBack.onGetZoneClientFailure("Erreur lors de la récupération des favoris du client");
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Appeler le callback avec un message d'erreur en cas d'échec de la requête
                callBack.onGetZoneClientFailure("Erreur lors de la récupération des favoris du client");
            }
        });
    }

    public interface GetZoneCallBack {
        void onGetZoneClientSuccess(ArrayList<Zone> favoris);

        void onGetZoneClientFailure(String messageError);
    }
}
