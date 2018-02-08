package app.comps456f;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Quiz extends Fragment implements View.OnClickListener{
    TextView tv1, tv2, tv3;
    RadioButton a, b, c;
    Button bt;
    RadioGroup rg;
    int question, score, questionno;
    View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_quiz, container, false);

        init();
        return view;

    }

    public void init(){
        tv1 = (TextView) view.findViewById(R.id.question);
        tv2 = (TextView) view.findViewById(R.id.response);
        tv3 = (TextView) view.findViewById(R.id.score);
        rg = (RadioGroup) view.findViewById(R.id.optionGroup);
        a = (RadioButton) view.findViewById(R.id.choice1);
        b = (RadioButton) view.findViewById(R.id.choice2);
        c = (RadioButton) view.findViewById(R.id.choice3);
        bt = (Button) view.findViewById(R.id.next);
        question = 0;
        score = 0;
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (question) {
            case 0: {
                rg.setVisibility(View.VISIBLE);
                a.setChecked(false);
                b.setChecked(false);
                c.setChecked(false);
                a.setEnabled(true);
                b.setEnabled(true);
                c.setEnabled(true);
                bt.setText("Submit");
                score = 0;

                tv1.setText("1. Select the code which shows players, their team and the amount of goals they scored against Japan(JPN)");
                a.setText("A. SELECT player, teamid, COUNT(*) FROM game JOIN goal ON matchid = id WHERE (team1 = 'JPN') AND teamid != 'GRE' GROUP BY player, teamid");
                b.setText("B.  SELECT player, teamid, COUNT(*) FROM game JOIN goal ON matchid = id WHERE (team1 = 'JPN' OR team2 = 'JPN') AND teamid != 'GRE' GROUP BY player, teamid");
                c.setText("C. SELECT player, teamid FROM game JOIN goal ON matchid = id WHERE (team1 = 'JPN' OR team2 = 'JPN') AND teamid != 'JPN' GROUP BY player, teamid");
                tv2.setText("");
                tv3.setText("");
                question = 1;
                break;
            }
            case 1: {
                a.setEnabled(false);
                b.setEnabled(false);
                c.setEnabled(false);
                bt.setText("Next Question");
                tv1.setText("1. Select the code which shows players, their team and the amount of goals they scored against Japan(JPN)");
                a.setText("A. SELECT player, teamid, COUNT(*) FROM game JOIN goal ON matchid = id WHERE (team1 = 'JPN') AND teamid != 'GRE' GROUP BY player, teamid");
                b.setText("B.  SELECT player, teamid, COUNT(*) FROM game JOIN goal ON matchid = id WHERE (team1 = 'JPN' OR team2 = 'JPN') AND teamid != 'GRE' GROUP BY player, teamid");
                c.setText("C. SELECT player, teamid FROM game JOIN goal ON matchid = id WHERE (team1 = 'JPN' OR team2 = 'JPN') AND teamid != 'JPN' GROUP BY player, teamid");
                if (b.isChecked()) {
                    tv2.setText("Right Answer");
                    score += 1;
                } else {
                    tv2.setText("Wrong Answer   B was Right Answer");
                }
                a.setChecked(false);
                b.setChecked(false);
                c.setChecked(false);
                question=2;
                break;
            }
            case 2:{
                a.setEnabled(true);
                b.setEnabled(true);
                c.setEnabled(true);
                bt.setText("Submit");

                tv1.setText("2.How many tables may be included with a join?");
                a.setText("A. 1");
                b.setText("B. 2");
                c.setText("C.All of the above");
                tv2.setText("");
                tv3.setText("");
                question = 3;
                break;
            }
            case 3: {
                a.setEnabled(false);
                b.setEnabled(false);
                c.setEnabled(false);
                bt.setText("Next Question");
                tv1.setText("2.How many tables may be included with a join?");
                a.setText("A. 1");
                b.setText("B. 2");
                c.setText("C.All of the above");
                if (c.isChecked()) {
                    tv2.setText("Right Answer");
                    score += 1;
                } else {
                    tv2.setText("Wrong Answer   C was Right Answer");
                }
                a.setChecked(false);
                b.setChecked(false);
                c.setChecked(false);
                question=4;
                break;
            }
            case 4: {
                a.setEnabled(true);
                b.setEnabled(true);
                c.setEnabled(true);
                bt.setText("Submit");
                tv1.setText("3.Which of the following is the method to join tables in SQL?");
                a.setText("A. Subqueries");
                b.setText("B. Left Outer Join");
                c.setText("C.All of the above");
                tv2.setText("");
                tv3.setText("");
                question = 5;
                break;
            }
            case 5: {
                a.setEnabled(false);
                b.setEnabled(false);
                c.setEnabled(false);
                bt.setText("Next Question");
                tv1.setText("3.Which of the following is the method to join tables in SQL?");
                a.setText("A. Subqueries");
                b.setText("B. Left Outer Join");
                c.setText("C.All of the above");
                if (c.isChecked()) {
                    tv2.setText("Right Answer");
                    score += 1;
                } else {
                    tv2.setText("Wrong Answer   C was Right Answer");
                }
                a.setChecked(false);
                b.setChecked(false);
                c.setChecked(false);
                question=6;
                break;
            }
            case 6: {
                a.setEnabled(true);
                b.setEnabled(true);
                c.setEnabled(true);
                bt.setText("Submit");
                tv1.setText("4.Which of the operation are allowed in a join view?");
                a.setText("A. DELETE");
                b.setText("B. UPDATE");
                c.setText("C.All of the above");
                tv2.setText("");
                tv3.setText("");
                question = 7;
                break;
            }
            case 7: {
                a.setEnabled(false);
                b.setEnabled(false);
                c.setEnabled(false);
                bt.setText("Next Question");
                tv1.setText("4.Which of the operation are allowed in a join view?");
                a.setText("A. DELETE");
                b.setText("B. UPDATE");
                c.setText("C.All of the above");
                if (c.isChecked()) {
                    tv2.setText("Right Answer");
                    score += 1;
                } else {
                    tv2.setText("Wrong Answer   C was Right Answer");
                }
                a.setChecked(false);
                b.setChecked(false);
                c.setChecked(false);
                question = 8;
                break;
            }

            case 8: {
                a.setEnabled(true);
                b.setEnabled(true);
                c.setEnabled(true);
                bt.setText("Submit");
                tv1.setText("5.Which type of joins is not correct?");
                a.setText("A. Inner Join");
                b.setText("B. All join");
                c.setText("C. Left Join");
                tv2.setText("");
                tv3.setText("");
                question = 9;
                break;
            }

            case 9: {
                a.setEnabled(false);
                b.setEnabled(false);
                c.setEnabled(false);
                bt.setText("Finish");
                tv1.setText("5.Which type of joins is not correct?");
                a.setText("A. Inner Join");
                b.setText("B. All join");
                c.setText("C. Left Join");
                if (b.isChecked()) {
                    tv2.setText("Right Answer");
                    score += 1;
                } else {
                    tv2.setText("Wrong Answer   B was Right Answer");
                }
                a.setChecked(false);
                b.setChecked(false);
                c.setChecked(false);
                question=10;
                break;
            }

            case 10: {
                a.setEnabled(false);
                b.setEnabled(false);
                c.setEnabled(false);
                questionno =(question / 2) - 1;
                tv3.setText("Your have correct " + score + " out of " + questionno + " question.");
                bt.setText("Restart");
                question = 0;
                break;
            }
        }
    }
}
