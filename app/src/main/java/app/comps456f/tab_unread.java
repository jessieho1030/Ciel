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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class tab_unread extends Fragment{
    private View rootView;
    private String postId;
    private String category;
    private RecyclerView post_recyclerview;
    private ArrayList<String> subject = new ArrayList<String>();
    private ArrayList<String> name = new ArrayList<String>();
    private ArrayList<String> time = new ArrayList<String>();
    private ArrayList<String> pid = new ArrayList<String>();
    private Discuss_recycler_adapter draU;
    private RecyclerView rv1;

    private DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference().child("comment");
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String reader = user.getUid();

    public tab_unread() {
        // Required empty public constructor
    }

    public static tab_unread newInstance(){
        tab_unread tab_unread = new tab_unread();
        return tab_unread;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab_read, null);
        //rootView = inflater.inflate(R.layout.post_cardview, container, false);

        Bundle b = getActivity().getIntent().getExtras();
        category = b.getString("category");
        Log.d("category",category);

        init();
        return rootView;
    }

    public String toString(){
        return "Unread";
    }


    public void init(){

        getDataBaseData(category);

        rv1 = (RecyclerView) rootView.findViewById(R.id.post_recycler_view);
        rv1.setHasFixedSize(true);

        //dra = new Discuss_recycler_adapter(subject,name,time,pid);
        //rv.setAdapter(dra);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv1.setLayoutManager(llm);
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

                    if(uniqueKey.child("readby").child(reader).getValue(String.class) == null){
                        pid.add(post_id);
                        name.add(post_name);
                        subject.add(post_subject);
                        time.add(post_time);
                    }

                }
                Collections.reverse(subject);
                Collections.reverse(name);
                Collections.reverse(time);
                Collections.reverse(pid);
                draU = new Discuss_recycler_adapter(subject, name, time, pid, category);
                draU.notifyDataSetChanged();

                rv1.setAdapter(draU);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    };



    

}






