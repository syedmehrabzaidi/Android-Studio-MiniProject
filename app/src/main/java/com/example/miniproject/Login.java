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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    FirebaseAuth Auth;
    EditText emailE, passE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Auth = FirebaseAuth.getInstance();
        emailE =findViewById(R.id.L_email);
        passE =findViewById(R.id.L_pass);

    }

    public void LoginMethod(View view) {
      String  email =emailE.getText().toString();
        String   pass =passE.getText().toString();

        if(email.isEmpty()){
            emailE.setError("email Requird");
        }else if(pass.isEmpty()){
            passE.setError("Required Password");
        }else {

            Method(email,pass);

        }

    }


        public void Method(String email,String pass) {

        Auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i =new Intent(Login.this,MainActivity.class);
                            startActivity(i);
                        } else {

                            Toast.makeText(Login.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });


    }

    public void Sign(View view) {
        Intent i =new Intent(Login.this,Signup.class);
        startActivity(i);
    }
}
