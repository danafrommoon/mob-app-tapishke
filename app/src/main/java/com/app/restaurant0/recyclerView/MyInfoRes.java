package com.app.restaurant0.recyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.restaurant0.R;
import com.app.restaurant0.activity.ReservationActivity;
import com.app.restaurant0.model.InfoRes;
import com.app.restaurant0.register.LoginActivity;
import com.app.restaurant0.register.RegisterActivity;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyInfoRes extends FirebaseRecyclerAdapter<InfoRes,MyInfoRes.myviewholder>
{
    public MyInfoRes(@NonNull FirebaseRecyclerOptions<InfoRes> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final InfoRes model) {
        holder.nameRes.setText(model.getNameRes());
        holder.phoneRes.setText(model.getPhoneRes());
        holder.streetRes.setText(model.getStreetRes());
        holder.priceRes.setText(model.getPriceRes());
        Glide.with(holder.imgRes.getContext()).load(model.getImgRes()).into(holder.imgRes);
//        holder.btnRes.
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_rest,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView imgRes;
        TextView nameRes,phoneRes,streetRes, priceRes;
        Button btnRes;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imgRes = itemView.findViewById(R.id.imgRes);
            nameRes = itemView.findViewById(R.id.nameRes);
            phoneRes = itemView.findViewById(R.id.phoneRes);
            streetRes = itemView.findViewById(R.id.streetRes);
            priceRes = itemView.findViewById(R.id.priceRes);
            btnRes = itemView.findViewById(R.id.btnRes);

            btnRes.setOnClickListener(v ->  {
                Intent mySuperIntent = new Intent(itemView.getContext(), ReservationActivity.class);
                mySuperIntent.putExtra("id", getAdapterPosition());
                ((FragmentActivity)itemView.getContext()).startActivity(mySuperIntent);
                ((FragmentActivity)itemView.getContext()).overridePendingTransition(R.anim.slidein, R.anim.slideout);
            });
        }
    }
}