package com.example.quizz_game;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class Quiz_Page extends AppCompatActivity {
    TextView time, correct, wrong, empty, timeCounter, correctIndex, wrongIndex, question;
    Button a,b,c,d;
    Button next,finish ;
    int correctCount=0;
    Boolean timerOn = false;
    int wrongCount=0;
    int emptyCount=0;
    int questionCount=0;
    CountDownTimer countDownTimer;
    private static final long TOTAL_TIME=25000;

    long timeLeft=TOTAL_TIME;

    private QuestionDatabase qdbDatabase;
    private QuestionModel questionData;
    private ArrayList<QuestionModel>questionModelArrayList;

    boolean buttonControl=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        time=findViewById(R.id.textTime);
        timeCounter=findViewById(R.id.timeView60s);
        correct=findViewById(R.id.textCorrect);
        correctIndex=findViewById(R.id.timeViewCorrect);
        wrong=findViewById(R.id.textWrong);
        wrongIndex=findViewById(R.id.timeViewWrong);
        empty=findViewById(R.id.emptyQuiz);

        question=findViewById(R.id.Question);
        a=findViewById(R.id.A);
        b=findViewById(R.id.B);
        c=findViewById(R.id.C);
        d=findViewById(R.id.D);

        next=findViewById(R.id.buttonNext);
        finish=findViewById(R.id.buttonFinish);


        qdbDatabase=new QuestionDatabase(Quiz_Page.this);

            try {
                qdbDatabase.open();
            } catch (SQLException e) {
                Log.d("EROARE DESCHIDERE BAZA", "eroare la deschiderea bazei de date");
            }


        questionModelArrayList=qdbDatabase.getRandomQuestion();

        loadQuestions();

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopTimer();
                answerControl(a);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();

                answerControl(b);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopTimer();
                answerControl(c);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
                answerControl(d);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                   if (!buttonControl && questionCount < questionModelArrayList.size()) {
                       emptyCount++;
                       loadQuestions();

                   }else if (buttonControl&&questionCount<questionModelArrayList.size()){
                       loadQuestions();
                       a.setBackgroundColor(Color.rgb(251, 192, 45));
                       b.setBackgroundColor(Color.rgb(251, 192, 45));
                       c.setBackgroundColor(Color.rgb(251, 192, 45));
                       d.setBackgroundColor(Color.rgb(251, 192, 45));

                       a.setClickable(true);
                       b.setClickable(true);
                       c.setClickable(true);
                       d.setClickable(true);

                   }else if(questionCount==questionModelArrayList.size()){
                       Intent i=new Intent(Quiz_Page.this,ResultActivity.class);
                       i.putExtra("correct: ",correctCount);
                       i.putExtra("wrong: ",wrongCount);
                       i.putExtra("empty: ",emptyCount);
                       qdbDatabase.close();
                       startActivity(i);
                       finish();
               }
                   buttonControl=false;

            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                stopTimer();
                qdbDatabase.close();
                Intent i=new Intent(Quiz_Page.this,ResultActivity.class);
                i.putExtra("correct: ",correctCount);
                i.putExtra("wrong: ",wrongCount);
                i.putExtra("empty: ",emptyCount);
                startActivity(i);
                finish();
            }
        });

    }
    public void loadQuestions() {
        stopTimer();
        resetTimer();
        startTimer();
        questionData = questionModelArrayList.get(questionCount);
        question.setText(questionData.getQuestionName());

        ArrayList<String> mixedOptions=new ArrayList<>();
        mixedOptions.add(questionData.getQuestionOptionA());
        mixedOptions.add(questionData.getQuestionOptionB());
        mixedOptions.add(questionData.getQuestionOptionC());
        mixedOptions.add(questionData.getQuestionOptionD());
        Collections.shuffle(mixedOptions);


        a.setText(mixedOptions.get(0));
        b.setText(mixedOptions.get(1));
        c.setText(mixedOptions.get(2));
        d.setText(mixedOptions.get(3));

        questionCount++;
    }

    public void answerControl(Button button){
        String buttonText= button.getText().toString();
        String correctAnswer=questionData.getQuestionCorrectAnswer();
        if(buttonText.equals(correctAnswer)){
            correctCount++;
            button.setBackgroundColor(Color.GREEN);
        }else{
            wrongCount++;
            button.setBackgroundColor(Color.RED);

            if(a.getText().equals(correctAnswer)) {
                a.setBackgroundColor(Color.GREEN);
            }
            if(b.getText().equals(correctAnswer)) {
                b.setBackgroundColor(Color.GREEN);
            }
            if(c.getText().equals(correctAnswer)) {
                c.setBackgroundColor(Color.GREEN);
            }
            if(d.getText().equals(correctAnswer)) {
                d.setBackgroundColor(Color.GREEN);
            }

        }
        a.setClickable(false);
        b.setClickable(false);
        c.setClickable(false);
        d.setClickable(false);

        correctIndex.setText( String.valueOf(correctCount));
        wrongIndex.setText(String.valueOf(wrongCount));
        buttonControl=true;

    }

    public void startTimer(){

        countDownTimer=new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeLeft=millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                question.setText("Sorry, Time is up!!!!");
                timerOn = false;
                String correctAnswer=questionData.getQuestionCorrectAnswer();
                if(a.getText().equals(correctAnswer)) {
                    a.setBackgroundColor(Color.GREEN);
                }
                if(b.getText().equals(correctAnswer)) {
                    b.setBackgroundColor(Color.GREEN);
                }
                if(c.getText().equals(correctAnswer)) {
                    c.setBackgroundColor(Color.GREEN);
                }
                if(d.getText().equals(correctAnswer)) {
                    d.setBackgroundColor(Color.GREEN);
                }
                emptyCount++;
            }
        }.start();
        timerOn = true;
    }


    public void resetTimer(){
        timeLeft=TOTAL_TIME;
        updateCountDownText();

    }

    public void updateCountDownText(){

        int second=(int)(timeLeft/1000)%60;
        timeCounter.setText(String.valueOf(second));

    }

    public void stopTimer()
    {
        if (timerOn)
            countDownTimer.cancel();
    }


}