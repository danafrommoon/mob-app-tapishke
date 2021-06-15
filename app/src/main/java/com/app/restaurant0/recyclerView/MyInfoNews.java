package com.app.restaurant0.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.restaurant0.R;
import com.app.restaurant0.model.InfoNews;
import com.app.restaurant0.model.InfoRes;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyInfoNews extends FirebaseRecyclerAdapter<InfoNews,MyInfoNews.myinfoholder>
{
    public MyInfoNews(@NonNull FirebaseRecyclerOptions<InfoNews> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyInfoNews.myinfoholder holder, int position, @NonNull final InfoNews model) {
        holder.infoNews.setText(model.getInfoNews());
        holder.descriptionNews.setText(model.getDescriptionNews());
        Glide.with(holder.imgNews.getContext()).load(model.getImgNews()).into(holder.imgNews);
    }

    @NonNull
    @Override
    public MyInfoNews.myinfoholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new myinfoholder(view);
    }

    public class myinfoholder extends RecyclerView.ViewHolder
    {
        ImageView imgNews;
        TextView infoNews,descriptionNews;

        public myinfoholder(@NonNull View itemView) {
            super(itemView);

            imgNews = itemView.findViewById(R.id.imgNews);
            infoNews = itemView.findViewById(R.id.infoNews);
            descriptionNews = itemView.findViewById(R.id.descriptionNews);
        }
    }
}
