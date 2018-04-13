package app.comps456f;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

public class Quiz extends Fragment implements View.OnClickListener {
    private View view;
    private RelativeLayout quiz0_rl, quiz1_rl,quiz2_rl,quiz3_rl;
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";
    public static final String IMAGE_VISIBLE = String.valueOf(View.VISIBLE);

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
        quiz0_rl = (RelativeLayout) view.findViewById(R.id.tutorial0);
        quiz1_rl = (RelativeLayout) view.findViewById(R.id.tutorial1);
        quiz2_rl = (RelativeLayout) view.findViewById(R.id.tutorial2);
        quiz3_rl = (RelativeLayout) view.findViewById(R.id.tutorial3);
        quiz0_rl.setOnClickListener(this);
        quiz1_rl.setOnClickListener(this);
        quiz2_rl.setOnClickListener(this);
        quiz3_rl.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tutorial0:{
                Intent intent = new Intent(getActivity(), Quiz0.class);
                intent.putExtra(EXTRA_DIFFICULTY,"Quiz0");

                startActivity(intent);
                break;
            }
            case R.id.tutorial1:{
                Intent intent = new Intent(getActivity(), Quiz1.class);
                intent.putExtra(EXTRA_DIFFICULTY,"Quiz1");
                intent.putExtra(IMAGE_VISIBLE,1);

                startActivity(intent);
                break;
            }

            case R.id.tutorial2:{
                Intent intent = new Intent(getActivity(), Quiz2.class);
                intent.putExtra(EXTRA_DIFFICULTY,"Quiz2");
                intent.putExtra(IMAGE_VISIBLE,2);
                startActivity(intent);
                break;
            }

            case R.id.tutorial3:{
                Intent intent = new Intent(getActivity(),Quiz3.class);
                intent.putExtra(EXTRA_DIFFICULTY,"Quiz3");
                intent.putExtra(IMAGE_VISIBLE,3);
                startActivity(intent);
                break;
            }

            default:
                break;


        }
    }
}
