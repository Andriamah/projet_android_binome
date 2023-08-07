package com.example.projetm1.view;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetm1.R;
import com.example.projetm1.adapter.FavoriAdapter;
import com.example.projetm1.controller.FavoriController;
import com.example.projetm1.model.Historique_favori;

import java.util.ArrayList;

public class FavoriListActivity extends AppCompatActivity implements FavoriController.GetFavoriClientCallBack {

    private FavoriController favoriController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favori_list);
        favoriController = new FavoriController();

        // Appeler la méthode pour récupérer la liste de Favori
        int idClient = 1; // Remplacez 1 par l'ID du client que vous souhaitez récupérer les favoris
        favoriController.getListFavoriClient(idClient, this);
    }

    private void creerListe(ArrayList<Historique_favori> lesFavori) {
        if (lesFavori != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ListView listDesFavori = (ListView) findViewById(R.id.listeViewFavori);
                    FavoriAdapter adapter = new FavoriAdapter(FavoriListActivity.this, lesFavori);
                    listDesFavori.setAdapter(adapter);
                }
            });
        }
    }

    @Override
    public void onGetFavoriClientSuccess(ArrayList<Historique_favori> favoris) {
        creerListe(favoris);
    }

    @Override
    public void onGetFavoriClientFailure(String messageError) {

    }
}