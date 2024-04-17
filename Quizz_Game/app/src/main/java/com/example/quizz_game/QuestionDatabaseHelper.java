package com.example.quizz_game;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class QuestionDatabaseHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "Questions_database.db";
    static final String TABLE_NAME = "QuestionsTable";

    private final Context currentContext;

    public QuestionDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        currentContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "  (\n"+
                "\t\"questionId\"\t	INTEGER,\n"+
                "\t\"questionName\"\t	TEXT,\n"+
                "\t\"questionCorrectAnswer\"\t	TEXT,\n"+
                "\t\"questionOptionA\"\t		TEXT,\n"+
                "\t\"questionOptionB\"\t	TEXT,\n"+
                "\t\"questionOptionC\"\tTEXT,\n"+
                "\t\"questionOptionD\"\t	TEXT\n"+
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE IF EXISTS QuestionsTable ");
     onCreate(db);
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
