package com.example.projetm1.view.fragment.parametre;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.projetm1.R;
import com.example.projetm1.databinding.FragmentParametreBinding;

public class ParametreFragment extends Fragment {
    private FragmentParametreBinding binding;
    private NavController navController;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ParametreViewModel parametreViewModel =
                new ViewModelProvider(this).get(ParametreViewModel.class);


        binding = FragmentParametreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textSlideshow;
//        parametreViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        CardView card1 = view.findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Utilisation de NavController pour naviguer vers un autre fragment
                navController.navigate(R.id.nav_profil);
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