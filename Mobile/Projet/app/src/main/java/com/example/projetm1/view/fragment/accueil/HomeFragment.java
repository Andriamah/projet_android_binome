package com.example.projetm1.view.fragment.accueil;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetm1.R;
import com.example.projetm1.config.CardAdapter;
import com.example.projetm1.controller.FavoriController;
import com.example.projetm1.controller.ZoneController;
import com.example.projetm1.databinding.FragmentHomeBinding;
import com.example.projetm1.model.CardModel;
import com.example.projetm1.model.Historique_favori;
import com.example.projetm1.model.Zone;
import com.example.projetm1.view.fragment.contenu.ContenuFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private CardAdapter cardAdapter;
    private NavController navController;

    private ZoneController zoneController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        floatingActionButton = rootView.findViewById(R.id.fab);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        zoneController = new ZoneController();
        ArrayList<CardModel> cardList = new ArrayList<>();
        final CountDownLatch latch = new CountDownLatch(1);
     zoneController.getListFavoriClient(new ZoneController.GetZoneCallBack(){
         @Override
         public void onGetZoneClientSuccess(ArrayList<Zone> favoris) {
             for (Zone z: favoris) {
                 String test = z.getIntitule();
                 int drawableResourceId = getResources().getIdentifier(test, "drawable",requireContext().getPackageName());
                 Log.d("haha",""+drawableResourceId);
                 if (drawableResourceId==0){
                     cardList.add(new CardModel(R.drawable.baobabs, z.getIntitule(), z.getDescription()));
                 }else{
                     cardList.add(new CardModel(drawableResourceId, z.getIntitule(), z.getDescription()));
                 }

                 Log.d("ieeeeeeeee","yesssssssssss"+favoris.size());
             }
             latch.countDown();
         }
         @Override
         public void onGetZoneClientFailure(String messageError) {
                Log.d("aaa","nonnnnnnnnnnnnnnnnnnnnnnnnnnnn"+messageError);
             latch.countDown();
         }
     });
        try {
            // Attendez ici que le compte à rebours atteigne zéro
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Log.d("ieeeeeeeee","faharoa");
        // Remplissez votre liste de cartes avec les données nécessaires
//        cardList.add(new CardModel(R.drawable.baobabs, "Titre 1", ""));
//        cardList.add(new CardModel(R.drawable.baobabs, "Titre 2", ""));
//        cardList.add(new CardModel(R.drawable.baobabs, "Titre 3", ""));
//        cardList.add(new CardModel(R.drawable.baobabs, "Titre 4", ""));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}