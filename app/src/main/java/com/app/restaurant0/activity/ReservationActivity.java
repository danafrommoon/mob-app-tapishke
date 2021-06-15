package com.app.restaurant0.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.restaurant0.R;
import com.app.restaurant0.model.InfoRes;
import com.app.restaurant0.model.InfoUser;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ReservationActivity extends AppCompatActivity {

    DatabaseReference reference, mRootRe;

    FirebaseUser user;
    ImageView imgReser;
    TextView nameReser, phoneReser, streetReser, priceReser, descriptionReser;
    EditText edTextDate, edTextGuest, edTablet;
    Button btnResOrd;

    String username, nameRes;

    InfoUser infoUser = new InfoUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        getSupportActionBar().hide();

        imgReser = findViewById(R.id.imgReser);
        nameReser = findViewById(R.id.nameReser);
        phoneReser = findViewById(R.id.phoneReser);
        streetReser = findViewById(R.id.streetReser);
        priceReser = findViewById(R.id.priceReser);
        descriptionReser = findViewById(R.id.descriptionReser);

        edTextDate = findViewById(R.id.edTextDate);
        edTextGuest = findViewById(R.id.edTextGuest);
        edTablet = findViewById(R.id.edTablet);

        btnResOrd = findViewById(R.id.btnResOrd);


        SimpleDateFormat dateF = new SimpleDateFormat("dd.MM.yyy" + " " + "HH:mm");
        String date = dateF.format(Calendar.getInstance().getTime());

        edTextDate.setText(date);

        // ======================================================================

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        mRootRe = FirebaseDatabase.getInstance().getReference().child("Order");

        InfoUser infoUser = new InfoUser();
        InfoRes infoRes = new InfoRes();
        username = infoUser.getUsername();
        nameRes = infoRes.getNameRes();

        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "");
        map.put("restOrd", "");
        map.put("tabletNumOrd", "");
        map.put("guestsOrd", "");
//
        map.put("timeOrd", edTextDate.getText().toString());
        map.put("statusOrd", "Оплачено");

        btnResOrd.setOnClickListener(v -> {
            mRootRe.child(String.valueOf(id + 1)).setValue(map);
            Toast.makeText(ReservationActivity.this, "Успешно", Toast.LENGTH_SHORT).show();
        });

        // ======================================================================


        user = FirebaseAuth.getInstance().getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("Reservation").child(String.valueOf(id + 1));

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                InfoRes infoRes = snapshot.getValue(InfoRes.class);
                Log.d("InfoRes", infoRes.getNameRes());
                Log.d("InfoRes", infoRes.getPhoneRes());
                Log.d("InfoRes", infoRes.getStreetRes());
                Log.d("InfoRes", infoRes.getPriceRes());
                Log.d("InfoRes", infoRes.getDescriptionRes());

                nameReser.setText(infoRes.getNameRes());
                phoneReser.setText(infoRes.getPhoneRes());
                streetReser.setText(infoRes.getStreetRes());
                priceReser.setText(infoRes.getPriceRes());
                descriptionReser.setText(infoRes.getDescriptionRes());
                Glide.with(imgReser.getContext()).load(infoRes.getImgRes()).into(imgReser);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}