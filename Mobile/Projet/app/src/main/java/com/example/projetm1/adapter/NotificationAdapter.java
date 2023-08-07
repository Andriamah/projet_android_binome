package com.example.projetm1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetm1.model.Historique_notif;

import java.util.ArrayList;

public class NotificationAdapter extends BaseAdapter {
    ArrayList<Historique_notif> notifArrayList;
    private LayoutInflater inflater;

    public NotificationAdapter(Context context, ArrayList<Historique_notif> notifArrayList) {
        this.notifArrayList = notifArrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return notifArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return notifArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NotificationAdapter.ViewHolderNotif holder;


        //holder.textDate.setText(notifArrayList.get(i).getDate_notif());

        return view;
    }

    public class ViewHolderNotif{
        ImageView IMGView;
        TextView textReag;
        TextView textDate;
    }
}
