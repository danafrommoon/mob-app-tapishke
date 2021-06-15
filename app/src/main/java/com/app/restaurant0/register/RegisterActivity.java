package com.app.restaurant0.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.restaurant0.MainActivity;
import com.app.restaurant0.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText phone;
    private EditText email;
    private EditText password;
    private Button register;
    private TextView loginUser;


    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        username = findViewById(R.id.nameRe);
        phone = findViewById(R.id.phoneRe);
        email = findViewById(R.id.emailRe);
        password = findViewById(R.id.passRe);
        register = findViewById(R.id.renameBtn);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);


        register.setOnClickListener(v -> {
            String txtUsername = username.getText().toString();
            String txtName = phone.getText().toString();
            String txtEmail = email.getText().toString();
            String txtPassword = password.getText().toString();

            if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtName)
                    || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)){
                Toast.makeText(RegisterActivity.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
            } else if (txtPassword.length() < 6){
                Toast.makeText(RegisterActivity.this, "Password too short!", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(txtUsername , txtName , txtEmail , txtPassword);
            }
        });
    }

    private void registerUser(final String username, final String name, final String phone, String password) {

        pd.setMessage("Please Wail!");
        pd.show();

        mAuth.createUserWithEmailAndPassword(phone , password).addOnSuccessListener(authResult -> {

            HashMap<String , Object> map = new HashMap<>();
            map.put("phone" , name);
            map.put("email", phone);
            map.put("username" , username);
            map.put("id" , mAuth.getCurrentUser().getUid());
            map.put("info" , "");
            map.put("imageurl" , "default");

            mRootRef.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    pd.dismiss();
                    Toast.makeText(RegisterActivity.this, "Update the profile " +
                            "for better expereince", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this , MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            });
        }).addOnFailureListener(e -> {
            pd.dismiss();
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        });

    }
}
