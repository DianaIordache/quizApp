package com.example.quizz_game;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView correctAnswers, wrongAnswers, emptyAnswers,succesRate;
    private Button again, quit;
    int correct, wrong,empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.result_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.result), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        correctAnswers=findViewById(R.id.CorrectAnswer);
        wrongAnswers=findViewById(R.id.wrongAnswer);
        emptyAnswers=findViewById(R.id.EmptyAnswer);
        succesRate=findViewById(R.id.Succes);
        again=findViewById(R.id.again);
        quit=findViewById(R.id.quit);

        correct=getIntent().getIntExtra("correct: ",0);
        wrong=getIntent().getIntExtra("wrong: ",0);
        empty=getIntent().getIntExtra("empty: ",0);

        correctAnswers.setText("Total Correct Answers: "+correct);
        wrongAnswers.setText("Total Wrongs Answers: "+wrong);
        emptyAnswers.setText("Total Empty Answers: "+empty);
        succesRate.setText("Success Rate: "+correct*10);


        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i=new Intent(ResultActivity.this,mainActivity.class);
            startActivity(i);
            finish();
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent=new Intent(Intent.ACTION_MAIN);
                newIntent.addCategory(Intent.CATEGORY_HOME);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(newIntent);
                finish();

            }
        });

    }

    }
