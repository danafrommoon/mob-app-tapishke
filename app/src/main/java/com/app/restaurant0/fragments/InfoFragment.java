package com.app.restaurant0.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.restaurant0.R;

public class InfoFragment extends Fragment {

    View vista;
    TextView help;
    ImageView help1;
    Intent i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_info, container, false);
        help = vista.findViewById(R.id.help);
        help1 = vista.findViewById(R.id.help1);

        help.setOnClickListener(v -> {
            String url = "https://api.whatsapp.com/send/?phone=77006611707";
            Intent openPage= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(openPage);
            getActivity().overridePendingTransition(R.anim.slidein, R.anim.slideout);
        });
        help1.setOnClickListener(v -> {
            String url = "https://api.whatsapp.com/send/?phone=77006611707";
            Intent openPage= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(openPage);
            getActivity().overridePendingTransition(R.anim.slidein, R.anim.slideout);
        });
        return vista;
    }
}