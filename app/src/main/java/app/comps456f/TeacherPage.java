package app.comps456f;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by hoyin on 14/4/2018.
 */

    //TODO: Get quiz result from all student

public class TeacherPage extends AppCompatActivity {
    CheckBox reminder;
    String whichQuiz;
    private RecyclerView teacher_result_recycler;
    private ArrayList<String> student_id = new ArrayList<String>();
    private ArrayList<String> student_name = new ArrayList<String>();
    private ArrayList<String> studscore = new ArrayList<>();
    private ArrayList<String> submit = new ArrayList<String>();
    private Teacher_Recycler_adapter tra;


    private DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference().child("user");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_teacher);
        init();

    }

    public void init(){
        Bundle b = getIntent().getExtras();
        whichQuiz = b.getString("quiz_no");
        renderResult(whichQuiz);
        teacher_result_recycler = (RecyclerView)findViewById(R.id.teacher_result_recycler);
        teacher_result_recycler.setLayoutManager(new LinearLayoutManager(this));
        reminder = (CheckBox)findViewById(R.id.reminderCheckBox);

    }
    public void renderResult(String quizno){
        switch (quizno){
            case "Quiz 0":{

                mdatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot uniqueKey : dataSnapshot.getChildren()){
                          // loop for every student

                              //loop in every field in each student

                              String name = uniqueKey.child("name").getValue(String.class);
                              String id =   uniqueKey.child("sid").getValue(String.class);
                             // Log.v(" Student_id","Retrive : " + uniqueKey.child("sid").getValue(String.class));
                             // Log.v(" Student_name","Retrive : " + uniqueKey.child("name").getValue(String.class));
                            student_id.add(id);
                            student_name.add(name);

                                  //loop for quiz record

                                  if (uniqueKey.child("quiz").child("0").getChildren()!= null&& !uniqueKey.child("quiz/0").child("score").getValue().equals("null")
                                          && !uniqueKey.child("quiz/0").child("datetime").getValue().equals("null")) {
                                      String dodate = "Submitted";
                                      String mark = Long.toString(uniqueKey.child("quiz/0").child("score").getValue(Long.class));

                                      //Log.v(" Do-Date", "Retrive : " + uniqueKey.child("quiz/0").child("datetime").getValue(String.class));
                                      //Log.v(" Student_mark", "Retrive : " + uniqueKey.child("quiz/0").child("score").getValue(Long.class));
                                      studscore.add("      "+mark+"      ");
                                      submit.add(dodate);
                                  }
                                  else {
                                      String notSubmit = "Not Submit";
                                      String nomark = "       -       ";
                                      submit.add(notSubmit);
                                      studscore.add(nomark);
                                      // quiz 0 has no record
                                  }
                        }

                       tra = new Teacher_Recycler_adapter(student_id,student_name,studscore,submit,reminder);
                        teacher_result_recycler.setAdapter(tra);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}});
                //studscore [] = scoreQuery;
                break;
            }

            case "Quiz 1":{
                mdatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot uniqueKey : dataSnapshot.getChildren()){
                            // loop for every student
                            //loop in every field in each student

                            String name = uniqueKey.child("name").getValue(String.class);
                            String id =   uniqueKey.child("sid").getValue(String.class);
                            // Log.v(" Student_id","Retrive : " + uniqueKey.child("sid").getValue(String.class));
                            // Log.v(" Student_name","Retrive : " + uniqueKey.child("name").getValue(String.class));
                            student_id.add(id);
                            student_name.add(name);

                            //loop for quiz record

                            if (uniqueKey.child("quiz").child("1")!= null && !uniqueKey.child("quiz/1").child("score").getValue().equals("null")
                                    && !uniqueKey.child("quiz/1").child("datetime").getValue().equals("null")) {
                                String dodate = "Submitted";
                                String mark = Long.toString(uniqueKey.child("quiz/1").child("score").getValue(Long.class));

                                //Log.v(" Do-Date", "Retrive : " + uniqueKey.child("quiz/1").child("datetime").getValue(String.class));
                                //Log.v(" Student_mark", "Retrive : " + uniqueKey.child("quiz/1").child("score").getValue(Long.class));
                                studscore.add("      "+mark+"      ");
                                submit.add(dodate);
                            }
                            else {
                                String notSubmit = "Not Submit";
                                String nomark = "       -       ";
                                submit.add(notSubmit);
                                studscore.add(nomark);
                                // quiz 1 has no record
                            }
                        }

                        tra = new Teacher_Recycler_adapter(student_id,student_name,studscore,submit,reminder);
                        teacher_result_recycler.setAdapter(tra);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}});

                break;
            }

            case "Quiz 2":{
                mdatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot uniqueKey : dataSnapshot.getChildren()){
                            // loop for every student
                            //loop in every field in each student

                            String name = uniqueKey.child("name").getValue(String.class);
                            String id =   uniqueKey.child("sid").getValue(String.class);
                            // Log.v(" Student_id","Retrive : " + uniqueKey.child("sid").getValue(String.class));
                            // Log.v(" Student_name","Retrive : " + uniqueKey.child("name").getValue(String.class));
                            student_id.add(id);
                            student_name.add(name);

                            //loop for quiz record

                            if (uniqueKey.child("quiz").child("2")!= null && !uniqueKey.child("quiz/2").child("score").getValue().equals("null")
                                    && !uniqueKey.child("quiz/2").child("datetime").getValue().equals("null")) {
                                String dodate = "Submitted";
                                String mark = Long.toString(uniqueKey.child("quiz/2").child("score").getValue(Long.class));

                                //Log.v(" Do-Date", "Retrive : " + uniqueKey.child("quiz/2").child("datetime").getValue(String.class));
                                //Log.v(" Student_mark", "Retrive : " + uniqueKey.child("quiz/2").child("score").getValue(Long.class));
                                studscore.add("      "+mark+"      ");
                                submit.add(dodate);
                            }
                            else {
                                String notSubmit = "Not Submit";
                                String nomark = "       -       ";
                                submit.add(notSubmit);
                                studscore.add(nomark);
                                // quiz 2 has no record
                            }
                        }

                        tra = new Teacher_Recycler_adapter(student_id,student_name,studscore,submit,reminder);
                        teacher_result_recycler.setAdapter(tra);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}});
                break;
            }
            case "Quiz 3":{
                mdatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot uniqueKey : dataSnapshot.getChildren()){
                            // loop for every student

                            //loop in every field in each student

                            String name = uniqueKey.child("name").getValue(String.class);
                            String id =   uniqueKey.child("sid").getValue(String.class);
                            // Log.v(" Student_id","Retrive : " + uniqueKey.child("sid").getValue(String.class));
                            // Log.v(" Student_name","Retrive : " + uniqueKey.child("name").getValue(String.class));
                            student_id.add(id);
                            student_name.add(name);


                            //loop for quiz record

                            if (uniqueKey.child("quiz").child("3")!= null && !uniqueKey.child("quiz/3").child("score").getValue().equals("null")
                                    && !uniqueKey.child("quiz/3").child("datetime").getValue().equals("null")) {
                                String dodate = "Submitted";
                                String mark = Long.toString(uniqueKey.child("quiz/3").child("score").getValue(Long.class));

                                //Log.v(" Do-Date", "Retrive : " + uniqueKey.child("quiz/3").child("datetime").getValue(String.class));
                                //Log.v(" Student_mark", "Retrive : " + uniqueKey.child("quiz/3").child("score").getValue(Long.class));
                                studscore.add("      "+mark+"      ");
                                submit.add(dodate);
                            }
                            else {
                                String notSubmit = "Not Submit";
                                String nomark = "       -       ";
                                submit.add(notSubmit);
                                studscore.add(nomark);
                                // quiz 3 has no record
                            }


                        }

                        tra = new Teacher_Recycler_adapter(student_id,student_name,studscore,submit,reminder);
                        teacher_result_recycler.setAdapter(tra);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                break;
            }

        }
    }



}
