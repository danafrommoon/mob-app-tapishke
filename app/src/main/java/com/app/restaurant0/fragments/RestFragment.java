package com.app.restaurant0.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.restaurant0.R;
import com.app.restaurant0.activity.ReservationActivity;
import com.app.restaurant0.model.InfoRes;
import com.app.restaurant0.model.MyAdapter;
import com.app.restaurant0.recyclerView.MyInfoRes;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RestFragment extends Fragment implements View.OnClickListener{

    RecyclerView recview;
    MyInfoRes adapter;
    Button btnRes;
    Intent i;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static RestFragment newInstance(String param1, String param2) {
        RestFragment fragment = new RestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RestFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rest, container, false);

        recview = view.findViewById(R.id.recyclerViewRest);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<InfoRes> options =
                new FirebaseRecyclerOptions.Builder<InfoRes>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Reservation"), InfoRes.class)
                        .build();
        adapter=new MyInfoRes(options);
        recview.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onClick(View v) {
        
    }
}