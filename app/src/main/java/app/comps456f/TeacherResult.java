package app.comps456f;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TeacherResult extends Fragment {
    private final String [] quiz_title = {"Quiz 0","Quiz 1", "Quiz 2", "Quiz 3"};
    private final int [] quiz_img = {R.drawable.teach_result, R.drawable.teach_result1, R.drawable.teach_result3, R.drawable.teach_result4};
    private GridView gridView;
    private View view;

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

    public void init() {
        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < quiz_img.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("quiz_img", quiz_img[i]);
            item.put("quiz_title", quiz_title[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                items, R.layout.grid_item, new String[]{"quiz_img", "quiz_title"},
                new int[]{R.id.grid_img, R.id.grid_txt});
        gridView = (GridView)view.findViewById(R.id.gridView);
        gridView.setNumColumns(2);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),TeacherPage.class);
                Bundle bundle = new Bundle();
                System.out.print(quiz_title[position]);
                bundle.putString("quiz_no",quiz_title[position]);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
    }


}
