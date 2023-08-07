package com.example.projetm1.view.fragment.contenu;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetm1.R;
import com.example.projetm1.config.CardAdapter;
import com.example.projetm1.config.ContenuAdapter;
import com.example.projetm1.controller.ContenuController;
import com.example.projetm1.controller.FavoriController;
import com.example.projetm1.model.CardModel;
import com.example.projetm1.model.Contenu;
import com.example.projetm1.model.Historique_favori;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class ContenuFragment extends Fragment {
    private RecyclerView recyclerView;
    private ContenuAdapter contenuAdapter;
    private FloatingActionButton floatingActionButton;

    ContenuController contenuController;

    private NavController navController;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        floatingActionButton = rootView.findViewById(R.id.fab);
        Bundle args = getArguments();
        int contenuId = 1;
        if (args != null) {
             contenuId = args.getInt("contenuId", 1)+1; // Remplacez defaultValue par une valeur par défaut appropriée
            // Maintenant, vous avez la valeur contenuId
        }
        ArrayList<CardModel> cardList = new ArrayList<>();
        contenuController = new ContenuController();
        final CountDownLatch latch = new CountDownLatch(1);
      contenuController.getListFavoriClient(contenuId, new ContenuController.GetContenuCallBack() {
          @Override
          public void onGetFavoriClientSuccess(ArrayList<Contenu> favoris) {
              Log.d("tyy","niditra beeeeeeee");
              for (Contenu c: favoris
                   ) {
                  Log.d("tyy","niditra boucle");

                  cardList.add(new CardModel(R.drawable.baobabs, c.getCommentaire(), c.getDate_contenu()));
              }
              latch.countDown();
          }
          @Override
          public void onGetFavoriClientFailure(String messageError) {
              latch.countDown();
          }
      });

        try {
            // Attendez ici que le compte à rebours atteigne zéro
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Remplissez votre liste de cartes avec les données nécessaires
//        cardList.add(new CardModel(R.drawable.baobabs, "Titre 1", ""));
//        cardList.add(new CardModel(R.drawable.baobabs, "Titre 2", ""));
//        cardList.add(new CardModel(R.drawable.baobabs, "Titre 3", ""));
//        cardList.add(new CardModel(R.drawable.baobabs, "Titre 4", ""));
        // Ajoutez plus d'éléments à la liste

        contenuAdapter = new ContenuAdapter(cardList);
        recyclerView.setAdapter(contenuAdapter);
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Utilisation de NavController pour naviguer vers un autre fragment
                Log.d("hahahaha","tonga etoooooo");
                navController.navigate(R.id.ajout_contenu);
            }
        });
        // Le reste de votre code onViewCreated
    }
}