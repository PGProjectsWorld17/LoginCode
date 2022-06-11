package com.example.logindatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    TextView textView;
    EditText email, pass;
    Button button;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textView = findViewById(R.id.name);
        email = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextNumberPassword);
        button = findViewById(R.id.button2);
        auth = FirebaseAuth.getInstance();
        Intent intent = this.getIntent();
        textView.setText("Welcome "+intent.getStringExtra("name"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(email.getText().toString()))
                {
                    email.setError("Email is required.");
                }
                if(TextUtils.isEmpty(pass.getText().toString()))
                {
                    pass.setError("Password is required.");
                }
                if(pass.getText().toString().length()<4)
                {
                    pass.setError("Password must be 4 digits.");
                }
                String E_mail = email.getText().toString();
                String password = pass.getText().toString();
                auth.createUserWithEmailAndPassword(E_mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Register.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                email.setText("");
//                pass.setText("");
            }
        });
    }
}