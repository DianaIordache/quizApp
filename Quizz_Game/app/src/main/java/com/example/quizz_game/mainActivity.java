package com.example.quizz_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class mainActivity extends AppCompatActivity {

  private Button start;
  private Button quitGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainId), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        start=findViewById(R.id.Start);
        quitGame=findViewById(R.id.quitGame);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mainActivity.this,Quiz_Page.class);
                startActivity(i);

            }
        });
        quitGame.setOnClickListener(new View.OnClickListener() {
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