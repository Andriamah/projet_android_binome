package com.example.projetm1.controller;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.projetm1.config.ApiConfig;
import com.example.projetm1.model.Historique_favori;
import com.example.projetm1.outils.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FavoriController {

    public void addFavori(int id, int id_client, int id_contenu, final AddFavoriCallBack callBack) {
        String addFavoriUrl = ApiConfig.BASE_URL + "/favori/";
        OkHttpClient client = OkHttpUtils.getUnsafeOkHttpClient();

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

                    // Traiter la réponse JSON en fonction du succès ou de l'échec de
                    // l'authentification
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

    public interface AddFavoriCallBack {
        void onAddFavoriSuccess();

        void onAddFavoriFailure(String message);

    }

    public void getListFavoriClient(int id_client, final GetFavoriClientCallBack callBack) {
        String favoriUrl = ApiConfig.BASE_URL + "/favori/" + id_client;

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
                    ArrayList<Historique_favori> favoris = new Gson().fromJson(jsonResponse,
                            new TypeToken<ArrayList<Historique_favori>>() {
                            }.getType());

                    // Appeler le callback avec la liste de favoris
                    callBack.onGetFavoriClientSuccess(favoris);
                } else {
                    // Appeler le callback avec un message d'erreur en cas de réponse non réussie
                    callBack.onGetFavoriClientFailure("Erreur lors de la récupération des favoris du client");
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Appeler le callback avec un message d'erreur en cas d'échec de la requête
                callBack.onGetFavoriClientFailure("Erreur lors de la récupération des favoris du client");
            }
        });
    }

    public interface GetFavoriClientCallBack {

        void onGetFavoriClientSuccess(ArrayList<Historique_favori> favoris);

        void onGetFavoriClientFailure(String messageError);
    }
}
