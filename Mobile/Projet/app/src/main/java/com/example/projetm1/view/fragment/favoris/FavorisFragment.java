package com.example.projetm1.view.fragment.favoris;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetm1.R;
import com.example.projetm1.adapter.FavoriAdapter;
import com.example.projetm1.controller.FavoriController;
import com.example.projetm1.databinding.FragmentGalleryBinding;
import com.example.projetm1.model.Historique_favori;

import java.util.ArrayList;
public class FavorisFragment extends Fragment {
    private FragmentGalleryBinding binding;
    private FavoriController favoriController;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavorisViewModel favorisViewModel =
                new ViewModelProvider(this).get(FavorisViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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
}