package com.example.quizz_game;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Sign_Up_Page extends AppCompatActivity {

    EditText mailSignUp;
    EditText passwordSignUp;
    Button signUpPage;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mailSignUp=findViewById(R.id.editTextSignUpEmail);
        passwordSignUp=findViewById(R.id.editTextSignUpPassword);
        signUpPage=findViewById(R.id.SignUpPage);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        signUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpPage.setClickable(false);
                String userEmail= mailSignUp.getText().toString();
                String userPassword= passwordSignUp.getText().toString();
                signUpMember(userEmail,userPassword);


            }
        });

    }
    public void signUpMember(String userEmail, String userPassword){
     progressBar.setVisibility(View.VISIBLE);


    }
}