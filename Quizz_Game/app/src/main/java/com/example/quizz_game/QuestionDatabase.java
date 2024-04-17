package com.example.quizz_game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class QuestionDatabase {

    private QuestionDatabaseHelper dbHelper;

    private Context context;


    private SQLiteDatabase database;

    public QuestionDatabase(Context c) {
        context = c;
    }

    public QuestionDatabase open() throws SQLException {
        dbHelper = new QuestionDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
//        delete(0);
//        delete(1);
        dbHelper.onUpgrade(database, 1, 2);
        addQuestions();
        return this;
    }
    public ArrayList<QuestionModel> getRandomQuestion(){

        ArrayList<QuestionModel>modelArrayList=new ArrayList<>();

        Cursor cursor= database.rawQuery("SELECT * FROM QuestionsTable ORDER BY RANDOM () LIMIT 10",null );
        int questionIdIndex= cursor.getColumnIndex("questionId");
        int questionNameIndex=cursor.getColumnIndex("questionName");
        int questionCorrectAnswerIndex=cursor.getColumnIndex("questionCorrectAnswer");
        int questionOptionAIndex=cursor.getColumnIndex("questionOptionA");
        int questionOptionBIndex=cursor.getColumnIndex("questionOptionB");
        int questionOptionCIndex=cursor.getColumnIndex("questionOptionC");
        int questionOptionDIndex=cursor.getColumnIndex("questionOptionD");
        while(cursor.moveToNext()){
            QuestionModel model=new QuestionModel(cursor.getInt(questionIdIndex),
                    cursor.getString(questionNameIndex),cursor.getString(questionCorrectAnswerIndex),
                    cursor.getString(questionOptionAIndex), cursor.getString(questionOptionBIndex), cursor.getString(questionOptionCIndex), cursor.getString(questionOptionDIndex));
            modelArrayList.add(model);
        }
        return modelArrayList;
    }


    public void insert(int id, String questionName, String correctAnswer, HashMap<String, String> options) {
        ContentValues contentValue = new ContentValues();
        contentValue.put("questionId", id);
        contentValue.put("questionName", questionName);
        contentValue.put("questionCorrectAnswer", correctAnswer);
        options.forEach((key, value) ->{
          contentValue.put(key, value);
        });
        database.insert(QuestionDatabaseHelper.TABLE_NAME, null, contentValue);
    }
    public void addQuestions()
    {
        HashMap<String, String> options = new HashMap<>();

        /* Intrebarea nr 1*/
        options.clear();
        options.put("questionOptionA", "Iepure");
        options.put("questionOptionB", "Șobolan");
        options.put("questionOptionC", "Pinguin");
        options.put("questionOptionD", "Tatu");
        insert(0, "1.\tCe animal are abilitatea de a amâna nașterea puilor săi cu pana la 2 ani?", "Tatu", options);

        /* Intrebarea nr 2*/
        options.clear();
        options.put("questionOptionA", "Italia");
        options.put("questionOptionB", "Anglia");
        options.put("questionOptionC", "Franța");
        options.put("questionOptionD", "Germania");
        insert(1, "Care este țara de origine a curentului literar realismul?\n", "Franța", options);


        /* Intrebarea nr 3*/
        options.clear();
        options.put("questionOptionA", "Gheorghe Hagi");
        options.put("questionOptionB", "Mike Tyson");
        options.put("questionOptionC", "Ilie Năstase");
        options.put("questionOptionD", "Muhammad Ali");
        insert(2, "Cine a fost primul sportiv profesionist sponsorizat de compania Nike?\n", "Ilie Năstase", options);

        /* Intrebarea nr 4*/
        options.clear();
        options.put("questionOptionA", "Ecuador");
        options.put("questionOptionB", "Greenwich");
        options.put("questionOptionC", "Zero");
        options.put("questionOptionD", "Mercator");
        insert(3, "Cum se numește meridianul care desparte emisfera nordică de emisfera sudica?\n", "Ecuador", options);

        /* Intrebarea nr 5*/
        options.clear();
        options.put("questionOptionA", "Oxigenul");
        options.put("questionOptionB", "Hidrogenul");
        options.put("questionOptionC", "Heliu");
        options.put("questionOptionD", "Siciliu");
        insert(4, "Care este cel mai abundent element chimic din Cosmos?\n", "Hidrogenul", options);

        /* Intrebarea nr 6*/
        options.clear();
        options.put("questionOptionA", "Volți");
        options.put("questionOptionB", "Amperi");
        options.put("questionOptionC", "Wați");
        options.put("questionOptionD", "Cai putere");
        insert(5, "Care este unitatea de măsură a intensitătii curentului electric?\n", "Amperi", options);

        /* Intrebarea nr 7*/
        options.clear();
        options.put("questionOptionA", "Vioară");
        options.put("questionOptionB", "Pian");
        options.put("questionOptionC", "Orgă");
        options.put("questionOptionD", "Bouzouki");
        insert(6, "La ce instrument cânta faimosul fizician Albert Einstein?\n", "Vioară", options);

        /* Intrebarea nr 8*/
        options.clear();
        options.put("questionOptionA", " Fritz Haber");
        options.put("questionOptionB", "Charles Pfizer");
        options.put("questionOptionC", "Nicolae Paulescu");
        options.put("questionOptionD", "Adrian Păunescu");
        insert(7, "Cine a inventat insulina?\n", "Nicolae Paulescu", options);

        /* Intrebarea nr 9*/
        options.clear();
        options.put("questionOptionA", "Generative Pre-training Transformer");
        options.put("questionOptionB", "General Pre-training Transformer");
        options.put("questionOptionC", "Generative Packing Transformer");
        options.put("questionOptionD", "Global Positioning Transformer");
        insert(8, "De la provine acronimul GPT în Chat-GPT\n", "Generative Pre-training Transformer", options);

        /* Intrebarea nr 10*/
        options.clear();
        options.put("questionOptionA", "Frica extremă de numere");
        options.put("questionOptionB", "Frica extremă de a rămane fără telefonul mobil");
        options.put("questionOptionC", "Frica extremă de gândaci");
        options.put("questionOptionD", "Frica extremă de a sta in singurătate");
        insert(9, "Ce reprezintă nomophobia?", "Frica extremă de a rămane fără telefonul mobil", options);

        /* Intrebarea nr 11*/
        options.clear();
        options.put("questionOptionA", "O afectiune cronica a inimii");
        options.put("questionOptionB", "Un cuvant/un grup de cuvinte/un numar care este egal cu inversul lui");
        options.put("questionOptionC", "Cuvânt asemănător cu altul din punctul de vedere al formei, dar deosebit de acesta ca sens ");
        options.put("questionOptionD", "Cuvânt asemănător ca sunete, dar deosebit ca sens de alt cuvânt, cu care în vorbire");
        insert(10, "Ce este un palindrom?\n", "Un cuvant/un grup de cuvinte/un numar care este egal cu inversul lui", options);

    }


    public void close() {
        dbHelper.close();
    }
    public void delete(int _id) {
        database.delete(QuestionDatabaseHelper.TABLE_NAME, "questionId = " + _id, null);
    }
}
