package app.comps456f;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hoyin on 31/3/2018.
 */

public class Questions implements Parcelable {
    public static final String DIFFICULTY_Q0 = "Quiz0";
    public static final String DIFFICULTY_Q1 = "Quiz1";
    public static final String DIFFICULTY_Q2 = "Quiz2";
    public static final String DIFFICULTY_Q3 = "Quiz3";

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int ansno;
    private String difficulty;

    public Questions(){}

    public Questions(String question, String option1, String option2, String option3, String option4, int ansno, String diificulty) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.ansno = ansno;
        this.difficulty = diificulty;
    }

    protected Questions(Parcel in) {
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        ansno = in.readInt();
        difficulty = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeInt(ansno);
        dest.writeString(difficulty);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }
    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }
    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnsno() {
        return ansno;
    }

    public void setAnsno(int ansno) {
        this.ansno = ansno;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };
    public static String[] getAllDifficulty(){
        return  new String[]{
                DIFFICULTY_Q0,
                DIFFICULTY_Q1,
                DIFFICULTY_Q2,
                DIFFICULTY_Q3
        };
    }
}
