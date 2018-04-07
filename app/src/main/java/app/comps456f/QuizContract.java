package app.comps456f;

import android.provider.BaseColumns;

/**
 * Created by hoyin on 31/3/2018.
 */

public class QuizContract {
    public static class QuestionTable implements BaseColumns{
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NO = "ansno";
        public static final String COLUMN_DIFFICULTY = "difficulty";
    }

}
