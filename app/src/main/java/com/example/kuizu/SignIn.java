package com.example.kuizu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        TextView text_btn_login;
        Button btn_register;
        EditText edit_username;
        EditText editTextTextEmailAddress1;
        EditText editTextTextPassword1;
        EditText editTextTextPassword2;
        FirebaseAuth mAuth;

        edit_username = findViewById(R.id.edit_username);
        editTextTextPassword1 = findViewById(R.id.editTextTextPassword1);
        editTextTextEmailAddress1 = findViewById(R.id.editTextTextEmailAddress1);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        text_btn_login = findViewById(R.id.text_btn_login);
        btn_register = findViewById(R.id.btn_register);
        mAuth = FirebaseAuth.getInstance();
        text_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(SignIn.this, SignUp.class);
                startActivity(log);
                finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, conf_password, username;
                username = edit_username.getText().toString();
                password = editTextTextPassword1.getText().toString();
                conf_password = editTextTextPassword2.getText().toString();
                email = editTextTextEmailAddress1.getText().toString();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(SignIn.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(SignIn.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(username))
                {
                    Toast.makeText(SignIn.this, "Enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(conf_password))
                {
                    Toast.makeText(SignIn.this, "Re-enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(SignIn.this, "Account Created",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(SignIn.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
}