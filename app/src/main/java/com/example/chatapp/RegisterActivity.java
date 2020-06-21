package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashtable;

public class RegisterActivity extends AppCompatActivity {


    private static final String TAG = "RegisterActivity";
    private FirebaseAuth mAuth;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        findViewById(R.id.registerButton).setOnClickListener(onClickListener);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.registerButton:
                    signUp();
                    break;
            }
        }
    };


    private void signUp() {
        String email = ((EditText)findViewById(R.id.sign_email)).getText().toString();
        String password = ((EditText)findViewById(R.id.sign_password)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.Con_password)).getText().toString();
        if(email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0){
            if(password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                String uid = task.getResult().getUser().getUid();
                                if (task.isSuccessful()) {
                                    User userModel = new User();
                                    userModel.email = userModel.getEmail();
                                    userModel.uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    FirebaseDatabase.getInstance().getReference().child("users").child(uid).setValue(userModel);


                                    FirebaseUser user = mAuth.getCurrentUser();
//
                                    DatabaseReference myRef = database.getReference("users").child(user.getUid());
//                                    User userModel = new User();
                                    userModel.uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    Hashtable<String, String> numbers = new Hashtable<String, String>();
                                    numbers.put("email", user.getEmail());
                                    myRef.setValue(numbers);

//                                    startToast("Register complete.");
                                    Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_LONG).show();
                                RegisterActivity.this.finish();
                                } else {
                                    if(task.getException() != null) {
                                        startToast(task.getException().toString());
                                    }
                                }

                            }
                        });
            } else {
                startToast("Password error.");
            }
        } else {
            startToast("enter a email or password");
        }

    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}

