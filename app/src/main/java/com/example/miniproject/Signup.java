package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    FirebaseAuth Auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    EditText nameE, emailE,passE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();

        nameE=findViewById(R.id.S_name);
        emailE=findViewById(R.id.S_email);
        passE=findViewById(R.id.S_pass);

    }

    public void save(View view) {
      final    String name = nameE.getText().toString();
        final    String email = emailE.getText().toString();
        final    String pass = passE.getText().toString();

        if(name.isEmpty()){
            nameE.setError("name Requird");
        }else
        if(email.isEmpty()){
            emailE.setError("email Requird");
        }else if(pass.isEmpty()){
            passE.setError("Required Password");
        }else {



        Auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(Signup.this,"before if", Toast.LENGTH_SHORT).show();

                if (task.isSuccessful()){
                    Toast.makeText(Signup.this,"after if", Toast.LENGTH_SHORT).show();

                    String key = Auth.getCurrentUser().getUid();

                    User user=new User(email,pass,name);
                    reference.child(key).setValue(user);
                                Intent intent = new Intent(Signup.this,Login.class);

                                startActivity(intent);}


                 else {
                    Toast.makeText(Signup.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    }

    }




