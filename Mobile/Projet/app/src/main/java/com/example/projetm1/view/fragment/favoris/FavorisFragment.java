package com.example.projetm1.view.fragment.favoris;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetm1.controller.FavoriController;
import com.example.projetm1.databinding.FragmentGalleryBinding;
import com.example.projetm1.model.Historique_favori;

import com.example.projetm1.R;
import com.example.projetm1.adapter.FavoriAdapter;
import com.example.projetm1.controller.FavoriController;
import com.example.projetm1.databinding.FragmentGalleryBinding;
import com.example.projetm1.model.Historique_favori;

import java.util.ArrayList;


import java.util.ArrayList;

public class FavorisFragment extends Fragment implements FavoriController.GetFavoriClientCallBack {

    private FragmentGalleryBinding binding;
    private FavoriController favoriController;

    private View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        favoriController = new FavoriController();

        // Remplacez 1 par l'ID du client que vous souhaitez récupérer les favoris
        int idClient = 1;
        favoriController.getListFavoriClient(idClient, this);


        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        favoriController = new FavoriController();

        // Appeler la méthode pour récupérer la liste de Favori
        int idClient = 1; // Remplacez 1 par l'ID du client que vous souhaitez récupérer les favoris
        favoriController.getListFavoriClient(idClient, new FavoriController.GetFavoriClientCallBack() {
            @Override
            public void onGetFavoriClientSuccess(ArrayList<Historique_favori> favoris) {
                creerListe(favoris);
            }

            @Override
            public void onGetFavoriClientFailure(String messageError) {

            }
        });

        return root;
    }

    private void creerListe(ArrayList<Historique_favori> lesFavori) {
        if (lesFavori != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ListView listDesFavori = getView().findViewById(R.id.listeViewFavori);
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
    private void creerListe(ArrayList<Historique_favori> lesFavori) {
        if (lesFavori != null) {
            requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                  /*  ListView listDesFavori = root.findViewById(R.id.listeViewFavori); ETO LAY LAYOUT MISY AN LE LISTvIEW
                    FavoriAdapter adapter = new FavoriAdapter(requireContext(), lesFavori);
                    listDesFavori.setAdapter(adapter);*/
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
        // Traiter l'échec de la récupération des favoris ici si nécessaire
    }



}
