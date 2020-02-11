package com.firas.project.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firas.project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Authentification_Act extends AppCompatActivity {
    private EditText edmail ,edpass;
    private Button signinbutton ,sigoutbuttom;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification_);
        getSupportActionBar().hide();
        edmail=(EditText)findViewById(R.id.Edmail);
        edpass=(EditText) findViewById(R.id.Edpassword);
        sigoutbuttom=(Button) findViewById(R.id.sign_out_button);
        signinbutton=(Button) findViewById(R.id.sign_in_button);
        auth=FirebaseAuth.getInstance();
        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = edmail.getText().toString();
                final String password = edpass.getText().toString();
                if (TextUtils.isEmpty(mail)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(Authentification_Act.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        edpass.setError("Password too short, enter minimum 6 characters!");
                                    } else {
                                        Toast.makeText(Authentification_Act.this, "Authentication failed, check your email and password or sign up", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(Authentification_Act.this, Emission_Act.class);
                                    startActivity(intent);
                                   //*finish();
                                }


                            }
                        });


            }
        });
    }
}