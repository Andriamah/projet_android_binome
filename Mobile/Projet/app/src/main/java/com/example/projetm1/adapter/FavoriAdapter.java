package com.example.projetm1.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetm1.model.Historique_favori;

import java.util.ArrayList;

public class FavoriAdapter extends BaseAdapter {

    private ArrayList<Historique_favori> favoriArrayList;
    private LayoutInflater inflater;

    public FavoriAdapter(Context context,ArrayList<Historique_favori> favoriArrayList) {
        this.favoriArrayList = favoriArrayList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return favoriArrayList.size();
    }
    //item liste ectuel
    @Override
    public Object getItem(int i) {
        return favoriArrayList.get(i);
    }
    //indice % ligne actuel
    @Override
    public long getItemId(int i) {
        return i;
    }
    //la view foemate
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        return view;
    }

    public class ViewHolder{
        ImageView IMGView;
        TextView textPub;
        TextView textDate;
    }
}

