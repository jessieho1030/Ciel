package app.comps456f;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Result extends Fragment {

    private View view;
    private DatabaseReference rdatabase = FirebaseDatabase.getInstance().getReference().child("user");
    private DatabaseReference nameDb = FirebaseDatabase.getInstance().getReference().child("user");
    private FirebaseAuth firebaseauth;
    private TextView txtProgress, name_tv;
    private ProgressBar progressBar;
    private int pStatus = 0;
    private Handler handler = new Handler();
    private Double avgScore;

    public Result() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_result, container, false);
        init();
        return view;
    }

    public void init(){
        txtProgress = (TextView) view.findViewById(R.id.txtProgress);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        firebaseauth = FirebaseAuth.getInstance();
        name_tv = (TextView)view.findViewById(R.id.resultName);
        String id = firebaseauth.getCurrentUser().getUid();
       nameDb.child(id).child("name").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               String name = dataSnapshot.getValue(String.class);
               name_tv.setText(name);
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

        rdatabase.child(id).child("quiz").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // firebase render all quiz score if not null and then count ++
                double score = 0.0;
                double counter = 0.0;
                for(int i = 0; i<dataSnapshot.getChildrenCount(); i++){
                    // loop all quizzes
                    if(dataSnapshot.child(String.valueOf(i)).getChildren()!= null&& !dataSnapshot.child(String.valueOf(i)).child("score").getValue().equals("null")
                            && !dataSnapshot.child(String.valueOf(i)).child("datetime").getValue().equals("null")) {
                        score += dataSnapshot.child(String.valueOf(i)).child("score").getValue(Long.class);

                        counter ++;
                        Log.v(" quiz " + i, " accumulated score: " + score + " also counter : " +counter);
                    }
                }
                counter *= 10.0;
                avgScore = (score/counter) * 100.0;

                Log.v(" avgScore","is :" +avgScore);
                doProgressBar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }

    private void doProgressBar() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= avgScore.intValue()) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(pStatus);
                            txtProgress.setText(pStatus + " %");
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
            }
        }).start();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
