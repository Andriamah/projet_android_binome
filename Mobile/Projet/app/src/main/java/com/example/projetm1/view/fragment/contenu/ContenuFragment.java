package com.example.projetm1.view.fragment.contenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import com.example.projetm1.model.CardModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContenuFragment extends Fragment {
    private RecyclerView recyclerView;
    private ContenuAdapter contenuAdapter;
    private FloatingActionButton floatingActionButton;

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
        ArrayList<CardModel> cardList = new ArrayList<>();
        // Remplissez votre liste de cartes avec les données nécessaires
        cardList.add(new CardModel(R.drawable.baobabs, "Titre 1", ""));
        cardList.add(new CardModel(R.drawable.baobabs, "Titre 2", ""));
        cardList.add(new CardModel(R.drawable.baobabs, "Titre 3", ""));
        cardList.add(new CardModel(R.drawable.baobabs, "Titre 4", ""));
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