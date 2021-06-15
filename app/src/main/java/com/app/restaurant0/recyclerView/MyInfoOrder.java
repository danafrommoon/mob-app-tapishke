package com.app.restaurant0.recyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.restaurant0.R;
import com.app.restaurant0.activity.ReservationActivity;
import com.app.restaurant0.model.InfoRes;
import com.app.restaurant0.model.Order;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyInfoOrder extends FirebaseRecyclerAdapter<Order, MyInfoOrder.MyViewHolder>
{
    public MyInfoOrder(@NonNull FirebaseRecyclerOptions<Order> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull final Order model) {
        holder.nameOrd.setText("Имя: " + model.getUsername());
        holder.restOrd.setText("Ресторан: " + model.getRestOrd());
        holder.tabletNumOrd.setText("Номер стола: " + model.getTabletNumOrd());
        holder.timeOrd.setText("Время: " + model.getTimeOrd());
        holder.guestsOrd.setText("Количество гостей: " + model.getGuestsOrd());
        holder.statusOrd.setText("Статус: " + model.getStatusOrd());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_order,parent,false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameOrd,restOrd,tabletNumOrd, timeOrd, guestsOrd, statusOrd;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOrd = itemView.findViewById(R.id.nameOrd);
            restOrd = itemView.findViewById(R.id.restOrd);
            tabletNumOrd = itemView.findViewById(R.id.tabletNumOrd);
            timeOrd = itemView.findViewById(R.id.timeOrd);
            guestsOrd = itemView.findViewById(R.id.guestsOrd);
            statusOrd = itemView.findViewById(R.id.statusOrd);
        }
    }
}