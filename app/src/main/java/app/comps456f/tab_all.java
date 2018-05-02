package app.comps456f;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class tab_all extends Fragment{
    private View rootView;
    private String postId;
    private String category;
    private RecyclerView post_recyclerview;
    private ArrayList<String> subject = new ArrayList<String>();
    private ArrayList<String> name = new ArrayList<String>();
    private ArrayList<String> time = new ArrayList<String>();
    private ArrayList<String> pid = new ArrayList<String>();
    private Discuss_recycler_adapter draA;
    private RecyclerView rv3;

    private DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference().child("comment");


    public tab_all() {
        // Required empty public constructor
    }

    public static tab_all newInstance(){
        tab_all tab_all = new tab_all();
        return tab_all;
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab_read, null);

        Bundle b = getActivity().getIntent().getExtras();
        category = b.getString("category");
        Log.d("category",category);

        init();
        return rootView;
    }


    public String toString(){
        return "All";
    }


    public void init(){

        getDataBaseData(category);

        rv3 = (RecyclerView) rootView.findViewById(R.id.post_recycler_view);
        rv3.setHasFixedSize(true);

        //dra = new Discuss_recycler_adapter(subject,name,time,pid);
        //rv.setAdapter(dra);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv3.setLayoutManager(llm);
    }

    public void getDataBaseData(final String category){

        mdatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                pid.clear();
                name.clear();
                subject.clear();
                time.clear();
                for (DataSnapshot uniqueKey : dataSnapshot.child(category).getChildren()) {


                    //loop in every field in each comment
                    String post_id = uniqueKey.getKey();
                    String post_name = uniqueKey.child("name").getValue(String.class);
                    String post_subject = uniqueKey.child("subject").getValue(String.class);
                    String post_time = uniqueKey.child("time").getValue(String.class);

                    Log.v(" Student_id","Retrive : " + uniqueKey.child("name").getValue(String.class));
                    Log.v(" Student_name","Retrive : " + uniqueKey.child("subject").getValue(String.class));
                    Log.v(" key","Retrive : " + uniqueKey.getKey());

                    pid.add(post_id);
                    name.add(post_name);
                    subject.add(post_subject);
                    time.add(post_time);
                }
                Collections.reverse(subject);
                Collections.reverse(name);
                Collections.reverse(time);
                Collections.reverse(pid);
                draA = new Discuss_recycler_adapter(subject, name, time, pid, category);
                draA.notifyDataSetChanged();

                rv3.setAdapter(draA);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    };


}






