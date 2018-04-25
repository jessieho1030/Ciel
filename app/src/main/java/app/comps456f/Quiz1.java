package app.comps456f;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by hoyin on 31/3/2018.
 */

public class Quiz1 extends AppCompatActivity {
    public static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCROE1 = "keyHighScore1";

    private DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uID= user.getUid();

    private TextView highscore_tv;
    private int HighScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial1);
        highscore_tv = (TextView) findViewById(R.id.text_view_highscore);
        loadHighScore();
        Button btn_quiz = (Button) findViewById(R.id.button_start_quiz);
        btn_quiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startQuiz1();
            }
        });


    }

    public void startQuiz1(){

        Intent quiz1intent = new Intent(this,QuizPage.class);
        quiz1intent.getStringExtra(Quiz.IMAGE_VISIBLE);
        quiz1intent.getStringExtra(Quiz.EXTRA_DIFFICULTY);
        quiz1intent.putExtra(Quiz.EXTRA_DIFFICULTY,"Quiz1");
        quiz1intent.putExtra(Quiz.IMAGE_VISIBLE, 1);

        startActivityForResult(quiz1intent, REQUEST_CODE_QUIZ);
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
                else{
                    mdatabase.child("user").child(uID).child("quiz").child("1").child("score").setValue(HighScore);//store score in firebase
                }
            }
        }
    }

    private void loadHighScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        HighScore = prefs.getInt(KEY_HIGHSCROE1, 0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Calendar c = Calendar.getInstance();
        String rightNow = df.format(c.getTime());
        mdatabase.child("user").child(uID).child("quiz").child("1").child("datetime").setValue(rightNow); //store datetime in firebase
        highscore_tv.setText("Highest Score:   " + HighScore);
    }

    private void updateHighscore(int highScorenew){
        HighScore = highScorenew;
        highscore_tv.setText("Highest Score:   " + HighScore);
        mdatabase.child("user").child(uID).child("quiz").child("1").child("score").setValue(HighScore);//update score in firebase
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Calendar c = Calendar.getInstance();
        String rightNow = df.format(c.getTime());
        mdatabase.child("user").child(uID).child("quiz").child("1").child("datetime").setValue(rightNow); //update datetime in firebase
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCROE1, HighScore);
        editor.apply();
    }

}