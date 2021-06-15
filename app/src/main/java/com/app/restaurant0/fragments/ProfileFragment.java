package com.app.restaurant0.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.restaurant0.R;
import com.app.restaurant0.activity.RenameActivity;
import com.app.restaurant0.activity.ReservationActivity;
import com.app.restaurant0.model.InfoRes;
import com.app.restaurant0.model.InfoUser;
import com.app.restaurant0.model.Order;
import com.app.restaurant0.recyclerView.MyInfoOrder;
import com.app.restaurant0.recyclerView.MyInfoRes;
import com.app.restaurant0.register.LoginActivity;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    private FirebaseAuth mAuth;

    View vista;
    ImageView logOut;
    Button rename, btnRes;
    Intent i;
    RecyclerView recview;
    MyInfoOrder adapter;

    DatabaseReference reference;
    FirebaseUser user;
    ImageView imageView;
    TextView username, phone, email;
    CardView info;
    public static final int CAMERA_CODE = 200;
    public static final int GALLERY_CODE = 100;
    Uri imageUri = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        logOut = view.findViewById(R.id.logOut);
        rename = view.findViewById(R.id.rename);

        Permissions(); // for permissions

        imageView = view.findViewById(R.id.imageView4);
        username = view.findViewById(R.id.textView2);
        phone = view.findViewById(R.id.textView3);
        email = view.findViewById(R.id.textView4);
        btnRes = view.findViewById(R.id.btnRes);


        // ======================================================================

        recview = view.findViewById(R.id.recyclerViewOrder);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Order> options =
                new FirebaseRecyclerOptions.Builder<Order>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Order"), Order.class)
                        .build();

        adapter = new MyInfoOrder(options);
        recview.setAdapter(adapter);

        // ======================================================================


        user = FirebaseAuth.getInstance().getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                InfoUser infoUser = snapshot.getValue(InfoUser.class);

                username.setText(infoUser.getUsername());
                phone.setText(infoUser.getPhone());
                email.setText(infoUser.getEmail());

                if (infoUser.getImageurl().equals("default")) {
                    imageView.setImageResource(R.drawable.ic_baseline_person_24);
                } else {
                    // change this to
                    // Glide.with(getActivity().getApplicationContext()).load(users.getImageURL()).into(imageView);
                    Glide.with(getActivity().getApplicationContext()).load(infoUser.getImageurl()).into(imageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        rename.setOnClickListener(v -> {
            i = new Intent(getActivity(), RenameActivity.class);
            startActivity(i);
            getActivity().overridePendingTransition(R.anim.slidein, R.anim.slideout);
        });

        logOut.setOnClickListener(v -> {
            i = new Intent(getActivity(), LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            getActivity().overridePendingTransition(R.anim.slidein, R.anim.slideout);
            FirebaseAuth.getInstance().signOut();
        });

        return view;
    }

    private void openGallery() {
        Intent intent = new Intent (Intent.ACTION_PICK);
        intent.setType("image/*"); // all kinds of images
        startActivityForResult(intent, GALLERY_CODE);
    }

    private void openCamera() {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Temp Pick");
        values.put(MediaStore.Images.Media.TITLE, "Temp Desc");
        imageUri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CAMERA_CODE);
    }


    private void Permissions() {


        Dexter.withContext(getContext())
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
            }
            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

            }

        }).check();



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {

            imageUri = data.getData();

            String filepath = "Photos/" + "userprofile_" + user.getUid();

            StorageReference reference = FirebaseStorage.getInstance().getReference(filepath);
            reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();

                    task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String imageURL = uri.toString();

                            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("imageURL", imageURL);
                            reference1.updateChildren(hashMap);


                        }
                    });


                }
            });
        }


        if (requestCode == CAMERA_CODE && resultCode == RESULT_OK) {

            Uri uri = imageUri;

            String filepath = "Photos/" + "userprofile_" + user.getUid();

            StorageReference reference = FirebaseStorage.getInstance().getReference(filepath);
            reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();

                    task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String imageURL = uri.toString();

                            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("imageURL", imageURL);
                            reference1.updateChildren(hashMap);


                        }
                    });
                }
            });
        }
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
}