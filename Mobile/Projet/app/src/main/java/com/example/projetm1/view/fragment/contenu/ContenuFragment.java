package com.example.projetm1.view.fragment.contenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetm1.R;
import com.example.projetm1.config.CardAdapter;
import com.example.projetm1.model.CardModel;

import java.util.ArrayList;

public class ContenuFragment extends Fragment {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        ArrayList<CardModel> cardList = new ArrayList<>();
        // Remplissez votre liste de cartes avec les données nécessaires
        cardList.add(new CardModel(R.drawable.baobabs, "Titre 1", ""));
        cardList.add(new CardModel(R.drawable.baobabs, "Titre 2", ""));
        cardList.add(new CardModel(R.drawable.baobabs, "Titre 3", ""));
        cardList.add(new CardModel(R.drawable.baobabs, "Titre 4", ""));
        // Ajoutez plus d'éléments à la liste

        cardAdapter = new CardAdapter(cardList);
        recyclerView.setAdapter(cardAdapter);
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
        return rootView;
    }
}