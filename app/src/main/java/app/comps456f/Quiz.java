package app.comps456f;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

public class Quiz extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getActivity(), QuizPage.class);
        startActivity(intent);
    }
}
