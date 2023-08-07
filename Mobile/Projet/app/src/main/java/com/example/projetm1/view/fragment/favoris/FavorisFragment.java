package com.example.projetm1.view.fragment.favoris;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetm1.adapter.FavoriAdapter;
import com.example.projetm1.controller.FavoriController;
import com.example.projetm1.databinding.FragmentGalleryBinding;
import com.example.projetm1.model.Historique_favori;

import java.util.ArrayList;

public class FavorisFragment extends Fragment implements FavoriController.GetFavoriClientCallBack {

    private FragmentGalleryBinding binding;
    private FavoriController favoriController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        favoriController = new FavoriController();

        // Appeler la méthode pour récupérer la liste de Favori
        int idClient = 1; // Remplacez 1 par l'ID du client que vous souhaitez récupérer les favoris
        favoriController.getListFavoriClient(idClient, this);

        return root;
    }

    private void creerListe(ArrayList<Historique_favori> lesFavori) {
        if (lesFavori != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ListView listDesFavori = binding.listeViewFavori; // Utiliser binding pour accéder à la ListView
                    FavoriAdapter adapter = new FavoriAdapter(getActivity(), lesFavori);
                    listDesFavori.setAdapter(adapter);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onGetFavoriClientSuccess(ArrayList<Historique_favori> favoris) {
        if (favoris != null) {
            Log.d("FavorisFragment", "Nombre de favoris récupérés : " + favoris.size());
            for (Historique_favori favori : favoris) {
                Log.d("FavorisFragment", "Favori : " + favori.toString());
            }
            creerListe(favoris);
        } else {
            Log.d("FavorisFragment", "Liste de favoris est vide.");
        }
    }


    @Override
    public void onGetFavoriClientFailure(String messageError) {

    }
}
