package app.comps456f;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class TeacherResult extends Fragment {
    private RecyclerView teacher_result_recycler;
    private View view;
     String [] student_name = {"Esther", "Lazy", "Ed Sheeran", "Fish", "Beeno"};
     String [] student_rank = {"5", "4", "3", "2", "1"};

    private Teacher_Recycler_adapter tra;
    private LinearLayoutManager layoutManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view =  inflater.inflate(R.layout.fragment_teacher_result, container, false);
        init();
        return view;
    }

    public void init(){
        teacher_result_recycler = (RecyclerView)view.findViewById(R.id.teacher_result_recycler);

        layoutManager = new LinearLayoutManager(getActivity());
        teacher_result_recycler.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tra = new Teacher_Recycler_adapter(student_name,student_rank);
        teacher_result_recycler.setVisibility(View.VISIBLE);
        teacher_result_recycler.setAdapter(tra);

    }


}
