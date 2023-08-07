package com.example.projetm1.controller;

import android.util.Log;

import com.example.projetm1.config.ApiConfig;
import com.example.projetm1.model.Client;
import com.google.gson.Gson;

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
                .add("username", username)
                .add("password", password)
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
                    boolean success = jsonObject.getBoolean("success");
                    String message = jsonObject.getString("error");

                    // Traiter la réponse JSON en fonction du succès ou de l'échec de l'authentification
                    if (success) {
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

    public void updateProfil(int id, String nom, String prenom, String pseudo, String mail, String mdp, String pdp,
                                    final UpdateProfilCallback callback){
        String updateUrl = ApiConfig.BASE_URL + "/client/"+id;
        OkHttpClient client = new OkHttpClient();

        // Construire le corps de la requête PuT avec les informations d'identification
        RequestBody formBody = new FormBody.Builder()
                .add("nom", nom)
                .add("prenom", prenom)
                .add("pseudo", pseudo)
                .add("mail", mail)
                .add("mdp", mdp)
                .add("pdp", pdp)
                .build();

        // Construire la requête POST
        Request request = new Request.Builder()
                .url(updateUrl)
                .put(formBody)
                .build();

        // Exécuter la requête asynchrone
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Gérer les erreurs de la requête
                e.printStackTrace();
                callback.onUpdateProfilFailure("Erreur de connexion");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Récupérer la réponse JSON du serveur
                String jsonResponse = response.body().string();
                Log.d("MyTag", jsonResponse);
                try {
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    String message = jsonObject.getString("message");
                    boolean success = !jsonObject.getBoolean("error"); // Inverser la valeur de "error" pour obtenir "success"

                    // Traiter la réponse JSON en fonction du succès ou de l'échec de la mise à jour du profil
                    if (success) {
                        // La mise à jour du profil a réussi
                        callback.onUpdateProfilSuccess();
                    } else {
                        // La mise à jour du profil a échoué
                        callback.onUpdateProfilFailure(message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onUpdateProfilFailure("Erreur de traitement de la réponse du serveur");
                }
            }
        });
    }
    public void getClientById(int clientId, final GetClientByIdCallback callback) {
            // Construire l'URL de l'API pour récupérer les détails du client avec l'ID donné
        String apiUrl = ApiConfig.BASE_URL + "/client/" + clientId;

        // Créer une instance d'OkHttpClient pour effectuer la requête
            OkHttpClient client = new OkHttpClient();

            // Construire la requête GET pour récupérer les détails du client
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .get()
                    .build();

            // Exécuter la requête asynchrone
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    // Gérer les erreurs de la requête
                    e.printStackTrace();
                    callback.onGetClientByIdFailure("Erreur de connexion");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    // Récupérer la réponse JSON du serveur
                    String jsonResponse = response.body().string();
                    Log.d("MyTag", jsonResponse);
                    try {
                        // Convertir la réponse JSON en objet Client
                        Gson gson = new Gson();
                        Client client = gson.fromJson(jsonResponse, Client.class);

                        // Appeler la méthode de succès du callback avec le client récupéré
                        callback.onGetClientByIdSuccess(client);
                    } catch (Exception e) {
                        e.printStackTrace();
                        callback.onGetClientByIdFailure("Erreur de traitement de la réponse du serveur");
                    }
                }
            });
        }

        // Interface pour le callback de récupération du client par ID
    public interface GetClientByIdCallback {
        void onGetClientByIdSuccess(Client client);

        void onGetClientByIdFailure(String errorMessage);
    }

    // Interface de rappel pour la connexion
    public interface LoginCallback {
        void onLoginSuccess();
        void onLoginFailure(String errorMessage);
    }

    public interface UpdateProfilCallback {
        void onUpdateProfilSuccess();
        void onUpdateProfilFailure(String errorMessage);
    }

}

