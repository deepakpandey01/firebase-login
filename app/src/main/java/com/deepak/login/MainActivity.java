package com.deepak.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    EditText emailId,pwd;
    Button btnSignUp,btnLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        
        emailId =findViewById(R.id.email);
        pwd=findViewById(R.id.editTextTextPassword);
        btnLogin=findViewById(R.id.button2);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });



        btnSignUp=findViewById(R.id.button);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailId.getText().toString();
                String password=pwd.getText().toString();
                if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please Enter values in Field",Toast.LENGTH_SHORT);
                }
                else if(email.isEmpty()){
                    emailId.setError("Provide Email");
                    emailId.requestFocus();
                }
                else if(password.isEmpty()){
                    pwd.setError("Provide Valid password");
                    pwd.requestFocus();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(MainActivity.this,homeActivity.class));

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(MainActivity.this,"Auth Failed",Toast.LENGTH_SHORT);

                                    }

                                    // ...
                                }
                            });
                }
            }
        });
    }
}