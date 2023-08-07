package com.example.projetm1.view;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetm1.R;
import com.example.projetm1.adapter.NotificationAdapter;
import com.example.projetm1.controller.NotificationController;
import com.example.projetm1.model.Historique_notif;

import java.util.ArrayList;

public class Notification_listActivity extends AppCompatActivity implements NotificationController.GetNotifClientCallBack {

    NotificationController notificationController;
    ArrayList<Historique_notif> listNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);

        notificationController = new NotificationController();

        // Appeler la méthode pour récupérer la liste de Favori
        int idClient = 1; // Remplacez 1 par l'ID du client que vous souhaitez récupérer les favoris
        notificationController.getListNotifClient(idClient, this);
    }
    private void creerListe(ArrayList<Historique_notif> lesNotifs) {
        if (lesNotifs != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ListView listDesNotifs = (ListView) findViewById(R.id.listeViewNotif);
                    NotificationAdapter adapter = new NotificationAdapter(Notification_listActivity.this, lesNotifs);
                    listDesNotifs.setAdapter(adapter);
                }
            });
        }
    }
    @Override
    public void onGetNotifClientSuccess(ArrayList<Historique_notif> notifs) {
        creerListe(notifs);
    }
    @Override
    public void onGetNotifClientFailure(String messageError) {

    }
}