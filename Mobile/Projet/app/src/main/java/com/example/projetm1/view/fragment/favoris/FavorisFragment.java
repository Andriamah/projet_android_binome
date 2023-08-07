package com.example.projetm1.view.fragment.favoris;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetm1.controller.FavoriController;
import com.example.projetm1.databinding.FragmentGalleryBinding;


public class FavorisFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private FavoriController favoriController;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavorisViewModel favorisViewModel =
                new ViewModelProvider(this).get(FavorisViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View rootView = inflater.inflate(R.layout.fragment_favori_list, container, false);
        favoriController = new FavoriController();

        // Appeler la méthode pour récupérer la liste de Favori
        int idClient = 1; // Remplacez 1 par l'ID du client que vous souhaitez récupérer les favoris
        favoriController.getListFavoriClient(idClient, this);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}