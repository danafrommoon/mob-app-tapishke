package com.app.restaurant0.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.restaurant0.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.annotations.NotNull;


public class MyAdapter extends FirebaseRecyclerAdapter<Model, MyAdapter.myviewholder> {



    public MyAdapter(@NonNull FirebaseRecyclerOptions<Model> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull myviewholder holder, int i, @NonNull Model Model) {

        holder.name.setText(Model.getName());
        holder.feedback.setText(Model.getName());
    }


    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView name, feedback;
        public myviewholder(@NonNull @NotNull View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.name);
            feedback = itemView.findViewById(R.id.feedback);

        }
    }

}
