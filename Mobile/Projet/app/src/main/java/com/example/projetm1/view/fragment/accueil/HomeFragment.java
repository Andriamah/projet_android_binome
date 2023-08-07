package com.example.projetm1.view.fragment.accueil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetm1.R;
import com.example.projetm1.config.CardAdapter;
import com.example.projetm1.databinding.FragmentHomeBinding;
import com.example.projetm1.model.CardModel;
import com.example.projetm1.view.fragment.contenu.ContenuFragment;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
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

        cardAdapter = new CardAdapter(cardList, new CardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int contenuId) {
                // Passer l'ID au nouveau fragment et ouvrir le fragment
                Fragment fragment = new ContenuFragment();
                Bundle args = new Bundle();
                args.putInt("contenuId", contenuId);
                fragment.setArguments(args);
            }
        });
        recyclerView.setAdapter(cardAdapter);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}