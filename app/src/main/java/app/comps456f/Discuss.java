package app.comps456f;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

public class Discuss extends Fragment implements View.OnClickListener{
    private View view;
    private RelativeLayout cat_1, cat_2, cat_3, cat_4;
    public static final String CATEGORY = "cat";
    //public static final String IMAGE_VISIBLE = String.valueOf(View.VISIBLE);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_discuss, container, false);
        init();
        return view;
    }

    public void init(){
        cat_1 = (RelativeLayout) view.findViewById(R.id.category1);
        cat_2 = (RelativeLayout) view.findViewById(R.id.category2);
        cat_3 = (RelativeLayout) view.findViewById(R.id.category3);
        cat_4 = (RelativeLayout) view.findViewById(R.id.category4);
        cat_1.setOnClickListener(this);
        cat_2.setOnClickListener(this);
        cat_3.setOnClickListener(this);
        cat_4.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreatePost.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.category1:{
                Intent intent = new Intent(getActivity(), Discuss_tab.class);
                intent.putExtra(Discuss.CATEGORY,"Join");
                startActivity(intent);
                break;
            }

            case R.id.category2:{
                Intent intent = new Intent(getActivity(), Discuss_tab.class);
                intent.putExtra(Discuss.CATEGORY,"Aggregate");
                startActivity(intent);
                break;
            }

            case R.id.category3:{
                Intent intent = new Intent(getActivity(), Discuss_tab.class);
                intent.putExtra(Discuss.CATEGORY,"Sub-query");
                startActivity(intent);
                break;
            }

            case R.id.category4:{
                Intent intent = new Intent(getActivity(),Discuss_tab.class);
                intent.putExtra(Discuss.CATEGORY,"Other");
                startActivity(intent);
                break;
            }

            default:
                break;


        }
    }

   /*
   @Override
   public void onAttach(Context context) {
       super.onAttach(context);
   }*/

}


