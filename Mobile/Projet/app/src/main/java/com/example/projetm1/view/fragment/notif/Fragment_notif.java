package com.example.projetm1.view.fragment.notif;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetm1.adapter.NotificationAdapter;
import com.example.projetm1.controller.NotificationController;
import com.example.projetm1.databinding.FragmentNotifBinding;
import com.example.projetm1.model.Historique_notif;

import java.util.ArrayList;

public class Fragment_notif extends Fragment implements NotificationController.GetNotifClientCallBack{

    private FragmentNotifBinding binding;
    private NotificationController notificationController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotifBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        notificationController = new NotificationController();

        // Appeler la méthode pour récupérer la liste de Favori
        int idClient = 1; // Remplacez 1 par l'ID du client que vous souhaitez récupérer les favoris
        notificationController.getListNotifClient(idClient, this);

        return root;
    }

    private void creerListe(ArrayList<Historique_notif> lesNotifs) {
        if (lesNotifs != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ListView listDesNotif = binding.listeViewNotif; // Utiliser binding pour accéder à la ListView
                    NotificationAdapter adapter = new NotificationAdapter(getActivity(), lesNotifs);
                    listDesNotif.setAdapter(adapter);
                }
            });
        }
    }

    @Override
    public void onGetNotifClientSuccess(ArrayList<Historique_notif> notifs) {
        Log.d("Fragment_notif", "Nombre d'éléments dans la liste notifs : " + notifs.size());
        for (Historique_notif notif : notifs) {
            Log.d("Fragment_notif", "Date_notif : " + notif.getDate_notif() + ", Pseudo : " + notif.getPseudo());
        }
        creerListe(notifs);
    }

    @Override
    public void onGetNotifClientFailure(String messageError) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
