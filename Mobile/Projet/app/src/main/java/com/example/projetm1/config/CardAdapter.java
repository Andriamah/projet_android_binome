package com.example.projetm1.config;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetm1.R;
import com.example.projetm1.model.CardModel;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private ArrayList<CardModel> cardList;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int contenuId); // Passer l'ID au clic
    }

    public CardAdapter(ArrayList<CardModel> cardList,OnItemClickListener listener) {
        this.cardList = cardList;
        this.onItemClickListener = listener;
    }

    public CardAdapter(ArrayList<CardModel> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardModel card = cardList.get(position);
        holder.cardImage.setImageResource(card.getImageResource());
        holder.cardImage.setBackgroundResource(R.drawable.rounded_image_view);
        holder.cardTitle.setText(card.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putInt("contenuId", card.getId());
                Navigation.findNavController(v).navigate(R.id.nav_contenu, args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImage;
        TextView cardTitle;
        TextView cardDescription;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.cardImage);
            cardTitle = itemView.findViewById(R.id.cardTitle);
            cardDescription = itemView.findViewById(R.id.cardDescription);
        }
    }
}

