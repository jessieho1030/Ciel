package app.comps456f;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hoyin on 31/3/2018.
 */

public class Quiz3 extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCROE = "keyHighScore";

    String[] difficultyLevels = Questions.getAllDifficulty();

    private TextView highscore_tv;
    private int HighScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial3);
        highscore_tv = (TextView) findViewById(R.id.text_view_highscore);
        loadHighScore();
        Button btn_quiz = (Button) findViewById(R.id.button_start_quiz);
        btn_quiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startQuiz3();
            }
        });


    }

    public void startQuiz3(){
        Intent quiz3intent = new Intent(this,QuizPage.class);
        quiz3intent.getStringExtra(Quiz.EXTRA_DIFFICULTY);
        quiz3intent.putExtra(Quiz.EXTRA_DIFFICULTY,"Quiz3");
        startActivityForResult(quiz3intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == REQUEST_CODE_QUIZ){
            if(resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizPage.EXTRA_SCORE,0);
                if(score > HighScore){
                    updateHighscore(score);
                }
            }
        }
    }

    private void loadHighScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        HighScore = prefs.getInt(KEY_HIGHSCROE, 0);
        highscore_tv.setText("High Score:   " + HighScore);
    }

    private void updateHighscore(int highScorenew){
        HighScore = highScorenew;
        highscore_tv.setText("High Score:   " + HighScore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCROE, HighScore);
        editor.apply();
    }


}