package app.comps456f;

import android.app.Fragment;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by hoyin on 10/1/2018.
 */

public class QuizPage extends AppCompatActivity{
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN = 30000;


    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_TIME_LEFT = "keyTimeLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView question_tv,score_tv,count_question_tv,timer_tv, diff_tv;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3,rb4;
    private Button confirm_btn;
    private ColorStateList txtColorDefault;
    private ColorStateList txtColorDefaultCount;
    private int quizCounter;
    private int questionTotal;
    private Questions currentQuestion;
    private int score;
    private boolean answered;

    private long backpresstime;
    private ArrayList<Questions> questionList;

    private CountDownTimer countTimer;
    private long timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        question_tv = (TextView) findViewById(R.id.questions_tv);
        score_tv = (TextView) findViewById(R.id.score_tv);
        count_question_tv = (TextView) findViewById(R.id.count_question_tv);
        timer_tv = (TextView) findViewById(R.id.timer_tv);
        radioGroup = (RadioGroup) findViewById(R.id.radio_grp);
        rb1 = (RadioButton) findViewById(R.id.radio_btn1);
        rb2 = (RadioButton) findViewById(R.id.radio_btn2);
        rb3 = (RadioButton) findViewById(R.id.radio_btn3);
        rb4 = (RadioButton) findViewById(R.id.radio_btn4);
        confirm_btn = (Button) findViewById(R.id.btn_confirm);
        diff_tv = (TextView)findViewById(R.id.difficulty);

        txtColorDefault = rb1.getTextColors();
        txtColorDefaultCount = timer_tv.getTextColors();

        Intent intent = getIntent();
        String difficulty = intent.getStringExtra(Quiz.EXTRA_DIFFICULTY);

        if(savedInstanceState == null) {
            QuizDb db = new QuizDb(this);
            questionList = db.getQuestions(difficulty);
            questionTotal = questionList.size();
            Collections.shuffle(questionList);

            showNext();
        }else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);

            questionTotal = questionList.size();
            quizCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(quizCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeft = savedInstanceState.getLong(KEY_TIME_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if(!answered){
                startCountDown();
            }else{
                updateCountDownTxt();
                showSolution();
            }
        }
        confirm_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkAns();
                    }
                    else{
                        Toast.makeText(QuizPage.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    showNext();
                }
            }
        });
    }



    private void showNext(){
        rb1.setTextColor(txtColorDefault);
        rb2.setTextColor(txtColorDefault);
        rb3.setTextColor(txtColorDefault);
        rb4.setTextColor(txtColorDefault);
        radioGroup.clearCheck();

        if(quizCounter < questionTotal){
            currentQuestion = questionList.get(quizCounter);
            question_tv.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            quizCounter++;
            count_question_tv.setText("Question: " + quizCounter + " / " + questionTotal);
            answered = false;
            confirm_btn.setText("Confirm");

            timeLeft = COUNTDOWN;
            startCountDown();
        }
        else {
            finishQuiz();
        }
    }

    private void startCountDown(){
        countTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateCountDownTxt();
            }

            @Override
            public void onFinish() {
                timeLeft = 0;
                updateCountDownTxt();
                checkAns();
            }
        }.start();
    }

    private void updateCountDownTxt(){
        int min = (int)(timeLeft/1000)/60;
        int sec = (int)(timeLeft/1000) % 60;

        String timeFormat = String.format(Locale.getDefault(),"%02d:%02d", min,sec);
        timer_tv.setText(timeFormat);

        if(timeLeft < 1000){
            timer_tv.setTextColor(Color.RED);
        }else{
            timer_tv.setTextColor(txtColorDefault);
        }
    }

    private void checkAns() {
        answered = true;
        countTimer.cancel();
        RadioButton rbSelected = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNr = radioGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnsno()) {
            score++;
            score_tv.setText("Score: " + score);
        }

        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);
        switch (currentQuestion.getAnsno()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                question_tv.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                question_tv.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                question_tv.setText("Answer 3 is correct");
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                question_tv.setText("Answer 4 is correct");
                break;
        }

        if (quizCounter < questionTotal) {
            confirm_btn.setText("Next");
        } else {
            confirm_btn.setText("Finish");
        }
    }


    private void finishQuiz(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE,score);
        setResult(RESULT_OK,resultIntent);
        finish();
    }
    @Override
    public void onBackPressed(){
        if(backpresstime + 2000 > System.currentTimeMillis()){
            finishQuiz();
        }
        else{
            Toast.makeText(this,"Press back again to finish",Toast.LENGTH_SHORT).show();
        }
        backpresstime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countTimer != null){
            countTimer.cancel();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE,score);
        outState.putInt(KEY_QUESTION_COUNT,quizCounter);
        outState.putLong(KEY_TIME_LEFT,timeLeft);
        outState.putBoolean(KEY_ANSWERED,answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);


    }
}

