package com.example.projetm1.config;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetm1.R;
import com.example.projetm1.model.CardModel;

import java.util.ArrayList;

public class ContenuAdapter extends RecyclerView.Adapter<ContenuAdapter.ContenuViewHolder> {

    private ArrayList<CardModel> contenuList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(CardModel contenu);
    }

    public ContenuAdapter(ArrayList<CardModel> contenuList, OnItemClickListener listener) {
        this.contenuList = contenuList;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ContenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ContenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContenuViewHolder holder, int position) {
        CardModel contenu = contenuList.get(position);
        holder.contenuImage.setImageResource(contenu.getImageResource());
        holder.contenuTitle.setText(contenu.getTitle());
        holder.contenuDescription.setText(contenu.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(contenu);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contenuList.size();
    }

    public static class ContenuViewHolder extends RecyclerView.ViewHolder {
        ImageView contenuImage;
        TextView contenuTitle;
        TextView contenuDescription;

        public ContenuViewHolder(@NonNull View itemView) {
            super(itemView);
            contenuImage = itemView.findViewById(R.id.cardImage);
            contenuTitle = itemView.findViewById(R.id.cardTitle);
            contenuDescription = itemView.findViewById(R.id.cardDescription);
        }
    }
}

