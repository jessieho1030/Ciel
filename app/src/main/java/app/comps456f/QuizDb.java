package app.comps456f;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import app.comps456f.QuizContract.*;
/**
 * Created by hoyin on 31/3/2018.
 */

public class QuizDb extends SQLiteOpenHelper {
    private static final  String DATABASE_NAME = "Quiz.db";
    private static final  int DATABASE_VERSION = 5;

    private SQLiteDatabase db;

    public QuizDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE "+
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NO + " INTEGER," +
                QuestionTable.COLUMN_DIFFICULTY + " TEXT" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL( "DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionTable(){
        Questions q1 = new Questions("What does SQL stand for?", "Structured Query Language", "Structured Queue Language"
                , "Short Query Language","None of above",1 , Questions.DIFFICULTY_Q0);
        addQuestion(q1);
        Questions q2 = new Questions("How to select columns Name and ID from table “Student” in SQL", "SELECT ALL FROM Student", "SELECT Name, ID IN Student",
                "SELECT Name, ID FROM Student","FROM Student SELECT Name, ID ",3, Questions.DIFFICULTY_Q0);
        addQuestion(q2);
        Questions q3 = new Questions("DML does NOT include which statement below?", "INSERT", "UPDATE",
                "DELETE","CREATE",4, Questions.DIFFICULTY_Q0);
        addQuestion(q3);
        Questions q4 = new Questions("Which statement is used to insert record?", "CREATE", "INSERT INTO",
                "DROP","SELECT",2, Questions.DIFFICULTY_Q0);
        addQuestion(q4);
        Questions q5 = new Questions("Which operator is used to search for a specified pattern?", "IN", "LIKE",
                "BETWEEN","None of above",1, Questions.DIFFICULTY_Q0);
        addQuestion(q5);
        Questions q6 = new Questions("How to drop a table named “Course”?", "DROP Course", "DELETE Table Course",
                "DROP TABLE Course","DELETE Course",3, Questions.DIFFICULTY_Q0);
        addQuestion(q6);
        Questions q7 = new Questions("Which statement is used to modify the existing records?", "UPDATE", "CREATE TABLE",
                "DROP TABLE","INSERT INTO",1, Questions.DIFFICULTY_Q0);
        addQuestion(q7);
        Questions q8 = new Questions("Which statement is used to add, delete, or modify columns in an existing table?", "CREATE TABLE",
                "DROP TABLE", "INSERT INTO","ALTER TABLE",4, Questions.DIFFICULTY_Q0);
        addQuestion(q8);
        Questions q9 = new Questions("test", "A",
                "B", "C","D",4, Questions.DIFFICULTY_Q1);
        addQuestion(q9);
        Questions q10 = new Questions("test2", "A",
                "B", "C","D",2, Questions.DIFFICULTY_Q1);
        addQuestion(q10);
        Questions q11 = new Questions("test3", "A",
                "B", "C","D",3, Questions.DIFFICULTY_Q2);
        addQuestion(q11);
        Questions q12 = new Questions("test4", "A",
                "B", "C","D",1, Questions.DIFFICULTY_Q2);
        addQuestion(q12);
        Questions q13 = new Questions("test5", "A",
                "B", "C","D",2, Questions.DIFFICULTY_Q3);
        addQuestion(q13);
        Questions q14 = new Questions("test6", "A",
                "B", "C","D",2, Questions.DIFFICULTY_Q3);
        addQuestion(q14);


    }
    private void addQuestion(Questions question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NO, question.getAnsno());
        cv.put(QuestionTable.COLUMN_DIFFICULTY, question.getDifficulty() );
        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Questions> getAllQuestions() {
        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnsno(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NO)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                questionList.add(question);

            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<Questions> getQuestions(String difficulty) {
        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME +
                " WHERE " + QuestionTable.COLUMN_DIFFICULTY + " =  ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnsno(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NO)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                questionList.add(question);

            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

}
