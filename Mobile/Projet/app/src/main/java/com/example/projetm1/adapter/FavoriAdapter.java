package com.example.projetm1.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetm1.R;
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
        if(view==null){
            holder = new ViewHolder();
            //ligne construite
            view = inflater.inflate(R.layout.layout_list_favori,null);
            //ch pr du holder relie çà prop
            holder.IMGView =(ImageView) view.findViewById(R.id.IMGviewNotif);
            holder.textDate = view.findViewById(R.id.textDate);
            holder.textPub = view.findViewById(R.id.textPublieur);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.textDate.setText(favoriArrayList.get(i).getDate_contenu());
        holder.textPub.setText(favoriArrayList.get(i).getPseudo());
        return view;
    }

    public class ViewHolder{
        ImageView IMGView;
        TextView textPub;
        TextView textDate;
    }
}

